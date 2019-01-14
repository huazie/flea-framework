package com.huazie.frame.cache.memcached.impl;


import com.huazie.frame.cache.AbstractSpringCache;
import com.huazie.frame.cache.IFleaCache;
import com.whalin.MemCached.MemCachedClient;

/**
 * <p>
 * 		封装MemcachedCache的实现，主要实现spring的cache接口 
 * </p>
 * 
 * @author huazie
 * @version v1.0.0
 * @date 2017年2月6日
 *
 */
public class MemcachedSpringCache extends AbstractSpringCache{
	
	public MemcachedSpringCache(String name, IFleaCache fleaCache) {
		super(name, fleaCache);
	}

	public MemcachedSpringCache(String name, int expire, MemCachedClient memcachedClient) {
		super(name, new MemcachedFleaCache(name, expire, memcachedClient));
	}
	
}
