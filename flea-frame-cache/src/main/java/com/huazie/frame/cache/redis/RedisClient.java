package com.huazie.frame.cache.redis;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.params.SetParams;

/**
 * <p> Redis客户端对外接口 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface RedisClient {

    /**
     * <p> 往Redis塞数据 </p>
     *
     * @param key   数据键
     * @param value 数据值
     * @return 状态码 （OK ：成功）
     * @since 1.0.0
     */
    String set(final String key, final String value);

    /**
     * <p> 往Redis赛数据（用于序列化对象） </p>
     *
     * @param key   数据键
     * @param value 数据值
     * @return 状态码 （OK ：成功）
     * @since 1.0.0
     */
    String set(final byte[] key, final byte[] value);

    /**
     * <p> 往Redis塞数据 (可以带失效时间) </p>
     * <p> 注意 ： (单位：s)</p>
     *
     * @param key    数据键
     * @param value  数据值
     * @param expiry 失效时间（单位：s）
     * @return 状态码 （OK ：成功）
     * @since 1.0.0
     */
    String set(final String key, final String value, final int expiry);

    /**
     * <p> 往Redis塞数据 (可以带失效时间，用于序列化对象) </p>
     * <p> 注意 ： (单位：s)</p>
     *
     * @param key    数据键
     * @param value  数据值
     * @param expiry 失效时间（单位：s）
     * @return 状态码 （OK ：成功）
     * @since 1.0.0
     */
    String set(final byte[] key, final byte[] value, final int expiry);

    /**
     * <p> 往Redis塞数据 (可以带失效时间) </p>
     * <p> 注意：（单位：ms） </p>
     *
     * @param key    数据键
     * @param value  数据值
     * @param expiry 失效时间（单位：ms）
     * @return 状态码 （OK ：成功）
     * @since 1.0.0
     */
    String set(final String key, final String value, final long expiry);

    /**
     * <p> 往Redis塞数据 (可以带失效时间，用于序列化对象) </p>
     * <p> 注意：（单位：ms） </p>
     *
     * @param key    数据键
     * @param value  数据值
     * @param expiry 失效时间（单位：ms）
     * @return 状态码 （OK ：成功）
     * @since 1.0.0
     */
    String set(final byte[] key, final byte[] value, final long expiry);

    /**
     * <p> 往Redis塞数据 (带参数) </p>
     *
     * @param key    数据键
     * @param value  数据值
     * @param params 参数
     * @return 状态码 （OK ：成功）
     * @since 1.0.0
     */
    String set(final String key, final String value, SetParams params);

    /**
     * <p> 往Redis塞数据 (带参数，用于序列化对象) </p>
     *
     * @param key    数据键
     * @param value  数据值
     * @param params 参数
     * @return 状态码 （OK ：成功）
     * @since 1.0.0
     */
    String set(final byte[] key, final byte[] value, SetParams params);

    /**
     * <p> 从Redis取数据 </p>
     *
     * @param key 数据键
     * @return 数据值
     * @since 1.0.0
     */
    String get(final String key);

    /**
     * <p> 从Redis取数据（用于获取序列化对象） </p>
     *
     * @param key 数据键
     * @return 数据值
     * @since 1.0.0
     */
    byte[] get(final byte[] key);

    /**
     * <p> 从Redis中删除数据 </p>
     *
     * @param key 数据键
     * @return 被删除key的数量
     * @since 1.0.0
     */
    Long del(final String key);

    /**
     * <p> 获取数据所在的Redis服务器ip(主机地址+端口) </p>
     *
     * @param key 数据键
     * @return 当前数据所在的Redis服务器ip
     * @since 1.0.0
     */
    String getLocation(final String key);

    /**
     * <p> 获取数据所在的Redis服务器主机 </p>
     *
     * @param key 数据键
     * @return 数据所在的Redis服务器主机
     * @since 1.0.0
     */
    String getHost(final String key);

    /**
     * <p> 获取数据所在的Redis服务器主机端口 </p>
     *
     * @param key 数据键
     * @return 数据所在的Redis服务器主机端口
     * @since 1.0.0
     */
    Integer getPort(final String key);

    /**
     * <p> 获取分布式Redis集群客户端连接池 </p>
     *
     * @return 分布式Redis集群客户端连接池
     * @since 1.0.0
     */
    ShardedJedisPool getJedisPool();

    /**
     * <p> 设置分布式Redis集群客户端 </p>
     *
     * @param shardedJedis 分布式Redis集群客户端
     * @since 1.0.0
     */
    void setShardedJedis(ShardedJedis shardedJedis);

    /**
     * <p> 获取分布式Redis集群客户端 </p>
     *
     * @return 分布是Redis集群客户端
     * @since 1.0.0
     */
    ShardedJedis getShardedJedis();

}
