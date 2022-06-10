package com.huazie.fleaframework.db.common.util;

import com.huazie.fleaframework.common.util.ObjectUtils;
import org.springframework.core.NamedThreadLocal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Flea分库工具类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaLibUtil {

    private static final ThreadLocal<Map<String, Object>> resources = new NamedThreadLocal<>("Flea Lib Resources");

    private FleaLibUtil() {
    }

    /**
     * 设置当前线程下的分库序列值
     *
     * @param key 分库序列键，参考 <split seq="key"/>
     * @param value 分库序列值
     * @since 2.0.0
     */
    public static void setSplitLibSeqValue(String key, Object value) {
        Map<String, Object> map = resources.get();
        if (ObjectUtils.isEmpty(map)) {
            map = new HashMap<>();
        }
        map.put(key, value);
        resources.set(map);
    }

    /**
     * 获取当前线程下的分库序列字典集合
     *
     * @return 分库序列集合，存储分库序列键值
     * @since 2.0.0
     */
    public static Map<String, Object> getSplitLibSeqValues() {
        Map<String, Object> map = resources.get();
        return (ObjectUtils.isNotEmpty(map) ? Collections.unmodifiableMap(map) : null);
    }

    /**
     * 清除当前线程下的分库序列
     *
     * @since 2.0.0
     */
    public static void clearSplitLibSeqValues() {
        resources.set(null);
    }

}
