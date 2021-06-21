package com.huazie.frame.cache.common;

/**
 * 缓存模式枚举，主要包含单机模式和集群模式
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public enum CacheModeEnum {

    SINGLE(0, "单机模式，即每台缓存服务器都是独立部署，适用于MemCached 或 Redis"),
    CLUSTER(1, "集群模式，即缓存服务器是集群化部署，适用于Redis");

    private int mode;

    private String desc;

    CacheModeEnum(int mode, String desc) {
        this.mode = mode;
        this.desc = desc;
    }

    public int getMode() {
        return mode;
    }

    public String getDesc() {
        return desc;
    }
}