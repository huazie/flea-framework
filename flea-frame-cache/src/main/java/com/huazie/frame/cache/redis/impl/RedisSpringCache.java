package com.huazie.frame.cache.redis.impl;

import com.huazie.frame.cache.AbstractSpringCache;
import com.huazie.frame.cache.IFleaCache;

/**
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月19日
 *
 */
public class RedisSpringCache extends AbstractSpringCache {

	public RedisSpringCache(String name, IFleaCache fleaCache) {
		super(name, fleaCache);
	}

}
