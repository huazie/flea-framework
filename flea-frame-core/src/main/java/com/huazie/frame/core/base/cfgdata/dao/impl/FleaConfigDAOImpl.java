package com.huazie.frame.core.base.cfgdata.dao.impl;

import com.huazie.frame.db.jpa.dao.impl.AbstractFleaJPADAOImpl;
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
	protected EntityManager entityManager;
	
	@Override
	@Transactional("fleaConfigTransactionManager")
	public boolean remove(long entityId) throws Exception {
		return super.remove(entityId);
	}
	
	@Override
	@Transactional("fleaConfigTransactionManager")
	public boolean remove(String entityId) throws Exception {
		return super.remove(entityId);
	}

	@Override
	@Transactional("fleaConfigTransactionManager")
	public T update(T entity) throws Exception {
		return super.update(entity);
	}
	
	@Override
	@Transactional("fleaConfigTransactionManager")
	public List<T> batchUpdate(List<T> entities) throws Exception {
		return super.batchUpdate(entities);
	}

	@Override
	@Transactional("fleaConfigTransactionManager")
	public void save(T entity) throws Exception {
		super.save(entity);
	}
	
	@Override
	@Transactional("fleaConfigTransactionManager")
	public void batchSave(List<T> entities) throws Exception {
		super.batchSave(entities);
	}

	@Override
	@Transactional("fleaConfigTransactionManager")
	public List<T> query(String relationId, T t) throws Exception {
		return super.query(relationId, t);
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
