package com.huazie.frame.cache.redis.impl;

import com.huazie.frame.cache.redis.RedisClient;
import com.huazie.frame.cache.redis.RedisPool;
import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.util.ObjectUtils;
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

    private ShardedJedisPool shardedJedisPool;

    private ShardedJedis shardedJedis;

    /**
     * <p> Redis客户端构造方法 </p>
     *
     * @since 1.0.0
     */
    public FleaRedisClient() {
        shardedJedisPool = RedisPool.getInstance().getJedisPool();
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
        StringBuilder ip = new StringBuilder();
        Client client = shardedJedis.getShard(key).getClient();
        if (ObjectUtils.isNotEmpty(client)) {
            ip.append(client.getHost()).append(CommonConstants.SymbolConstants.COLON).append(client.getPort());
        }
        return ip.toString();
    }

    @Override
    public String getHost(final String key) {
        Client client = shardedJedis.getShard(key).getClient();
        if (ObjectUtils.isNotEmpty(client)) {
            return client.getHost();
        }
        return null;
    }

    @Override
    public Integer getPort(final String key) {
        Client client = shardedJedis.getShard(key).getClient();
        if (ObjectUtils.isNotEmpty(client)) {
            return client.getPort();
        }
        return null;
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
}
