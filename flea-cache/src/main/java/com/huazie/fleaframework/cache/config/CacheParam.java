package com.huazie.fleaframework.cache.config;

import com.huazie.fleaframework.common.config.ConfigValue;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 缓存参数，对应【flea-cache-config.xml】中
 * 【{@code <cache-param key="" desc=""></cache-param>}】
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class CacheParam extends ConfigValue {

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
