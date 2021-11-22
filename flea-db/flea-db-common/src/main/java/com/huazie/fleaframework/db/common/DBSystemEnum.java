package com.huazie.fleaframework.db.common;

/**
 * <p> 数据库系统枚举类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public enum DBSystemEnum {
    /**
     * 一个关系型数据库管理系统（由瑞典MySQL AB公司开发，目前属于 Oracle 旗下产品）
     */
    MySQL("MySQL", "一个关系型数据库管理系统（由瑞典MySQL AB公司开发，目前属于 Oracle 旗下产品）"),
    /**
     * 一款关系数据库管理系统（由美国ORACLE公司（甲骨文）提供的以分布式数据库为核心的一组软件产品）
     */
    Oracle("Oracle", "一款关系数据库管理系统（由美国ORACLE公司（甲骨文）提供的以分布式数据库为核心的一组软件产品）");

    private String name;    //数据库系统名称
    private String desc;    //数据库系统描述

    DBSystemEnum(String name, String desc) {
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
