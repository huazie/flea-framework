package com.huazie.frame.db.common.sql.template.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p> 定义模板和规则中各语句的属性 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Property {

    private String key;   // 属性键

    private String value; // 属性值

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
