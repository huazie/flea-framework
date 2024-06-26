package com.huazie.fleaframework.db.jpa.handler;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.db.jpa.common.FleaJPAQuery;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Flea JPA 分库分表处理者接口，包含分库分表相关的处理接口方法、
 * 增删改查的数据操作接口方法。
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public interface IFleaJPASplitHandler {

    /**
     * 使用标准化查询时，处理分库分表信息
     *
     * @param query  Flea JPA 查询对象
     * @param entity 实体类对象实例
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    void handle(FleaJPAQuery query, Object entity) throws CommonException;

    /**
     * 使用标准化查询时，存在分表场景，具体的JPA查询对象重新设置持久化信息
     *
     * @param query      Flea JPA 查询对象
     * @param typedQuery 具体的JPA查询对象，包装了一个DatabaseQuery
     * @throws CommonException 通用异常
     * @since 1.1.0
     */
    void handle(FleaJPAQuery query, TypedQuery typedQuery) throws CommonException;

    /**
     * 使用持久化接口时，处理分库分表信息
     *
     * @param entityManager 持久化接口对象实例
     * @param entity        实体类对象实例
     * @param flag          获取实体管理器标识【true：getFleaNextValue获取实体管理器， false: 其他场景获取实体管理器】
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    EntityManager handle(EntityManager entityManager, Object entity, boolean flag) throws CommonException;

    /**
     * 分表场景下，取事务管理器中的实体管理器工厂类，并将其作为键，
     * 绑定实体管理器对应的包装类资源到当前线程。以支持JPA的增删改操作。
     *
     * @param definition         事务属性
     * @param transactionManager 事务管理器
     * @param entityManager      持久化接口对象实例
     * @since 1.2.0
     */
    TransactionStatus getTransaction(TransactionDefinition definition, PlatformTransactionManager transactionManager, EntityManager entityManager);

    /**
     * 获取下一个主键值
     *
     * @param entityManager 持久化接口对象实例
     * @param entityClass   实体类Class对象实例
     * @param entity        实体类对象
     * @return 下一个主键值
     * @since 1.0.0
     */
    <T> Number getNextValue(EntityManager entityManager, Class<T> entityClass, T entity);

    /**
     * 根据主键查找表数据
     *
     * @param entityManager 实体管理类
     * @param primaryKey    主键
     * @param entity        实体类对象
     * @param <T>           实体类型
     * @return 实体数据
     * @since 1.2.0
     */
    <T> T find(EntityManager entityManager, Object primaryKey, Class<T> entityClass, T entity);

    /**
     * 删除给定的实体数据
     *
     * @param entityManager 实体管理类
     * @param entity        实体类对象
     * @param <T>           实体类型
     * @return true： 删除成功，false：删除失败
     * @since 1.2.0
     */
    <T> boolean remove(EntityManager entityManager, T entity);

    /**
     * 将给定实体的状态合并（即更新）到当前持久化上下文中。
     * <p> 注意：调用该方法后，待修改的数据还未更新到数据库中。
     *
     * @param entityManager 实体管理类
     * @param entity        实体类对象
     * @param <T>           实体类型
     * @return 已合并（更新）状态的托管实体对象
     * @since 1.2.0
     */
    <T> T merge(EntityManager entityManager, T entity);

    /**
     * 将实体类添加到持久化上下文中，并管理该实体类
     * <p> 注意：调用该方法后，待保存的数据还未添加到数据库中。
     *
     * @param entityManager 实体管理类
     * @param entity        实体类对象
     * @param <T>           实体类型
     * @since 1.2.0
     */
    <T> void persist(EntityManager entityManager, T entity);

    /**
     * 将持久化上下文同步到底层数据库。
     *
     * @param entityManager 实体管理器
     * @param entity        实体类对象
     * @param <T>           实体类型
     * @since 2.0.0
     */
    <T> void flush(EntityManager entityManager, T entity);
}
