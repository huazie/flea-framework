package com.huazie.frame.cache.memcached;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.AbstractFleaCacheManager;
import com.huazie.frame.cache.memcached.config.MemcachedConfig;
import com.huazie.frame.cache.memcached.impl.MemcachedFleaCache;
import com.whalin.MemCached.MemCachedClient;

/**
 * <p>
 * 		缓存管理类
 * </p>
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月22日
 *
 */
public class MemcachedFleaCacheManager extends AbstractFleaCacheManager{
	
	private static MemcachedFleaCacheManager cacheManager;
	private MemCachedClient memcachedClient;   //memcached客户端类
	
	private MemcachedFleaCacheManager(MemCachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

	/**
	 * <p>
	 * 		获取缓存管理类
	 * </p>
	 * @version v1.0.0
	 * @date 2018年1月23日
	 *
	 * @return
	 */
	public static MemcachedFleaCacheManager getInstance(){
		if(cacheManager == null){
			// 初始化连接池
			MemcachedConfig.initSockIOPool();
			cacheManager = new MemcachedFleaCacheManager(MemcachedConfig.getClient());
		}
		return cacheManager;
	}
	
	@Override
	protected AbstractFleaCache newCache(String name, int expire) {
		return new MemcachedFleaCache(name, expire, this.memcachedClient);
	}

	public void setMemcachedClient(MemCachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}
	
}
