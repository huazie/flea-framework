package com.huazie.frame.common.util.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * 
 * <p>FastJson 工具包</p>
 * 
 * @author huazie
 * @version v1.0.0
 * @date 2017年3月2日
 *
 */
public class FastJsonUtils {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(FastJsonUtils.class);

	/**
	 * 
	 * <p>使用fastjson进行解析 ,获取T</p>
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
			t = JSON.parseObject(json, clazz);
			if(FastJsonUtils.LOGGER.isDebugEnabled()){
				FastJsonUtils.LOGGER.debug("FastJsonUtils.getEntity() Entity={}", t);
			}
		} catch (Exception e) {
			FastJsonUtils.LOGGER.error("FastJsonUtils.getEntity() Exception={}",e);
		}
		return t;
	}

	/**
	 * 
	 * <p> 使用fastjson进行解析 ,获取List<T> </p>
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
			list = JSON.parseArray(json, clazz);
			if(FastJsonUtils.LOGGER.isDebugEnabled()){
				FastJsonUtils.LOGGER.debug("FastJsonUtils.getEntityList() List={}", list);
			}
		} catch (Exception e) {
			FastJsonUtils.LOGGER.error("FastJsonUtils.getEntityList() Exception={}",e);
		}
		return list;
	}
	
	/**
	 * <p> 使用fastjson进行解析，并获取Map<String, Object> </p>
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
			map = JSON.parseObject(json, new TypeReference<Map<String, Object>>(){});
			if(FastJsonUtils.LOGGER.isDebugEnabled()){
				FastJsonUtils.LOGGER.debug("FastJsonUtils.getMap() Map={}", map);
			}
		} catch (Exception e) {
			FastJsonUtils.LOGGER.error("FastJsonUtils.getMap() Exception={}",e);
		}
		return map;
	}
	
	/**
	 * <p> 使用fastjson进行解析，并获取List<Map<String, Object>> </p>
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
			list = JSON.parseObject(json, new TypeReference<List<Map<String, Object>>>(){});
			if(FastJsonUtils.LOGGER.isDebugEnabled()){
				FastJsonUtils.LOGGER.debug("FastJsonUtils.getMapList() List={}", list);
			}
		} catch (Exception e) {
			FastJsonUtils.LOGGER.error("FastJsonUtils.getMapList() Exception={}",e);
		}
		return list;
	}
	
}
