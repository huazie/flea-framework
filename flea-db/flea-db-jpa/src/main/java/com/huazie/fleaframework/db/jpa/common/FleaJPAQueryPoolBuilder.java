package com.huazie.fleaframework.db.jpa.common;

import com.huazie.fleaframework.common.pool.FleaObjectPool;
import com.huazie.fleaframework.common.pool.IFleaObjectPoolBuilder;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;

/**
 * Flea JPA查询对象池构建者，用于新建Flea JPA查询对象池。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJPAQueryPoolBuilder implements IFleaObjectPoolBuilder {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaJPAQueryPoolBuilder.class);

    @Override
    public FleaObjectPool build(String poolName) {

        // 获取Flea JPA查询对象池配置
        FleaJPAQueryPoolConfig fleaJPAQueryPoolConfig = FleaJPAQueryPoolConfig.getConfig();
        // 新建 Flea JPA查询对象池
        FleaObjectPool fleaObjectPool = new FleaJPAQueryPool(poolName, fleaJPAQueryPoolConfig);

        Object obj = new Object() {};
        LOGGER.debug1(obj, "FleaJPAQueryPoolConfig = {}", fleaJPAQueryPoolConfig);
        LOGGER.debug1(obj, "FleaObjectPool = {}", fleaObjectPool);
        return fleaObjectPool;
    }

}
