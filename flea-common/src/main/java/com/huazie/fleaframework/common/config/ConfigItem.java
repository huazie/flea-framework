package com.huazie.fleaframework.common.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p> 配置项 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class ConfigItem extends ConfigValue {

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
