package com.huazie.frame.common;

import com.huazie.frame.common.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> Flea 通用配置类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaCommonConfig {

    protected Map<String, Object> config = new HashMap<String, Object>();

    /**
     * <p> 添加配置数据 </p>
     *
     * @param key   配置数据键
     * @param value 配置数据值
     * @since 1.0.0
     */
    public void put(String key, Object value) {
        config.put(key, value);
    }

    /**
     * <p> 获取配置数据 </p>
     *
     * @param key 配置数据键
     * @return 配置数据值
     * @since 1.0.0
     */
    public Object get(String key) {
        return config.get(key);
    }

    /**
     * <p> 获取配置数据 </p>
     *
     * @param key   配置数据键
     * @param clazz 配置数据值Class
     * @param <T>   配置数据值类型
     * @return 配置数据值（具体到指定类型）
     * @since 1.0.0
     */
    public <T> T get(String key, Class<T> clazz) {
        T value = null;
        Object obj = config.get(key);
        if (ObjectUtils.isNotEmpty(clazz) && clazz.isInstance(obj)) {
            value = clazz.cast(obj);
        }
        return value;
    }

    /**
     * <p> 判断配置数据是否为空 </p>
     *
     * @return true:配置数据为空，false: 存在配置数据
     * @since 1.0.0
     */
    public boolean isEmpty() {
        return config.isEmpty();
    }

    /**
     * <p> 清空配置 </p>
     *
     * @since 1.0.0
     */
    public void clear() {
        config.clear();
    }
}
