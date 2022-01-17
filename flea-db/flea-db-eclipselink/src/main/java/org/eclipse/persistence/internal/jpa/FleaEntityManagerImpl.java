package org.eclipse.persistence.internal.jpa;

import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.db.common.table.pojo.SplitTable;
import com.huazie.fleaframework.db.eclipselink.util.EclipseLinkUtils;
import com.huazie.fleaframework.db.jpa.persistence.FleaEntityManager;
import org.eclipse.persistence.config.EntityManagerProperties;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.internal.localization.ExceptionLocalization;
import org.eclipse.persistence.internal.sequencing.FleaSequencingManager;
import org.eclipse.persistence.internal.sequencing.Sequencing;
import org.eclipse.persistence.internal.sessions.AbstractSession;
import org.eclipse.persistence.internal.sessions.CommitManager;
import org.eclipse.persistence.internal.sessions.FleaCommitManager;
import org.eclipse.persistence.internal.sessions.FleaUnitOfWork;
import org.eclipse.persistence.internal.sessions.RepeatableWriteUnitOfWork;
import org.eclipse.persistence.logging.SessionLog;
import org.eclipse.persistence.sessions.broker.SessionBroker;
import org.eclipse.persistence.sessions.server.ServerSession;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.LockTimeoutException;
import java.util.Map;

/**
 * Flea 实体管理器 EclipseLink 版实现
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.2.0
 */
public final class FleaEntityManagerImpl extends EntityManagerImpl {

    /**
     * 获取指定JPA实体管理器工厂类对应的自定义的Flea实体管理器实现
     *
     * @param entityManager 实体管理器
     * @return Flea实体管理器
     * @since 1.2.0
     */
    public static FleaEntityManagerImpl getFleaEntityManagerImpl(EntityManager entityManager) {
        EntityManagerFactory entityManagerFactory = entityManager.getEntityManagerFactory();
        FleaEntityManagerImpl fleaEntityManagerImpl = (FleaEntityManagerImpl) FleaEntityManager.getResource(entityManagerFactory);
        if (ObjectUtils.isEmpty(fleaEntityManagerImpl)) {
            fleaEntityManagerImpl = new FleaEntityManagerImpl(entityManager);
            FleaEntityManager.bindResource(entityManagerFactory, fleaEntityManagerImpl);
        }
        return fleaEntityManagerImpl;
    }

    /**
     * 通过EntityManagerImpl构建FleaEntityManagerImpl
     *
     * @param entityManager 实体管理器
     * @since 1.2.0
     */
    private FleaEntityManagerImpl(EntityManager entityManager) {
        super(entityManager.getEntityManagerFactory().unwrap(EntityManagerFactoryImpl.class).unwrap(), entityManager.getProperties(), null);
    }

    /**
     * 分表场景下，根据主键查找实体数据
     *
     * @param entityClass 实体类Class类型
     * @param primaryKey  主键值
     * @param splitTable  分表信息
     * @param <T>         实体类型
     * @return 实体数据
     * @since 1.2.0
     */
    public <T> T find(Class<T> entityClass, Object primaryKey, SplitTable splitTable) {
        return find(entityClass, primaryKey, null, getQueryHints(entityClass, OperationType.FIND), splitTable);
    }

    /**
     * 分表场景下，根据主键查找实体数据
     *
     * @param entityClass 实体类Class类型
     * @param primaryKey  主键值
     * @param properties  属性信息
     * @param splitTable  分表信息
     * @param <T>         实体类型
     * @return 实体数据
     * @since 1.2.0
     */
    public <T> T find(Class<T> entityClass, Object primaryKey, Map<String, Object> properties, SplitTable splitTable) {
        return find(entityClass, primaryKey, null, properties, splitTable);
    }

