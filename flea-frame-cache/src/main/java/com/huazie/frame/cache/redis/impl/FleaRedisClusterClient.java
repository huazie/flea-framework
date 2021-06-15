package com.huazie.frame.cache.redis.impl;

import com.huazie.frame.cache.redis.FleaRedisClient;
import com.huazie.frame.cache.redis.RedisClient;
import com.huazie.frame.cache.redis.RedisClusterPool;
import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import redis.clients.jedis.Client;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.params.SetParams;
import redis.clients.jedis.util.JedisClusterCRC16;
import redis.clients.jedis.util.SafeEncoder;

/**
 * Flea Redis集群客户端实现，封装了Flea框架操作Redis缓存的基本操作。
 *
 * <p> 它内部具体操作Redis集群缓存的功能，由Jedis集群实例对象完成，
 * 包含读、写、删除Redis缓存的基本操作方法。
 *
 * <p> 集群模式下，单个缓存接入场景，可通过如下方式使用：
 * <pre>
 * RedisClient redisClient = new FleaRedisClusterClient.Builder().build();
 * // 执行读，写，删除等基本操作
 * redisClient.set("key", "value"); </pre>
 *
 * <p> 集群模式下，整合缓存接入场景，可通过如下方式使用：
 * <pre>
 * RedisClient redisClient = new FleaRedisClusterClient.Builder(poolName).build();
 * // 执行读，写，删除等基本操作
 * redisClient.set("key", "value"); </pre>
 *
 * <p> 当然每次都新建Redis客户端显然不可取，我们可通过Redis客户端工厂获取Redis客户端。
 * <p> 集群模式下，单个缓存接入场景，可通过如下方式使用：
 * <pre>
 * RedisClient redisClient = RedisClientFactory.getInstance(CacheModeEnum.CLUSTER);</pre>
 * <p> 集群模式下，整合缓存接入场景，可通过如下方式使用：
 * <pre>
 * RedisClient redisClient = RedisClientFactory.getInstance(poolName, CacheModeEnum.CLUSTER); </pre>
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class FleaRedisClusterClient extends FleaRedisClient {

    private JedisCluster jedisCluster;

    /**
     * <p> Redis集群客户端构造方法 (默认) </p>
     *
     * @since 1.1.0
     */
    private FleaRedisClusterClient() {
        this(CommonConstants.FleaPoolConstants.DEFAULT_POOL_NAME);
    }

    /**
     * <p> Redis集群客户端构造方法（指定连接池名） </p>
     *
     * @param poolName 连接池名
     * @since 1.1.0
     */
    private FleaRedisClusterClient(String poolName) {
        super(poolName);
        init();
    }

    /**
     * <p> 初始化Jedis集群实例 </p>
     *
     * @since 1.1.0
     */
    private void init() {
        if (CommonConstants.FleaPoolConstants.DEFAULT_POOL_NAME.equals(getPoolName())) {
            jedisCluster = RedisClusterPool.getInstance().getJedisCluster();
        } else {
            jedisCluster = RedisClusterPool.getInstance(getPoolName()).getJedisCluster();
        }

    }

    @Override
    public String set(String key, Object value) {
        if (value instanceof String)
            return jedisCluster.set(key, (String) value);
        else
            return jedisCluster.set(SafeEncoder.encode(key), ObjectUtils.serialize(value));
    }

    @Override
    public String set(byte[] key, byte[] value) {
        return jedisCluster.set(key, value);
    }

    @Override
    public String set(final String key, final Object value, final int expiry) {
        if (value instanceof String)
            return jedisCluster.setex(key, expiry, (String) value);
        else
            return jedisCluster.setex(SafeEncoder.encode(key), expiry, ObjectUtils.serialize(value));
    }

    @Override
    public String set(byte[] key, byte[] value, int expiry) {
        return jedisCluster.setex(key, expiry, value);
    }

    @Override
    public String set(String key, Object value, long expiry) {
        if (value instanceof String)
            return jedisCluster.psetex(key, expiry, (String) value);
        else
            return jedisCluster.psetex(SafeEncoder.encode(key), expiry, ObjectUtils.serialize(value));
    }

    @Override
    public String set(byte[] key, byte[] value, long expiry) {
        return jedisCluster.psetex(key, expiry, value);
    }

    @Override
    public String set(final String key, final Object value, final SetParams params) {
        if (value instanceof String)
            return jedisCluster.set(key, (String) value, params);
        else
            return jedisCluster.set(SafeEncoder.encode(key), ObjectUtils.serialize(value), params);
    }

    @Override
    public String set(byte[] key, byte[] value, SetParams params) {
        return jedisCluster.set(key, value, params);
    }

    @Override
    public byte[] get(byte[] key) {
        return jedisCluster.get(key);
    }

    @Override
    public Long del(final String key) {
        return jedisCluster.del(key);
    }

    /**
     * <p> 获取客户端类 </p>
     *
     * @param key 缓存实际存储的键
     * @return 客户端类
     * @since 1.1.0
     */
    @Override
    protected Client getClientByKey(Object key) {
        Client client = null;
        if (ObjectUtils.isNotEmpty(key)) {
            // 获取缓存键key对应的槽位slot
            int slot = 0;
            if (key instanceof String) {
                slot = JedisClusterCRC16.getSlot(key.toString());
            } else if (key instanceof byte[]) {
                slot = JedisClusterCRC16.getSlot((byte[]) key);
            }
            // 根据槽位slot获取缓存键key所在的节点
            client = jedisCluster.getConnectionFromSlot(slot).getClient();
        }
        return client;
    }

    /**
     * <p> 内部建造者类 </p>
     *
     * @author huazie
     * @version 1.1.0
     * @since 1.1.0
     */
    public static class Builder {

        private String poolName; // 连接池名

        /**
         * <p> 默认构造器 </p>
         *
         * @since 1.1.0
         */
        public Builder() {
        }

        /**
         * <p> 指定连接池的构造器 </p>
         *
         * @param poolName 连接池名
         * @since 1.1.0
         */
        public Builder(String poolName) {
            this.poolName = poolName;
        }

        /**
         * <p> 构建Redis集群客户端对象 </p>
         *
         * @return Redis集群客户端
         * @since 1.1.0
         */
        public RedisClient build() {
            if (StringUtils.isBlank(poolName)) {
                return new FleaRedisClusterClient();
            } else {
                return new FleaRedisClusterClient(poolName);
            }
        }
    }
}
