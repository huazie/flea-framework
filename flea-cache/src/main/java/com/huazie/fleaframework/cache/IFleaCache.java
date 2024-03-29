package com.huazie.fleaframework.cache;

import java.util.Set;

/**
 * Flea缓存接口类，定义了对缓存数据进行读、写和删除的基本操作。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaCache {

    /**
     * 读缓存
     *
     * @param key 数据键关键字
     * @return 数据值
     * @since 1.0.0
     */
    Object get(String key);

    /**
     * 写缓存
     *
     * @param key   数据键关键字
     * @param value 数据值
     * @since 1.0.0
     */
    void put(String key, Object value);

    /**
     * 清空缓存
     *
     * @since 1.0.0
     */
    void clear();

    /**
     * 删除缓存
     *
     * @param key 数据键关键字
     * @since 1.0.0
     */
    void delete(String key);

    /**
     * 获取记录当前Cache所有数据键关键字的Set集合
     *
     * @return 数据键key的集合
     * @since 1.0.0
     */
    Set<String> getCacheKey();

    /**
     * 获取缓存所属系统名
     *
     * @return 缓存所属系统名
     * @since 1.0.0
     */
    String getSystemName();

}
