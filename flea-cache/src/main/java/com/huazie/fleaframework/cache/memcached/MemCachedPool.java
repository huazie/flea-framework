package com.huazie.fleaframework.cache.memcached;

import com.huazie.fleaframework.cache.common.CacheConfigUtils;
import com.huazie.fleaframework.cache.common.CacheConstants;
import com.huazie.fleaframework.cache.config.CacheParam;
import com.huazie.fleaframework.cache.config.CacheServer;
import com.huazie.fleaframework.cache.exceptions.FleaCacheException;
import com.huazie.fleaframework.cache.memcached.config.MemCachedConfig;
import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.ExceptionUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.whalin.MemCached.SockIOPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Flea MemCached连接池，用于初始化MemCached的套接字连接池。
 *
 * <p> 针对单独缓存接入场景，采用默认连接池初始化的方式；<br/>
 * 可参考如下：
 * <pre>
 *   // 初始化默认连接池
 *   MemCachedPool.getInstance().initialize(); </pre>
 *
 * <p> 针对整合缓存接入场景，采用指定连接池初始化的方式；<br/>
 * 可参考如下：
 * <pre>
 *   // 初始化指定连接池
 *   MemCachedPool.getInstance(group).initialize(cacheServerList); </pre>
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.0.0
 */
public class MemCachedPool {

    private String poolName; // 连接池名

    private MemCachedConfig memCachedConfig; // MemCached 配置信息

    private SockIOPool sockIOPool; // MemCached SockIOPool

    private MemCachedPool() {
    }

    /**
     * 获取MemCached连接池实例 (默认)
     *
     * @return MemCached连接池实例对象
     * @since 1.0.0
     */
    public static MemCachedPool getInstance() {
        MemCachedPool memCachedPool = new MemCachedPool();
        memCachedPool.memCachedConfig = MemCachedConfig.getConfig();
        memCachedPool.sockIOPool = SockIOPool.getInstance();
        return memCachedPool;
    }

    /**
     * 获取MemCached连接池实例（指定连接池名poolName）
     *
     * @param poolName 连接池名
     * @return MemCached连接池实例对象
     * @since 1.0.0
     */
    public static MemCachedPool getInstance(String poolName) {
        MemCachedPool memCachedPool = new MemCachedPool();
        memCachedPool.poolName = poolName;
        memCachedPool.sockIOPool = SockIOPool.getInstance(poolName);
        return memCachedPool;
    }

