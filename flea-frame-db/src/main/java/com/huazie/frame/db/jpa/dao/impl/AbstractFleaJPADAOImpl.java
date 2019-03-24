package com.huazie.frame.db.jpa.dao.impl;

import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.DBConstants;
import com.huazie.frame.db.common.exception.DaoException;
import com.huazie.frame.db.jpa.common.FleaJPAQuery;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;

/**
 * 抽象Dao层实现类，该类实现了基本的增删改查功能，开发人员可以自行拓展
 * 
 * @author huazie
 * @version v1.0.0
 * @date 2016年9月24日
 * 
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class AbstractFleaJPADAOImpl<T> implements IAbstractFleaJPADAO<T> {

	private final static Logger LOGGER = LoggerFactory.getLogger(AbstractFleaJPADAOImpl.class);
	
	private Class<T> clazz;

	/**
	 * 获取T类型的Class对象
	 */
	public AbstractFleaJPADAOImpl() {
		// 获取泛型类的子类对象的Class对象
		Class clz = this.getClass();
		// 获取子类对象的泛型父类类型（也就是AbstractDaoImpl<T>）
		ParameterizedType type = (ParameterizedType) clz.getGenericSuperclass();
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Type={}", type);
		}
		Type[] types = type.getActualTypeArguments();
		clazz = (Class<T>) types[0];
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("ClassName={}", clazz.getName());
		}
	}

	@Override
	public T query(long entityId) throws Exception {
		checkPrimaryKey(entityId);
		T t = getEntityManager().find(clazz, entityId);
		return t;
	}
	
	@Override
	public T query(String entityId) throws Exception {
		checkPrimaryKey(entityId);
		T t = getEntityManager().find(clazz, entityId);
		return t;
	}

	@Override
	public List<T> query(Map<String, Object> paramterMap) throws Exception {
		FleaJPAQuery query = this.getQuery(null);
		query.equal(paramterMap);
		List<T> ts = query.getResultList();
		return ts;
	}
	
	@Override
	public List<T> query(Map<String, Object> paramterMap, String attrName, String orderBy) throws Exception {
		FleaJPAQuery query = this.getQuery(null);
		query.equal(paramterMap);
		query.addOrder(attrName, orderBy);
		List<T> ts = query.getResultList();
		return ts;
	}

	@Override
	public List<T> query(Map<String, Object> paramterMap, int start, int max) throws Exception {
		FleaJPAQuery query = this.getQuery(null);
		query.equal(paramterMap);
		List<T> ts = query.getResultList(start, max);
		return ts;
	}
	
	@Override
	public List<T> query(Map<String, Object> paramterMap, String attrName, String orderBy, int start, int max)
			throws Exception {
		FleaJPAQuery query = this.getQuery(null);
		query.equal(paramterMap);
		query.addOrder(attrName, orderBy);
		List<T> ts = query.getResultList(start, max);
		return ts;
	}

	@Override
	public List<T> queryAll() throws Exception {
		FleaJPAQuery query = this.getQuery(null);
		List<T> ts = query.getResultList();
		return ts;
	}
	
	@Override
	public List<T> queryAll(String attrName, String orderBy) throws Exception {
		FleaJPAQuery query = this.getQuery(null);
		query.addOrder(attrName, orderBy);
		List<T> ts = query.getResultList();
		return ts;
	}

	@Override
	public List<T> queryAll(int start, int max) throws Exception {
		FleaJPAQuery query = this.getQuery(null);
		List<T> ts = query.getResultList(start, max);
		return ts;
	}
	
	@Override
	public List<T> queryAll(String attrName, String orderBy, int start, int max) throws Exception {
		FleaJPAQuery query = this.getQuery(null);
		query.addOrder(attrName, orderBy);
		List<T> ts = query.getResultList(start, max);
		return ts;
	}

	@Override
	public int queryCount() throws Exception {
		FleaJPAQuery query = this.getQuery(Long.class);
		query.countDistinct();
		int count = ((Long) query.getSingleResult()).intValue();
		return count;
	}

	@Override
	public int queryCount(Map<String, Object> paramterMap) throws Exception {
		FleaJPAQuery query = this.getQuery(Long.class);
		query.equal(paramterMap);//添加Where子句查询条件
		query.countDistinct();//设置调用SQL的COUNT函数统计数目
		int count = ((Long) query.getSingleResult()).intValue();
		return count;
	}

	@Override
	public boolean remove(long entityId) throws Exception {
		checkPrimaryKey(entityId);
		final T old = this.query(entityId);
		if (old != null) {
			getEntityManager().remove(old);
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean remove(String entityId) throws Exception {
		checkPrimaryKey(entityId);
		final T old = this.query(entityId);
		if (old != null) {
			getEntityManager().remove(old);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public T store(T entity) throws Exception {
		if (entity == null) {
			throw new DaoException("The entity need to be stored is null");
		}
		T t = getEntityManager().merge(entity);
		return t;
	}
	
	@Override
	public List<T> batchUpdate(List<T> entitys) throws Exception {
		if (entitys == null || entitys.isEmpty()) {
			throw new DaoException("The entitys need to be saved is null or empty");
		}
		for(int i = 0; i< entitys.size(); i++){
			getEntityManager().merge(entitys.get(i));
		}
		return entitys;
	}

	@Override
	public void save(T entity) throws Exception {
		if (entity == null) {
			throw new DaoException("The entity need to be saved is null");
		}
		getEntityManager().persist(entity);
	}
	
	@Override
	public void batchSave(List<T> entitys) throws Exception {
		if (entitys == null || entitys.isEmpty()) {
			throw new DaoException("The entitys need to be saved is null or empty");
		}
		for(int i = 0; i< entitys.size(); i++){
			getEntityManager().persist(entitys.get(i));
		}
	}

	@Override
	public boolean update(T entity) throws Exception {
		if (entity == null) {
			throw new DaoException("The entity need to be updated is null");
		}
		String entityName = StringUtils.toLowerCaseInitial(clazz.getSimpleName());
		StringBuilder sql = new StringBuilder(
				DBConstants.SQLConstants.SQL_UPDATE + DBConstants.SQLConstants.SQL_BLANK + clazz.getSimpleName() + DBConstants.SQLConstants.SQL_BLANK
						+ entityName + DBConstants.SQLConstants.SQL_BLANK + DBConstants.SQLConstants.SQL_SET);
		StringBuilder whereSql = new StringBuilder(DBConstants.SQLConstants.SQL_WHERE + DBConstants.SQLConstants.SQL_BLANK);//where子句
		Field[] fields = entity.getClass().getDeclaredFields();//获取该实体类中的属性集
		Map<String,Object> map = new HashMap<String,Object>();//用于存储更新需要的参数
		boolean isFoundPrimaryKey = false;//实体中是否存在主键
		for (int i = 0; i < fields.length; i++) {
			// 2 表示private修饰的属性，可以过滤掉定义的静态变量等
			if (fields[i].getModifiers() != Modifier.PRIVATE) {
				continue;
			} else {
				String attrName = fields[i].getName();// 属性的字段名称
				String getter = CommonConstants.MethodConstants.GET + StringUtils.toUpperCaseInitial(attrName);// 属性的get方法名
				Method method = entity.getClass().getMethod(getter, new Class[] {});
				Object value = method.invoke(entity, new Object[] {});//该属性对应的值
				if(LOGGER.isDebugEnabled()){
					LOGGER.debug("type={},name={},value={}", fields[i].getType().toString(),
							fields[i].getName(), value);
				}
				boolean isPrimarykey = false;//判断当前的属性是否是主键
				if (!isFoundPrimaryKey) {
					Annotation[] annotations = fields[i].getAnnotations();//获取属性上的注解
					if(annotations == null || annotations.length == 0){//表示属性上没有注解
						annotations = method.getAnnotations();//获取方法上的注解
						if(annotations == null || annotations.length == 0){//表示方法上没有注解
							throw new DaoException("The Entity of " + clazz.getSimpleName() + "is not be annotated");
						}
					}
					for (Annotation an : annotations) {//遍历属性或get方法上的注解（注解一般要么全部写在属性上，要么全部写在get方法上）
						if (javax.persistence.Id.class.getName().equals(an.annotationType().getName())) {// 表示该属性是主键
							if (fields[i].getType() == long.class || fields[i].getType() == Long.class) {// 该实体的主键是long类型
								if (Long.valueOf(value.toString()) <= 0) {
									throw new DaoException("The primary key '" + attrName + "' is not a positive Integer");
								}
							} else if (fields[i].getType() == String.class) {// 该实体的主键是String类型
								if (ObjectUtils.isEmpty(value)) {
									throw new DaoException("The primary key '" + attrName + "' is null or empty");
								}
							} else {
								throw new DaoException("The primary key '" + attrName + "' must be long(Long) or String type");
							}
							whereSql.append(entityName).append(DBConstants.SQLConstants.SQL_DOT).append(attrName)
									.append(DBConstants.SQLConstants.SQL_BLANK).append(DBConstants.SQLConstants.SQL_EQUAL)
									.append(DBConstants.SQLConstants.SQL_BLANK).append(DBConstants.SQLConstants.SQL_COLON).append(attrName);
							isPrimarykey = true;// true表示该字段是主键
							isFoundPrimaryKey = true;// 表示找到了主键
							break;
						}
					}
				}
				
				if (value == null) {//值为空，直接跳过，继续下一个属性
					continue;
				} else {
					map.put(attrName, value);//添加不为空的数据（基本数据类型有默认值，这个需要关注）
					if (!isPrimarykey) {// 不是主键才可以添加在SET子句中
						sql.append(DBConstants.SQLConstants.SQL_BLANK).append(entityName).append(DBConstants.SQLConstants.SQL_DOT)
								.append(attrName).append(DBConstants.SQLConstants.SQL_BLANK).append(DBConstants.SQLConstants.SQL_EQUAL)
								.append(DBConstants.SQLConstants.SQL_BLANK).append(DBConstants.SQLConstants.SQL_COLON).append(attrName)
								.append(DBConstants.SQLConstants.SQL_COMMA);
					}
				}
			}
		}
		if (isFoundPrimaryKey) {//只有存在主键的实体，才能去执行更新操作
			sql.deleteCharAt(sql.length() - 1);// 去掉最后的逗号
			sql.append(DBConstants.SQLConstants.SQL_BLANK).append(whereSql);
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("SQL = {}", sql.toString());
			}
			Query query = getEntityManager().createQuery(sql.toString());
			Set<String> keySet = map.keySet();
			Iterator<String> it = keySet.iterator();
			while (it.hasNext()) {
				String key = it.next();
				Object value = map.get(key);
				query.setParameter(key, value);// 设置参数
			}
			int row = query.executeUpdate();// 执行更新语句
			if (row == 1) {
				return true;
			} else {
				return false;
			}
		} else {
			throw new DaoException("The primary key of " + clazz.getSimpleName() + " is not exist");
		}
	}
	
	/**
	 * 
	 * <p>获取指定的查询对象</p>
	 * 
	 * @version v1.0.0
	 * @date 2017年3月6日
	 *
	 * @return
	 */
	protected FleaJPAQuery getQuery(Class result){
		FleaJPAQuery query = FleaJPAQuery.getQuery();
		query.init(getEntityManager(), clazz, result);
		return query;
	}
	
	/**
	 * 校验主键
	 * 
	 * @date 2018年5月30日
	 *
	 * @param entityId
	 * @throws DaoException
	 */
	private void checkPrimaryKey(Object entityId) throws DaoException{
		if(entityId.getClass() == long.class || entityId.getClass() == Long.class) {// 该实体的主键是long类型
			if (Long.valueOf(entityId.toString()) <= 0) {
				throw new DaoException("The primary key must be positive number");
			}
		}else if(entityId.getClass() == String.class){// 该实体的主键是String类型
			if(ObjectUtils.isEmpty(entityId)){
				throw new DaoException("The primary key is null or empty");
			}
		}else{
			throw new DaoException("The primary key must be long(Long) or String type");
		}
	}

	/**
	 * 获取EntityManager对象
	 * 
	 * @date 2017年1月27日
	 *
	 * @return 
	 */
	protected abstract EntityManager getEntityManager();
	

}
