package com.huazie.frame.common.util;

import java.util.Map;

/**
 * <p> Map集合处理工具类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class MapUtils {

    /**
     * <p> 判断Map集合是否空（Map集合为null 或 无数据） </p>
     *
     * @param map Map集合
     * @return true : Map集合为空; false : Map集合非空
     * @since 1.0.0
     */
    public static boolean isEmpty(Map map) {
        if (null == map || map.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * <p> 判断Map集合是否非空（Map集合存在数据） </p>
     *
     * @param map Map集合
     * @return true : Map集合非空; false : Map集合为空
     * @since 1.0.0
     */
    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

}
