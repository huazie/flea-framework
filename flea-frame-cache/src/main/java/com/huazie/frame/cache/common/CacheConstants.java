package com.huazie.frame.cache.common;

/**
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年6月4日
 *
 */
public class CacheConstants {
	
	public interface MemcachedConfigConstants{
		/**
		 * memcache 文件名
		 */
		String MEMCACHE_FILE_NAME = "memcached.properties";
		/**
		 * Memcached服务器地址总数
		 */
		String MEMCACHED_CONFIG_SERVER_COUNT = "memcached.server.count";
		/**
		 * Memcached服务器地址
		 */
		String MEMCACHED_CONFIG_SERVER = "memcached.server";
		/**
		 * 初始化时对每个服务器建立的连接数目
		 */
		String MEMCACHED_CONFIG_INITCONN = "memcached.initConn";
		/**
		 * 每个服务器建立最小的连接数
		 */
		String MEMCACHED_CONFIG_MINCONN = "memcached.minConn";
		/**
		 * 每个服务器建立最大的连接数 
		 */
		String MEMCACHED_CONFIG_MAXCONN = "memcached.maxConn";
		/**
		 * 自查线程周期进行工作，其每次休眠时间  
		 */
		String MEMCACHED_CONFIG_MAINTSLEEP = "memcached.maintSleep";
		/**
		 * Socket的参数，如果是true在写数据时不缓冲，立即发送出去
		 */
		String MEMCACHED_CONFIG_NAGLE = "memcached.nagle";
		/**
		 * Socket阻塞读取数据的超时时间  
		 */
		String MEMCACHED_CONFIG_SOCKETTO = "memcached.socketTO";
		/**
		 * Socket连接超时时间
		 */
		String MEMCACHED_CONFIG_SOCKETCONNECTTO = "memcached.socketConnectTO";
		/**
		 * Memcached服务器权重分配
		 */
		String MEMCACHED_CONFIG_WEIGHT = "memcached.weight";
		/**
		 * Memcached分布式hash算法
		 */
		String MEMCACHED_CONFIG_HASHINGALG = "memcached.hashingAlg";
	}
	
}
