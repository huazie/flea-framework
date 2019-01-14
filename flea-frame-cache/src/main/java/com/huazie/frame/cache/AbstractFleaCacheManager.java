package com.huazie.frame.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <p>
 * 		抽象缓存管理类
 * </p>
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月22日
 *
 */
public abstract class AbstractFleaCacheManager {
	
	protected ConcurrentMap<String, AbstractFleaCache> cacheMap = new ConcurrentHashMap<String, AbstractFleaCache>(); 
	protected Map<String, Integer> configMap = new HashMap<String, Integer>();   //各缓存的时间Map
	
	protected Collection<? extends AbstractFleaCache> loadCaches() {
		Collection<AbstractFleaCache> values = cacheMap.values();  
        return values;
	}
	
	public AbstractFleaCache getCache(String name) {
		AbstractFleaCache cache = cacheMap.get(name);
		if(cache == null){
			Integer expire = configMap.get(name);
			if(expire == null){
				expire = 0;//表示永久
				configMap.put(name, expire);
			}
			cache = newCache(name, expire);
			cacheMap.put(name, cache);
		}
		return cache;
	}
	
	protected abstract AbstractFleaCache newCache(String name, int expire);
	
	public void setConfigMap(Map<String, Integer> configMap) {
		this.configMap = configMap;
	}
	
}
