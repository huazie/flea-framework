package com.huazie.frame.common.exception;

import com.huazie.frame.common.FleaFrameManager;
import com.huazie.frame.common.i18n.FleaI18nHelper;
import com.huazie.frame.common.i18n.FleaI18nResEnum;
import com.huazie.frame.common.util.ArrayUtils;
import com.huazie.frame.common.util.ObjectUtils;

import java.util.Locale;

/**
 * <p> Flea I18N 通用异常 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class CommonException extends Exception {

    private String key;                     // 国际化资源数据关键字

    private Locale locale;                  // 国际化区域标识

    private FleaI18nResEnum i18nResEnum;    // 国际化资源类型

    public CommonException(String mKey, FleaI18nResEnum mI18nResEnum) {
        this(mKey, mI18nResEnum, FleaFrameManager.getManager().getLocale());// 使用服务器当前默认的国际化区域设置
    }

    public CommonException(String mKey, FleaI18nResEnum mI18nResEnum, String... mValues) {
        this(mKey, mI18nResEnum, FleaFrameManager.getManager().getLocale(), mValues);// 使用服务器当前默认的国际化区域设置
    }

    public CommonException(String mKey, FleaI18nResEnum mI18nResEnum, Locale mLocale) {
        this(mKey, mI18nResEnum, mLocale, new String[]{});// 使用指定的国际化区域设置
    }

    public CommonException(String mKey, FleaI18nResEnum mI18nResEnum, Locale mLocale, String... mValues) {
        super(convert(mKey, mValues, mI18nResEnum, mLocale)); // 使用指定的国际化区域设置
        key = mKey;
        locale = mLocale;
        i18nResEnum = mI18nResEnum;
    }

    public CommonException(String mKey, FleaI18nResEnum mI18nResEnum, Throwable cause) {
        this(mKey, mI18nResEnum, FleaFrameManager.getManager().getLocale(), cause);// 使用服务器当前默认的国际化区域设置
    }

    public CommonException(String mKey, FleaI18nResEnum mI18nResEnum, Throwable cause, String... mValues) {
        this(mKey, mI18nResEnum, FleaFrameManager.getManager().getLocale(), cause, mValues);// 使用服务器当前默认的国际化区域设置
    }

    public CommonException(String mKey, FleaI18nResEnum mI18nResEnum, Locale mLocale, Throwable cause) {
        this(mKey, mI18nResEnum, mLocale, cause, new String[]{});// 使用指定的国际化区域设置
    }

    public CommonException(String mKey, FleaI18nResEnum mI18nResEnum, Locale mLocale, Throwable cause, String... mValues) {
        super(convert(mKey, mValues, mI18nResEnum, mLocale), cause); // 使用指定的国际化区域设置
        key = mKey;
        locale = mLocale;
        i18nResEnum = mI18nResEnum;
    }

    private static String convert(String key, String[] values, FleaI18nResEnum i18nResEnum, Locale locale) {
        if (ObjectUtils.isEmpty(locale)) {
            locale = FleaFrameManager.getManager().getLocale(); // 使用服务器当前默认的国际化区域设置
        }
        if (ArrayUtils.isNotEmpty(values) && ObjectUtils.isNotEmpty(i18nResEnum)) {
            return FleaI18nHelper.i18n(key, values, i18nResEnum.getResName(), locale);
        } else {
            return FleaI18nHelper.i18n(key, i18nResEnum.getResName(), locale);
        }
    }

    public String getKey() {
        return key;
    }

    public Locale getLocale() {
        return locale;
    }

    public FleaI18nResEnum getI18nResEnum() {
        return i18nResEnum;
    }
}
