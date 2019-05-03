package com.huazie.frame.cache.common.exception;

import com.huazie.frame.common.exception.CommonException;

public class MemcachedException extends CommonException {

    public MemcachedException(String key) {
        super(key);
    }

    public MemcachedException(String key, String... values) {
        super(key, values);
    }

    public MemcachedException(String key, Throwable cause) {
        super(key, cause);
    }

    public MemcachedException(String key, Throwable cause, String... values) {
        super(key, cause, values);
    }
}
