package com.huazie.frame.common.util.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * <p> Gson 工具包 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class GsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(GsonUtils.class);

    /**
     * <p> 使用Gson进行解析 ,并获取T对象 </p>
     *
     * @param json  json字符串
     * @param clazz T类的Class对象
     * @return T对象
     * @since 1.0.0
     */
    public static <T> T toEntity(String json, Class<T> clazz) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("GsonUtils##toEntity(String, Class<T>) Json = {}", json);
            LOGGER.debug("GsonUtils##toEntity(String, Class<T>) Class = {}", clazz);
        }
        T t = null;
        try {
            Gson gson = new Gson();
            t = gson.fromJson(json, clazz);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("GsonUtils##toEntity() Exception = ", e);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("GsonUtils##toEntity(String, Class<T>) Entity = {}", t);
        }
        return t;
    }

    /**
     * <p> 使用Gson进行解析 , 并获取T对象的List集合 </p>
     *
     * @param json  json字符串
     * @param clazz 实体类的Class对象
     * @return T对象的List集合
     * @since 1.0.0
     */
    public static <T> List<T> toEntityList(String json, Class<T> clazz) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("GsonUtils##toEntityList(String, Class<T>) Json = {}", json);
            LOGGER.debug("GsonUtils##toEntityList(String, Class<T>) Class = {}", clazz);
        }
        List<T> entityList = null;
        try {
            Gson gson = new Gson();
            entityList = gson.fromJson(json, new TypeToken<List<T>>() {
            }.getType());
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("GsonUtils##toEntityList(String, Class<T>) Exception = ", e);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("GsonUtils##toEntityList(String, Class<T>) EntityList={}", entityList);
        }
        return entityList;
    }

    /**
     * <p> 使用Gson进行解析 ，并获取Map对象 </p>
     *
     * @param json json字符串
     * @return Map对象
     * @since 1.0.0
     */
    public static Map<String, Object> toMap(String json) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("GsonUtils##toMap(String) Json = {}", json);
        }
        Map<String, Object> map = null;
        try {
            Gson gson = new Gson();
            map = gson.fromJson(json, new TypeToken<Map<String, Object>>() {
            }.getType());
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("GsonUtils##toMap(String) Exception = ", e);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("GsonUtils##toMap(String) Map = {}", map);
        }
        return map;
    }

    /**
     * <p> 使用Gson进行解析 ,并获取Map对象的List集合 </p>
     *
     * @param json json字符串
     * @return Map对象的List集合
     * @since 1.0.0
     */
    public static List<Map<String, Object>> toMapList(String json) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("GsonUtils##toMapList(String) Json = {}", json);
        }
        List<Map<String, Object>> mapList = null;
        try {
            Gson gson = new Gson();
            mapList = gson.fromJson(json, new TypeToken<List<Map<String, Object>>>() {
            }.getType());

        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("GsonUtils##toMapList(String) Exception = ", e);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("GsonUtils##toMapList(String) MapList={}", mapList);
        }
        return mapList;
    }

    /**
     * <p> 将对象转换成json字符串 </p>
     *
     * @param obj 待转换对象
     * @return json字符串
     * @since 1.0.0
     */
    public static String toJsonString(Object obj) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("GsonUtils##toJsonString(String) Object = {}", obj);
        }
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("GsonUtils##toJsonString(String) Json = {}", json);
        }
        return json;
    }
}
