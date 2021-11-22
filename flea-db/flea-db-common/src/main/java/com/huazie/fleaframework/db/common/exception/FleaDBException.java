package com.huazie.fleaframework.db.common.exception;

import com.huazie.fleaframework.common.exception.FleaException;

/**
 * Flea DB异常
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.0.0
 */
public class FleaDBException extends FleaException {

    private static final long serialVersionUID = 2526455706598295071L;

    public FleaDBException(String message) {
        super(message);
    }

    public FleaDBException(Throwable e) {
        super(e);
    }

    public FleaDBException(String message, Throwable cause) {
        super(message, cause);
    }
}
