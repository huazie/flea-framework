package com.huazie.frame.cache.common;

/**
 * <p> 缓存系统 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public enum CacheEnum {
    /**
     * 一个高性能的分布式内存对象缓存系统
     */
    MemCached("MemCached", "一个高性能的分布式内存对象缓存系统"),
    /**
     * 一个开源的使用ANSI C语言编写、支持网络、可基于内存亦可持久化的高性能的日志型、Key-Value存储系统
     */
    Redis("Redis", "一个开源的使用ANSI C语言编写、支持网络、可基于内存亦可持久化的高性能的日志型、Key-Value存储系统");

    private String name;    //缓存系统名称
    private String desc;    //缓存系统描述

    CacheEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

}
