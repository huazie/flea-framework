package com.huazie.frame.cache.core.impl;

import com.huazie.frame.cache.AbstractSpringCache;
import com.huazie.frame.cache.IFleaCache;

/**
 * <p> 核心Spring缓存类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class CoreSpringCache extends AbstractSpringCache {

    /**
     * <p> 带参数构造方法 </p>
     *
     * @param name      缓存主关键字
     * @param fleaCache Flea Cache具体实现
     * @since 1.0.0
     */
    public CoreSpringCache(String name, IFleaCache fleaCache) {
        super(name, fleaCache);
    }

    /**
     * <p> 带参数构造方法 </p>
     *
     * @param name 缓存主关键字
     * @since 1.0.0
     */
    public CoreSpringCache(String name) {
        super(name, new CoreFleaCache(name));
    }
}
