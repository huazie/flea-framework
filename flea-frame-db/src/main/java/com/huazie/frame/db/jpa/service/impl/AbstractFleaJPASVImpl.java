package com.huazie.frame.db.jpa.service.impl;

import java.util.List;
import java.util.Map;

import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.interfaces.IAbstractFleaJPASV;

/**
 * 抽象服务层实现类
 * 
 * @author huazie
 * @version v1.0.0
 * @date 2017年2月6日
 * 
 */
public abstract class AbstractFleaJPASVImpl<T> implements IAbstractFleaJPASV<T> {
	
	@Override
	public T query(long entityId) throws Exception {
		return this.getDAO().query(entityId);
	}

	@Override
	public T query(String entityId) throws Exception {
		return this.getDAO().query(entityId);
	}

	@Override
	public List<T> query(Map<String, Object> paramterMap) throws Exception {
		return this.getDAO().query(paramterMap);
	}

	@Override
	public List<T> query(Map<String, Object> paramterMap, String attrName, String orderBy) throws Exception {
		return this.getDAO().query(paramterMap, attrName, orderBy);
	}

	@Override
	public List<T> query(Map<String, Object> paramterMap, int start, int max) throws Exception {
		return this.getDAO().query(paramterMap, start, max);
	}

	@Override
	public List<T> query(Map<String, Object> paramterMap, String attrName, String orderBy, int start, int max)
			throws Exception {
		return this.getDAO().query(paramterMap, attrName, orderBy, start, max);
	}

	@Override
	public List<T> queryAll() throws Exception {
		return this.getDAO().queryAll();
	}

	@Override
	public List<T> queryAll(String attrName, String orderBy) throws Exception {
		return this.getDAO().queryAll(attrName, orderBy);
	}

	@Override
	public List<T> queryAll(int start, int max) throws Exception {
		return this.getDAO().queryAll(start, max);
	}

	@Override
	public List<T> queryAll(String attrName, String orderBy, int start, int max) throws Exception {
		return this.getDAO().queryAll(attrName, orderBy, start, max);
	}

	@Override
	public int queryCount() throws Exception {
		return this.getDAO().queryCount();
	}

	@Override
	public int queryCount(Map<String, Object> paramterMap) throws Exception {
		return this.getDAO().queryCount(paramterMap);
	}

	@Override
	public boolean remove(long entityId) throws Exception {
		return this.getDAO().remove(entityId);
	}

	@Override
	public boolean remove(String entityId) throws Exception {
		return this.getDAO().remove(entityId);
	}

	@Override
	public T store(T entity) throws Exception {
		return this.getDAO().store(entity);
	}
	
	@Override
	public List<T> batchUpdate(List<T> entitys) throws Exception {
		return this.getDAO().batchUpdate(entitys);
	}

	@Override
	public void save(T entity) throws Exception {
		this.getDAO().save(entity);
	}
	
	@Override
	public void batchSave(List<T> entitys) throws Exception {
		this.getDAO().batchSave(entitys);
	}

	@Override
	public boolean update(T entity) throws Exception {
		return this.getDAO().update(entity);
	}
	
	/**
	 * 获取DAO层实现
	 * 
	 * @date 2018年11月15日
	 *
	 * @return
	 */
	protected abstract IAbstractFleaJPADAO<T> getDAO();
}
