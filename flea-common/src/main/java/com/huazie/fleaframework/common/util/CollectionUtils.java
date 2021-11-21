package com.huazie.fleaframework.common.util;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;

import java.util.Collection;
import java.util.List;

/**
 * <p> Collection集合处理工具类  </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class CollectionUtils {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(CollectionUtils.class);

    private CollectionUtils() {
    }

    /**
     * <p> 判断Collection集合是否空（Collection集合为null 或 无数据） </p>
     *
     * @param collection Collection集合
     * @return true : Collection集合为空; false : Collection集合非空
     * @since 1.0.0
     */
    public static boolean isEmpty(Collection collection) {
        return null == collection || collection.isEmpty();
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

    /**
     * 如果elementList不为空，且有数据，则取第一条数据，否则返回null
     *
     * @param elementList  元素List
     * @param elementClazz 元素Class类型
     * @param <T>          元素类型
     * @return 第一条数据
     * @since 2.0.0
     */
    public static <T> T getFirstElement(List<T> elementList, Class<T> elementClazz) {
        T t = null;

        if (isNotEmpty(elementList)) {
            t = elementList.get(0);
        }

        if (LOGGER.isDebugEnabled() && ObjectUtils.isNotEmpty(elementClazz)) {
            LOGGER.debug1(new Object() {}, elementClazz.getSimpleName() + " = {}", t);
        }

        return t;
    }
}
