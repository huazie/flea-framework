package com.huazie.frame.core.flea;

import com.huazie.frame.db.jpa.dao.impl.AbstractFleaJPADAOImpl;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * <p> FleaManagement数据源DAO层实现类 </p>
 * 
 * @author huazie
 * @version v1.0.0
 * @date 2017年1月28日
 * 
 */
public class FleaManagementDAOImpl<T> extends AbstractFleaJPADAOImpl<T> {
	
	//private final static Logger LOGGER = LoggerFactory.getLogger(FleaManagementDAOImpl.class);

	@PersistenceContext(unitName="fleamanagement")
	protected EntityManager entityManager;
	
	@Override
	@Transactional("fleaManagementTransactionManager")
	public boolean remove(long entityId) throws Exception {
		return super.remove(entityId);
	}
	
	@Override
	@Transactional("fleaManagementTransactionManager")
	public boolean remove(String entityId) throws Exception {
		return super.remove(entityId);
	}

	@Override
	@Transactional("fleaManagementTransactionManager")
	public T store(T entity) throws Exception {
		return super.store(entity);
	}
	
	@Override
	@Transactional("fleaManagementTransactionManager")
	public List<T> batchUpdate(List<T> entitys) throws Exception {
		return super.batchUpdate(entitys);
	}

	@Override
	@Transactional("fleaManagementTransactionManager")
	public void save(T entity) throws Exception {
		super.save(entity);
	}
	
	@Override
	@Transactional("fleaManagementTransactionManager")
	public void batchSave(List<T> entitys) throws Exception {
		super.batchSave(entitys);
	}

	@Override
	@Transactional("fleaManagementTransactionManager")
	public boolean update(T entity) throws Exception {
		return super.update(entity);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

}
