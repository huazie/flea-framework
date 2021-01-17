package com.huazie.frame.cache.config;

import com.huazie.frame.common.util.MapUtils;
import com.huazie.frame.common.util.ObjectUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> 缓存服务器列表 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class CacheServers {

    private List<CacheServer> cacheServerList = new ArrayList<>(); // 缓存服务器列表中的各个缓存服务器

    public List<CacheServer> getCacheServerList() {
        return cacheServerList;
    }

    /**
     * <p> 添加一个缓存服务器 </p>
     *
     * @param cacheServer 缓存服务器
     * @since 1.0.0
     */
    public void addCacheServer(CacheServer cacheServer) {
        cacheServerList.add(cacheServer);
    }

    /**
     * <p> 根据缓存服务器名group获取指定的缓存服务器集 </p>
     *
     * @param group 缓存服务器归属组
     * @return 缓存服务器集
     * @since 1.0.0
     */
    public List<CacheServer> getCacheServersByGroup(String group) {
        List<CacheServer> cacheServers = null;
        Map<String, List<CacheServer>> cacheServerMap = toCacheServerMap();
        if (MapUtils.isNotEmpty(cacheServerMap)) {
            cacheServers = cacheServerMap.get(group);
        }
        return cacheServers;
    }

    /**
     * <p> 获取指定缓存服务器列表中的缓存服务器的Map，便于根据各缓存服务器的服务器归属组group查找 </p>
     *
     * @return 缓存服务器的Map
     * @since 1.0.0
     */
    private Map<String, List<CacheServer>> toCacheServerMap() {
        Map<String, List<CacheServer>> cacheServerMap = new HashMap<>();
        for (CacheServer cacheServer : cacheServerList) {
            if (ObjectUtils.isNotEmpty(cacheServer)) {
                String group = cacheServer.getGroup();
                List<CacheServer> cServerList;
                if (cacheServerMap.containsKey(group)) {
                    cServerList = cacheServerMap.get(group);
                    cServerList.add(cacheServer);
                } else {
                    cServerList = new ArrayList<>();
                    cServerList.add(cacheServer);
                    cacheServerMap.put(group, cServerList);
                }
            }
        }
        return cacheServerMap;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
