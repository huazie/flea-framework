package com.huazie.frame.cache.memcached;

import com.huazie.frame.cache.memcached.config.MemCachedConfig;
import com.whalin.MemCached.SockIOPool;

/**
 * <p> MemCached连接池 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class MemCachedPool {

    private MemCachedConfig memCachedConfig;

    private SockIOPool sockIOPool;

    private MemCachedPool() {
    }

    /**
     * <p> 获取MemCached连接池实例 </p>
     *
     * @return Memcached连接池实例对象
     * @since 1.0.0
     */
    public static MemCachedPool getInstance() {
        MemCachedPool memCachedPool = new MemCachedPool();
        memCachedPool.memCachedConfig = MemCachedConfig.getConfig();
        memCachedPool.sockIOPool = SockIOPool.getInstance();
        return memCachedPool;
    }

    /**
     * <p> 初始化连接池 </p>
     *
     * @since 1.0.0
     */
    public void initialize() {
        sockIOPool.setServers(memCachedConfig.getServers());
        sockIOPool.setWeights(memCachedConfig.getWeights());
        sockIOPool.setInitConn(memCachedConfig.getInitConn());
        sockIOPool.setMinConn(memCachedConfig.getMinConn());
        sockIOPool.setMaxConn(memCachedConfig.getMaxConn());
        sockIOPool.setMaintSleep(memCachedConfig.getMaintSleep());
        sockIOPool.setNagle(memCachedConfig.isNagle());
        sockIOPool.setSocketTO(memCachedConfig.getSocketTO());
        sockIOPool.setSocketConnectTO(memCachedConfig.getSocketConnectTO());
        sockIOPool.setHashingAlg(memCachedConfig.getHashingAlg());
        sockIOPool.initialize();
    }

    /**
     * <p> 关闭连接池 </p>
     *
     * @since 1.0.0
     */
    public void shutDown() {
        sockIOPool.shutDown();
    }

}
