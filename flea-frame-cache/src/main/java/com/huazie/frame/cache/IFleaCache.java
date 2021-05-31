package com.huazie.frame.cache;

import java.util.Set;

/**
 * Flea缓存接口类，定义了对缓存数据进行读、写和删除的基本操作。
 *
 * <p> 方法 {@code get}，实现读缓存的基本操作；
 *
 * <p> 方法 {@code put}，实现写缓存的基本操作；
 *
 * <p> 方法 {@code delete}, 实现删除缓存的基本操作。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaCache {

    /**
     * <p> 读缓存 </p>
     *
     * @param key 数据键关键字
     * @return 数据值
     * @since 1.0.0
     */
    Object get(String key);

    /**
     * <p> 写缓存 </p>
     *
     * @param key   数据键关键字
     * @param value 数据值
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
     * <p> 删除指定数据键关键字对应的缓存 </p>
     *
     * @param key 数据键关键字
     * @since 1.0.0
     */
    void delete(String key);

    /**
     * <p> 获取 记录当前Cache所有数据键关键字 的Set集合 </p>
     *
     * @return 数据键key的集合
     * @since 1.0.0
     */
    Set<String> getCacheKey();

    /**
     * <p> 获取缓存所属系统名 </p>
     *
     * @return 缓存所属系统名
     * @since 1.0.0
     */
    String getSystemName();

}
