package com.huazie.frame.cache.core.impl;

import com.huazie.frame.cache.AbstractSpringCache;
import com.huazie.frame.cache.IFleaCache;

/**
 * 核心Spring缓存类，继承了抽象Spring缓存父类的读【{@code get}】、
 * 写【{@code put}】、删除【{@code delete}】和清空【{@code clear}】
 * 缓存的基本操作方法，由核心Spring缓存管理类初始化。
 *
 * <p> 它初始化时，需要一个具体Flea缓存实现，这里我们使用
 * Core Flea缓存【{@code CoreFleaCache}】。
 *
 * @author huazie
 * @version 1.0.0
 * @see CoreFleaCache
 * @since 1.0.0
 */
public class CoreSpringCache extends AbstractSpringCache {

    /**
     * 传入具体Flea缓存实现，用以初始化核心Spring缓存
     *
     * @param name      缓存数据主关键字
     * @param fleaCache Flea缓存具体实现
     * @since 1.0.0
     */
    public CoreSpringCache(String name, IFleaCache fleaCache) {
        super(name, fleaCache);
    }

    /**
     * 用核心Flea缓存，来初始化核心Spring缓存
     *
     * @param name 缓存数据主关键字
     * @since 1.0.0
     */
    public CoreSpringCache(String name) {
        this(name, new CoreFleaCache(name));
    }
}
