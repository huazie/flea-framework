package com.huazie.fleaframework.cache.config;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 缓存定义集，对应【flea-cache.xml】中
 * 【{@code <caches> <caches>}】
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public class Caches extends CacheMap {

    private List<Cache> cacheList = new ArrayList<>(); // Flea缓存集中的各个Flea缓存

    @Override
    public List<Cache> getCacheList() {
        return cacheList;
    }

    /**
     * 添加一个Flea缓存
     *
     * @param cache Flea缓存
     * @since 1.0.0
     */
    public void addFleaCache(Cache cache) {
        cacheList.add(cache);
    }

    @Override
    protected List<Cache> getConfigList() {
        return cacheList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
