package com.huazie.frame.cache.config;

import com.huazie.frame.common.util.MapUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> 缓存数据集 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class CacheDatas {

    private List<CacheData> cacheDataList = new ArrayList<>(); // 缓存数据集中的各类缓存数据

    public List<CacheData> getCacheDataList() {
        return cacheDataList;
    }

    /**
     * <p> 添加一类缓存数据 </p>
     *
     * @param cacheData 缓存数据
     * @since 1.0.0
     */
    public void addCacheData(CacheData cacheData) {
        cacheDataList.add(cacheData);
    }

    /**
     * <p> 根据缓存数据类型获取指定的缓存数据 </p>
     *
     * @param type 缓存数据类型
     * @return 一类缓存数据
     * @since 1.0.0
     */
    public CacheData getCacheData(String type) {
        CacheData cacheData = null;
        Map<String, CacheData> cacheDataMap = toCacheDataMap();
        if (MapUtils.isNotEmpty(cacheDataMap)) {
            cacheData = cacheDataMap.get(type);
        }
        return cacheData;
    }

    /**
     * <p> 获取指定缓存数据集中的缓存数据的Map，便于根据各缓存数据类型type查找 </p>
     *
     * @return 缓存集的Map
     * @since 1.0.0
     */
    private Map<String, CacheData> toCacheDataMap() {
        Map<String, CacheData> cacheDataMap = new HashMap<>();
        for (CacheData cacheData : cacheDataList) {
            cacheDataMap.put(cacheData.getType(), cacheData);
        }
        return cacheDataMap;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
