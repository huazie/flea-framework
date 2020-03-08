package com.huazie.frame.cache.config;

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

    private List<CacheFile> cacheFiles = new ArrayList<CacheFile>();

    public List<CacheFile> getCacheFiles() {
        return cacheFiles;
    }

    /**
     * <p> 添加一个缓存定义配置文件 </p>
     *
     * @param cacheFile
     */
    public void addCacheFile(CacheFile cacheFile) {
        cacheFiles.add(cacheFile);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
