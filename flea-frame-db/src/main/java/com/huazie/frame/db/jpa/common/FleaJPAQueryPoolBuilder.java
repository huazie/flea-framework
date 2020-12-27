package com.huazie.frame.db.jpa.common;

import com.huazie.frame.common.pool.FleaObjectPool;
import com.huazie.frame.common.pool.IFleaObjectPoolBuilder;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;

/**
 * <p> Flea JPA查询对象池构建者 </p>
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

        if (LOGGER.isDebugEnabled()) {
            Object obj = new Object() {};
            LOGGER.debug1(obj, "FleaJPAQueryPoolConfig = {}", fleaJPAQueryPoolConfig);
            LOGGER.debug1(obj, "FleaObjectPool = {}", fleaObjectPool);
        }
        return fleaObjectPool;
    }

}
