package com.huazie.fleaframework.common.util.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.huazie.fleaframework.common.exceptions.FleaException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.ExceptionUtils;

import java.util.List;
import java.util.Map;

/**
 * FastJson 工具类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FastJsonUtils {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FastJsonUtils.class);

    private FastJsonUtils() {
    }

    /**
     * 使用FastJson进行解析 ,获取T对象
     *
     * @param json  json字符串
     * @param clazz T类的Class对象
     * @return T对象
     * @since 1.0.0
     */
    public static <T> T toEntity(String json, Class<T> clazz) {
        Object obj = new Object() {};
        LOGGER.debug1(obj, "#F# Json converted to Entity, Json = {}", json);
        LOGGER.debug1(obj, "#F# Json converted to Entity, Class = {}", clazz);
        T t = null;
        try {
            t = JSON.parseObject(json, clazz);
        } catch (Exception e) {
            LOGGER.error1(obj, "#F# Json converted to Entity, Exception = ", e);
            ExceptionUtils.throwFleaException(FleaException.class, e);
        }
        LOGGER.debug1(obj, "#F# Json converted to Entity, Entity = {}", t);
        return t;
    }

    /**
     * 使用FastJson进行解析 ,获取T对象的List集合
     *
     * @param json  json字符串
     * @param clazz T类的Class对象
     * @return T对象的List集合
     * @since 1.0.0
     */
    public static <T> List<T> toEntityList(String json, Class<T> clazz) {
        Object obj = new Object() {};
        LOGGER.debug1(obj, "#F# Json converted to EntityList, Json = {}", json);
        LOGGER.debug1(obj, "#F# Json converted to EntityList, Class = {}", clazz);
        List<T> entityList = null;
        try {
            entityList = JSON.parseArray(json, clazz);
        } catch (Exception e) {
            LOGGER.error1(obj, "#F# Json converted to EntityList, Exception = ", e);
            ExceptionUtils.throwFleaException(FleaException.class, e);
        }
        LOGGER.debug1(obj, "#F# Json converted to EntityList, EntityList={}", entityList);
        return entityList;
    }

    /**
     * 使用FastJson进行解析，并获取Map对象
     *
     * @param json json字符串
     * @return Map对象
     * @since 1.0.0
     */
    public static Map<String, Object> toMap(String json) {
        Object obj = new Object() {};
        LOGGER.debug1(obj, "#F# Json converted to Map, Json = {}", json);
        Map<String, Object> map = null;
        try {
            map = JSON.parseObject(json, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            LOGGER.error1(obj, "#F# Json converted to Map, Exception = \n", e);
            ExceptionUtils.throwFleaException(FleaException.class, e);
        }
        LOGGER.debug1(obj, "#F# Json converted to Map, Map = {}", map);
        return map;
    }

    /**
     * 使用FastJson进行解析，并获取Map对象的List集合
     *
     * @param json json字符串
     * @return Map对象的List集合
     * @since 1.0.0
     */
    public static List<Map<String, Object>> toMapList(String json) {
        Object obj = new Object() {};
        LOGGER.debug1(obj, "#F# Json converted to MapList, Json = {}", json);
        List<Map<String, Object>> mapList = null;
        try {
            mapList = JSON.parseObject(json, new TypeReference<List<Map<String, Object>>>() {});
        } catch (Exception e) {
            LOGGER.error1(new Object() {}, "#F# Json converted to MapList, Exception = ", e);
            ExceptionUtils.throwFleaException(FleaException.class, e);
        }
        LOGGER.debug1(obj, "#F# Json converted to MapList, MapList={}", mapList);
        return mapList;
    }

    /**
     * 将对象转换成json字符串
     *
     * @param object 待转换对象
     * @return json字符串
     * @since 1.0.0
     */
    public static String toJsonString(Object object) {
        Object obj = new Object() {};
        LOGGER.debug1(obj, "#F# Object converted to Json String, Object = {}", object);
        String json = JSON.toJSONString(object);
        LOGGER.debug1(obj, "#F# Object converted to Json String, Json = {}", json);
        return json;
    }

}
