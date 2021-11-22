package com.huazie.fleaframework.jersey.client.core;

import com.huazie.fleaframework.common.FleaConfigManager;
import com.huazie.fleaframework.jersey.common.FleaJerseyConstants;

/**
 * <p> Flea Jersey 客户端配置工具类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJerseyClientConfig {

    /**
     * <p> 获取系统账户编号 </p>
     *
     * @return 系统账户编号
     * @since 1.0.0
     */
    public static <T> T getSystemAccountId(Class<T> clazz) {
        T t = null;
        String systemAccountId = FleaConfigManager.getConfigItemValue(
                FleaJerseyConstants.JerseyClientConstants.CONFIG_ITEMS_KEY,
                FleaJerseyConstants.JerseyClientConstants.CONFIG_ITEM_SYSTEM_ACCOUNT_ID);
        if (clazz == String.class) {
            t = clazz.cast(systemAccountId);
        } else if (clazz == Long.class) {
            long sysAcctId = -1L;
            try {
                sysAcctId = Long.parseLong(systemAccountId);
            } finally {
                t = clazz.cast(sysAcctId);
            }
        }
        return t;
    }

    /**
     * <p> 获取系统账户密码 </p>
     *
     * @return 系统账户密码
     * @since 1.0.0
     */
    public static String getSystemAccountPwd() {
        return FleaConfigManager.getConfigItemValue(
                FleaJerseyConstants.JerseyClientConstants.CONFIG_ITEMS_KEY,
                FleaJerseyConstants.JerseyClientConstants.CONFIG_ITEM_SYSTEM_ACCOUNT_PWD);
    }

}
