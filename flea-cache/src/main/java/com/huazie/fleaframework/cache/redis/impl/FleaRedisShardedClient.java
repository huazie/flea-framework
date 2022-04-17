package com.huazie.fleaframework.cache.redis.impl;

import com.huazie.fleaframework.cache.common.CacheConfigUtils;
import com.huazie.fleaframework.cache.redis.FleaRedisClient;
import com.huazie.fleaframework.cache.redis.RedisClient;
import com.huazie.fleaframework.cache.redis.RedisClientCommand;
import com.huazie.fleaframework.cache.redis.RedisShardedPool;
import com.huazie.fleaframework.cache.redis.config.RedisShardedConfig;
import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import redis.clients.jedis.Client;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.params.SetParams;
import redis.clients.jedis.util.SafeEncoder;

/**
 * Flea分片模式Redis客户端实现类，封装了Flea框架操作Redis缓存的基本操作。
 *
 * <p> 它内部具体操作Redis缓存的功能，由分布式Jedis对象完成，
 * 包含读、写、删除Redis缓存的基本操作方法。
 *
 * <p> 分片模式下，单个缓存接入场景，可通过如下方式使用：
 * <pre>
 *  RedisClient redisClient = new FleaRedisShardedClient.Builder().build();
 *  // 执行读，写，删除等基本操作
 *  redisClient.set("key", "value"); </pre>
 *
 * <p> 分片模式下，整合缓存接入场景，可通过如下方式使用：
 * <pre>
 *  RedisClient redisClient = new FleaRedisShardedClient.Builder(poolName).build();
 *  // 执行读，写，删除等基本操作
 *  redisClient.set("key", "value"); </pre>
 *
 * <p> 当然每次都新建Redis客户端显然不可取，我们可通过Redis客户端工厂获取Redis客户端。
 *
 * <p> 分片模式下，单个缓存接入场景，可通过如下方式使用：
 * <pre>
 *  RedisClient redisClient = RedisClientFactory.getInstance();
 *  或者
 *  RedisClient redisClient = RedisClientFactory.getInstance(CacheModeEnum.SHARDED);</pre>
 *
 * <p> 分片模式下，整合缓存接入场景，可通过如下方式使用：
 * <pre>
 *  RedisClient redisClient = RedisClientFactory.getInstance(poolName);
 *  或者
 *  RedisClient redisClient = RedisClientFactory.getInstance(poolName, CacheModeEnum.SHARDED); </pre>
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.0.0
 */
public class FleaRedisShardedClient extends FleaRedisClient {

    private ShardedJedisPool shardedJedisPool; // 分布式Jedis连接池

    private int maxAttempts; // Redis客户端操作最大尝试次数【包含第一次操作】

    /**
     * Redis客户端构造方法 (默认连接池名)
     *
     * @since 1.0.0
     */
    private FleaRedisShardedClient() {
        this(CommonConstants.FleaPoolConstants.DEFAULT_POOL_NAME);
    }

    /**
     * Redis客户端构造方法（指定连接池名）
     *
     * @param poolName 连接池名
     * @since 1.0.0
     */
    private FleaRedisShardedClient(String poolName) {
        super(poolName);
        init();
    }

    /**
     * 初始化分布式Jedis连接池
     *
     * @since 1.0.0
     */
    private void init() {
        if (CommonConstants.FleaPoolConstants.DEFAULT_POOL_NAME.equals(getPoolName())) {
            shardedJedisPool = RedisShardedPool.getInstance().getJedisPool();
            maxAttempts = RedisShardedConfig.getConfig().getMaxAttempts();
        } else {
            shardedJedisPool = RedisShardedPool.getInstance(getPoolName()).getJedisPool();
            maxAttempts = CacheConfigUtils.getMaxAttempts();
        }
    }

    @Override
    public String set(final String key, final Object value) {
        return new RedisClientCommand<String>(this.shardedJedisPool, this.maxAttempts) {
            @Override
            public String execute(ShardedJedis connection) {
                if (value instanceof String)
                    return connection.set(key, (String) value);
                else
                    return connection.set(SafeEncoder.encode(key), ObjectUtils.serialize(value));
            }
        }.run();
    }

    @Override
    public String set(final byte[] key, final byte[] value) {
        return new RedisClientCommand<String>(this.shardedJedisPool, this.maxAttempts) {
            @Override
            public String execute(ShardedJedis connection) {
                return connection.set(key, value);
            }
        }.run();
    }

