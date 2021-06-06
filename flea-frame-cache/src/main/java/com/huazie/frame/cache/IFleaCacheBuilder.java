package com.huazie.frame.cache;

import com.huazie.frame.cache.config.CacheServer;

import java.util.List;

/**
 * Flea缓存建造者接口类，定义了构建Flea缓存对象的通用接口。
 *
 * <p> 该接口由Flea缓存工厂类【{@code FleaCacheFactory}】使用，
 * 根据不同缓存数据归属缓存组所在的缓存类型，读取缓存配置中的
 * Flea缓存建造者配置项集，通过反射实例化具体的Flea缓存。
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
     * @return Flea缓存对象
     * @since 1.0.0
     */
    AbstractFleaCache build(String name, List<CacheServer> cacheServerList);

}
