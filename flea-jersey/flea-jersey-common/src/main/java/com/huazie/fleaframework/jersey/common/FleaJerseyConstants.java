package com.huazie.fleaframework.jersey.common;

/**
 * Flea Jersey 常量类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public final class FleaJerseyConstants {

    /**
     * Jersey 过滤器常量
     *
     * @since 1.0.0
     */
    public static final class JerseyFilterConstants {
        /**
         * Jersey 过滤器链配置文件路径
         */
        public static final String JSERSY_FILTER_FILE_PATH = "flea/jersey/flea-jersey-filter.xml";
        /**
         * Jersey 过滤器链配置文件系统属性键
         */
        public static final String JERSEY_FILTER_FILE_SYSTEM_KEY = "fleaframework.jersey.filter.filename";
        /**
         * Jersey Filter 国际码前缀
         */
        public static final String PREFIX_ERROR_JERSEY_FILTER = "ERROR-JERSEY-FILTER";
    }

    /**
     * 响应结果常量
     *
     * @since 1.0.0
     */
    public static final class ResponseResultConstants {
        /**
         * 响应成功的返回码
         */
        public static final String RESULT_CODE_SUCCESS = "000000";

        /**
         * 响应成功的返回信息
         */
        public static final String RESULT_MESS_SUCCESS = "success";

        /**
         * 返回码未配置
         */
        public static final String RESULT_CODE_NOT_CONFIG = "999998";

        /**
         * 未知异常 (系统异常等，非自定义的异常)
         */
        public static final String RESULT_CODE_OTHER = "999999";
    }

    /**
     * 请求公共报文常量
     *
     * @since 1.0.0
     */
    public static final class RequestPublicDataConstants {
        /**
         * 系统账户编号
         */
        public static final String SYSTEM_ACCOUNT_ID = "SYSTEM_ACCOUNT_ID";

        /**
         * 账户编号
         */
        public static final String ACCOUNT_ID = "ACCOUNT_ID";

        /**
         * 资源编码
         */
        public static final String RESOURCE_CODE = "RESOURCE_CODE";

        /**
         * 服务编码
         */
        public static final String SERVICE_CODE = "SERVICE_CODE";
    }

    /**
     * 表单数据常量
     *
     * @since 1.0.0
     */
    public static final class FormDataConstants {
        /**
         * 文件数据键
         */
        public static final String FORM_DATA_KEY_FILE = "FILE";

        /**
         * 请求数据键
         */
        public static final String FORM_DATA_KEY_REQUEST = "REQUEST";

        /**
         * 响应数据键
         */
        public static final String FORM_DATA_KEY_RESPONSE = "RESPONSE";
    }

    /**
     * 文件资源常量
     *
     * @since 1.0.0
     */
    public static final class FileResourceConstants {
        /**
         * 文件上传资源路径
         */
        public static final String FILE_UPLOAD_PATH = "fileUpload";
        /**
         * 文件下载资源路径
         */
        public static final String FILE_DOWNLOAD_PATH = "fileDownload";
    }

}
