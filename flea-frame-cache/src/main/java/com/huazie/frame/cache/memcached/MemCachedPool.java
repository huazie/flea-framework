package com.huazie.frame.cache.memcached;

import com.huazie.frame.cache.common.CacheConstants.MemCachedConfigConstants;
import com.huazie.frame.cache.config.CacheParam;
import com.huazie.frame.cache.config.CacheParams;
import com.huazie.frame.cache.config.CacheServer;
import com.huazie.frame.cache.memcached.config.MemCachedConfig;
import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.whalin.MemCached.SockIOPool;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> MemCached连接池 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class MemCachedPool {

    private String poolName; // 连接池名

    private MemCachedConfig memCachedConfig; // MemCached 配置信息

    private SockIOPool sockIOPool; // MemCached SockIOPool

    private MemCachedPool() {
    }

    /**
     * <p> 获取MemCached连接池实例 (默认) </p>
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
     * <p> 获取MemCached连接池实例（指定连接池名poolName） </p>
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
     * <p> 初始化MemCached连接池 </p>
     *
     * @since 1.0.0
     */
    void initialize() {
        if (ObjectUtils.isEmpty(memCachedConfig)) {
            throw new RuntimeException("采用默认初始化，请使用MemCachedPool##getInstance()");
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
     * <p> 初始化MemCached连接池 </p>
     *
     * @param cacheServerList 缓存服务器集
     * @param cacheParams     缓存参数集
     * @since 1.0.0
     */
    void initialize(List<CacheServer> cacheServerList, CacheParams cacheParams) {
        if (StringUtils.isBlank(poolName)) {
            throw new RuntimeException("采用指定连接池名初始化，请使用MemCachedPool##getInstance(String poolName)");
        }
        // 如果连接池已经初始化过了，则跳过
        if (sockIOPool.isInitialized()) {
            return;
        }
        // 缓存服务器集为空，则跳过
        if (CollectionUtils.isEmpty(cacheServerList)) {
            return;
        }
        // 缓存参数集为空，则跳过
        if (ObjectUtils.isEmpty(cacheParams) || CollectionUtils.isEmpty(cacheParams.getCacheParamList())) {
            return;
        }
        List<String> servers = new ArrayList<>();
        List<Integer> weights = new ArrayList<>();
        // 遍历缓存服务器集
        for (CacheServer cacheServer : cacheServerList) {
            if (ObjectUtils.isNotEmpty(cacheServer)) {
                String server = cacheServer.getServer();
                if (StringUtils.isBlank(server)) {
                    throw new RuntimeException("请检查flea-cache-config.xml配置,【<cache-server group=" + poolName + " ></cache-server>】未配置缓存服务器");
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
        CacheParam initConnParam = cacheParams.getCacheParam(MemCachedConfigConstants.MEMCACHED_CACHE_PARAM_INITCONN);
        if (ObjectUtils.isEmpty(initConnParam) || StringUtils.isBlank(initConnParam.getValue())) {
            throw new RuntimeException("请检查flea-cache-config.xml配置,【<cache-param key=" + MemCachedConfigConstants.MEMCACHED_CACHE_PARAM_INITCONN + " ></cache-param>】未配置或配置值为空");
        }
        sockIOPool.setInitConn(Integer.parseInt(initConnParam.getValue()));
        // 每个服务器建立最小的连接数
        CacheParam minConnParam = cacheParams.getCacheParam(MemCachedConfigConstants.MEMCACHED_CACHE_PARAM_MINCONN);
        if (ObjectUtils.isEmpty(minConnParam) || StringUtils.isBlank(minConnParam.getValue())) {
            throw new RuntimeException("请检查flea-cache-config.xml配置,【<cache-param key=" + MemCachedConfigConstants.MEMCACHED_CACHE_PARAM_MINCONN + " ></cache-param>】未配置或配置值为空");
        }
        sockIOPool.setMinConn(Integer.parseInt(minConnParam.getValue()));
        // 每个服务器建立最大的连接数
        CacheParam maxConnParam = cacheParams.getCacheParam(MemCachedConfigConstants.MEMCACHED_CACHE_PARAM_MAXCONN);
        if (ObjectUtils.isEmpty(maxConnParam) || StringUtils.isBlank(maxConnParam.getValue())) {
            throw new RuntimeException("请检查flea-cache-config.xml配置,【<cache-param key=" + MemCachedConfigConstants.MEMCACHED_CACHE_PARAM_MAXCONN + " ></cache-param>】未配置或配置值为空");
        }
        sockIOPool.setMaxConn(Integer.parseInt(maxConnParam.getValue()));
        // 自查线程周期进行工作，其每次休眠时间
        CacheParam maintSleepParam = cacheParams.getCacheParam(MemCachedConfigConstants.MEMCACHED_CACHE_PARAM_MAINTSLEEP);
        if (ObjectUtils.isEmpty(maintSleepParam) || StringUtils.isBlank(maintSleepParam.getValue())) {
            throw new RuntimeException("请检查flea-cache-config.xml配置,【<cache-param key=" + MemCachedConfigConstants.MEMCACHED_CACHE_PARAM_MAINTSLEEP + " ></cache-param>】未配置或配置值为空");
        }
        sockIOPool.setMaintSleep(Long.parseLong(maintSleepParam.getValue()));
        // Socket的参数，如果是true在写数据时不缓冲，立即发送出去
        CacheParam nagleParam = cacheParams.getCacheParam(MemCachedConfigConstants.MEMCACHED_CACHE_PARAM_NAGLE);
        if (ObjectUtils.isEmpty(nagleParam) || StringUtils.isBlank(nagleParam.getValue())) {
            throw new RuntimeException("请检查flea-cache-config.xml配置,【<cache-param key=" + MemCachedConfigConstants.MEMCACHED_CACHE_PARAM_NAGLE + " ></cache-param>】未配置或配置值为空");
        }
        sockIOPool.setNagle(Boolean.parseBoolean(nagleParam.getValue()));
        // Socket阻塞读取数据的超时时间
        CacheParam socketTOParam = cacheParams.getCacheParam(MemCachedConfigConstants.MEMCACHED_CACHE_PARAM_SOCKETTO);
        if (ObjectUtils.isEmpty(socketTOParam) || StringUtils.isBlank(socketTOParam.getValue())) {
            throw new RuntimeException("请检查flea-cache-config.xml配置,【<cache-param key=" + MemCachedConfigConstants.MEMCACHED_CACHE_PARAM_SOCKETTO + " ></cache-param>】未配置或配置值为空");
        }
        sockIOPool.setSocketTO(Integer.parseInt(socketTOParam.getValue()));
        // Socket连接超时时间
        CacheParam socketConnectTOParam = cacheParams.getCacheParam(MemCachedConfigConstants.MEMCACHED_CACHE_PARAM_SOCKETCONNECTTO);
        if (ObjectUtils.isEmpty(socketConnectTOParam) || StringUtils.isBlank(socketConnectTOParam.getValue())) {
            throw new RuntimeException("请检查flea-cache-config.xml配置,【<cache-param key=" + MemCachedConfigConstants.MEMCACHED_CACHE_PARAM_SOCKETCONNECTTO + " ></cache-param>】未配置或配置值为空");
        }
        sockIOPool.setSocketConnectTO(Integer.parseInt(socketConnectTOParam.getValue()));
        // MemCached分布式hash算法
        CacheParam hashingAlgParam = cacheParams.getCacheParam(MemCachedConfigConstants.MEMCACHED_CACHE_PARAM_HASHINGALG);
        if (ObjectUtils.isEmpty(hashingAlgParam) || StringUtils.isBlank(hashingAlgParam.getValue())) {
            throw new RuntimeException("请检查flea-cache-config.xml配置,【<cache-param key=" + MemCachedConfigConstants.MEMCACHED_CACHE_PARAM_HASHINGALG + " ></cache-param>】未配置或配置值为空");
        }
        sockIOPool.setHashingAlg(Integer.parseInt(hashingAlgParam.getValue()));
        // 连接池初始化
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

    /**
     * <p> 获取 Memcached 缓存连接池名 </p>
     *
     * @return Memcached 缓存连接池名
     * @since 1.0.0
     */
    public String getPoolName() {
        return poolName;
    }

    /**
     * <p> 获取 Memcached 套接字连接池 </p>
     *
     * @return Memcached 套接字连接池
     * @since 1.0.0
     */
    public SockIOPool getSockIOPool() {
        return sockIOPool;
    }
}
