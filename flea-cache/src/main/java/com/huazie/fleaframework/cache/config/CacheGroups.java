package com.huazie.fleaframework.cache.config;

import com.huazie.fleaframework.common.config.ConfigMap;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 缓存组集，对应【flea-cache-config.xml】中
 * 【{@code <cache-groups> </cache-groups>}】
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public class CacheGroups extends ConfigMap<CacheGroup> {

    private List<CacheGroup> cacheGroupList = new ArrayList<>(); // 缓存组集中的各个缓存组

    public List<CacheGroup> getCacheGroupList() {
        return cacheGroupList;
    }

    /**
     * 添加一个缓存组
     *
     * @param cacheGroup 缓存组
     * @since 1.0.0
     */
    public void addCacheGroup(CacheGroup cacheGroup) {
        cacheGroupList.add(cacheGroup);
    }

    @Override
    protected List<CacheGroup> getConfigList() {
        return cacheGroupList;
    }

    @Override
    protected String getConfigKey(CacheGroup cacheGroup) {
        return cacheGroup.getGroup();
    }

    /**
     * 根据缓存组名group获取指定的缓存组
     *
     * @param group 缓存组名
     * @return 缓存组
     * @since 1.0.0
     */
    public CacheGroup getCacheGroup(String group) {
        return getConfig(group);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
