package com.huazie.frame.cache.redis.impl;

import com.huazie.frame.cache.redis.RedisClient;
import com.huazie.frame.cache.redis.RedisPool;
import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.util.ArrayUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import redis.clients.jedis.Client;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.params.SetParams;
import redis.clients.jedis.util.SafeEncoder;

/**
 * Flea Redis客户端实现类，封装了Flea框架操作Redis的基本操作。
 *
 * <p> 它内部具体操作Redis缓存的功能，由分布式Jedis对象完成，
 * 包含读、写、删除Redis缓存的基本操作方法。
 *
 * <p> 单个缓存接入场景，可通过如下方式使用：
 * <pre>
 *  RedisClient redisClient = new FleaRedisClient.Builder().build();
 *  ShardedJedisPool jedisPool = redisClient.getJedisPool();
 *  redisClient.setShardedJedis(jedisPool.getResource());
 *  // 执行读，写，删除等基本操作
 *  redisClient.set("key", "value");
 *  ShardedJedis shardedJedis = redisClient.getShardedJedis();
 *  shardedJedis.close(); </pre>
 *
 * <p> 整合缓存接入场景，可通过如下方式使用：
 * <pre>
 *  RedisClient redisClient = new FleaRedisClient.Builder(poolName).build();
 *  ShardedJedisPool jedisPool = redisClient.getJedisPool();
 *  redisClient.setShardedJedis(jedisPool.getResource());
 *  // 执行读，写，删除等基本操作
 *  redisClient.set("key", "value");
 *  ShardedJedis shardedJedis = redisClient.getShardedJedis();
 *  shardedJedis.close(); </pre>
 *
 * <p> 这里直接去新建Flea Redis客户端实现类并不推荐。因为在实际
 * 使用操作Redis缓存的分布式Jedis对象时，需要先从分布式Jedis连接
 * 池中获取，然后操作Redis缓存结束后又需要归还给连接池。我们可通过
 * Redis客户端代理来获取代理的Redis客户端
 *
 * <p> 单个缓存接入场景，可通过如下方式使用：
 * <pre>
 *  RedisClient redisClient = RedisClientProxy.getProxyInstance(); </pre>
 *
 * <p> 整合缓存接入场景，可通过如下方式使用：
 * <pre>
 *  RedisClient redisClient = RedisClientProxy.getProxyInstance(poolName); </pre>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaRedisClient implements RedisClient {

    private ShardedJedisPool shardedJedisPool; // 分布式Jedis连接池

    private ShardedJedis shardedJedis; // 分布式Jedis对象

    private String poolName; // 连接池名

    /**
     * <p> Redis客户端构造方法 (默认) </p>
     *
     * @since 1.0.0
     */
    private FleaRedisClient() {
        this(null);
    }

    /**
     * <p> Redis客户端构造方法（指定连接池名） </p>
     *
     * @param poolName 连接池名
     * @since 1.0.0
     */
    private FleaRedisClient(String poolName) {
        this.poolName = poolName;
        init();
    }

    /**
     * <p> 初始化分布式Jedis连接池 </p>
     *
     * @since 1.0.0
     */
    private void init() {
        if (StringUtils.isBlank(poolName)) {
            poolName = CommonConstants.FleaPoolConstants.DEFAULT_POOL_NAME;
            shardedJedisPool = RedisPool.getInstance().getJedisPool();
        } else {
            shardedJedisPool = RedisPool.getInstance(poolName).getJedisPool();
        }

    }

    @Override
    public String set(final String key, final Object value) {
        if (value instanceof String)
            return shardedJedis.set(key, (String) value);
        else
            return shardedJedis.set(SafeEncoder.encode(key), ObjectUtils.serialize(value));
    }

    @Override
    public String set(byte[] key, byte[] value) {
        return shardedJedis.set(key, value);
    }

    @Override
    public String set(final String key, final Object value, final int expiry) {
        if (value instanceof String)
            return shardedJedis.setex(key, expiry, (String) value);
        else
            return shardedJedis.setex(SafeEncoder.encode(key), expiry, ObjectUtils.serialize(value));
    }

    @Override
    public String set(byte[] key, byte[] value, int expiry) {
        return shardedJedis.setex(key, expiry, value);
    }

    @Override
    public String set(String key, Object value, long expiry) {
        if (value instanceof String)
            return shardedJedis.psetex(key, expiry, (String) value);
        else
            return shardedJedis.psetex(SafeEncoder.encode(key), expiry, ObjectUtils.serialize(value));
    }

    @Override
    public String set(byte[] key, byte[] value, long expiry) {
        return shardedJedis.psetex(key, expiry, value);
    }

    @Override
    public String set(final String key, final Object value, final SetParams params) {
        if (value instanceof String)
            return shardedJedis.set(key, (String) value, params);
        else
            return shardedJedis.set(SafeEncoder.encode(key), ObjectUtils.serialize(value), params);
    }

    @Override
    public String set(byte[] key, byte[] value, SetParams params) {
        return shardedJedis.set(key, value, params);
    }

    @Override
    public Object get(final String key) {
        byte[] value = get(SafeEncoder.encode(key));
        Object object = ObjectUtils.deserialize(value);
        if (ObjectUtils.isEmpty(object) && ArrayUtils.isNotEmpty(value))
            return SafeEncoder.encode(value);
        else
            return object;
    }

    @Override
    public byte[] get(byte[] key) {
        return shardedJedis.get(key);
    }

    @Override
    public Long del(final String key) {
        return shardedJedis.del(key);
    }

    @Override
    public String getLocation(final String key) {
        return getLocationByKey(key);
    }

    @Override
    public String getLocation(byte[] key) {
        return getLocationByKey(key);
    }

    @Override
    public String getHost(final String key) {
        return getHostByKey(key);
    }

    @Override
    public String getHost(byte[] key) {
        return getHostByKey(key);
    }

    @Override
    public Integer getPort(final String key) {
        return getPortByKey(key);
    }

    @Override
    public Integer getPort(byte[] key) {
        return getPortByKey(key);
    }

    @Override
    public Client getClient(String key) {
        return getClientByKey(key);
    }

    @Override
    public Client getClient(byte[] key) {
        return getClientByKey(key);
    }

    /**
     * <p> 获取数据所在的Redis服务器ip(主机地址+端口) </p>
     *
     * @param key 数据键
     * @return 当前数据所在的Redis服务器ip
     * @since 1.0.0
     */
    private String getLocationByKey(Object key) {
        StringBuilder location = new StringBuilder();
        Client client = getClientByKey(key);
        if (ObjectUtils.isNotEmpty(client)) {
            location.append(client.getHost()).append(CommonConstants.SymbolConstants.COLON).append(client.getPort());
        }
        return location.toString();
    }

    /**
     * <p> 获取数据所在的Redis服务器主机 </p>
     *
     * @param key 数据键
     * @return 数据所在的Redis服务器主机
     * @since 1.0.0
     */
    private String getHostByKey(Object key) {
        Client client = getClientByKey(key);
        if (ObjectUtils.isNotEmpty(client)) {
            return client.getHost();
        }
        return null;
    }

    /**
     * <p> 获取数据所在的Redis服务器主机端口 </p>
     *
     * @param key 数据键
     * @return 数据所在的Redis服务器主机端口
     * @since 1.0.0
     */
    private Integer getPortByKey(Object key) {
        Client client = getClientByKey(key);
        if (ObjectUtils.isNotEmpty(client)) {
            return client.getPort();
        }
        return null;
    }

    /**
     * <p> 获取客户端类 </p>
     *
     * @param key 数据键
     * @return 客户端类
     * @since 1.0.0
     */
    private Client getClientByKey(Object key) {
        Client client = null;
        if (ObjectUtils.isNotEmpty(key)) {
            if (key instanceof String) {
                client = shardedJedis.getShard(key.toString()).getClient();
            } else if (key instanceof byte[]) {
                client = shardedJedis.getShard((byte[]) key).getClient();
            }
        }
        return client;
    }

    @Override
    public ShardedJedisPool getJedisPool() {
        return shardedJedisPool;
    }

    @Override
    public void setShardedJedis(ShardedJedis shardedJedis) {
        this.shardedJedis = shardedJedis;
    }

    @Override
    public ShardedJedis getShardedJedis() {
        return shardedJedis;
    }

    @Override
    public String getPoolName() {
        return poolName;
    }

    @Override
    public void setPoolName(String poolName) {
        this.poolName = poolName;
        init();
    }

    /**
     * <p> 内部建造者类 </p>
     *
     * @author huazie
     * @since 1.0.0
     */
    static class Builder {

        private String poolName; // 连接池名

        /**
         * <p> 默认构造器 </p>
         *
         * @since 1.0.0
         */
        Builder() {
        }

        /**
         * <p> 指定连接池的构造器 </p>
         *
         * @param poolName 连接池名
         * @since 1.0.0
         */
        Builder(String poolName) {
            this.poolName = poolName;
        }

        /**
         * <p> 构建Redis客户端对象 </p>
         *
         * @return Redis客户端
         * @since 1.0.0
         */
        RedisClient build() {
            if (StringUtils.isBlank(poolName)) {
                return new FleaRedisClient();
            } else {
                return new FleaRedisClient(poolName);
            }
        }
    }
}
