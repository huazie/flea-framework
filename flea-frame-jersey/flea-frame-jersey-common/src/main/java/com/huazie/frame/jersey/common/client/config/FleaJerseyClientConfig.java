package com.huazie.frame.jersey.common.client.config;

import com.huazie.frame.common.util.ObjectUtils;

/**
 * <p> Flea Jersey 客户端配置 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJerseyClientConfig {

    private static volatile FleaJerseyClientConfig config;

    private FleaJerseyClientConfig() {
    }

    public static FleaJerseyClientConfig getConfig() {
        if (ObjectUtils.isEmpty(config)) {
            synchronized (FleaJerseyClientConfig.class) {
                if (ObjectUtils.isEmpty(config)) {
                    config = new FleaJerseyClientConfig();
                }
            }
        }
        return config;
    }

    public String getSystemUserId() {
        return "1000";
    }

    public String getSystemUserPwd() {
        return "123ASD";
    }

}
