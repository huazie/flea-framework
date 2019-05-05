package com.huazie.frame.cache;

/**
 * <p> 自定义Cache接口类（主要定义了一些增删改查的方法） </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaCache {

    /**
     * <p> 获取指定缓存key对应的值 </p>
     *
     * @param key 缓存键
     * @return 缓存key对应的值
     * @since 1.0.0
     */
    Object get(String key);

    /**
     * <p> 将对应键和值写入缓存中 </p>
     *
     * @param key   缓存键
     * @param value 缓存值
     * @since 1.0.0
     */
    void put(String key, Object value);

    /**
     * <p> 清空所有缓存 </p>
     *
     * @since 1.0.0
     */
    void clear();

    /**
     * <p> 清除指定键的缓存 </p>
     *
     * @param key 缓存键
     * @since 1.0.0
     */
    void delete(String key);

}
