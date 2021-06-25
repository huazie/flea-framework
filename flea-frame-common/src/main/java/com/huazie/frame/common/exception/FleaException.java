package com.huazie.frame.common.exception;

/**
 * Flea异常
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.0.0
 */
public class FleaException extends RuntimeException {

    private static final long serialVersionUID = 2526455706598295071L;

    public FleaException(String message) {
        super(message);
    }

    public FleaException(Throwable e) {
        super(e);
    }

    public FleaException(String message, Throwable cause) {
        super(message, cause);
    }
}
