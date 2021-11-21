package com.huazie.fleaframework.common.util.json;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.ObjectUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p> Json 工具包 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class JsonUtils {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(JsonUtils.class);

    private JsonUtils() {
    }

    /**
     * <p> 使用net.sf.json解析，获取String对象的List集合 </p>
     *
     * @param json json字符串
     * @param key  json串的键
     * @return String对象的List集合
     * @since 1.0.0
     */
    public static List<String> toStringList(String json, String key) {
        Object obj = null;
        if (LOGGER.isDebugEnabled()) {
            obj = new Object() {};
            LOGGER.debug1(obj, "Json converted to List<String>, Json = {}", json);
            LOGGER.debug1(obj, "Json converted to List<String>, Key = {}", key);
        }
        List<String> list = null;
        try {
            JSONTokener jsonTokener = new JSONTokener(json);
            Object object = jsonTokener.nextValue();
            if (ObjectUtils.isNotEmpty(object) && object instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) object;
                JSONArray jsonArray = jsonObject.getJSONArray(key);
                if (null != jsonArray && !jsonArray.isEmpty()) {
                    list = new ArrayList<>();
                    for (int i = 0; i < jsonArray.size(); i++) {
                        list.add(jsonArray.getString(i));
                    }
                }
            }
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error1(new Object() {}, "Json converted to List<String>, Exception = \n", e);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(obj, "Json converted to List<String>, List<String> = {}", list);
        }
        return list;
    }

    /**
     * <p> 使用net.sf.json解析，获取Map对象的List集合 </p>
     *
     * @param json json字符串
     * @param key  json对象的键
     * @return Map对象的List集合
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> toMapList(String json, String key) {
        Object obj = null;
        if (LOGGER.isDebugEnabled()) {
            obj = new Object() {};
            LOGGER.debug1(obj, "Json converted to List<Map<String, Object>>, Json = {}", json);
            LOGGER.debug1(obj, "Json converted to List<Map<String, Object>>, Key = {}", key);
        }
        List<Map<String, Object>> list = null;
        try {
            JSONTokener jsonTokener = new JSONTokener(json);
            Object object = jsonTokener.nextValue();
            if (ObjectUtils.isNotEmpty(object) && object instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) object;
                JSONArray jsonArray = jsonObject.getJSONArray(key);
                if (null != jsonArray && !jsonArray.isEmpty()) {
                    list = new ArrayList<>();
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                        if (null != jsonObject2) {
                            Map<String, Object> map = new HashMap<>();
                            Iterator<String> iterator = jsonObject2.keys();
                            while (iterator.hasNext()) {
                                String jsonKey = iterator.next();
                                Object jsonValue = jsonObject2.get(jsonKey);
                                map.put(jsonKey, jsonValue);
                            }
                            list.add(map);
                        }
                    }
                }
            }
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error1(new Object() {}, "Json converted to List<Map<String, Object>>, Exception = \n", e);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(obj, "Json converted to List<Map<String, Object>>, List<Map<String, Object>> = {}", list);
        }
        return list;
    }
}
