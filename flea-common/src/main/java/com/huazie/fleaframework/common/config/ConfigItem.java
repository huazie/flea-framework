package com.huazie.fleaframework.common.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 配置项，可在【flea-config.xml】文件中
 * 查看 {@code <config-item />} 节点。
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
