package com.huazie.fleaframework.db.eclipselink;

import com.huazie.fleaframework.common.FleaEntity;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.db.common.DBConstants;
import com.huazie.fleaframework.db.common.table.pojo.SplitTable;
import com.huazie.fleaframework.db.eclipselink.util.EclipseLinkUtils;
import com.huazie.fleaframework.db.jpa.common.FleaJPAQuery;
import com.huazie.fleaframework.db.jpa.handler.impl.FleaLibTableSplitHandler;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.internal.jpa.FleaEntityManagerImpl;
import org.eclipse.persistence.internal.jpa.metamodel.EntityTypeImpl;
import org.eclipse.persistence.internal.sessions.AbstractSession;
import org.eclipse.persistence.queries.ReadAllQuery;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.metamodel.EntityType;

/**
 * EclipseLink 分库分表处理者实现类
 *
 * @author huazie
 * @version 1.2.0
 * @since 1.0.0
 */
public class EclipseLinkLibTableSplitHandler extends FleaLibTableSplitHandler {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(EclipseLinkLibTableSplitHandler.class);

    @Override
    public void handle(FleaJPAQuery query, TypedQuery typedQuery) {
        Object entity = query.getEntity();
        if (ObjectUtils.isEmpty(entity) || !(entity instanceof FleaEntity)) {
            return;
        }
        FleaEntity fleaEntity = (FleaEntity) entity;
        SplitTable splitTable = fleaEntity.get(DBConstants.LibTableSplitConstants.SPLIT_TABLE, SplitTable.class);
        // 分表信息为空或不存在分表，默认不处理
        if (ObjectUtils.isEmpty(splitTable) || !splitTable.isExistSplitTable()) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error1(new Object() {}, "分表信息为空或不存在分表，本次不处理【FleaJPAQuery】！");
            }
            return;
        }
        // 获取实体类型
        EntityType entityType = query.getRoot().getModel();
        // 获取实体类对应的持久化信息
        ClassDescriptor classDescriptor = ((EntityTypeImpl) entityType).getDescriptor();
        // 分表场景，这里的entityManager已经重新设置为 FleaEntityManagerImpl
        AbstractSession abstractSession = ((FleaEntityManagerImpl) query.getEntityManager()).getDatabaseSession();
        classDescriptor = EclipseLinkUtils.getSplitDescriptor(classDescriptor, abstractSession, splitTable);
        // 获取内部DatabaseQuery对象
        ReadAllQuery readAllQuery = typedQuery.unwrap(ReadAllQuery.class);
        // 重新设置实体类的描述符信息
        readAllQuery.setDescriptor(classDescriptor);
        // 重新设置实体类的描述符信息
        readAllQuery.getExpressionBuilder().setQueryClassAndDescriptor(classDescriptor.getJavaClass(), classDescriptor);
    }

    @Override
    protected EntityManager getFleaEntityMangerImpl(EntityManager entityManager) {
        return FleaEntityManagerImpl.getFleaEntityManagerImpl(entityManager);
    }

    @Override
    public TransactionStatus getTransaction(TransactionDefinition definition, PlatformTransactionManager transactionManager, EntityManager entityManager) {
        if (ObjectUtils.isEmpty(entityManager)) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error1(new Object() {}, "分表信息为空或不存在分表，本次不处理【TransactionStatus】！");
            }
            return transactionManager.getTransaction(definition);
        }
        // 获取Flea实体管理器实现类
        FleaEntityManagerImpl fleaEntityManagerImpl = FleaEntityManagerImpl.getFleaEntityManagerImpl(entityManager);
        // 新建实体管理器包装类资源，持有Flea实体管理器实现类
        EntityManagerHolder entityManagerHolder = new EntityManagerHolder(fleaEntityManagerImpl);
        // JPA事物管理器
        JpaTransactionManager jpaTransactionManager = (JpaTransactionManager) transactionManager;
        Object obj = TransactionSynchronizationManager.getResource(jpaTransactionManager.getEntityManagerFactory());
        if (ObjectUtils.isNotEmpty(obj)) {
            // 从当前线程解除给定实体管理器工厂类的实体管理器包装类资源绑定。
            TransactionSynchronizationManager.unbindResource(jpaTransactionManager.getEntityManagerFactory());
        }
        // 将实体管理器工厂类的实体管理器包装类资源绑定到当前线程
        TransactionSynchronizationManager.bindResource(jpaTransactionManager.getEntityManagerFactory(), entityManagerHolder);
        // 重新获取事物状态对象，并开启事物
        return jpaTransactionManager.getTransaction(definition);
    }

    @Override
    public <T> Number getNextValue(EntityManager entityManager, Class<T> entityClass, T entity) {
        SplitTable splitTable = null;
        if (ObjectUtils.isNotEmpty(entity) && entity instanceof FleaEntity) {
            // 获取分表名
            FleaEntity fleaEntity = (FleaEntity) entity;
            splitTable = fleaEntity.get(DBConstants.LibTableSplitConstants.SPLIT_TABLE, SplitTable.class);
        }

        FleaEntityManagerImpl fleaEntityManagerImpl = FleaEntityManagerImpl.getFleaEntityManagerImpl(entityManager);
        return fleaEntityManagerImpl.getNextValue(entityClass, splitTable);
    }

    @Override
    public <T> T find(EntityManager entityManager, Object primaryKey, Class<T> entityClass, T entity) {
        if (ObjectUtils.isEmpty(entity) || !(entity instanceof FleaEntity)) {
            return null;
        }

        // 获取分表名
        FleaEntity fleaEntity = (FleaEntity) entity;
        SplitTable splitTable = fleaEntity.get(DBConstants.LibTableSplitConstants.SPLIT_TABLE, SplitTable.class);
        // 分表信息为空或不存在分表，默认不处理
        if (ObjectUtils.isEmpty(splitTable) || !splitTable.isExistSplitTable()) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error1(new Object() {}, "分表信息为空或不存在分表，本次不执行【find】！");
            }
            return null;
        }

        FleaEntityManagerImpl fleaEntityManagerImpl = FleaEntityManagerImpl.getFleaEntityManagerImpl(entityManager);
        return fleaEntityManagerImpl.find(entityClass, primaryKey, splitTable);
    }

    @Override
    public <T> boolean remove(EntityManager entityManager, T entity) {
        if (ObjectUtils.isEmpty(entity) || !(entity instanceof FleaEntity)) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error1(new Object() {}, "待删除实体对象为空或不是FleaEntity的子类，本次不执行【remove】！");
            }
            return false;
        }

        // 获取分表名
        FleaEntity fleaEntity = (FleaEntity) entity;
        SplitTable splitTable = fleaEntity.get(DBConstants.LibTableSplitConstants.SPLIT_TABLE, SplitTable.class);
        // 分表场景
        if (ObjectUtils.isNotEmpty(splitTable) && splitTable.isExistSplitTable()) {
            // 删除实体数据
            FleaEntityManagerImpl.getFleaEntityManagerImpl(entityManager).remove(entity);
        } else {
            entityManager.remove(entity);
        }
        return true;
    }

    @Override
    public <T> T merge(EntityManager entityManager, T entity) {
        if (ObjectUtils.isEmpty(entity) || !(entity instanceof FleaEntity)) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error1(new Object() {}, "待删除实体对象为空或不是FleaEntity的子类，本次不执行【merge】！");
            }
            return null;
        }

        // 获取分表名
        FleaEntity fleaEntity = (FleaEntity) entity;
        SplitTable splitTable = fleaEntity.get(DBConstants.LibTableSplitConstants.SPLIT_TABLE, SplitTable.class);
        // 分表场景
        if (ObjectUtils.isNotEmpty(splitTable) && splitTable.isExistSplitTable()) {
            // 合并实体数据的状态至当前持久化上下文中
            return FleaEntityManagerImpl.getFleaEntityManagerImpl(entityManager).merge(entity);
        } else {
            return entityManager.merge(entity);
        }
    }

    @Override
    public <T> void persist(EntityManager entityManager, T entity) {
        if (ObjectUtils.isEmpty(entity) || !(entity instanceof FleaEntity)) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error1(new Object() {}, "待删除实体对象为空或不是FleaEntity的子类，本次不执行【persist】！");
            }
            return;
        }

        // 获取分表名
        FleaEntity fleaEntity = (FleaEntity) entity;
        SplitTable splitTable = fleaEntity.get(DBConstants.LibTableSplitConstants.SPLIT_TABLE, SplitTable.class);
        // 分表场景
        if (ObjectUtils.isNotEmpty(splitTable) && splitTable.isExistSplitTable()) {
            // 向工作单元注册对象
            FleaEntityManagerImpl.getFleaEntityManagerImpl(entityManager).persist(entity);
        } else {
            entityManager.persist(entity);
        }
    }

}
