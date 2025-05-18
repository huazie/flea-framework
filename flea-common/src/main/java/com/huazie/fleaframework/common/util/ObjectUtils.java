package com.huazie.fleaframework.common.util;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.exceptions.FleaException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Object工具类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class ObjectUtils {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(ObjectUtils.class);

    private ObjectUtils() {
    }

    /**
     * 判断value是否为空
     *
     * @param value 校验的对象
     * @return 如果为空或null，返回true；否则返回false
     * @since 1.0.0
     */
    public static boolean isEmpty(Object value) {
        if (value instanceof String) {
            return "".equals(value);
        }
        return null == value;
    }

    /**
     * 判断value是否为空
     *
     * @param value 校验的对象
     * @return 如果不为空或null，返回true；否则返回false
     * @since 1.0.0
     */
    public static boolean isNotEmpty(Object value) {
        return !isEmpty(value);
    }

    /**
     * 将对象序列化为字节数组
     *
     * @param object 待序列化的对象
     * @return 字节数组
     * @since 1.0.0
     */
    public static byte[] serialize(Object object) {
        if (isEmpty(object)) {
            return null;
        }
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(object);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            LOGGER.error1(new Object(), "Exception occurs in the process of object serialization : \n", e);
        }
        return null;
    }

    /**
     * 将字节数组反序列化为对象
     *
     * @param objectBytes 待反序列化的字节数组
     * @return 对象
     * @since 1.0.0
     */
    public static Object deserialize(byte[] objectBytes) {
        if (ArrayUtils.isEmpty(objectBytes)) {
            return null;
        }
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(objectBytes);
             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
            return objectInputStream.readObject();
        } catch (Exception e) {
            LOGGER.error1(new Object() {
                }, "Exception occurs in the process of object deserialization : \n", e);
        }
        return null;
    }

    /**
     * 校验obj对象，如果为空，则抛出异常
     *
     * @param obj            待校验对象
     * @param exceptionClazz 通用异常子类类对象
     * @param params         通用异常子类构造参数
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static void checkEmpty(Object obj, Class<? extends CommonException> exceptionClazz, Object... params) throws CommonException {
        if (isEmpty(obj)) {
            ExceptionUtils.throwCommonException(exceptionClazz, params);
        }
    }

    /**
     * 校验obj对象，如果为空，则抛出异常
     *
     * @param obj            待校验对象
     * @param exceptionClazz Flea异常子类类对象
     * @param message        提示信息
     * @since 2.0.0
     */
    public static void checkEmpty(Object obj, Class<? extends FleaException> exceptionClazz, String message) {
        if (isEmpty(obj)) {
            ExceptionUtils.throwFleaException(exceptionClazz, message);
        }
    }

    /**
     * 校验obj对象，如果非空，则抛出异常
     *
     * @param obj            待校验对象
     * @param exceptionClazz 通用异常子类类对象
     * @param params         通用异常子类构造参数
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static void checkNotEmpty(Object obj, Class<? extends CommonException> exceptionClazz, Object... params) throws CommonException {
        if (isNotEmpty(obj)) {
            ExceptionUtils.throwCommonException(exceptionClazz, params);
        }
    }

    /**
     * 校验obj对象，如果非空，则抛出异常
     *
     * @param obj            待校验对象
     * @param exceptionClazz Flea异常子类类对象
     * @param message        提示信息
     * @since 2.0.0
     */
    public static void checkNotEmpty(Object obj, Class<? extends FleaException> exceptionClazz, String message) {
        if (isNotEmpty(obj)) {
            ExceptionUtils.throwFleaException(exceptionClazz, message);
        }
    }
}
