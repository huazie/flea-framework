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
public class JPAQueryPool {

    private static final ConcurrentMap<String, JPAQueryPool> jpaQueryPools = new ConcurrentHashMap<String, JPAQueryPool>();

    private String poolName; // 连接池名

    private FleaJPAQueryPool fleaJPAQueryPool; // Flea JPA查询对象池

    private JPAQueryPool(String poolName) {
        this.poolName = poolName;
    }

    /**
     * <p> 获取Redis连接池实例 (指定连接池名)</p>
     *
     * @param poolName 连接池名
     * @return Redis连接池实例对象
     * @since 1.0.0
     */
    public static JPAQueryPool getInstance(String poolName) {
        if (!jpaQueryPools.containsKey(poolName)) {
            synchronized (jpaQueryPools) {
                if (!jpaQueryPools.containsKey(poolName)) {
                    JPAQueryPool jpaQueryPool = new JPAQueryPool(poolName);
                    jpaQueryPool.fleaJPAQueryPool = new FleaJPAQueryPool(FleaJPAQueryPoolConfig.getConfig());
                    jpaQueryPools.putIfAbsent(poolName, jpaQueryPool);
                }
            }
        }
        return jpaQueryPools.get(poolName);
    }

    /**
     * <p> 获取Redis连接池实例 (默认) </p>
     *
     * @return Redis连接池实例对象
     * @since 1.0.0
     */
    public static JPAQueryPool getInstance() {
        return getInstance(CommonConstants.FleaPoolConstants.DEFAUTL_POOL_NAME);
    }

    public String getPoolName() {
        return poolName;
    }

    public FleaJPAQueryPool getFleaJPAQueryPool() {
        return fleaJPAQueryPool;
    }
}
