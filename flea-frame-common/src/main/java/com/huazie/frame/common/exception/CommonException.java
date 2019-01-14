package com.huazie.frame.common.exception;

import java.util.Locale;

import com.huazie.frame.common.FleaFrameManager;
import com.huazie.frame.common.i18n.FleaI18nHelper;

/**
 *  Flea I18n通用异常实现
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年11月12日
 *
 */
@SuppressWarnings("serial")
public class CommonException extends Exception {
	
	private String key;
	private Locale locale;

	public CommonException(String mKey) {
		this(mKey, FleaFrameManager.getManager().getLocale());// 使用服务器当前默认的区域设置
	}
	
	public CommonException(String mKey, Locale mLocale) {
		super(convert(mKey, mLocale));// 使用指定的区域设置
		this.key = mKey;
		this.locale = mLocale;
	}
	
	private static String convert(String key, Locale locale){
		if(locale == null){
			locale = Locale.getDefault();
		}
		String value = "";
		try {
			value = FleaI18nHelper.i18nForError(key, locale);
		} catch (Exception e) {
		}
		return value;
	}

	public String getKey() {
		return key;
	}

	public Locale getLocale() {
		return locale;
	}

}
