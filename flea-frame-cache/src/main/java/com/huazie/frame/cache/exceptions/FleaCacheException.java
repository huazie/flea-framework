package com.huazie.frame.cache.exceptions;

/**
 * FleaCache异常类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaCacheException extends RuntimeException {

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
