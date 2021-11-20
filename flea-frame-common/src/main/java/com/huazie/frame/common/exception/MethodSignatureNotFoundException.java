package com.huazie.frame.common.exception;

/**
 * 连接点方法签名未找到异常，当这个连接点的签名不是方法签名时会出现。
 *
 * @author huazie
 * @version 1.2.0
 * @since 1.2.0
 */
public class MethodSignatureNotFoundException extends FleaException {

    public MethodSignatureNotFoundException(String message) {
        super(message);
    }

    public MethodSignatureNotFoundException(Throwable e) {
        super(e);
    }

    public MethodSignatureNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
