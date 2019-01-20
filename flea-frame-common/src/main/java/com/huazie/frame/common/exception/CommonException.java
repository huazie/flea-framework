package com.huazie.frame.common.exception;

import com.huazie.frame.common.FleaFrameManager;
import com.huazie.frame.common.i18n.FleaI18nHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

/**
 * <p>Flea I18n通用异常实现</p>
 *  
 * @author huazie
 * @version v1.0.0
 * @since 1.0.0
 */
public class CommonException extends Exception {

	private final static Logger LOGGER = LoggerFactory.getLogger(CommonException.class);
	
	private String key;
	private Locale locale;

	public CommonException(String mKey) {
		this(mKey, FleaFrameManager.getManager().getLocale());// 使用服务器当前默认的国际化区域设置
	}
	
	public CommonException(String mKey, Locale mLocale) {
		super(convert(mKey, mLocale));// 使用指定的区域设置
		this.key = mKey;
		this.locale = mLocale;
	}
	
	private static String convert(String key, Locale locale){
		if(locale == null){
			locale = FleaFrameManager.getManager().getLocale(); //使用服务器当前默认的国际化区域设置
		}
		return FleaI18nHelper.i18nForError(key, locale);
	}

	public String getKey() {
		return this.key;
	}

	public Locale getLocale(){
		return this.locale;
	}

}
