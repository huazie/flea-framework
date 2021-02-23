package com.huazie.frame.cache.config;

import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.ObjectUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> 其他缓存定义配置文件集合 </p>
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
     * <p> 添加一个缓存定义配置文件 </p>
     *
     * @param cacheFile 缓存定义配置文件
     * @since 1.0.0
     */
    public void addCacheFile(CacheFile cacheFile) {
        cacheFiles.add(cacheFile);
    }

    /**
     * <p> 根据缓存数据键Key从其他缓存文件配置中获取指定的Flea缓存 </p>
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
