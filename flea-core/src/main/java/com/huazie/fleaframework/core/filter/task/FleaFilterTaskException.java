package com.huazie.fleaframework.core.filter.task;

import com.huazie.fleaframework.core.common.FleaCoreCommonException;

/**
 * Flea 过滤器任务异常类，对应国际化资源名为 error_core。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaFilterTaskException extends FleaCoreCommonException {

    private static final long serialVersionUID = 6035285395039000686L;

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
