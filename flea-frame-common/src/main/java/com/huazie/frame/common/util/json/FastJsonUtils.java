package com.huazie.frame.common.util.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
            LOGGER.debug("FastJsonUtils#toEntity(String, Class<T>) Json = {}", json);
            LOGGER.debug("FastJsonUtils#toEntity(String, Class<T>) Class = {}", clazz);
        }
        T t = null;
        try {
            t = JSON.parseObject(json, clazz);
        } catch (Exception e) {
            if(LOGGER.isErrorEnabled()){
                LOGGER.error("FastJsonUtils#toEntity(String, Class<T>) Exception = ", e);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FastJsonUtils#toEntity(String, Class<T>) Entity = {}", t);
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
            LOGGER.debug("FastJsonUtils#toEntityList(String, Class<T>) Json = {}", json);
            LOGGER.debug("FastJsonUtils#toEntityList(String, Class<T>) Class = {}", clazz);
        }
        List<T> entityList = null;
        try {
            entityList = JSON.parseArray(json, clazz);
        } catch (Exception e) {
            if(LOGGER.isErrorEnabled()){
                LOGGER.error("FastJsonUtils#toEntityList(String, Class<T>) Exception = ", e);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FastJsonUtils#toEntityList(String, Class<T>) EntityList = {}", entityList);
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
            LOGGER.debug("FastJsonUtils#toMap(String) Json = {}", json);
        }
        Map<String, Object> map = null;
        try {
            map = JSON.parseObject(json, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            if(LOGGER.isErrorEnabled()){
                LOGGER.error("FastJsonUtils#toMap(String) Exception = ", e);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FastJsonUtils#toMap(String) Map = {}", map);
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
            LOGGER.debug("FastJsonUtils#toMapList(String) Json = {}", json);
        }
        List<Map<String, Object>> mapList = null;
        try {
            mapList = JSON.parseObject(json, new TypeReference<List<Map<String, Object>>>() {
            });
        } catch (Exception e) {
            if(LOGGER.isErrorEnabled()){
                LOGGER.error("FastJsonUtils#toMapList(String) Exception = ", e);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FastJsonUtils#toMapList(String) MapList = {}", mapList);
        }
        return mapList;
    }

    /**
     * <p>将对象转换成json字符串</p>
     *
     * @param obj 待转换对象
     * @return json字符串
     * @since 1.0.0
     */
    public static String toJsonString(Object obj){
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FastJsonUtils#toJsonString(String) Object = {}", obj);
        }
        String json = JSON.toJSONString(obj);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FastJsonUtils#toJsonString(String) Json = {}", json);
        }
        return json;
    }

}
