package com.huazie.fleaframework.cache.redis.config;

import com.huazie.fleaframework.cache.common.CacheConfigUtils;
import com.huazie.fleaframework.cache.common.CacheConstants.RedisConfigConstants;
import com.huazie.fleaframework.cache.exceptions.FleaCacheConfigException;
import com.huazie.fleaframework.cache.exceptions.FleaCacheException;
import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.ArrayUtils;
import com.huazie.fleaframework.common.util.ExceptionUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.PropertiesUtil;
import com.huazie.fleaframework.common.util.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import redis.clients.jedis.Protocol;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Redis哨兵缓存配置类，用于单个缓存接入场景，相关配置项可查看
 * Redis哨兵缓存配置文件【redis.sentinel.properties】
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class RedisSentinelConfig extends RedisCommonConfig {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(RedisSentinelConfig.class);

    private static volatile RedisSentinelConfig config;

    private static Properties prop;

    private String masterName; // Redis主服务器节点名称

    private Set<String> sentinels; // Redis哨兵节点的地址集合

    private String password; // Redis哨服务器节点登录密码（各节点配置同一个）

    private int connectionTimeout; // Redis哨兵客户端socket连接超时时间（单位：ms）

    private int soTimeout; // Redis哨兵客户端socket读写超时时间（单位：ms）

    static {
        String fileName = RedisConfigConstants.REDIS_SENTINEL_FILE_NAME;
        if (StringUtils.isNotBlank(System.getProperty(RedisConfigConstants.REDIS_SENTINEL_CONFIG_FILE_SYSTEM_KEY))) {
            fileName = StringUtils.trim(System.getProperty(RedisConfigConstants.REDIS_SENTINEL_CONFIG_FILE_SYSTEM_KEY));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("RedisSentinelConfig Use the specified redis.sentinel.properties：{}", fileName);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("RedisSentinelConfig Use the current redis.sentinel.properties：{}", fileName);
        }
        // 获取配置文件
        prop = PropertiesUtil.getProperties(fileName);
    }

    private RedisSentinelConfig() {
        try {
            // Redis哨兵配置开关（1：开启 0：关闭），如果不配置也默认开启
            setSwitchFlag(prop, RedisConfigConstants.REDIS_SENTINEL_CONFIG_SWITCH);
            // 缓存归属系统
            setSystemName(prop);
            // 设置Redis主服务器节点名称
            setMasterName();
            // 添加Redis哨兵的服务节点Set集合
            setSentinels();
            // 设置Redis服务器节点登录密码
            setPassword();
            // Redis哨兵客户端socket连接超时时间（单位：ms）
            setConnectionTimeout();
            // Redis哨兵客户端socket读写超时时间（单位：ms）
            setSoTimeout();
            // Redis哨兵客户端操作最大尝试次数【包含第一次操作】
            setMaxAttempts(prop);
            // 空缓存数据有效期
            setNullCacheExpiry(prop);
            // Jedis连接配置信息
            setJedisPoolConfig(prop);
        } catch (FleaCacheException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Please check the redis sentinel config :", e);
            }
        }
    }

    /**
     * 获取Redis哨兵缓存配置类实例
     *
     * @return Redis哨兵缓存配置类实例
     * @since 2.0.0
     */
    public static RedisSentinelConfig getConfig() {
        if (ObjectUtils.isEmpty(config)) {
            synchronized (RedisSentinelConfig.class) {
                if (ObjectUtils.isEmpty(config)) {
                    config = new RedisSentinelConfig();
                }
            }
        }
        return config;
    }

    /**
     * 获取Redis主服务器节点名称
     *
     * @return Redis主服务器节点名称
     * @since 2.0.0
     */
    public String getMasterName() {
        return masterName;
    }

    /**
     * 设置Redis主服务器节点名称
     *
     * @since 2.0.0
     */
    private void setMasterName() {
        this.masterName = PropertiesUtil.getStringValue(prop, RedisConfigConstants.REDIS_SENTINEL_CONFIG_MASTERNAME);
    }

    /**
     * 获取Redis哨兵节点的地址Set集合
     *
     * @return Redis哨兵节点的地址Set集合
     * @since 2.0.0
     */
    public Set<String> getSentinels() {
        return sentinels;
    }

    /**
     * 设置Redis哨兵节点的地址Set集合
     *
     * @since 2.0.0
     */
    private void setSentinels() throws FleaCacheConfigException {
        // Redis哨兵服务节点Set集合
        Set<String> sentinels = null;
        try {
            // 取 Redis哨兵节点的地址
            String servers = PropertiesUtil.getStringValue(prop, RedisConfigConstants.REDIS_SENTINEL_CONFIG_SERVER);
            if (StringUtils.isNotBlank(servers)) {
                // 取逗号分隔的服务器地址信息
                String[] serverArr = StringUtils.split(servers, CommonConstants.SymbolConstants.COMMA);
                if (ArrayUtils.isNotEmpty(serverArr)) {
                    sentinels = new HashSet<>(Arrays.asList(serverArr));
                }
            }
        } catch (Exception e) {
            ExceptionUtils.throwFleaException(FleaCacheConfigException.class, "Redis哨兵服务器地址有误，请检查配置【" + RedisConfigConstants.REDIS_SENTINEL_CONFIG_SERVER + "】", e);
        }
        if (ObjectUtils.isEmpty(sentinels)) {
            ExceptionUtils.throwFleaException(FleaCacheConfigException.class, "未配置Redis哨兵节点的地址，请检查配置【" + RedisConfigConstants.REDIS_SENTINEL_CONFIG_SERVER + "】");
        }
        this.sentinels = sentinels;
    }

    /**
     * 获取Redis主从服务器节点登录密码（各节点配置同一个）
     *
     * @return Redis主从服务器节点登录密码（各节点配置同一个）
     * @since 2.0.0
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置Redis主从服务器节点登录密码（各节点配置同一个）
     *
     * @since 2.0.0
     */
    private void setPassword() {
        this.password = PropertiesUtil.getStringValue(prop, RedisConfigConstants.REDIS_SENTINEL_CONFIG_PASSWORD);
    }

    /**
     * 获取Redis哨兵客户端socket连接超时时间（单位：ms）
     *
     * @return Redis哨兵客户端socket连接超时时间（单位：ms）
     * @since 2.0.0
     */
    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    /**
     * 设置Redis哨兵客户端socket连接超时时间（单位：ms）
     *
     * @since 2.0.0
     */
    private void setConnectionTimeout() {
        int connectionTimeout = Protocol.DEFAULT_TIMEOUT; // 默认Redis哨兵客户端socket连接超时时间（单位：ms）
        String connectionTimeoutStr = PropertiesUtil.getStringValue(prop, RedisConfigConstants.REDIS_SENTINEL_CONFIG_CONNECTIONTIMEOUT);
        if (StringUtils.isNotBlank(connectionTimeoutStr)) {
            connectionTimeout = Integer.parseInt(connectionTimeoutStr);
        }
        this.connectionTimeout = connectionTimeout;
    }

    /**
     * 获取Redis哨兵客户端socket读写超时时间（单位：ms）
     *
     * @return Redis哨兵客户端socket读写超时时间（单位：ms）
     * @since 2.0.0
     */
    public int getSoTimeout() {
        return soTimeout;
    }

    /**
     * 设置Redis哨兵客户端socket读写超时时间（单位：ms）
     *
     * @since 2.0.0
     */
    private void setSoTimeout() {
        int soTimeout = Protocol.DEFAULT_TIMEOUT; // 默认Redis客户端socket读写超时时间（单位：ms）
        String soTimeoutStr = PropertiesUtil.getStringValue(prop, RedisConfigConstants.REDIS_SENTINEL_CONFIG_SOTIMEOUT);
        if (StringUtils.isNotBlank(soTimeoutStr)) {
            soTimeout = Integer.parseInt(soTimeoutStr);
        }
        this.soTimeout = soTimeout;
    }

    /**
     * 获取Redis哨兵客户端当前连接的名称
     *
     * @return Redis哨兵客户端当前连接的名称
     * @since 2.0.0
     */
    public String getClientName() {
        return CacheConfigUtils.getClientName(getSystemName());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
