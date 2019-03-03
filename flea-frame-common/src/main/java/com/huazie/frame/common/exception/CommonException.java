package com.huazie.frame.common.exception;

import com.huazie.frame.common.FleaFrameManager;
import com.huazie.frame.common.i18n.FleaI18nHelper;

import java.util.Locale;

/**
 * <p> Flea I18n 通用异常实现 </p>
 *
 * @author huazie
 * @version v1.0.0
 * @since 1.0.0
 */
public class CommonException extends Exception {

    private String key;
    private Locale locale;

    public CommonException(String mKey) {
        this(mKey, FleaFrameManager.getManager().getLocale());// 使用服务器当前默认的国际化区域设置
    }

    public CommonException(String mKey, String... mValues) {
        this(mKey, FleaFrameManager.getManager().getLocale(), mValues);// 使用服务器当前默认的国际化区域设置
    }

    public CommonException(String mKey, Locale mLocale) {
        this(mKey, mLocale, null);// 使用指定的国际化区域设置
    }

    public CommonException(String mKey, Locale mLocale, String... mValues) {
        super(convert(mKey, mValues, mLocale)); // 使用指定的国际化区域设置
        this.key = mKey;
        this.locale = mLocale;
    }

    public CommonException(String mKey, Throwable cause) {
        this(mKey, cause, FleaFrameManager.getManager().getLocale());// 使用服务器当前默认的国际化区域设置
    }

    public CommonException(String mKey, Throwable cause, String... mValues) {
        this(mKey, cause, FleaFrameManager.getManager().getLocale(), mValues);// 使用服务器当前默认的国际化区域设置
    }

    public CommonException(String mKey, Throwable cause, Locale mLocale) {
        this(mKey, cause, mLocale, null);// 使用指定的国际化区域设置
    }

    public CommonException(String mKey, Throwable cause, Locale mLocale, String... mValues) {
        super(convert(mKey, mValues, mLocale), cause); // 使用指定的国际化区域设置
        this.key = mKey;
        this.locale = mLocale;
    }

    private static String convert(String key, String[] values, Locale locale) {
        if (null == locale) {
            locale = FleaFrameManager.getManager().getLocale(); // 使用服务器当前默认的国际化区域设置
        }
        if(null != values){
            return FleaI18nHelper.i18nForError(key, values, locale);
        }else{
            return FleaI18nHelper.i18nForError(key, locale);
        }
    }

    public String getKey() {
        return this.key;
    }

    public Locale getLocale() {
        return this.locale;
    }

}
