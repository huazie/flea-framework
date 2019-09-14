package com.huazie.frame.db.jpa.common;

import com.huazie.frame.common.CommonConstants;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <p> JPA查询对象池 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJPAQueryObjectPool {

    private static final ConcurrentMap<String, FleaJPAQueryObjectPool> jpaQueryPools = new ConcurrentHashMap<String, FleaJPAQueryObjectPool>();

    private String poolName; // 对象池名

    private FleaJPAQueryPool fleaJPAQueryPool; // Flea JPA查询对象池

    /**
     * <p> 私有构造方法，指定对象池名 </p>
     *
     * @param poolName 对象池名
     * @since 1.0.0
     */
    private FleaJPAQueryObjectPool(String poolName) {
        this.poolName = poolName;
    }

    /**
     * <p> 获取JPA查询对象池实例 (指定对象池名) </p>
     *
     * @param poolName 对象池名
     * @return JPA查询对象池实例
     * @since 1.0.0
     */
    public static FleaJPAQueryObjectPool getInstance(String poolName) {
        if (!jpaQueryPools.containsKey(poolName)) {
            synchronized (jpaQueryPools) {
                if (!jpaQueryPools.containsKey(poolName)) {
                    FleaJPAQueryObjectPool jpaQueryPool = new FleaJPAQueryObjectPool(poolName);
                    jpaQueryPool.fleaJPAQueryPool = new FleaJPAQueryPool(FleaJPAQueryPoolConfig.getConfig());
                    jpaQueryPools.putIfAbsent(poolName, jpaQueryPool);
                }
            }
        }
        return jpaQueryPools.get(poolName);
    }

    /**
     * <p> 获取JPA查询对象池实例 (默认对象池名 default) </p>
     *
     * @return JPA查询对象池实例
     * @since 1.0.0
     */
    public static FleaJPAQueryObjectPool getInstance() {
        return getInstance(CommonConstants.FleaPoolConstants.DEFAUTL_POOL_NAME);
    }

    /**
     * <p> 获取对象池名</p>
     *
     * @return 对象池名
     * @since 1.0.0
     */
    public String getPoolName() {
        return poolName;
    }

    /**
     * <p> 获取Flea JPA查询对象池 </p>
     *
     * @return Flea JPA查询对象池实例
     * @since 1.0.0
     */
    public FleaJPAQueryPool getFleaJPAQueryPool() {
        return fleaJPAQueryPool;
    }
}
