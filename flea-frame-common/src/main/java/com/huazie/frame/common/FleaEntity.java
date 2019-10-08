package com.huazie.frame.common;

import com.huazie.frame.common.util.ObjectUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> 通用实体父类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaEntity implements Serializable {

    private Map<String, Object> other = new HashMap<String, Object>();

    /**
     * <p> 获取其他实体属性值 </p>
     *
     * @param key  属性键
     * @return 属性值
     * @since 1.0.0
     */
    public Object get(String key) {
        return other.get(key);
    }

    /**
     * <p> 获取其他实体属性值 </p>
     *
     * @param key  属性键
     * @param type 属性值Class
     * @param <T>  属性值类型
     * @return 属性值
     * @since 1.0.0
     */
    public <T> T get(String key, Class<T> type) {
        Object obj = other.get(key);
        if (ObjectUtils.isNotEmpty(obj)) {
            return type.cast(obj);
        } else {
            return null;
        }
    }

    /**
     * <p> 添加其他属性信息, 不论属性键存在还是不存在，都覆盖属性值 </p>
     *
     * @param key   属性键
     * @param value 属性值
     * @since 1.0.0
     */
    public void put(String key, Object value) {
        other.put(key, value);
    }

    /**
     * <p> 添加其他属性信息，只有属性值键不存在或者属性值为空，才覆盖属性值 </p>
     *
     * @param key   属性键
     * @param value 属性值
     * @since 1.0.0
     */
    public void putIfAbsent(String key, Object value) {
        Object obj = other.get(key);
        if (ObjectUtils.isEmpty(obj)) {
            put(key, value);
        }
    }

    /**
     * <p> 判断是否包含指定属性键 </p>
     *
     * @param key 属性键
     * @return 如果包含，返回true；否则返回false
     * @since 1.0.0
     */
    public boolean contains(String key) {
        return other.containsKey(key);
    }

}
