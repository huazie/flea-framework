package com.huazie.fleaframework.auth.base;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.dao.impl.AbstractFleaJPADAOImpl;
import com.huazie.fleaframework.db.jpa.transaction.FleaTransactional;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * FleaAuth数据源DAO层父类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaAuthDAOImpl<T> extends AbstractFleaJPADAOImpl<T> {

    @PersistenceContext(unitName="fleaauth")
    private EntityManager entityManager;

    @Override
    @Transactional("fleaAuthTransactionManager")
    public Number getFleaNextValue(T entity) throws CommonException {
        return super.getFleaNextValue(entity);
    }

    @Override
    @Transactional("fleaAuthTransactionManager")
    public boolean remove(long entityId) throws CommonException {
        return super.remove(entityId);
    }

    @Override
    @Transactional("fleaAuthTransactionManager")
    public boolean remove(String entityId) throws CommonException {
        return super.remove(entityId);
    }

    @Override
    @FleaTransactional("fleaAuthTransactionManager")
    public boolean remove(T entity) throws CommonException {
        return super.remove(entity);
    }

    @Override
    @FleaTransactional("fleaAuthTransactionManager")
    public boolean removeNew(long entityId, T entity) throws CommonException {
        return super.removeNew(entityId, entity);
    }

    @Override
    @FleaTransactional("fleaAuthTransactionManager")
    public boolean removeNew(String entityId, T entity) throws CommonException {
        return super.removeNew(entityId, entity);
    }

    @Override
    @FleaTransactional("fleaAuthTransactionManager")
    public T update(T entity) throws CommonException {
        return super.update(entity);
    }

    @Override
    @FleaTransactional("fleaAuthTransactionManager")
    public List<T> batchUpdate(List<T> entities) throws CommonException {
        return super.batchUpdate(entities);
    }

    @Override
    @FleaTransactional("fleaAuthTransactionManager")
    public void save(T entity) throws CommonException {
        super.save(entity);
    }

    @Override
    @FleaTransactional("fleaAuthTransactionManager")
    public void batchSave(List<T> entities) throws CommonException {
        super.batchSave(entities);
    }

    @Override
    @Transactional("fleaAuthTransactionManager")
    public int insert(String relationId, T entity) throws CommonException {
        return super.insert(relationId, entity);
    }

    @Override
    @Transactional("fleaAuthTransactionManager")
    public int update(String relationId, T entity) throws CommonException {
        return super.update(relationId, entity);
    }

    @Override
    @Transactional("fleaAuthTransactionManager")
    public int delete(String relationId, T entity) throws CommonException {
        return super.delete(relationId, entity);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}