package com.huazie.frame.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectUtils.class);

    /**
     * <p> 判断value是否为空 </p>
     *
     * @param value 校验的对象
     * @return 如果为空或null，返回true；否则返回false
     * @since 1.0.0
     */
    public static boolean isEmpty(Object value) {
        if (value instanceof String) {
            return null == value || "".equals(value);
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
        ObjectOutputStream objectOutputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        if (ObjectUtils.isEmpty(object)) {
            return null;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Exception occurs in the process of object serialization : ", e);
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
        ObjectInputStream objectInputStream;
        ByteArrayInputStream byteArrayInputStream;
        if (ArrayUtils.isEmpty(objectBytes)) {
            return null;
        }
        byteArrayInputStream = new ByteArrayInputStream(objectBytes);
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return objectInputStream.readObject();
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Exception occurs in the process of object deserialization : ", e);
            }
        }
        return null;
    }
}
