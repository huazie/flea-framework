package com.huazie.fleaframework.db.jpa.service.impl;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 抽象Flea JPA SV层实现类，该类实现了基本的增删改查的数据操作功能，
 * 子类可以自行拓展其他功能。
 *
 * <p> 该类定义了抽象方法 <code> getDAO </code>，由子类实现该方法，
 * 实际的增删改查的数据操作，都是由DAO层子类实现；当然这些代码都可以
 * 使用 <b> flea-tools </b> 中的工具自动生成。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class AbstractFleaJPASVImpl<T> implements IAbstractFleaJPASV<T> {

    @Override
    public Number getFleaNextValue(T entity) throws CommonException {
        return getDAO().getFleaNextValue(entity);
    }

    @Override
    public T query(long entityId) throws CommonException {
        return getDAO().query(entityId);
    }

    @Override
    public T query(String entityId) throws CommonException {
        return getDAO().query(entityId);
    }

    @Override
    public T query(long entityId, T entity) throws CommonException {
        return getDAO().query(entityId, entity);
    }

    @Override
    public T query(String entityId, T entity) throws CommonException {
        return getDAO().query(entityId, entity);
    }

    @Override
    public List<T> query(Map<String, Object> paramMap) throws CommonException {
        return getDAO().query(paramMap);
    }

    @Override
    public List<T> query(Map<String, Object> paramMap, String attrName, String orderBy) throws CommonException {
        return getDAO().query(paramMap, attrName, orderBy);
    }

    @Override
    public List<T> query(Map<String, Object> paramMap, int start, int max) throws CommonException {
        return getDAO().query(paramMap, start, max);
    }

    @Override
    public List<T> query(Map<String, Object> paramMap, String attrName, String orderBy, int start, int max)
            throws CommonException {
        return getDAO().query(paramMap, attrName, orderBy, start, max);
    }

    @Override
    public List<T> queryAll() throws CommonException {
        return getDAO().queryAll();
    }

    @Override
    public List<T> queryAll(String attrName, String orderBy) throws CommonException {
        return getDAO().queryAll(attrName, orderBy);
    }

    @Override
    public List<T> queryAll(int start, int max) throws CommonException {
        return getDAO().queryAll(start, max);
    }

    @Override
    public List<T> queryAll(String attrName, String orderBy, int start, int max) throws CommonException {
        return getDAO().queryAll(attrName, orderBy, start, max);
    }

    @Override
    public long queryCount() throws CommonException {
        return getDAO().queryCount();
    }

    @Override
    public long queryCount(Map<String, Object> paramMap) throws CommonException {
        return getDAO().queryCount(paramMap);
    }

    @Override
    public List<T> query(Set<String> attrNames, T entity) throws CommonException {
        return getDAO().query(attrNames, entity);
    }

    @Override
    public List<T> query(Set<String> attrNames, String attrName, String orderBy, T entity) throws CommonException {
        return getDAO().query(attrNames, attrName, orderBy, entity);
    }

    @Override
    public List<T> query(Set<String> attrNames, int start, int max, T entity) throws CommonException {
        return getDAO().query(attrNames, start, max, entity);
    }

    @Override
    public List<T> query(Set<String> attrNames, String attrName, String orderBy, int start, int max, T entity) throws CommonException {
        return getDAO().query(attrNames, attrName, orderBy, start, max, entity);
    }

    @Override
    public List<T> queryAll(T entity) throws CommonException {
        return getDAO().queryAll(entity);
    }

    @Override
    public List<T> queryAll(String attrName, String orderBy, T entity) throws CommonException {
        return getDAO().queryAll(attrName, orderBy, entity);
    }

    @Override
    public List<T> queryAll(int start, int max, T entity) throws CommonException {
        return getDAO().queryAll(start, max, entity);
    }

    @Override
    public List<T> queryAll(String attrName, String orderBy, int start, int max, T entity) throws CommonException {
        return getDAO().queryAll(attrName, orderBy, start, max, entity);
    }

    @Override
    public long queryCount(T entity) throws CommonException {
        return getDAO().queryCount(entity);
    }

    @Override
    public long queryCount(Set<String> attrNames, T entity) throws CommonException {
        return getDAO().queryCount(attrNames, entity);
    }

    @Override
    public boolean remove(long entityId) throws CommonException {
        return getDAO().remove(entityId);
    }

    @Override
    public boolean remove(String entityId) throws CommonException {
        return getDAO().remove(entityId);
    }

    @Override
    public boolean remove(T entity) throws CommonException {
        return getDAO().remove(entity);
    }

    @Override
    public boolean remove(long entityId, T entity) throws CommonException {
        return getDAO().remove(entityId, entity);
    }

    @Override
    public boolean remove(String entityId, T entity) throws CommonException {
        return getDAO().remove(entityId, entity);
    }

    @Override
    public T update(T entity) throws CommonException {
        return getDAO().update(entity);
    }

    @Override
    public List<T> batchUpdate(List<T> entities) throws CommonException {
        return getDAO().batchUpdate(entities);
    }

    @Override
    public void save(T entity) throws CommonException {
        getDAO().save(entity);
    }

    @Override
    public void batchSave(List<T> entities) throws CommonException {
        getDAO().batchSave(entities);
    }

    @Override
    public List<T> queryAll(String relationId, T entity) throws CommonException {
        return getDAO().queryAll(relationId, entity);
    }

    @Override
    public Object querySingle(String relationId, T entity) throws CommonException {
        return getDAO().querySingle(relationId, entity);
    }

    @Override
    public int insert(String relationId, T entity) throws CommonException {
        return getDAO().insert(relationId, entity);
    }

    @Override
    public int update(String relationId, T entity) throws CommonException {
        return getDAO().update(relationId, entity);
    }

    @Override
    public int delete(String relationId, T entity) throws CommonException {
        return getDAO().delete(relationId, entity);
    }

    @Override
    public void flush() {
        getDAO().flush();
    }

    @Override
    public void flush(T entity) throws CommonException {
        getDAO().flush(entity);
    }

    /**
     * 获取DAO层实现
     *
     * @return 抽象DAO层实现
     * @since 1.0.0
     */
    protected abstract IAbstractFleaJPADAO<T> getDAO();
}
