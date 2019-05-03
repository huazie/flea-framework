package com.huazie.frame.cache.memcached.impl;

import org.apache.log4j.Logger;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.common.CacheEnum;
import com.whalin.MemCached.MemCachedClient;

/**
 * <p> 自定义Memcached缓存类 </p>
 * 
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
public class MemcachedFleaCache extends AbstractFleaCache{
	
	private static final Logger LOGGER = Logger.getLogger(MemcachedFleaCache.class);
	
    private final MemCachedClient memcachedClient;  // Memcached客户端类
    
    public MemcachedFleaCache(String name, int expire, MemCachedClient memcachedClient) {
        super(name, expire);
        this.memcachedClient = memcachedClient;
        this.cache = CacheEnum.Memcached;
    }

	@Override
	protected Object getNativeValue(String key) throws Exception {
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("MemcachedFleaCacheImpl##getNativeValue(String) KEY=" + key);
    	}
		return this.memcachedClient.get(key);
	}

	@Override
	protected void putNativeValue(String key, Object value, int expire) throws Exception {
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("MemcachedFleaCacheImpl##putNativeValue(String, Object, int) KEY=" + key);
			LOGGER.debug("MemcachedFleaCacheImpl##putNativeValue(String, Object, int) VALUE=" + value);
    	}
		this.memcachedClient.set(key, value, expire);
	}

	@Override
	protected void deleteNativeValue(String key) throws Exception {
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("MemcachedFleaCacheImpl##deleteNativeValue(String) KEY=" + key);
    	}
		this.memcachedClient.delete(key);
	}
   
}
