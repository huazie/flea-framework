package com.huazie.frame.cache;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huazie.frame.cache.memcached.MemcachedSpringCacheManager;

public class SpringCacheTest {

	private final static Logger LOGGER = LoggerFactory.getLogger(SpringCacheTest.class);

	@Test
	public void test() {
		ApplicationContext applicationContext = null;
		try {
			applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
			SpringCacheTest.LOGGER.debug(applicationContext+"");
			
			MemcachedSpringCacheManager memcachedCacheManager = (MemcachedSpringCacheManager) applicationContext.getBean("cacheManager");
			SpringCacheTest.LOGGER.debug("MemcachedCacheManager={}",memcachedCacheManager);
			
			AbstractSpringCache cache = memcachedCacheManager.getCache("fleaparadetail");
			SpringCacheTest.LOGGER.debug("Cache={}",cache);
			
			//#### 1.  简单字符串
//			cache.put("menu1", "huazie");
			SpringCacheTest.LOGGER.debug(cache.get("menu1", String.class));
			
			//#### 2.  简单对象(要是可以序列化的对象)
//			String user = new String("huazie");
//			cache.put("user", user);
//			TestCache.LOGGER.debug(cache.get("user", String.class));
			
			//#### 3.  List塞对象
//			List<String> userList = new ArrayList<String>();
//			userList.add("huazie");
//			userList.add("lgh");
//			cache.put("user_list", userList);

//			TestCache.LOGGER.debug(cache.get("user_list",userList.getClass()).toString());
			
		} catch (Exception e) {
			SpringCacheTest.LOGGER.error("",e);
		}
	}
	
}
