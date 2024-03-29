package com.huazie.fleaframework.common;

import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;

import java.util.Locale;

/**
 * Flea Frame Manager
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaFrameManager {

    private static volatile FleaFrameManager manager;

    private static ThreadLocal<Locale> sLocale = new ThreadLocal<>();// 存储国际化标识，每个线程留有一个副本

    private static ThreadLocal<String> sDBLocal = new ThreadLocal<>(); // 当前线程采用JDBC连的数据库前缀配置

    private static ThreadLocal<IFleaUser> sFleaUser = new ThreadLocal<>(); // 当前用户信息

    private FleaFrameManager() {
    }

    /**
     * 获取一个Flea Frame管理类实例
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
     * 获取当前线程的国际化区域标识
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
     * 设置当前线程的国际化标识
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
     * 获取当前线程中使用JDBC连接的数据库配置键
     *
     * @return 当前线程中使用JDBC连接的数据库配置键
     * @since 1.0.0
     */
    public String getDBConfigKey() {
        return sDBLocal.get();
    }

    /**
     * 设置当前线程中使用JDBC连接的数据库配置键
     *
     * @param dbSysName 数据库系统名
     * @param dbName    数据库名
     * @since 1.0.0
     */
    public void setDBConfigKey(String dbSysName, String dbName) {
        if (StringUtils.isNotBlank(dbSysName) && StringUtils.isNotBlank(dbName)) {
            sDBLocal.set(dbSysName.toLowerCase() + CommonConstants.SymbolConstants.HYPHEN + dbName.toLowerCase());
        }
    }

    /**
     * 获取当前线程的用户信息
     *
     * @return 用户信息
     */
    public IFleaUser getUserInfo() {
        return sFleaUser.get();
    }

    /**
     * 设置当前线程的用户信息
     *
     * @param fleaUser 用户信息
     * @since 1.0.0
     */
    public void setUserInfo(IFleaUser fleaUser) {
        if (ObjectUtils.isNotEmpty(fleaUser)) {
            sFleaUser.set(fleaUser);
        }
    }

}
