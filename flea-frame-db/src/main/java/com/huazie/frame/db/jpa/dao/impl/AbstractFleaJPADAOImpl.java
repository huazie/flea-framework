package com.huazie.frame.db.jpa.dao.impl;

import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.DBConstants;
import com.huazie.frame.db.common.exception.DaoException;
import com.huazie.frame.db.jpa.common.FleaJPAQuery;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;

/**
 * <p> 抽象Dao层实现类，该类实现了基本的增删改查功能，可以自行拓展 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class AbstractFleaJPADAOImpl<T> implements IAbstractFleaJPADAO<T> {

    private final static Logger LOGGER = LoggerFactory.getLogger(AbstractFleaJPADAOImpl.class);

    private Class<T> clazz;

    /**
     * <p> 获取T类型的Class对象 </p>
     *
     * @since 1.0.0
     */
    public AbstractFleaJPADAOImpl() {
        // 获取泛型类的子类对象的Class对象
        Class clz = getClass();
        // 获取子类对象的泛型父类类型（也就是AbstractDaoImpl<T>）
        ParameterizedType type = (ParameterizedType) clz.getGenericSuperclass();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Type={}", type);
        }
        Type[] types = type.getActualTypeArguments();
        clazz = (Class<T>) types[0];
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ClassName={}", clazz.getName());
        }
    }

    @Override
    public T query(long entityId) throws Exception {
        return queryById(entityId);
    }

    @Override
    public T query(String entityId) throws Exception {
        return queryById(entityId);
    }

    /**
     * <p> 根据主键编号查询数据行对应的实体类信息 </p>
     *
     * @param entityId 主键编号
     * @return 数据行对应的实体类信息
     * @throws Exception
     */
    protected T queryById(Object entityId) throws Exception {
        checkPrimaryKey(entityId);
        T t = getEntityManager().find(clazz, entityId);
        return t;
    }

    @Override
    public List<T> query(Map<String, Object> paramMap) throws Exception {
        FleaJPAQuery query = getQuery(null);
        query.equal(paramMap);
        List<T> ts = query.getResultList();
        return ts;
    }

    @Override
    public List<T> query(Map<String, Object> paramMap, String attrName, String orderBy) throws Exception {
        FleaJPAQuery query = getQuery(null);
        query.equal(paramMap);
        query.addOrderby(attrName, orderBy);
        List<T> ts = query.getResultList();
        return ts;
    }

    @Override
    public List<T> query(Map<String, Object> paramMap, int start, int max) throws Exception {
        FleaJPAQuery query = getQuery(null);
        query.equal(paramMap);
        List<T> ts = query.getResultList(start, max);
        return ts;
    }

    @Override
    public List<T> query(Map<String, Object> paramMap, String attrName, String orderBy, int start, int max)
            throws Exception {
        FleaJPAQuery query = getQuery(null);
        query.equal(paramMap);
        query.addOrderby(attrName, orderBy);
        List<T> ts = query.getResultList(start, max);
        return ts;
    }

    @Override
    public List<T> queryAll() throws Exception {
        FleaJPAQuery query = getQuery(null);
        List<T> ts = query.getResultList();
        return ts;
    }

    @Override
    public List<T> queryAll(String attrName, String orderBy) throws Exception {
        FleaJPAQuery query = getQuery(null);
        query.addOrderby(attrName, orderBy);
        List<T> ts = query.getResultList();
        return ts;
    }

    @Override
    public List<T> queryAll(int start, int max) throws Exception {
        FleaJPAQuery query = getQuery(null);
        List<T> ts = query.getResultList(start, max);
        return ts;
    }

    @Override
    public List<T> queryAll(String attrName, String orderBy, int start, int max) throws Exception {
        FleaJPAQuery query = getQuery(null);
        query.addOrderby(attrName, orderBy);
        List<T> ts = query.getResultList(start, max);
        return ts;
    }

    @Override
    public long queryCount() throws Exception {
        FleaJPAQuery query = getQuery(Long.class);
        query.countDistinct();
        long count = ((Long) query.getSingleResult());
        return count;
    }

    @Override
    public long queryCount(Map<String, Object> paramMap) throws Exception {
        FleaJPAQuery query = getQuery(Long.class);
        // 添加Where子句查询条件
        query.equal(paramMap);
        // 设置调用SQL的COUNT函数统计数目
        query.countDistinct();
        long count = ((Long) query.getSingleResult());
        return count;
    }

    @Override
    public boolean remove(long entityId) throws Exception {
        return removeById(entityId);
    }

    @Override
    public boolean remove(String entityId) throws Exception {
        return removeById(entityId);
    }

    /**
     * <p> 根据主键编号删除指定行数据 </p>
     *
     * @param entityId 主键数据
     * @return true : 删除成功; false : 删除失败
     * @throws Exception
     */
    protected boolean removeById(Object entityId) throws Exception {
        checkPrimaryKey(entityId);
        final T old = queryById(entityId);
        if (ObjectUtils.isNotEmpty(old)) {
            getEntityManager().remove(old);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public T update(T entity) throws Exception {
        if (ObjectUtils.isEmpty(entity)) {
            throw new DaoException("ERROR-DB-DAO0000000012");
        }
        T t = getEntityManager().merge(entity);
        return t;
    }

    @Override
    public List<T> batchUpdate(List<T> entities) throws Exception {
        if (CollectionUtils.isEmpty(entities)) {
            throw new DaoException("ERROR-DB-DAO0000000013");
        }
        for (T t : entities) {
            getEntityManager().merge(t);
        }
        return entities;
    }

    @Override
    public void save(T entity) throws Exception {
        if (ObjectUtils.isEmpty(entity)) {
            throw new DaoException("ERROR-DB-DAO0000000012");
        }
        getEntityManager().persist(entity);
    }

    @Override
    public void batchSave(List<T> entities) throws Exception {
        if (CollectionUtils.isEmpty(entities)) {
            throw new DaoException("ERROR-DB-DAO0000000013");
        }
        for (T t : entities) {
            getEntityManager().persist(t);
        }
    }

    public boolean update(T entity, String a) throws Exception {
        if (entity == null) {
            throw new DaoException("The entity need to be updated is null");
        }
        String entityName = StringUtils.toLowerCaseInitial(clazz.getSimpleName());
        StringBuilder sql = new StringBuilder(
                DBConstants.SQLConstants.SQL_UPDATE + DBConstants.SQLConstants.SQL_BLANK + clazz.getSimpleName() + DBConstants.SQLConstants.SQL_BLANK
                        + entityName + DBConstants.SQLConstants.SQL_BLANK + DBConstants.SQLConstants.SQL_SET);
        StringBuilder whereSql = new StringBuilder(DBConstants.SQLConstants.SQL_WHERE + DBConstants.SQLConstants.SQL_BLANK);//where子句
        Field[] fields = entity.getClass().getDeclaredFields();//获取该实体类中的属性集
        Map<String, Object> map = new HashMap<String, Object>();//用于存储更新需要的参数
        boolean isFoundPrimaryKey = false;//实体中是否存在主键
        for (int i = 0; i < fields.length; i++) {
            // 2 表示private修饰的属性，可以过滤掉定义的静态变量等
            if (fields[i].getModifiers() != Modifier.PRIVATE) {
                continue;
            } else {
                String attrName = fields[i].getName();// 属性的字段名称
                String getter = CommonConstants.MethodConstants.GET + StringUtils.toUpperCaseInitial(attrName);// 属性的get方法名
                Method method = entity.getClass().getMethod(getter, new Class[]{});
                Object value = method.invoke(entity, new Object[]{});//该属性对应的值
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("type={},name={},value={}", fields[i].getType().toString(),
                            fields[i].getName(), value);
                }
                boolean isPrimarykey = false;//判断当前的属性是否是主键
                if (!isFoundPrimaryKey) {
                    Annotation[] annotations = fields[i].getAnnotations();//获取属性上的注解
                    if (annotations == null || annotations.length == 0) {//表示属性上没有注解
                        annotations = method.getAnnotations();//获取方法上的注解
                        if (annotations == null || annotations.length == 0) {//表示方法上没有注解
                            throw new DaoException("The Entity of " + clazz.getSimpleName() + "is not be annotated");
                        }
                    }
                    for (Annotation an : annotations) {//遍历属性或get方法上的注解（注解一般要么全部写在属性上，要么全部写在get方法上）
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
                            whereSql.append(entityName).append(DBConstants.SQLConstants.SQL_DOT).append(attrName)
                                    .append(DBConstants.SQLConstants.SQL_BLANK).append(DBConstants.SQLConstants.SQL_EQUAL)
                                    .append(DBConstants.SQLConstants.SQL_BLANK).append(DBConstants.SQLConstants.SQL_COLON).append(attrName);
                            isPrimarykey = true;// true表示该字段是主键
                            isFoundPrimaryKey = true;// 表示找到了主键
                            break;
                        }
                    }
                }

                if (value == null) {//值为空，直接跳过，继续下一个属性
                    continue;
                } else {
                    map.put(attrName, value);//添加不为空的数据（基本数据类型有默认值，这个需要关注）
                    if (!isPrimarykey) {// 不是主键才可以添加在SET子句中
                        sql.append(DBConstants.SQLConstants.SQL_BLANK).append(entityName).append(DBConstants.SQLConstants.SQL_DOT)
                                .append(attrName).append(DBConstants.SQLConstants.SQL_BLANK).append(DBConstants.SQLConstants.SQL_EQUAL)
                                .append(DBConstants.SQLConstants.SQL_BLANK).append(DBConstants.SQLConstants.SQL_COLON).append(attrName)
                                .append(DBConstants.SQLConstants.SQL_COMMA);
                    }
                }
            }
        }
        if (isFoundPrimaryKey) {//只有存在主键的实体，才能去执行更新操作
            sql.deleteCharAt(sql.length() - 1);// 去掉最后的逗号
            sql.append(DBConstants.SQLConstants.SQL_BLANK).append(whereSql);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("SQL = {}", sql);
            }
            Query query = getEntityManager().createQuery(sql.toString());
            Set<String> keySet = map.keySet();
            Iterator<String> it = keySet.iterator();
            while (it.hasNext()) {
                String key = it.next();
                Object value = map.get(key);
                query.setParameter(key, value);// 设置参数
            }
            int row = query.executeUpdate();// 执行更新语句
            if (row == 1) {
                return true;
            } else {
                return false;
            }
        } else {
            throw new DaoException("The primary key of " + clazz.getSimpleName() + " is not exist");
        }
    }

    @Override
    public List<T> query(String relationId, T t) throws Exception {

        return null;
    }

    /**
     * <p> 获取指定的查询对象 </p>
     *
     * @return 自定义Flea JPA查询对象
     * @since 1.0.0
     */
    protected FleaJPAQuery getQuery(Class result) {
        FleaJPAQuery query = FleaJPAQuery.getQuery();
        query.init(getEntityManager(), clazz, result);
        return query;
    }

    /**
     * <p> 校验主键合法性 </p>
     *
     * @param entityId 实体类对应的主键编号
     * @throws DaoException 数据操作层异常
     * @since 1.0.0
     */
    private void checkPrimaryKey(Object entityId) throws DaoException {
        if (entityId.getClass() == long.class || entityId.getClass() == Long.class) {
            if (Long.valueOf(entityId.toString()) <= 0) {
                // 主键字段必须是正整数
                throw new DaoException("ERROR-DB-DAO0000000009");
            }
        } else if (entityId.getClass() == String.class) {
            if (ObjectUtils.isEmpty(entityId)) {
                // 主键字段不能为空
                throw new DaoException("ERROR-DB-DAO0000000010");
            }
        } else {
            // 主键必须是long(Long) 或 String
            throw new DaoException("ERROR-DB-DAO0000000011");
        }
    }

    /**
     * <p> 获取EntityManager对象 </p>
     *
     * @return EntityManager对象
     * @since 1.0.0
     */
    protected abstract EntityManager getEntityManager();

}
