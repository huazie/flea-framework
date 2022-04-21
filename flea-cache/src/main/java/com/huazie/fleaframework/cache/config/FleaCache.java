package com.huazie.fleaframework.cache.config;

import com.huazie.fleaframework.common.util.xml.ImportList;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Flea缓存定义，对应【flea-cache.xml】中
 * 【{@code <flea-cache> <flea-cache>}】
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaCache extends ImportList {

    private Caches caches; // Flea缓存集

    private CacheFiles cacheFiles; // 其他缓存定义配置文件集合

    public Caches getCaches() {
        return caches;
    }

    public void setCaches(Caches caches) {
        this.caches = caches;
    }

    public CacheFiles getCacheFiles() {
        return cacheFiles;
    }

    public void setCacheFiles(CacheFiles cacheFiles) {
        this.cacheFiles = cacheFiles;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
