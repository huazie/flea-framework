package com.huazie.fleaframework.cache.config;

import com.huazie.fleaframework.common.config.ConfigValue;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 缓存配置项，对应【flea-cache-config.xml】中
 * 【{@code <cache-item key="" desc=""></cache-item>}】
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class CacheItem extends ConfigValue {

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
