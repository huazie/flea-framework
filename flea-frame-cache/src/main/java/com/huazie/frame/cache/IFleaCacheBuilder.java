package com.huazie.frame.cache;

import com.huazie.frame.cache.config.CacheParams;
import com.huazie.frame.cache.config.CacheServer;

import java.util.List;

/**
 * <p> Flea缓存建造者接口类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaCacheBuilder {

    /**
     * <p> 构建Flea缓存对象 </p>
     *
     * @param name            缓存主关键字
     * @param cacheServerList 缓存服务器集
     * @param cacheParams     缓存参数集
     * @return Flea缓存对象
     * @since 1.0.0
     */
    AbstractFleaCache build(String name, List<CacheServer> cacheServerList, CacheParams cacheParams);

}
