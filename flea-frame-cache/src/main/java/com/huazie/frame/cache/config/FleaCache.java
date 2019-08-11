package com.huazie.frame.cache.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p> Flea缓存 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaCache {

    private Caches caches;

    public Caches getCaches() {
        return caches;
    }

    public void setCaches(Caches caches) {
        this.caches = caches;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
