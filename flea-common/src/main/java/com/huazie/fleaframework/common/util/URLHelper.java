package com.huazie.fleaframework.common.util;

import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.FleaConfigManager;

/**
 * URL相关工具类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class URLHelper {

    private static final String TAOBAO_IP_URL = "https://ip.taobao.com/outGetIpInfo?accessKey=alibaba-inc&ip=";

    /**
     * 获取淘宝IP地址查询的URL
     *
     * @return 淘宝IP地址查询的URL
     * @since 2.0.0
     */
    public static String getTaobaoIPURL() {
        String taobaoIPUrl = getURL(CommonConstants.FleaConfigConstants.TAOBAO_IP_URL);
        if (StringUtils.isBlank(taobaoIPUrl)) {
            taobaoIPUrl = TAOBAO_IP_URL;
        }
        return taobaoIPUrl;
    }

    /**
     * 获取指定配置的URL
     *
     * @param urlKey 指定的URL键
     * @return 指定URL键对应的URL配置
     * @since 2.0.0
     */
    public static String getURL(String urlKey) {
        // 获取Flea URL配置
        return FleaConfigManager.getConfigItemValue(CommonConstants.FleaConfigConstants.FLEA_URL_CONFIG, urlKey);
    }
}
