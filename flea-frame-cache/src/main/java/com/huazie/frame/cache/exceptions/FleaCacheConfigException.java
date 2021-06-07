package com.huazie.frame.cache.exceptions;

/**
 * FleaCache异常类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaCacheConfigException extends FleaCacheException {

    public FleaCacheConfigException(String message) {
        super(message);
    }

    public FleaCacheConfigException(Throwable e) {
        super(e);
    }

    public FleaCacheConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
