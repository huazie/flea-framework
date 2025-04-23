package com.huazie.fleaframework.common;

/**
 * 公共常量类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public final class CommonConstants {

    private CommonConstants() {
    }

    public static final class SystemConstants {
        public static final String APP_CODE = "APP_CODE";
    }

    /**
     * 方法常量
     *
     * @since 1.0.0
     */
    public static final class MethodConstants {
        public static final String GET = "get";
        public static final String SET = "set";
    }

    /**
     * 数字常量
     *
     * @since 1.0.0
     */
    public static final class NumeralConstants {
        /**
         * 整数 -1， public static final long类型
         */
        public static final long MINUS_ONE = -1L;
        /**
         * 整数 -2， public static final long类型
         */
        public static final long MINUS_TWO = -2L;
        /**
         * 整数 0 , public static final long类型
         */
        public static final long ZERO = 0L;
        /**
         * 整数 0 , public static final int类型
         */
        public static final int INT_ZERO = 0;
        /**
         * 整数 1 , public static final int类型
         */
        public static final int INT_ONE = 1;
        /**
         * 整数 2 , public static final int类型
         */
        public static final int INT_TWO = 2;
        /**
         * 整数 3 , public static final int类型
         */
        public static final int INT_THREE = 3;
    }

    /**
     * 符号常量
     *
     * @since 1.0.0
     */
    public static final class SymbolConstants {
        /**
         * 左花括号
         */
        public static final String LEFT_CURLY_BRACE = "{";
        /**
         * 右花括号
         */
        public static final String RIGHT_CURLY_BRACE = "}";
        /**
         * 竖线
         */
        public static final String VERTICAL_LINE = "|";
        /**
         * 下划线
         */
        public static final String UNDERLINE = "_";
        /**
         * 连号
         */
        public static final String HYPHEN = "-";
        /**
         * 点号
         */
        public static final String DOT = ".";
        /**
         * 斜杠
         */
        public static final String SLASH = "/";
        /**
         * 逗号
         */
        public static final String COMMA = ",";
        /**
         * 冒号
         */
        public static final String COLON = ":";
        /**
         * 星号
         */
        public static final String ASTERISK = "*";
        /**
         * 百分号
         */
        public static final String PERCENT = "%";
    }

    /**
     * Flea I18N 常量
     *
     * @since 1.0.0
     */
    public static final class FleaI18NConstants {
        public static final String FLEA_I18N_FILE_PATH = "flea/i18n/";
        public static final String FLEA_I18N_FILE_NAME_PREFIX = "flea_i18n";
        public static final String FLEA_I18N_CONFIG_ITEMS_KEY = "flea-i18n-config";
    }

    /**
     * IP地址常量
     *
     * @since 1.0.0
     */
    public static final class IPAddressConstants {
        /**
         * 国家
         */
        public static final String COUNTRY = "country";
        /**
         * 地区
         */
        public static final String REGION = "region";
        /**
         * 省份
         */
        public static final String PROVINCE = "province";
        /**
         * 城市
         */
        public static final String CITY = "city";
        /**
         * 互联网服务提供商
         */
        public static final String ISP = "isp";
    }

    /**
     * Flea Config 常量
     *
     * @since 1.0.0
     */
    public static final class FleaConfigConstants {
        /**
         * Flea Config 默认文件路径
         */
        public static final String FLEA_CONFIG_FILE_NAME = "flea/flea-config.xml";
        /**
         * Flea Config 系统环境变量
         */
        public static final String FLEA_CONFIG_FILE_SYSTEM_KEY = "fleaframework.flea.config.filename";
        /**
         * Flea URL 配置
         */
        public static final String FLEA_URL_CONFIG = "flea-url-config";
        /**
         * 淘宝IP地址信息查询URL
         */
        public static final String TAOBAO_IP_URL = "taobao_ip_url";
    }

    /**
     * Flea池常量
     *
     * @since 1.0.0
     */
    public static final class FleaPoolConstants {
        /**
         * 默认池名
         */
        public static final String DEFAULT_POOL_NAME = "default";
        /**
         * Flea对象池配置
         */
        public static final String FLEA_OBJECT_POOL = "flea-object-pool";
    }

    /**
     * Flea Frame 初始化常量
     *
     * @since 1.0.0
     */
    public static final class FleaFrameInitConstants {
        /**
         * Flea Framework 初始化配置
         */
        public static final String FLEA_FRAMEWORK_INIT = "flea-framework-init";

        /**
         * 系统账户编号
         */
        public static final String CONFIG_ITEM_SYSTEM_ACCOUNT_ID = "system_account_id";

        /**
         * 系统账户密码
         */
        public static final String CONFIG_ITEM_SYSTEM_ACCOUNT_PWD = "system_account_pwd";

        /**
         * 失效时间(永久)
         */
        public static final String CONFIG_ITEM_EXPIRY_TIME_FOREVER = "expiry_time_forever";
    }
}
