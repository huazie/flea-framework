package com.huazie.fleaframework.cache.config;

import com.huazie.fleaframework.common.config.ConfigMap;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 缓存数据集，对应【flea-cache-config.xml】中
 * 【{@code <cache-datas> </cache-datas>}】
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public class CacheDatas extends ConfigMap<CacheData> {

    private List<CacheData> cacheDataList = new ArrayList<>(); // 缓存数据集中的各类缓存数据

    public List<CacheData> getCacheDataList() {
        return cacheDataList;
    }

    /**
     * 添加一类缓存数据
     *
     * @param cacheData 缓存数据
     * @since 1.0.0
     */
    public void addCacheData(CacheData cacheData) {
        cacheDataList.add(cacheData);
        addConfig(cacheData);
    }

    @Override
    protected String getConfigKey(CacheData cacheData) {
        return cacheData.getType();
    }

    /**
     * 根据缓存数据类型获取指定的缓存数据
     *
     * @param type 缓存数据类型
     * @return 一类缓存数据
     * @since 1.0.0
     */
    public CacheData getCacheData(String type) {
        return getConfig(type);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
