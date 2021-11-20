package com.huazie.frame.db.jpa.transaction;

import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.db.jpa.LibTableSplitHelper;
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
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * Flea事物模板，简化程序化事务划分和事务异常处理的模板类。
 * <p>核心方法是{@link #execute}, 支持实现 {@link TransactionCallback}
 * 接口的事务代码。此模板处理事务生命周期和可能的异常，因此
 * {@link TransactionCallback} 实现和调用代码都不需要显式处理事务。
 *
 * @author huazie
 * @version 1.2.0
 * @since 1.2.0
 */
public class FleaTransactionTemplate extends DefaultTransactionDefinition implements TransactionOperations, InitializingBean {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaTransactionTemplate.class);

    private PlatformTransactionManager transactionManager;

    private EntityManager entityManager;

    /**
     * Construct a new TransactionTemplate for bean usage.
     * <p>Note: The PlatformTransactionManager needs to be set before
     * any {@code execute} calls.
     *
     * @see #setTransactionManager
     */
    public FleaTransactionTemplate() {
    }

    /**
     * Construct a new TransactionTemplate using the given transaction manager.
     *
     * @param transactionManager the transaction management strategy to be used
     */
    public FleaTransactionTemplate(PlatformTransactionManager transactionManager, EntityManager entityManager) {
        this.transactionManager = transactionManager;
        this.entityManager = entityManager;
    }

    /**
     * Construct a new TransactionTemplate using the given transaction manager,
     * taking its default settings from the given transaction definition.
     *
     * @param transactionManager    the transaction management strategy to be used
     * @param transactionDefinition the transaction definition to copy the
     *                              default settings from. Local properties can still be set to change values.
     */
    public FleaTransactionTemplate(PlatformTransactionManager transactionManager, EntityManager entityManager, TransactionDefinition transactionDefinition) {
        super(transactionDefinition);
        this.entityManager = entityManager;
        this.transactionManager = transactionManager;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public PlatformTransactionManager getTransactionManager() {
        return this.transactionManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void afterPropertiesSet() {
        if (this.transactionManager == null) {
            throw new IllegalArgumentException("Property 'transactionManager' is required");
        }
    }

    @Override
    public <T> T execute(TransactionCallback<T> action) throws TransactionException {
        if (this.transactionManager instanceof CallbackPreferringPlatformTransactionManager) {
            return ((CallbackPreferringPlatformTransactionManager) this.transactionManager).execute(this, action);
        } else {
            TransactionStatus status = this.transactionManager.getTransaction(this);
            // 分表场景下，处理事物代码，以支持JPA的增删改操作
            status = LibTableSplitHelper.findTableSplitHandle().handle(status, this, transactionManager, entityManager);
            T result;
            try {
                result = action.doInTransaction(status);
            } catch (RuntimeException ex) {
                // Transactional code threw application exception -> rollback
                rollbackOnException(status, ex);
                throw ex;
            } catch (Error err) {
                // Transactional code threw error -> rollback
                rollbackOnException(status, err);
                throw err;
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
     * Perform a rollback, handling rollback exceptions properly.
     *
     * @param status object representing the transaction
     * @param ex     the thrown application exception or error
     * @throws TransactionException in case of a rollback error
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
        return (this == other || (super.equals(other) && (!(other instanceof TransactionTemplate) ||
                getTransactionManager() == ((TransactionTemplate) other).getTransactionManager())));
    }

}