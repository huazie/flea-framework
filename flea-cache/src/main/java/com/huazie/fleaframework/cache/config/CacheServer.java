package com.huazie.fleaframework.cache.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 缓存服务器，对应【flea-cache-config.xml】中
 * 【{@code <cache-server group="" password="" weight=""
 * master="" database="" desc="">ip:port</cache-server>}】
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public class CacheServer {

    private String group; // 缓存服务器归属组名

    private String password; // 缓存服务器密码

    private String weight; // 缓存服务器权重

    private String master; // 主服务器节点名称

    private Integer database; // redis数据库索引

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

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public Integer getDatabase() {
        return database;
    }

    public void setDatabase(Integer database) {
        this.database = database;
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
