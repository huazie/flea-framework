package com.huazie.fleaframework.db.common.sql.template.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p> 定义SQL模板 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Template extends Properties {

    private String id;    // 模板编号

    private String ruleId; // 校验规则编号

    private String name; // 模板名

    private String desc; // 模板描述

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
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
