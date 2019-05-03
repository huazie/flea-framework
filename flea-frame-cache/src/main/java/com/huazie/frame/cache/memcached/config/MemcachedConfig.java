package com.huazie.frame.cache.memcached.config;

import com.huazie.frame.cache.common.CacheConstants;
import com.huazie.frame.cache.common.exception.MemcachedException;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.PropertiesUtil;
import com.huazie.frame.common.util.StringUtils;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * <p> Memcached缓存配置类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class MemcachedConfig {

    private final static Logger LOGGER = LoggerFactory.getLogger(MemcachedConfig.class);

    private static volatile MemcachedConfig config;

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
        String fileName = CacheConstants.MemcachedConfigConstants.MEMCACHE_FILE_NAME;
        if (StringUtils.isNotBlank(System.getProperty("fleaframe.cache.memcached.config.filename"))) {
            fileName = StringUtils.trim(System.getProperty("fleaframe.cache.memcached.config.filename"));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("JDBCConfig Use the specified memcached.properties：{}", fileName);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("JDBCConfig Use the current memcached.properties：{}", fileName);
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
    public static MemcachedConfig getConfig() throws Exception {

        if (null == config) {
            synchronized (MemcachedConfig.class){
                if(null == config){
                    config = new MemcachedConfig();
                    int serverCount = PropertiesUtil.getIntegerValue(prop, CacheConstants.MemcachedConfigConstants.MEMCACHED_CONFIG_SERVER_COUNT);
                    if (serverCount > 0) {
                        String[] servers = new String[serverCount];
                        for (int i = 0; i < serverCount; i++) {
                            String server = PropertiesUtil.getStringValue(prop, CacheConstants.MemcachedConfigConstants.MEMCACHED_CONFIG_SERVER + (i + 1));
                            if (StringUtils.isBlank(server)) {
                                throw new MemcachedException("请检查Memcached配置：" + CacheConstants.MemcachedConfigConstants.MEMCACHED_CONFIG_SERVER + (i + 1) + "不存在");
                            }
                            servers[i] = server;
                        }

                        Integer[] weights = new Integer[serverCount];
                        for (int i = 0; i < serverCount; i++) {
                            Integer weight = PropertiesUtil.getIntegerValue(prop, CacheConstants.MemcachedConfigConstants.MEMCACHED_CONFIG_WEIGHT + (i + 1));
                            if (ObjectUtils.isEmpty(weight)) {
                                throw new MemcachedException("请检查Memcached配置：" + CacheConstants.MemcachedConfigConstants.MEMCACHED_CONFIG_WEIGHT + (i + 1) + "不存在");
                            }
                            weights[i] = weight;
                        }

                        config.setServers(servers);
                        config.setWeights(weights);
                        config.setInitConn(PropertiesUtil.getIntegerValue(prop, CacheConstants.MemcachedConfigConstants.MEMCACHED_CONFIG_INITCONN));
                        config.setMinConn(PropertiesUtil.getIntegerValue(prop, CacheConstants.MemcachedConfigConstants.MEMCACHED_CONFIG_MINCONN));
                        config.setMaxConn(PropertiesUtil.getIntegerValue(prop, CacheConstants.MemcachedConfigConstants.MEMCACHED_CONFIG_MAXCONN));
                        config.setMaintSleep(PropertiesUtil.getIntegerValue(prop, CacheConstants.MemcachedConfigConstants.MEMCACHED_CONFIG_MAINTSLEEP));
                        config.setNagle(PropertiesUtil.getBooleanValue(prop, CacheConstants.MemcachedConfigConstants.MEMCACHED_CONFIG_NAGLE));
                        config.setSocketTO(PropertiesUtil.getIntegerValue(prop, CacheConstants.MemcachedConfigConstants.MEMCACHED_CONFIG_SOCKETTO));
                        config.setSocketConnectTO(PropertiesUtil.getIntegerValue(prop, CacheConstants.MemcachedConfigConstants.MEMCACHED_CONFIG_SOCKETCONNECTTO));
                        config.setHashingAlg(PropertiesUtil.getIntegerValue(prop, CacheConstants.MemcachedConfigConstants.MEMCACHED_CONFIG_HASHINGALG));
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