    @Override
    public String set(final String key, final Object value, final int expiry) {
        return new RedisClientCommand<String>(this.shardedJedisPool, this.maxAttempts) {
            @Override
            public String execute(ShardedJedis connection) {
                if (value instanceof String)
                    return connection.setex(key, expiry, (String) value);
                else
                    return connection.setex(SafeEncoder.encode(key), expiry, ObjectUtils.serialize(value));
            }
        }.run();
    }

    @Override
    public String set(final byte[] key, final byte[] value, final int expiry) {
        return new RedisClientCommand<String>(this.shardedJedisPool, this.maxAttempts) {
            @Override
            public String execute(ShardedJedis connection) {
                return connection.setex(key, expiry, value);
            }
        }.run();
    }

    @Override
    public String set(final String key, final Object value, final long expiry) {
        return new RedisClientCommand<String>(this.shardedJedisPool, this.maxAttempts) {
            @Override
            public String execute(ShardedJedis connection) {
                if (value instanceof String)
                    return connection.psetex(key, expiry, (String) value);
                else
                    return connection.psetex(SafeEncoder.encode(key), expiry, ObjectUtils.serialize(value));
            }
        }.run();
    }

    @Override
    public String set(final byte[] key, final byte[] value, final long expiry) {
        return new RedisClientCommand<String>(this.shardedJedisPool, this.maxAttempts) {
            @Override
            public String execute(ShardedJedis connection) {
                return connection.psetex(key, expiry, value);
            }
        }.run();
    }

    @Override
    public String set(final String key, final Object value, final SetParams params) {
        return new RedisClientCommand<String>(this.shardedJedisPool, this.maxAttempts) {
            @Override
            public String execute(ShardedJedis connection) {
                if (value instanceof String)
                    return connection.set(key, (String) value, params);
                else
                    return connection.set(SafeEncoder.encode(key), ObjectUtils.serialize(value), params);
            }
        }.run();
    }

    @Override
    public String set(final byte[] key, final byte[] value, final SetParams params) {
        return new RedisClientCommand<String>(this.shardedJedisPool, this.maxAttempts) {
            @Override
            public String execute(ShardedJedis connection) {
                return connection.set(key, value, params);
            }
        }.run();
    }

    @Override
    public byte[] get(final byte[] key) {
        return new RedisClientCommand<byte[]>(this.shardedJedisPool, this.maxAttempts) {
            @Override
            public byte[] execute(ShardedJedis connection) {
                return connection.get(key);
            }
        }.run();
    }

    @Override
    public Long del(final String key) {
        return new RedisClientCommand<Long>(this.shardedJedisPool, this.maxAttempts) {
            @Override
            public Long execute(ShardedJedis connection) {
                return connection.del(key);
            }
        }.run();
    }

    /**
     * 获取客户端类
     *
     * @param key 数据键
     * @return 客户端类
     * @since 1.0.0
     */
    @Override
    protected Client getClientByKey(final Object key) {
        return new RedisClientCommand<Client>(this.shardedJedisPool, this.maxAttempts) {
            @Override
            public Client execute(ShardedJedis connection) {
                Client client = null;
                if (ObjectUtils.isNotEmpty(key)) {
                    if (key instanceof String) {
                        client = connection.getShard(key.toString()).getClient();
                    } else if (key instanceof byte[]) {
                        client = connection.getShard((byte[]) key).getClient();
                    }
                }
                return client;
            }
        }.run();
    }

    /**
     * 内部建造者类
     *
     * @author huazie
     * @version 1.1.0
     * @since 1.0.0
     */
    public static class Builder {

        private String poolName; // 连接池名

        /**
         * 默认构造器
         *
         * @since 1.0.0
         */
        public Builder() {
        }

        /**
         * 指定连接池的构造器
         *
         * @param poolName 连接池名
         * @since 1.0.0
         */
        public Builder(String poolName) {
            this.poolName = poolName;
        }

        /**
         * 构建Redis客户端对象
         *
         * @return Redis客户端
         * @since 1.0.0
         */
        public RedisClient build() {
            if (StringUtils.isBlank(poolName)) {
                return new FleaRedisShardedClient();
            } else {
                return new FleaRedisShardedClient(poolName);
            }
        }
    }
}
