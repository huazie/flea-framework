package com.huazie.frame.common.util.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

public class JsonUtils {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

	/**
	 * 
	 * <p>使用net.sf.json解析，获取List<String></p>
	 * <pre>
	 *		String json = "{\"liststring\":[\"beijing\",\"shanghai\",\"hunan\"]}";
	 *		JsonUtils.getStringList(json, "liststring");
	 * </pre>
	 * 
	 * @version v1.0.0
	 * @date 2017年3月2日
	 *
	 * @param json
	 *            json字符串
	 * @param key
	 *            json对象的键
	 * @return
	 */
	public static List<String> getStringList(String json, String key) {
		List<String> list = new ArrayList<String>();
		try {
			JSONTokener jsonTokener = new JSONTokener(json);
			JSONObject jsonObject = (JSONObject) jsonTokener.nextValue();
			JSONArray jsonArray = jsonObject.getJSONArray(key);

			for (int i = 0; i < jsonArray.size(); i++) {
				list.add(jsonArray.getString(i));
			}
			if(JsonUtils.LOGGER.isDebugEnabled()){
				JsonUtils.LOGGER.debug("GsonUtils.getStringList() List={}", list);
			}
		} catch (Exception e) {
			JsonUtils.LOGGER.error("GsonUtils.getStringList() Exception={}", e);
		}
		return list;
	}

	/**
	 * 
	 * <p>使用net.sf.json解析，获取List<Map<String, Object>></p>
	 * <pre>
	 * 		String json = "{\"listmap\":[{\"address\":\"beijing\", \"name\":\"jack\", \"id\":1001}, {\"address\": \"shanghai\", \"name\":\"rose\", \"id\":1002}]}";
	 *		JsonUtils.getMapList(json, "listmap");
	 * </pre>
	 * 
	 * @version v1.0.0
	 * @date 2017年3月2日
	 *
	 * @param json
	 *            json字符串
	 * @param key
	 *            json对象的键
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> getMapList(String json, String key) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			JSONTokener jsonTokener = new JSONTokener(json);
			JSONObject jsonObject = (JSONObject) jsonTokener.nextValue();
			JSONArray jsonArray = jsonObject.getJSONArray(key);

			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject2 = jsonArray.getJSONObject(i);
				Map<String, Object> map = new HashMap<String, Object>();
				Iterator<String> iterator = jsonObject2.keys();
				while (iterator.hasNext()) {
					String json_key = iterator.next();
					Object json_value = jsonObject2.get(json_key);
					if (json_value == null) {
						json_value = "";
					}
					map.put(json_key, json_value);
				}
				list.add(map);
			}
			if(JsonUtils.LOGGER.isDebugEnabled()){
				JsonUtils.LOGGER.debug("GsonUtils.getMapList() List={}", list);
			}
		} catch (Exception e) {
			JsonUtils.LOGGER.error("GsonUtils.getMapList() Exception={}", e);
		}
		return list;
	}
	
	public static void main(String[] args) {
		String json = "{\"liststring\":[\"beijing\",\"shanghai\",\"hunan\"]}";
		JsonUtils.getStringList(json, "liststring");
	}
}
