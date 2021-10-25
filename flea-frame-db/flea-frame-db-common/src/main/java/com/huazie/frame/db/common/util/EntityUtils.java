package com.huazie.frame.db.common.util;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.ArrayUtils;
import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.ExceptionUtils;
import com.huazie.frame.common.util.NumberUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.ReflectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.FleaTable;
import com.huazie.frame.db.common.exception.DaoException;
import com.huazie.frame.db.common.sql.template.config.Param;
import com.huazie.frame.db.common.sql.template.config.Relation;
import com.huazie.frame.db.common.sql.template.config.Template;
import com.huazie.frame.db.common.table.pojo.Column;
import com.huazie.frame.db.common.table.pojo.SplitTable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
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

    private EntityUtils() {
    }

    /**
     * <p> 获取SQL模板的Map集合，便于根据SQL模板id查找 </p>
     *
     * @param templates SQL模板的List对象
     * @return SQL模板的Map集合
     * @since 1.0.0
     */
    public static Map<String, Template> toTemplatesMap(List<Template> templates) {
        Map<String, Template> templatesMap = new HashMap<>();
        for (Template template : templates) {
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
        Map<String, Param> paramsMap = new HashMap<>();
        for (Param param : params) {
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
        Map<String, Relation> relationsMap = new HashMap<>();
        for (Relation relation : relations) {
            relationsMap.put(relation.getId(), relation);
        }
        return relationsMap;
    }

    /**
     * 获取实体类entity的列相关的信息
     *
     * @param entity 实体类对象
     * @return 实体对象的列数组
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static Column[] toColumnsArray(Object entity) throws CommonException {
        List<Column> columnList = toColumnsList(entity);
        if (CollectionUtils.isNotEmpty(columnList)) {
            return columnList.toArray(new Column[0]);
        } else {
            return null;
        }
    }

    /**
     * 获取实体entity的列相关信息
     *
     * @param entity 实体类对象
     * @return 实体对象的列集合
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static List<Column> toColumnsList(Object entity) throws CommonException {
        // 获取该实体类中的属性集
        Field[] fields = entity.getClass().getDeclaredFields();
        if (ArrayUtils.isEmpty(fields)) {
            return null;
        }
        List<Column> columns = new ArrayList<>();
        for (Field field : fields) {
            // 2 表示private修饰的属性，可以过滤掉定义的静态变量等
            if (field.getModifiers() == Modifier.PRIVATE) {
                Column column = new Column();
                column.setAttrType(field.getType());// 属性的类型

                String attrName = field.getName();
                column.setAttrName(field.getName());// 属性的字段名称

                Object value = ReflectUtils.getObjectAttrValue(entity, attrName);
                column.setAttrValue(value);

                String colName = ""; // 当前属性对应的字段名
                boolean isPrimarykey = false;// 判断当前的属性是否是主键
                boolean isNullable = false;// 判断当前的属性是否可空
                boolean isUnique = false;// 判断当前的属性是否唯一
                String pkColumnValue = ""; // ID生成器表中的主键值模板

                Annotation[] annotations = field.getAnnotations();// 获取属性上的注解
                if (ArrayUtils.isEmpty(annotations)) {// 表示属性上没有注解
                    Method method = ReflectUtils.getObjectAttrMethod(entity, attrName);
                    if (ObjectUtils.isNotEmpty(method)) {
                        annotations = method.getAnnotations();// 获取方法上的注解
                        // 实体类上 [{0}] 没有注解
                        ArrayUtils.checkEmpty(annotations, DaoException.class, "ERROR-DB-DAO0000000012", entity.getClass().getSimpleName());
                    }
                }
                // 遍历属性或get方法上的注解（注解一般要么全部写在属性上，要么全部写在get方法上）
                for (Annotation an : annotations) {
                    // 兼容JPA
                    if (javax.persistence.Id.class.getName().equals(an.annotationType().getName())) {// 表示该属性是主键
                        if (ObjectUtils.isNotEmpty(value)) {
                            if (long.class == field.getType() || Long.class == field.getType()) {// 该实体的主键是long类型
                                if (!NumberUtils.isPositiveNumber(Long.valueOf(value.toString()))) {
                                    // 主键字段必须是正整数
                                    ExceptionUtils.throwCommonException(DaoException.class, "ERROR-DB-DAO0000000007");
                                }
                            } else if (String.class == field.getType()) {// 该实体的主键是String类型
                                // 主键字段不能为空
                                ObjectUtils.checkEmpty(value, DaoException.class, "ERROR-DB-DAO0000000008");
                            } else {
                                // 主键必须是long(Long) 或 String
                                ExceptionUtils.throwCommonException(DaoException.class, "ERROR-DB-DAO0000000009");
                            }
                        }
                        isPrimarykey = true;// true表示该字段是主键
                    }
                    // 兼容JPA
                    if (javax.persistence.Column.class.getName().equals(an.annotationType().getName())) {
                        javax.persistence.Column col = (javax.persistence.Column) an;
                        colName = col.name();
                        isNullable = col.nullable();
                        isUnique = col.unique();
                    }
                    // 兼容JPA
                    if (javax.persistence.TableGenerator.class.getName().equals(an.annotationType().getName())) {
                        javax.persistence.TableGenerator tableGenerator = (javax.persistence.TableGenerator) an;
                        pkColumnValue = tableGenerator.pkColumnValue();
                    }
                }
                column.setTabColumnName(colName);
                column.setPrimaryKey(isPrimarykey);
                column.setNullable(isNullable);
                column.setUnique(isUnique);
                column.setPkColumnValue(pkColumnValue);

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
    public static Object getEntity(Object[] objs, String attrName, Object attrValue) {
        Object object = null;
        if (ArrayUtils.isNotEmpty(objs)) {
            for (Object obj : objs) {
                Object value = ReflectUtils.getObjectAttrValue(obj, attrName); // 该属性对应的值
                if (value != null && value.equals(attrValue)) {
                    object = obj;
                    break;
                }
            }
        }
        return object;
    }

    /**
     * 根据实体类获取对应的表名信息，实体类上包含 @Table 或者 @FleaTable 注解）
     *
     * @param entity 被@Table或者@FleaTable注解修饰的实体类
     * @return 实体类对应数据库表名
     * @since 1.0.0
     */
    public static String getTableName(Object entity) {
        return getTableName(entity.getClass());
    }

    /**
     * 根据实体类的Class类型获取对应的表名信息，实体类上包含 @Table 或者 @FleaTable 注解）
     *
     * @param entityClass 被@Table或者@FleaTable注解修饰的实体类的Class类型
     * @return 实体类对应数据库表名
     * @since 1.0.0
     */
    public static String getTableName(Class<?> entityClass) {
        String tableName = "";
        javax.persistence.Table tableAnnotation = entityClass.getAnnotation(javax.persistence.Table.class);
        if (ObjectUtils.isNotEmpty(tableAnnotation)) {
            tableName = tableAnnotation.name();
        } else {
            FleaTable fleaTableAnnotation = entityClass.getAnnotation(FleaTable.class);
            if (ObjectUtils.isNotEmpty(fleaTableAnnotation)) {
                tableName = fleaTableAnnotation.name();
            }
        }
        return tableName;
    }

    /**
     * <p> 获取真实的表名，如是分表，则获取分表名 </p>
     *
     * @param entity 实体类对象实例
     * @return 真实的表名，如是分表，则返回相应的分表名
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static SplitTable getSplitTable(Object entity) throws CommonException {
        // 从实体类上获取表名
        String tableName = getTableName(entity);
        // 请检查初始实体类(其上的注解@Table或者@FleaTable对应的表名不能为空)
        StringUtils.checkBlank(tableName, DaoException.class, "ERROR-DB-SQT0000000012");

        // 获取实体类T的对象的属性列相关信息
        Column[] entityCols = toColumnsArray(entity);
        // 请检查初始实体类（实体类的属性列相关信息不存在）
        ArrayUtils.checkEmpty(entityCols, DaoException.class, "ERROR-DB-SQT0000000016");
        return FleaSplitUtils.getSplitTable(tableName, entityCols);
    }

}
