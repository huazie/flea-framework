package com.huazie.fleaframework.cache.redis;

import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.util.ArrayUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import redis.clients.jedis.Client;
import redis.clients.jedis.util.SafeEncoder;

/**
 * 抽象Redis客户端，封装一些Redis缓存处理的通用的逻辑
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public abstract class FleaRedisClient implements RedisClient {

    private String poolName; // 连接池名

    public FleaRedisClient(String poolName) {
        this.poolName = poolName;
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
    public String getLocation(final String key) {
        return getLocationByKey(key);
    }

    @Override
    public String getLocation(final byte[] key) {
        return getLocationByKey(key);
    }

    @Override
    public String getHost(final String key) {
        return getHostByKey(key);
    }

    @Override
    public String getHost(final byte[] key) {
        return getHostByKey(key);
    }

    @Override
    public Integer getPort(final String key) {
        return getPortByKey(key);
    }

    @Override
    public Integer getPort(final byte[] key) {
        return getPortByKey(key);
    }

    @Override
    public Client getClient(final String key) {
        return getClientByKey(key);
    }

    @Override
    public Client getClient(final byte[] key) {
        return getClientByKey(key);
    }

    /**
     * <p> 获取数据所在的Redis服务器ip(主机地址+端口) </p>
     *
     * @param key 数据键
     * @return 当前数据所在的Redis服务器ip
     * @since 1.1.0
     */
    private String getLocationByKey(final Object key) {
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
     * @since 1.1.0
     */
    private String getHostByKey(final Object key) {
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
     * @since 1.1.0
     */
    private Integer getPortByKey(final Object key) {
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
     * @since 1.1.0
     */
    protected abstract Client getClientByKey(final Object key);

    @Override
    public String getPoolName() {
        return poolName;
    }
}
