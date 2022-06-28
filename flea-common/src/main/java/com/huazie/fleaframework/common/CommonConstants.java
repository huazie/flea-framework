package com.huazie.fleaframework.common;

/**
 * 公共常量类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CommonConstants {

    interface SystemConstants {
        String APP_CODE = "APP_CODE";
    }

    /**
     * 方法常量
     *
     * @since 1.0.0
     */
    interface MethodConstants {
        String GET = "get";
        String SET = "set";
    }

    /**
     * 数字常量
     *
     * @since 1.0.0
     */
    interface NumeralConstants {
        /**
         * 整数 -1， long类型
         */
        long MINUS_ONE = -1L;
        /**
         * 整数 -2， long类型
         */
        long MINUS_TWO = -2L;
        /**
         * 整数 0 , long类型
         */
        long ZERO = 0L;
        /**
         * 整数 0 , int类型
         */
        int INT_ZERO = 0;
        /**
         * 整数 1 , int类型
         */
        int INT_ONE = 1;
        /**
         * 整数 2 , int类型
         */
        int INT_TWO = 2;
        /**
         * 整数 3 , int类型
         */
        int INT_THREE = 3;
    }

    /**
     * 符号常量
     *
     * @since 1.0.0
     */
    interface SymbolConstants {
        /**
         * 左花括号
         */
        String LEFT_CURLY_BRACE = "{";
        /**
         * 右花括号
         */
        String RIGHT_CURLY_BRACE = "}";
        /**
         * 竖线
         */
        String VERTICAL_LINE = "|";
        /**
         * 下划线
         */
        String UNDERLINE = "_";
        /**
         * 连号
         */
        String HYPHEN = "-";
        /**
         * 点号
         */
        String DOT = ".";
        /**
         * 斜杠
         */
        String SLASH = "/";
        /**
         * 逗号
         */
        String COMMA = ",";
        /**
         * 冒号
         */
        String COLON = ":";
        /**
         * 星号
         */
        String ASTERISK = "*";
        /**
         * 百分号
         */
        String PERCENT = "%";
    }

    /**
     * Flea I18N 常量
     *
     * @since 1.0.0
     */
    interface FleaI18NConstants {
        String FLEA_I18N_FILE_PATH = "flea/i18n/";
        String FLEA_I18N_FILE_NAME_PREFIX = "flea_i18n";
        String FLEA_I18N_CONFIG_ITEMS_KEY = "flea-i18n-config";
    }

    /**
     * IP地址常量
     *
     * @since 1.0.0
     */
    interface IPAddressConstants {
        /**
         * 国家
         */
        String COUNTRY = "country";
        /**
         * 地区
         */
        String REGION = "region";
        /**
         * 省份
         */
        String PROVINCE = "province";
        /**
         * 城市
         */
        String CITY = "city";
        /**
         * 互联网服务提供商
         */
        String ISP = "isp";
    }

    /**
     * Flea Config 常量
     *
     * @since 1.0.0
     */
    interface FleaConfigConstants {
        /**
         * Flea Config 默认文件路径
         */
        String FLEA_CONFIG_FILE_NAME = "flea/flea-config.xml";
        /**
         * Flea Config 系统环境变量
         */
        String FLEA_CONFIG_FILE_SYSTEM_KEY = "fleaframework.flea.config.filename";
        /**
         * Flea URL 配置
         */
        String FLEA_URL_CONFIG = "flea-url-config";
        /**
         * 淘宝IP地址信息查询URL
         */
        String TAOBAO_IP_URL = "taobao_ip_url";
    }

    /**
     * Flea池常量
     */
    interface FleaPoolConstants {
        /**
         * 默认池名
         */
        String DEFAULT_POOL_NAME = "default";
        /**
         * Flea对象池配置
         */
        String FLEA_OBJECT_POOL = "flea-object-pool";
    }

    /**
     * Flea Frame 初始化常量
     *
     * @since 1.0.0
     */
    interface FleaFrameInitConstants {
        /**
         * Flea Framework 初始化配置
         */
        String FLEA_FRAMEWORK_INIT = "flea-framework-init";

        /**
         * 系统账户编号
         */
        String CONFIG_ITEM_SYSTEM_ACCOUNT_ID = "system_account_id";

        /**
         * 系统账户密码
         */
        String CONFIG_ITEM_SYSTEM_ACCOUNT_PWD = "system_account_pwd";

        /**
         * 失效时间(永久)
         */
        String CONFIG_ITEM_EXPIRY_TIME_FOREVER = "expiry_time_forever";
    }
}
