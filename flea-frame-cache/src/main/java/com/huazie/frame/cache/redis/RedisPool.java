package com.huazie.frame.cache.redis;

import com.huazie.frame.cache.common.CacheConstants.RedisConfigConstants;
import com.huazie.frame.cache.config.CacheParam;
import com.huazie.frame.cache.config.CacheParams;
import com.huazie.frame.cache.config.CacheServer;
import com.huazie.frame.cache.redis.config.RedisConfig;
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
 * <p>  Flea Redis 连接池 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class RedisPool {

    private static final ConcurrentMap<String, RedisPool> redisPools = new ConcurrentHashMap<String, RedisPool>();

    private String poolName; // 连接池名

    private ShardedJedisPool shardedJedisPool;

    private RedisPool(String poolName) {
        this.poolName = poolName;
    }

    /**
     * <p> 获取Redis连接池实例 (指定连接池名)</p>
     *
     * @param poolName 连接池名
     * @return Redis连接池实例对象
     * @since 1.0.0
     */
    public static RedisPool getInstance(String poolName) {
        if (!redisPools.containsKey(poolName)) {
            synchronized (redisPools) {
                if (!redisPools.containsKey(poolName)) {
                    RedisPool redisPool = new RedisPool(poolName);
                    redisPools.putIfAbsent(poolName, redisPool);
                }
            }
        }
        return redisPools.get(poolName);
    }

    /**
     * <p> 获取Redis连接池实例 (默认) </p>
     *
     * @return Redis连接池实例对象
     * @since 1.0.0
     */
    public static RedisPool getInstance() {
        return getInstance(CommonConstants.FleaPoolConstants.DEFAULT_POOL_NAME);
    }

    /**
     * <p> 默认初始化 </p>
     *
     * @since 1.0.0
     */
    public void initialize() {
        if (!CommonConstants.FleaPoolConstants.DEFAULT_POOL_NAME.equals(poolName)) {
            throw new RuntimeException("采用默认初始化，请使用RedisPool##getInstance()");
        }
        RedisConfig redisConfig = RedisConfig.getConfig();
        if (ObjectUtils.isEmpty(shardedJedisPool)) {
            shardedJedisPool = new ShardedJedisPool(redisConfig.getJedisPoolConfig(), redisConfig.getServers(), redisConfig.getHashingAlg());
        }
    }

    /**
     * <p> 初始化 (非默认连接池) </p>
     *
     * @param cacheServerList 缓存服务器集
     * @param cacheParams     缓存参数
     * @since 1.0.0
     */
    public void initialize(List<CacheServer> cacheServerList, CacheParams cacheParams) {
        if (CommonConstants.FleaPoolConstants.DEFAULT_POOL_NAME.equals(poolName)) {
            throw new RuntimeException("采用指定连接池名初始化，请使用RedisPool##getInstance(String poolName)");
        }

        if (CollectionUtils.isEmpty(cacheServerList)) {
            return;
        }

        if (ObjectUtils.isEmpty(cacheParams) || CollectionUtils.isEmpty(cacheParams.getCacheParamList())) {
            return;
        }

        // 1. 获取客户端连接池配置信息
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // Redis客户端Jedis连接池最大连接数
        CacheParam poolMaxTotal = cacheParams.getCacheParam(RedisConfigConstants.REDIS_CACHE_PARAM_POOL_MAXTOTAL);
        if (ObjectUtils.isEmpty(poolMaxTotal) || StringUtils.isBlank(poolMaxTotal.getValue())) {
            throw new RuntimeException("请检查flea-cache-config.xml配置，【<cache-param key=" + RedisConfigConstants.REDIS_CACHE_PARAM_POOL_MAXTOTAL + " ></cache-param>】未配置或配置值为空");
        }
        poolConfig.setMaxTotal(Integer.parseInt(poolMaxTotal.getValue()));
        // Redis客户端Jedis连接池最大空闲连接数
        CacheParam poolMaxIdle = cacheParams.getCacheParam(RedisConfigConstants.REDIS_CACHE_PARAM_POOL_MAXIDLE);
        if (ObjectUtils.isEmpty(poolMaxIdle) || StringUtils.isBlank(poolMaxIdle.getValue())) {
            throw new RuntimeException("请检查flea-cache-config.xml配置，【<cache-param key=" + RedisConfigConstants.REDIS_CACHE_PARAM_POOL_MAXIDLE + " ></cache-param>】未配置或配置值为空");
        }
        poolConfig.setMaxIdle(Integer.parseInt(poolMaxIdle.getValue()));
        // Redis客户端Jedis连接池最小空闲连接数
        CacheParam poolMinIdle = cacheParams.getCacheParam(RedisConfigConstants.REDIS_CACHE_PARAM_POOL_MINIDLE);
        if (ObjectUtils.isEmpty(poolMinIdle) || StringUtils.isBlank(poolMinIdle.getValue())) {
            throw new RuntimeException("请检查flea-cache-config.xml配置，【<cache-param key=" + RedisConfigConstants.REDIS_CACHE_PARAM_POOL_MINIDLE + " ></cache-param>】未配置或配置值为空");
        }
        poolConfig.setMinIdle(Integer.parseInt(poolMinIdle.getValue()));
        // Redis客户端Jedis连接池获取连接时的最大等待毫秒数
        CacheParam poolMaxWaitMillis = cacheParams.getCacheParam(RedisConfigConstants.REDIS_CACHE_PARAM_POOL_MAXWAITMILLIS);
        if (ObjectUtils.isEmpty(poolMaxWaitMillis) || StringUtils.isBlank(poolMaxWaitMillis.getValue())) {
            throw new RuntimeException("请检查flea-cache-config.xml配置，【<cache-param key=" + RedisConfigConstants.REDIS_CACHE_PARAM_POOL_MAXWAITMILLIS + " ></cache-param>】未配置或配置值为空");
        }
        poolConfig.setMaxWaitMillis(Integer.parseInt(poolMaxWaitMillis.getValue()));

        // 2. 获取服务器配置信息
        // Redis客户端socket连接超时时间
        CacheParam connectionTimeoutParam = cacheParams.getCacheParam(RedisConfigConstants.REDIS_CACHE_PARAM_CONNECTIONTIMEOUT);
        if (ObjectUtils.isEmpty(connectionTimeoutParam) || StringUtils.isBlank(connectionTimeoutParam.getValue())) {
            throw new RuntimeException("请检查flea-cache-config.xml配置，【<cache-param key=" + RedisConfigConstants.REDIS_CACHE_PARAM_CONNECTIONTIMEOUT + " ></cache-param>】未配置或配置值为空");
        }
        int connectionTimeout = Integer.parseInt(connectionTimeoutParam.getValue());
        // Redis客户端socket读写超时时间
        CacheParam soTimeoutParam = cacheParams.getCacheParam(RedisConfigConstants.REDIS_CACHE_PARAM_SOTIMEOUT);
        if (ObjectUtils.isEmpty(soTimeoutParam) || StringUtils.isBlank(soTimeoutParam.getValue())) {
            throw new RuntimeException("请检查flea-cache-config.xml配置，【<cache-param key=" + RedisConfigConstants.REDIS_CACHE_PARAM_SOTIMEOUT + " ></cache-param>】未配置或配置值为空");
        }
        int soTimeout = Integer.parseInt(soTimeoutParam.getValue());
        // 遍历缓存服务器集
        List<JedisShardInfo> servers = new ArrayList<JedisShardInfo>();
        for (CacheServer cacheServer : cacheServerList) {
            if (ObjectUtils.isNotEmpty(cacheServer)) {

                String server = cacheServer.getServer();
                if (StringUtils.isBlank(server)) {
                    throw new RuntimeException("请检查flea-cache-config.xml配置,【<cache-server group=" + poolName + " ></cache-server>】未配置缓存服务器");
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
        CacheParam hashingAlgParam = cacheParams.getCacheParam(RedisConfigConstants.REDIS_CACHE_PARAM_HASHINGALG);
        if (ObjectUtils.isEmpty(hashingAlgParam) || StringUtils.isBlank(hashingAlgParam.getValue())) {
            throw new RuntimeException("请检查flea-cache-config.xml配置，【<cache-param key=" + RedisConfigConstants.REDIS_CACHE_PARAM_HASHINGALG + " ></cache-param>】未配置或配置值为空");
        }
        int alg = Integer.parseInt(hashingAlgParam.getValue());
        Hashing hashingAlg;
        if (RedisConfigConstants.REDIS_CONFIG_HASHINGALG_MURMUR_HASH == alg) {
            hashingAlg = Hashing.MURMUR_HASH;
        } else if (RedisConfigConstants.REDIS_CONFIG_HASHINGALG_MD5 == alg) {
            hashingAlg = Hashing.MD5;
        } else {
            throw new RuntimeException("请检查flea-cache-config.xml配置，【<cache-param key=" + RedisConfigConstants.REDIS_CACHE_PARAM_HASHINGALG + " ></cache-param>】Redis分布式hash算法配置值非法");
        }

        if (ObjectUtils.isEmpty(shardedJedisPool)) {
            shardedJedisPool = new ShardedJedisPool(poolConfig, servers, hashingAlg);
        }

    }

    /**
     * <p> 分布式Redis集群客户端连接池 </p>
     *
     * @return 分布式Redis集群客户端连接池
     * @since 1.0.0
     */
    public ShardedJedisPool getJedisPool() {
        if (ObjectUtils.isEmpty(shardedJedisPool)) {
            throw new RuntimeException("获取分布式Redis集群客户端连接池失败：请先调用initialize初始化");
        }
        return shardedJedisPool;
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
}
