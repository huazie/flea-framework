package com.huazie.fleaframework.cache.exceptions;

/**
 * Flea缓存配置异常
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.0.0
 */
public class FleaCacheConfigException extends FleaCacheException {

    private static final long serialVersionUID = 5684674612420720550L;

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
