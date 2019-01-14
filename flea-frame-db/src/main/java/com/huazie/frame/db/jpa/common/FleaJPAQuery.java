package com.huazie.frame.db.jpa.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.DBConstants;
import com.huazie.frame.db.common.exception.DaoException;

/**
 * 
 * 自定义Flea JPA查询对象, 用于实现 JPA方式的数据库查询操作，可以自行组装查询条件
 * 
 * @author huazie
 * @version v1.0.0
 * @date 2016年9月27日
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public final class FleaJPAQuery implements Serializable {

	private static final long serialVersionUID = -4272805743956204878L;

	private final static Logger LOGGER = LoggerFactory.getLogger(FleaJPAQuery.class);
	
	private static volatile FleaJPAQuery query;
	
	private EntityManager entityManager;		// JPA中用于增删改查的接口

	private Class sourceClazz;					// 实体类类对象
	
	private Class resultClazz;					// 操作结果类类对象

	private Root root;							// 

	private List<Predicate> predicates;			// 

	private CriteriaBuilder criteriaBuilder;	// 

	private CriteriaQuery criteriaQuery;		// 
	
	private List<Order> orders;					// 

	private String groupBy;						// 

	private FleaJPAQuery() {
	}

	/**
	 * getQuery()之后，一定要调用该方法进行初始化
	 * 
	 * @date 2016年9月27日
	 *
	 * @param entityManager
	 *            JPA中用于增删改查的接口
	 * @param source
	 *            实体类类对象
	 * @param result
	 *            操作结果类类对象
	 */
	public void init(EntityManager entityManager, Class source, Class result) {
		
		this.entityManager = entityManager;
		this.sourceClazz = source;
		this.resultClazz = result;
		this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
		if(this.resultClazz == null){
			this.criteriaQuery = this.criteriaBuilder.createQuery(this.sourceClazz);
		}else{
			this.criteriaQuery = this.criteriaBuilder.createQuery(this.resultClazz);
		}
		this.root = this.criteriaQuery.from(this.sourceClazz);
		this.predicates = new ArrayList<Predicate>();
		this.orders = new ArrayList<Order>();
	}

	/**
	 * 
	 * 获取查询对象
	 * 
	 * @date 2016年9月27日
	 *
	 * @return FleaJPAQuery查询对象
	 */
	public static FleaJPAQuery getQuery() {
		if (query == null) {
			synchronized (FleaJPAQuery.class) {
				if(query == null){
					query = new FleaJPAQuery();
				}
			}
		}
		return query;
	}

	/**
	 * 
	 * @Function equal(String attrName, Object value)
	 * 相等的条件
	 * 
	 * @date 2016年9月27日
	 *
	 * @param attrName
	 *            属性名
	 * @param value
	 *            属性值
	 * @throws DaoException
	 */
	public void equal(String attrName, Object value) throws DaoException {
		if (StringUtils.isNotBlank(attrName)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "AttrName is null or empty");
		}
		if (ObjectUtils.isEmpty(value)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL,
					"The value of '" + attrName + "' is null");
		}
		if(LOGGER.isDebugEnabled()){
        	LOGGER.debug("FMJPAQuery##equal(attrName, value) -->> AttrName={},Value={}", attrName, value);
    	}
		this.predicates.add(this.criteriaBuilder.equal(this.root.get(attrName), value));
	}

	/**
	 * 
	 * @Function equal(Map<String, Object> paramterMap)
	 * 多个相等的条件
	 * 
	 * @date 2016年9月27日
	 *
	 * @param paramterMap
	 *            条件集合
	 * @throws DaoException
	 */
	public void equal(Map<String, Object> paramterMap) throws DaoException {
		if (paramterMap == null || paramterMap.isEmpty()) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "ParamterMap is null or empty");
		}
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("FMJPAQuery##equal(paramterMap) -->> paramterMap={}", paramterMap);
    	}
		Set<String> keySet = paramterMap.keySet();
		Iterator<String> it = keySet.iterator();
		while (it.hasNext()) {
			String key = it.next();
			Object value = paramterMap.get(key);
			this.predicates.add(this.criteriaBuilder.equal(this.root.get(key), value));
		}
	}

	/**
	 * 
	 * @Function notEqual(String attrName, Object value)
	 * 不相等的条件
	 * 
	 * @date 2016年9月27日
	 *
	 * @param attrName
	 *            属性名
	 * @param value
	 *            属性值
	 * @throws DaoException
	 */
	public void notEqual(String attrName, Object value) throws DaoException {
		if (StringUtils.isNotBlank(attrName)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "AttrName is null or empty");
		}
		if (ObjectUtils.isEmpty(value)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "This value of '" + attrName + "' is null or empty");
		}
		if(LOGGER.isDebugEnabled()){
        	LOGGER.debug("FMJPAQuery##notEqual(attrName, value) -->> AttrName={},Value={}", attrName, value);
    	}
		this.predicates.add(this.criteriaBuilder.notEqual(this.root.get(attrName), value));
	}

	/**
	 * 
	 * @Function isNull(String attrName)
	 * 某属性值为空的条件
	 * 
	 * @date 2016年9月27日
	 *
	 * @param attrName
	 *            属性名
	 * @throws DaoException
	 */
	public void isNull(String attrName) throws DaoException {
		if (StringUtils.isNotBlank(attrName)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "AttrName is null or empty");
		}
		if(LOGGER.isDebugEnabled()){
        	LOGGER.debug("FMJPAQuery##isNull(attrName) -->> AttrName={},Value={}", attrName);
    	}
		this.predicates.add(this.criteriaBuilder.isNull(this.root.get(attrName)));
	}

	/**
	 * 
	 * @Function isNotNull(String attrName)
	 * 某属性值为非空的条件
	 * 
	 * @date 2016年9月27日
	 *
	 * @param attrName
	 *            属性名
	 * @throws DaoException
	 */
	public void isNotNull(String attrName) throws DaoException {
		if (StringUtils.isNotBlank(attrName)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "AttrName is null or empty");
		}
		if(LOGGER.isDebugEnabled()){
        	LOGGER.debug("FMJPAQuery##isNotNull(attrName) -->> AttrName={},Value={}", attrName);
    	}
		this.predicates.add(this.criteriaBuilder.isNotNull(this.root.get(attrName)));
	}

    /**
     * 
     * @Function in(String attrName, Collection value)
     * attrName属性的值在value集合中的查询条件
     * 
     * @date 2016年9月27日
     *
     * @param attrName
     *            属性名称
     * @param value
     *            值集合
     * @throws DaoException 
     */
    public void in(String attrName, Collection value) throws DaoException {
    	if (StringUtils.isNotBlank(attrName)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "AttrName is null or empty");
		}
		if (ObjectUtils.isEmpty(value) || value.isEmpty()) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "This value of collection is null or empty");
		}
		if(LOGGER.isDebugEnabled()){
        	LOGGER.debug("FMJPAQuery##in(attrName, value) -->> AttrName={},Value={}", attrName, value);
    	}
        Iterator iterator = value.iterator();
        In in = this.criteriaBuilder.in(this.root.get(attrName));
        while (iterator.hasNext()) {
            in.value(iterator.next());
        }
        this.predicates.add(in);
    }
	
    /**
     * 
     * @Function notIn(String attrName, Collection value)
     *  attrName属性的值不在value集合中的查询条件
     * 
     * @date 2016年9月27日
     *
     * @param attrName
     *            属性名称
     * @param value
     *            值集合
     * @throws DaoException 
     */
    public void notIn(String attrName, Collection value) throws DaoException {
     	if (StringUtils.isNotBlank(attrName)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "AttrName is null or empty");
		}
		if (ObjectUtils.isEmpty(value) || value.isEmpty()) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "This value of collection is null or empty");
		}
		if(LOGGER.isDebugEnabled()){
        	LOGGER.debug("FMJPAQuery##notIn(attrName, value) -->> AttrName={},Value={}", attrName, value);
    	}
        Iterator iterator = value.iterator();
        In in = this.criteriaBuilder.in(this.root.get(attrName));
        while (iterator.hasNext()) {
            in.value(iterator.next());
        }
        this.predicates.add(this.criteriaBuilder.not(in));
    }
    
    /**
     * 
     * @Function  like(String attrName, String value)
     *  模糊匹配条件（value一定要是字符类型的）
     * 
     * @date 2016年9月27日
     *
     * @param attrName
     *            属性名称
     * @param value
     *            属性值
     * @throws DaoException
     */
    public void like(String attrName, String value) throws DaoException {
    	if (StringUtils.isNotBlank(attrName)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "AttrName is null or empty");
		}
    	if (ObjectUtils.isEmpty(value)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "This value of '" + attrName + "' is null or empty");
		}
    	if(LOGGER.isDebugEnabled()){
        	LOGGER.debug("FMJPAQuery##like(attrName, value) -->> AttrName={},Value={}", attrName, value);
    	}
        if (value.indexOf("%") < 0){
        	value = "%" + value + "%";
        }
        this.predicates.add(this.criteriaBuilder.like(this.root.get(attrName), value));
    }
    
    /**
     * 
     * @Function le(String attrName, Number value) 
     * 小于等于
     * 
     * @date 2016年9月27日
     *
     * @param attrName
     *            属性名称
     * @param value
     *            属性值
     * @throws DaoException
     */
    public void le(String attrName, Number value) throws DaoException {
    	if (StringUtils.isNotBlank(attrName)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "AttrName is null or empty");
		}
    	if (ObjectUtils.isEmpty(value)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "This value of '" + attrName + "' is null or empty");
		}
    	if(LOGGER.isDebugEnabled()){
        	LOGGER.debug("FMJPAQuery##le(attrName, value) -->> AttrName={},Value={}", attrName, value);
    	}
        this.predicates.add(this.criteriaBuilder.le(this.root.get(attrName), value));
    }
    
    /**
     * 
     * @Function lt(String attrName, Number value) 
     * 小于
     * 
     * @date 2016年9月27日
     *
     * @param attrName
     *            属性名称
     * @param value
     *            属性值
     * @throws DaoException
     */
    public void lt(String attrName, Number value) throws DaoException {
    	if (StringUtils.isNotBlank(attrName)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "AttrName is null or empty");
		}
    	if (ObjectUtils.isEmpty(value)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "This value of '" + attrName + "' is null or empty");
		}
    	if(LOGGER.isDebugEnabled()){
        	LOGGER.debug("FMJPAQuery##lt(attrName, value) -->> AttrName={},Value={}", attrName, value);
    	}
        this.predicates.add(this.criteriaBuilder.lt(this.root.get(attrName), value));
    }
    
    /**
     * 
     * @Function ge(String attrName, Number value) 
     * 大于等于
     * 
     * @date 2016年9月27日
     *
     * @param attrName
     *            属性名称
     * @param value
     *            属性值
     * @throws DaoException
     */
    public void ge(String attrName, Number value) throws DaoException {
    	if (StringUtils.isNotBlank(attrName)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "AttrName is null or empty");
		}
    	if (ObjectUtils.isEmpty(value)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "This value of '" + attrName + "' is null or empty");
		}
    	if(LOGGER.isDebugEnabled()){
        	LOGGER.debug("FMJPAQuery##ge(attrName, value) -->> AttrName={},Value={}", attrName, value);
    	}
        this.predicates.add(this.criteriaBuilder.ge(this.root.get(attrName), value));
    }
    
    /**
     * 
     * @Function gt(String attrName, Number value) 
     * 大于
     * 
     * @date 2016年9月27日
     *
     * @param attrName
     *            属性名称
     * @param value
     *            属性值
     * @throws DaoException
     */
    public void gt(String attrName, Number value) throws DaoException {
    	if (StringUtils.isNotBlank(attrName)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "AttrName is null or empty");
		}
    	if (ObjectUtils.isEmpty(value)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "This value of '" + attrName + "' is null or empty");
		}
    	if(LOGGER.isDebugEnabled()){
        	LOGGER.debug("FMJPAQuery##gt(attrName, value) -->> AttrName={},Value={}", attrName, value);
    	}
        this.predicates.add(this.criteriaBuilder.gt(this.root.get(attrName), value));
    }
    
    /**
	 * 
	 * @Function between(String attrName, Date startTime, Date endTime)
	 * 时间区间查询条件
	 * 
	 * @date 2016年9月27日
	 *
	 * @param attrName
	 *            属性名
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @throws DaoException
	 */
    public void between(String attrName, Date startTime, Date endTime) throws DaoException{
    	if (StringUtils.isNotBlank(attrName)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "AttrName is null or empty");
		}
		if (ObjectUtils.isEmpty(startTime) || ObjectUtils.isEmpty(endTime)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "StartTime or EndTime is null");
		}
    	if(startTime.after(endTime)){
    		throw new DaoException(DBConstants.DaoConstants.DAO_INVALID_START_END_TIME, "开始时间不能大于结束时间");
    	}
    	if(LOGGER.isDebugEnabled()){
        	LOGGER.debug("FMJPAQuery##between(attrName, startTime, endTime) -->> AttrName={},StartTime={},EndTime={}", attrName, startTime, endTime);
    	}
    	this.predicates.add(this.criteriaBuilder.between(this.root.get(attrName), startTime, endTime));
    }
    
    /**
	 * 
	 * @Function greaterThan(String attrName, Date value)
	 * 大于某个日期值
	 * 
	 * @date 2017年3月6日
	 *
	 * @param attrName
	 *            属性名
	 * @param value
	 *            指定日期值
	 * @throws DaoException
	 */
    public void greaterThan(String attrName, Date value) throws DaoException{
    	if (StringUtils.isNotBlank(attrName)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "AttrName is null or empty");
		}
    	if (ObjectUtils.isEmpty(value)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "This value of '" + attrName + "' is null or empty");
		}
    	if(LOGGER.isDebugEnabled()){
    		LOGGER.debug("FMJPAQuery##greaterThan() -->> AttrName={}, Date={}", attrName, value);
    	}
    	this.predicates.add(this.criteriaBuilder.greaterThan(this.root.get(attrName), value));
    }
    
    /**
     * 
     * @Function greaterThanOrEqualTo(String attrName, Date value)
     * 大于等于某个日期值
     * 
     * @date 2017年3月6日
	 *
	 * @param attrName
	 *            属性名
	 * @param value
	 *            指定日期值
     * @throws DaoException
     */
    public void greaterThanOrEqualTo(String attrName, Date value) throws DaoException{
    	if (StringUtils.isNotBlank(attrName)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "AttrName is null or empty");
		}
    	if (ObjectUtils.isEmpty(value)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "This value of '" + attrName + "' is null or empty");
		}
    	if(LOGGER.isDebugEnabled()){
    		LOGGER.debug("FMJPAQuery##greaterThanOrEqualTo() -->> AttrName={}, Date={}", attrName, value);
    	}
    	this.predicates.add(this.criteriaBuilder.greaterThanOrEqualTo(this.root.get(attrName), value));
    }
    
    /**
     * 
     * @Function lessThan(String attrName, Date value)
     * 小于某个日期值
     * 
     * @date 2017年3月6日
	 *
	 * @param attrName
	 *            属性名
	 * @param value
	 *            指定日期值
     * @throws DaoException
     */
    public void lessThan(String attrName, Date value) throws DaoException{
    	if (StringUtils.isNotBlank(attrName)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "AttrName is null or empty");
		}
    	if (ObjectUtils.isEmpty(value)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "This value of '" + attrName + "' is null or empty");
		}
    	if(LOGGER.isDebugEnabled()){
    		LOGGER.debug("FMJPAQuery##lessThan() -->> AttrName={}, Date={}", attrName, value);
    	}
    	this.predicates.add(this.criteriaBuilder.lessThan(this.root.get(attrName), value));
    }
    
    /**
     * 
     * @Function lessThanOrEqualTo(String attrName, Date value)
     * 小于等于某个日期值
     * 
     * @date 2017年3月6日
     *
	 * @param attrName
	 *            属性名
	 * @param value
	 *            指定日期值
     * @throws DaoException
     */
    public void lessThanOrEqualTo(String attrName, Date value) throws DaoException{
    	if (StringUtils.isNotBlank(attrName)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "AttrName is null or empty");
		}
    	if (ObjectUtils.isEmpty(value)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "This value of '" + attrName + "' is null or empty");
		}
    	if(LOGGER.isDebugEnabled()){
    		LOGGER.debug("FMJPAQuery##lessThanOrEqualTo() -->> AttrName={}, Date={}", attrName, value);
    	}
    	this.predicates.add(this.criteriaBuilder.lessThanOrEqualTo(this.root.get(attrName), value));
    }
    
    /**
     * 
     * @Function count()
     * 统计数目，在getSingleResult调用之前使用
     * 
     * @date 2016年9月27日
     *
     */
    public void count(){
    	criteriaQuery.select(criteriaBuilder.count(root));
    }
    
    /**
     * 
     * @Function countDistinct()
     * 统计数目(带distinct参数)，在getSingleResult调用之前使用
     * 
     * @date 2016年9月27日
     *
     */
    public void countDistinct(){
    	criteriaQuery.select(criteriaBuilder.countDistinct(root));
    }
    
    /**
     * 
     * @Function max(String attrName)
     * 设置查询某属性的最大值，在getSingleResult调用之前使用
     * 
     * @date 2016年9月27日
     *
     * @param attrName
     * @throws DaoException
     */
    public void max(String attrName) throws DaoException{
    	if (StringUtils.isNotBlank(attrName)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "AttrName is null or empty");
		}
    	if(LOGGER.isDebugEnabled()){
    		LOGGER.debug("FMJPAQuery##max(attrName) -->> AttrName={}", attrName);
    	}
    	criteriaQuery.select(criteriaBuilder.max(root.get(attrName)));
    }
    
    /**
     * 
     * @Function min(String attrName)
     * 设置查询某属性的最小值，在getSingleResult调用之前使用
     * 
     * @date 2016年9月27日
     *
     * @param attrName
     * @throws DaoException
     */
    public void min(String attrName) throws DaoException{
    	if (StringUtils.isNotBlank(attrName)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "AttrName is null or empty");
		}
    	if(LOGGER.isDebugEnabled()){
    		LOGGER.debug("FMJPAQuery##min(attrName) -->> AttrName={}", attrName);
    	}
    	criteriaQuery.select(criteriaBuilder.min(root.get(attrName)));
    }
    
    /**
     * 
     * @Function avg(String attrName)
     * 设置查询某属性的平均值，在getSingleResult调用之前使用
     * 
     * @date 2016年9月27日
     *
     * @param attrName
     * @throws DaoException
     */
    public void avg(String attrName) throws DaoException{
    	if (StringUtils.isNotBlank(attrName)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "AttrName is null or empty");
		}
    	if(LOGGER.isDebugEnabled()){
    		LOGGER.debug("FMJPAQuery##avg(attrName) -->> AttrName={}", attrName);
    	}
    	criteriaQuery.select(criteriaBuilder.avg(root.get(attrName)));
    }
    
    /**
     * 
     * @Function sum(String attrName)
     * 设置查询某属性的值的总和，在getSingleResult调用之前使用
     * 
     * @date 2016年9月27日
     *
     * @param attrName
     * @throws DaoException
     */
    public void sum(String attrName) throws DaoException{
    	if (StringUtils.isNotBlank(attrName)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "AttrName is null or empty");
		}
    	if(LOGGER.isDebugEnabled()){
    		LOGGER.debug("FMJPAQuery##sum(attrName) -->> AttrName={}", attrName);
    	}
    	criteriaQuery.select(criteriaBuilder.sum(root.get(attrName)));
    }
    
    /**
     * 
     * @Function sumAsLong(String attrName)
     * 设置查询某属性的值的总和，在getSingleResult调用之前使用
     * 
     * @date 2016年9月27日
     *
     * @param attrName
     * @throws DaoException
     */
    public void sumAsLong(String attrName) throws DaoException{
    	if (StringUtils.isNotBlank(attrName)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "AttrName is null or empty");
		}
    	if(LOGGER.isDebugEnabled()){
    		LOGGER.debug("FMJPAQuery##sumAsLong(attrName) -->> AttrName={}", attrName);
    	}
    	criteriaQuery.select(criteriaBuilder.sumAsLong(root.get(attrName)));
    }
    
    /**
     * 
     * @Function sumAsDouble(String attrName)
     * 设置查询某属性的值的总和，在getSingleResult调用之前使用
     * 
     * @date 2016年9月27日
     *
     * @param attrName
     * @throws DaoException
     */
    public void sumAsDouble(String attrName) throws DaoException{
    	if (StringUtils.isNotBlank(attrName)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "AttrName is null or empty");
		}
    	if(LOGGER.isDebugEnabled()){
    		LOGGER.debug("FMJPAQuery##sumAsDouble(attrName) -->> AttrName={}", attrName);
    	}
    	criteriaQuery.select(criteriaBuilder.sumAsDouble(root.get(attrName)));
    }
    
    /**
     * 
     * 去重某一列
     * 
     * @date 2017年5月1日
     *
     * @param attrName
     * @throws DaoException
     */
    public void distinct(String attrName) throws DaoException{
    	if (StringUtils.isNotBlank(attrName)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "AttrName is null or empty");
		}
    	if(LOGGER.isDebugEnabled()){
    		LOGGER.debug("FMJPAQuery##sumAsDouble(attrName) -->> AttrName={}", attrName);
    	}
    	criteriaQuery.select(root.get(attrName)).distinct(true);
    }
    
    /**
	 * 
	 * @Function addOrder(String attrName, String order)
	 * 添加order by的属性
	 * 
	 * @date 2016年9月27日
	 *
	 * @param attrName
	 *            属性名
	 * @param orderBy
	 *            排序顺序
	 * @throws DaoException
	 */
    public void addOrder(String attrName, String orderBy) throws DaoException {
    	if (StringUtils.isNotBlank(attrName)) {
			throw new DaoException(DBConstants.DaoConstants.DAO_PARAMETER_IS_NULL, "AttrName is null or empty");
		}
        if (this.orders == null){
        	this.orders = new ArrayList();
        }
		if (orderBy.equalsIgnoreCase(DBConstants.SQLConstants.SQL_ORDER_ASC)) {
			this.orders.add(this.criteriaBuilder.asc(this.root.get(attrName)));
		} else if (orderBy.equalsIgnoreCase(DBConstants.SQLConstants.SQL_ORDER_DESC)) {
			this.orders.add(this.criteriaBuilder.desc(this.root.get(attrName)));
		} else {
			throw new DaoException(DBConstants.DaoConstants.DAO_INVALID_ORDER, "Order '" + orderBy + "' is invalid, it must be 'asc' or 'desc'");
		}
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("FMJPAQuery##addOrder(attrName, orderBy) -->> AttrName={},OrderBy={}", attrName, orderBy);
    	}
    }
    
	/**
	 * 
	 * 获取查询的结果集合
	 * 
	 * @date 2016年9月27日
	 *
	 * @return 查询结果集合
	 * @throws DaoException
	 */
	public List getResultList() throws DaoException {
		if(this.sourceClazz == null){
			throw new DaoException(DBConstants.DaoConstants.DAO_INVALID_QUERY, "This Query is invalid,SourceClazz is null");
		}
		if(this.predicates != null && !this.predicates.isEmpty()){
			// 将所有条件用 and 联合起来
			this.criteriaQuery.where(this.criteriaBuilder.and(this.predicates.toArray(new Predicate[0])));
			this.criteriaQuery.select(this.root);
		}
		if (this.orders != null && !this.orders.isEmpty()) {
            this.criteriaQuery.orderBy(this.orders);
        }
		if (this.groupBy != null && !this.groupBy.isEmpty()) {//有待修改
			this.criteriaQuery.groupBy(root.get(this.groupBy));
		}
		return this.entityManager.createQuery(this.criteriaQuery).getResultList();
	}
	
	/**
	 * 
	 * 获取查询的结果集合（设置分页）
	 * 
	 * @date 2016年9月27日
	 *
	 * @param start
	 *            开始查询记录行
	 * @param max
	 *            最大查询数量
	 * @return
	 * @throws DaoException 
	 */
	public List getResultList(int start, int max) throws DaoException{
		if(this.sourceClazz == null){
			throw new DaoException(DBConstants.DaoConstants.DAO_INVALID_QUERY, "This Query is invalid, SourceClazz is null");
		}
		if(predicates != null && !predicates.isEmpty()){
			// 将所有条件用 and 联合起来
			this.criteriaQuery.where(this.criteriaBuilder.and(this.predicates.toArray(new Predicate[0])));
			this.criteriaQuery.select(this.root);
		}
		if (this.orders != null && !this.orders.isEmpty()) {
            this.criteriaQuery.orderBy(this.orders);
        }
		final TypedQuery query = this.entityManager.createQuery(this.criteriaQuery);
		query.setMaxResults(max);// 设置一次最大查询数量
		query.setFirstResult(start);// 设置开始查询记录行
		return query.getResultList();
	}
	
	/**
	 * 
	 * 获取查询的单个结果集合<br/>
	 * 注意：这个需要提前调用select
	 * 
	 * @date 2016年9月27日
	 *
	 * @return 查询单个结果的集合
	 * @throws DaoException 
	 */
	public List getSingleResultList() throws DaoException {
		if(this.sourceClazz == null){
			throw new DaoException(DBConstants.DaoConstants.DAO_INVALID_QUERY, "This Query is invalid,SourceClazz is null");
		}
		if(this.predicates != null && !this.predicates.isEmpty()){
			// 将所有条件用 and 联合起来
			this.criteriaQuery.where(this.criteriaBuilder.and(this.predicates.toArray(new Predicate[0])));
		}
		if (this.orders != null && !this.orders.isEmpty()) {
            this.criteriaQuery.orderBy(this.orders);
        }
		if (this.groupBy != null && !this.groupBy.isEmpty()) {//有待修改
			this.criteriaQuery.groupBy(root.get(this.groupBy));
		}
		return this.entityManager.createQuery(this.criteriaQuery).getResultList();
	}
	
	/**
	 * 
	 * 获取查询的单个结果集合（设置分页）
	 * 
	 * 这个需要提前调用select
	 * 
	 * @date 2016年9月27日
	 *
	 * @param start
	 *            开始查询记录行
	 * @param max
	 *            最大查询数量
	 * @return 查询的单个结果集合（分页）
	 * @throws DaoException 
	 */
	public List getSingleResultList(int start, int max) throws DaoException{
		if(this.sourceClazz == null){
			throw new DaoException(DBConstants.DaoConstants.DAO_INVALID_QUERY, "This Query is invalid, SourceClazz is null");
		}
		if(predicates != null && !predicates.isEmpty()){
			// 将所有条件用 and 联合起来
			this.criteriaQuery.where(this.criteriaBuilder.and(this.predicates.toArray(new Predicate[0])));
		}
		if (this.orders != null && !this.orders.isEmpty()) {
            this.criteriaQuery.orderBy(this.orders);
        }
		final TypedQuery query = this.entityManager.createQuery(this.criteriaQuery);
		query.setMaxResults(max);// 设置一次最大查询数量
		query.setFirstResult(start);// 设置开始查询记录行
		return query.getResultList();
	}
	
	/**
	 * 
	 * 获取查询的单个结果
	 * 
	 * @date 2016年9月27日
	 *
	 * @return 查询的单个结果
	 * @throws DaoException
	 */
	public Object getSingleResult() throws DaoException{
		if(this.resultClazz == null){
			throw new DaoException(DBConstants.DaoConstants.DAO_INVALID_QUERY, "This Query is invalid,ResultClazz is null");
		}
		if(predicates != null && !predicates.isEmpty()){
			// 将所有条件用 and 联合起来
			this.criteriaQuery.where(this.criteriaBuilder.and(this.predicates.toArray(new Predicate[0])));
		}
		if (this.orders != null && !this.orders.isEmpty()) {
			// 将order by 添加到查询语句中
            this.criteriaQuery.orderBy(this.orders);
        }
		Object result = entityManager.createQuery(criteriaQuery).getSingleResult();
		return result;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Class getSourceClazz() {
		return sourceClazz;
	}

	public void setSourceClazz(Class sourceClazz) {
		this.sourceClazz = sourceClazz;
	}

	public Class getResultClazz() {
		return resultClazz;
	}

	public void setResultClazz(Class resultClazz) {
		this.resultClazz = resultClazz;
	}

	public Root getRoot() {
		return this.root;
	}

	public void setRoot(Root root) {
		this.root = root;
	}

	public List<Predicate> getPredicates() {
		return this.predicates;
	}

	public void setPredicates(List<Predicate> predicates) {
		this.predicates = predicates;
	}

	public CriteriaBuilder getCriteriaBuilder() {
		return this.criteriaBuilder;
	}

	public void setCriteriaBuilder(CriteriaBuilder criteriaBuilder) {
		this.criteriaBuilder = criteriaBuilder;
	}

	public CriteriaQuery getCriteriaQuery() {
		return this.criteriaQuery;
	}

	public void setCriteriaQuery(CriteriaQuery criteriaQuery) {
		this.criteriaQuery = criteriaQuery;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

}
