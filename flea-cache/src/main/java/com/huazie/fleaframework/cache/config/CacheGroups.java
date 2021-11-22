package com.huazie.fleaframework.cache.config;

import com.huazie.fleaframework.common.util.MapUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 缓存组集，对应【flea-cache-config.xml】中
 * 【{@code <cache-groups> </cache-groups>}】
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class CacheGroups {

    private List<CacheGroup> cacheGroupList = new ArrayList<>(); // 缓存组集中的各个缓存组

    public List<CacheGroup> getCacheGroupList() {
        return cacheGroupList;
    }

    /**
     * <p> 添加一个缓存组 </p>
     *
     * @param cacheGroup 缓存组
     * @since 1.0.0
     */
    public void addCacheGroup(CacheGroup cacheGroup) {
        cacheGroupList.add(cacheGroup);
    }

    /**
     * <p> 根据缓存组名group获取指定的缓存组 </p>
     *
     * @param group 缓存组名
     * @return 缓存组
     * @since 1.0.0
     */
    public CacheGroup getCacheGroup(String group) {
        CacheGroup cacheGroup = null;
        Map<String, CacheGroup> cacheGroupMap = toCacheGroupMap();
        if (MapUtils.isNotEmpty(cacheGroupMap)) {
            cacheGroup = cacheGroupMap.get(group);
        }
        return cacheGroup;
    }

    /**
     * <p> 获取指定缓存组列表中的缓存组的Map，便于根据各缓存组的组名group查找 </p>
     *
     * @return 缓存组的Map
     * @since 1.0.0
     */
    private Map<String, CacheGroup> toCacheGroupMap() {
        Map<String, CacheGroup> cacheGroupMap = new HashMap<>();
        for (CacheGroup cacheGroup : cacheGroupList) {
            cacheGroupMap.put(cacheGroup.getGroup(), cacheGroup);
        }
        return cacheGroupMap;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
