package com.huazie.frame.common.util.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> FastJson 工具类 </p>
 *
 * @author huazie
 * @version v1.0.0
 * @since 1.0.0
 */
public class FastJsonUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(FastJsonUtils.class);

    /**
     * <p> 使用FastJson进行解析 ,获取T对象 </p>
     *
     * @param json  json字符串
     * @param clazz T类的Class对象
     * @return T对象
     * @since 1.0.0
     */
    public static <T> T toEntity(String json, Class<T> clazz) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FastJsonUtils#getMapList(String) Json = {}", json);
            LOGGER.debug("FastJsonUtils#getMapList(String) Class = {}", clazz);
        }
        T t = null;
        try {
            t = JSON.parseObject(json, clazz);
        } catch (Exception e) {
            LOGGER.error("FastJsonUtils#getEntity(String, Class<T>) Exception = {}", e);
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FastJsonUtils#getEntity(String, Class<T>) Entity = {}", t);
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
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FastJsonUtils#getMapList(String) Json = {}", json);
            LOGGER.debug("FastJsonUtils#getMapList(String) Class = {}", clazz);
        }
        List<T> entityList = new ArrayList<T>();
        try {
            entityList = JSON.parseArray(json, clazz);
        } catch (Exception e) {
            LOGGER.error("FastJsonUtils#getEntityList(String, Class<T>) Exception = {}", e);
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FastJsonUtils#getEntityList(String, Class<T>) EntityList = {}", entityList);
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
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FastJsonUtils#getMapList(String) Json = {}", json);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = JSON.parseObject(json, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            LOGGER.error("FastJsonUtils#getMap(String) Exception = {}", e);
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FastJsonUtils#getMap(String) Map = {}", map);
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
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FastJsonUtils#getMapList(String) Json = {}", json);
        }
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        try {
            mapList = JSON.parseObject(json, new TypeReference<List<Map<String, Object>>>() {
            });
        } catch (Exception e) {
            LOGGER.error("FastJsonUtils#getMapList(String) Exception = {}", e);
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FastJsonUtils#getMapList(String) MapList = {}", mapList);
        }
        return mapList;
    }

    /**
     * <p>将对象转换成Json字符串</p>
     *
     * @param obj
     * @return
     */
    public static String toJsonString(Object obj){
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FastJsonUtils#getMapList(String) Object = {}", obj);
        }
        String json = JSON.toJSONString(obj);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FastJsonUtils#getMapList(String) Json = {}", json);
        }
        return json;
    }

}
