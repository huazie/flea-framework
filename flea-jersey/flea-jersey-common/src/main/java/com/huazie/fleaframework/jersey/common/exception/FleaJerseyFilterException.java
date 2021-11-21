package com.huazie.fleaframework.jersey.common.exception;

/**
 * <p> Flea Jersey 服务端过滤器异常处理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJerseyFilterException extends FleaJerseyCommonException {

    public FleaJerseyFilterException(String key) {
        super(key);
    }

    public FleaJerseyFilterException(String key, String... values) {
        super(key, values);
    }

    public FleaJerseyFilterException(String key, Throwable cause) {
        super(key, cause);
    }

    public FleaJerseyFilterException(String key, Throwable cause, String... values) {
        super(key, cause, values);
    }

}
