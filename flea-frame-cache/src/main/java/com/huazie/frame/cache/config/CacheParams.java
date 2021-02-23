package com.huazie.frame.cache.config;

import com.huazie.frame.common.config.ConfigKey;
import com.huazie.frame.common.util.MapUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> 缓存参数集 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class CacheParams extends ConfigKey {

    private List<CacheParam> cacheParamList = new ArrayList<>(); // 缓存参数集中的各个缓存参数

    public List<CacheParam> getCacheParamList() {
        return cacheParamList;
    }

    /**
     * <p> 添加一个缓存配置项 </p>
     *
     * @param cacheParam 缓存配置项
     * @since 1.0.0
     */
    public void addCacheParam(CacheParam cacheParam) {
        cacheParamList.add(cacheParam);
    }

    /**
     * <p> 从指定缓存参数集，根据Key获取指定的缓存参数 </p>
     *
     * @param key 配置项键
     * @return 缓存配置项
     * @since 1.0.0
     */
    public CacheParam getCacheParam(String key) {
        CacheParam cacheParam = null;
        Map<String, CacheParam> cacheParamMap = toCacheParamMap();
        if (MapUtils.isNotEmpty(cacheParamMap)) {
            cacheParam = cacheParamMap.get(key);
        }
        return cacheParam;
    }

    /**
     * <p> 获取指定配置项列表中的配置项的Map，便于根据各配置项key查找 </p>
     *
     * @return 配置项的Map
     * @since 1.0.0
     */
    private Map<String, CacheParam> toCacheParamMap() {
        Map<String, CacheParam> cacheParamMap = new HashMap<>();
        for (CacheParam cacheParam : cacheParamList) {
            cacheParamMap.put(cacheParam.getKey(), cacheParam);
        }
        return cacheParamMap;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
