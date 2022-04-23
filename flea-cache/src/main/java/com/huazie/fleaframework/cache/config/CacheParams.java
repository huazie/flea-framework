package com.huazie.fleaframework.cache.config;

import com.huazie.fleaframework.common.config.ConfigMap;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 缓存参数集，对应【flea-cache-config.xml】中
 * 【{@code <cache-params> </cache-params>}】
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public class CacheParams extends ConfigMap<CacheParam> {

    private List<CacheParam> cacheParamList = new ArrayList<>(); // 缓存参数集中的各个缓存参数

    public List<CacheParam> getCacheParamList() {
        return cacheParamList;
    }

    /**
     * 添加一个缓存配置项
     *
     * @param cacheParam 缓存配置项
     * @since 1.0.0
     */
    public void addCacheParam(CacheParam cacheParam) {
        cacheParamList.add(cacheParam);
        addConfig(cacheParam);
    }

    @Override
    protected String getConfigKey(CacheParam cacheParam) {
        return cacheParam.getKey();
    }

    /**
     * 从指定缓存参数集，根据Key获取指定的缓存参数
     *
     * @param key 配置项键
     * @return 缓存配置项
     * @since 1.0.0
     */
    public CacheParam getCacheParam(String key) {
        return getConfig(key);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
