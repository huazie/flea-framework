package com.huazie.frame.common.util;

import com.huazie.frame.common.exception.CommonException;

import java.util.Collection;

/**
 * <p> Collection集合处理工具类  </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class CollectionUtils {

    /**
     * <p> 判断Collection集合是否空（Collection集合为null 或 无数据） </p>
     *
     * @param collection Collection集合
     * @return true : Collection集合为空; false : Collection集合非空
     * @since 1.0.0
     */
    public static boolean isEmpty(Collection collection) {
        if (null == collection || collection.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * <p> 判断Collection集合是否非空（Collection集合存在数据） </p>
     *
     * @param collection Collection集合
     * @return true : Collection集合非空; false : Collection集合为空
     * @since 1.0.0
     */
    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    /**
     * <p> 校验collection集合对象，如果空，则抛出异常 </p>
     *
     * @param collection     待校验集合对象
     * @param exceptionClazz 通用异常子类类对象
     * @param params         通用异常子类构造参数
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static void checkEmpty(Collection collection, Class<? extends CommonException> exceptionClazz, Object... params) throws CommonException {
        if (isEmpty(collection)) {
            ExceptionUtils.throwCommonException(exceptionClazz, params);
        }
    }

    /**
     * <p> 校验collection集合对象，如果非空，则抛出异常 </p>
     *
     * @param collection     待校验集合对象
     * @param exceptionClazz 通用异常子类类对象
     * @param params         通用异常子类构造参数
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static void checkNotEmpty(Collection collection, Class<? extends CommonException> exceptionClazz, Object... params) throws CommonException {
        if (isNotEmpty(collection)) {
            ExceptionUtils.throwCommonException(exceptionClazz, params);
        }
    }
}
