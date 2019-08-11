package com.huazie.frame.cache.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p> 缓存服务器 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class CacheServer {

    private String group; // 缓存服务器归属组名

    private String password; // 缓存服务器密码

    private String weight; // 缓存服务器权重

    private String desc; // 缓存服务器描述

    private String server; // 缓存服务器地址（ip + 端口）

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
