package com.huazie.fleaframework.cache.exceptions;

/**
 * FleaCache最大尝试次数异常
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class FleaCacheMaxAttemptsException extends FleaCacheException {

    private static final long serialVersionUID = 6953127198007455758L;

    public FleaCacheMaxAttemptsException(String message) {
        super(message);
    }

    public FleaCacheMaxAttemptsException(Throwable e) {
        super(e);
    }

    public FleaCacheMaxAttemptsException(String message, Throwable cause) {
        super(message, cause);
    }
}
