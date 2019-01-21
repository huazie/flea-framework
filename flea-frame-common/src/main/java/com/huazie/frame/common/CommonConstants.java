package com.huazie.frame.common;

/**
 * <p>公共常量类</p>
 *
 * @author huazie
 * @version v1.0.0
 * @since 1.0.0
 */
public interface CommonConstants {

    /**
     * <p>方法常量</p>
     *
     * @author huazie
     * @since 1.0.0
     */
    interface MethodConstants {
        String GET = "get";
        String SET = "set";
    }

    /**
     * <p>符号常量</p>
     *
     * @author huazie
     * @since 1.0.0
     */
    interface SymbolConstants {
        /**
         * <p>左花括号</p>
         */
        String LEFT_CURLY_BRACE = "{";
        /**
         * <p>右花括号</p>
         */
        String RIGHT_CURLY_BRACE = "}";
        /**
         * <p>竖线</p>
         */
        String VERTICAL_LINE = "|";
        /**
         * <p>下划线</p>
         */
        String UNDERLINE = "_";
        /**
         * <p>点号</p>
         */
        String DOT = ".";
    }

    /**
     * <p>Flea I18N 常量</p>
     *
     * @author huazie
     * @since 1.0.0
     */
    interface FleaI18NConstants {
        String FLEA_I18N_FILE_NAME_PREFIX = "flea_i18n";
        String FLEA_I18N_CONFIG_FILE_NAME = "flea_i18n_config.properties";
        String FLEA_I18N_CONFIG_KEY_FILE_NAME_PREFIX = "file_name_prefix";
    }

    /**
     * <p>IP地址常量</p>
     *
     * @author huazie
     * @since 1.0.0
     */
    interface IPAddressConstants {
        /**
         * <p>国家</p>
         */
        String COUNTRY = "country";
        /**
         * <p>地区</p>
         */
        String REGION = "region";
        /**
         * <p>省份</p>
         */
        String PROVINCE = "province";
        /**
         * <p>城市</p>
         */
        String CITY = "city";
        /**
         * <p>互联网服务提供商</p>
         */
        String ISP = "isp";
    }
}
