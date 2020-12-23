package com.huazie.frame.common.util.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;

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

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(GsonUtils.class);

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
            LOGGER.debug1(new Object() {}, "Json = {}", json);
            LOGGER.debug("Class = {}", clazz);
        }
        T t = null;
        try {
            Gson gson = new Gson();
            t = gson.fromJson(json, clazz);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Exception = ", e);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entity = {}", t);
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
            LOGGER.debug1(new Object() {}, "Json = {}", json);
            LOGGER.debug("Class = {}", clazz);
        }
        List<T> entityList = null;
        try {
            entityList = new Gson().fromJson(json, new TypeToken<List<T>>() {}.getType());
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Exception = ", e);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("EntityList={}", entityList);
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
            LOGGER.debug1(new Object() {}, "Json = {}", json);
        }
        Map<String, Object> map = null;
        try {
            map = new Gson().fromJson(json, new TypeToken<Map<String, Object>>() {}.getType());
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Exception = ", e);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Map = {}", map);
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
            LOGGER.debug1(new Object() {}, "Json = {}", json);
        }
        List<Map<String, Object>> mapList = null;
        try {
            Gson gson = new Gson();
            mapList = gson.fromJson(json, new TypeToken<List<Map<String, Object>>>() {}.getType());
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Exception = ", e);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("MapList={}", mapList);
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
            LOGGER.debug1(new Object() {}, "Object = {}", obj);
        }
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Json = {}", json);
        }
        return json;
    }
}
