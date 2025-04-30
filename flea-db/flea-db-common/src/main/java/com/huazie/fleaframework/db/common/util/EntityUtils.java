package com.huazie.fleaframework.db.common.util;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.ArrayUtils;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.ExceptionUtils;
import com.huazie.fleaframework.common.util.NumberUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.ReflectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.db.common.FleaTable;
import com.huazie.fleaframework.db.common.FleaTableGenerator;
import com.huazie.fleaframework.db.common.exceptions.DaoException;
import com.huazie.fleaframework.db.common.exceptions.FleaDBException;
import com.huazie.fleaframework.db.common.sql.template.config.Param;
import com.huazie.fleaframework.db.common.sql.template.config.Relation;
import com.huazie.fleaframework.db.common.sql.template.config.Template;
import com.huazie.fleaframework.db.common.table.pojo.Column;
import com.huazie.fleaframework.db.common.table.pojo.SplitTable;

import javax.persistence.Id;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 实体工具类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public class EntityUtils {

    private EntityUtils() {
    }

    /**
     * 获取SQL模板的Map集合，便于根据SQL模板id查找
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
     * 获取SQL模板参数的Map集合，便于根据SQL模板参数id查找
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
     * 获取SQL模板和模板参数关联关系的Map集合，便于根据关系id查找
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
                boolean isPrimarykey = false; // 判断当前的属性是否是主键
                boolean isNullable = false; // 判断当前的属性是否可空
                boolean isUnique = false; // 判断当前的属性是否唯一
                String pkColumnValue = ""; // ID生成器表中的主键值模板
                boolean generatorFlag = true; // 生成器标识，默认为true【true：生成器表在模板库中 false：生成器表在分库中】

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
                    // 生成器标识
                    if (FleaTableGenerator.class.getName().equals(an.annotationType().getName())) {
                        FleaTableGenerator fleaTableGenerator = (FleaTableGenerator) an;
                        generatorFlag = fleaTableGenerator.generatorFlag();
                    }
                }
                column.setTabColumnName(colName);
                column.setPrimaryKey(isPrimarykey);
                column.setNullable(isNullable);
                column.setUnique(isUnique);
                column.setPkColumnValue(pkColumnValue);
                column.setGeneratorFlag(generatorFlag);

                columns.add(column);
            }
        }
        return columns;
    }

    /**
     * 根据指定的属性名及其属性值，获取指定实体
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
     * 获取真实的表名，如是分表，则获取分表名
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

    private static final Map<Class<?>, Field> idFieldCache = new ConcurrentHashMap<>();

    /**
     * 动态创建实体类实例并设置主键字段的值
     *
     * <p>通过反射机制实现以下步骤：<br>
     * 1. 调用默认构造方法创建实例<br>
     * 2. 查找类及其父类中标记 {@link javax.persistence.Id} 注解的主键字段<br>
     * 3. 将主键值设置到实例的对应字段</p>
     *
     * @param clazz    实体类的 Class 对象，需满足以下条件：<br>
     *                 - 提供无参构造方法<br>
     *                 - 包含标记 {@link javax.persistence.Id} 的字段
     * @param keyValue 主键值，类型必须与主键字段的类型兼容
     * @param <T>      实体类型泛型
     * @return 已设置主键的实体类实例
     * @throws FleaDBException 出现以下情况时抛出：<br>
     *                         - 类无无参构造方法<br>
     *                         - 主键字段未找到<br>
     *                         - 字段访问权限不足<br>
     *                         - 主键值类型不匹配
     * @since 2.0.0
     */
    public static <T> T createEntityWithId(Class<T> clazz, Object keyValue) {
        T instance = null;
        try {
            instance = clazz.getDeclaredConstructor().newInstance();
            Field idField = findIdField(clazz);
            idField.setAccessible(true);
            idField.set(instance, keyValue);
        } catch (Exception e) {
            ExceptionUtils.throwFleaException(FleaDBException.class, e);
        }
        return instance;
    }

    private static Field findIdField(Class<?> clazz) {
        // 1. 先从缓存中查找
        Field cachedField = idFieldCache.get(clazz);
        if (cachedField != null) {
            return cachedField;
        }

        // 2. 缓存未命中时遍历字段
        Class<?> currentClass = clazz;
        while (currentClass != null && currentClass != Object.class) {
            for (Field field : currentClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(Id.class)) {
                    // 3. 存入缓存（线程安全）
                    synchronized (idFieldCache) {
                        if (!idFieldCache.containsKey(clazz)) {
                            idFieldCache.put(clazz, field);
                        }
                    }
                    return field;
                }
            }
            currentClass = currentClass.getSuperclass();
        }

        throw new FleaDBException("No @Id field found in class: " + clazz.getName());
    }

}
