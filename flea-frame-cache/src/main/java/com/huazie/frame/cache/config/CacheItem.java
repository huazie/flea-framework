package com.huazie.frame.cache.config;

import com.huazie.frame.common.config.ConfigValue;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p> 缓存配置项 </p>
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
