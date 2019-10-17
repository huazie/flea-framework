package com.huazie.frame.cache.redis.impl;

import com.huazie.frame.cache.redis.RedisClient;
import com.huazie.frame.cache.redis.RedisPool;
import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import redis.clients.jedis.Client;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.params.SetParams;

/**
 * <p> Flea Redis客户端类 </p>
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
    public FleaRedisClient() {
        this(null);
    }

    /**
     * <p> Redis客户端构造方法 </p>
     *
     * @param poolName 连接池名
     * @since 1.0.0
     */
    public FleaRedisClient(String poolName) {
        this.poolName = poolName;
        init();
    }

    /**
     * <p> 初始化 </p>
     *
     * @since 1.0.0
     */
    private void init() {
        if (StringUtils.isBlank(poolName)) {
            poolName = CommonConstants.FleaPoolConstants.DEFAUTL_POOL_NAME;
            shardedJedisPool = RedisPool.getInstance().getJedisPool();
        } else {
            shardedJedisPool = RedisPool.getInstance(poolName).getJedisPool();
        }

    }

    @Override
    public String set(final String key, final String value) {
        return shardedJedis.set(key, value);
    }

    @Override
    public String set(byte[] key, byte[] value) {
        return shardedJedis.set(key, value);
    }

    @Override
    public String set(final String key, final String value, final int expiry) {
        return shardedJedis.setex(key, expiry, value);
    }

    @Override
    public String set(byte[] key, byte[] value, int expiry) {
        return shardedJedis.setex(key, expiry, value);
    }

    @Override
    public String set(String key, String value, long expiry) {
        return shardedJedis.psetex(key, expiry, value);
    }

    @Override
    public String set(byte[] key, byte[] value, long expiry) {
        return shardedJedis.psetex(key, expiry, value);
    }

    @Override
    public String set(final String key, final String value, final SetParams params) {
        return shardedJedis.set(key, value, params);
    }

    @Override
    public String set(byte[] key, byte[] value, SetParams params) {
        return shardedJedis.set(key, value, params);
    }

    @Override
    public String get(final String key) {
        return shardedJedis.get(key);
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
}
