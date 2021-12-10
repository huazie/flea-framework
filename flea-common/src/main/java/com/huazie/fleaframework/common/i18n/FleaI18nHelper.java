package com.huazie.fleaframework.common.i18n;

import com.huazie.fleaframework.common.i18n.config.FleaI18nConfig;
import com.huazie.fleaframework.common.FleaFrameManager;

import java.util.Locale;

/**
 * Flea I18N 工具类, 用于获取本地国际化资源数据。
 *
 * @author huazie
 * @version 1.0.0
 * @see FleaI18nConfig
 * @since 1.0.0
 */
public class FleaI18nHelper {

    private FleaI18nHelper() {
    }

    /**
     * I18N资源数据获取
     *
     * @param key     资源数据编码
     * @param resName 资源文件名
     * @param locale  国际化标识
     * @return I18N资源数据
     * @since 1.0.0
     */
    public static String i18n(String key, String resName, Locale locale) {
        return FleaI18nConfig.getConfig().getI18NDataValue(key, resName, locale);
    }

    /**
     * I18N资源数据获取
     *
     * @param key     资源数据编码
     * @param values  待替换字符串数组
     * @param resName 资源文件名
     * @param locale  国际化标识
     * @return I18N资源数据
     * @since 1.0.0
     */
    public static String i18n(String key, String[] values, String resName, Locale locale) {
        return FleaI18nConfig.getConfig().getI18NDataValue(key, values, resName, locale);
    }

    /**
     * I18N资源数据获取。实际在调用该方法之前，可以通过
     * FleaFrameManager.getManager().setLocale(Locale)
     * 设置当前线程的国际化标识。
     *
     * @param key     资源数据编码
     * @param resName 资源文件名
     * @return I18N资源数据
     * @since 1.0.0
     */
    public static String i18n(String key, String resName) {
        return i18n(key, resName, FleaFrameManager.getManager().getLocale());
    }

    /**
     * I18N资源数据获取。实际在调用该方法之前，可以通过
     * FleaFrameManager.getManager().setLocale(Locale)
     * 设置当前线程的国际化标识。
     *
     * @param key     资源数据编码
     * @param values  待替换字符串数组
     * @param resName 资源文件名
     * @return I18N资源数据
     * @since 1.0.0
     */
    public static String i18n(String key, String[] values, String resName) {
        return i18n(key, values, resName, FleaFrameManager.getManager().getLocale());
    }

    /**
     * 错误码资源数据获取
     *
     * @param key 资源数据编码
     * @return I18N资源数据
     * @since 1.0.0
     */
    public static String i18nForError(String key) {
        return i18n(key, FleaI18nResEnum.ERROR.getResName());
    }

    /**
     * 错误码资源数据获取
     *
     * @param key    资源数据编码
     * @param values 待替换字符串数组
     * @return I18N资源数据
     * @since 1.0.0
     */
    public static String i18nForError(String key, String[] values) {
        return i18n(key, values, FleaI18nResEnum.ERROR.getResName());
    }

    /**
     * 错误码资源数据获取
     *
     * @param key    资源数据编码
     * @param locale 国际化标识
     * @return I18N资源数据
     * @since 1.0.0
     */
    public static String i18nForError(String key, Locale locale) {
        return i18n(key, FleaI18nResEnum.ERROR.getResName(), locale);
    }

    /**
     * 错误码资源数据获取
     *
     * @param key    资源数据编码
     * @param values 待替换字符串数组
     * @param locale 国际化标识
     * @return I18N资源数据
     * @since 1.0.0
     */
    public static String i18nForError(String key, String[] values, Locale locale) {
        return i18n(key, values, FleaI18nResEnum.ERROR.getResName(), locale);
    }

    /**
     * Auth 资源数据获取
     *
     * @param key 资源数据编码
     * @return I18N资源数据
     * @since 1.0.0
     */
    public static String i18nForAuth(String key) {
        return i18n(key, FleaI18nResEnum.AUTH.getResName());
    }

    /**
     * Auth 资源数据获取
     *
     * @param key    资源数据编码
     * @param values 待替换字符串数组
     * @return I18N资源数据
     * @since 1.0.0
     */
    public static String i18nForAuth(String key, String[] values) {
        return i18n(key, values, FleaI18nResEnum.AUTH.getResName());
    }

    /**
     * Auth 资源数据获取
     *
     * @param key    资源数据编码
     * @param locale 国际化标识
     * @return I18N资源数据
     * @since 1.0.0
     */
    public static String i18nForAuth(String key, Locale locale) {
        return i18n(key, FleaI18nResEnum.AUTH.getResName(), locale);
    }

    /**
     * Auth 资源数据获取
     *
     * @param key    资源数据编码
     * @param values 待替换字符串数组
     * @param locale 国际化标识
     * @return I18N资源数据
     * @since 1.0.0
     */
    public static String i18nForAuth(String key, String[] values, Locale locale) {
        return i18n(key, values, FleaI18nResEnum.AUTH.getResName(), locale);
    }

    /**
     * 公共资源数据获取
     *
     * @param key 资源数据编码
     * @return I18N资源数据
     * @since 1.0.0
     */
    public static String i18nForCommon(String key) {
        return i18n(key, FleaI18nResEnum.COMMON.getResName());
    }

    /**
     * 公共资源数据获取
     *
     * @param key    资源数据编码
     * @param values 待替换字符串数组
     * @return I18N资源数据
     * @since 1.0.0
     */
    public static String i18nForCommon(String key, String[] values) {
        return i18n(key, values, FleaI18nResEnum.COMMON.getResName());
    }

    /**
     * 公共资源数据获取
     *
     * @param key    资源数据编码
     * @param locale 国际化标识
     * @return I18N资源数据
     * @since 1.0.0
     */
    public static String i18nForCommon(String key, Locale locale) {
        return i18n(key, FleaI18nResEnum.COMMON.getResName(), locale);
    }

    /**
     * 公共资源数据获取
     *
     * @param key    资源数据编码
     * @param values 待替换字符串数组
     * @param locale 国际化标识
     * @return I18N资源数据
     * @since 1.0.0
     */
    public static String i18nForCommon(String key, String[] values, Locale locale) {
        return i18n(key, values, FleaI18nResEnum.COMMON.getResName(), locale);
    }
}
