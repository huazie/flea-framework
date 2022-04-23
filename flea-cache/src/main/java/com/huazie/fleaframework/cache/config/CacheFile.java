package com.huazie.fleaframework.cache.config;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 其他缓存定义配置文件，对应【flea-cache.xml】中
 * 【{@code <cache-file> </cache-file>}】
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public class CacheFile extends CacheMap {

    private String location; // 文件位置

    private List<Cache> cacheList = new ArrayList<>(); // 其他缓存文件中定义的Flea缓存集合

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Cache> getCacheList() {
        return cacheList;
    }

    public void setCacheList(List<Cache> cacheList) {
        this.cacheList = cacheList;
        addConfigList(cacheList);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
