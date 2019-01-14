package com.huazie.frame.db.jpa.common;

import java.util.List;
import java.util.Map;

/**
 * 数据处理的基本方法接口
 * 
 * @author huazie
 * @version v1.0.0
 * @date 2017年2月6日
 *
 */
public interface IFleaJPABaseDataHandler<T> {
	/**
	 * 
	 * 根据主键查询
	 * 
	 * @date 2016年9月26日
	 * 
	 * @param entityId
	 *            主键
	 * @return 实体对象
	 * @throws Exception
	 */
	public T query(long entityId) throws Exception;
	
	/**
	 * 
	 * 根据主键查询
	 * 
	 * @date 2016年9月26日
	 * 
	 * @param entityId
	 *            主键
	 * @return 实体对象
	 * @throws Exception
	 */
	public T query(String entityId) throws Exception;
	
	/**
	 * 
	 * 多条件查询
	 * 
	 * @date 2016年9月26日
	 *
	 * @param paramterMap
	 *            多条件参数
	 * @return 实体对象集合
	 * @throws Exception
	 */
	public List<T> query(Map<String, Object> paramterMap) throws Exception;
	
	/**
	 * 
	 * 多条件排序查询
	 * 
	 * @date 2016年9月27日
	 *
	 * @param paramterMap
	 *            多条件参数
	 * @param attrName
	 *            属性名
	 * @param orderBy
	 *            排序方式（desc：降序，asc：升序）
	 * @return
	 * @throws Exception
	 */
	public List<T> query(Map<String, Object> paramterMap, String attrName, String orderBy) throws Exception;
	
	/**
	 * 
	 * 多条件分页查询
	 * 
	 * @date 2016年9月26日
	 *
	 * @param paramterMap
	 *            多条件参数
	 * @param start
	 *            开始查询记录行
	 * @param max
	 *            一次最大查询数量
	 * @return 实体对象集合
	 * @throws Exception
	 */
	public List<T> query(Map<String, Object> paramterMap, int start, int max) throws Exception;
	
	/**
	 * 
	 * 多条件分页排序查询
	 * 
	 * @date 2016年9月27日
	 *
	 * @param paramterMap
	 *            多条件参数
	 * @param attrName
	 *            属性名
	 * @param orderBy
	 *            排序方式（desc：降序，asc：升序）
	 * @param start
	 *            开始查询记录行
	 * @param max
	 *            一次最大查询数量
	 * @return
	 * @throws Exception
	 */
	public List<T> query(Map<String, Object> paramterMap, String attrName, String orderBy, int start, int max) throws Exception;
	
	/**
	 * 
	 * 查询所有数据
	 * 
	 * @date 2016年9月27日
	 *
	 * @return 
	 * @throws Exception
	 */
	public List<T> queryAll() throws Exception;

	/**
	 * 
	 * 查询所有数据(排序)
	 * 
	 * @date 2016年9月27日
	 *
	 * @param attrName
	 *            属性名
	 * @param orderBy
	 *            排序方式（desc：降序，asc：升序）
	 * @return
	 * @throws Exception
	 */
	public List<T> queryAll(String attrName, String orderBy) throws Exception;
	
	/**
	 * 
	 * 分页查询所有的数据
	 * 
	 * @date 2016年9月27日
	 *
	 * @param start
	 *            开始查询记录行
	 * @param max
	 *            一次最大查询数量
	 * @return
	 * @throws Exception
	 */
	public List<T> queryAll(int start, int max) throws Exception;
	
	/**
	 * 
	 * 分页排序查询所有的数据
	 * 
	 * @date 2016年9月27日
	 *
	 * @param attrName
	 *            属性名
	 * @param orderBy
	 *            排序方式（desc：降序，asc：升序）
	 * @param start
	 *            开始查询记录行
	 * @param max
	 *            一次最大查询数量
	 * @return
	 * @throws Exception
	 */
	public List<T> queryAll(String attrName, String orderBy, int start, int max) throws Exception;
	
	/**
	 * 查询表中数据的总数
	 * 
	 * @date 2016年9月27日
	 *
	 * @return
	 * @throws Exception
	 */
	public int queryCount() throws Exception;

	/**
	 * 
	 * 带条件查询表中数据的总数
	 * 
	 * @date 2016年9月27日
	 *
	 * @param paramterMap
	 *            多条件参数
	 * @return
	 * @throws Exception
	 */
	public int queryCount(Map<String, Object> paramterMap) throws Exception;
	
	/**
	 * 
	 * 删除某条数据
	 * 
	 * @date 2016年9月28日
	 *
	 * @param entityId
	 *            实体类的主键
	 * @return
	 * @throws Exception
	 */
	public boolean remove(long entityId)throws Exception;
	
	/**
	 * 
	 * 删除某条数据
	 * 
	 * @date 2016年9月28日
	 *
	 * @param entityId
	 *            实体类的主键
	 * @return
	 * @throws Exception
	 */
	public boolean remove(String entityId)throws Exception;
	
	/**
	 * 
	 * 保存一条记录,可用于更新
	 * 
	 * @date 2016年9月28日
	 *
	 * @param entity
	 *            一条记录对应的实体类
	 * @return
	 * @throws Exception
	 */
	public T store(final T entity)throws Exception;
	
	/**
	 * 
	 * 批量更新
	 * 
	 * @date 2017年3月19日
	 *
	 * @param entitys
	 * @throws Exception
	 */
	public List<T> batchUpdate(List<T> entitys)throws Exception;
	
	/**
	 * 
	 * 保存一条记录
	 * 
	 * @date 2016年9月28日
	 *
	 * @param entity
	 *            一条记录对应的实体类
	 * @return
	 * @throws Exception
	 */
	public void save(final T entity)throws Exception;
	
	/**
	 * 
	 * 批量保存
	 * 
	 * @date 2017年3月19日
	 *
	 * @param entitys
	 *            待保存的实体集合
	 * @throws Exception
	 */
	public void batchSave(List<T> entitys)throws Exception;
	
	/**
	 * 
	 * 更新一条记录
	 * 
	 * @date 2016年9月28日
	 *
	 * @param entity
	 *            一条记录对应的实体类
	 * @return 更新成功返回true
	 * @throws Exception
	 */
	public boolean update(final T entity)throws Exception;
}
