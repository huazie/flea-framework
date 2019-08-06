package com.huazie.frame.cache.memcached.config;

import com.huazie.frame.cache.common.CacheConstants;
import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.util.ArrayUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.PropertiesUtil;
import com.huazie.frame.common.util.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * <p> Memcached缓存配置类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class MemCachedConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemCachedConfig.class);

    private static volatile MemCachedConfig config;

    private static Properties prop;

    private String[] servers;   //服务器地址

    private Integer[] weights;  //Memcached权重分配

    private int initConn;       //初始化时对每个服务器建立的连接数目

    private int minConn;        //每个服务器建立最小的连接数

    private int maxConn;        //每个服务器建立最大的连接数

    private int maintSleep;     //自查线程周期进行工作，其每次休眠时间

    private boolean nagle;      //Socket的参数，如果是true在写数据时不缓冲，立即发送出去

    private int socketTO;       //Socket阻塞读取数据的超时时间

    private int socketConnectTO;//Socket连接的超时时间

    private int hashingAlg;     // 一致性hash算法

    static {
        String fileName = CacheConstants.MemCachedConfigConstants.MEMCACHE_FILE_NAME;
        if (StringUtils.isNotBlank(System.getProperty("fleaframe.cache.memcached.config.filename"))) {
            fileName = StringUtils.trim(System.getProperty("fleaframe.cache.memcached.config.filename"));
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

                        // 获取MemCached服务器地址
                        String allServer = PropertiesUtil.getStringValue(prop, CacheConstants.MemCachedConfigConstants.MEMCACHED_CONFIG_SERVER);
                        if (StringUtils.isBlank(allServer)) {
                            throw new Exception("The configuration attribute [" + CacheConstants.MemCachedConfigConstants.MEMCACHED_CONFIG_SERVER + "] is not exist");
                        }
                        String[] serverArr = StringUtils.split(allServer, CommonConstants.SymbolConstants.COMMA);
                        if (ArrayUtils.isEmpty(serverArr)) {
                            throw new Exception("The value of configuration attribute [" + CacheConstants.MemCachedConfigConstants.MEMCACHED_CONFIG_SERVER + "] is empty");
                        }

                        String allWeight = PropertiesUtil.getStringValue(prop, CacheConstants.MemCachedConfigConstants.MEMCACHED_CONFIG_WEIGHT);
                        List<Integer> weightList = new ArrayList<Integer>();
                        if (StringUtils.isNotBlank(allWeight)) {
                            String[] weightStrArr = StringUtils.split(allWeight, CommonConstants.SymbolConstants.COMMA);
                            if (ArrayUtils.isSameLength(serverArr, weightStrArr)) {
                                for (String weightStr : weightStrArr) {
                                    Integer weight = Integer.valueOf(weightStr);
                                    weightList.add(weight);
                                }
                            }
                        }

                        config.setServers(serverArr);
                        config.setWeights(weightList.toArray(new Integer[0]));
                        config.setInitConn(PropertiesUtil.getIntegerValue(prop, CacheConstants.MemCachedConfigConstants.MEMCACHED_CONFIG_INITCONN));
                        config.setMinConn(PropertiesUtil.getIntegerValue(prop, CacheConstants.MemCachedConfigConstants.MEMCACHED_CONFIG_MINCONN));
                        config.setMaxConn(PropertiesUtil.getIntegerValue(prop, CacheConstants.MemCachedConfigConstants.MEMCACHED_CONFIG_MAXCONN));
                        config.setMaintSleep(PropertiesUtil.getIntegerValue(prop, CacheConstants.MemCachedConfigConstants.MEMCACHED_CONFIG_MAINTSLEEP));
                        config.setNagle(PropertiesUtil.getBooleanValue(prop, CacheConstants.MemCachedConfigConstants.MEMCACHED_CONFIG_NAGLE));
                        config.setSocketTO(PropertiesUtil.getIntegerValue(prop, CacheConstants.MemCachedConfigConstants.MEMCACHED_CONFIG_SOCKETTO));
                        config.setSocketConnectTO(PropertiesUtil.getIntegerValue(prop, CacheConstants.MemCachedConfigConstants.MEMCACHED_CONFIG_SOCKETCONNECTTO));
                        config.setHashingAlg(PropertiesUtil.getIntegerValue(prop, CacheConstants.MemCachedConfigConstants.MEMCACHED_CONFIG_HASHINGALG));
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

    public String[] getServers() {
        return servers;
    }

    public void setServers(String[] servers) {
        this.servers = servers;
    }

    public Integer[] getWeights() {
        return weights;
    }

    public void setWeights(Integer[] weights) {
        this.weights = weights;
    }

    public int getInitConn() {
        return initConn;
    }

    public void setInitConn(int initConn) {
        this.initConn = initConn;
    }

    public int getMinConn() {
        return minConn;
    }

    public void setMinConn(int minConn) {
        this.minConn = minConn;
    }

    public int getMaxConn() {
        return maxConn;
    }

    public void setMaxConn(int maxConn) {
        this.maxConn = maxConn;
    }

    public int getMaintSleep() {
        return maintSleep;
    }

    public void setMaintSleep(int maintSleep) {
        this.maintSleep = maintSleep;
    }

    public boolean isNagle() {
        return nagle;
    }

    public void setNagle(boolean nagle) {
        this.nagle = nagle;
    }

    public int getSocketTO() {
        return socketTO;
    }

    public void setSocketTO(int socketTO) {
        this.socketTO = socketTO;
    }

    public int getSocketConnectTO() {
        return socketConnectTO;
    }

    public void setSocketConnectTO(int socketConnectTO) {
        this.socketConnectTO = socketConnectTO;
    }

    public int getHashingAlg() {
        return hashingAlg;
    }

    public void setHashingAlg(int hashingAlg) {
        this.hashingAlg = hashingAlg;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
