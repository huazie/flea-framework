package com.huazie.fleaframework.common.util;

import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.exceptions.FleaException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Collection集合处理工具类
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
     * 判断Collection集合是否空（Collection集合为null 或 无数据）
     *
     * @param collection Collection集合
     * @return true : Collection集合为空; false : Collection集合非空
     * @since 1.0.0
     */
    public static boolean isEmpty(Collection collection) {
        return null == collection || collection.isEmpty();
    }

    /**
     * 判断Collection集合是否非空（Collection集合存在数据）
     *
     * @param collection Collection集合
     * @return true : Collection集合非空; false : Collection集合为空
     * @since 1.0.0
     */
    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    /**
     * 校验collection集合对象，如果空，则抛出异常
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
     * 校验collection集合对象，如果非空，则抛出异常
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

        if (ObjectUtils.isNotEmpty(elementClazz)) {
            LOGGER.debug1(new Object() {}, elementClazz.getSimpleName() + " = {}", t);
        }

        return t;
    }

    /**
     * 新建一个包含入参元素的ArrayList，注意：入参元素不能为空
     *
     * @param element 元素
     * @param <T>     元素类型
     * @return 包含入参元素的ArrayList
     * @since 2.0.0
     */
    public static <T> List<T> newArrayList(T element) {
        List<T> elementList = new ArrayList<>();
        ObjectUtils.checkEmpty(element, FleaException.class, "The element is null !");
        elementList.add(element);
        return elementList;
    }

    /**
     * 添加集合数据，如果List集合中已经存在，则不再添加进去
     *
     * @param list    List集合
     * @param element 待添加的元素
     * @param <T>     元素类型
     * @since 2.0.0
     */
    public static <T> void distinctAdd(List<T> list, T element) {
        ObjectUtils.checkEmpty(list, FleaException.class, "The list is null !");
        if (!list.contains(element)) {
            list.add(element);
        }
    }

    /**
     * 添加List集合数据，如果List集合中已经存在，则不再添加进去。
     * 注意：待添加元素在字符串 elementStr 中以逗号分隔。
     *
     * @param list         List集合
     * @param elementStr   待添加元素的字符串
     * @param <T>          待添加元素的类型
     * @since 2.0.0
     */
    public static <T> void distinctAddWithComma(List<T> list, String elementStr) {
        distinctAdd(list, elementStr, CommonConstants.SymbolConstants.COMMA);
    }

    /**
     * 添加List集合数据，如果List集合中已经存在，则不再添加进去。
     *
     * @param list         List集合
     * @param elementStr   待添加元素的字符串
     * @param placeholder  占位符，待添加元素字符串中各元素以此进行分隔
     * @param <T>          待添加元素的类型
     * @since 2.0.0
     */
    @SuppressWarnings({"unchecked"})
    public static <T> void distinctAdd(List<T> list, String elementStr, String placeholder) {
        ObjectUtils.checkEmpty(list, FleaException.class, "The list is null !");
        String[] elementArr = StringUtils.split(elementStr, placeholder);
        if (ArrayUtils.isNotEmpty(elementArr)) {
            for (String element : elementArr) {
                CollectionUtils.distinctAdd(list, (T) element);
            }
        }
    }
}
