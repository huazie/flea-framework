package com.huazie.frame.db.common.sql.template.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p> 定义SQL模板参数 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Param extends Properties {

    private String id;   // SQL模板参数编号

    private String name; // SQL模板参数名称

    private String desc; // SQL模板参数描述

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
