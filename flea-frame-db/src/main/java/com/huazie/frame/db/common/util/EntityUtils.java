package com.huazie.frame.db.common.util;

import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.util.ArrayUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.ReflectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.exception.DaoException;
import com.huazie.frame.db.common.sql.template.config.Param;
import com.huazie.frame.db.common.sql.template.config.Relation;
import com.huazie.frame.db.common.sql.template.config.Template;
import com.huazie.frame.db.common.table.pojo.Column;

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
     * <p> 获取SQL模板的Map集合，便于根据SQL模板id查找 </p>
     *
     * @param templates SQL模板的List对象
     * @return SQL模板的Map集合
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
     * <p> 获取SQL模板参数的Map集合，便于根据SQL模板参数id查找 </p>
     *
     * @param params SQL模板参数的List对象
     * @return SQL模板参数的Map集合
     * @since 1.0.0
     */
    public static Map<String, Param> toParamsMap(List<Param> params) {
        Map<String, Param> paramsMap = new HashMap<String, Param>();
        Iterator<Param> paramsIt = params.iterator();
        while (paramsIt.hasNext()) {
            Param param = paramsIt.next();
            paramsMap.put(param.getId(), param);
        }
        return paramsMap;
    }

    /**
     * <p> 获取SQL模板和模板参数关联关系的Map集合，便于根据关系id查找 </p>
     *
     * @param relations SQL模板和模板参数关联关系的List对象
     * @return SQL模板和模板参数关联关系的Map集合
     * @since 1.0.0
     */
    public static Map<String, Relation> toRelationsMap(List<Relation> relations) {
        Map<String, Relation> relationsMap = new HashMap<String, Relation>();
        Iterator<Relation> relationsIt = relations.iterator();
        while (relationsIt.hasNext()) {
            Relation relation = relationsIt.next();
            relationsMap.put(relation.getId(), relation);
        }
        return relationsMap;
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
        // 获取该实体类中的属性集
        Field[] fields = entity.getClass().getDeclaredFields();
        if (ArrayUtils.isEmpty(fields)) {
            return null;
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
                Object value = ReflectUtils.getObjectAttrValue(entity, attrName);
                column.setAttrValue(value);

                String colName = ""; // 当前属性对应的字段名
                boolean isPrimarykey = false;// 判断当前的属性是否是主键
                boolean isNullable = false;// 判断当前的属性是否可空
                boolean isUnique = false;// 判断当前的属性是否唯一

                Annotation[] annotations = fields[i].getAnnotations();// 获取属性上的注解
                if (ArrayUtils.isEmpty(annotations)) {// 表示属性上没有注解
                    annotations = method.getAnnotations();// 获取方法上的注解
                    if (ArrayUtils.isEmpty(annotations)) {// 表示方法上没有注解
                        // 实体类上 [{0}] 没有注解
                        throw new DaoException("ERROR-DB-DAO0000000014", entity.getClass().getSimpleName());
                    }
                }
                // 遍历属性或get方法上的注解（注解一般要么全部写在属性上，要么全部写在get方法上）
                for (Annotation an : annotations) {
                    // 兼容JPA
                    if (javax.persistence.Id.class.getName().equals(an.annotationType().getName())) {// 表示该属性是主键
                        if (ObjectUtils.isNotEmpty(value)) {
                            if (long.class == fields[i].getType() || Long.class == fields[i].getType()) {// 该实体的主键是long类型
                                if (Long.valueOf(value.toString()) <= 0) {
                                    // 主键字段必须是正整数
                                    throw new DaoException("ERROR-DB-DAO0000000009");
                                }
                            } else if (String.class == fields[i].getType()) {// 该实体的主键是String类型
                                if (ObjectUtils.isEmpty(value)) {
                                    // 主键字段不能为空
                                    throw new DaoException("ERROR-DB-DAO0000000010");
                                }
                            } else {
                                // 主键必须是long(Long) 或 String
                                throw new DaoException("ERROR-DB-DAO0000000011");
                            }
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
        if (ArrayUtils.isNotEmpty(objs)) {
            for (int i = 0; i < objs.length; i++) {
                Object obj = objs[i];
                Field field = obj.getClass().getDeclaredField(attrName);
                // 属性的get方法名
                String getter = CommonConstants.MethodConstants.GET + StringUtils.toUpperCaseInitial(field.getName());
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
        if (ObjectUtils.isNotEmpty(tableAnnotation)) {
            javax.persistence.Table table = (javax.persistence.Table) tableAnnotation;
            tableName = table.name();
        } else {
            Annotation fleaTableAnnotation = entity.getClass().getAnnotation(com.huazie.frame.db.common.FleaTable.class);
            if (ObjectUtils.isNotEmpty(fleaTableAnnotation)) {
                com.huazie.frame.db.common.FleaTable fleaTable = (com.huazie.frame.db.common.FleaTable) fleaTableAnnotation;
                tableName = fleaTable.name();
            }
        }
        return tableName;
    }

}
