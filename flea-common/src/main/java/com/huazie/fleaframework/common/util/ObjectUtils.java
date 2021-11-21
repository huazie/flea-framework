package com.huazie.fleaframework.common.util;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.exception.CommonException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * <p> Object工具类 </p>
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
     * <p> 判断value是否为空 </p>
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
     * <p> 判断value是否为空 </p>
     *
     * @param value 校验的对象
     * @return 如果不为空或null，返回true；否则返回false
     * @since 1.0.0
     */
    public static boolean isNotEmpty(Object value) {
        return !isEmpty(value);
    }

    /**
     * <p> 将对象序列化为字节数组 </p>
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
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error1(new Object(), "Exception occurs in the process of object serialization : \n", e);
            }
        }
        return null;
    }

    /**
     * <p> 将字节数组反序列化为对象 </p>
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
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error1(new Object() {}, "Exception occurs in the process of object deserialization : \n", e);
            }
        }
        return null;
    }

    /**
     * <p> 校验obj对象，如果空，则抛出异常 </p>
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
     * <p> 校验obj对象，如果非空，则抛出异常 </p>
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

}
