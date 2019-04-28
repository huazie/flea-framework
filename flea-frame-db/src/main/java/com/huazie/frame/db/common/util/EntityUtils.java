package com.huazie.frame.db.common.util;

import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.util.ArrayUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.exception.DaoException;
import com.huazie.frame.db.common.sql.template.config.Template;
import com.huazie.frame.db.common.tab.column.Column;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p> 实体工具类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class EntityUtils {

    /**
     * <p> 获取Sql模板的Map集合，便于根据id查找 </p>
     *
     * @param templates Sql模板的List对象
     * @return Sql模板的Map集合
     * @since 1.0.0
     */
    public static Map<String, Template> toTemplatesMap(List<Template> templates) {
        Map<String, Template> templatesMap = new HashMap<String, Template>();
        Iterator<Template> templatesIt = templates.iterator();
        while (templatesIt.hasNext()) {
            Template template = templatesIt.next();
            templatesMap.put(template.getId(), template);
        }
        return templatesMap;
    }

    /**
     * 获取实体类entity的列相关的信息
     *
     * @param entity 实体类对象
     * @return 实体对象的列数组
     * @throws Exception
     * @since 1.0.0
     */
    public static Column[] toColumnsArray(Object entity) throws Exception {
        return toColumnsList(entity).toArray(new Column[0]);
    }

    /**
     * 获取实体entity的列相关信息
     *
     * @param entity 实体类对象
     * @return 实体对象的列集合
     * @throws Exception
     * @since 1.0.0
     */
    public static List<Column> toColumnsList(Object entity) throws Exception {
        Field[] fields = entity.getClass().getDeclaredFields();// 获取该实体类中的属性集
        if (fields == null || fields.length <= 0) {
            throw new Exception("请检查实体类的属性（属性不能为空）");
        }
        List<Column> columns = new ArrayList<Column>();
        for (int i = 0; i < fields.length; i++) {
            // 2 表示private修饰的属性，可以过滤掉定义的静态变量等
            if (fields[i].getModifiers() != Modifier.PRIVATE) {
                continue;
            } else {
                Column column = new Column();
                column.setAttrType(fields[i].getType());// 属性的类型

                String attrName = fields[i].getName();
                column.setAttrName(fields[i].getName());// 属性的字段名称

                String getter = CommonConstants.MethodConstants.GET + StringUtils.toUpperCaseInitial(attrName);// 属性的get方法名
                Method method = entity.getClass().getMethod(getter, new Class[]{});
                Object value = method.invoke(entity, new Object[]{});// 该属性对应的值
                column.setAttrValue(value);

                String colName = ""; // 当前属性对应的字段名
                boolean isPrimarykey = false;// 判断当前的属性是否是主键
                boolean isNullable = false;// 判断当前的属性是否可空
                boolean isUnique = false;// 判断当前的属性是否唯一

                Annotation[] annotations = fields[i].getAnnotations();// 获取属性上的注解
                if (ArrayUtils.isEmpty(annotations)) {// 表示属性上没有注解
                    annotations = method.getAnnotations();// 获取方法上的注解
                    if (ArrayUtils.isEmpty(annotations)) {// 表示方法上没有注解
                        throw new DaoException("The Entity of " + entity.getClass().getSimpleName() + "is not be annotated");
                    }
                }
                // 遍历属性或get方法上的注解（注解一般要么全部写在属性上，要么全部写在get方法上）
                for (Annotation an : annotations) {
                    // 兼容JPA
                    if (javax.persistence.Id.class.getName().equals(an.annotationType().getName())) {// 表示该属性是主键
                        if (fields[i].getType() == long.class || fields[i].getType() == Long.class) {// 该实体的主键是long类型
                            if (Long.valueOf(value.toString()) <= 0) {
                                throw new DaoException("The primary key '" + attrName + "' is not a positive Integer");
                            }
                        } else if (fields[i].getType() == String.class) {// 该实体的主键是String类型
                            if (ObjectUtils.isEmpty(value)) {
                                throw new DaoException("The primary key '" + attrName + "' is null or empty");
                            }
                        } else {
                            throw new DaoException("The primary key '" + attrName + "' must be long(Long) or String type");
                        }
                        isPrimarykey = true;// true表示该字段是主键
                    }
                    // 兼容JPA
                    if (javax.persistence.Column.class.getName().equals(an.annotationType().getName())) {// 含有Column注解
                        javax.persistence.Column col = (javax.persistence.Column) an;
                        colName = col.name();
                        isNullable = col.nullable();
                        isUnique = col.unique();
                    }
                }
                column.setTabColumnName(colName);
                column.setPrimaryKey(isPrimarykey);
                column.setNullable(isNullable);
                column.setUnique(isUnique);

                columns.add(column);
            }
        }
        return columns;
    }

    /**
     * <p> 根据指定的属性名及其属性值，获取指定实体 </p>
     *
     * @param objs      实体数组
     * @param attrName  属性名
     * @param attrValue 属性值
     * @return 实体对象
     * @since 1.0.0
     */
    public static Object getEntity(Object[] objs, String attrName, Object attrValue) throws Exception {
        Object object = null;
        if (objs != null && objs.length > 0) {
            for (int i = 0; i < objs.length; i++) {
                Object obj = objs[i];
                Field field = obj.getClass().getDeclaredField(attrName);
                if (field == null) {
                    throw new Exception("请检查实体类中的属性（该属性" + attrName + "不存在）");
                }
                String getter = CommonConstants.MethodConstants.GET + StringUtils.toUpperCaseInitial(attrName);// 属性的get方法名
                Method method = obj.getClass().getMethod(getter, new Class[]{});
                Object value = method.invoke(obj, new Object[]{});// 该属性对应的值
                if (value != null && value.equals(attrValue)) {
                    object = obj;
                    break;
                }
            }
        }
        return object;
    }

    /**
     * <p> 根据实体类获取对应的表名信息 </p>
     * <p>（前提：实体类包含@Table或者@FleaTable注解）</p>
     *
     * @param entity 被@Table或者@FleaTable注解修饰的实体类
     * @return 实体类对应数据库表名
     * @since 1.0.0
     */
    public static String getTableName(Object entity) {
        String tableName = "";
        Annotation tableAnnotation = entity.getClass().getAnnotation(javax.persistence.Table.class);
        if (tableAnnotation != null) {
            javax.persistence.Table table = (javax.persistence.Table) tableAnnotation;
            tableName = table.name();
        } else {
            Annotation fleaTableAnnotation = entity.getClass().getAnnotation(com.huazie.frame.db.common.FleaTable.class);
            if (fleaTableAnnotation != null) {
                com.huazie.frame.db.common.FleaTable fleaTable = (com.huazie.frame.db.common.FleaTable) fleaTableAnnotation;
                tableName = fleaTable.name();
            }
        }
        return tableName;
    }

}