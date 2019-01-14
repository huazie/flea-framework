package com.huazie.frame.cache;

/**
 * <p> 
 * 		自定义Cache接口类（主要定义了一些增删改查的方法）
 * </p>
 * 
 * @author huazie
 * @version v1.0.0
 * @date 2017年2月8日
 * 
 */
public interface IFleaCache {
	
	public Object get(String key);
	
	public void put(String key, Object value);
	
	public void clear();
	
	public void delete(String key);

}
