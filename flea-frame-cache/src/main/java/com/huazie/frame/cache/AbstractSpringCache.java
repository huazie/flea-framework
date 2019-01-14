package com.huazie.frame.cache;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

/**
 * <p>
 * 		抽象缓存类，实现Spring的Cache
 * </p>
 * 
 * @author huazie
 * @version v1.0.0
 * @date 2017年2月8日
 *
 */
public abstract class AbstractSpringCache implements Cache {
	
	private final static Logger LOGGER = Logger.getLogger(AbstractSpringCache.class);
	
	protected final String name;  //缓存主要关键字（用于区分）
	
	protected final IFleaCache fleaCache;
	
	public AbstractSpringCache(String name, IFleaCache fleaCache) {
		this.name = name;
		this.fleaCache = fleaCache;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public IFleaCache getNativeCache() {
		return this.fleaCache;
	}

	@Override
	public ValueWrapper get(Object key) {
		ValueWrapper wrapper = null;
		Object cacheValue = this.fleaCache.get(key.toString());
		if(cacheValue != null){
			if(AbstractSpringCache.LOGGER.isDebugEnabled()){
				AbstractSpringCache.LOGGER.debug("AbstractSpringCache##get Get Value:" + cacheValue);
			}
			wrapper = new SimpleValueWrapper(cacheValue);
		}
		return wrapper;
	}

	@Override
	public void put(Object key, Object value) {
		this.fleaCache.put(key.toString(), value);
	}

	@Override
	public void evict(Object key) {
		this.fleaCache.delete(key.toString());		
	}

	@Override
	public void clear() {
		this.fleaCache.clear();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Object key, Class<T> type) {
        Object cacheValue = this.fleaCache.get(key.toString());
        Object value = (cacheValue != null ? cacheValue : null); 
        if (type != null && !type.isInstance(value)) {
            throw new IllegalStateException("Cached value is not of required type [" + type.getName() + "]: " + value);  
        }
        return (T)value;
    }
	
	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		ValueWrapper wrapper = null;
		Object cacheValue = this.fleaCache.get(key.toString());
		if(cacheValue == null){
			this.fleaCache.put(key.toString(), value);
		}else{
			if(AbstractSpringCache.LOGGER.isDebugEnabled()){
				AbstractSpringCache.LOGGER.debug("AbstractSpringCache##putIfAbsent Find Value:" + cacheValue);
			}
			wrapper = new SimpleValueWrapper(cacheValue);
		}
		return wrapper;
	} 

	@Override
	public <T> T get(Object key, Callable<T> valueLoader) {
		return null;
	}
	
}
