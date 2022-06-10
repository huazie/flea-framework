package com.huazie.fleaframework.cache.config;

import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 其他缓存定义配置文件集，对应【flea-cache.xml】中
 * 【{@code <cache-files> </cache-files>}】
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class CacheFiles {

    private List<CacheFile> cacheFiles = new ArrayList<>();

    public List<CacheFile> getCacheFiles() {
        return cacheFiles;
    }

    /**
     * 添加一个缓存定义配置文件
     *
     * @param cacheFile 缓存定义配置文件
     * @since 1.0.0
     */
    public void addCacheFile(CacheFile cacheFile) {
        cacheFiles.add(cacheFile);
    }

    /**
     * 根据缓存数据键Key从其他缓存文件配置中获取指定的Flea缓存
     *
     * @param key 缓存数据键
     * @return Flea缓存
     * @since 1.0.0
     */
    public Cache getFleaCache(String key) {
        Cache cache = null;
        if (CollectionUtils.isNotEmpty(cacheFiles)) {
            for (CacheFile cacheFile : cacheFiles) {
                if (ObjectUtils.isNotEmpty(cacheFile)) {
                    cache = cacheFile.getFleaCache(key);
                    if (ObjectUtils.isNotEmpty(cache)) {
                        // 取到指定非空的缓存定义配置，则跳出循环
                        break;
                    }
                }
            }
        }
        return cache;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
