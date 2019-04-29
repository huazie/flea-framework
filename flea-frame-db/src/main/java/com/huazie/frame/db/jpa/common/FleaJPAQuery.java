package com.huazie.frame.db.jpa.common;

import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.DBConstants;
import com.huazie.frame.db.common.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p> 自定义Flea JPA查询对象, 用于实现 JPA方式的数据库查询操作，可以自行组装查询条件 </p>
 *
 * @author huazie
 * @version v1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public final class FleaJPAQuery implements Serializable {

    private static final long serialVersionUID = -4272805743956204878L;

    private final static Logger LOGGER = LoggerFactory.getLogger(FleaJPAQuery.class);

    private static volatile FleaJPAQuery query;

    private EntityManager entityManager; // JPA中用于增删改查的接口

    private Class sourceClazz; // 实体类类对象

    private Class resultClazz; // 操作结果类类对象

    private Root root; //

    private List<Predicate> predicates; //

    private CriteriaBuilder criteriaBuilder; //

    private CriteriaQuery criteriaQuery; //

    private List<Order> orders; // 排序

    private String groupBy; // 分组

    private FleaJPAQuery() {
    }

    /**
     * <p> getQuery()之后，一定要调用该方法进行初始化 </p>
     *
     * @param entityManager JPA中用于增删改查的接口
     * @param sourceClazz   实体类类对象
     * @param resultClazz   操作结果类类对象
     * @since 1.0.0
     */
    public void init(EntityManager entityManager, Class sourceClazz, Class resultClazz) {

        this.entityManager = entityManager;
        this.sourceClazz = sourceClazz;
        this.resultClazz = resultClazz;
        criteriaBuilder = entityManager.getCriteriaBuilder();
        if (null == resultClazz) {
            criteriaQuery = criteriaBuilder.createQuery(sourceClazz);
        } else {
            criteriaQuery = criteriaBuilder.createQuery(resultClazz);
        }
        root = criteriaQuery.from(sourceClazz);
        predicates = new ArrayList<Predicate>();
        orders = new ArrayList<Order>();
    }

    /**
     * <p> 获取查询对象 </p>
     *
     * @return FleaJPAQuery查询对象
     * @since 1.0.0
     */
    public static FleaJPAQuery getQuery() {
        if (null == query) {
            synchronized (FleaJPAQuery.class) {
                if (null == query) {
                    query = new FleaJPAQuery();
                }
            }
        }
        return query;
    }

    /**
     * <p> 等于条件 </p>
     *
     * @param attrName 属性名
     * @param value    属性值
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public void equal(String attrName, Object value) throws DaoException {
        if (StringUtils.isBlank(attrName)) {
            throw new DaoException("AttrName is null or empty");
        }
        if (ObjectUtils.isEmpty(value)) {
            throw new DaoException("The value of '" + attrName + "' is null");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##equal(attrName, value) -->> AttrName={},Value={}", attrName, value);
        }
        predicates.add(criteriaBuilder.equal(root.get(attrName), value));
    }

    /**
     * <p> 等于条件 </p>
     *
     * @param paramterMap 条件集合
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public void equal(Map<String, Object> paramterMap) throws DaoException {
        if (paramterMap == null || paramterMap.isEmpty()) {
            throw new DaoException("ParamterMap is null or empty");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##equal(paramterMap) -->> paramterMap={}", paramterMap);
        }
        Set<String> keySet = paramterMap.keySet();
        Iterator<String> it = keySet.iterator();
        while (it.hasNext()) {
            String key = it.next();
            Object value = paramterMap.get(key);
            predicates.add(criteriaBuilder.equal(root.get(key), value));
        }
    }

    /**
     * <p> 不等于条件 </p>
     *
     * @param attrName 属性名
     * @param value    属性值
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public void notEqual(String attrName, Object value) throws DaoException {
        if (StringUtils.isBlank(attrName)) {
            throw new DaoException("AttrName is null or empty");
        }
        if (ObjectUtils.isEmpty(value)) {
            throw new DaoException("This value of '" + attrName + "' is null or empty");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##notEqual(attrName, value) -->> AttrName={},Value={}", attrName, value);
        }
        predicates.add(criteriaBuilder.notEqual(root.get(attrName), value));
    }

    /**
     * <p> 等于条件 </p>
     *
     * @param paramterMap 条件集合
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public void notEqual(Map<String, Object> paramterMap) throws DaoException {
        if (paramterMap == null || paramterMap.isEmpty()) {
            throw new DaoException("ParamterMap is null or empty");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##equal(paramterMap) -->> paramterMap={}", paramterMap);
        }
        Set<String> keySet = paramterMap.keySet();
        Iterator<String> it = keySet.iterator();
        while (it.hasNext()) {
            String key = it.next();
            Object value = paramterMap.get(key);
            predicates.add(criteriaBuilder.notEqual(root.get(key), value));
        }
    }

    /**
     * <p>某属性值为空的条件</p>
     *
     * @param attrName 属性名
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public void isNull(String attrName) throws DaoException {
        if (StringUtils.isBlank(attrName)) {
            throw new DaoException("AttrName is null or empty");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##isNull(attrName) -->> AttrName={},Value={}", attrName);
        }
        predicates.add(criteriaBuilder.isNull(root.get(attrName)));
    }

    /**
     * <p> 某属性值为非空的条件 </p>
     *
     * @param attrName 属性名
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public void isNotNull(String attrName) throws DaoException {
        if (StringUtils.isBlank(attrName)) {
            throw new DaoException("AttrName is null or empty");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##isNotNull(attrName) -->> AttrName={},Value={}", attrName);
        }
        predicates.add(criteriaBuilder.isNotNull(root.get(attrName)));
    }

    /**
     * <p> attrName属性的值在value集合中的查询条件 </p>
     *
     * @param attrName 属性名称
     * @param value    值集合
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public void in(String attrName, Collection value) throws DaoException {
        if (StringUtils.isBlank(attrName)) {
            throw new DaoException("AttrName is null or empty");
        }
        if (ObjectUtils.isEmpty(value) || value.isEmpty()) {
            throw new DaoException("This value of collection is null or empty");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##in(attrName, value) -->> AttrName={},Value={}", attrName, value);
        }
        Iterator iterator = value.iterator();
        In in = criteriaBuilder.in(root.get(attrName));
        while (iterator.hasNext()) {
            in.value(iterator.next());
        }
        predicates.add(in);
    }

    /**
     * <p> attrName属性的值不在value集合中的查询条件 </p>
     *
     * @param attrName 属性名称
     * @param value    值集合
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public void notIn(String attrName, Collection value) throws DaoException {
        if (StringUtils.isBlank(attrName)) {
            throw new DaoException("AttrName is null or empty");
        }
        if (ObjectUtils.isEmpty(value) || value.isEmpty()) {
            throw new DaoException("This value of collection is null or empty");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##notIn(attrName, value) -->> AttrName={},Value={}", attrName, value);
        }
        Iterator iterator = value.iterator();
        In in = criteriaBuilder.in(root.get(attrName));
        while (iterator.hasNext()) {
            in.value(iterator.next());
        }
        predicates.add(criteriaBuilder.not(in));
    }

    /**
     * <p> 模糊匹配条件（value一定要是字符类型的） </p>
     *
     * @param attrName 属性名称
     * @param value    属性值
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public void like(String attrName, String value) throws DaoException {
        if (StringUtils.isBlank(attrName)) {
            throw new DaoException("AttrName is null or empty");
        }
        if (ObjectUtils.isEmpty(value)) {
            throw new DaoException("This value of '" + attrName + "' is null or empty");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##like(attrName, value) -->> AttrName={},Value={}", attrName, value);
        }
        if (value.indexOf("%") < 0) {
            value = "%" + value + "%";
        }
        predicates.add(criteriaBuilder.like(root.get(attrName), value));
    }

    /**
     * <p> 小于等于条件 </p>
     *
     * @param attrName 属性名称
     * @param value    属性值
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public void le(String attrName, Number value) throws DaoException {
        if (StringUtils.isBlank(attrName)) {
            throw new DaoException("AttrName is null or empty");
        }
        if (ObjectUtils.isEmpty(value)) {
            throw new DaoException("This value of '" + attrName + "' is null or empty");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##le(attrName, value) -->> AttrName={},Value={}", attrName, value);
        }
        predicates.add(criteriaBuilder.le(root.get(attrName), value));
    }

    /**
     * <p>小于条件</p>
     *
     * @param attrName 属性名称
     * @param value    属性值
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public void lt(String attrName, Number value) throws DaoException {
        if (StringUtils.isBlank(attrName)) {
            throw new DaoException("AttrName is null or empty");
        }
        if (ObjectUtils.isEmpty(value)) {
            throw new DaoException("This value of '" + attrName + "' is null or empty");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##lt(attrName, value) -->> AttrName={},Value={}", attrName, value);
        }
        predicates.add(criteriaBuilder.lt(root.get(attrName), value));
    }

    /**
     * <p> 大于等于条件 </p>
     *
     * @param attrName 属性名称
     * @param value    属性值
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public void ge(String attrName, Number value) throws DaoException {
        if (StringUtils.isBlank(attrName)) {
            throw new DaoException("AttrName is null or empty");
        }
        if (ObjectUtils.isEmpty(value)) {
            throw new DaoException("This value of '" + attrName + "' is null or empty");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##ge(attrName, value) -->> AttrName={},Value={}", attrName, value);
        }
        predicates.add(criteriaBuilder.ge(root.get(attrName), value));
    }

    /**
     * <p> 大于条件 </p>
     *
     * @param attrName 属性名称
     * @param value    属性值
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public void gt(String attrName, Number value) throws DaoException {
        if (StringUtils.isBlank(attrName)) {
            throw new DaoException("AttrName is null or empty");
        }
        if (ObjectUtils.isEmpty(value)) {
            throw new DaoException("This value of '" + attrName + "' is null or empty");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##gt(attrName, value) -->> AttrName={},Value={}", attrName, value);
        }
        predicates.add(criteriaBuilder.gt(root.get(attrName), value));
    }

    /**
     * <p> 时间区间查询条件 </p>
     *
     * @param attrName  属性名
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public void between(String attrName, Date startTime, Date endTime) throws DaoException {
        if (StringUtils.isBlank(attrName)) {
            throw new DaoException("AttrName is null or empty");
        }
        if (ObjectUtils.isEmpty(startTime) || ObjectUtils.isEmpty(endTime)) {
            throw new DaoException("StartTime or EndTime is null");
        }
        if (startTime.after(endTime)) {
            throw new DaoException("开始时间不能大于结束时间");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##between(attrName, startTime, endTime) -->> AttrName={},StartTime={},EndTime={}", attrName, startTime, endTime);
        }
        predicates.add(criteriaBuilder.between(root.get(attrName), startTime, endTime));
    }

    /**
     * <p> 大于某个日期值条件 </p>
     *
     * @param attrName 属性名
     * @param value    指定日期值
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public void greaterThan(String attrName, Date value) throws DaoException {
        if (StringUtils.isBlank(attrName)) {
            throw new DaoException("AttrName is null or empty");
        }
        if (ObjectUtils.isEmpty(value)) {
            throw new DaoException("This value of '" + attrName + "' is null or empty");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##greaterThan() -->> AttrName={}, Date={}", attrName, value);
        }
        predicates.add(criteriaBuilder.greaterThan(root.get(attrName), value));
    }

    /**
     * <p> 大于等于某个日期值 </p>
     *
     * @param attrName 属性名
     * @param value    指定日期值
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public void greaterThanOrEqualTo(String attrName, Date value) throws DaoException {
        if (StringUtils.isBlank(attrName)) {
            throw new DaoException("AttrName is null or empty");
        }
        if (ObjectUtils.isEmpty(value)) {
            throw new DaoException("This value of '" + attrName + "' is null or empty");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##greaterThanOrEqualTo() -->> AttrName={}, Date={}", attrName, value);
        }
        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(attrName), value));
    }

    /**
     * <p> 小于某个日期值条件 </p>
     *
     * @param attrName 属性名
     * @param value    指定日期值
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public void lessThan(String attrName, Date value) throws DaoException {
        if (StringUtils.isBlank(attrName)) {
            throw new DaoException("AttrName is null or empty");
        }
        if (ObjectUtils.isEmpty(value)) {
            throw new DaoException("This value of '" + attrName + "' is null or empty");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##lessThan() -->> AttrName={}, Date={}", attrName, value);
        }
        predicates.add(criteriaBuilder.lessThan(root.get(attrName), value));
    }

    /**
     * <p> 小于等于某个日期值条件 </p>
     *
     * @param attrName 属性名
     * @param value    指定日期值
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public void lessThanOrEqualTo(String attrName, Date value) throws DaoException {
        if (StringUtils.isBlank(attrName)) {
            throw new DaoException("AttrName is null or empty");
        }
        if (ObjectUtils.isEmpty(value)) {
            throw new DaoException("This value of '" + attrName + "' is null or empty");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##lessThanOrEqualTo() -->> AttrName={}, Date={}", attrName, value);
        }
        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(attrName), value));
    }

    /**
     * <p> 统计数目，在getSingleResult调用之前使用 </p>
     *
     * @since 1.0.0
     */
    public void count() {
        criteriaQuery.select(criteriaBuilder.count(root));
    }

    /**
     * <p> 统计数目(带distinct参数)，在getSingleResult调用之前使用 </p>
     *
     * @since 1.0.0
     */
    public void countDistinct() {
        criteriaQuery.select(criteriaBuilder.countDistinct(root));
    }

    /**
     * <p> 设置查询某属性的最大值，在getSingleResult调用之前使用 </p>
     *
     * @param attrName
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public void max(String attrName) throws DaoException {
        if (StringUtils.isBlank(attrName)) {
            throw new DaoException("AttrName is null or empty");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##max(attrName) -->> AttrName={}", attrName);
        }
        criteriaQuery.select(criteriaBuilder.max(root.get(attrName)));
    }

    /**
     * <p> 设置查询某属性的最小值，在getSingleResult调用之前使用 </p>
     *
     * @param attrName
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public void min(String attrName) throws DaoException {
        if (StringUtils.isBlank(attrName)) {
            throw new DaoException("AttrName is null or empty");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##min(attrName) -->> AttrName={}", attrName);
        }
        criteriaQuery.select(criteriaBuilder.min(root.get(attrName)));
    }

    /**
     * <p> 设置查询某属性的平均值，在getSingleResult调用之前使用 </p>
     *
     * @param attrName
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public void avg(String attrName) throws DaoException {
        if (StringUtils.isBlank(attrName)) {
            throw new DaoException("AttrName is null or empty");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##avg(attrName) -->> AttrName={}", attrName);
        }
        criteriaQuery.select(criteriaBuilder.avg(root.get(attrName)));
    }

    /**
     * <p> 设置查询某属性的值的总和，在getSingleResult调用之前使用 </p>
     *
     * @param attrName
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public void sum(String attrName) throws DaoException {
        if (StringUtils.isBlank(attrName)) {
            throw new DaoException("AttrName is null or empty");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##sum(attrName) -->> AttrName={}", attrName);
        }
        criteriaQuery.select(criteriaBuilder.sum(root.get(attrName)));
    }

    /**
     * <p> 设置查询某属性的值的总和，在getSingleResult调用之前使用 </p>
     *
     * @param attrName
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public void sumAsLong(String attrName) throws DaoException {
        if (StringUtils.isBlank(attrName)) {
            throw new DaoException("AttrName is null or empty");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##sumAsLong(attrName) -->> AttrName={}", attrName);
        }
        criteriaQuery.select(criteriaBuilder.sumAsLong(root.get(attrName)));
    }

    /**
     * <p> 设置查询某属性的值的总和，在getSingleResult调用之前使用 </p>
     *
     * @param attrName
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public void sumAsDouble(String attrName) throws DaoException {
        if (StringUtils.isBlank(attrName)) {
            throw new DaoException("AttrName is null or empty");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##sumAsDouble(attrName) -->> AttrName={}", attrName);
        }
        criteriaQuery.select(criteriaBuilder.sumAsDouble(root.get(attrName)));
    }

    /**
     * <p> 去重某一列 </p>
     *
     * @param attrName
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public void distinct(String attrName) throws DaoException {
        if (StringUtils.isBlank(attrName)) {
            throw new DaoException("AttrName is null or empty");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##sumAsDouble(attrName) -->> AttrName={}", attrName);
        }
        criteriaQuery.select(root.get(attrName)).distinct(true);
    }

    /**
     * <p> 添加order by的属性 </p>
     *
     * @param attrName 属性名
     * @param orderBy  排序顺序
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public void addOrder(String attrName, String orderBy) throws DaoException {
        if (StringUtils.isBlank(attrName)) {
            throw new DaoException("AttrName is null or empty");
        }
        if (orders == null) {
            orders = new ArrayList();
        }
        if (orderBy.equalsIgnoreCase(DBConstants.SQLConstants.SQL_ORDER_ASC)) {
            orders.add(criteriaBuilder.asc(root.get(attrName)));
        } else if (orderBy.equalsIgnoreCase(DBConstants.SQLConstants.SQL_ORDER_DESC)) {
            orders.add(criteriaBuilder.desc(root.get(attrName)));
        } else {
            throw new DaoException("Order '" + orderBy + "' is invalid, it must be 'asc' or 'desc'");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##addOrder(attrName, orderBy) -->> AttrName={},OrderBy={}", attrName, orderBy);
        }
    }

    /**
     * <p> 获取查询的结果集合 </p>
     *
     * @return 查询结果集合
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public List getResultList() throws DaoException {
        if (sourceClazz == null) {
            throw new DaoException("This Query is invalid,SourceClazz is null");
        }
        if (predicates != null && !predicates.isEmpty()) {
            // 将所有条件用 and 联合起来
            criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
            criteriaQuery.select(root);
        }
        if (orders != null && !orders.isEmpty()) {
            criteriaQuery.orderBy(orders);
        }
        if (groupBy != null && !groupBy.isEmpty()) {//有待修改
            criteriaQuery.groupBy(root.get(groupBy));
        }
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * <p> 获取查询的结果集合（设置分页） </p>
     *
     * @param start 开始查询记录行
     * @param max   最大查询数量
     * @return 查询的结果集合（可以分页）
     * @since 1.0.0
     */
    public List getResultList(int start, int max) throws DaoException {
        if (sourceClazz == null) {
            throw new DaoException("This Query is invalid, SourceClazz is null");
        }
        if (predicates != null && !predicates.isEmpty()) {
            // 将所有条件用 and 联合起来
            criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
            criteriaQuery.select(root);
        }
        if (orders != null && !orders.isEmpty()) {
            criteriaQuery.orderBy(orders);
        }
        final TypedQuery query = entityManager.createQuery(criteriaQuery);
        query.setMaxResults(max);// 设置一次最大查询数量
        query.setFirstResult(start);// 设置开始查询记录行
        return query.getResultList();
    }

    /**
     * <p> 获取查询的单个结果集合 </p>
     * <p> 注意：这个需要提前调用select </p>
     *
     * @return 查询单个结果的集合
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public List getSingleResultList() throws DaoException {
        if (sourceClazz == null) {
            throw new DaoException("This Query is invalid,SourceClazz is null");
        }
        if (predicates != null && !predicates.isEmpty()) {
            // 将所有条件用 and 联合起来
            criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        }
        if (orders != null && !orders.isEmpty()) {
            criteriaQuery.orderBy(orders);
        }
        if (groupBy != null && !groupBy.isEmpty()) {//有待修改
            criteriaQuery.groupBy(root.get(groupBy));
        }
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * <p> 获取查询的单个结果集合（设置分页）</p>
     * <p> 注意：这个需要提前调用select </p>
     *
     * @param start 开始查询记录行
     * @param max   最大查询数量
     * @return 查询的单个结果集合（分页）
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public List getSingleResultList(int start, int max) throws DaoException {
        if (sourceClazz == null) {
            throw new DaoException("This Query is invalid, SourceClazz is null");
        }
        if (predicates != null && !predicates.isEmpty()) {
            // 将所有条件用 and 联合起来
            criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        }
        if (orders != null && !orders.isEmpty()) {
            criteriaQuery.orderBy(orders);
        }
        final TypedQuery query = entityManager.createQuery(criteriaQuery);
        query.setMaxResults(max);// 设置一次最大查询数量
        query.setFirstResult(start);// 设置开始查询记录行
        return query.getResultList();
    }

    /**
     * <p> 获取查询的单个结果 </p>
     *
     * @return 查询的单个结果
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public Object getSingleResult() throws DaoException {
        if (resultClazz == null) {
            throw new DaoException("This Query is invalid,ResultClazz is null");
        }
        if (predicates != null && !predicates.isEmpty()) {
            // 将所有条件用 and 联合起来
            criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        }
        if (orders != null && !orders.isEmpty()) {
            // 将order by 添加到查询语句中
            criteriaQuery.orderBy(orders);
        }
        Object result = entityManager.createQuery(criteriaQuery).getSingleResult();
        return result;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Class getSourceClazz() {
        return sourceClazz;
    }

    public Class getResultClazz() {
        return resultClazz;
    }

    public Root getRoot() {
        return root;
    }

    public List<Predicate> getPredicates() {
        return predicates;
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return criteriaBuilder;
    }

    public CriteriaQuery getCriteriaQuery() {
        return criteriaQuery;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public String getGroupBy() {
        return groupBy;
    }

}
