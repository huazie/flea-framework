package com.huazie.frame.cache.core;

import com.huazie.frame.cache.AbstractSpringCache;
import com.huazie.frame.cache.AbstractSpringCacheManager;
import com.huazie.frame.cache.core.impl.CoreSpringCache;

/**
 * 核心Spring缓存管理类，用于接入Spring框架管理核心Spring缓存。
 *
 * <p> 核心Spring缓存是Flea框架提供出来的整合各类缓存的缓存实现。
 *
 * <p> 方法【{@code newCache}】用于创建一个核心Spring缓存，
 * 而它内部是由核心Flea缓存【{@code CoreFleaCache}】实现具体的
 * 读、写、删除 和 清空 缓存的基本操作。
 *
 * @author huazie
 * @version 1.0.0
 * @see CoreSpringCache
 * @since 1.0.0
 */
public class CoreSpringCacheManager extends AbstractSpringCacheManager {

    @Override
    protected AbstractSpringCache newCache(String name, int expiry) {
        return new CoreSpringCache(name);
    }
}
