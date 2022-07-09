package com.huazie.fleaframework.db.jpa.common;

import com.huazie.fleaframework.common.FleaConfigManager;
import com.huazie.fleaframework.common.config.ConfigItem;
import com.huazie.fleaframework.common.config.ConfigItems;
import com.huazie.fleaframework.common.pool.FleaObjectPoolConfig;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.db.common.DBConstants.JPAQueryPoolConfigConstants;

/**
 * Flea JPA查询对象池配置，包含最大连接数、最大空闲连接数、
 * 最小空闲连接数、获取连接时的最大等待毫秒数等。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJPAQueryPoolConfig extends FleaObjectPoolConfig {

    private static volatile FleaJPAQueryPoolConfig config;

    /**
     * 无参构造方法，初始化部分默认配置
     *
     * @since 1.0.0
     */
    private FleaJPAQueryPoolConfig() {
        super();
        initConfig();
    }

    /**
     * 获取Flea JPA查询对象池配置实例
     *
     * @return Flea JPA查询对象池配置实例
     * @since 1.0.0
     */
    public static FleaJPAQueryPoolConfig getConfig() {
        if (ObjectUtils.isEmpty(config)) {
            synchronized (FleaJPAQueryPoolConfig.class) {
                if (ObjectUtils.isEmpty(config)) {
                    config = new FleaJPAQueryPoolConfig();
                }
            }
        }
        return config;
    }

    /**
     * 初始化对象池配置
     *
     * @since 1.0.0
     */
    private void initConfig() {
        // 从flea-config.xml配置中获取对象池配置信息
        ConfigItems configItems = FleaConfigManager.getConfigItems(JPAQueryPoolConfigConstants.FLEA_JPA_QUERY);
        // Flea JPA查询对象池最大连接数
        ConfigItem maxTotalConfigItem = FleaConfigManager.getConfigItem(JPAQueryPoolConfigConstants.JPA_QUERY_POOL_MAXTOTAL, configItems);
        if (ObjectUtils.isNotEmpty(maxTotalConfigItem)) {
            this.setMaxTotal(Integer.parseInt(maxTotalConfigItem.getValue()));
        }
        // Flea JPA查询对象池最大空闲连接数
        ConfigItem maxIdleConfigItem = FleaConfigManager.getConfigItem(JPAQueryPoolConfigConstants.JPA_QUERY_POOL_MAXIDLE, configItems);
        if (ObjectUtils.isNotEmpty(maxIdleConfigItem)) {
            this.setMaxIdle(Integer.parseInt(maxIdleConfigItem.getValue()));
        }
        // Flea JPA查询对象池最小空闲连接数
        ConfigItem minIdleConfigItem = FleaConfigManager.getConfigItem(JPAQueryPoolConfigConstants.JPA_QUERY_POOL_MINIDLE, configItems);
        if (ObjectUtils.isNotEmpty(minIdleConfigItem)) {
            this.setMinIdle(Integer.parseInt(minIdleConfigItem.getValue()));
        }
        // Flea JPA查询对象池获取连接时的最大等待毫秒数
        ConfigItem maxWaitMillisConfigItem = FleaConfigManager.getConfigItem(JPAQueryPoolConfigConstants.JPA_QUERY_POOL_MAXWAITMILLIS, configItems);
        if (ObjectUtils.isNotEmpty(maxWaitMillisConfigItem)) {
            this.setMaxWaitMillis(Integer.parseInt(maxWaitMillisConfigItem.getValue()));
        }
    }
}
