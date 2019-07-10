package com.huazie.frame.common;

/**
 * <p> 公共常量类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CommonConstants {

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
        String FLEA_I18N_CONFIG_ITEM_KEY_FILE_NAME_PREFIX = "file_name_prefix";
        String FLEA_I18N_CONFIG_ITEM_KEY_FILE_PATH = "file_path";
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
}
