package com.huazie.frame.db.jpa.dao.impl;

import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.db.common.exception.DaoException;
import com.huazie.frame.db.common.sql.pojo.SqlParam;
import com.huazie.frame.db.common.sql.template.ITemplate;
import com.huazie.frame.db.common.sql.template.TemplateTypeEnum;
import com.huazie.frame.db.common.sql.template.impl.DeleteSqlTemplate;
import com.huazie.frame.db.common.sql.template.impl.InsertSqlTemplate;
import com.huazie.frame.db.common.sql.template.impl.SelectSqlTemplate;
import com.huazie.frame.db.common.sql.template.impl.UpdateSqlTemplate;
import com.huazie.frame.db.jpa.common.FleaJPAQuery;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<T> query(String relationId, T entity) throws Exception {
        // 构建并执行 SELECT SQL模板
        ITemplate<T> selectSqlTemplate = new SelectSqlTemplate<T>(relationId, entity);
        selectSqlTemplate.initialize();
        String nativeSql = selectSqlTemplate.toNativeSql();
        List<SqlParam> nativeParam = selectSqlTemplate.toNativeParams();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("AbstractFleaJPADAOImpl##query(String, T) SQL = {}", nativeSql);
        }

        Query query = getEntityManager().createNativeQuery(nativeSql, entity.getClass());
        setParameter(query, nativeParam, TemplateTypeEnum.SELECT.getKey());

        return query.getResultList();
    }

    @Override
    public int insert(String relationId, T entity) throws Exception {
        // 构建并执行INSERT SQL模板
        return save(new InsertSqlTemplate<T>(relationId, entity), TemplateTypeEnum.INSERT.getKey());
    }

    @Override
    public int update(String relationId, T entity) throws Exception {
        // 构建并执行UPDATE SQL模板
        return save(new UpdateSqlTemplate<T>(relationId, entity), TemplateTypeEnum.UPDATE.getKey());
    }

    @Override
    public int delete(String relationId, T entity) throws Exception {
        // 构建并执行DELETE SQL模板
        return save(new DeleteSqlTemplate<T>(relationId, entity), TemplateTypeEnum.DELETE.getKey());
    }

    /**
     * <p> 处理INSERT,UPDATE,DELETE SQL模板 </p>
     *
     * @param sqlTemplate SQL模板（包含 INSERT,UPDATE,DELETE）
     * @throws Exception
     * @since 1.0.0
     */
    private int save(ITemplate<T> sqlTemplate, String templateType) throws Exception {
        sqlTemplate.initialize();
        String nativeSql = sqlTemplate.toNativeSql();
        List<SqlParam> nativeParam = sqlTemplate.toNativeParams();

        if (LOGGER.isDebugEnabled()) {
            if(TemplateTypeEnum.INSERT.getKey().equals(templateType)) {
                LOGGER.debug("AbstractFleaJPADAOImpl##insert(String, T) SQL = {}", nativeSql);
            } else if(TemplateTypeEnum.UPDATE.getKey().equals(templateType)) {
                LOGGER.debug("AbstractFleaJPADAOImpl##update(String, T) SQL = {}", nativeSql);
            } else if(TemplateTypeEnum.DELETE.getKey().equals(templateType)) {
                LOGGER.debug("AbstractFleaJPADAOImpl##delete(String, T) SQL = {}", nativeSql);
            }
        }

        Query query = getEntityManager().createNativeQuery(nativeSql);
        setParameter(query, nativeParam, templateType);
        // 执行原生SQL语句（可能包含 INSERT, UPDATE, DELETE）
        return query.executeUpdate();
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
     * <p> 为<code>Query</code>对象设置参数 </p>
     *
     * @param query     <code>Query</code>对象
     * @param sqlParams 原生Sql参数
     * @since 1.0.0
     */
    private void setParameter(Query query, List<SqlParam> sqlParams, String templateType) {
        if (CollectionUtils.isNotEmpty(sqlParams)) {
            for (SqlParam sqlParam : sqlParams) {
                if(TemplateTypeEnum.INSERT.getKey().equals(templateType)) {
                    LOGGER.debug("AbstractFleaJPADAOImpl##insert(String, T) COL{} = {}, PARAM{} = {}", sqlParam.getIndex(), sqlParam.getTabColName(), sqlParam.getIndex(), sqlParam.getAttrValue());
                } else if(TemplateTypeEnum.UPDATE.getKey().equals(templateType)) {
                    LOGGER.debug("AbstractFleaJPADAOImpl##update(String, T) COL{} = {}, PARAM{} = {}", sqlParam.getIndex(), sqlParam.getTabColName(), sqlParam.getIndex(), sqlParam.getAttrValue());
                } else if(TemplateTypeEnum.DELETE.getKey().equals(templateType)) {
                    LOGGER.debug("AbstractFleaJPADAOImpl##delete(String, T) COL{} = {}, PARAM{} = {}", sqlParam.getIndex(), sqlParam.getTabColName(), sqlParam.getIndex(), sqlParam.getAttrValue());
                } else if(TemplateTypeEnum.SELECT.getKey().equals(templateType)) {
                    LOGGER.debug("AbstractFleaJPADAOImpl##query(String, T) COL{} = {}, PARAM{} = {}", sqlParam.getIndex(), sqlParam.getTabColName(), sqlParam.getIndex(), sqlParam.getAttrValue());
                }
                query.setParameter(sqlParam.getIndex(), sqlParam.getAttrValue());
            }
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
