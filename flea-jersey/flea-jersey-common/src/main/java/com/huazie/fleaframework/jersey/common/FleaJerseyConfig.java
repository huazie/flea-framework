package com.huazie.fleaframework.jersey.common;

import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.FleaConfigManager;

/**
 * Flea Jersey 配置工具类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJerseyConfig {

    /**
     * 获取系统账户编号，可在配置文件 <b>flea-config.xml</b> 中查看
     * {@code <config-item key="system_account_id"> } 节点。
     *
     * @return 系统账户编号
     * @since 1.0.0
     */
    public static <T> T getSystemAccountId(Class<T> clazz) {
        T t = null;
        String systemAccountId = FleaConfigManager.getConfigItemValue(
                CommonConstants.FleaFrameInitConstants.FLEA_FRAMEWORK_INIT,
                CommonConstants.FleaFrameInitConstants.CONFIG_ITEM_SYSTEM_ACCOUNT_ID);
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

}
