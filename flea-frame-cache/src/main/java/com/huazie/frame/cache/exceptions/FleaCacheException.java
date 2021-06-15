package com.huazie.frame.cache.exceptions;

/**
 * Flea缓存异常
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.0.0
 */
public class FleaCacheException extends RuntimeException {

    private static final long serialVersionUID = 2526455706598295071L;

    public FleaCacheException(String message) {
        super(message);
    }

    public FleaCacheException(Throwable e) {
        super(e);
    }

    public FleaCacheException(String message, Throwable cause) {
        super(message, cause);
    }
}
