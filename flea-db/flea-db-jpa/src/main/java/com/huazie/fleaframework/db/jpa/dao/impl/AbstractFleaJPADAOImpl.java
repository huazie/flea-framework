package com.huazie.fleaframework.db.jpa.dao.impl;

import com.huazie.fleaframework.common.FleaEntity;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.exceptions.FleaException;
import com.huazie.fleaframework.common.pool.FleaObjectPoolFactory;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.ExceptionUtils;
import com.huazie.fleaframework.common.util.NumberUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.db.common.DBConstants;
import com.huazie.fleaframework.db.common.exceptions.DaoException;
import com.huazie.fleaframework.db.common.sql.pojo.SqlParam;
import com.huazie.fleaframework.db.common.sql.template.ITemplate;
import com.huazie.fleaframework.db.common.sql.template.SqlTemplateFactory;
import com.huazie.fleaframework.db.common.sql.template.TemplateTypeEnum;
import com.huazie.fleaframework.db.jpa.FleaJPASplitHelper;
import com.huazie.fleaframework.db.jpa.common.FleaJPAQuery;
import com.huazie.fleaframework.db.jpa.common.FleaJPAQueryPool;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.persistence.FleaEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 抽象Flea JPA DAO层实现类，实现了基本的增删改查的数据操作功能，
 * 子类可以自行拓展其他功能。
 *
 * <p> 该类一般由数据源DAO层实现类（子类A）继承，子类A中主要包含
 * 标记了持久化上下文注解（其中定义了持久化单元名）的实体管理器
 * 和 标记了事务注解的增删改方法；数据源这里跟持久化单元相对应。
 *
 * <p> 然后归属于该数据源的某表DAO层实现类（子类B），再继承子类A，
 * 当然这些代码都可以使用 flea-tools 中的工具自动生成。实际使用中，
 * 子类B中可以调用 getQuery 方法，获取指定的Flea JPA 查询对象，
 * 从而组装各种查询条件，根据业务需要定制化查询代码。
 * <pre>举例如下：
 *   // 获取指定的Flea JPA 查询对象【传null表示返回记录行结果】
 *   FleaJPAQuery query = getQuery(null);
 *   // 组装各种查询条件
 *   query.equal(attrName1, attrValue)
 *        .like(attrName2, attrValue)
 *        .in(attrName3, value);
 *   // 获取查询的记录行结果集合
 *   List<T> entityList = query.getResultList();
 *
 *   // 当然上述也可以写成一行代码
 *   List<T> entityList = getQuery(null)
 *          .equal(attrName1, attrValue)
 *          .like(attrName2, attrValue)
 *          .in(attrName3, value)
 *          .getResultList();
 * </pre>
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public abstract class AbstractFleaJPADAOImpl<T> implements IAbstractFleaJPADAO<T> {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(AbstractFleaJPADAOImpl.class);

    private Class<T> entityClass; // 实体类对应的Class对象

    /**
     * 获取T类型的Class对象
     *
     * @since 1.0.0
     */
    @SuppressWarnings({"unchecked"})
    public AbstractFleaJPADAOImpl() {
        // 获取泛型类的子类对象的Class对象
        Class<?> clz = getClass();
        // 获取子类对象的泛型父类类型（也就是AbstractDaoImpl<T>）
        ParameterizedType type = (ParameterizedType) clz.getGenericSuperclass();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Type = {}", type);
        }
        Type[] types = type.getActualTypeArguments();
        entityClass = (Class<T>) types[0];
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ClassName = {}", entityClass.getName());
        }
    }

    @Override
    public Number getFleaNextValue(T entity) throws CommonException {
        return FleaEntityManager.getFleaNextValue(getEntityManager(entity, true), entityClass, entity);
    }

    @Override
    public T query(long entityId) throws CommonException {
        return queryById(entityId, null);
    }

    @Override
    public T query(String entityId) throws CommonException {
        return queryById(entityId, null);
    }

    @Override
    public T query(long entityId, T entity) throws CommonException {
        return queryById(entityId, entity);
    }

    @Override
    public T query(String entityId, T entity) throws CommonException {
        return queryById(entityId, entity);
    }

    /**
     * 根据主键编号查询数据行对应的实体类信息
     *
     * @param entityId 主键编号
     * @return 数据行对应的实体类信息
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    protected T queryById(Object entityId, T entity) throws CommonException {
        checkPrimaryKey(entityId);
        return FleaEntityManager.find(getEntityManager(entity), entityId, entityClass, entity);
    }

    @Override
    public List<T> query(Map<String, Object> paramMap) throws CommonException {
        return getQuery(null).equal(paramMap).getResultList();
    }

    @Override
    public List<T> query(Map<String, Object> paramMap, String attrName, String orderBy) throws CommonException {
        return getQuery(null).equal(paramMap).addOrderBy(attrName, orderBy).getResultList();
    }

    @Override
    public List<T> query(Map<String, Object> paramMap, int start, int max) throws CommonException {
        return getQuery(null).equal(paramMap).getResultList(start, max);
    }

    @Override
    public List<T> query(Map<String, Object> paramMap, String attrName, String orderBy, int start, int max)
            throws CommonException {
        return getQuery(null).equal(paramMap).addOrderBy(attrName, orderBy).getResultList(start, max);
    }

    @Override
    public List<T> queryAll() throws CommonException {
        return getQuery(null).getResultList();
    }

    @Override
    public List<T> queryAll(String attrName, String orderBy) throws CommonException {
        return getQuery(null).addOrderBy(attrName, orderBy).getResultList();
    }

    @Override
    public List<T> queryAll(int start, int max) throws CommonException {
        return getQuery(null).getResultList(start, max);
    }

    @Override
    public List<T> queryAll(String attrName, String orderBy, int start, int max) throws CommonException {
        return getQuery(null).addOrderBy(attrName, orderBy).getResultList(start, max);
    }

    @Override
    public long queryCount() throws CommonException {
        // 调用SQL的COUNT函数统计数目 count()
        return ((Long) getQuery(Long.class).count().getSingleResult());
    }

    @Override
    public long queryCount(Map<String, Object> paramMap) throws CommonException {
        // 添加Where子句查询条件 equal(Map)
        // 调用SQL的COUNT函数统计数目 count()
        return ((Long) getQuery(Long.class).equal(paramMap).count().getSingleResult());
    }

    @Override
    public List<T> query(Set<String> attrNames, T entity) throws CommonException {
        return getQuery(null).initQueryEntity(entity).equal(attrNames).getResultList();
    }

    @Override
    public List<T> query(Set<String> attrNames, String attrName, String orderBy, T entity) throws CommonException {
        return getQuery(null).initQueryEntity(entity).equal(attrNames).addOrderBy(attrName, orderBy).getResultList();
    }

    @Override
    public List<T> query(Set<String> attrNames, int start, int max, T entity) throws CommonException {
        return getQuery(null).initQueryEntity(entity).equal(attrNames).getResultList(start, max);
    }

    @Override
    public List<T> query(Set<String> attrNames, String attrName, String orderBy, int start, int max, T entity) throws CommonException {
        return getQuery(null).initQueryEntity(entity).equal(attrNames).addOrderBy(attrName, orderBy).getResultList(start, max);
    }

    @Override
    public List<T> queryAll(T entity) throws CommonException {
        return getQuery(null).initQueryEntity(entity).getResultList();
    }

    @Override
    public List<T> queryAll(String attrName, String orderBy, T entity) throws CommonException {
        return getQuery(null).initQueryEntity(entity).addOrderBy(attrName, orderBy).getResultList();
    }

    @Override
    public List<T> queryAll(int start, int max, T entity) throws CommonException {
        return getQuery(null).initQueryEntity(entity).getResultList(start, max);
    }

    @Override
    public List<T> queryAll(String attrName, String orderBy, int start, int max, T entity) throws CommonException {
        return getQuery(null).initQueryEntity(entity).addOrderBy(attrName, orderBy).getResultList(start, max);
    }

    @Override
    public long queryCount(T entity) throws CommonException {
        // 调用SQL的COUNT函数统计数目 count()
        return ((Long) getQuery(Long.class).initQueryEntity(entity).count().getSingleResult());
    }

    @Override
    public long queryCount(Set<String> attrNames, T entity) throws CommonException {
        // 添加Where子句查询条件 equal(Set)
        // 调用SQL的COUNT函数统计数目 count()
        return ((Long) getQuery(Long.class).initQueryEntity(entity).equal(attrNames).count().getSingleResult());
    }

    @Override
    public boolean remove(long entityId) throws CommonException {
        return removeById(entityId, null);
    }

    @Override
    public boolean remove(String entityId) throws CommonException {
        return removeById(entityId, null);
    }

    @Override
    public boolean remove(T entity) throws CommonException {
        // 实体对象不能为空
        ObjectUtils.checkEmpty(entity, DaoException.class, "ERROR-DB-DAO0000000010");
        return FleaEntityManager.remove(getEntityManager(entity), entity);
    }

    @Override
    public boolean remove(long entityId, T entity) throws CommonException {
        return removeById(entityId, entity);
    }

    @Override
    public boolean remove(String entityId, T entity) throws CommonException {
        return removeById(entityId, entity);
    }

    /**
     * 根据主键编号删除指定行数据
     *
     * @param entityId 主键数据
     * @return true : 删除成功, false : 删除失败
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    protected boolean removeById(Object entityId, T entity) throws CommonException {
        checkPrimaryKey(entityId);
        final T old = queryById(entityId, entity);
        if (ObjectUtils.isNotEmpty(old)) {
            return FleaEntityManager.remove(getEntityManager(old), old);
        } else {
            return false;
        }
    }

    @Override
    public T update(T entity) throws CommonException {
        // 实体对象不能为空
        ObjectUtils.checkEmpty(entity, DaoException.class, "ERROR-DB-DAO0000000010");
        return FleaEntityManager.merge(getEntityManager(entity), entity);
    }

    @Override
    public List<T> batchUpdate(List<T> entities) throws CommonException {
        // 实体对象集合不能为空
        CollectionUtils.checkEmpty(entities, DaoException.class, "ERROR-DB-DAO0000000011");
        for (T entity : entities) {
            update(entity);
        }
        return entities;
    }

    @Override
    public void save(T entity) throws CommonException {
        // 实体对象不能为空
        ObjectUtils.checkEmpty(entity, DaoException.class, "ERROR-DB-DAO0000000010");
        FleaEntityManager.persist(getEntityManager(entity), entity);
    }

    @Override
    public void batchSave(List<T> entities) throws CommonException {
        // 实体对象集合不能为空
        CollectionUtils.checkEmpty(entities, DaoException.class, "ERROR-DB-DAO0000000011");
        for (T entity : entities) {
            save(entity);
        }
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public List<T> queryAll(String relationId, T entity) throws CommonException {
        return createNativeQuery(relationId, entity, false).getResultList();
    }

    @Override
    public Object querySingle(String relationId, T entity) throws CommonException {
        return createNativeQuery(relationId, entity, true).getSingleResult();
    }

    /**
     * 构建原生查询对象
     *
     * @param relationId 关系编号
     * @param entity     实体类
     * @return 实体类数据集合
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private Query createNativeQuery(String relationId, T entity, boolean isSingle) throws CommonException {
        // 构建并执行 SELECT SQL模板
        ITemplate<Object> selectSqlTemplate = SqlTemplateFactory.newSqlTemplate(relationId, entity, TemplateTypeEnum.SELECT);
        selectSqlTemplate.initialize();
        String nativeSql = selectSqlTemplate.toNativeSql();
        List<SqlParam> nativeParam = selectSqlTemplate.toNativeParams();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(new Object() {}, "SQL = {}", nativeSql);
        }

        Query query;
        if (isSingle) {
            query = getEntityManager(entity).createNativeQuery(nativeSql);
        } else {
            query = getEntityManager(entity).createNativeQuery(nativeSql, entity.getClass());
        }

        setParameter(query, nativeParam, TemplateTypeEnum.SELECT.getKey());

        return query;
    }

    @Override
    public int insert(String relationId, T entity) throws CommonException {
        // 构建并执行INSERT SQL模板
        return save(SqlTemplateFactory.newSqlTemplate(relationId, entity, TemplateTypeEnum.INSERT), entity);
    }

    @Override
    public int update(String relationId, T entity) throws CommonException {
        // 构建并执行UPDATE SQL模板
        return save(SqlTemplateFactory.newSqlTemplate(relationId, entity, TemplateTypeEnum.UPDATE), entity);
    }

    @Override
    public int delete(String relationId, T entity) throws CommonException {
        // 构建并执行DELETE SQL模板
        return save(SqlTemplateFactory.newSqlTemplate(relationId, entity, TemplateTypeEnum.DELETE), entity);
    }

    /**
     * 处理INSERT,UPDATE,DELETE SQL模板
     *
     * @param sqlTemplate SQL模板（包含 INSERT,UPDATE,DELETE）
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private int save(ITemplate<Object> sqlTemplate, T entity) throws CommonException {
        sqlTemplate.initialize();
        String nativeSql = sqlTemplate.toNativeSql();
        List<SqlParam> nativeParam = sqlTemplate.toNativeParams();

        if (LOGGER.isDebugEnabled()) {
            Object obj = new Object() {};
            if (TemplateTypeEnum.INSERT.getKey().equals(sqlTemplate.getTemplateType().getKey())) {
                LOGGER.debug1(obj, "SQL = {}", nativeSql);
            } else if (TemplateTypeEnum.UPDATE.getKey().equals(sqlTemplate.getTemplateType().getKey())) {
                LOGGER.debug1(obj, "SQL = {}", nativeSql);
            } else if (TemplateTypeEnum.DELETE.getKey().equals(sqlTemplate.getTemplateType().getKey())) {
                LOGGER.debug1(obj, "SQL = {}", nativeSql);
            }
        }

        Query query = getEntityManager(entity).createNativeQuery(nativeSql);
        setParameter(query, nativeParam, sqlTemplate.getTemplateType().getKey());
        // 执行原生SQL语句（可能包含 INSERT, UPDATE, DELETE）
        return query.executeUpdate();
    }

    /**
     * 校验主键合法性
     *
     * @param entityId 实体类对应的主键编号
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private void checkPrimaryKey(Object entityId) throws CommonException {
        if (entityId.getClass() == long.class || entityId.getClass() == Long.class) {
            if (!NumberUtils.isPositiveNumber(Long.valueOf(entityId.toString()))) {
                // 主键字段必须是正整数
                ExceptionUtils.throwCommonException(DaoException.class, "ERROR-DB-DAO0000000007");
            }
        } else if (entityId.getClass() == String.class) {
            // 主键字段不能为空
            ObjectUtils.checkEmpty(entityId, DaoException.class, "ERROR-DB-DAO0000000008");
        } else {
            // 主键必须是long(Long) 或 String
            ExceptionUtils.throwCommonException(DaoException.class, "ERROR-DB-DAO0000000009");
        }
    }

    /**
     * 查询对象设置参数
     *
     * @param query     查询对象
     * @param sqlParams 原生Sql参数
     * @since 1.0.0
     */
    private void setParameter(Query query, List<SqlParam> sqlParams, String templateType) {
        if (CollectionUtils.isNotEmpty(sqlParams)) {
            for (SqlParam sqlParam : sqlParams) {
                Object obj = new Object() {};
                if (TemplateTypeEnum.INSERT.getKey().equals(templateType)) {
                    LOGGER.debug1(obj, "JPA, COL{} = {}, PARAM{} = {}", sqlParam.getIndex(), sqlParam.getTabColName(), sqlParam.getIndex(), sqlParam.getAttrValue());
                } else if (TemplateTypeEnum.UPDATE.getKey().equals(templateType)) {
                    LOGGER.debug1(obj, "JPA, COL{} = {}, PARAM{} = {}", sqlParam.getIndex(), sqlParam.getTabColName(), sqlParam.getIndex(), sqlParam.getAttrValue());
                } else if (TemplateTypeEnum.DELETE.getKey().equals(templateType)) {
                    LOGGER.debug1(obj, "JPA, COL{} = {}, PARAM{} = {}", sqlParam.getIndex(), sqlParam.getTabColName(), sqlParam.getIndex(), sqlParam.getAttrValue());
                } else if (TemplateTypeEnum.SELECT.getKey().equals(templateType)) {
                    LOGGER.debug1(obj, "JPA, COL{} = {}, PARAM{} = {}", sqlParam.getIndex(), sqlParam.getTabColName(), sqlParam.getIndex(), sqlParam.getAttrValue());
                }
                query.setParameter(sqlParam.getIndex(), sqlParam.getAttrValue());
            }
        }
    }

    /**
     * 获取指定的Flea JPA 查询对象
     *
     * @return 自定义Flea JPA查询对象
     * @since 1.0.0
     */
    protected FleaJPAQuery getQuery(Class result) {
        // 获取当前的持久化单元名
        String unitName = FleaEntityManager.getPersistenceUnitName(this.getClass().getSuperclass());
        FleaJPAQueryPool pool;
        if (StringUtils.isBlank(unitName)) {
            // 获取Flea JPA查询对象池 （使用默认对象池名"default"即可）
            pool = FleaObjectPoolFactory.getFleaObjectPool(FleaJPAQuery.class, FleaJPAQueryPool.class);
        } else {
            // 获取Flea JPA查询对象池 （使用持久化单元名unitName作为对象池名）
            pool = FleaObjectPoolFactory.getFleaObjectPool(unitName, FleaJPAQuery.class, FleaJPAQueryPool.class);
        }

        if (ObjectUtils.isEmpty(pool)) {
            throw new FleaException("Can not get a object pool instance");
        }

        // 获取Flea JPA查询对象实例
        FleaJPAQuery query = pool.getFleaObject();
        if (LOGGER.isDebugEnabled()) {
            Object obj = new Object() {};
            LOGGER.debug1(obj, "FleaJPAQueryPool = {}", pool);
            LOGGER.debug1(obj, "FleaJPAQuery = {}", query);
        }
        // 获取实例后必须调用该方法,对Flea JPA查询对象进行初始化
        query.init(getEntityManager(), entityClass, result);
        return query;
    }

    @Override
    public void flush() {
        FleaEntityManager.flush(getEntityManager(), null);
    }

    @Override
    public void flush(T entity) throws CommonException {
        FleaEntityManager.flush(getEntityManager(entity), entity);
    }

    /**
     * 获取实体管理器
     *
     * @return 实体管理器
     * @since 1.0.0
     */
    protected abstract EntityManager getEntityManager();

    /**
     * 获取实体管理器
     *
     * @param entity 实体类对象实例
     * @return 实体管理器类
     * @since 1.0.0
     */
    public EntityManager getEntityManager(T entity) throws CommonException {
        return getEntityManager(entity, false);
    }

    /**
     * 获取实体管理器
     *
     * @param entity 实体类对象实例
     * @param flag   获取实体管理器标识【true：getFleaNextValue获取实体管理器， false: 其他场景获取实体管理器】
     * @return 实体管理器类
     * @throws CommonException 通用异常
     * @since 1.2.0
     */
    private EntityManager getEntityManager(T entity, boolean flag) throws CommonException {
        EntityManager entityManager = getEntityManager();

        // 实体类设置默认库名
        setDefaultLibName(entity);

        // 处理并添加分表信息，如果不存在分表则不处理
        entityManager = FleaJPASplitHelper.getHandler().handle(entityManager, entity, flag);
        return entityManager;
    }

    /**
     * 实体类设置默认库名，用于分库配置查询
     *
     * @param entity 实体类对象
     * @since 1.2.0
     */
    private void setDefaultLibName(T entity) {
        if (ObjectUtils.isNotEmpty(entity) && entity instanceof FleaEntity) {
            FleaEntity fleaEntity = (FleaEntity) entity;
            // 设置默认库名，取持久化单元名
            String unitName = FleaEntityManager.getPersistenceUnitName(this.getClass().getSuperclass());
            fleaEntity.put(DBConstants.LibTableSplitConstants.FLEA_LIB_NAME, unitName);
        }
    }
}