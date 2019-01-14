package com.huazie.frame.cache.memcached.impl;

import org.apache.log4j.Logger;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.common.CacheEnum;
import com.whalin.MemCached.MemCachedClient;

/**
 * <p>
 * 		自定义Memcached缓存类
 * </p>
 * 
 * @author huazie
 * @version v1.0.0
 * @date 2017年2月6日
 * 
 */
public class MemcachedFleaCache extends AbstractFleaCache{
	
	private static final Logger LOGGER = Logger.getLogger(MemcachedFleaCache.class);
	
    private final MemCachedClient memcachedClient;  //Memcached客户端类
    
    public MemcachedFleaCache(String name, int expire, MemCachedClient memcachedClient) {
        super(name, expire);
        this.memcachedClient = memcachedClient;
        this.cache = CacheEnum.Memcached;
    }

	@Override
	protected Object getNativeValue(String key) throws Exception {
		if(MemcachedFleaCache.LOGGER.isDebugEnabled()){
			MemcachedFleaCache.LOGGER.debug("MemcachedFleaCacheImpl##getNativeValue KEY=" + key);
    	}
		return this.memcachedClient.get(key);
	}

	@Override
	protected void putNativeValue(String key, Object value, int expire) throws Exception {
		if(MemcachedFleaCache.LOGGER.isDebugEnabled()){
			MemcachedFleaCache.LOGGER.debug("MemcachedFleaCacheImpl##putNativeValue KEY=" + key);
    	}
		this.memcachedClient.set(key, value, expire);
	}

	@Override
	protected void deleteNativeValue(String key) throws Exception {
		if(MemcachedFleaCache.LOGGER.isDebugEnabled()){
			MemcachedFleaCache.LOGGER.debug("MemcachedFleaCacheImpl##deleteNativeValue KEY=" + key);
    	}
		this.memcachedClient.delete(key);
	}
   
}
