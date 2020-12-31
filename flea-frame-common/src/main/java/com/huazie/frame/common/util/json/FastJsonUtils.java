package com.huazie.frame.common.util.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * <p> FastJson 工具类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FastJsonUtils {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FastJsonUtils.class);

    /**
     * <p> 使用FastJson进行解析 ,获取T对象 </p>
     *
     * @param json  json字符串
     * @param clazz T类的Class对象
     * @return T对象
     * @since 1.0.0
     */
    public static <T> T toEntity(String json, Class<T> clazz) {
        Object obj = null;
        if (LOGGER.isDebugEnabled()) {
            obj = new Object() {};
            LOGGER.debug1(obj, "Json converted to Entity, Json = {}", json);
            LOGGER.debug1(obj, "Json converted to Entity, Class = {}", clazz);
        }
        T t = null;
        try {
            t = JSON.parseObject(json, clazz);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error1(new Object() {}, "Json converted to Entity, Exception = ", e);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(obj, "Json converted to Entity, Entity = {}", t);
        }
        return t;
    }

    /**
     * <p> 使用FastJson进行解析 ,获取T对象的List集合 </p>
     *
     * @param json  json字符串
     * @param clazz T类的Class对象
     * @return T对象的List集合
     * @since 1.0.0
     */
    public static <T> List<T> toEntityList(String json, Class<T> clazz) {
        Object obj = null;
        if (LOGGER.isDebugEnabled()) {
            obj = new Object() {};
            LOGGER.debug1(obj, "Json converted to EntityList, Json = {}", json);
            LOGGER.debug1(obj, "Json converted to EntityList, Class = {}", clazz);
        }
        List<T> entityList = null;
        try {
            entityList = JSON.parseArray(json, clazz);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error1(new Object() {}, "Json converted to EntityList, Exception = ", e);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(obj, "Json converted to EntityList, EntityList={}", entityList);
        }
        return entityList;
    }

    /**
     * <p> 使用FastJson进行解析，并获取Map对象 </p>
     *
     * @param json json字符串
     * @return Map对象
     * @since 1.0.0
     */
    public static Map<String, Object> toMap(String json) {
        Object obj = null;
        if (LOGGER.isDebugEnabled()) {
            obj = new Object() {};
            LOGGER.debug1(obj, "Json converted to Map, Json = {}", json);
        }
        Map<String, Object> map = null;
        try {
            map = JSON.parseObject(json, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error1(new Object() {}, "Json converted to Map, Exception = \n", e);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(obj, "Json converted to Map, Map = {}", map);
        }
        return map;
    }

    /**
     * <p> 使用FastJson进行解析，并获取Map对象的List集合 </p>
     *
     * @param json json字符串
     * @return Map对象的List集合
     * @since 1.0.0
     */
    public static List<Map<String, Object>> toMapList(String json) {
        Object obj = null;
        if (LOGGER.isDebugEnabled()) {
            obj = new Object() {};
            LOGGER.debug1(obj, "Json converted to MapList, Json = {}", json);
        }
        List<Map<String, Object>> mapList = null;
        try {
            mapList = JSON.parseObject(json, new TypeReference<List<Map<String, Object>>>() {});
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error1(new Object() {}, "Json converted to MapList, Exception = ", e);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(obj, "Json converted to MapList, MapList={}", mapList);
        }
        return mapList;
    }

    /**
     * <p>将对象转换成json字符串</p>
     *
     * @param object 待转换对象
     * @return json字符串
     * @since 1.0.0
     */
    public static String toJsonString(Object object) {
        Object obj = null;
        if (LOGGER.isDebugEnabled()) {
            obj = new Object() {};
            LOGGER.debug1(obj, "Object converted to Json String, Object = {}", object);
        }
        String json = JSON.toJSONString(object);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(obj, "Object converted to Json String, Json = {}", json);
        }
        return json;
    }

}
