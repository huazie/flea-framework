package com.huazie.fleaframework.common.exception;

/**
 * Flea策略异常
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class FleaStrategyException extends FleaException {

    private static final long serialVersionUID = 1814380613775967198L;

    public FleaStrategyException(String message) {
        super(message);
    }

    public FleaStrategyException(Throwable e) {
        super(e);
    }

    public FleaStrategyException(String message, Throwable cause) {
        super(message, cause);
    }
}
