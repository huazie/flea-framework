package com.huazie.fleaframework.common.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Flea Map 封装了 HashMap 的常用逻辑
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class FleaMap implements Serializable {

    private static final long serialVersionUID = 8766840358247197499L;

    private Map<String, Object> fleaMap = new HashMap<>();

    /**
     * 根据指定数据键获取Map中的值
     *
     * @param key 数据键
     * @return 数据值
     * @since 1.1.0
     */
    public Object get(String key) {
        return fleaMap.get(key);
    }

    /**
     * 根据指定数据键和指定数据值Class类型获取Map中的值
     *
     * @param key   数据键
     * @param clazz 数据值对应的Class类型
     * @param <T>   数据值类型
     * @return 数据值
     * @since 1.1.0
     */
    public <T> T get(String key, Class<T> clazz) {
        T value = null;
        Object obj = fleaMap.get(key);
        if (ObjectUtils.isNotEmpty(clazz) && clazz.isInstance(obj)) {
            value = clazz.cast(obj);
        }
        return value;
    }

    /**
     * 添加其他数据信息, 不论数据键存在还是不存在，都覆盖数据值
     *
     * @param key   数据键
     * @param value 数据值
     * @since 1.1.0
     */
    public void put(String key, Object value) {
        fleaMap.put(key, value);
    }

    /**
     * 添加其他数据信息，只有数据值键不存在或者属性值为空，才覆盖数据值
     *
     * @param key   数据键
     * @param value 数据值
     * @since 1.1.0
     */
    public void putIfAbsent(String key, Object value) {
        Object obj = fleaMap.get(key);
        if (ObjectUtils.isEmpty(obj)) {
            put(key, value);
        }
    }

    /**
     * 添加多个数据信息
     *
     * @param otherMap 其他数据Map
     * @since 2.0.0
     */
    public void putAll(Map<String, Object> otherMap) {
        if (MapUtils.isNotEmpty(otherMap)) {
            fleaMap.putAll(otherMap);
        }
    }

    /**
     * 判断是否包含指定数据键
     *
     * @param key 数据键
     * @return 如果包含，返回true；否则返回false
     * @since 1.0.0
     */
    public boolean contains(String key) {
        return fleaMap.containsKey(key);
    }

    /**
     * 判断数据Map是否为空
     *
     * @return true:数据为空，false: 数据不为空
     * @since 1.1.0
     */
    public boolean isEmpty() {
        return fleaMap.isEmpty();
    }

    /**
     * 清空数据Map
     *
     * @since 1.1.0
     */
    public void clear() {
        fleaMap.clear();
    }

    /**
     * 获取数据Map
     *
     * @return 数据Map
     * @since 1.1.0
     */
    public Map<String, Object> getFleaMap() {
        return fleaMap;
    }
}
