package com.huazie.fleaframework.db.common.sql.template.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p> SQL模板校验规则 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Rule extends Properties {

    private String id;   // 规则编号

    private String name; // 规则描述

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
