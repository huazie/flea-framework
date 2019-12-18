package com.huazie.frame.jersey.common.exception;

/**
 * <p> Flea Jersey 客户端异常处理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJerseyClientException extends FleaJerseyCommonException {

    public FleaJerseyClientException(String key) {
        super(key);
    }

    public FleaJerseyClientException(String key, String... values) {
        super(key, values);
    }

    public FleaJerseyClientException(String key, Throwable cause) {
        super(key, cause);
    }

    public FleaJerseyClientException(String key, Throwable cause, String... values) {
        super(key, cause, values);
    }

}
