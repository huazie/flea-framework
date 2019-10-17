package com.huazie.frame.db.jpa.common;

import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.MapUtils;
import com.huazie.frame.common.util.NumberUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.ReflectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.DBConstants;
import com.huazie.frame.db.common.exception.DaoException;
import com.huazie.frame.db.common.table.split.TableSplitHelper;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Closeable;
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
 * @version 1.0.0
 * @see FleaJPAQueryPool Flea JPA查询对象池
 * @since 1.0.0
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public final class FleaJPAQuery implements Closeable {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaJPAQuery.class);

    private FleaJPAQueryPool fleaObjectPool; // Flea JPA查询对象池

    private EntityManager entityManager; // JPA中用于增删改查的持久化接口

    private Class sourceClazz; // 实体类类对象

    private Class resultClazz; // 操作结果类类对象

    private Root root; // 根SQL表达式对象

    private CriteriaBuilder criteriaBuilder; //标准化生成器

    private CriteriaQuery criteriaQuery; // 标准化查询对象

    private List<Predicate> predicates = new ArrayList<Predicate>(); // Where条件集合

    private List<Order> orders; // 排序集合

    private List<Expression> groups; // 分组集合

    private Object entity; // 查询的数据库实体类实例

    public FleaJPAQuery() {
    }

    @Override
    public void close() {
        if (ObjectUtils.isNotEmpty(fleaObjectPool)) {
            fleaObjectPool.returnFleaObject(this);
            fleaObjectPool = null;
        }
    }

    /**
     * <p> Flea JPA查询对象池获取之后，一定要调用该方法进行初始化 </p>
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
        // 从持久化接口中获取标准化生成器
        criteriaBuilder = entityManager.getCriteriaBuilder();
        // 通过标准化生成器 获取 标准化查询对象
        if (ObjectUtils.isEmpty(resultClazz)) {
            // 行记录查询结果
            criteriaQuery = criteriaBuilder.createQuery(sourceClazz);
        } else {
            // 单个查询结果
            criteriaQuery = criteriaBuilder.createQuery(resultClazz);
        }
        // 通过标准化查询对象，获取根SQL表达式对象
        root = criteriaQuery.from(sourceClazz);
    }

    /**
     * <p> 初始化查询实体 </p>
     *
     * @param entity 实体类实例
     * @return
     * @throws Exception
     */
    public FleaJPAQuery initQueryEntity(Object entity) throws Exception {

        if (ObjectUtils.isEmpty(entity) || (ObjectUtils.isNotEmpty(sourceClazz) && !sourceClazz.isInstance(entity))) {
            return this;
        }

        this.entity = entity;

        // 处理并添加分表信息，如果不存在分表则不处理
        TableSplitHelper.findTableSplitHandle().handle(criteriaQuery, entity);

        return this;
    }

    /**
     * <p> 等于条件 </p>
     * <p> 需要先初始化实体类,即调用initQueryEntity(Object entity) </p>
     *
     * @param attrName 属性名
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery equal(String attrName) throws DaoException {
        return equal(attrName, getAttrValue(attrName));
    }

    /**
     * <p> 等于条件 </p>
     *
     * @param attrName 属性名
     * @param value    属性值
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery equal(String attrName, Object value) throws DaoException {
        return newEqualExpression(attrName, value, true);
    }

    /**
     * <p> 等于条件 </p>
     * <p> 需要先初始化实体类,即调用initQueryEntity(Object entity) </p>
     *
     * @param attrNames 实体属性名集合
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery equal(Set<String> attrNames) throws DaoException {
        return newEqualExpression(attrNames, true);
    }

    /**
     * <p> 等于条件 </p>
     *
     * @param paramMap 参数集合
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery equal(Map<String, Object> paramMap) throws DaoException {
        return newEqualExpression(paramMap, true);
    }

    /**
     * <p> 不等于条件 </p>
     * <p> 需要先初始化实体类,即调用initQueryEntity(Object entity) </p>
     *
     * @param attrName 属性名
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery notEqual(String attrName) throws DaoException {
        return notEqual(attrName, getAttrValue(attrName));
    }

    /**
     * <p> 不等于条件 </p>
     *
     * @param attrName 属性名
     * @param value    属性值
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery notEqual(String attrName, Object value) throws DaoException {
        return newEqualExpression(attrName, value, false);
    }

    /**
     * <p> 不等于条件 </p>
     * <p> 需要先初始化实体类,即调用initQueryEntity(Object entity) </p>
     *
     * @param attrNames 实体属性名集合
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery notEqual(Set<String> attrNames) throws DaoException {
        return newEqualExpression(attrNames, false);
    }

    /**
     * <p> 不等于条件 </p>
     *
     * @param paramMap 条件集合
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery notEqual(Map<String, Object> paramMap) throws DaoException {
        return newEqualExpression(paramMap, false);
    }

    /**
     * <p> 构建 equal 或 notEqual 表达式 </p>
     *
     * @param attrName 属性名
     * @param value    属性值
     * @param isEqual  true: 构建equal表达式; false: 构建notEqual表达式
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    private FleaJPAQuery newEqualExpression(String attrName, Object value, boolean isEqual) throws DaoException {
        // 属性列名非空校验
        checkAttrName(attrName);

        if (ObjectUtils.isEmpty(value)) {
            // 不做处理，直接返回即可
            return this;
        }
        if (LOGGER.isDebugEnabled()) {
            if (isEqual) {
                LOGGER.debug("FMJPAQuery##equal(attrName, value) -->> AttrName = {}, Value = {}", attrName, value);
            } else {
                LOGGER.debug("FMJPAQuery##notEqual(attrName, value) -->> AttrName = {}, Value = {}", attrName, value);
            }
        }
        if (isEqual) {
            predicates.add(criteriaBuilder.equal(root.get(attrName), value));
        } else {
            predicates.add(criteriaBuilder.notEqual(root.get(attrName), value));
        }
        return this;
    }

    /**
     * <p> 构建 equal 或 notEqual 表达式 </p>
     *
     * @param attrNames 属性名集合
     * @param isEqual   true: 构建equal表达式; false: 构建notEqual表达式
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    private FleaJPAQuery newEqualExpression(Set<String> attrNames, boolean isEqual) throws DaoException {
        if (CollectionUtils.isEmpty(attrNames)) {
            // 条件参数Map不能为空
            throw new DaoException("ERROR-DB-DAO0000000003");
        }
        if (LOGGER.isDebugEnabled()) {
            if (isEqual) {
                LOGGER.debug("FMJPAQuery##equal(Set<String> attrNames) -->> attrNames = {}", attrNames);
            } else {
                LOGGER.debug("FMJPAQuery##notEqual(Set<String> attrNames) -->> attrNames = {}", attrNames);
            }
        }
        Iterator<String> attrNameIt = attrNames.iterator();
        while (attrNameIt.hasNext()) {
            String attrName = attrNameIt.next();
            Object attrValue = getAttrValue(attrName);
            if (ObjectUtils.isEmpty(attrValue)) {
                // 属性值为空，跳过处理下一个
                continue;
            }
            if (isEqual) {
                predicates.add(criteriaBuilder.equal(root.get(attrName), attrValue));
            } else {
                predicates.add(criteriaBuilder.notEqual(root.get(attrName), attrValue));
            }
        }
        return this;
    }

    /**
     * <p> 构建 equal 或 notEqual 表达式 </p>
     *
     * @param paramMap 条件集合
     * @param isEqual  true: 构建equal表达式; false: 构建notEqual表达式
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    private FleaJPAQuery newEqualExpression(Map<String, Object> paramMap, boolean isEqual) throws DaoException {
        if (MapUtils.isEmpty(paramMap)) {
            // 条件参数Map不能为空
            throw new DaoException("ERROR-DB-DAO0000000003");
        }
        if (LOGGER.isDebugEnabled()) {
            if (isEqual) {
                LOGGER.debug("FMJPAQuery##equal(Map<String, Object>) -->> paramMap = {}", paramMap);
            } else {
                LOGGER.debug("FMJPAQuery##notEqual(Map<String, Object>) -->> paramMap = {}", paramMap);
            }
        }
        Set<String> keySet = paramMap.keySet();
        Iterator<String> it = keySet.iterator();
        while (it.hasNext()) {
            String key = it.next();
            Object value = paramMap.get(key);
            if (isEqual) {
                predicates.add(criteriaBuilder.equal(root.get(key), value));
            } else {
                predicates.add(criteriaBuilder.notEqual(root.get(key), value));
            }
        }
        return this;
    }

    /**
     * <p>某属性值为空的条件</p>
     *
     * @param attrName 属性名
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery isNull(String attrName) throws DaoException {
        return newIsNullExpression(attrName, true);
    }

    /**
     * <p> 某属性值为非空的条件 </p>
     *
     * @param attrName 属性名
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery isNotNull(String attrName) throws DaoException {
        return newIsNullExpression(attrName, false);
    }

    /**
     * <p> 构建 isNull 或 isNotNull 表达式  </p>
     *
     * @param attrName 属性名
     * @param isNull   true: 构建isNull表达式; false: 构建isNotNull表达式
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    private FleaJPAQuery newIsNullExpression(String attrName, boolean isNull) throws DaoException {
        // 属性列名非空校验
        checkAttrName(attrName);

        if (LOGGER.isDebugEnabled()) {
            if (isNull) {
                LOGGER.debug("FMJPAQuery##isNull(attrName) -->> AttrName = {}, Value = {}", attrName);
            } else {
                LOGGER.debug("FMJPAQuery##isNotNull(attrName) -->> AttrName = {}, Value = {}", attrName);
            }
        }
        if (isNull) {
            predicates.add(criteriaBuilder.isNull(root.get(attrName)));
        } else {
            predicates.add(criteriaBuilder.isNotNull(root.get(attrName)));
        }
        return this;
    }

    /**
     * <p> in条件，attrName属性的值在value集合中 </p>
     *
     * @param attrName 属性名称
     * @param value    值集合
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery in(String attrName, Collection value) throws DaoException {
        return newInExpression(attrName, value, true);
    }

    /**
     * <p> not in条件，attrName属性的值不在value集合中 </p>
     *
     * @param attrName 属性名称
     * @param value    值集合
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery notIn(String attrName, Collection value) throws DaoException {
        return newInExpression(attrName, value, false);
    }

    /**
     * <p> 构建 In 或 notIn 表达式 </p>
     *
     * @param attrName 属性名称
     * @param value    值集合
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    private FleaJPAQuery newInExpression(String attrName, Collection value, boolean isIn) throws DaoException {
        // 属性列名非空校验
        checkAttrName(attrName);

        if (CollectionUtils.isEmpty(value)) {
            // 条件参数Collection不能为空
            throw new DaoException("ERROR-DB-DAO0000000004");
        }
        if (LOGGER.isDebugEnabled()) {
            if (isIn) {
                LOGGER.debug("FMJPAQuery##in(attrName, value) -->> AttrName = {}, Value = {}", attrName, value);
            } else {
                LOGGER.debug("FMJPAQuery##notIn(attrName, value) -->> AttrName = {}, Value = {}", attrName, value);
            }
        }
        Iterator iterator = value.iterator();
        In in = criteriaBuilder.in(root.get(attrName));
        while (iterator.hasNext()) {
            in.value(iterator.next());
        }
        if (isIn) {
            predicates.add(in);
        } else {
            predicates.add(criteriaBuilder.not(in));
        }
        return this;
    }

    /**
     * <p> 模糊匹配条件（value一定要是字符串类型的） </p>
     * <p> 需要先初始化实体类,即调用initQueryEntity(Object entity) </p>
     *
     * @param attrName 属性名称
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery like(String attrName) throws DaoException {
        return like(attrName, StringUtils.valueOf(getAttrValue(attrName)));
    }

    /**
     * <p> 模糊匹配条件（value一定要是字符串类型的） </p>
     *
     * @param attrName 属性名称
     * @param value    属性值
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery like(String attrName, String value) throws DaoException {
        // 属性列名非空校验
        checkAttrName(attrName);

        if (ObjectUtils.isEmpty(value)) {
            // 不做处理，直接返回即可
            return this;
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##like(attrName, value) -->> AttrName = {}, Value = {}", attrName, value);
        }
        if (!value.contains(DBConstants.SQLConstants.SQL_PERCENT)) {
            value = DBConstants.SQLConstants.SQL_PERCENT + value + DBConstants.SQLConstants.SQL_PERCENT;
        }
        predicates.add(criteriaBuilder.like(root.get(attrName), value));
        return this;
    }

    /**
     * <p> 小于等于条件 </p>
     * <p> 需要先初始化实体类,即调用initQueryEntity(Object entity) </p>
     *
     * @param attrName 属性名称
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery le(String attrName) throws DaoException {
        return le(attrName, getNumberAttrValue(attrName));
    }

    /**
     * <p> 小于等于条件 </p>
     *
     * @param attrName 属性名称
     * @param value    属性值
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery le(String attrName, Number value) throws DaoException {
        // 属性列名非空校验
        checkAttrName(attrName);

        if (ObjectUtils.isEmpty(value)) {
            // 不做处理，直接返回即可
            return this;
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##le(attrName, value) -->> AttrName = {}, Value = {}", attrName, value);
        }
        predicates.add(criteriaBuilder.le(root.get(attrName), value));
        return this;
    }

    /**
     * <p> 小于条件 </p>
     * <p> 需要先初始化实体类,即调用initQueryEntity(Object entity) </p>
     *
     * @param attrName 属性名称
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery lt(String attrName) throws DaoException {
        return lt(attrName, getNumberAttrValue(attrName));
    }

    /**
     * <p>小于条件</p>
     *
     * @param attrName 属性名称
     * @param value    属性值
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery lt(String attrName, Number value) throws DaoException {
        // 属性列名非空校验
        checkAttrName(attrName);

        if (ObjectUtils.isEmpty(value)) {
            // 不做处理，直接返回即可
            return this;
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##lt(attrName, value) -->> AttrName = {}, Value = {}", attrName, value);
        }
        predicates.add(criteriaBuilder.lt(root.get(attrName), value));
        return this;
    }

    /**
     * <p> 大于等于条件 </p>
     * <p> 需要先初始化实体类,即调用initQueryEntity(Object entity) </p>
     *
     * @param attrName 属性名称
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery ge(String attrName) throws DaoException {
        return ge(attrName, getNumberAttrValue(attrName));
    }

    /**
     * <p> 大于等于条件 </p>
     *
     * @param attrName 属性名称
     * @param value    属性值
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery ge(String attrName, Number value) throws DaoException {
        // 属性列名非空校验
        checkAttrName(attrName);

        if (ObjectUtils.isEmpty(value)) {
            // 不做处理，直接返回即可
            return this;
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##ge(attrName, value) -->> AttrName = {}, Value = {}", attrName, value);
        }
        predicates.add(criteriaBuilder.ge(root.get(attrName), value));
        return this;
    }

    /**
     * <p> 大于条件 </p>
     * <p> 需要先初始化实体类,即调用initQueryEntity(Object entity) </p>
     *
     * @param attrName 属性名称
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery gt(String attrName) throws DaoException {
        return gt(attrName, getNumberAttrValue(attrName));
    }

    /**
     * <p> 大于条件 </p>
     *
     * @param attrName 属性名称
     * @param value    属性值
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery gt(String attrName, Number value) throws DaoException {
        // 属性列名非空校验
        checkAttrName(attrName);

        if (ObjectUtils.isEmpty(value)) {
            // 不做处理，直接返回即可
            return this;
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##gt(attrName, value) -->> AttrName = {}, Value = {}", attrName, value);
        }
        predicates.add(criteriaBuilder.gt(root.get(attrName), value));
        return this;
    }

    /**
     * <p> 时间区间查询条件 </p>
     *
     * @param attrName  属性名
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery between(String attrName, Date startTime, Date endTime) throws DaoException {
        // 属性列名非空校验
        checkAttrName(attrName);

        if (ObjectUtils.isEmpty(startTime) || ObjectUtils.isEmpty(endTime)) {
            // 开始时间或结束时间为空
            throw new DaoException("ERROR-DB-DAO0000000005");
        }
        if (startTime.after(endTime)) {
            // 开始时间必须小于结束时间
            throw new DaoException("ERROR-DB-DAO0000000006");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##between(attrName, startTime, endTime) -->> AttrName = {}, StartTime = {}, EndTime = {}", attrName, startTime, endTime);
        }
        predicates.add(criteriaBuilder.between(root.get(attrName), startTime, endTime));
        return this;
    }

    /**
     * <p> 大于某个日期值条件 </p>
     * <p> 需要先初始化实体类,即调用initQueryEntity(Object entity) </p>
     *
     * @param attrName 属性名
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery greaterThan(String attrName) throws DaoException {
        return greaterThan(attrName, getDateAttrValue(attrName));
    }

    /**
     * <p> 大于某个日期值条件 </p>
     *
     * @param attrName 属性名
     * @param value    指定日期值
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery greaterThan(String attrName, Date value) throws DaoException {
        // 属性列名非空校验
        checkAttrName(attrName);

        if (ObjectUtils.isEmpty(value)) {
            // 不做处理，直接返回即可
            return this;
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##greaterThan() -->> AttrName = {}, Date = {}", attrName, value);
        }
        predicates.add(criteriaBuilder.greaterThan(root.get(attrName), value));
        return this;
    }

    /**
     * <p> 大于等于某个日期值 </p>
     * <p> 需要先初始化实体类,即调用initQueryEntity(Object entity) </p>
     *
     * @param attrName 属性名
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery greaterThanOrEqualTo(String attrName) throws DaoException {
        return greaterThanOrEqualTo(attrName, getDateAttrValue(attrName));
    }

    /**
     * <p> 大于等于某个日期值 </p>
     *
     * @param attrName 属性名
     * @param value    指定日期值
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery greaterThanOrEqualTo(String attrName, Date value) throws DaoException {
        // 属性列名非空校验
        checkAttrName(attrName);

        if (ObjectUtils.isEmpty(value)) {
            // 不做处理，直接返回即可
            return this;
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##greaterThanOrEqualTo() -->> AttrName = {}, Date = {}", attrName, value);
        }
        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(attrName), value));
        return this;
    }

    /**
     * <p> 小于某个日期值条件 </p>
     * <p> 需要先初始化实体类,即调用initQueryEntity(Object entity) </p>
     *
     * @param attrName 属性名
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery lessThan(String attrName) throws DaoException {
        return lessThan(attrName, getDateAttrValue(attrName));
    }

    /**
     * <p> 小于某个日期值条件 </p>
     *
     * @param attrName 属性名
     * @param value    指定日期值
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery lessThan(String attrName, Date value) throws DaoException {
        // 属性列名非空校验
        checkAttrName(attrName);

        if (ObjectUtils.isEmpty(value)) {
            // 不做处理，直接返回即可
            return this;
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##lessThan() -->> AttrName = {}, Date = {}", attrName, value);
        }
        predicates.add(criteriaBuilder.lessThan(root.get(attrName), value));
        return this;
    }

    /**
     * <p> 小于等于某个日期值条件 </p>
     * <p> 需要先初始化实体类,即调用initQueryEntity(Object entity) </p>
     *
     * @param attrName 属性名
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery lessThanOrEqualTo(String attrName) throws DaoException {
        return lessThanOrEqualTo(attrName, getDateAttrValue(attrName));
    }

    /**
     * <p> 小于等于某个日期值条件 </p>
     *
     * @param attrName 属性名
     * @param value    指定日期值
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery lessThanOrEqualTo(String attrName, Date value) throws DaoException {
        // 属性列名非空校验
        checkAttrName(attrName);

        if (ObjectUtils.isEmpty(value)) {
            // 不做处理，直接返回即可
            return this;
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##lessThanOrEqualTo() -->> AttrName = {}, Date = {}", attrName, value);
        }
        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(attrName), value));
        return this;
    }

    /**
     * <p> 统计数目，在getSingleResult调用之前使用 </p>
     *
     * @return Flea JPA查询对象
     * @since 1.0.0
     */
    public FleaJPAQuery count() {
        criteriaQuery.select(criteriaBuilder.count(root));
        return this;
    }

    /**
     * <p> 统计数目(带distinct参数)，在getSingleResult调用之前使用 </p>
     *
     * @return Flea JPA查询对象
     * @since 1.0.0
     */
    public FleaJPAQuery countDistinct(String attrName) throws DaoException {
        // 属性列名非空校验
        checkAttrName(attrName);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##countDistinct(attrName) -->> AttrName = {}", attrName);
        }

        criteriaQuery.select(criteriaBuilder.countDistinct(root.get(attrName)));
        return this;
    }

    /**
     * <p> 设置查询某属性的最大值，在getSingleResult调用之前使用 </p>
     *
     * @param attrName 属性名
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery max(String attrName) throws DaoException {
        // 属性列名非空校验
        checkAttrName(attrName);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##max(attrName) -->> AttrName = {}", attrName);
        }
        criteriaQuery.select(criteriaBuilder.max(root.get(attrName)));
        return this;
    }

    /**
     * <p> 设置查询某属性的最小值，在getSingleResult调用之前使用 </p>
     *
     * @param attrName 属性名
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery min(String attrName) throws DaoException {
        // 属性列名非空校验
        checkAttrName(attrName);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##min(attrName) -->> AttrName = {}", attrName);
        }
        criteriaQuery.select(criteriaBuilder.min(root.get(attrName)));
        return this;
    }

    /**
     * <p> 设置查询某属性的平均值，在getSingleResult调用之前使用 </p>
     *
     * @param attrName 属性名
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery avg(String attrName) throws DaoException {
        // 属性列名非空校验
        checkAttrName(attrName);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##avg(attrName) -->> AttrName = {}", attrName);
        }
        criteriaQuery.select(criteriaBuilder.avg(root.get(attrName)));
        return this;
    }

    /**
     * <p> 设置查询某属性的值的总和，在getSingleResult调用之前使用 </p>
     *
     * @param attrName 属性名
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery sum(String attrName) throws DaoException {
        // 属性列名非空校验
        checkAttrName(attrName);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##sum(attrName) -->> AttrName = {}", attrName);
        }
        criteriaQuery.select(criteriaBuilder.sum(root.get(attrName)));
        return this;
    }

    /**
     * <p> 设置查询某属性的值的总和，在getSingleResult调用之前使用 </p>
     *
     * @param attrName 属性名
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery sumAsLong(String attrName) throws DaoException {
        // 属性列名非空校验
        checkAttrName(attrName);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##sumAsLong(attrName) -->> AttrName = {}", attrName);
        }
        criteriaQuery.select(criteriaBuilder.sumAsLong(root.get(attrName)));
        return this;
    }

    /**
     * <p> 设置查询某属性的值的总和，在getSingleResult调用之前使用 </p>
     *
     * @param attrName 属性名
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery sumAsDouble(String attrName) throws DaoException {
        // 属性列名非空校验
        checkAttrName(attrName);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##sumAsDouble(attrName) -->> AttrName = {}", attrName);
        }
        criteriaQuery.select(criteriaBuilder.sumAsDouble(root.get(attrName)));
        return this;
    }

    /**
     * <p> 去重某一列 </p>
     *
     * @param attrName 属性名
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery distinct(String attrName) throws DaoException {
        // 属性列名非空校验
        checkAttrName(attrName);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##distinct(attrName) -->> AttrName = {}", attrName);
        }
        criteriaQuery.select(root.get(attrName)).distinct(true);
        return this;
    }

    /**
     * <p> 添加order by子句 </p>
     *
     * @param attrName 属性名
     * @param orderBy  排序顺序
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery addOrderby(String attrName, String orderBy) throws DaoException {
        // 属性列名非空校验
        checkAttrName(attrName);

        if (CollectionUtils.isEmpty(orders)) {
            orders = new ArrayList<Order>();
        }
        if (orderBy.equalsIgnoreCase(DBConstants.SQLConstants.SQL_ORDER_ASC)) {
            orders.add(criteriaBuilder.asc(root.get(attrName)));
        } else if (orderBy.equalsIgnoreCase(DBConstants.SQLConstants.SQL_ORDER_DESC)) {
            orders.add(criteriaBuilder.desc(root.get(attrName)));
        } else {
            // 排序关键字【{0}】非法, 必须是【asc, ASC】 或【desc, DESC】
            throw new DaoException("ERROR-DB-DAO0000000007", orderBy);
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##addOrderby(attrName, orderBy) -->> AttrName = {}, OrderBy = {}", attrName, orderBy);
        }
        return this;
    }

    /**
     * <p> 添加group by子句 </p>
     *
     * @param attrName 属性名
     * @return Flea JPA查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public FleaJPAQuery addGroupBy(String attrName) throws DaoException {
        // 属性列名非空校验
        checkAttrName(attrName);

        if (CollectionUtils.isEmpty(groups)) {
            groups = new ArrayList<Expression>();
        }

        groups.add(root.get(attrName));

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FMJPAQuery##addGroupBy(attrName) -->> AttrName = {}, GroupBy = {}", attrName);
        }
        return this;
    }

    /**
     * <p> 获取查询的记录行结果集合 </p>
     *
     * @return 记录行结果集合
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public List getResultList() throws DaoException {
        try {
            return createQuery(false).getResultList();
        } finally {
            // 将Flea JPA查询对象重置，并归还给对象池
            close();
        }
    }

    /**
     * <p> 获取查询的记录行结果集合（设置查询范围，可用于分页） </p>
     *
     * @param start 开始查询记录行
     * @param max   最大查询数量
     * @return 记录行结果集合
     * @since 1.0.0
     */
    public List getResultList(int start, int max) throws DaoException {
        try {
            TypedQuery query = createQuery(false);
            // 设置开始查询记录行
            query.setFirstResult(start);
            // 设置一次最大查询数量
            query.setMaxResults(max);
            return query.getResultList();
        } finally {
            // 将Flea JPA查询对象重置，并归还给对象池
            close();
        }
    }

    /**
     * <p> 获取查询的单个属性列结果集合 </p>
     * <p> 需要先调用 distinct，否则默认返回行记录结果集合 </p>
     *
     * @return 单个属性列结果集合
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public List getSingleResultList() throws DaoException {
        try {
            return createQuery(true).getResultList();
        } finally {
            // 将Flea JPA查询对象重置，并归还给对象池
            close();
        }
    }

    /**
     * <p> 获取查询的单个属性列结果集合（设置查询范围，可用于分页） </p>
     * <p> 需要先调用 distinct，否则默认返回行记录结果集合 </p>
     *
     * @param start 开始查询记录行
     * @param max   最大查询数量
     * @return 单个属性列结果集合（
     * @since 1.0.0
     */
    public List getSingleResultList(int start, int max) throws DaoException {
        try {
            TypedQuery query = createQuery(true);
            // 设置开始查询记录行
            query.setFirstResult(start);
            // 设置一次最大查询数量
            query.setMaxResults(max);
            return query.getResultList();
        } finally {
            // 将Flea JPA查询对象重置，并归还给对象池
            close();
        }
    }

    /**
     * <p> 获取查询的单个结果 </p>
     * <p> select 提前调用 (count, countDistinct, max, min, avg, sum, sumAsLong, sumAsDouble) </p>
     *
     * @return 查询的单个结果
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    public Object getSingleResult() throws DaoException {
        try {
            return createQuery(true).getSingleResult();
        } finally {
            // 将Flea JPA查询对象重置，并归还给对象池
            close();
        }
    }

    /**
     * <p> 创建查询对象 </p>
     *
     * @return 查询对象
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    private TypedQuery createQuery(boolean isSingle) throws DaoException {
        if (ObjectUtils.isEmpty(sourceClazz)) {
            // 查询非法，实体类类对象为空
            throw new DaoException("ERROR-DB-DAO0000000008");
        }
        if (!isSingle) {
            criteriaQuery.select(root);
        }
        if (CollectionUtils.isNotEmpty(predicates)) {
            // 将所有条件用 and 联合起来
            criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        }
        if (CollectionUtils.isNotEmpty(orders)) {
            // 将order by 添加到查询语句中
            criteriaQuery.orderBy(orders);
        }
        if (CollectionUtils.isNotEmpty(groups)) {
            // 将group by 添加到查询语句中
            criteriaQuery.groupBy(groups);
        }
        return entityManager.createQuery(criteriaQuery);
    }

    /**
     * <p> 属性列名非空校验 </p>
     *
     * @param attrName 属性列名
     * @throws DaoException 数据操作层异常类
     * @since 1.0.0
     */
    private void checkAttrName(String attrName) throws DaoException {
        if (StringUtils.isBlank(attrName)) {
            // 属性列名不能为空
            throw new DaoException("ERROR-DB-DAO0000000001");
        }
    }

    /**
     * <p> 根据属性名，从查询实体类实例中获取对应值 </p>
     *
     * @param attrName 属性列名
     * @return 属性值
     * @throws DaoException
     * @since 1.0.0
     */
    private Object getAttrValue(String attrName) throws DaoException {
        checkAttrName(attrName);

        if (ObjectUtils.isEmpty(entity) || (ObjectUtils.isNotEmpty(sourceClazz) && !sourceClazz.isInstance(entity))) {
            return null;
        }

        return ReflectUtils.getObjectAttrValue(entity, attrName);
    }

    /**
     * <p> 根据属性名，从查询实体类中获取对应的数字值 </p>
     *
     * @param attrName 属性列名
     * @return 属性值
     * @throws DaoException
     * @since 1.0.0
     */
    private Number getNumberAttrValue(String attrName) throws DaoException {
        return NumberUtils.toNumber(getAttrValue(attrName));
    }

    /**
     * <p> 根据属性名，从查询实体类中获取对应的日期值 </p>
     *
     * @param attrName 属性列名
     * @return 属性值
     * @throws DaoException
     * @since 1.0.0
     */
    private Date getDateAttrValue(String attrName) throws DaoException {
        Object attrValue = getAttrValue(attrName);
        Date value = null;
        if (ObjectUtils.isNotEmpty(attrValue) && Date.class.isInstance(attrValue)) {
            value = (Date) attrValue;
        }
        return value;
    }

    /**
     * <p> 设置Flea对象池 </p>
     *
     * @param fleaObjectPool Flea JPA查询对象池
     * @since 1.0.0
     */
    public void setFleaObjectPool(FleaJPAQueryPool fleaObjectPool) {
        this.fleaObjectPool = fleaObjectPool;
    }

    /**
     * <p> 重置Flea JPA查询对象 </p>
     *
     * @since 1.0.0
     */
    public void reset() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaJPAQuery##reset() Start");
            LOGGER.debug("FleaJPAQuery##reset() Before FleaJPAQuery = {}", toString());
        }
        entityManager = null;
        sourceClazz = null;
        resultClazz = null;
        root = null;
        criteriaBuilder = null;
        criteriaQuery = null;
        if (CollectionUtils.isNotEmpty(predicates)) {
            predicates.clear();
        }
        orders = null;
        groups = null;
        entity = null;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaJPAQuery##reset() After FleaJPAQuery = {}", toString());
            LOGGER.debug("FleaJPAQuery##reset() End");
        }
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

    public List<Expression> getGroups() {
        return groups;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
