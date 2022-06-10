package com.huazie.fleaframework.cache.config;

import com.huazie.fleaframework.common.config.ConfigListMap;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 缓存服务器集，对应【flea-cache-config.xml】中
 * 【{@code <cache-servers> </cache-servers>}】
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class CacheServers extends ConfigListMap<CacheServer> {

    private List<CacheServer> cacheServerList = new ArrayList<>(); // 缓存服务器集中的各个缓存服务器

    public List<CacheServer> getCacheServerList() {
        return cacheServerList;
    }

    /**
     * 添加一个缓存服务器
     *
     * @param cacheServer 缓存服务器
     * @since 1.0.0
     */
    public void addCacheServer(CacheServer cacheServer) {
        cacheServerList.add(cacheServer);
        addConfig(cacheServer);
    }

    @Override
    protected String getConfigKey(CacheServer cacheServer) {
        return cacheServer.getGroup();
    }

    /**
     * 根据缓存服务器名group获取指定的缓存服务器集
     *
     * @param group 缓存服务器归属组
     * @return 缓存服务器集
     * @since 1.0.0
     */
    public List<CacheServer> getCacheServersByGroup(String group) {
        return getConfigList(group);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
