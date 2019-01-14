package com.huazie.frame.cache.redis;

import com.huazie.frame.cache.AbstractSpringCache;
import com.huazie.frame.cache.AbstractSpringCacheManager;

/**
 * <p>
 * 		MemcachedCacheManager管理类
 * </p>
 * 
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月19日
 *
 */
public class RedisFleaCacheManager extends AbstractSpringCacheManager{

	@Override
	protected AbstractSpringCache newCache(String name, int expire) {
		return null;
	}

}
