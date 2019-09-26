package com.huazie.frame.db.jpa.service.impl;

import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p> 抽象Flea JPA SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class AbstractFleaJPASVImpl<T> implements IAbstractFleaJPASV<T> {

    @Override
    public T query(long entityId) throws Exception {
        return getDAO().query(entityId);
    }

    @Override
    public T query(String entityId) throws Exception {
        return getDAO().query(entityId);
    }

    @Override
    public T queryNew(long entityId, T entity) throws Exception {
        return getDAO().queryNew(entityId, entity);
    }

    @Override
    public T queryNew(String entityId, T entity) throws Exception {
        return getDAO().queryNew(entityId, entity);
    }

    @Override
    public List<T> query(Map<String, Object> paramMap) throws Exception {
        return getDAO().query(paramMap);
    }

    @Override
    public List<T> query(Map<String, Object> paramMap, String attrName, String orderBy) throws Exception {
        return getDAO().query(paramMap, attrName, orderBy);
    }

    @Override
    public List<T> query(Map<String, Object> paramMap, int start, int max) throws Exception {
        return getDAO().query(paramMap, start, max);
    }

    @Override
    public List<T> query(Map<String, Object> paramMap, String attrName, String orderBy, int start, int max)
            throws Exception {
        return getDAO().query(paramMap, attrName, orderBy, start, max);
    }

    @Override
    public List<T> queryAll() throws Exception {
        return getDAO().queryAll();
    }

    @Override
    public List<T> queryAll(String attrName, String orderBy) throws Exception {
        return getDAO().queryAll(attrName, orderBy);
    }

    @Override
    public List<T> queryAll(int start, int max) throws Exception {
        return getDAO().queryAll(start, max);
    }

    @Override
    public List<T> queryAll(String attrName, String orderBy, int start, int max) throws Exception {
        return getDAO().queryAll(attrName, orderBy, start, max);
    }

    @Override
    public long queryCount() throws Exception {
        return getDAO().queryCount();
    }

    @Override
    public long queryCount(Map<String, Object> paramMap) throws Exception {
        return getDAO().queryCount(paramMap);
    }

    @Override
    public List<T> query(Set<String> attrNames, T entity) throws Exception {
        return getDAO().query(attrNames, entity);
    }

    @Override
    public List<T> query(Set<String> attrNames, String attrName, String orderBy, T entity) throws Exception {
        return getDAO().query(attrNames, attrName, orderBy, entity);
    }

    @Override
    public List<T> query(Set<String> attrNames, int start, int max, T entity) throws Exception {
        return getDAO().query(attrNames, start, max, entity);
    }

    @Override
    public List<T> query(Set<String> attrNames, String attrName, String orderBy, int start, int max, T entity) throws Exception {
        return getDAO().query(attrNames, attrName, orderBy, start, max, entity);
    }

    @Override
    public List<T> queryAll(T entity) throws Exception {
        return getDAO().queryAll(entity);
    }

    @Override
    public List<T> queryAll(String attrName, String orderBy, T entity) throws Exception {
        return getDAO().queryAll(attrName, orderBy, entity);
    }

    @Override
    public List<T> queryAll(int start, int max, T entity) throws Exception {
        return getDAO().queryAll(start, max, entity);
    }

    @Override
    public List<T> queryAll(String attrName, String orderBy, int start, int max, T entity) throws Exception {
        return getDAO().queryAll(attrName, orderBy, start, max, entity);
    }

    @Override
    public long queryCount(T entity) throws Exception {
        return getDAO().queryCount(entity);
    }

    @Override
    public long queryCount(Set<String> attrNames, T entity) throws Exception {
        return getDAO().queryCount(attrNames, entity);
    }

    @Override
    public boolean remove(long entityId) throws Exception {
        return getDAO().remove(entityId);
    }

    @Override
    public boolean remove(String entityId) throws Exception {
        return getDAO().remove(entityId);
    }

    @Override
    public boolean removeNew(long entityId, T entity) throws Exception {
        return getDAO().removeNew(entityId, entity);
    }

    @Override
    public boolean removeNew(String entityId, T entity) throws Exception {
        return getDAO().removeNew(entityId, entity);
    }

    @Override
    public T update(T entity) throws Exception {
        return getDAO().update(entity);
    }

    @Override
    public List<T> batchUpdate(List<T> entities) throws Exception {
        return getDAO().batchUpdate(entities);
    }

    @Override
    public void save(T entity) throws Exception {
        getDAO().save(entity);
    }

    @Override
    public void batchSave(List<T> entities) throws Exception {
        getDAO().batchSave(entities);
    }

    @Override
    public List<T> query(String relationId, T entity) throws Exception {
        return getDAO().query(relationId, entity);
    }

    @Override
    public int insert(String relationId, T entity) throws Exception {
        return getDAO().insert(relationId, entity);
    }

    @Override
    public int update(String relationId, T entity) throws Exception {
        return getDAO().update(relationId, entity);
    }

    @Override
    public int delete(String relationId, T entity) throws Exception {
        return getDAO().delete(relationId, entity);
    }

    /**
     * <p> 获取DAO层实现 </p>
     *
     * @return 抽象DAO层实现
     * @since 1.0.0
     */
    protected abstract IAbstractFleaJPADAO<T> getDAO();
}
