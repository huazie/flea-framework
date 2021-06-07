package com.huazie.frame.cache.common;

/**
 * Flea缓存常量类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class CacheConstants {

    /**
     * <p> Flea Cache配置数据常量 </p>
     *
     * @since 1.0.0
     */
    public interface FleaCacheConfigConstants {
        /**
         * <p> Flea Cache 默认文件路径 </p>
         */
        String FLEA_CACHE_FILE_NAME = "flea/cache/flea-cache.xml";
        /**
         * <p> Flea Cache 系统环境变量 </p>
         */
        String FLEA_CACHE_FILE_SYSTEM_KEY = "fleaframe.flea.cache.filename";
        /**
         * <p> Flea Cache Config 默认文件路径 </p>
         */
        String FLEA_CACHE_CONFIG_FILE_NAME = "flea/cache/flea-cache-config.xml";
        /**
         * <p> Flea Cache Config 系统环境变量 </p>
         */
        String FLEA_CACHE_CONFIG_FILE_SYSTEM_KEY = "fleaframe.cache.fleacache.config.filename";
        /**
         * <p> Flea缓存建造者配置项集 </p>
         */
        String FLEA_CACHE_BUILDER = "FleaCacheBuilder";
        /**
         * <p> Flea缓存初始化配置项集 </p>
         */
        String FLEA_CACHE_INIT = "FleaCacheInit";
        /**
         * <p> Flea缓存所属系统名 </p>
         */
        String SYSTEM_NAME = "systemName";
        /**
         * <p> 空缓存数据有效期 </p>
         */
        String NULL_CACHE_EXPIRY = "fleacore.nullCacheExpiry";
        /**
         * <p> 默认有效期（单位：s）</p>
         */
        int DEFAULT_EXPIRY = 300;
    }

    /**
     * <p> MemCached配置数据常量 </p>
     *
     * @since 1.0.0
     */
    public interface MemCachedConfigConstants {
        /**
         * <p> MemCached 配置文件名 </p>
         */
        String MEMCACHED_FILE_NAME = "flea/cache/memcached.properties";
        /**
         * <p> MemCached 配置文件系统环境变量 </p>
         */
        String MEMCACHED_CONFIG_FILE_SYSTEM_KEY = "fleaframe.cache.memcached.config.filename";
        /**
         * <p> MemCached 缓存所属系统名 </p>
         */
        String MEMCACHED_CONFIG_SYSTEM_NAME = "memcached.systemName";
        /**
         * <p> MemCached 服务器地址 </p>
         */
        String MEMCACHED_CONFIG_SERVER = "memcached.server";
        /**
         * <p> MemCached 服务器权重分配 </p>
         */
        String MEMCACHED_CONFIG_WEIGHT = "memcached.weight";
        /**
         * <p> 初始化时对每个服务器建立的连接数目 </p>
         */
        String MEMCACHED_CONFIG_INITCONN = "memcached.initConn";
        /**
         * <p> 每个服务器建立最小的连接数 </p>
         */
        String MEMCACHED_CONFIG_MINCONN = "memcached.minConn";
        /**
         * <p> 每个服务器建立最大的连接数 </p>
         */
        String MEMCACHED_CONFIG_MAXCONN = "memcached.maxConn";
        /**
         * <p> 自查线程周期进行工作，其每次休眠时间（单位：ms）</p>
         */
        String MEMCACHED_CONFIG_MAINTSLEEP = "memcached.maintSleep";
        /**
         * <p> Socket的参数，如果是true在写数据时不缓冲，立即发送出去 </p>
         */
        String MEMCACHED_CONFIG_NAGLE = "memcached.nagle";
        /**
         * <p> Socket阻塞读取数据的超时时间（单位：ms） </p>
         */
        String MEMCACHED_CONFIG_SOCKETTO = "memcached.socketTO";
        /**
         * <p> Socket连接超时时间（单位：ms） </p>
         */
        String MEMCACHED_CONFIG_SOCKETCONNECTTO = "memcached.socketConnectTO";
        /**
         * <p> MemCached 一致性hash算法 </p>
         */
        String MEMCACHED_CONFIG_HASHINGALG = "memcached.hashingAlg";
        /**
         * <p> MemCached 空缓存数据有效期（单位：s） </p>
         */
        String MEMCACHED_CONFIG_NULLCACHEEXPIRY = "memcached.nullCacheExpiry";
    }

    /**
     * <p> Redis配置数据常量 </p>
     *
     * @since 1.0.0
     */
    public interface RedisConfigConstants {
        /**
         * <p> Redis 配置文件名 </p>
         */
        String REDIS_FILE_NAME = "flea/cache/redis.properties";
        /**
         * <p> Redis 配置文件系统环境变量 </p>
         */
        String REDIS_CONFIG_FILE_SYSTEM_KEY = "fleaframe.cache.redis.config.filename";
        /**
         * <p> Redis 缓存所属系统名 </p>
         */
        String REDIS_CONFIG_SYSTEM_NAME = "redis.systemName";
        /**
         * <p> Redis 服务器地址 </p>
         */
        String REDIS_CONFIG_SERVER = "redis.server";
        /**
         * <p> Redis 授权密码 </p>
         */
        String REDIS_CONFIG_PASSWORD = "redis.password";
        /**
         * <p> Redis 服务器权重配置 </p>
         */
        String REDIS_CONFIG_WEIGHT = "redis.weight";
        /**
         * <p> Redis 客户端socket连接超时时间 </p>
         */
        String REDIS_CONFIG_CONNECTIONTIMEOUT = "redis.connectionTimeout";
        /**
         * <p> Redis 客户端socket连接超时时间 </p>
         */
        String REDIS_CONFIG_SOTIMEOUT = "redis.soTimeout";
        /**
         * <p> Redis 分布式hash算法 </p>
         * <p> 1 : MURMUR_HASH </p>
         * <p> 2 : MD5 </p>
         */
        String REDIS_CONFIG_HASHINGALG = "redis.hashingAlg";
        /**
         * <p> MURMUR_HASH 分布式hash算法 </p>
         */
        int REDIS_CONFIG_HASHINGALG_MURMUR_HASH = 1;
        /**
         * <p> MD5 分布式hash算法 </p>
         */
        int REDIS_CONFIG_HASHINGALG_MD5 = 2;
        /**
         * <p> Redis客户端Jedis连接池最大连接数 </p>
         */
        String REDIS_CONFIG_POOL_MAXTOTAL = "redis.pool.maxTotal";
        /**
         * <p> Redis客户端Jedis连接池最大空闲连接数 </p>
         */
        String REDIS_CONFIG_POOL_MAXIDLE = "redis.pool.maxIdle";
        /**
         * <p> Redis客户端Jedis连接池最小空闲连接数 </p>
         */
        String REDIS_CONFIG_POOL_MINIDLE = "redis.pool.minIdle";
        /**
         * <p> Redis客户端Jedis连接池获取连接时的最大等待毫秒数 </p>
         */
        String REDIS_CONFIG_POOL_MAXWAITMILLIS = "redis.pool.maxWaitMillis";
        /**
         * <p> Redis 空缓存数据有效期（单位：s） </p>
         */
        String REDIS_CONFIG_NULLCACHEEXPIRY = "redis.nullCacheExpiry";
    }

}
