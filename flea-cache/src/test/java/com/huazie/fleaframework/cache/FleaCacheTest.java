package com.huazie.fleaframework.cache;

import com.huazie.fleaframework.cache.common.CacheEnum;
import com.huazie.fleaframework.cache.common.FleaCacheManagerFactory;
import com.huazie.fleaframework.cache.memcached.config.MemCachedConfig;
import com.huazie.fleaframework.cache.redis.RedisSentinelPool;
import com.huazie.fleaframework.cache.redis.config.RedisClusterConfig;
import com.huazie.fleaframework.cache.redis.config.RedisSentinelConfig;
import com.huazie.fleaframework.cache.redis.config.RedisShardedConfig;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Flea 缓存单测类
 *
 * @author huazie
 * @version 1.0.0
 */
public class FleaCacheTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaCacheTest.class);

    @Test
    public void testProperties() {
        MemCachedConfig memCachedConfig = MemCachedConfig.getConfig();
        LOGGER.debug("MemCachedConfig={}", memCachedConfig);
        RedisShardedConfig redisShardedConfig = RedisShardedConfig.getConfig();
        LOGGER.debug("RedisShardedConfig={}", redisShardedConfig);
        RedisClusterConfig redisClusterConfig = RedisClusterConfig.getConfig();
        LOGGER.debug("RedisClusterConfig={}", redisClusterConfig);
        RedisSentinelConfig redisSentinelConfig = RedisSentinelConfig.getConfig();
        LOGGER.debug("RedisSentinelConfig={}", redisSentinelConfig);
    }

    @Test
    public void testMemcachedExpire() {
        Date date = new Date(1000);
        long aa = date.getTime() / 1000;
        LOGGER.debug("huazie:{}", aa);
    }

    @Test
    public void testRedis() {
        Jedis jedis = new Jedis("127.0.0.1", 10003);
        jedis.auth("huazie123");
        //查看服务是否运行
        LOGGER.debug("服务正在运行: {} ", jedis.ping());

        // #1. redis 字符串数据
        //jedis.set("huazie", "hello world");

        // #2. 获取redis字符串
        String msg = jedis.get("huazie");
        LOGGER.debug("value = {}", msg);

        // #3. 存储数据到列表中
//        jedis.lpush("huazie-list", "Beijing");
//        jedis.lpush("huazie-list", "Shanghai");
//        jedis.lpush("huazie-list", "Hangzhou");

        // #4. 获取列表数据输出
        List<String> list = jedis.lrange("huazie-list", 0, 2);
        LOGGER.debug("list = {}", list);

        // #5. 获取数据并输出
        Set<String> keys = jedis.keys("*");
        Iterator<String> it = keys.iterator();
        int i = 1;
        while (it.hasNext()) {
            String key = it.next();
            LOGGER.debug("key{} = {}", i++, key);
        }

        // #6. 删除数据
        LOGGER.debug("del ret = {}", jedis.del("huazie-list"));

    }

    @Test
    public void testJedisCluster() {
        // 集群的服务节点Set集合
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("127.0.0.1", 20011));
        nodes.add(new HostAndPort("127.0.0.1", 20012));
        nodes.add(new HostAndPort("127.0.0.1", 20021));
        nodes.add(new HostAndPort("127.0.0.1", 20022));
        nodes.add(new HostAndPort("127.0.0.1", 20031));
        nodes.add(new HostAndPort("127.0.0.1", 20032));

        // Jedis连接池配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大空闲连接数, 默认8个
        jedisPoolConfig.setMaxIdle(100);
        // 最大连接数, 默认8个
        jedisPoolConfig.setMaxTotal(500);
        //最小空闲连接数, 默认0
        jedisPoolConfig.setMinIdle(0);
        // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        jedisPoolConfig.setMaxWaitMillis(2000); // 设置2秒
        //对拿到的connection进行validateObject校验
        jedisPoolConfig.setTestOnBorrow(true);

        int connectionTimeout = 2000;
        int soTimeout = 2000;
        int maxAttempts = 5;
        String password = "huazie123";
        String clientName = "flea-redis-cluster";
        JedisCluster jedis = new JedisCluster(nodes, connectionTimeout, soTimeout, maxAttempts, password, clientName, jedisPoolConfig);

        // jedis.set("huazie", "hello world");

        LOGGER.debug("get huazie = {}", jedis.get("huazie"));
    }

    @Test
    public void testJedisSentinel() {
        // 哨兵的集合
        Set<String> sentinels = new HashSet<>();
        sentinels.add("127.0.0.1:36379"); // 替换为你的哨兵地址和端口
        sentinels.add("127.0.0.1:36380"); // 可以添加多个哨兵
        sentinels.add("127.0.0.1:36381"); // 可以添加多个哨兵

        // 哨兵的主节点名称
        String masterName = "mymaster"; // 替换为你的哨兵配置中的主节点名称

        // 创建哨兵池
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        JedisSentinelPool sentinelPool = new JedisSentinelPool(masterName, sentinels, poolConfig);

        try (Jedis jedis = sentinelPool.getResource()) {
            // 连接到主节点后，执行一些 Redis 操作
            jedis.set("author", "huazie");
            String author = jedis.get("author");
            System.out.println("Author = " + author);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭哨兵池
            sentinelPool.close();
        }
    }

    @Test
    public void testJedisSentinelNew() {

        RedisSentinelPool pool = RedisSentinelPool.getInstance();
        // 初始化默认连接池
        pool.initialize(0);

        JedisSentinelPool sentinelPool = pool.getJedisSentinelPool();

        try (Jedis jedis = sentinelPool.getResource()) {
            // 连接到主节点后，执行一些 Redis 操作
            jedis.set("author", "huazie");
            String author = jedis.get("author");
            System.out.println("Author = " + author);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭哨兵池
            sentinelPool.close();
        }
    }

    @Test
    public void testMemeCachedFleaCache() {

        try {
            AbstractFleaCacheManager manager = FleaCacheManagerFactory.getFleaCacheManager(CacheEnum.MemCached.getName());
            AbstractFleaCache cache = manager.getCache("fleaconfigdata");
            LOGGER.debug("Cache={}", cache);
            //#### 1.  简单字符串
            cache.put("author", "huazie");
            cache.put("other", null);
//            cache.get("author");
//            cache.get("other");
//            cache.delete("author");
//            cache.delete("other");
//            cache.clear();
            cache.getCacheKey();
            LOGGER.debug(cache.getCacheName() + ">>>" + cache.getCacheDesc());
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testRedisShardedFleaCache() {
        try {
            // 分片模式下Flea缓存管理类
            AbstractFleaCacheManager manager = FleaCacheManagerFactory.getFleaCacheManager(CacheEnum.RedisSharded.getName());
            AbstractFleaCache cache = manager.getCache("fleaconfigdata");
            LOGGER.debug("Cache={}", cache);
            //#### 1.  简单字符串
            cache.put("author", "huazie");
            cache.put("other", null);
//            cache.get("author");
//            cache.get("other");
//            cache.delete("author");
//            cache.delete("other");
//            cache.clear();
            cache.getCacheKey();
            LOGGER.debug(cache.getCacheName() + ">>>" + cache.getCacheDesc());
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testCoreFleaCache() {
        try {
            AbstractFleaCacheManager manager = FleaCacheManagerFactory.getFleaCacheManager(CacheEnum.FleaCore.getName());
            AbstractFleaCache cache = manager.getCache("fleaconfigdata");
            LOGGER.debug("Cache={}", cache);
            //#### 1.  简单字符串
            cache.put("author", "huazie");
            cache.put("other", null);
//            cache.get("author");
//            cache.get("other");
//            cache.delete("author");
//            cache.delete("other");
//            cache.clear();
            cache.getCacheKey();
            LOGGER.debug(cache.getCacheName() + ">>>" + cache.getCacheDesc());
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testRedisClusterFleaCache() {
        try {
            // 集群模式下Flea缓存管理类
            AbstractFleaCacheManager manager = FleaCacheManagerFactory.getFleaCacheManager(CacheEnum.RedisCluster.getName());
            AbstractFleaCache cache = manager.getCache("fleaconfigdata");
            LOGGER.debug("Cache={}", cache);
            //#### 1.  简单字符串
            cache.put("author", "huazie");
            cache.put("other", null);
//            cache.get("author");
//            cache.get("other");
//            cache.delete("author");
//            cache.delete("other");
            cache.clear();
            cache.getCacheKey();
            LOGGER.debug(cache.getCacheName() + ">>>" + cache.getCacheDesc());
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testCoreFleaCacheForRedisCluster() {
        try {
            AbstractFleaCacheManager manager = FleaCacheManagerFactory.getFleaCacheManager(CacheEnum.FleaCore.getName());
            AbstractFleaCache cache = manager.getCache("fleaconfigdata");
            LOGGER.debug("Cache={}", cache);
            //#### 1.  简单字符串
            cache.put("author", "huazie");
            cache.put("other", null);
//            cache.get("author");
//            cache.get("other");
//            cache.delete("author");
//            cache.delete("other");
            cache.clear();
            cache.getCacheKey();
            LOGGER.debug(cache.getCacheName() + ">>>" + cache.getCacheDesc());
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testRedisSentinelFleaCache() {
        try {
            // 哨兵模式下Flea缓存管理类，复用原有获取方式
//            AbstractFleaCacheManager manager = FleaCacheManagerFactory.getFleaCacheManager(CacheEnum.RedisSentinel.getName());
            // 哨兵模式下Flea缓存管理类，指定数据库索引
            AbstractFleaCacheManager manager = FleaCacheManagerFactory.getFleaCacheManager(0);
            AbstractFleaCache cache = manager.getCache("fleaconfigdata");
            LOGGER.debug("Cache={}", cache);
            //#### 1.  简单字符串
            cache.put("author", "huazie");
            cache.put("other", null);
//            cache.get("author");
//            cache.get("other");
//            cache.delete("author");
//            cache.delete("other");
//            cache.clear();
            cache.getCacheKey();
            LOGGER.debug(cache.getCacheName() + ">>>" + cache.getCacheDesc());
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

}
