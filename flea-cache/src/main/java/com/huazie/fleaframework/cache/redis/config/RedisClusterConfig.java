package com.huazie.fleaframework.cache.redis.config;

import com.huazie.fleaframework.cache.common.CacheConfigUtils;
import com.huazie.fleaframework.cache.common.CacheConstants.RedisConfigConstants;
import com.huazie.fleaframework.cache.common.CacheUtils;
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
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Protocol;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Redis集群缓存配置类，用于单个缓存接入场景，相关配置项可查看
 * Redis集群缓存配置文件【redis.cluster.properties】
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class RedisClusterConfig extends RedisCommonConfig {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(RedisClusterConfig.class);

    private static volatile RedisClusterConfig config;

    private static Properties prop;

    private Set<HostAndPort> nodes; // Redis集群的服务节点Set集合

    private String password; // Redis集群的服务节点登录密码（集群配置同一个）

    private int connectionTimeout; // Redis集群客户端socket连接超时时间（单位：ms）

    private int soTimeout; // Redis集群客户端socket读写超时时间（单位：ms）

    static {
        String fileName = RedisConfigConstants.REDIS_CLUSTER_FILE_NAME;
        if (StringUtils.isNotBlank(System.getProperty(RedisConfigConstants.REDIS_CLUSTER_CONFIG_FILE_SYSTEM_KEY))) {
            fileName = StringUtils.trim(System.getProperty(RedisConfigConstants.REDIS_CLUSTER_CONFIG_FILE_SYSTEM_KEY));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("RedisClusterConfig Use the specified redis.cluster.properties：{}", fileName);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("RedisClusterConfig Use the current redis.cluster.properties：{}", fileName);
        }
        // 获取配置文件
        prop = PropertiesUtil.getProperties(fileName);
    }

    private RedisClusterConfig() {
        try {
            // Redis集群配置开关（1：开启 0：关闭），如果不配置也默认开启
            setSwitchFlag(prop, RedisConfigConstants.REDIS_CLUSTER_CONFIG_SWITCH);
            // 缓存归属系统
            setSystemName(prop);
            // 空缓存数据有效期
            setNullCacheExpiry(prop);
            // Jedis连接配置信息
            setJedisPoolConfig(prop);
            // 添加Redis集群的服务节点Set集合
            setNodes();
            // 设置Redis集群的服务节点登录密码
            setPassword();
            // Redis集群客户端socket连接超时时间（单位：ms）
            setConnectionTimeout();
            // Redis客户端socket读写超时时间（单位：ms）
            setSoTimeout();
            // Redis客户端操作最大尝试次数【包含第一次操作】
            setMaxAttempts(prop);
        } catch (FleaCacheException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Please check the redis cluster config :", e);
            }
        }
    }

    /**
     * 读取Redis缓存配置类实例
     *
     * @return Redis缓存配置类实例
     * @since 1.1.0
     */
    public static RedisClusterConfig getConfig() {
        if (ObjectUtils.isEmpty(config)) {
            synchronized (RedisShardedConfig.class) {
                if (ObjectUtils.isEmpty(config)) {
                    config = new RedisClusterConfig();
                }
            }
        }
        return config;
    }

    /**
     * 获取Redis集群服务节点Set集合
     *
     * @return Redis集群服务节点Set集合
     * @since 1.1.0
     */
    public Set<HostAndPort> getNodes() {
        return nodes;
    }

    /**
     * 设置Redis集群服务节点Set集合
     *
     * @throws FleaCacheConfigException Flea缓存配置异常类
     * @since 1.1.0
     */
    private void setNodes() throws FleaCacheConfigException {
        // Redis集群服务节点Set集合
        Set<HostAndPort> nodes = null;
        try {
            // 取 Redis集群的服务器地址
            String servers = PropertiesUtil.getStringValue(prop, RedisConfigConstants.REDIS_CLUSTER_CONFIG_SERVER);
            if (StringUtils.isNotBlank(servers)) {
                // 取逗号分隔的服务器地址信息
                String[] serverArr = StringUtils.split(servers, CommonConstants.SymbolConstants.COMMA);
                if (ArrayUtils.isNotEmpty(serverArr)) {
                    nodes = new HashSet<>();
                    for (String server : serverArr) {
                        nodes.add(CacheUtils.parseString(server));
                    }
                }
            }
        } catch (Exception e) {
            ExceptionUtils.throwFleaException(FleaCacheConfigException.class, "Redis集群服务器地址有误，请检查配置【" + RedisConfigConstants.REDIS_CLUSTER_CONFIG_SERVER + "】", e);
        }
        if (ObjectUtils.isEmpty(nodes)) {
            ExceptionUtils.throwFleaException(FleaCacheConfigException.class, "未配置Redis集群服务器地址，请检查配置【" + RedisConfigConstants.REDIS_CLUSTER_CONFIG_SERVER + "】");
        }
        this.nodes = nodes;
    }

    /**
     * 获取Redis集群的服务节点登录密码（集群配置同一个）
     *
     * @return Redis集群的服务节点登录密码（集群配置同一个）
     * @since 1.1.0
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置Redis集群的服务节点登录密码（集群配置同一个）
     *
     * @since 1.1.0
     */
    private void setPassword() {
        this.password = PropertiesUtil.getStringValue(prop, RedisConfigConstants.REDIS_CLUSTER_CONFIG_PASSWORD);
    }

    /**
     * 获取Redis集群客户端socket连接超时时间（单位：ms）
     *
     * @return Redis集群客户端socket连接超时时间（单位：ms）
     * @since 1.1.0
     */
    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    /**
     * 设置Redis集群客户端socket连接超时时间（单位：ms）
     *
     * @since 1.1.0
     */
    private void setConnectionTimeout() {
        int connectionTimeout = Protocol.DEFAULT_TIMEOUT; // 默认Redis集群客户端socket连接超时时间（单位：ms）
        String connectionTimeoutStr = PropertiesUtil.getStringValue(prop, RedisConfigConstants.REDIS_CLUSTER_CONFIG_CONNECTIONTIMEOUT);
        if (StringUtils.isNotBlank(connectionTimeoutStr)) {
            connectionTimeout = Integer.parseInt(connectionTimeoutStr);
        }
        this.connectionTimeout = connectionTimeout;
    }

    /**
     * 获取Redis集群客户端socket读写超时时间（单位：ms）
     *
     * @return Redis集群客户端socket读写超时时间（单位：ms）
     * @since 1.1.0
     */
    public int getSoTimeout() {
        return soTimeout;
    }

    /**
     * 设置Redis集群客户端socket读写超时时间（单位：ms）
     *
     * @since 1.1.0
     */
    private void setSoTimeout() {
        int soTimeout = Protocol.DEFAULT_TIMEOUT; // 默认Redis客户端socket读写超时时间（单位：ms）
        String soTimeoutStr = PropertiesUtil.getStringValue(prop, RedisConfigConstants.REDIS_CLUSTER_CONFIG_SOTIMEOUT);
        if (StringUtils.isNotBlank(soTimeoutStr)) {
            soTimeout = Integer.parseInt(soTimeoutStr);
        }
        this.soTimeout = soTimeout;
    }

    /**
     * 获取Redis集群客户端当前连接的名称
     *
     * @return Redis集群客户端当前连接的名称
     * @since 1.1.0
     */
    public String getClientName() {
        return CacheConfigUtils.getClientName(getSystemName());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
