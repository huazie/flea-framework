package com.huazie.fleaframework.db.jpa.handler.impl;

import com.huazie.fleaframework.common.FleaEntity;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.db.common.DBConstants;
import com.huazie.fleaframework.db.common.lib.pojo.SplitLib;
import com.huazie.fleaframework.db.common.table.pojo.SplitTable;
import com.huazie.fleaframework.db.common.util.FleaSplitUtils;
import com.huazie.fleaframework.db.jpa.persistence.FleaEntityManager;
import com.huazie.fleaframework.db.jpa.common.FleaJPAQuery;
import com.huazie.fleaframework.db.jpa.handler.IFleaJPASplitHandler;

import javax.persistence.EntityManager;

/**
 * Flea分表分库处理抽象实现类
 *
 * @author huazie
 * @version 1.1.0
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

        // 获取分表信息（包括主表名 和 分表名 【如果存在分表返回】）
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
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error1(new Object() {}, "默认库名为空，本次不处理【FleaJPAQuery】！");
                }
                return;
            }
            splitLib = FleaSplitUtils.getSplitLib(libName, fleaEntity.getEntityMap());
        }
        // 分库场景，重新初始化Flea JPA查询对象
        handleInner(query, splitLib);
    }

    @Override
    public EntityManager handle(EntityManager entityManager, Object entity, boolean flag) throws CommonException {

        if (ObjectUtils.isEmpty(entityManager) || ObjectUtils.isEmpty(entity) || !(entity instanceof FleaEntity)) {
            return entityManager;
        }

        // 获取分表信息（包括主表名 和 分表名 【如果存在分表返回】）
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
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error1(new Object() {}, "默认库名为空，本次不处理【EntityManager】！");
                }
                return entityManager;
            }
            splitLib = FleaSplitUtils.getSplitLib(libName, fleaEntity.getEntityMap());
            // 设置分库信息
            fleaEntity.put(DBConstants.LibTableSplitConstants.SPLIT_LIB, splitLib);
        }

        // 如果是getFleaNextValue获取实体管理器，并且主键生成器在模板库中，直接返回实体管理器
        if (flag && splitTable.isGeneratorFlag()) {
            return entityManager;
        }

        // 分库场景，重新初始化实体管理类
        return handleInner(entityManager, splitLib);
    }

    /**
     * 分库场景，重新初始化Flea JPA查询对象
     *
     * @param query    Flea JPA 查询对象
     * @param splitLib 分库对象
     * @throws CommonException 通用异常
     * @since 1.1.0
     */
    private void handleInner(FleaJPAQuery query, SplitLib splitLib) throws CommonException {
        // 分库场景，重新获取对应分库下的实体管理类
        EntityManager splitEntityManager = handleInner(splitLib);
        if (ObjectUtils.isNotEmpty(splitEntityManager)) {
            // 分库场景，重新初始化Flea JPA查询对象
            query.init(splitEntityManager, query.getSourceClazz(), query.getResultClazz());
        }
    }

    /**
     * 分库场景，重新初始化实体管理类
     *
     * @param entityManager 实体管理类对象
     * @param splitLib      分库对象
     * @return 实体管理类
     * @throws CommonException 通用异常
     * @since 1.1.0
     */
    private EntityManager handleInner(EntityManager entityManager, SplitLib splitLib) throws CommonException {
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
}
