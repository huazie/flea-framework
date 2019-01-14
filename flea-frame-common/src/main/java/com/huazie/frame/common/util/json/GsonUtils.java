package com.huazie.frame.common.util.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * <p> Gson 工具包 </p>
 * 
 * @author huazie
 * @version v1.0.0
 * @date 2017年3月2日
 *
 */
public class GsonUtils {

	private final static Logger LOGGER = LoggerFactory.getLogger(GsonUtils.class);
	
	/**
	 * 
	 * <p> 使用Gson进行解析 ,并获取T </p>
	 * 
	 * @version v1.0.0
	 * @date 2017年3月2日
	 *
	 * @param json
	 *            json字符串
	 * @param clazz
	 *            实体类的Class对象
	 * @return
	 */
	public static <T> T getEntity(String json, Class<T> clazz) {
		T t = null;
		try {
			Gson gson = new Gson();
			t = gson.fromJson(json, clazz);
			if(GsonUtils.LOGGER.isDebugEnabled()){
				GsonUtils.LOGGER.debug("GsonUtils.getEntity() Entity={}", t);
			}
		} catch (Exception e) {
			GsonUtils.LOGGER.error("GsonUtils.getEntity() Exception={}", e);
		}
		return t;
	}

	/**
	 * 
	 * <p> 使用Gson进行解析 ,并获取List<T> </p>
	 * 
	 * @version v1.0.0
	 * @date 2017年3月2日
	 *
	 * @param json
	 *            json字符串
	 * @param clazz
	 *            实体类的Class对象
	 * @return
	 */
	public static <T> List<T> getEntityList(String json, Class<T> clazz) {
		List<T> list = new ArrayList<T>();
		try {
			Gson gson = new Gson();
			list = gson.fromJson(json, new TypeToken<List<T>>() {}.getType());
			if(GsonUtils.LOGGER.isDebugEnabled()){
				GsonUtils.LOGGER.debug("GsonUtils.getEntityList() List={}", list);
			}
		} catch (Exception e) {
			GsonUtils.LOGGER.error("GsonUtils.getEntityList() Exception={}", e);
		}
		return list;
	}
	
	/**
	 * <p> 使用Gson进行解析 ，并获取Map<String, Object> </p>
	 * 
	 * @version v1.0.0
	 * @date 2017年3月2日
	 * 
	 * @param json
	 *            json字符串
	 * @return
	 */
	public static Map<String, Object> getMap(String json) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Gson gson = new Gson();
			map = gson.fromJson(json, new TypeToken<Map<String, Object>>() {}.getType());
			if(GsonUtils.LOGGER.isDebugEnabled()){
				GsonUtils.LOGGER.debug("GsonUtils.getMap() Map={}", map);
			}
		} catch (Exception e) {
			GsonUtils.LOGGER.error("GsonUtils.getMap() Exception=",e);
		}
		return map;
	}
	
	/**
	 * 
	 * <p> 使用Gson进行解析 ,并获取List<Map<String, Object>> </p>
	 * 
	 * @version v1.0.0
	 * @date 2017年3月2日
	 *
	 * @param json
	 *            json字符串
	 * @return
	 */
	public static List<Map<String, Object>> getMapList(String json) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Gson gson = new Gson();
			list = gson.fromJson(json, new TypeToken<List<Map<String, Object>>>() {}.getType());
			if (GsonUtils.LOGGER.isDebugEnabled()) {
				GsonUtils.LOGGER.debug("GsonUtils.getMapList() List={}", list);
			}
		} catch (Exception e) {
			GsonUtils.LOGGER.error("GsonUtils.getMapList() Exception={}", e);
		}
		return list;
	}
}
