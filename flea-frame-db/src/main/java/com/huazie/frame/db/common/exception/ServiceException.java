package com.huazie.frame.db.common.exception;

import com.huazie.frame.common.exception.CommonException;

/**
 * <p> 业务逻辑层出现的异常 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class ServiceException extends CommonException {

    public ServiceException(String key) {
        super(key);
    }

    public ServiceException(String key, String... values) {
        super(key, values);
    }

    public ServiceException(String key, Throwable cause) {
        super(key, cause);
    }

    public ServiceException(String key, Throwable cause, String... values) {
        super(key, cause, values);
    }

}
