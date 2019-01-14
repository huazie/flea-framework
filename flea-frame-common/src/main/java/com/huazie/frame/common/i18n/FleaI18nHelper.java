package com.huazie.frame.common.i18n;

import java.util.Locale;

import com.huazie.frame.common.FleaFrameManager;
import com.huazie.frame.common.i18n.config.FleaI18nConfig;

/**
 *  Flea I18N 工具类
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年11月12日
 *
 */
public class FleaI18nHelper {
	
	/**
	 * 获取i18n资源数据
	 * 
	 * @date 2018年11月14日
	 *
	 * @param key
	 *            资源数据编码
	 * @param resName
	 *            资源文件名
	 * @param locale
	 *            国际化标识
	 * @return i18n资源数据
	 * @throws Exception
	 */
	public static String i18n(String key, String resName, Locale locale)throws Exception{
		return FleaI18nConfig.getConfig().getI18NDataValue(key, resName, locale);
	}
	
	/**
	 * 获取i18n资源数据
	 * 
	 * @date 2018年11月14日
	 *
	 * @param key
	 *            资源数据编码
	 * @param values
	 *            待替换字符串数组
	 * @param resName
	 *            资源文件名
	 * @param locale
	 *            国际化标识
	 * @return i18n资源数据
	 * @throws Exception
	 */
	public static String i18n(String key, String[] values, String resName, Locale locale)throws Exception{
		return FleaI18nConfig.getConfig().getI18NDataValue(key, values, resName, locale);
	}
	
	/**
	 * 获取i18n资源数据
	 * 
	 * @date 2018年11月15日
	 *
	 * @param key
	 *            资源数据编码
	 * @param resName
	 *            资源文件名
	 * @return i18n资源数据
	 * @throws Exception
	 */
	public static String i18n(String key, String resName)throws Exception{
		return i18n(key, resName, FleaFrameManager.getManager().getLocale());
	}
	
	/**
	 * 获取i18n资源数据
	 * 
	 * @date 2018年11月15日
	 *
	 * @param key
	 *            资源数据编码
	 * @param values
	 * 			    待替换字符串数组
	 * @param resName
	 *            资源文件名
	 * @return i18n资源数据
	 * @throws Exception
	 */
	public static String i18n(String key, String[] values, String resName)throws Exception{
		return i18n(key, values, resName, FleaFrameManager.getManager().getLocale());
	}
	
	/**
	 * 错误码资源数据获取
	 * 
	 * @date 2018年11月14日
	 *
	 * @param key
	 *            资源数据编码
	 * @return i18n资源数据
	 * @throws Exception
	 */
	public static String i18nForError(String key)throws Exception{
		return i18n(key, FleaI18nResEnum.ERROR.getResName());
	}
	
	/**
	 * 错误码资源数据获取
	 * 
	 * @date 2018年11月14日
	 *
	 * @param key
	 *            资源数据编码
	 * @param values
	 *            待替换字符串数组
	 * @return i18n资源数据
	 * @throws Exception
	 */
	public static String i18nForError(String key, String[] values)throws Exception{
		return i18n(key, values, FleaI18nResEnum.ERROR.getResName());
	}
	
	/**
	 * 错误码资源数据获取
	 * 
	 * @date 2018年11月14日
	 *
	 * @param key
	 *            资源数据编码
	 * @param locale
	 *            国际化标识
	 * @return i18n资源数据
	 * @throws Exception
	 */
	public static String i18nForError(String key, Locale locale)throws Exception{
		return i18n(key, FleaI18nResEnum.ERROR.getResName(), locale);
	}
	
	/**
	 * 错误码资源数据获取
	 * 
	 * @date 2018年11月14日
	 *
	 * @param key
	 *            资源数据编码
	 * @param values
	 * 			    待替换字符串数组
	 * @param locale
	 *            国际化标识
	 * @return i18n资源数据
	 * @throws Exception
	 */
	public static String i18nForError(String key, String[] values, Locale locale)throws Exception{
		return i18n(key, values, FleaI18nResEnum.ERROR.getResName(), locale);
	}
	
	/**
	 * 公共资源数据获取
	 * 
	 * @date 2018年11月14日
	 *
	 * @param key
	 *            资源数据编码
	 * @param locale
	 *            国际化标识
	 * @return
	 * @throws Exception
	 */
	public static String i18nForCommon(String key)throws Exception{
		return i18n(key, FleaI18nResEnum.COMMON.getResName());
	}
	
	/**
	 * 公共资源数据获取
	 * 
	 * @date 2018年11月14日
	 *
	 * @param key
	 *            资源数据编码
	 * @param values
	 *            待替换字符串数组
	 * @param locale
	 *            国际化标识
	 * @return
	 * @throws Exception
	 */
	public static String i18nForCommon(String key, String[] values)throws Exception{
		return i18n(key, values, FleaI18nResEnum.COMMON.getResName());
	}
	
	/**
	 * 公共资源数据获取
	 * 
	 * @date 2018年11月14日
	 *
	 * @param key
	 *            资源数据编码
	 * @param locale
	 *            国际化标识
	 * @return
	 * @throws Exception
	 */
	public static String i18nForCommon(String key, Locale locale)throws Exception{
		return i18n(key, FleaI18nResEnum.COMMON.getResName(), locale);
	}
	
	/**
	 * 公共资源数据获取
	 * 
	 * @date 2018年11月14日
	 *
	 * @param key
	 *            资源数据编码
	 * @param values
	 * 			    待替换字符串数组
	 * @param locale
	 *            国际化标识
	 * @return
	 * @throws Exception
	 */
	public static String i18nForCommon(String key, String[] values, Locale locale)throws Exception{
		return i18n(key, values, FleaI18nResEnum.COMMON.getResName(), locale);
	}
}
