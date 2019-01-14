package com.huazie.frame.common.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * <p>
 * 		读取后缀为properties的配置文件
 * </p>
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月22日
 *
 */
public class PropertiesUtil {
	
	private final static Logger LOGGER = Logger.getLogger(PropertiesUtil.class);
	
	/**
	 * <p>
	 * 		获取配置对象
	 * </p>
	 * @version v1.0.0
	 * @date 2018年1月22日
	 *
	 * @param path
	 * @return
	 */
	public static Properties getProperties(String path){
		Properties prop = new Properties();
		InputStream input = null;
		BufferedReader reader = null;
		try {
			input = ResourcesUtil.getInputStreamFromClassPath(path);
			if(input == null){
				throw new Exception("该路径下找不到指定配置文件");
			}
			reader = new BufferedReader(new InputStreamReader(input));
			prop.load(reader);
		} catch (Exception e) {
			prop = null;
			PropertiesUtil.LOGGER.error("PropertiesUtil##getProperties 读取路径为【" + path + "】的配置出错", e);
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if(input != null){
					input.close();
				}
			} catch (Exception e) {
				prop = null;
				PropertiesUtil.LOGGER.error("PropertiesUtil##getProperties 读取路径为【" + path + "】的配置出错", e);
			}
		}
		
		return prop;
	}
	
	/**
	 * <p>
	 * 		根据键，获取对应的值
	 * </p>
	 * @version v1.0.0
	 * @date 2018年1月22日
	 *
	 * @param prop
	 * @param key
	 * @return
	 */
	public static Object getObjectValue(Properties prop, String key){
		Object value = null;
		if(prop != null){
			value = prop.get(key);
		}
		return value;
	}
	
	/**
	 * <p>
	 * 		根据键，获取对应的字符串值
	 * </p>
	 * @version v1.0.0
	 * @date 2018年1月22日
	 *
	 * @param prop
	 * @param key
	 * @return
	 */
	public static String getStringValue(Properties prop, String key){
		String value = null;
		if(prop != null){
			value = prop.getProperty(key);
		}
		return value;
	}
	
	/**
	 * <p>
	 * 		根据键，获取对应的Integer值
	 * </p>
	 * @version v1.0.0
	 * @date 2018年1月22日
	 *
	 * @param prop
	 * @param key
	 * @return
	 */
	public static Integer getIntegerValue(Properties prop, String key)throws Exception{
		Integer value = null;
		String val = getStringValue(prop, key);
		if(val != null){
			value = Integer.valueOf(val);
		}
		return value;
	}
	
	/**
	 * <p>
	 * 		根据键，获取对应的Long值
	 * </p>
	 * @version v1.0.0
	 * @date 2018年1月22日
	 *
	 * @param prop
	 * @param key
	 * @return
	 */
	public static Long getLongValue(Properties prop, String key)throws Exception{
		Long value = null;
		String val = getStringValue(prop, key);
		if(val != null){
			value = Long.valueOf(val);
		}
		return value;
	}
	
	/**
	 * <p>
	 * 		根据键，获取对应的Boolean值
	 * </p>
	 * @version v1.0.0
	 * @date 2018年1月23日
	 *
	 * @param prop
	 * @param key
	 * @return
	 */
	public static Boolean getBooleanValue(Properties prop, String key)throws Exception{
		Boolean value = null;
		String val = getStringValue(prop, key);
		if(val != null){
			value = Boolean.valueOf(val);
		}
		return value;
	}
}
