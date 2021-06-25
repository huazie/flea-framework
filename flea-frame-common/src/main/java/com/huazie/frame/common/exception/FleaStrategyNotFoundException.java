package com.huazie.frame.common.exception;

/**
 * Flea策略未找到异常
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class FleaStrategyNotFoundException extends FleaStrategyException {

    public FleaStrategyNotFoundException(String message) {
        super(message);
    }

    public FleaStrategyNotFoundException(Throwable e) {
        super(e);
    }

    public FleaStrategyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
