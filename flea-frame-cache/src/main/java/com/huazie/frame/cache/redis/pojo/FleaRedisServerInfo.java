package com.huazie.frame.cache.redis.pojo;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p> Flea Redis 服务器配置信息 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaRedisServerInfo {

    private String host;        // Redis 主机地址

    private int port;           // Redis 主机端口

    private String password;    // Redis 连接授权密码

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
