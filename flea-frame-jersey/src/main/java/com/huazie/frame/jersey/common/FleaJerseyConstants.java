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
    }

}
