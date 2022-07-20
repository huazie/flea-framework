package com.huazie.fleaframework.common.util;

import com.huazie.fleaframework.common.exception.CommonException;

import java.util.Map;

/**
 * Map集合处理工具类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class MapUtils extends org.apache.commons.collections.MapUtils {

    private MapUtils() {
    }

    /**
     * 判断Map集合是否空（Map集合为null 或 无数据）
     *
     * @param map Map集合
     * @return true : Map集合为空; false : Map集合非空
     * @since 1.0.0
     */
    public static boolean isEmpty(Map map) {
        return null == map || map.isEmpty();
    }

    /**
     * 判断Map集合是否非空（Map集合存在数据）
     *
     * @param map Map集合
     * @return true : Map集合非空; false : Map集合为空
     * @since 1.0.0
     */
    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    /**
     * 校验map集合对象，如果空，则抛出异常
     *
     * @param map            Map集合
     * @param exceptionClazz 通用异常子类类对象
     * @param params         通用异常子类构造参数
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static void checkEmpty(Map map, Class<? extends CommonException> exceptionClazz, Object... params) throws CommonException {
        if (isEmpty(map)) {
            ExceptionUtils.throwCommonException(exceptionClazz, params);
        }
    }

    /**
     * 校验map集合对象，如果非空，则抛出异常
     *
     * @param map            Map集合
     * @param exceptionClazz 通用异常子类类对象
     * @param params         通用异常子类构造参数
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static void checkNotEmpty(Map map, Class<? extends CommonException> exceptionClazz, Object... params) throws CommonException {
        if (isNotEmpty(map)) {
            ExceptionUtils.throwCommonException(exceptionClazz, params);
        }
    }
}