    /**
     * 初始化MemCached连接池
     *
     * @since 1.0.0
     */
    public void initialize() {
        if (ObjectUtils.isEmpty(memCachedConfig)) {
            ExceptionUtils.throwFleaException(FleaCacheException.class, "采用默认初始化，请使用MemCachedPool##getInstance()");
        }
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
     * 初始化MemCached连接池
     *
     * @param cacheServerList 缓存服务器集
     * @since 1.0.0
     */
    public void initialize(List<CacheServer> cacheServerList) {
        if (StringUtils.isBlank(poolName)) {
            ExceptionUtils.throwFleaException(FleaCacheException.class, "采用指定连接池名初始化，请使用MemCachedPool##getInstance(String poolName)");
        }
        // 如果连接池已经初始化过了，则跳过
        if (sockIOPool.isInitialized()) {
            return;
        }
        // 缓存服务器集为空，则跳过
        if (CollectionUtils.isEmpty(cacheServerList)) {
            return;
        }
        List<String> servers = new ArrayList<>();
        List<Integer> weights = new ArrayList<>();
        // 遍历缓存服务器集
        for (CacheServer cacheServer : cacheServerList) {
            if (ObjectUtils.isNotEmpty(cacheServer)) {
                String server = cacheServer.getServer();
                if (StringUtils.isBlank(server)) {
                    ExceptionUtils.throwFleaException(FleaCacheException.class, "请检查flea-cache-config.xml配置,【<cache-server group=" + poolName + " ></cache-server>】未配置缓存服务器");
                }
                servers.add(server);
                String weight = cacheServer.getWeight();
                if (StringUtils.isBlank(weight)) {
                    // 默认权重
                    weights.add(CommonConstants.NumeralConstants.INT_ONE);
                } else {
                    weights.add(Integer.valueOf(weight));
                }
            }
        }
        sockIOPool.setServers(servers.toArray(new String[0]));
        sockIOPool.setWeights(weights.toArray(new Integer[0]));

        // 获取MemCached 连接池配置其他参数
        // 初始化时对每个服务器建立的连接数目
        CacheParam initConnParam = CacheConfigUtils.getCacheParam(CacheConstants.MemCachedConfigConstants.MEMCACHED_CONFIG_INITCONN);
        if (ObjectUtils.isEmpty(initConnParam) || StringUtils.isBlank(initConnParam.getValue())) {
            ExceptionUtils.throwFleaException(FleaCacheException.class, "请检查flea-cache-config.xml配置,【<cache-param key=" + CacheConstants.MemCachedConfigConstants.MEMCACHED_CONFIG_INITCONN + " ></cache-param>】未配置或配置值为空");
        }
        sockIOPool.setInitConn(Integer.parseInt(initConnParam.getValue()));
        // 每个服务器建立最小的连接数
        CacheParam minConnParam = CacheConfigUtils.getCacheParam(CacheConstants.MemCachedConfigConstants.MEMCACHED_CONFIG_MINCONN);
        if (ObjectUtils.isEmpty(minConnParam) || StringUtils.isBlank(minConnParam.getValue())) {
            ExceptionUtils.throwFleaException(FleaCacheException.class, "请检查flea-cache-config.xml配置,【<cache-param key=" + CacheConstants.MemCachedConfigConstants.MEMCACHED_CONFIG_MINCONN + " ></cache-param>】未配置或配置值为空");
        }
        sockIOPool.setMinConn(Integer.parseInt(minConnParam.getValue()));
        // 每个服务器建立最大的连接数
        CacheParam maxConnParam = CacheConfigUtils.getCacheParam(CacheConstants.MemCachedConfigConstants.MEMCACHED_CONFIG_MAXCONN);
        if (ObjectUtils.isEmpty(maxConnParam) || StringUtils.isBlank(maxConnParam.getValue())) {
            ExceptionUtils.throwFleaException(FleaCacheException.class, "请检查flea-cache-config.xml配置,【<cache-param key=" + CacheConstants.MemCachedConfigConstants.MEMCACHED_CONFIG_MAXCONN + " ></cache-param>】未配置或配置值为空");
        }
        sockIOPool.setMaxConn(Integer.parseInt(maxConnParam.getValue()));
        // 自查线程周期进行工作，其每次休眠时间
        CacheParam maintSleepParam = CacheConfigUtils.getCacheParam(CacheConstants.MemCachedConfigConstants.MEMCACHED_CONFIG_MAINTSLEEP);
        if (ObjectUtils.isEmpty(maintSleepParam) || StringUtils.isBlank(maintSleepParam.getValue())) {
            ExceptionUtils.throwFleaException(FleaCacheException.class, "请检查flea-cache-config.xml配置,【<cache-param key=" + CacheConstants.MemCachedConfigConstants.MEMCACHED_CONFIG_MAINTSLEEP + " ></cache-param>】未配置或配置值为空");
        }
        sockIOPool.setMaintSleep(Long.parseLong(maintSleepParam.getValue()));
        // Socket的参数，如果是true在写数据时不缓冲，立即发送出去
        CacheParam nagleParam = CacheConfigUtils.getCacheParam(CacheConstants.MemCachedConfigConstants.MEMCACHED_CONFIG_NAGLE);
        if (ObjectUtils.isEmpty(nagleParam) || StringUtils.isBlank(nagleParam.getValue())) {
            ExceptionUtils.throwFleaException(FleaCacheException.class, "请检查flea-cache-config.xml配置,【<cache-param key=" + CacheConstants.MemCachedConfigConstants.MEMCACHED_CONFIG_NAGLE + " ></cache-param>】未配置或配置值为空");
        }
        sockIOPool.setNagle(Boolean.parseBoolean(nagleParam.getValue()));
        // Socket阻塞读取数据的超时时间
        CacheParam socketTOParam = CacheConfigUtils.getCacheParam(CacheConstants.MemCachedConfigConstants.MEMCACHED_CONFIG_SOCKETTO);
        if (ObjectUtils.isEmpty(socketTOParam) || StringUtils.isBlank(socketTOParam.getValue())) {
            ExceptionUtils.throwFleaException(FleaCacheException.class, "请检查flea-cache-config.xml配置,【<cache-param key=" + CacheConstants.MemCachedConfigConstants.MEMCACHED_CONFIG_SOCKETTO + " ></cache-param>】未配置或配置值为空");
        }
        sockIOPool.setSocketTO(Integer.parseInt(socketTOParam.getValue()));
        // Socket连接超时时间
        CacheParam socketConnectTOParam = CacheConfigUtils.getCacheParam(CacheConstants.MemCachedConfigConstants.MEMCACHED_CONFIG_SOCKETCONNECTTO);
        if (ObjectUtils.isEmpty(socketConnectTOParam) || StringUtils.isBlank(socketConnectTOParam.getValue())) {
            ExceptionUtils.throwFleaException(FleaCacheException.class, "请检查flea-cache-config.xml配置,【<cache-param key=" + CacheConstants.MemCachedConfigConstants.MEMCACHED_CONFIG_SOCKETCONNECTTO + " ></cache-param>】未配置或配置值为空");
        }
        sockIOPool.setSocketConnectTO(Integer.parseInt(socketConnectTOParam.getValue()));
        // MemCached分布式hash算法
        CacheParam hashingAlgParam = CacheConfigUtils.getCacheParam(CacheConstants.MemCachedConfigConstants.MEMCACHED_CONFIG_HASHINGALG);
        if (ObjectUtils.isEmpty(hashingAlgParam) || StringUtils.isBlank(hashingAlgParam.getValue())) {
            ExceptionUtils.throwFleaException(FleaCacheException.class, "请检查flea-cache-config.xml配置,【<cache-param key=" + CacheConstants.MemCachedConfigConstants.MEMCACHED_CONFIG_HASHINGALG + " ></cache-param>】未配置或配置值为空");
        }
        sockIOPool.setHashingAlg(Integer.parseInt(hashingAlgParam.getValue()));
        // 连接池初始化
        sockIOPool.initialize();
    }

    /**
     * 关闭连接池
     *
     * @since 1.0.0
     */
    public void shutDown() {
        sockIOPool.shutDown();
    }

    /**
     * 获取 Memcached 缓存连接池名
     *
     * @return Memcached 缓存连接池名
     * @since 1.0.0
     */
    public String getPoolName() {
        return poolName;
    }

    /**
     * 获取 Memcached 套接字连接池
     *
     * @return Memcached 套接字连接池
     * @since 1.0.0
     */
    public SockIOPool getSockIOPool() {
        return sockIOPool;
    }
}
