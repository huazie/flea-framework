package com.huazie.fleaframework.cache.config;

import com.huazie.fleaframework.common.util.MapUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 其他缓存定义配置文件，对应【flea-cache.xml】中
 * 【{@code <cache-file> </cache-file>}】
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class CacheFile {

    private String location; // 文件位置

    private List<String> executions = new ArrayList<>(); // 需要过滤的缓存key

    private List<Cache> cacheList = new ArrayList<>(); // 其他缓存文件中定义的Flea缓存集合

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getExecutions() {
        return executions;
    }

    /**
     * <p> 添加一个需要过滤的缓存key配置 </p>
     *
     * @param execution 要过滤的缓存key
     * @since 1.0.0
     */
    public void addExecution(String execution) {
        if (!contains(execution)) {
            executions.add(execution);
        }
    }

    /**
     * <p> 判断是否包含指定缓存键</p>
     *
     * @param cacheKey 指定缓存键
     * @return true：包含; false: 不包含.
     * @since 1.0.0
     */
    public boolean contains(String cacheKey) {
        return executions.contains(cacheKey);
    }

    public void setCacheList(List<Cache> cacheList) {
        this.cacheList = cacheList;
    }

    /**
     * <p> 获取缓存文件中Flea缓存集中的配置项的Map，便于根据各缓存数据key查找 </p>
     *
     * @return Flea缓存的Map
     * @since 1.0.0
     */
    public Map<String, Cache> toCacheFileFleaCacheMap() {
        Map<String, Cache> fleaCacheMap = new HashMap<>();
        for (Cache cache : cacheList) {
            // 排除executions中指定的缓存
            if (ObjectUtils.isEmpty(cache) || contains(cache.getKey())) {
                continue;
            }
            fleaCacheMap.put(cache.getKey(), cache);
        }
        return fleaCacheMap;
    }

    /**
     * <p> 根据缓存数据键Key获取指定的Flea缓存 </p>
     *
     * @param key 缓存数据键
     * @return Flea缓存
     * @since 1.0.0
     */
    public Cache getFleaCache(String key) {
        Cache cache = null;
        Map<String, Cache> fleaCacheMap = toCacheFileFleaCacheMap();
        if (MapUtils.isNotEmpty(fleaCacheMap)) {
            cache = fleaCacheMap.get(key);
        }
        return cache;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
