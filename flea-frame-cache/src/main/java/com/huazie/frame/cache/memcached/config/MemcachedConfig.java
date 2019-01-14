package com.huazie.frame.cache.memcached.config;

import java.util.Properties;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.huazie.frame.cache.common.CacheConstants;
import com.huazie.frame.common.util.PropertiesUtil;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

/**
 * <p>
 * 		Memcached缓存配置类
 * </p>
 * 
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月22日
 *
 */
public class MemcachedConfig {
	
	private static MemcachedConfig config = null;
	
	private static Properties prop;
	
	private static MemCachedClient client;
	
	private String[] servers;	//服务器地址 
	
	private Integer[] weights;	//Memcached权重分配
	
	private int initConn;		//初始化时对每个服务器建立的连接数目
	
	private int minConn;		//每个服务器建立最小的连接数  
	
	private int maxConn;		//每个服务器建立最大的连接数  
	
	private int maintSleep;		//自查线程周期进行工作，其每次休眠时间  
	
	private boolean nagle;		//Socket的参数，如果是true在写数据时不缓冲，立即发送出去 
	
	private int socketTO;		//Socket阻塞读取数据的超时时间
	
	private int socketConnectTO;//Socket连接的超时时间
	
	private int hashingAlg;		// 一致性hash算法 
	
	static {
		// 获取配置文件
		prop = PropertiesUtil.getProperties(CacheConstants.MemcachedConfigConstants.MEMCACHE_FILE_NAME);
	}
	
	/**
	 * <p>
	 * 		读取Memcached缓存相关连接配置
	 * </p>
	 * @version v1.0.0
	 * @date 2018年1月23日
	 *
	 * @return
	 */
	public static MemcachedConfig getConfig()throws Exception{
		
		if(config == null){

			if(prop == null){
				return config;
			}
			
			int serverCount = PropertiesUtil.getIntegerValue(prop, CacheConstants.MemcachedConfigConstants.MEMCACHED_CONFIG_SERVER_COUNT);
			if(serverCount > 0){
				String[] servers = new String[serverCount];
				for(int i = 0; i < serverCount; i++){
					String server = PropertiesUtil.getStringValue(prop, CacheConstants.MemcachedConfigConstants.MEMCACHED_CONFIG_SERVER + (i+1));
					servers[i] = server;
				}
				
				Integer[] weights = new Integer[serverCount];
				for(int i = 0; i < serverCount; i++){
					Integer weight = PropertiesUtil.getIntegerValue(prop, CacheConstants.MemcachedConfigConstants.MEMCACHED_CONFIG_WEIGHT + (i+1));
					weights[i] = weight;
				}
				
				config = new MemcachedConfig();
				config.setServers(servers);
				config.setWeights(weights);
				config.setInitConn(PropertiesUtil.getIntegerValue(prop, CacheConstants.MemcachedConfigConstants.MEMCACHED_CONFIG_INITCONN));
				config.setMinConn(PropertiesUtil.getIntegerValue(prop, CacheConstants.MemcachedConfigConstants.MEMCACHED_CONFIG_MINCONN));
				config.setMaxConn(PropertiesUtil.getIntegerValue(prop, CacheConstants.MemcachedConfigConstants.MEMCACHED_CONFIG_MAXCONN));
				config.setMaintSleep(PropertiesUtil.getIntegerValue(prop, CacheConstants.MemcachedConfigConstants.MEMCACHED_CONFIG_MAINTSLEEP));
				config.setNagle(PropertiesUtil.getBooleanValue(prop, CacheConstants.MemcachedConfigConstants.MEMCACHED_CONFIG_NAGLE));
				config.setSocketTO(PropertiesUtil.getIntegerValue(prop, CacheConstants.MemcachedConfigConstants.MEMCACHED_CONFIG_SOCKETTO));
				config.setSocketConnectTO(PropertiesUtil.getIntegerValue(prop, CacheConstants.MemcachedConfigConstants.MEMCACHED_CONFIG_SOCKETCONNECTTO));
				config.setHashingAlg(PropertiesUtil.getIntegerValue(prop, CacheConstants.MemcachedConfigConstants.MEMCACHED_CONFIG_HASHINGALG));
			}
		}
		return config;
	}
	
	/**
	 * <p>
	 * 		获取memcached客户端
	 * </p>
	 * @version v1.0.0
	 * @date 2018年1月23日
	 *
	 * @return
	 */
	public static MemCachedClient getClient(){
		if(client == null){
			client = new MemCachedClient(); 
		}
		return client;
	}
	
	/**
	 * <p>
	 * 		初始化memcached连接池
	 * </p>
	 * @version v1.0.0
	 * @date 2018年1月23日
	 *
	 * @throws Exception
	 */
	public static void initSockIOPool(){
		try {
			MemcachedConfig config = MemcachedConfig.getConfig();
			SockIOPool sockIOPool = SockIOPool.getInstance();
			sockIOPool.setServers(config.getServers());
			sockIOPool.setWeights(config.getWeights());
			sockIOPool.setInitConn(config.getInitConn());
			sockIOPool.setMinConn(config.getMinConn());
			sockIOPool.setMaxConn(config.getMaxConn());
			sockIOPool.setMaintSleep(config.getMaintSleep());
			sockIOPool.setNagle(config.isNagle());
			sockIOPool.setSocketTO(config.getSocketTO());
			sockIOPool.setSocketConnectTO(config.getSocketConnectTO());
			sockIOPool.setHashingAlg(config.getHashingAlg());
			sockIOPool.initialize();
		} catch (Exception e) {
		}
	}

	public String[] getServers() {
		return servers;
	}

	public void setServers(String[] servers) {
		this.servers = servers;
	}

	public Integer[] getWeights() {
		return weights;
	}

	public void setWeights(Integer[] weights) {
		this.weights = weights;
	}

	public int getInitConn() {
		return initConn;
	}

	public void setInitConn(int initConn) {
		this.initConn = initConn;
	}

	public int getMinConn() {
		return minConn;
	}

	public void setMinConn(int minConn) {
		this.minConn = minConn;
	}

	public int getMaxConn() {
		return maxConn;
	}

	public void setMaxConn(int maxConn) {
		this.maxConn = maxConn;
	}

	public int getMaintSleep() {
		return maintSleep;
	}

	public void setMaintSleep(int maintSleep) {
		this.maintSleep = maintSleep;
	}

	public boolean isNagle() {
		return nagle;
	}

	public void setNagle(boolean nagle) {
		this.nagle = nagle;
	}

	public int getSocketTO() {
		return socketTO;
	}

	public void setSocketTO(int socketTO) {
		this.socketTO = socketTO;
	}

	public int getSocketConnectTO() {
		return socketConnectTO;
	}

	public void setSocketConnectTO(int socketConnectTO) {
		this.socketConnectTO = socketConnectTO;
	}

	public int getHashingAlg() {
		return hashingAlg;
	}

	public void setHashingAlg(int hashingAlg) {
		this.hashingAlg = hashingAlg;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
