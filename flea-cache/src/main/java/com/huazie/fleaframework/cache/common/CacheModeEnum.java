package com.huazie.fleaframework.cache.common;

/**
 * 缓存模式枚举，主要包含分片模式和集群模式、哨兵模式
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public enum CacheModeEnum {

    SHARDED(0, "分片模式，即缓存服务器是独立部署，客户端进行分片"),
    CLUSTER(1, "集群模式，即缓存服务器是集群化部署，适用于Redis"),
    SENTINEL(2, "哨兵模式，即缓存服务器是主从部署，哨兵进程监控主服务器，挂掉后从 从服务器 中选取一个服务器担任主节点。");

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
