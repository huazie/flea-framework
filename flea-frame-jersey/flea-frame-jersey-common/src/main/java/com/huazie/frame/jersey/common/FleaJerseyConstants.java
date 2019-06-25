package com.huazie.frame.jersey.common;

/**
 * <p> Flea Jersey 常量类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface FleaJerseyConstants {

    /**
     * <p> Jersey 过滤器常量 </p>
     *
     * @since 1.0.0
     */
    interface JerseyFilterConstants {
        /**
         * <p> Jersey 过滤器链配置文件路径 </p>
         */
        String JSERSY_FILTER_FILE_PATH = "flea/jersey/flea-jersey-filter.xml";
        /**
         * <p> Jersey 过滤器链配置文件系统属性键 </p>
         */
        String JERSEY_FILTER_FILE_SYSTEM_KEY = "fleaframe.jersey.filter.filename";
        /**
         * <p> Jersey 过滤器资源编码 </p>
         */
        String RESOURCE_CODE_FILTER = "jersey-filter-resource";
        /**
         * <p> Jersey 过滤器服务编码 </p>
         */
        String SERVICE_CODE_FILTER = "jersey-filter-service";

    }

    /**
     * <p> 响应结果常量 </p>
     *
     * @since 1.0.0
     */
    interface ResponseResultConstants {
        /**
         * <p> 响应成功的返回码 </p>
         */
        String RESULT_CODE_SUCCESS = "200";
        /**
         * <p> 响应成功的返回信息 </p>
         */
        String RESULT_MESS_SUCCESS = "success";
        /**
         * <p> 其他异常 (系统异常等，非自定义的异常) </p>
         */
        String RESULT_CODE_OTHER = "99999999";
        /**
         * <p> 返回码未配置 </p>
         */
        String RESULT_CODE_NOT_CONFIG = "88888888";
    }

    /**
     * <p> 请求公共报文常量 </p>
     *
     * @since 1.0.0
     */
    interface RequestPublicDataConstants {
        /**
         * <p> 系统用户编号 </p>
         */
        String SYSTEM_USER_ID = "SYSTEM_USER_ID";

        /**
         * <p> 系统用户密码（加密） </p>
         */
        String SYSTEM_USER_PWD = "SYSTEM_USER_PASSWORD";

        /**
         * <p> 资源编码 </p>
         */
        String RESOURCE_CODE = "RESOURCE_CODE";

        /**
         * <p> 服务编码 </p>
         */
        String SERVICE_CODE = "SERVICE_CODE";
    }

}