    /**
     * 分表场景下，根据主键查找实体数据
     *
     * @param entityClass 实体类Class类型
     * @param primaryKey  主键值
     * @param lockMode    加锁类型
     * @param splitTable  分表信息
     * @param <T>         实体类型
     * @return 实体数据
     * @since 1.2.0
     */
    public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode, SplitTable splitTable) {
        return find(entityClass, primaryKey, lockMode, getQueryHints(entityClass, OperationType.FIND), splitTable);
    }

    /**
     * 分表场景下，根据主键查找实体数据
     *
     * @param entityClass 实体类Class类型
     * @param primaryKey  主键值
     * @param lockMode    加锁类型
     * @param properties  属性信息
     * @param splitTable  分表信息
     * @param <T>         实体类型
     * @return 实体数据
     * @since 1.2.0
     */
    @SuppressWarnings({"unchecked"})
    public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode, Map<String, Object> properties, SplitTable splitTable) {
        try {
            verifyOpen();
            if (ObjectUtils.isNotEmpty(lockMode) && !lockMode.equals(LockModeType.NONE)) {
                checkForTransaction(true);
            }
            AbstractSession session = this.databaseSession;
            ClassDescriptor descriptor = session.getDescriptor(entityClass);
            if (descriptor == null || descriptor.isDescriptorTypeAggregate()) {
                throw new IllegalArgumentException(ExceptionLocalization.buildMessage("unknown_bean_class", new Object[]{entityClass}));
            }
            if (!descriptor.shouldBeReadOnly() || !descriptor.isSharedIsolation()) {
                session = (AbstractSession) getActiveSession();
            } else {
                session = (AbstractSession) getReadOnlySession();
            }
            // 确保从当前会话中获取实体类的持久化信息描述符
            if (descriptor.hasTablePerMultitenantPolicy()) {
                descriptor = session.getDescriptor(entityClass);
            }
            // 获取分表对应的实体类的持久化信息描述符
            descriptor = EclipseLinkUtils.getSplitDescriptor(descriptor, session, splitTable);
            // 复用实体管理器实现类的内部方法
            return (T) findInternal(descriptor, session, primaryKey, lockMode, properties);
        } catch (LockTimeoutException e) {
            throw e;
        } catch (RuntimeException e) {
            setRollbackOnly();
            throw e;
        }
    }

    /**
     * 获取指定实体类对应的下一个主键值
     *
     * @param entityClass 实体类Class类型
     * @param splitTable  分表信息
     * @param <T>         实体类型
     * @return 指定实体类对应的下一个主键值
     * @since 1.2.0
     */
    public <T> Number getNextValue(Class<T> entityClass, SplitTable splitTable) {
        // 获取实体类的持久化信息描述符
        AbstractSession session = this.databaseSession;
        ClassDescriptor descriptor = session.getDescriptor(entityClass);
        if (ObjectUtils.isEmpty(descriptor)) {
            throw new IllegalArgumentException(ExceptionLocalization.buildMessage("unknown_bean_class", new Object[]{entityClass}));
        }
        // 获取分表对应的实体类的持久化信息描述符
        descriptor = EclipseLinkUtils.getSplitDescriptor(descriptor, session, splitTable);

        Number nextValue;
        if (ObjectUtils.isNotEmpty(splitTable) && splitTable.isExistSplitTablePkColumn()) {
            String sequenceName = splitTable.getSplitTablePkColumnValue();
            Sequencing sequencing = session.getSequencing();
            FleaSequencingManager fleaSequencingManager = FleaSequencingManager.getFleaSequencingManager(sequenceName, sequencing, descriptor);
            nextValue = fleaSequencingManager.getNextValue(sequenceName);
        } else {
            nextValue = session.getNextSequenceNumberValue(entityClass);
        }
        return nextValue;
    }

    @Override
    public RepeatableWriteUnitOfWork getActivePersistenceContext(Object txn) {
        // use local uow as it will be local to this EM and not on the txn
        if (this.extendedPersistenceContext == null || !this.extendedPersistenceContext.isActive()) {
            AbstractSession client;
            if(this.databaseSession.isServerSession()) {
                createConnectionPolicy();
                client = ((ServerSession)this.databaseSession).acquireClientSession(this.connectionPolicy, this.properties);
            } else if(this.databaseSession.isBroker()) {
                Map mapOfProperties = null;
                if (properties != null) {
                    mapOfProperties = (Map)this.properties.get(EntityManagerProperties.COMPOSITE_UNIT_PROPERTIES);
                }
                createConnectionPolicies(mapOfProperties);
                client = ((SessionBroker)this.databaseSession).acquireClientSessionBroker(this.connectionPolicies, mapOfProperties);
            } else {
                // currently this can't happen - the databaseSession is either ServerSession or SessionBroker.
                client = this.databaseSession;
            }
            this.extendedPersistenceContext = new FleaUnitOfWork(client, this.referenceMode);
            this.extendedPersistenceContext.setResumeUnitOfWorkOnTransactionCompletion(!this.closeOnCommit);
            this.extendedPersistenceContext.setShouldDiscoverNewObjects(this.persistOnCommit);
            this.extendedPersistenceContext.setDiscoverUnregisteredNewObjectsWithoutPersist(this.commitWithoutPersistRules);
            this.extendedPersistenceContext.setFlushClearCache(this.flushClearCache);
            this.extendedPersistenceContext.setShouldValidateExistence(this.shouldValidateExistence);
            this.extendedPersistenceContext.setShouldOrderUpdates(this.shouldOrderUpdates);
            this.extendedPersistenceContext.setShouldCascadeCloneToJoinedRelationship(true);
            this.extendedPersistenceContext.setShouldStoreByPassCache(this.cacheStoreBypass);
            // 设置CommitManager
            CommitManager commitManager = new FleaCommitManager(this.extendedPersistenceContext);
            commitManager.initializeCommitOrder();
            this.extendedPersistenceContext.setCommitManager(commitManager);
            if (txn != null) {
                // if there is a txn, it means we have been marked to join with it.
                // All that is left is to register the UOW with the transaction
                transaction.registerIfRequired(this.extendedPersistenceContext);
            }
            if (client.shouldLog(SessionLog.FINER, SessionLog.TRANSACTION)) {
                client.log(SessionLog.FINER, SessionLog.TRANSACTION, "acquire_unit_of_work_with_argument", String.valueOf(System.identityHashCode(this.extendedPersistenceContext)));
            }
        }
        if (this.beginEarlyTransaction && txn != null && !this.extendedPersistenceContext.isInTransaction()) {
            // gf3334, force persistence context early transaction
            this.extendedPersistenceContext.beginEarlyTransaction();
        }
        return this.extendedPersistenceContext;
    }
}
