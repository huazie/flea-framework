package com.huazie.frame.core.common;

/**
 * <p> 核心包常量类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface FleaCoreConstants {

    /**
     * <p> Flea Request配置数据常量 </p>
     *
     * @since 1.0.0
     */
    interface FleaRequestConfigConstants {
        /**
         * <p> Flea Request 默认文件路径 </p>
         */
        String FLEA_REQUEST_FILE_NAME = "flea/web/flea-request.xml";
        /**
         * <p> Flea Request 系统环境变量 </p>
         */
        String FLEA_REQUEST_FILE_SYSTEM_KEY = "fleaframe.web.flea.request.filename";
        /**
         * <p> Flea Request Filter 默认文件路径 </p>
         */
        String FLEA_REQUEST_FILTER_FILE_NAME = "flea/web/flea-request-filter.xml";
        /**
         * <p> Flea Request Filter 系统环境变量 </p>
         */
        String FLEA_REQUEST_FILTER_FILE_SYSTEM_KEY = "fleaframe.web.flea.request.filter.filename";
    }

}
