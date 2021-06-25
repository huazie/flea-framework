package com.huazie.frame.cache.redis;

import com.huazie.frame.cache.common.CacheConfigUtils;
import com.huazie.frame.cache.common.CacheConstants.RedisConfigConstants;
import com.huazie.frame.cache.config.CacheParam;
import com.huazie.frame.cache.config.CacheServer;
import com.huazie.frame.cache.exceptions.FleaCacheConfigException;
import com.huazie.frame.cache.redis.config.RedisShardedConfig;
import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.util.Hashing;
import redis.clients.jedis.util.Sharded;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Flea Redis连接池，用于初始化分布式 Jedis 连接池。
 *
 * <p> 针对单独缓存接入场景，采用默认连接池初始化的方式；<br/>
 * 可参考如下：
 * <pre>
 *   // 初始化默认连接池
 *   RedisShardedPool.getInstance().initialize(); </pre>
 *
 * <p> 针对整合缓存接入场景，采用指定连接池初始化的方式；<br/>
 * 可参考如下：
 * <pre>
 *   // 初始化指定连接池
 *   RedisShardedPool.getInstance(group).initialize(cacheServerList); </pre>
 *
 * @author huazie
 * @version 1.1.0
 * @see RedisShardedFleaCacheManager
 * @see RedisShardedFleaCacheBuilder
 * @since 1.0.0
 */
public class RedisShardedPool {

    private static final ConcurrentMap<String, RedisShardedPool> redisPools = new ConcurrentHashMap<>();

    private String poolName; // 连接池名

    private ShardedJedisPool shardedJedisPool; // 分布式Jedis连接池

    private RedisShardedPool(String poolName) {
        this.poolName = poolName;
    }

    /**
     * <p> 获取Redis连接池实例 (默认连接池) </p>
     *
     * @return Redis连接池实例对象
     * @since 1.0.0
     */
    public static RedisShardedPool getInstance() {
        return getInstance(CommonConstants.FleaPoolConstants.DEFAULT_POOL_NAME);
    }

    /**
     * <p> 获取Redis连接池实例 (指定连接池名) </p>
     *
     * @param poolName 连接池名
     * @return Redis连接池实例对象
     * @since 1.0.0
     */
    public static RedisShardedPool getInstance(String poolName) {
        if (!redisPools.containsKey(poolName)) {
            synchronized (redisPools) {
                if (!redisPools.containsKey(poolName)) {
                    RedisShardedPool redisShardedPool = new RedisShardedPool(poolName);
                    redisPools.putIfAbsent(poolName, redisShardedPool);
                }
            }
        }
        return redisPools.get(poolName);
    }

    /**
     * <p> 默认初始化 </p>
     *
     * @since 1.0.0
     */
    void initialize() {
        if (!CommonConstants.FleaPoolConstants.DEFAULT_POOL_NAME.equals(poolName)) {
            throw new FleaCacheConfigException("采用默认初始化，请使用RedisPool##getInstance()");
        }
        RedisShardedConfig redisShardedConfig = RedisShardedConfig.getConfig();
        if (ObjectUtils.isEmpty(shardedJedisPool)) {
            shardedJedisPool = new ShardedJedisPool(redisShardedConfig.getJedisPoolConfig(), redisShardedConfig.getServerInfos(), redisShardedConfig.getHashingAlg());
        }
    }

