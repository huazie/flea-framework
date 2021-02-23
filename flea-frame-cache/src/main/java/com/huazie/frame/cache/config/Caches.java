package com.huazie.frame.cache.config;

import com.huazie.frame.common.util.MapUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p> Flea缓存集 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Caches {

    private List<Cache> cacheList = new ArrayList<>(); // Flea缓存集中的各个Flea缓存

    public List<Cache> getCacheList() {
        return cacheList;
    }

    /**
     * <p> 添加一个Flea缓存 </p>
     *
     * @param cache Flea缓存
     * @since 1.0.0
     */
    public void addFleaCache(Cache cache) {
        cacheList.add(cache);
    }

    /**
     * <p> 获取指定Flea缓存集中的配置项的Map，便于根据各缓存数据key查找 </p>
     *
     * @return Flea缓存的Map
     * @since 1.0.0
     */
    public Map<String, Cache> toFleaCacheMap() {
        Map<String, Cache> fleaCacheMap = new HashMap<>();
        Iterator<Cache> fleaCacheIt = cacheList.iterator();
        while (fleaCacheIt.hasNext()) {
            Cache cache = fleaCacheIt.next();
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
        Map<String, Cache> fleaCacheMap = toFleaCacheMap();
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
