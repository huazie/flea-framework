package com.huazie.fleaframework.db.jpa.persistence;

import com.huazie.fleaframework.common.FleaApplicationContext;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.ArrayUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.db.common.exceptions.DaoException;
import com.huazie.fleaframework.db.common.table.pojo.SplitTable;
import com.huazie.fleaframework.db.common.util.EntityUtils;
import com.huazie.fleaframework.db.jpa.FleaJPASplitHelper;
import com.huazie.fleaframework.db.jpa.transaction.FleaTransactional;
import org.springframework.core.NamedThreadLocal;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.SharedEntityManagerCreator;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Flea 实体管理器工具类，提供了获取持久化上下文交互的实体管理器接口、
 * 持久化单元名、事物名、分表信息、各持久化上下文交互接口的静态方法。
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.1.0
 */
public class FleaEntityManager {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaEntityManager.class);

    private static final ConcurrentMap<String, EntityManager> entityManagerMap = new ConcurrentHashMap<>();

    private static final Object entityManagerMapLock = new Object();

    private static final ThreadLocal<Map<Object, Object>> resources = new NamedThreadLocal<>("EntityManager resources");

    private FleaEntityManager() {
    }

    /**
     * 获取指定场景下的实体管理类
     *
     * @param unitName        持久化单元名（通常对应着库名）
     * @param transactionName 事物名（对应配置的事物管理者）
     * @return 实体管理类
     * @throws CommonException 通用异常
     * @since 1.1.0
     */
    public static EntityManager getEntityManager(String unitName, String transactionName) throws CommonException {

        // 【{0}】不能为空
        StringUtils.checkBlank(unitName, DaoException.class, "ERROR-DB-DAO0000000002", "libName");

        // 【{0}】不能为空
        StringUtils.checkBlank(transactionName, DaoException.class, "ERROR-DB-DAO0000000002", "transactionName");

        if (!entityManagerMap.containsKey(unitName)) {
            synchronized (entityManagerMapLock) {
                if (!entityManagerMap.containsKey(unitName)) {
                    // 根据事物名，获取配置的事物管理者
                    JpaTransactionManager manger = (JpaTransactionManager) FleaApplicationContext.getBean(transactionName);
                    // 事物名【{0}】非法，请检查！
                    ObjectUtils.checkEmpty(manger, DaoException.class, "ERROR-DB-DAO0000000015", transactionName);
                    // 获取实体管理者工厂类
                    EntityManagerFactory entityManagerFactory = manger.getEntityManagerFactory();
                    // 创建实体管理者
                    EntityManager entityManager = SharedEntityManagerCreator.createSharedEntityManager(entityManagerFactory);
                    entityManagerMap.put(unitName, entityManager);
                }
            }
        }
        return entityManagerMap.get(unitName);
    }

    /**
     * 从指定类的成员变量上，获取持久化单元名称。在 <b> flea-db </b> 模块中，
     * 该名称一般定义在 {@code AbstractFleaJPADAOImpl} 的子类的成员变量上，由 注解
     * {@code PersistenceContext} 或 注解 {@code FleaPersistenceContext} 进行标识。
     *
     * @param daoImplClazz 抽象Flea JPA DAO层实现类
     * @return 持久化单元名称
     * @since 1.1.0
     */
    public static String getPersistenceUnitName(Class<?> daoImplClazz) {
        String unitName = "";
        // 获取持久化单元DAO层实现类的所有成员变量
        Field[] fields = daoImplClazz.getDeclaredFields();
        // 遍历成员变量
        if (ArrayUtils.isNotEmpty(fields)) {
            for (Field field : fields) {
                PersistenceContext persistenceContext = field.getAnnotation(PersistenceContext.class);
                if (ObjectUtils.isNotEmpty(persistenceContext)) {
                    unitName = persistenceContext.unitName();
                    break;
                }
                FleaPersistenceContext fleaPersistenceContext = field.getAnnotation(FleaPersistenceContext.class);
                if (ObjectUtils.isNotEmpty(fleaPersistenceContext)) {
                    unitName = fleaPersistenceContext.unitName();
                    break;
                }
            }
        }
        return unitName;
    }

    /**
     * 从指定类的第一个成员方法上，获取事物名。在 <b> flea-db </b> 模块中，
     * 该名称一般定义在 {@code AbstractFleaJPADAOImpl} 的子类的成员方法上，
     * 由注解 {@code Transactional}或{@code FleaTransactional} 进行标识。
     *
     * @param daoImplClazz 抽象Flea JPA DAO层实现类
     * @return 事物名
     * @since 1.1.0
     */
    public static String getTransactionName(Class<?> daoImplClazz) {
        String transactionName = "";
        // 获取持久化单元DAO层实现类的所有成员方法
        Method[] methods = daoImplClazz.getDeclaredMethods();
        if (ArrayUtils.isNotEmpty(methods)) {
            for (Method method : methods) {
                Transactional transactional = method.getAnnotation(Transactional.class);
                if (ObjectUtils.isNotEmpty(transactional)) {
                    transactionName = transactional.value();
                    break;
                }
                FleaTransactional fleaTransactional = method.getAnnotation(FleaTransactional.class);
                if (ObjectUtils.isNotEmpty(fleaTransactional)) {
                    transactionName = fleaTransactional.value();
                    break;
                }
            }
        }
        return transactionName;
    }

    /**
     * 从指定类的成员方法上，获取事物名。在 <b> flea-db </b> 模块中，
     * 该名称一般定义在 {@code AbstractFleaJPADAOImpl} 的子类的成员方法上，
     * 由注解 {@code Transactional}或{@code FleaTransactional} 进行标识。
     *
     * @param method 类的成员方法
     * @return 事物名
     * @since 1.2.0
     */
    public static String getTransactionName(Method method) {
        String transactionName = "";
        if (ObjectUtils.isNotEmpty(method)) {
            Transactional transactional = method.getAnnotation(Transactional.class);
            if (ObjectUtils.isNotEmpty(transactional)) {
                transactionName = transactional.value();
            }
            FleaTransactional fleaTransactional = method.getAnnotation(FleaTransactional.class);
            if (ObjectUtils.isNotEmpty(fleaTransactional)) {
                transactionName = fleaTransactional.value();
            }
        }
        return transactionName;
    }

    /**
     * 从指定类的成员方法上，获取持久化单元名。在 <b> flea-db </b> 模块中，
     * 该名称定义在注解{@code FleaTransactional} 中，用于启动自定的事物。
     *
     * @param method 类的成员方法
     * @return 持久化单元名
     * @since 2.0.0
     */
    public static String getUnitName(Method method) {
        String unitName = "";
        if (ObjectUtils.isNotEmpty(method)) {
            FleaTransactional fleaTransactional = method.getAnnotation(FleaTransactional.class);
            if (ObjectUtils.isNotEmpty(fleaTransactional)) {
                unitName = fleaTransactional.unitName();
            }
        }
        return unitName;
    }

    /**
     * 根据实体对象，获取实体对应的分表信息
     *
     * @param entity 实体对象
     * @return 分表信息
     * @throws CommonException 通用异常
     * @since 1.2.0
     */
    public static SplitTable getSplitTable(Object entity) throws CommonException {
        SplitTable splitTable = (SplitTable) getResource(entity);
        if (ObjectUtils.isEmpty(splitTable)) {
            splitTable = EntityUtils.getSplitTable(entity);
            bindResource(entity, splitTable);
        }
        return splitTable;
    }

    /**
     * 返回绑定到当前线程的所有资源
     *
     * @return 绑定到当前线程的所有资源
     * @since 1.2.0
     */
    public static Map<Object, Object> getResourceMap() {
        Map<Object, Object> map = resources.get();
        return (ObjectUtils.isNotEmpty(map) ? Collections.unmodifiableMap(map) : Collections.emptyMap());
    }

    /**
     * 检查是否存在绑定到当前线程的给定键的资源。
     *
     * @param key 要检查的键
     * @return true: 存在， false：不存在
     * @since 1.2.0
     */
    public static boolean hasResource(Object key) {
        Object value = doGetResource(key);
        return ObjectUtils.isNotEmpty(value);
    }

    /**
     * 检索绑定到当前线程的给定键的资源。
     *
     * @param key 要检查的键
     * @return 绑定到当前线程（通常是活动资源对象）的给定键的资源；如果没有，则为null。
     * @since 1.2.0
     */
    public static Object getResource(Object key) {
        Object value = doGetResource(key);
        if (ObjectUtils.isNotEmpty(value) && LOGGER.isTraceEnabled()) {
            LOGGER.trace1(new Object() {}, "Retrieved value [{}] for key [{}] bound to thread [{}]", value, key, Thread.currentThread().getName());
        }
        return value;
    }

    /**
     * 实际检查给定键绑定的资源的值。
     *
     * @param actualKey 实际的键值
     * @return 实际检查给定键绑定的资源的值。
     * @since 1.2.0
     */
    private static Object doGetResource(Object actualKey) {
        Map<Object, Object> map = resources.get();
        if (ObjectUtils.isEmpty(map)) {
            return null;
        }
        return map.get(actualKey);
    }

    /**
     * 将给定键的给定资源绑定到当前线程。
     *
     * @param key   将值绑定到的键
     * @param value 要绑定的值
     * @throws IllegalStateException 如果已经有一个绑定到线程的值
     * @since 1.2.0
     */
    public static void bindResource(Object key, Object value) throws IllegalStateException {
        Assert.notNull(value, "Value must not be null");
        Map<Object, Object> map = resources.get();

        if (ObjectUtils.isEmpty(map)) {
            map = new HashMap<>();
            resources.set(map);
        }
        Object oldValue = map.put(key, value);
        if (oldValue != null) {
            throw new IllegalStateException("Already value [" + oldValue + "] for key [" + key + "] bound to thread [" + Thread.currentThread().getName() + "]");
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace1(new Object() {}, "Bound value [{}] for key [{}] to thread [{}]", value, key, Thread.currentThread().getName());
        }
    }

    /**
     * 从当前线程解除给定键的资源绑定。
     *
     * @param key 解除绑定的键
     * @return 之前绑定的值
     * @throws IllegalStateException 如果没有绑定到线程的值
     * @since 1.2.0
     */
    public static Object unbindResource(Object key) throws IllegalStateException {
        Object value = doUnbindResource(key);
        if (ObjectUtils.isEmpty(value)) {
            throw new IllegalStateException("No value for key [" + key + "] bound to thread [" + Thread.currentThread().getName() + "]");
        }
        return value;
    }

    /**
     * 从当前线程解除给定键的资源绑定。
     *
     * @param key 解除绑定的键
     * @return 先前绑定的值，如果没有绑定，则为null
     * @since 1.2.0
     */
    public static Object unbindResourceIfPossible(Object key) {
        return doUnbindResource(key);
    }

    /**
     * 实际删除为给定键绑定的资源的值。
     *
     * @param actualKey 实际的键值
     * @return 先前绑定的值，如果没有绑定，则为null
     * @since 1.2.0
     */
    private static Object doUnbindResource(Object actualKey) {
        Map<Object, Object> map = resources.get();
        if (ObjectUtils.isEmpty(map)) {
            return null;
        }
        Object value = map.remove(actualKey);
        if (map.isEmpty()) {
            resources.remove();
        }
        if (value != null && LOGGER.isTraceEnabled()) {
            LOGGER.trace1(new Object() {}, "Removed value [{}] for key [{}] from thread [{}]", value, actualKey, Thread.currentThread().getName());
        }
        return value;
    }

    /**
     * 获取下一个主键值
     *
     * @param entityManager 实体类管理者
     * @param entityClass   实体类Class对象实例
     * @param entity        实体类对象
     * @param <T>           实体类型
     * @return 下一个主键值
     * @since 1.1.0
     */
    public static <T> Number getFleaNextValue(EntityManager entityManager, Class<T> entityClass, T entity) {
        return FleaJPASplitHelper.getHandler().getNextValue(entityManager, entityClass, entity);
    }

    /**
     * 根据主键查找表数据
     *
     * @param entityManager 实体管理类
     * @param primaryKey    主键
     * @param entity        实体类对象
     * @param <T>           实体类型
     * @return 实体数据
     * @since 1.0.0
     */
    public static <T> T find(EntityManager entityManager, Object primaryKey, Class<T> entityClass, T entity) {
        return FleaJPASplitHelper.getHandler().find(entityManager, primaryKey, entityClass, entity);
    }

    /**
     * 删除实体类对应的一条数据
     *
     * @param entityManager 实体管理类
     * @param entity        实体类对象
     * @param <T>           实体类型
     * @since 1.0.0
     */
    public static <T> boolean remove(EntityManager entityManager, T entity) {
        return FleaJPASplitHelper.getHandler().remove(entityManager, entity);
    }

    /**
     * 将给定实体的状态合并（即更新）到当前持久化上下文中。
     * <p> 注意：调用该方法后，待修改的数据还未更新到数据库中。
     *
     * @param entityManager 实体管理类
     * @param entity        实体类对象
     * @param <T>           实体类型
     * @return 已合并（更新）状态的托管实体对象
     */
    public static <T> T merge(EntityManager entityManager, T entity) {
        return FleaJPASplitHelper.getHandler().merge(entityManager, entity);
    }

    /**
     * 将实体类添加到持久化上下文中，并管理该实体类
     * <p> 注意：调用该方法后，待保存的数据还未添加到数据库中。
     *
     * @param entityManager 实体管理类
     * @param entity        实体类对象
     * @param <T>           实体类型
     * @since 1.0.0
     */
    public static <T> void persist(EntityManager entityManager, T entity) {
        FleaJPASplitHelper.getHandler().persist(entityManager, entity);
    }

    /**
     * 将持久化上下文同步到底层数据库。
     *
     * @param entityManager 实体管理类
     * @param entity        实体类对象
     * @param <T>           实体类型
     * @since 2.0.0
     */
    public static <T> void flush(EntityManager entityManager, T entity) {
        FleaJPASplitHelper.getHandler().flush(entityManager, entity);
    }
}