    /**
     * <p> 初始化 (非默认连接池) </p>
     *
     * @param cacheServerList 缓存服务器集
     * @since 1.0.0
     */
    void initialize(List<CacheServer> cacheServerList) {
        if (CommonConstants.FleaPoolConstants.DEFAULT_POOL_NAME.equals(poolName)) {
            throw new FleaCacheConfigException("采用指定连接池名初始化，请使用RedisPool##getInstance(String poolName)");
        }

        if (CollectionUtils.isEmpty(cacheServerList)) {
            return;
        }

        // 1. 获取Jedis连接池配置信息
        JedisPoolConfig poolConfig = CacheConfigUtils.getJedisPoolConfig();

        // 2. 获取服务器配置信息
        // Redis客户端socket连接超时时间
        CacheParam connectionTimeoutParam = CacheConfigUtils.getCacheParam(RedisConfigConstants.REDIS_CONFIG_CONNECTIONTIMEOUT);
        if (ObjectUtils.isEmpty(connectionTimeoutParam) || StringUtils.isBlank(connectionTimeoutParam.getValue())) {
            throw new FleaCacheConfigException("请检查flea-cache-config.xml配置，【<cache-param key=" + RedisConfigConstants.REDIS_CONFIG_CONNECTIONTIMEOUT + " ></cache-param>】未配置或配置值为空");
        }
        int connectionTimeout = Integer.parseInt(connectionTimeoutParam.getValue());
        // Redis客户端socket读写超时时间
        CacheParam soTimeoutParam = CacheConfigUtils.getCacheParam(RedisConfigConstants.REDIS_CONFIG_SOTIMEOUT);
        if (ObjectUtils.isEmpty(soTimeoutParam) || StringUtils.isBlank(soTimeoutParam.getValue())) {
            throw new FleaCacheConfigException("请检查flea-cache-config.xml配置，【<cache-param key=" + RedisConfigConstants.REDIS_CONFIG_SOTIMEOUT + " ></cache-param>】未配置或配置值为空");
        }
        int soTimeout = Integer.parseInt(soTimeoutParam.getValue());
        // 遍历缓存服务器集
        List<JedisShardInfo> servers = new ArrayList<>();
        for (CacheServer cacheServer : cacheServerList) {
            if (ObjectUtils.isNotEmpty(cacheServer)) {

                String server = cacheServer.getServer();
                if (StringUtils.isBlank(server)) {
                    throw new FleaCacheConfigException("请检查flea-cache-config.xml配置,【<cache-server group=" + poolName + " ></cache-server>】未配置缓存服务器");
                }
                String host = StringUtils.subStrBefore(server, server.indexOf(CommonConstants.SymbolConstants.COLON));
                int port = Integer.parseInt(StringUtils.subStrLast(server, server.length() - server.indexOf(CommonConstants.SymbolConstants.COLON) - 1));

                String weightStr = cacheServer.getWeight();
                int weight;
                if (StringUtils.isBlank(weightStr)) {
                    weight = Sharded.DEFAULT_WEIGHT;
                } else {
                    weight = Integer.parseInt(weightStr);
                }

                JedisShardInfo jedisShardInfo = new JedisShardInfo(host, port, connectionTimeout, soTimeout, weight);

                String password = cacheServer.getPassword();
                if (ObjectUtils.isNotEmpty(password)) {
                    jedisShardInfo.setPassword(password);
                }
                servers.add(jedisShardInfo);
            }
        }

        // 3. 获取Redis分布式hash算法
        CacheParam hashingAlgParam = CacheConfigUtils.getCacheParam(RedisConfigConstants.REDIS_CONFIG_HASHINGALG);
        if (ObjectUtils.isEmpty(hashingAlgParam) || StringUtils.isBlank(hashingAlgParam.getValue())) {
            throw new FleaCacheConfigException("请检查flea-cache-config.xml配置，【<cache-param key=" + RedisConfigConstants.REDIS_CONFIG_HASHINGALG + " ></cache-param>】未配置或配置值为空");
        }
        int alg = Integer.parseInt(hashingAlgParam.getValue());
        Hashing hashingAlg;
        if (RedisConfigConstants.REDIS_CONFIG_HASHINGALG_MURMUR_HASH == alg) {
            hashingAlg = Hashing.MURMUR_HASH;
        } else if (RedisConfigConstants.REDIS_CONFIG_HASHINGALG_MD5 == alg) {
            hashingAlg = Hashing.MD5;
        } else {
            throw new FleaCacheConfigException("请检查flea-cache-config.xml配置，【<cache-param key=" + RedisConfigConstants.REDIS_CONFIG_HASHINGALG + " ></cache-param>】Redis分布式hash算法配置值非法");
        }

        if (ObjectUtils.isEmpty(shardedJedisPool)) {
            shardedJedisPool = new ShardedJedisPool(poolConfig, servers, hashingAlg);
        }

    }

    /**
     * <p> 获取当前连接池名 </p>
     *
     * @return 连接池名
     * @since 1.0.0
     */
    public String getPoolName() {
        return poolName;
    }

    /**
     * <p> 分布式Jedis连接池 </p>
     *
     * @return 分布式Jedis连接池
     * @since 1.0.0
     */
    public ShardedJedisPool getJedisPool() {
        if (ObjectUtils.isEmpty(shardedJedisPool)) {
            throw new FleaCacheConfigException("获取分布式Jedis连接池失败：请先调用initialize初始化");
        }
        return shardedJedisPool;
    }

}