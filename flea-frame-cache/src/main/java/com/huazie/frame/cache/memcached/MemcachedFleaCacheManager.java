package com.huazie.frame.cache.memcached;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.AbstractFleaCacheManager;
import com.huazie.frame.cache.memcached.config.MemcachedConfig;
import com.huazie.frame.cache.memcached.impl.MemcachedFleaCache;
import com.huazie.frame.common.util.ObjectUtils;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p> Memcached Flea缓存管理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class MemcachedFleaCacheManager extends AbstractFleaCacheManager {

    private final static Logger LOGGER = LoggerFactory.getLogger(MemcachedFleaCacheManager.class);

    private static volatile MemcachedFleaCacheManager cacheManager;

    private MemCachedClient memcachedClient;   // Memcached客户端类

    private MemcachedFleaCacheManager(MemCachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }

    /**
     * <p> 获取Flea缓存管理类 </p>
     *
     * @return Memcached的Flea缓存管理类实例对象
     * @since 1.0.0
     */
    public static MemcachedFleaCacheManager getInstance() {
        if (ObjectUtils.isEmpty(cacheManager)) {
            synchronized (MemcachedFleaCacheManager.class) {
                if (ObjectUtils.isEmpty(cacheManager)) {
                    cacheManager = new MemcachedFleaCacheManager(new MemCachedClient());
                    cacheManager.initSockIOPool();
                }
            }
        }
        return cacheManager;
    }

    /**
     * 初始化Memcached连接池
     *
     * @since 1.0.0
     */
    private void initSockIOPool() {
        try {
            MemcachedConfig config = MemcachedConfig.getConfig();
            SockIOPool sockIOPool = SockIOPool.getInstance();
            sockIOPool.setServers(config.getServers());
            sockIOPool.setWeights(config.getWeights());
            sockIOPool.setInitConn(config.getInitConn());
            sockIOPool.setMinConn(config.getMinConn());
            sockIOPool.setMaxConn(config.getMaxConn());
            sockIOPool.setMaintSleep(config.getMaintSleep());
            sockIOPool.setNagle(config.isNagle());
            sockIOPool.setSocketTO(config.getSocketTO());
            sockIOPool.setSocketConnectTO(config.getSocketConnectTO());
            sockIOPool.setHashingAlg(config.getHashingAlg());
            sockIOPool.initialize();
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.debug("The Memcached SockIOPool fails to be initialized", e);
            }
        }
    }

    @Override
    protected AbstractFleaCache newCache(String name, long expiry) {
        return new MemcachedFleaCache(name, expiry, memcachedClient);
    }

    public void setMemcachedClient(MemCachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }

}
