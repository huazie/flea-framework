package com.huazie.fleaframework.db.jpa.handler.impl;

import com.huazie.fleaframework.common.FleaEntity;
import com.huazie.fleaframework.common.FleaEntityConstants;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.ReflectUtils;
import com.huazie.fleaframework.db.common.DBConstants;
import com.huazie.fleaframework.db.common.lib.pojo.SplitLib;
import com.huazie.fleaframework.db.common.table.pojo.SplitTable;
import com.huazie.fleaframework.db.common.util.FleaLibUtil;
import com.huazie.fleaframework.db.common.util.FleaSplitUtils;
import com.huazie.fleaframework.db.jpa.common.FleaJPAQuery;
import com.huazie.fleaframework.db.jpa.handler.IFleaJPASplitHandler;
import com.huazie.fleaframework.db.jpa.persistence.FleaEntityManager;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Flea分表分库处理父类，由子类【与具体的JPA实现相关】
 * 实现自定义的实体管理器的增删改查等操作。
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.1.0
 */
public abstract class FleaLibTableSplitHandler implements IFleaJPASplitHandler {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaLibTableSplitHandler.class);

    @Override
    public void handle(FleaJPAQuery query, Object entity) throws CommonException {

        if (ObjectUtils.isEmpty(query) || ObjectUtils.isEmpty(entity) || !(entity instanceof FleaEntity)) {
            return;
        }

        FleaEntity fleaEntity = (FleaEntity) entity;

        // 获取分表信息（包括模板表名 和 分表名 【如果存在分表返回】）
        SplitTable splitTable = FleaEntityManager.getSplitTable(entity);

        SplitLib splitLib;
        // 存在分表，需要查询指定分表
        if (splitTable.isExistSplitTable()) {
            splitLib = splitTable.getSplitLib();
            // 设置分表信息
            fleaEntity.put(DBConstants.LibTableSplitConstants.SPLIT_TABLE, splitTable);
        } else {
            // 获取默认库名，这里的对象池名为持久化单元名【通常对应着库名】
            String libName = query.getPoolName();
            if (ObjectUtils.isEmpty(libName)) {
                LOGGER.error1(new Object() {}, "默认库名为空，本次不处理【FleaJPAQuery】！");
                return;
            }
            splitLib = FleaSplitUtils.getSplitLib(libName, FleaLibUtil.getSplitLibSeqValues());
        }

        // 分库场景，重新获取对应分库下的实体管理类
        EntityManager splitEntityManager = handleInner(splitLib);

        EntityManager entityManager;
        if (ObjectUtils.isEmpty(splitEntityManager)) {
            entityManager = query.getEntityManager();
        } else {
            entityManager = splitEntityManager;
        }

        // 分表场景 或 分表场景 或 当前线程存在自定义的Flea实体管理器实现, 直接获取
        if (isFleaEntityManagerImpl(entityManager, splitTable, splitLib)) {
            splitEntityManager = getFleaEntityMangerImpl(entityManager);
        }

        if (ObjectUtils.isNotEmpty(splitEntityManager)) {
            // 重新初始化Flea JPA查询对象
            query.init(splitEntityManager, query.getSourceClazz(), query.getResultClazz());
        }
    }

    @Override
    public void handle(FleaJPAQuery query, TypedQuery typedQuery) {
        SplitTable splitTable = getSplitTableFromEntity(query.getEntity());
        // 分表信息为空或不存在分表，默认不处理
        if (!splitTable.isExistSplitTable()) {
            LOGGER.error1(new Object() {}, "分表信息为空或不存在分表，本次不处理【FleaJPAQuery】！");
            return;
        }
        // 处理类型查询接口的分表信息
        handleInner(query, typedQuery, splitTable);
    }

    @Override
    public EntityManager handle(EntityManager entityManager, Object entity, boolean flag) throws CommonException {

        if (ObjectUtils.isEmpty(entityManager) || ObjectUtils.isEmpty(entity) || !(entity instanceof FleaEntity)) {
            return entityManager;
        }

        // 获取分表信息（包括模板表名 和 分表名 【如果存在分表返回】）
        SplitTable splitTable = FleaEntityManager.getSplitTable(entity);

        FleaEntity fleaEntity = (FleaEntity) entity;

        SplitLib splitLib;
        // 存在分表，则需要操作具体分表
        if (splitTable.isExistSplitTable()) {
            splitLib = splitTable.getSplitLib();
            // 设置分表信息
            fleaEntity.put(DBConstants.LibTableSplitConstants.SPLIT_TABLE, splitTable);
        } else {
            // 获取默认库名
            String libName = fleaEntity.get(DBConstants.LibTableSplitConstants.FLEA_LIB_NAME, String.class);
            if (ObjectUtils.isEmpty(libName)) {
                LOGGER.error1(new Object() {}, "默认库名为空，本次不处理【EntityManager】！");
                return entityManager;
            }
            splitLib = FleaSplitUtils.getSplitLib(libName, FleaLibUtil.getSplitLibSeqValues());
            // 设置分库信息
            fleaEntity.put(DBConstants.LibTableSplitConstants.SPLIT_LIB, splitLib);
        }

        // 如果是getFleaNextValue获取实体管理器，并且主键生成器表在模板库中，直接返回实体管理器
        if (flag && splitTable.isGeneratorFlag()) {
            return entityManager;
        }

        // 分库场景，重新获取对应分库下的实体管理类
        EntityManager splitEntityManager = handleInner(splitLib);
        if (ObjectUtils.isNotEmpty(splitEntityManager)) {
            // 分库场景，重新初始化实体管理类
            entityManager = splitEntityManager;
        }
        return entityManager;
    }

    /**
     * 分库场景，重新获取对应分库下的实体管理类
     *
     * @param splitLib 分库对象
     * @return 实体管理类
     * @throws CommonException 通用异常
     * @since 1.1.0
     */
    private EntityManager handleInner(SplitLib splitLib) throws CommonException {
        EntityManager entityManager = null;
        if (ObjectUtils.isNotEmpty(splitLib) && splitLib.isExistSplitLib()) {
            String unitName = splitLib.getSplitLibName();
            String transactionName = splitLib.getSplitLibTxName();
            entityManager = FleaEntityManager.getEntityManager(unitName, transactionName);
        }
        return entityManager;
    }

    @Override
    public TransactionStatus getTransaction(TransactionDefinition definition, PlatformTransactionManager transactionManager, EntityManager entityManager) {
        // JPA事务管理器
        JpaTransactionManager jpaTransactionManager = (JpaTransactionManager) transactionManager;
        Object obj = TransactionSynchronizationManager.getResource(jpaTransactionManager.getEntityManagerFactory());
        if (ObjectUtils.isEmpty(obj)) {
            // 获取Flea实体管理器实现类
            EntityManager fleaEntityManagerImpl = getFleaEntityMangerImpl(entityManager);
            // 新建实体管理器包装类资源，持有Flea实体管理器实现类
            EntityManagerHolder entityManagerHolder = new EntityManagerHolder(fleaEntityManagerImpl);
            // 将实体管理器工厂类的实体管理器包装类资源绑定到当前线程
            TransactionSynchronizationManager.bindResource(jpaTransactionManager.getEntityManagerFactory(), entityManagerHolder);
        }
        // 获取事务状态对象，并开启事务
        return jpaTransactionManager.getTransaction(definition);
    }

    @Override
    public <T> Number getNextValue(EntityManager entityManager, Class<T> entityClass, T entity) {
        SplitTable splitTable = getSplitTableFromEntity(entity);
        return getNextValueInner(entityManager, entityClass, splitTable);
    }

    @Override
    public <T> T find(EntityManager entityManager, Object primaryKey, Class<T> entityClass, T entity) {
        SplitTable splitTable = getSplitTableFromEntity(entity);
        SplitLib splitLib = getSplitLibFromEntity(entity);

        // 分表场景 或 分表场景 或 当前线程存在自定义的Flea实体管理器实现, 直接获取
        T t;
        if (isFleaEntityManagerImpl(entityManager, splitTable, splitLib)) {
            t = findInner(entityManager, primaryKey, entityClass, splitTable);
        } else {
            t =  entityManager.find(entityClass, primaryKey);
        }

        LOGGER.debug1(new Object() {}, entityClass.getSimpleName() + " = {}", t);

        return t;
    }

    @Override
    public <T> boolean remove(EntityManager entityManager, T entity) {
        SplitTable splitTable = getSplitTableFromEntity(entity);
        SplitLib splitLib = getSplitLibFromEntity(entity);

        // 分表场景 或 分表场景 或 当前线程存在自定义的Flea实体管理器实现, 直接获取
        if (isFleaEntityManagerImpl(entityManager, splitTable, splitLib)) {
            // 使用自定义的Flea实体管理器实现，删除实体数据
            removeInner(entityManager, entity);
        } else {
            if (!entityManager.contains(entity)) {
                entity = registerObject(entityManager, entity);
            }
            entityManager.remove(entity);
        }
        return true;
    }

    @Override
    public <T> T merge(EntityManager entityManager, T entity) {
        SplitTable splitTable = getSplitTableFromEntity(entity);
        SplitLib splitLib = getSplitLibFromEntity(entity);

        // 如果存在done_date字段，则默认设置为当前时间
        ReflectUtils.setValue(entity, FleaEntityConstants.E_DONE_DATE, DateUtils.getCurrentTime());

        // 分表场景 或 分表场景 或 当前线程存在自定义的Flea实体管理器实现, 直接获取
        if (isFleaEntityManagerImpl(entityManager, splitTable, splitLib)) {
            // 使用自定义的Flea实体管理器实现，合并实体数据的状态至当前持久化上下文中
            return mergeInner(entityManager, entity);
        } else {
            return entityManager.merge(entity);
        }
    }

    @Override
    public <T> void persist(EntityManager entityManager, T entity) {
        SplitTable splitTable = getSplitTableFromEntity(entity);
        SplitLib splitLib = getSplitLibFromEntity(entity);

        // 分表场景 或 分表场景 或 当前线程存在自定义的Flea实体管理器实现, 直接获取
        if (isFleaEntityManagerImpl(entityManager, splitTable, splitLib)) {
            // 使用自定义的Flea实体管理器实现，向工作单元注册对象
            persistInner(entityManager, entity);
        } else {
            entityManager.persist(entity);
        }
    }

    @Override
    public <T> void flush(EntityManager entityManager, T entity) {
        SplitTable splitTable = getSplitTableFromEntity(entity);
        SplitLib splitLib = getSplitLibFromEntity(entity);

        // 分表场景 或 分表场景 或 当前线程存在自定义的Flea实体管理器实现, 直接获取
        if (isFleaEntityManagerImpl(entityManager, splitTable, splitLib)) {
            // 使用自定义的Flea实体管理器实现，将持久性上下文同步到基础数据库。
            flushInner(entityManager);
        } else {
            entityManager.flush();
        }
    }

    /**
     * 是否使用自定义的Flea实体管理器实现
     *
     * @param entityManager 实体管理器
     * @param splitTable 分表信息
     * @param splitLib 分库信息
     * @return true：使用自定义的实体管理器实现 false: 使用原有实体管理器
     */
    private boolean isFleaEntityManagerImpl(EntityManager entityManager, SplitTable splitTable, SplitLib splitLib) {
        return (splitTable.isExistSplitTable() || splitLib.isExistSplitLib() || FleaEntityManager.hasResource(entityManager.getEntityManagerFactory()));
    }

    /**
     * 从实体类对象中获取分表信息
     *
     * @param entity 实体类对象
     * @return 分表信息
     * @since 2.0.0
     */
    private SplitTable getSplitTableFromEntity(Object entity) {
        SplitTable splitTable = null;
        if (ObjectUtils.isNotEmpty(entity) && (entity instanceof FleaEntity)) {
            // 获取分表信息
            FleaEntity fleaEntity = (FleaEntity) entity;
            splitTable = fleaEntity.get(DBConstants.LibTableSplitConstants.SPLIT_TABLE, SplitTable.class);
        }
        if (ObjectUtils.isEmpty(splitTable)) {
            splitTable = new SplitTable();
            splitTable.setExistSplitTable(false);
        }
        return splitTable;
    }

    /**
     * 从实体类对象中获取分库信息
     *
     * @param entity 实体类对象
     * @return 分库信息
     * @since 2.0.0
     */
    private SplitLib getSplitLibFromEntity(Object entity) {
        SplitLib splitLib = null;
        if (ObjectUtils.isNotEmpty(entity) && (entity instanceof FleaEntity)) {
            // 获取分库信息
            FleaEntity fleaEntity = (FleaEntity) entity;
            splitLib = fleaEntity.get(DBConstants.LibTableSplitConstants.SPLIT_LIB, SplitLib.class);
        }
        if (ObjectUtils.isEmpty(splitLib)) {
            splitLib = new SplitLib();
            splitLib.setExistSplitLib(false);
        }
        return splitLib;
    }

    /**
     * 获取自定义的Flea实体管理器实现
     *
     * @param entityManager 实体管理器
     * @return 自定义的Flea实体管理器实现
     * @since 2.0.0
     */
    protected abstract EntityManager getFleaEntityMangerImpl(EntityManager entityManager);

    /**
     * 处理类型查询接口的分表信息
     *
     * @param query Flea JPA查询对象
     * @param typedQuery 类型查询接口
     * @param splitTable 分表信息
     * @since 2.0.0
     */
    protected abstract void handleInner(FleaJPAQuery query, TypedQuery typedQuery, SplitTable splitTable);

    /**
     * 自定义的实体管理器实现，获取下一个主键值
     *
     * @param entityManager 实体管理器
     * @param entityClass 实体类Class对象
     * @param splitTable    分表信息
     * @param <T>           实体类型
     * @return 下一个主键值
     * @since 2.0.0
     */
    protected abstract <T> Number getNextValueInner(EntityManager entityManager, Class<T> entityClass, SplitTable splitTable);

    /**
     * 使用自定义的实体管理器实现，根据主键查询实体数据
     *
     * @param entityManager 实体管理器
     * @param primaryKey    主键
     * @param entityClass   实体类Class对象
     * @param splitTable    分表信息
     * @param <T>           实体类型
     * @return 实体数据
     * @since 2.0.0
     */
    protected abstract <T> T findInner(EntityManager entityManager, Object primaryKey, Class<T> entityClass, SplitTable splitTable);

    /**
     * 使用自定义的实体管理器实现，删除实体数据
     *
     * @param entityManager 实体管理器
     * @param entity        待删除的实体数据
     * @param <T>           实体类型
     * @since 2.0.0
     */
    protected abstract <T> void removeInner(EntityManager entityManager, T entity);

    /**
     * 使用自定义的实体管理器实现，合并实体数据的状态至当前持久化上下文中
     *
     * @param entityManager 实体管理器
     * @param entity        更新后的实体数据
     * @param <T>           实体类型
     * @return 更新后的实体数据
     * @since 2.0.0
     */
    protected abstract <T> T mergeInner(EntityManager entityManager, T entity);

    /**
     * 使用自定义的实体管理器实现，向工作单元注册对象
     *
     * @param entityManager 实体管理器
     * @param entity        待新增的实体数据
     * @param <T>           实体类型
     * @since 2.0.0
     */
    protected abstract <T> void persistInner(EntityManager entityManager, T entity);

    /**
     * 使用自定义的实体管理器实现，将持久化上下文同步到底层数据库。
     *
     * @param entityManager 实体管理器
     * @since 2.0.0
     */
    protected abstract void flushInner(EntityManager entityManager);

    /**
     * 注册实体对象
     *
     * @param entity 待注册的实体对象
     * @param <T> 实体类型
     * @return 注册后复制的实体对象
     * @since 2.0.0
     */
    protected abstract <T> T registerObject(EntityManager entityManager, T entity);
}
