package com.huazie.fleaframework.db.eclipselink;

import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.db.common.table.pojo.SplitTable;
import com.huazie.fleaframework.db.eclipselink.util.EclipseLinkUtils;
import com.huazie.fleaframework.db.jpa.common.FleaJPAQuery;
import com.huazie.fleaframework.db.jpa.handler.impl.FleaLibTableSplitHandler;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.internal.jpa.FleaEntityManagerImpl;
import org.eclipse.persistence.internal.jpa.metamodel.EntityTypeImpl;
import org.eclipse.persistence.internal.sessions.AbstractSession;
import org.eclipse.persistence.queries.ReadAllQuery;
import org.eclipse.persistence.sessions.UnitOfWork;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.metamodel.EntityType;

/**
 * EclipseLink 分库分表处理者，由自定义的实体管理器实现类处理增删改查等操作。
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public class EclipseLinkLibTableSplitHandler extends FleaLibTableSplitHandler {

    @Override
    protected void handleInner(FleaJPAQuery query, TypedQuery typedQuery, SplitTable splitTable) {
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
    protected <T> Number getNextValueInner(EntityManager entityManager, Class<T> entityClass, SplitTable splitTable) {
        return FleaEntityManagerImpl.getFleaEntityManagerImpl(entityManager).getNextValue(entityClass, splitTable);
    }

    @Override
    protected <T> T findInner(EntityManager entityManager, Object primaryKey, Class<T> entityClass, SplitTable splitTable) {
        return FleaEntityManagerImpl.getFleaEntityManagerImpl(entityManager).find(entityClass, primaryKey, splitTable);
    }

    @Override
    protected <T> void removeInner(EntityManager entityManager, T entity) {
        FleaEntityManagerImpl.getFleaEntityManagerImpl(entityManager).remove(entity);
    }

    @Override
    protected <T> T mergeInner(EntityManager entityManager, T entity) {
        return FleaEntityManagerImpl.getFleaEntityManagerImpl(entityManager).merge(entity);
    }

    @Override
    protected <T> void persistInner(EntityManager entityManager, T entity) {
        FleaEntityManagerImpl.getFleaEntityManagerImpl(entityManager).persist(entity);
    }

    @Override
    protected void flushInner(EntityManager entityManager) {
        FleaEntityManagerImpl.getFleaEntityManagerImpl(entityManager).flush();
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <T> T registerObject(EntityManager entityManager, T entity) {
        // 如果已经注册过了，直接返回待注册对象
        if (entityManager.contains(entity) || ObjectUtils.isEmpty(entity)) {
            return entity;
        }

        UnitOfWork unitOfWork = entityManager.unwrap(UnitOfWork.class);
        Object result = unitOfWork.registerObject(entity);
        return (T) result;
    }
}
