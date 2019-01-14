package com.huazie.frame.cache;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huazie.frame.cache.memcached.MemcachedFleaCacheManager;
import com.huazie.frame.cache.memcached.config.MemcachedConfig;

/**
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月22日
 *
 */
public class FleaCacheTest {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(FleaCacheTest.class);
	
	@Test
	public void testFleaCache()throws Exception{
		
		MemcachedFleaCacheManager manager = MemcachedFleaCacheManager.getInstance();
		
		AbstractFleaCache cache = manager.getCache("fleaparadetail");
		FleaCacheTest.LOGGER.debug("Cache={}",cache);
		
		//#### 1.  简单字符串
//		cache.put("menu1", "huazie");
		FleaCacheTest.LOGGER.debug(cache.get("menu1")+"");
		FleaCacheTest.LOGGER.debug(cache.getCacheName() + ">>>" + cache.getCacheDesc());
	}
	
	@Test
	public void testProperties()throws Exception{
		MemcachedConfig config = MemcachedConfig.getConfig();
		FleaCacheTest.LOGGER.debug(config.toString());
	}
}
