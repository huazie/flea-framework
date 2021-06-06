package com.huazie.frame.cache.memcached.config;

import com.huazie.frame.cache.common.CacheConstants.MemCachedConfigConstants;
import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.common.util.ArrayUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.PropertiesUtil;
import com.huazie.frame.common.util.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.huazie.frame.cache.common.CacheConstants.FleaCacheConfigConstants.DEFAULT_EXPIRY;

/**
 * MemCached 缓存配置类，用于单个缓存接入场景，相关配置项可查看
 * MemCached 缓存配置文件【memcached.properties】
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class MemCachedConfig {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(MemCachedConfig.class);

    private static volatile MemCachedConfig config;

    private static Properties prop;

    private String systemName; // 缓存所属系统名

    private String[] servers; // MemCached 服务器地址信息

    private Integer[] weights; // MemCached 服务器权重分配

    private int initConn; // 初始化时对每个服务器建立的连接数目

    private int minConn; // 每个服务器建立最小的连接数

    private int maxConn; // 每个服务器建立最大的连接数

    private int maintSleep; // 自查线程周期进行工作，其每次休眠时间（单位：ms）

    private boolean nagle; // Socket的参数，如果是true在写数据时不缓冲，立即发送出去

    private int socketTO; // Socket阻塞读取数据的超时时间（单位：ms）

    private int socketConnectTO; // Socket连接的超时时间（单位：ms）

    private int nullCacheExpiry; // 空缓存数据有效期（单位：s）

    private int hashingAlg; // 一致性hash算法

    static {
        String fileName = MemCachedConfigConstants.MEMCACHED_FILE_NAME;
        if (StringUtils.isNotBlank(System.getProperty(MemCachedConfigConstants.MEMCACHED_CONFIG_FILE_SYSTEM_KEY))) {
            fileName = StringUtils.trim(System.getProperty(MemCachedConfigConstants.MEMCACHED_CONFIG_FILE_SYSTEM_KEY));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("MemCachedConfig Use the specified memcached.properties：{}", fileName);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("MemCachedConfig Use the current memcached.properties：{}", fileName);
        }
        // 获取配置文件
        prop = PropertiesUtil.getProperties(fileName);
    }

    /**
     * <p> 读取Memcached缓存配置类实例 </p>
     *
     * @return Memcached缓存配置类实例
     * @since 1.0.0
     */
    public static MemCachedConfig getConfig() {

        if (ObjectUtils.isEmpty(config)) {
            synchronized (MemCachedConfig.class) {
                if (ObjectUtils.isEmpty(config)) {
                    config = new MemCachedConfig();
                    try {
                        // 缓存归属系统
                        config.setSystemName();
                        // MemCached 服务器地址信息
                        config.setServers();
                        // MemCached 服务器权重分配
                        config.setWeights();
                        // 初始化时对每个服务器建立的连接数目
                        config.setInitConn();
                        // 每个服务器建立最小的连接数
                        config.setMinConn();
                        // 每个服务器建立最大的连接数
                        config.setMaxConn();
                        // 自查线程周期进行工作，其每次休眠时间（单位：ms）
                        config.setMaintSleep();
                        // Socket的参数，如果是true在写数据时不缓冲，立即发送出去
                        config.setNagle();
                        // Socket阻塞读取数据的超时时间（单位：ms）
                        config.setSocketTO();
                        // Socket连接的超时时间（单位：ms）
                        config.setSocketConnectTO();
                        // 空缓存数据有效期（单位：s）
                        config.setNullCacheExpiry();
                        // 一致性hash算法
                        config.setHashingAlg();
                    } catch (Exception e) {
                        if (LOGGER.isErrorEnabled()) {
                            LOGGER.error("Please check the MemCached config :", e);
                        }
                    }
                }
            }
        }
        return config;
    }

    public String getSystemName() {
        return systemName;
    }

    private void setSystemName() throws RuntimeException{
        // 获取缓存所属系统名
        String systemName = PropertiesUtil.getStringValue(prop, MemCachedConfigConstants.MEMCACHED_CONFIG_SYSTEM_NAME);
        if (StringUtils.isBlank(systemName)) {
            throw new RuntimeException("缓存归属系统名未配置，请检查");
        }
        this.systemName = systemName;
    }

    public String[] getServers() {
        return servers;
    }

    private void setServers() throws RuntimeException {
        // 获取MemCached服务器地址
        String allServer = PropertiesUtil.getStringValue(prop, MemCachedConfigConstants.MEMCACHED_CONFIG_SERVER);
        if (StringUtils.isBlank(allServer)) {
            throw new RuntimeException("The configuration attribute [" + MemCachedConfigConstants.MEMCACHED_CONFIG_SERVER + "] is not exist");
        }
        String[] serverArr = StringUtils.split(allServer, CommonConstants.SymbolConstants.COMMA);
        if (ArrayUtils.isEmpty(serverArr)) {
            throw new RuntimeException("The value of configuration attribute [" + MemCachedConfigConstants.MEMCACHED_CONFIG_SERVER + "] is empty");
        }
        this.servers = serverArr;
    }

    public Integer[] getWeights() {
        return weights;
    }

    private void setWeights() {
        String allWeight = PropertiesUtil.getStringValue(prop, MemCachedConfigConstants.MEMCACHED_CONFIG_WEIGHT);
        List<Integer> weightList = new ArrayList<>();
        if (StringUtils.isNotBlank(allWeight)) {
            String[] weightStrArr = StringUtils.split(allWeight, CommonConstants.SymbolConstants.COMMA);
            if (ArrayUtils.isNotEmpty(weightStrArr) && ArrayUtils.isSameLength(getServers(), weightStrArr)) {
                for (String weightStr : weightStrArr) {
                    Integer weight = Integer.valueOf(weightStr);
                    weightList.add(weight);
                }
            }
        }
        this.weights = weightList.toArray(new Integer[0]);
    }

    public int getInitConn() {
        return initConn;
    }

    private void setInitConn() {
        this.initConn = PropertiesUtil.getIntegerValue(prop, MemCachedConfigConstants.MEMCACHED_CONFIG_INITCONN);
    }

    public int getMinConn() {
        return minConn;
    }

    private void setMinConn() {
        this.minConn = PropertiesUtil.getIntegerValue(prop, MemCachedConfigConstants.MEMCACHED_CONFIG_MINCONN);
    }

    public int getMaxConn() {
        return maxConn;
    }

    private void setMaxConn() {
        this.maxConn = PropertiesUtil.getIntegerValue(prop, MemCachedConfigConstants.MEMCACHED_CONFIG_MAXCONN);
    }

    public int getMaintSleep() {
        return maintSleep;
    }

    private void setMaintSleep() {
        this.maintSleep = PropertiesUtil.getIntegerValue(prop, MemCachedConfigConstants.MEMCACHED_CONFIG_MAINTSLEEP);
    }

    public boolean isNagle() {
        return nagle;
    }

    private void setNagle() {
        this.nagle = PropertiesUtil.getBooleanValue(prop, MemCachedConfigConstants.MEMCACHED_CONFIG_NAGLE);
    }

    public int getSocketTO() {
        return socketTO;
    }

    private void setSocketTO() {
        this.socketTO = PropertiesUtil.getIntegerValue(prop, MemCachedConfigConstants.MEMCACHED_CONFIG_SOCKETTO);
    }

    public int getSocketConnectTO() {
        return socketConnectTO;
    }

    private void setSocketConnectTO() {
        this.socketConnectTO = PropertiesUtil.getIntegerValue(prop, MemCachedConfigConstants.MEMCACHED_CONFIG_SOCKETCONNECTTO);
    }

    public int getNullCacheExpiry() {
        return nullCacheExpiry;
    }

    private void setNullCacheExpiry() {
        Integer nullCacheExpiry = PropertiesUtil.getIntegerValue(prop, MemCachedConfigConstants.MEMCACHED_CONFIG_NULLCACHEEXPIRY);
        if (null != nullCacheExpiry) {
            this.nullCacheExpiry = nullCacheExpiry;
        } else {
            this.nullCacheExpiry = DEFAULT_EXPIRY; // 默认5分钟
        }
    }

    public int getHashingAlg() {
        return hashingAlg;
    }

    private void setHashingAlg() {
        this.hashingAlg = PropertiesUtil.getIntegerValue(prop, MemCachedConfigConstants.MEMCACHED_CONFIG_HASHINGALG);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
