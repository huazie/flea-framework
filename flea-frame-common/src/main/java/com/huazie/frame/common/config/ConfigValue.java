package com.huazie.frame.common.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p> 配置Value </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class ConfigValue extends ConfigKey {

    private String value; // 配置值

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
