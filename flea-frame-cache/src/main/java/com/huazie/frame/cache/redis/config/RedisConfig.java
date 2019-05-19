package com.huazie.frame.cache.redis.config;

import com.huazie.frame.cache.common.CacheConstants;
import com.huazie.frame.cache.memcached.config.MemcachedConfig;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.PropertiesUtil;
import com.huazie.frame.common.util.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * <p> Redis缓存配置类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class RedisConfig {

    private final static Logger LOGGER = LoggerFactory.getLogger(RedisConfig.class);

    private static volatile RedisConfig config;

    private static Properties prop;

    private String[] servers;   // 服务器地址

    private int hashingAlg;     // 一致性hash算法, 用于计算缓存的归属

    static {
        String fileName = CacheConstants.RedisConfigConstants.REDIS_FILE_NAME;
        if (StringUtils.isNotBlank(System.getProperty("fleaframe.cache.redis.config.filename"))) {
            fileName = StringUtils.trim(System.getProperty("fleaframe.cache.redis.config.filename"));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("RedisConfig Use the specified redis.properties：{}", fileName);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("RedisConfig Use the current redis.properties：{}", fileName);
        }
        // 获取配置文件
        prop = PropertiesUtil.getProperties(fileName);
    }

    /**
     * <p> 读取Redis缓存配置类实例 </p>
     *
     * @return Redis缓存配置类实例
     * @since 1.0.0
     */
    public static RedisConfig getConfig() throws Exception {
        if (ObjectUtils.isEmpty(config)) {
            synchronized (MemcachedConfig.class) {
                if (ObjectUtils.isEmpty(config)) {
                    config = new RedisConfig();
                    int serverCount = PropertiesUtil.getIntegerValue(prop, CacheConstants.RedisConfigConstants.REDIS_CONFIG_SERVER_COUNT);
                    if (serverCount > 0) {
                        String[] servers = new String[serverCount];
                        for (int i = 0; i < serverCount; i++) {
                            String server = PropertiesUtil.getStringValue(prop, CacheConstants.RedisConfigConstants.REDIS_CONFIG_SERVER + (i + 1));
                            if (StringUtils.isBlank(server)) {
                                throw new Exception("Please check the redis config：" + CacheConstants.RedisConfigConstants.REDIS_CONFIG_SERVER + (i + 1) + " is not exist");
                            }
                            servers[i] = server;
                        }

                        config.setServers(servers);
                        config.setHashingAlg(PropertiesUtil.getIntegerValue(prop, CacheConstants.RedisConfigConstants.REDIS_CONFIG_HASHINGALG));
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
