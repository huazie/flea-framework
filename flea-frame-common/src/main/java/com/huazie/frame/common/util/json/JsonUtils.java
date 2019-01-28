package com.huazie.frame.common.util.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.huazie.frame.common.util.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

/**
 * <p> 工具包 </p>
 *
 * @author huazie
 * @version v1.0.0
 * @since 1.0.0
 */
public class JsonUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    /**
     * <p> 使用net.sf.json解析，获取String对象的List集合 </p>
     * <pre>
     * 		String json = "{\"liststring\":[\"beijing\",\"shanghai\",\"hunan\"]}";
     * 		JsonUtils.getStringList(json, "liststring");
     * </pre>
     *
     * @param json json字符串
     * @param key  json串的键
     * @return String对象的List集合
     * @since 1.0.0
     */
    public static List<String> toStringList(String json, String key) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("JsonUtils#toStringList(String, String) Json = {}", json);
            LOGGER.debug("JsonUtils#toStringList(String, String) Key = {}", key);
        }
        List<String> list = null;
        try {
            JSONTokener jsonTokener = new JSONTokener(json);
            if(null != jsonTokener){
                JSONObject jsonObject = (JSONObject) jsonTokener.nextValue();
                if(null != jsonObject){
                    JSONArray jsonArray = jsonObject.getJSONArray(key);
                    if(null != jsonArray && !jsonArray.isEmpty()){
                        list = new ArrayList<String>();
                        for (int i = 0; i < jsonArray.size(); i++) {
                            list.add(jsonArray.getString(i));
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("JsonUtils#toStringList() Exception={}", e);
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("JsonUtils#toStringList() List = {}", list);
        }
        return list;
    }

    /**
     * <p> 使用net.sf.json解析，获取Map对象的List集合 </p>
     * <pre>
     * 		String json = "{\"listmap\":[{\"address\":\"beijing\", \"name\":\"jack\", \"id\":1001}, {\"address\": \"shanghai\", \"name\":\"rose\", \"id\":1002}]}";
     * 		JsonUtils.getMapList(json, "listmap");
     * </pre>
     *
     * @param json json字符串
     * @param key  json对象的键
     * @return Map对象的List集合
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> toMapList(String json, String key) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("JsonUtils#toStringList(String, String) Json = {}", json);
            LOGGER.debug("JsonUtils#toStringList(String, String) Key = {}", key);
        }
        List<Map<String, Object>> list = null;
        try {
            JSONTokener jsonTokener = new JSONTokener(json);
            if (null != jsonTokener) {
                JSONObject jsonObject = (JSONObject) jsonTokener.nextValue();
                if (null != jsonObject) {
                    JSONArray jsonArray = jsonObject.getJSONArray(key);
                    if (null != jsonArray && !jsonArray.isEmpty()) {
                        list = new ArrayList<Map<String, Object>>();
                        for (int i = 0; i < jsonArray.size(); i++) {
                            JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                            if(null != jsonObject2){
                                Map<String, Object> map = new HashMap<String, Object>();
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
            }
        } catch (Exception e) {
            if(LOGGER.isErrorEnabled()){
                LOGGER.error("JsonUtils#toMapList() Exception = ", e);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("JsonUtils#toMapList() List = {}", list);
        }
        return list;
    }
}
