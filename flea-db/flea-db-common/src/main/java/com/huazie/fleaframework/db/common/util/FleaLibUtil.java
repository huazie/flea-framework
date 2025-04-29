package com.huazie.fleaframework.db.common.util;

import com.huazie.fleaframework.common.util.ObjectUtils;
import org.apache.commons.collections.MapUtils;
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
     * @param key   分库序列键，参考 <split seq="key"/>
     * @param value 分库序列值
     * @since 2.0.0
     */
    public static void setSplitLibSequence(String key, Object value) {
        Map<String, Object> map = resources.get();
        if (ObjectUtils.isEmpty(map)) {
            map = new HashMap<>();
        }
        map.put(key, value);
        resources.set(map);
    }

    /**
     * 分库序列集字符串，形如 分库序列键1=分库序列值1;分库序列键2=分库序列值2
     *
     * @param seq 分库序列集字符串
     * @since 2.0.0
     */
    public static void setSplitLibSequence(String seq) {
        Map<String, Object> map = resources.get();
        if (ObjectUtils.isEmpty(map)) {
            map = new HashMap<>();
        }
        for (String entry : seq.split(";")) {
            String[] kv = entry.split("=", 2);
            if (kv.length == 2) {
                map.put(kv[0].trim(), kv[1].trim());
            }
        }
        resources.set(map);
    }

    /**
     * 获取指定分库序列键对应的值，并转换为目标类型
     *
     * @param key   分库序列键
     * @param clazz 分库序列值的Class类型
     * @param <T>   泛型类型参数，需与 clazz 类型声明一致
     * @return 转换后的分库序列值，类型不匹配时返回 null
     * @since 1.0.0
     */
    public static <T> T getSplitLibSeqValue(String key, Class<T> clazz) {
        Map<String, Object> map = resources.get();
        Object value = MapUtils.getObject(map, key);
        return clazz.isInstance(value) ? clazz.cast(value) : null;
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
