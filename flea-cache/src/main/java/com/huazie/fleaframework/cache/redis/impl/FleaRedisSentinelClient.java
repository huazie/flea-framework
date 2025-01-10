package com.huazie.fleaframework.cache.redis.impl;

import com.huazie.fleaframework.cache.common.CacheConfigUtils;
import com.huazie.fleaframework.cache.redis.FleaRedisClient;
import com.huazie.fleaframework.cache.redis.RedisClient;
import com.huazie.fleaframework.cache.redis.RedisClientCommand;
import com.huazie.fleaframework.cache.redis.RedisSentinelPool;
import com.huazie.fleaframework.cache.redis.config.RedisSentinelConfig;
import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.params.SetParams;
import redis.clients.jedis.util.SafeEncoder;

/**
 * Flea哨兵模式Redis客户端实现，封装了Flea框架操作Redis缓存的基本操作。
 *
 * <p> 它内部具体操作Redis缓存的功能，由Jedis哨兵连接池获取Jedis实例对象完成，
 * 包含读、写、删除Redis缓存的基本操作方法。
 *
 * <p> 哨兵模式下，单个缓存接入场景，可通过如下方式使用：
 * <pre>
 * RedisClient redisClient = new FleaRedisSentinelClient.Builder().build();
 * // 执行读，写，删除等基本操作
 * redisClient.set("key", "value"); </pre>
 *
 * <p> 哨兵模式下，整合缓存接入场景，可通过如下方式使用：
 * <pre>
 * RedisClient redisClient = new FleaRedisSentinelClient.Builder(poolName).build();
 * // 执行读，写，删除等基本操作
 * redisClient.set("key", "value"); </pre>
 *
 * <p> 当然每次都新建Redis客户端显然不可取，我们可通过Redis客户端工厂获取Redis客户端。
 * <p> 哨兵模式下，单个缓存接入场景，可通过如下方式使用：
 * <pre>
 * RedisClient redisClient = RedisClientFactory.getInstance(CacheModeEnum.SENTINEL);</pre>
 * <p> 哨兵模式下，整合缓存接入场景，可通过如下方式使用：
 * <pre>
 * RedisClient redisClient = RedisClientFactory.getInstance(poolName, CacheModeEnum.SENTINEL); </pre>
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaRedisSentinelClient extends FleaRedisClient {

    private JedisSentinelPool jedisSentinelPool;

    private int maxAttempts;

    /**
     * Redis哨兵客户端构造方法 (默认)
     *
     * @since 2.0.0
     */
    private FleaRedisSentinelClient() {
        this(CommonConstants.FleaPoolConstants.DEFAULT_POOL_NAME);
    }

    /**
     * Redis哨兵客户端构造方法（指定连接池名）
     *
     * @param poolName 连接池名
     * @since 2.0.0
     */
    private FleaRedisSentinelClient(String poolName) {
        super(poolName);
        init();
    }

    /**
     * 初始化Jedis哨兵实例
     *
     * @since 2.0.0
     */
    private void init() {
        if (CommonConstants.FleaPoolConstants.DEFAULT_POOL_NAME.equals(getPoolName())) {
            jedisSentinelPool = RedisSentinelPool.getInstance().getJedisSentinelPool();
            maxAttempts = RedisSentinelConfig.getConfig().getMaxAttempts();
        } else {
            jedisSentinelPool = RedisSentinelPool.getInstance(getPoolName()).getJedisSentinelPool();
            maxAttempts = CacheConfigUtils.getMaxAttempts();
        }
    }

    @Override
    public String set(final String key, final Object value) {
        return new RedisClientCommand<String, JedisSentinelPool, Jedis>(this.jedisSentinelPool, this.maxAttempts) {
            @Override
            public String execute(Jedis connection) {
                if (value instanceof String)
                    return connection.set(key, (String) value);
                else
                    return connection.set(SafeEncoder.encode(key), ObjectUtils.serialize(value));
            }
        }.run();
    }

    @Override
    public String set(final byte[] key, final byte[] value) {
        return new RedisClientCommand<String, JedisSentinelPool, Jedis>(this.jedisSentinelPool, this.maxAttempts) {
            @Override
            public String execute(Jedis connection) {
                return connection.set(key, value);
            }
        }.run();
    }

    @Override
    public String set(final String key, final Object value, final int expiry) {
        return new RedisClientCommand<String, JedisSentinelPool, Jedis>(this.jedisSentinelPool, this.maxAttempts) {
            @Override
            public String execute(Jedis connection) {
                if (value instanceof String)
                    return connection.setex(key, expiry, (String) value);
                else
                    return connection.setex(SafeEncoder.encode(key), expiry, ObjectUtils.serialize(value));
            }
        }.run();
    }

    @Override
    public String set(final byte[] key, final byte[] value, final int expiry) {
        return new RedisClientCommand<String, JedisSentinelPool, Jedis>(this.jedisSentinelPool, this.maxAttempts) {
            @Override
            public String execute(Jedis connection) {
                return connection.setex(key, expiry, value);
            }
        }.run();
    }

    @Override
    public String set(final String key, final Object value, final long expiry) {
        return new RedisClientCommand<String, JedisSentinelPool, Jedis>(this.jedisSentinelPool, this.maxAttempts) {
            @Override
            public String execute(Jedis connection) {
                if (value instanceof String)
                    return connection.psetex(key, expiry, (String) value);
                else
                    return connection.psetex(SafeEncoder.encode(key), expiry, ObjectUtils.serialize(value));
            }
        }.run();
    }

    @Override
    public String set(final byte[] key, final byte[] value, final long expiry) {
        return new RedisClientCommand<String, JedisSentinelPool, Jedis>(this.jedisSentinelPool, this.maxAttempts) {
            @Override
            public String execute(Jedis connection) {
                return connection.psetex(key, expiry, value);
            }
        }.run();
    }

    @Override
    public String set(final String key, final Object value, final SetParams params) {
        return new RedisClientCommand<String, JedisSentinelPool, Jedis>(this.jedisSentinelPool, this.maxAttempts) {
            @Override
            public String execute(Jedis connection) {
                if (value instanceof String)
                    return connection.set(key, (String) value, params);
                else
                    return connection.set(SafeEncoder.encode(key), ObjectUtils.serialize(value), params);
            }
        }.run();
    }

    @Override
    public String set(final byte[] key, final byte[] value, final SetParams params) {
        return new RedisClientCommand<String, JedisSentinelPool, Jedis>(this.jedisSentinelPool, this.maxAttempts) {
            @Override
            public String execute(Jedis connection) {
                return connection.set(key, value, params);
            }
        }.run();
    }

    @Override
    public byte[] get(final byte[] key) {
        return new RedisClientCommand<byte[], JedisSentinelPool, Jedis>(this.jedisSentinelPool, this.maxAttempts) {
            @Override
            public byte[] execute(Jedis connection) {
                return connection.get(key);
            }
        }.run();
    }

    @Override
    public Long del(final String key) {
        return new RedisClientCommand<Long, JedisSentinelPool, Jedis>(this.jedisSentinelPool, this.maxAttempts) {
            @Override
            public Long execute(Jedis connection) {
                return connection.del(key);
            }
        }.run();
    }

    /**
     * 获取客户端类
     *
     * @param key 缓存实际存储的键
     * @return 客户端类
     * @since 2.0.0
     */
    @Override
    protected Client getClientByKey(Object key) {
        return new RedisClientCommand<Client, JedisSentinelPool, Jedis>(this.jedisSentinelPool, this.maxAttempts) {
            @Override
            public Client execute(Jedis connection) {
                return connection.getClient();
            }
        }.run();
    }

    /**
     * 内部建造者类
     *
     * @author huazie
     * @version 2.0.0
     * @since 2.0.0
     */
    public static class Builder {

        private String poolName; // 连接池名

        /**
         * 默认构造器
         *
         * @since 2.0.0
         */
        public Builder() {
        }

        /**
         * 指定连接池的构造器
         *
         * @param poolName 连接池名
         * @since 2.0.0
         */
        public Builder(String poolName) {
            this.poolName = poolName;
        }

        /**
         * 构建Redis哨兵客户端对象
         *
         * @return Redis哨兵客户端
         * @since 2.0.0
         */
        public RedisClient build() {
            if (StringUtils.isBlank(poolName)) {
                return new FleaRedisSentinelClient();
            } else {
                return new FleaRedisSentinelClient(poolName);
            }
        }
    }
}
