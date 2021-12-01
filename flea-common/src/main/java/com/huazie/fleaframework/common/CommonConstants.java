package com.huazie.fleaframework.common;

/**
 * <p> 公共常量类 </p>
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
     * <p> 方法常量 </p>
     *
     * @since 1.0.0
     */
    interface MethodConstants {
        String GET = "get";
        String SET = "set";
    }

    /**
     * <p> 数字常量 </p>
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
         * <p> 整数 0 , long类型 </p>
         */
        long ZERO = 0L;
        /**
         * <p> 整数 0 , int类型 </p>
         */
        int INT_ZERO = 0;
        /**
         * <p> 整数 1 , int类型 </p>
         */
        int INT_ONE = 1;
        /**
         * <p> 整数 2 , int类型 </p>
         */
        int INT_TWO = 2;
        /**
         * <p> 整数 3 , int类型 </p>
         */
        int INT_THREE = 3;
    }

    /**
     * <p> 符号常量 </p>
     *
     * @since 1.0.0
     */
    interface SymbolConstants {
        /**
         * <p> 左花括号 </p>
         */
        String LEFT_CURLY_BRACE = "{";
        /**
         * <p> 右花括号 </p>
         */
        String RIGHT_CURLY_BRACE = "}";
        /**
         * <p> 竖线 </p>
         */
        String VERTICAL_LINE = "|";
        /**
         * <p> 下划线 </p>
         */
        String UNDERLINE = "_";
        /**
         * <p> 连号 </p>
         */
        String HYPHEN = "-";
        /**
         * <p> 点号 </p>
         */
        String DOT = ".";
        /**
         * <p> 斜杠 </p>
         */
        String SLASH = "/";
        /**
         * <p> 逗号 </p>
         */
        String COMMA = ",";
        /**
         * <p> 冒号 </p>
         */
        String COLON = ":";
        /**
         * <p> 星号 </p>
         */
        String ASTERISK = "*";
        /**
         * <p> 百分号 </p>
         */
        String PERCENT = "%";
    }

    /**
     * <p> Flea I18N 常量 </p>
     *
     * @since 1.0.0
     */
    interface FleaI18NConstants {
        String FLEA_I18N_FILE_PATH = "flea/i18n/";
        String FLEA_I18N_FILE_NAME_PREFIX = "flea_i18n";
        String FLEA_I18N_CONFIG_ITEMS_KEY = "flea-i18n-config";
    }

    /**
     * <p> IP地址常量 </p>
     *
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

    /**
     * <p> Flea Config 常量 </p>
     *
     * @since 1.0.0
     */
    interface FleaConfigConstants {
        /**
         * <p> Flea Config 默认文件路径 </p>
         */
        String FLEA_CONFIG_FILE_NAME = "flea/flea-config.xml";
        /**
         * <p> Flea Config 系统环境变量 </p>
         */
        String FLEA_CONFIG_FILE_SYSTEM_KEY = "fleaframe.flea.config.filename";
    }

    /**
     * <p> Flea池常量 </p>
     */
    interface FleaPoolConstants {
        /**
         * <p> 默认池名 </p>
         */
        String DEFAULT_POOL_NAME = "default";
        /**
         * <p> Flea对象池配置 </p>
         */
        String FLEA_OBJECT_POOL = "flea-object-pool";
    }

    /**
     * <p> Flea Frame 初始化常量 </p>
     *
     * @since 1.0.0
     */
    interface FleaFrameInitConstants {
        /**
         * <p> Flea Frame初始化配置 </p>
         */
        String FLEA_FRAME_INIT = "flea-framework-init";

        /**
         * <p> 失效时间(永久) </p>
         */
        String EXPIRY_TIME_FOREVER = "expiry_time_forever";
    }
}
