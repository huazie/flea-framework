package com.huazie.frame.db.jpa.common;

import com.huazie.frame.common.pool.FleaObjectPool;
import com.huazie.frame.common.pool.IFleaObjectPoolBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p> Flea JPA查询对象池构建者 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJPAQueryPoolBuilder implements IFleaObjectPoolBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaJPAQueryPoolBuilder.class);

    @Override
    public FleaObjectPool build(String poolName) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Start");
        }

        // 获取Flea JPA查询对象池配置
        FleaJPAQueryPoolConfig fleaJPAQueryPoolConfig = FleaJPAQueryPoolConfig.getConfig();
        // 新建 Flea JPA查询对象池
        FleaObjectPool fleaObjectPool = new FleaJPAQueryPool(poolName, fleaJPAQueryPoolConfig);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaJPAQueryPoolConfig = {}", fleaJPAQueryPoolConfig);
            LOGGER.debug("FleaObjectPool = {}", fleaObjectPool);
            LOGGER.debug("End");
        }
        return fleaObjectPool;
    }

}
