package com.huazie.frame.common;

import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;

import java.util.Locale;

/**
 * <p> Flea Frame Manager </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaFrameManager {

    private static volatile FleaFrameManager manager;

    private static ThreadLocal<Locale> sLocale = new ThreadLocal<Locale>();// 存储国际化标识，每个线程留有一个副本

    private static ThreadLocal<String> sDBLocal = new ThreadLocal<String>(); // 当前线程采用JDBC连的数据库前缀配置

    private FleaFrameManager() {
    }

    /**
     * <p> 获取一个Flea Frame管理类实例 </p>
     *
     * @return Flea Frame 管理类
     * @since 1.0.0
     */
    public static FleaFrameManager getManager() {
        if (ObjectUtils.isEmpty(manager)) {
            synchronized (FleaFrameManager.class) {
                if (ObjectUtils.isEmpty(manager)) {
                    manager = new FleaFrameManager();
                }
            }
        }
        return manager;
    }

    /**
     * <p>获取当前线程的国际化区域标识</p>
     *
     * @return 当前线程设置的国际化区域标识
     * @since 1.0.0
     */
    public Locale getLocale() {
        Locale locale = sLocale.get();
        if (ObjectUtils.isEmpty(locale)) {
            locale = Locale.getDefault();
        }
        return locale;
    }

    /**
     * <p>设置当前线程的国际化标识</p>
     *
     * @param locale 国际化区域标识
     * @since 1.0.0
     */
    public void setLocale(Locale locale) {
        if (ObjectUtils.isEmpty(locale)) {
            locale = Locale.getDefault();
        }
        sLocale.set(locale);
    }

    /**
     * <p> 获取当前线程中使用JDBC连接的数据库配置前缀 </p>
     *
     * @return 当前线程中使用JDBC连接的数据库配置前缀
     */
    public String getDBPrefix() {
        return sDBLocal.get();
    }

    /**
     * <p> 设置当前线程中使用JDBC连接的数据库配置前缀 </p>
     *
     * @param dbSysName 数据库系统名
     * @param dbName    数据库名
     */
    public void setDBPrefix(String dbSysName, String dbName) {
        if (StringUtils.isNotBlank(dbSysName) && StringUtils.isNotBlank(dbName)) {
            sDBLocal.set(dbSysName.toLowerCase() + CommonConstants.SymbolConstants.DOT + dbName.toLowerCase());
        }
    }

}
