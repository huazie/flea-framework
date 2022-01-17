package com.huazie.fleaframework.db.jpa.transaction;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.db.jpa.FleaJPASplitHelper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.support.CallbackPreferringPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionOperations;

import javax.persistence.EntityManager;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * Flea事物模板，简化程序化事务划分和事务异常处理的模板类。
 * <p>核心方法是{@link #execute}, 支持实现 {@link TransactionCallback}
 * 接口的事务代码。此模板处理事务生命周期和可能的异常，因此
 * {@link TransactionCallback} 实现和调用代码都不需要显式处理事务。
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.2.0
 */
@SuppressWarnings("serial")
public class FleaTransactionTemplate extends DefaultTransactionDefinition implements TransactionOperations, InitializingBean {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaTransactionTemplate.class);

    private PlatformTransactionManager transactionManager;

    private EntityManager entityManager;

    /**
     * 构造一个新的 FleaTransactionTemplate；
     * <p> 注意：PlatformTransactionManager 需要在任何
     * {@code execute} 调用之前设置。
     *
     * @see #setTransactionManager
     * @since 1.2.0
     */
    public FleaTransactionTemplate() {
    }

    /**
     * 使用给定的事务管理器构造一个新的 FleaTransactionTemplate。
     *
     * @param transactionManager 事物管理器
     * @param entityManager      实体管理器
     * @since 1.2.0
     */
    public FleaTransactionTemplate(PlatformTransactionManager transactionManager, EntityManager entityManager) {
        this.transactionManager = transactionManager;
        this.entityManager = entityManager;
    }

    /**
     * 使用给定的事务管理器构造一个新的 TransactionTemplate，
     * 从给定的事务定义中获取其默认设置。
     *
     * @param transactionManager    事物管理器
     * @param entityManager         实体管理器
     * @param transactionDefinition 事务定义
     * @since 1.2.0
     */
    public FleaTransactionTemplate(PlatformTransactionManager transactionManager, EntityManager entityManager, TransactionDefinition transactionDefinition) {
        super(transactionDefinition);
        this.entityManager = entityManager;
        this.transactionManager = transactionManager;
    }

    /**
     * Flea事物模板设置事物管理器
     *
     * @param transactionManager 待设置的事物管理器
     * @since 1.2.0
     */
    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    /**
     * 获取事物管理器
     *
     * @return 事物管理器
     * @since 1.2.0
     */
    public PlatformTransactionManager getTransactionManager() {
        return this.transactionManager;
    }

    /**
     * Flea事物模板设置实体管理器
     *
     * @param entityManager 待设置的实体管理器
     * @since 1.2.0
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * 获取实体管理器
     *
     * @return 实体管理器
     * @since 1.2.0
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void afterPropertiesSet() {
        if (ObjectUtils.isEmpty(this.transactionManager)) {
            throw new IllegalArgumentException("Property 'transactionManager' is required");
        }
    }

    @Override
    public <T> T execute(TransactionCallback<T> action) throws TransactionException {
        if (this.transactionManager instanceof CallbackPreferringPlatformTransactionManager) {
            return ((CallbackPreferringPlatformTransactionManager) this.transactionManager).execute(this, action);
        } else {
            // 开启Flea自定义事物
            TransactionStatus status = FleaJPASplitHelper.getHandler().getTransaction(this, transactionManager, entityManager);
            T result;
            try {
                result = action.doInTransaction(status);
            } catch (RuntimeException | Error ex) {
                // Transactional code threw application exception -> rollback
                // Transactional code threw error -> rollback
                rollbackOnException(status, ex);
                throw ex;
            } catch (Throwable ex) {
                // Transactional code threw unexpected exception -> rollback
                rollbackOnException(status, ex);
                throw new UndeclaredThrowableException(ex, "TransactionCallback threw undeclared checked exception");
            }
            this.transactionManager.commit(status);
            return result;
        }
    }

    /**
     * 执行回滚，正确处理回滚异常。
     *
     * @param status 事物对象
     * @param ex     抛出的应用程序异常或错误
     * @throws TransactionException 发生回滚错误时
     * @since 1.2.0
     */
    private void rollbackOnException(TransactionStatus status, Throwable ex) throws TransactionException {
        LOGGER.debug("Initiating transaction rollback on application exception", ex);
        try {
            this.transactionManager.rollback(status);
        } catch (TransactionSystemException ex2) {
            LOGGER.error("Application exception overridden by rollback exception", ex);
            ex2.initApplicationException(ex);
            throw ex2;
        } catch (RuntimeException ex2) {
            LOGGER.error("Application exception overridden by rollback exception", ex);
            throw ex2;
        } catch (Error err) {
            LOGGER.error("Application exception overridden by rollback error", ex);
            throw err;
        }
    }

    @Override
    public boolean equals(Object other) {
        return (this == other || (super.equals(other) && (!(other instanceof FleaTransactionTemplate) ||
                getTransactionManager() == ((FleaTransactionTemplate) other).getTransactionManager())));
    }

}