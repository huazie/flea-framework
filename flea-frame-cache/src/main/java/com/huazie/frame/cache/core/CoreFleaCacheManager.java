package com.huazie.frame.cache.core;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.AbstractFleaCacheManager;
import com.huazie.frame.cache.core.impl.CoreFleaCache;

/**
 * 核心Flea缓存管理类，用于接入Flea框架管理核心Flea缓存。
 *
 * <p> 核心Flea缓存是Flea框架提供出来的整合各类缓存的缓存实现。
 *
 * <p> 方法 {@code newCache}，用于创建一个核心Flea缓存，
 * 它里面包含了读、写、删除和清空缓存的基本操作。
 *
 * @author huazie
 * @version 1.0.0
 * @see CoreFleaCache
 * @since 1.0.0
 */
public class CoreFleaCacheManager extends AbstractFleaCacheManager {

    @Override
    protected AbstractFleaCache newCache(String name, int expiry) {
        return new CoreFleaCache(name);
    }
}
