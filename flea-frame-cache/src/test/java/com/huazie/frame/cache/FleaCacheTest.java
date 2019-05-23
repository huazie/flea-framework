package com.huazie.frame.cache;

import com.huazie.frame.cache.memcached.MemCachedFleaCacheManager;
import com.huazie.frame.cache.memcached.config.MemCachedConfig;
import com.huazie.frame.cache.redis.RedisPool;
import com.huazie.frame.cache.redis.config.RedisConfig;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月22日
 */
public class FleaCacheTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(FleaCacheTest.class);

    @Test
    public void testFleaCache() {

        MemCachedFleaCacheManager manager = MemCachedFleaCacheManager.getInstance();
        manager.initPool();
        AbstractFleaCache cache = manager.getCache("fleaparadetail");
        LOGGER.debug("Cache={}", cache);

        //#### 1.  简单字符串
//		cache.put("menu1", "huazie");
        cache.get("menu1");
//        cache.delete("menu1");
        LOGGER.debug(cache.getCacheName() + ">>>" + cache.getCacheDesc());
    }

    @Test
    public void testProperties() {
        MemCachedConfig config = MemCachedConfig.getConfig();
        LOGGER.debug("MemCachedConfig={}", config);
        RedisConfig redisConfig = RedisConfig.getConfig();
        LOGGER.debug("RedisConfig={}", redisConfig);
    }

    @Test
    public void testMemcachedExpire() {
        Date date = new Date(1000);
        long aa = date.getTime() / 1000;
        LOGGER.debug("huazie:{}", aa);
    }

    @Test
    public void testRedis() {
        Jedis jedis = new Jedis("127.0.0.1", 10001);
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
        List<String> list = jedis.lrange("huazie-list", 0 ,2);
        LOGGER.debug("list = {}", list);

        // #5. 获取数据并输出
        Set<String> keys = jedis.keys("*");
        Iterator<String> it=keys.iterator();
        int i = 1;
        while(it.hasNext()){
            String key = it.next();
            LOGGER.debug("key{} = {}", i++, key);
        }

        // #6. 删除数据
        //LOGGER.debug("del ret = {}", jedis.del("huazie"));

    }

    @Test
    public void testShardedJedis(){

        //初始化ShardedJedisPool
        ShardedJedisPool jedisPool = RedisPool.getInstance().getJedisPool();

        //进行查询等其他操作
        ShardedJedis jedis = null;

        try {
            jedis = jedisPool.getResource();

            // 设置数据
            // jedis.set("huazie", "hello world");
            // jedis.set("huazie1", "我是谁，我在哪");

            // 获取数据
            Client client1 = jedis.getShard("huazie").getClient();
            Client client2 = jedis.getShard("huazie1").getClient();
            LOGGER.debug("value = {}, ip = {}:{}", jedis.get("huazie"), client1.getHost(), client1.getPort());
            LOGGER.debug("value = {}, ip = {}:{}", jedis.get("huazie1"), client2.getHost(), client2.getPort());

        } finally {
            //使用后一定关闭，还给连接池
            if(jedis != null) {
                jedis.close();
            }
        }

    }
}
