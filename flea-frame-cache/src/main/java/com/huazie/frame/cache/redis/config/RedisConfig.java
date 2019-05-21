package com.huazie.frame.cache.redis.config;

import com.huazie.frame.cache.common.CacheConstants;
import com.huazie.frame.cache.memcached.config.MemcachedConfig;
import com.huazie.frame.cache.redis.pojo.FleaRedisServerInfo;
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

    private List<FleaRedisServerInfo> servers; // 服务器信息 （ host + port + password）

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
                    List<FleaRedisServerInfo> fleaRedisServerInfos = null;
                    String servers = PropertiesUtil.getStringValue(prop, CacheConstants.RedisConfigConstants.REDIS_CONFIG_SERVER);
                    String passwords = PropertiesUtil.getStringValue(prop, CacheConstants.RedisConfigConstants.REDIS_CONFIG_PASSWORD);
                    if (StringUtils.isNotBlank(servers) && StringUtils.isNotBlank(passwords)) {
                        // 取逗号分隔的服务器地址列表
                        String[] serverArr = StringUtils.split(servers, CommonConstants.SymbolConstants.SQL_COMMA);
                        String[] passwordArr = StringUtils.split(passwords, CommonConstants.SymbolConstants.SQL_COMMA);
                        if (ArrayUtils.isNotEmpty(serverArr)) {
                            fleaRedisServerInfos = new ArrayList<FleaRedisServerInfo>();
                            for (int i = 0; i < serverArr.length; i++) {
                                String ip = serverArr[i];
                                FleaRedisServerInfo redisServerInfo = new FleaRedisServerInfo();
                                if (StringUtils.isNotBlank(ip)) {
                                    String[] ipArr = StringUtils.split(ip, CommonConstants.SymbolConstants.SQL_COLON);
                                    if (ArrayUtils.isNotEmpty(ipArr) && CommonConstants.NumeralConstants.INT_TWO == ipArr.length) {
                                        redisServerInfo.setHost(ipArr[CommonConstants.NumeralConstants.INT_ZERO]);
                                        redisServerInfo.setPort(Integer.parseInt(ipArr[CommonConstants.NumeralConstants.INT_ONE]));
                                    }
                                }

                                if (ArrayUtils.isSameLength(serverArr, passwordArr)) {
                                    redisServerInfo.setPassword(passwordArr[i]);
                                }
                                fleaRedisServerInfos.add(redisServerInfo);
                            }
                        }
                    }
                    config.setServers(fleaRedisServerInfos);
                }
            }
        }
        return config;
    }

    public List<FleaRedisServerInfo> getServers() {
        return servers;
    }

    public void setServers(List<FleaRedisServerInfo> servers) {
        this.servers = servers;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
