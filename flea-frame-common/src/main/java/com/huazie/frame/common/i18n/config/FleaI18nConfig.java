package com.huazie.frame.common.i18n.config;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.i18n.pojo.FleaI18nData;
import com.huazie.frame.common.util.PropertiesUtil;
import com.huazie.frame.common.util.StringUtils;

/**
 *  i18n 配置类
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年7月29日
 *
 */
public class FleaI18nConfig {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(FleaI18nConfig.class);
	
	private static volatile FleaI18nConfig config;
	
	private static String FILE_NAME_PREFIX = CommonConstants.FleaI18NConstants.FLEA_I18N_FILE_NAME_PREFIX;
	
	private Map<String, ResourceBundle> resources = new HashMap<String, ResourceBundle>();//资源集合
	
	static{
		Properties fleaI18nConfig = PropertiesUtil.getProperties(CommonConstants.FleaI18NConstants.FLEA_I18N_CONFIG_FILE_NAME);
		if(fleaI18nConfig != null){
			FILE_NAME_PREFIX = PropertiesUtil.getStringValue(fleaI18nConfig, CommonConstants.FleaI18NConstants.FLEA_I18N_CONFIG_KEY_FILE_NAME_PREFIX);
		}
	}
	
	/**
	 * 只允许通过getConfig()获取 i18n配置类实例
	 */
	private FleaI18nConfig(){
	}

	public static FleaI18nConfig getConfig(){
		if(config == null){
			synchronized (FleaI18nConfig.class) {
				if(config == null){
					config = new FleaI18nConfig();
				}
			}
		}
		return config;
	}
	
	/**
	 * 通过国际化数据的key，获取当前系统指定资源的国际化资源
	 * 
	 * @date 2018年7月29日
	 *
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public FleaI18nData getI18NData(String key, String resName, Locale locale)throws Exception{
		return new FleaI18nData(key, this.getI18NDataValue(key, resName, locale));
	}
	
	/**
	 * 通过国际化数据的key，获取当前系统指定资源的国际化资源
	 * 
	 * @date 2018年12月24日
	 *
	 * @param key
	 * @param values
	 * @param resName
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	public String getI18NDataValue(String key, String[] values, String resName, Locale locale)throws Exception{
		String value = this.getI18NDataValue(key, resName, locale);
		if(!ArrayUtils.isEmpty(values)){
			StringBuilder strBuilder = new StringBuilder(value);
			for(int i = 0; i < values.length; i++){
				StringUtils.replace(strBuilder, CommonConstants.SymbolConstants.LEFT_CURLY_BRACE + i + CommonConstants.SymbolConstants.RIGHT_CURLY_BRACE, values[i]);
			}
			value = strBuilder.toString();
		}
		return value;
	}
	
	/**
	 * 通过国际化数据的key，获取当前系统指定资源的国际化资源
	 * 
	 * @date 2018年7月29日
	 *
	 * @param key
	 * @param resName
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	public String getI18NDataValue(String key, String resName, Locale locale)throws Exception{
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Find the key    : {}" , key);
			LOGGER.debug("Find the resource name: {}", resName);
			LOGGER.debug("Find the locale : {} , {}" , locale == null ? Locale.getDefault() : locale , locale == null ? Locale.getDefault().getDisplayLanguage() : locale.getDisplayLanguage());
		}
		ResourceBundle resource = this.getResouceBundle(resName, locale);
		
		String value = null;
		if(null != resource){
			value = resource.getString(key);
		}
		
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Find the value  : {} " , value);
		}
		return value;
	}
	
	/**
	 * 根据国际化标识获取指定国际化配置文件
	 * 
	 * @date 2018年8月12日
	 *
	 * @param locale
	 * @return
	 */
	private ResourceBundle getResouceBundle(String resName, Locale locale)throws Exception{
		
		String key = this.generateKey(resName, locale);
		ResourceBundle resource = this.resources.get(key);
		
		// 获取资源文件名
		StringBuilder fileName = new StringBuilder(FILE_NAME_PREFIX);
		if(StringUtils.isNotBlank(resName)){
			fileName.append(CommonConstants.SymbolConstants.UNDERLINE).append(resName);
		}
		
		// 获取资源文件
		if(null == resource){
			if(null == locale){
				resource = ResourceBundle.getBundle(fileName.toString());
			}else{
				resource = ResourceBundle.getBundle(fileName.toString(), locale);
			}
			this.resources.put(key, resource);
		}
		
		return resource;
	}
	
	/**
	 * 获取资源和国际化的key
	 * 
	 * 如果资源名不为空，则资源名作为key，同时如果国际化标识不为空，则取资源名+下划线+国际化语言作为key；
	 * 
	 * @date 2018年8月19日
	 *
	 * @param resName
	 *            资源名
	 * @param locale
	 *            国际化标识
	 * @return
	 */
	private String generateKey(String resName, Locale locale)throws Exception{
		String key = null;
		if(StringUtils.isNotBlank(resName)){
			key = resName;
			if(null != locale){
				key += CommonConstants.SymbolConstants.UNDERLINE + locale.getLanguage();
			}
		}else{
			// 国际化资源文件名不能为空
			throw new CommonException("ERROR0000000001", locale);
		}
		return key;
	}
	
}
