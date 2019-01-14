package com.huazie.frame.cache;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.huazie.frame.cache.common.CacheEnum;

/**
 *  
 *
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月22日
 *
 */
public abstract class AbstractFleaCache implements IFleaCache {
	
	private final static Logger LOGGER = Logger.getLogger(AbstractFleaCache.class);
	
	private Set<String> keySet = new HashSet<String>();
    private final String name;  //缓存主要关键字（用于区分）
    private final int expire;  	//失效时间（毫秒单位）
    
    protected CacheEnum cache;	//缓存类型
	
    public AbstractFleaCache(String name, int expire) {
		this.name = name;
		this.expire = expire;
	}
    
	@Override
	public Object get(String key) {
		Object value = null;
		try {
			value = this.getNativeValue(this.getNativeKey(key));
		} catch (Exception e) {
			AbstractFleaCache.LOGGER.warn("获取" + cache.getName() + "缓存出现异常", e);
		}
		return value;
	}

	@Override
	public void put(String key, Object value) {
		if (value == null)
			return;
        try {
        	this.putNativeValue(this.getNativeKey(key), value, expire);
            keySet.add(key);
        }catch (Exception e) {
        	AbstractFleaCache.LOGGER.warn("更新" + cache.getName() + "缓存出现异常", e);  
        }
	}

	@Override
	public void clear() {
		for (String key : keySet) {
        	delete(key);
        }
	}

	@Override
	public void delete(String key) {
		try {
            this.deleteNativeValue(this.getNativeKey(key));
        } catch (Exception e) {
        	AbstractFleaCache.LOGGER.warn("删除" + cache.getName() + "缓存出现异常", e);
        }
	}
	
	protected abstract Object getNativeValue(String key)throws Exception;
	
	protected abstract void putNativeValue(String key, Object value, int expire)throws Exception;
	
	protected abstract void deleteNativeValue(String key)throws Exception;
	
	protected String getNativeKey(String key){
		return name + "_" + key;  //可以自己定义
	}

	public String getName() {
		return name;
	}

	public int getExpire() {
		return expire;
	}
	
	public String getCacheName(){
		return cache.getName();
	}
	
	public String getCacheDesc(){
		return cache.getDesc();
	}
    
}
