package com.huazie.frame.common.util;

import com.huazie.frame.common.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * <p> 反射工具类 </p>
 *
 * @author huazie
 * @version v1.0.0
 * @since 1.0.0
 */
public class ReflectUtils {

    public final static Logger LOGGER = LoggerFactory.getLogger(ReflectUtils.class);

    /**
     * <p> 获取类实例对象 </p>
     *
     * @param className 类名
     * @return 实例化的类对象
     * @since 1.0.0
     */
    public static Object newInstance(String className) {
        Object obj = null;
        try {
            Class<?> clazz = Class.forName(className);
            obj = clazz.newInstance();
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("当前类反射出错，Class={}", className, e);
            }
        }
        return obj;
    }

    /**
     * <p> 获取对象指定属性的值 </p>
     *
     * @param obj      待取值对象
     * @param attrName 对象的一个属性
     * @return 返回attrName属性对应的值
     * @since 1.0.0
     */
    public static Object getObjectAttrValue(Object obj, String attrName) {
        Object value = null;
        try {
            Field field = obj.getClass().getDeclaredField(attrName);
            if (field != null) {
                String getter = CommonConstants.MethodConstants.GET + StringUtils.toUpperCaseInitial(attrName);// 属性的get方法名
                Method method = obj.getClass().getMethod(getter, new Class[]{});
                value = method.invoke(obj, new Object[]{});
            }
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("获取对象指定属性值出错，Exception=", e);
            }
        }
        return value;
    }

}
