package com.huazie.frame.cache.redis;

import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.util.ObjectUtils;
import redis.clients.jedis.Client;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * <p> Redis客户端类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class RedisClient {

    private ShardedJedisPool shardedJedisPool;

    /**
     * <p> Redis客户端构造方法 </p>
     *
     * @since 1.0.0
     */
    public RedisClient() {
        init();
    }

    private void init() {
        shardedJedisPool = RedisPool.getInstance().getJedisPool();
    }

    /**
     * <p> 往Redis塞数据 </p>
     *
     * @param key   数据键
     * @param value 数据值
     * @return 状态码 （OK ：成功）
     * @since 1.0.0
     */
    public String set(final String key, final String value) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            return shardedJedis.set(key, value);
        } finally {
            if (ObjectUtils.isNotEmpty(shardedJedis)) {
                shardedJedis.close();
            }
        }
    }

    /**
     * <p> 从Redis取数据 </p>
     *
     * @param key 数据键
     * @return 数据值
     * @since 1.0.0
     */
    public String get(final String key) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            return shardedJedis.get(key);
        } finally {
            if (ObjectUtils.isNotEmpty(shardedJedis)) {
                shardedJedis.close();
            }
        }
    }

    /**
     * <p> 获取数据所在的Redis服务器ip(主机地址+端口) </p>
     *
     * @param key 数据键
     * @return 当前数据所在的Redis服务器ip
     * @since 1.0.0
     */
    public String getLocation(final String key) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            StringBuilder ip = new StringBuilder();
            Client client = shardedJedis.getShard(key).getClient();
            if (ObjectUtils.isNotEmpty(client)) {
                ip.append(client.getHost()).append(CommonConstants.SymbolConstants.COLON).append(client.getPort());
            }
            return ip.toString();
        } finally {
            if (ObjectUtils.isNotEmpty(shardedJedis)) {
                shardedJedis.close();
            }
        }
    }

    /**
     * <p> 获取数据所在的Redis服务器主机 </p>
     *
     * @param key 数据键
     * @return 数据所在的Redis服务器主机
     * @since 1.0.0
     */
    public String getHost(final String key) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            Client client = shardedJedis.getShard(key).getClient();
            if (ObjectUtils.isNotEmpty(client)) {
                return client.getHost();
            }
            return null;
        } finally {
            if (ObjectUtils.isNotEmpty(shardedJedis)) {
                shardedJedis.close();
            }
        }
    }

    /**
     * <p> 获取数据所在的Redis服务器主机端口 </p>
     *
     * @param key 数据键
     * @return 数据所在的Redis服务器主机端口
     * @since 1.0.0
     */
    public Integer getPort(final String key) {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        try {
            Client client = shardedJedis.getShard(key).getClient();
            if (ObjectUtils.isNotEmpty(client)) {
                return client.getPort();
            }
            return null;
        } finally {
            if (ObjectUtils.isNotEmpty(shardedJedis)) {
                shardedJedis.close();
            }
        }
    }



}
