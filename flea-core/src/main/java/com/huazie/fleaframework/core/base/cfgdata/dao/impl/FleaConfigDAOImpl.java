package com.huazie.fleaframework.core.base.cfgdata.dao.impl;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.dao.impl.AbstractFleaJPADAOImpl;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * <p> FleaConfig数据源DAO层父类 </p>
 * 
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
public class FleaConfigDAOImpl<T> extends AbstractFleaJPADAOImpl<T> {
	
	@PersistenceContext(unitName="fleaconfig")
	private EntityManager entityManager;

	@Override
	@Transactional("fleaConfigTransactionManager")
	public Number getFleaNextValue(T entity) throws CommonException {
		return super.getFleaNextValue(entity);
	}

	@Override
	@Transactional("fleaConfigTransactionManager")
	public boolean remove(long entityId) throws CommonException {
		return super.remove(entityId);
	}
	
	@Override
	@Transactional("fleaConfigTransactionManager")
	public boolean remove(String entityId) throws CommonException {
		return super.remove(entityId);
	}

	@Override
	@Transactional("fleaConfigTransactionManager")
	public boolean remove(long entityId, T entity) throws CommonException {
		return super.remove(entityId, entity);
	}

	@Override
	@Transactional("fleaConfigTransactionManager")
	public boolean remove(String entityId, T entity) throws CommonException {
		return super.remove(entityId, entity);
	}

	@Override
	@Transactional("fleaConfigTransactionManager")
	public T update(T entity) throws CommonException {
		return super.update(entity);
	}
	
	@Override
	@Transactional("fleaConfigTransactionManager")
	public List<T> batchUpdate(List<T> entities) throws CommonException {
		return super.batchUpdate(entities);
	}

	@Override
	@Transactional("fleaConfigTransactionManager")
	public void save(T entity) throws CommonException {
		super.save(entity);
	}
	
	@Override
	@Transactional("fleaConfigTransactionManager")
	public void batchSave(List<T> entities) throws CommonException {
		super.batchSave(entities);
	}

	@Override
	@Transactional("fleaConfigTransactionManager")
	public int insert(String relationId, T entity) throws CommonException {
		return super.insert(relationId, entity);
	}

	@Override
	@Transactional("fleaConfigTransactionManager")
	public int update(String relationId, T entity) throws CommonException {
		return super.update(relationId, entity);
	}

	@Override
	@Transactional("fleaConfigTransactionManager")
	public int delete(String relationId, T entity) throws CommonException {
		return super.delete(relationId, entity);
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
