package com.huazie.fleaframework.core.filter.task;

import com.huazie.fleaframework.core.common.FleaCoreCommonException;

/**
 * <p> Flea 过滤器任务异常类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaFilterTaskException extends FleaCoreCommonException {

    public FleaFilterTaskException(String key) {
        super(key);
    }

    public FleaFilterTaskException(String key, String... values) {
        super(key, values);
    }

    public FleaFilterTaskException(String key, Throwable cause) {
        super(key, cause);
    }

    public FleaFilterTaskException(String key, Throwable cause, String... values) {
        super(key, cause, values);
    }
}
