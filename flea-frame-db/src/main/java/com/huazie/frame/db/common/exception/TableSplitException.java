package com.huazie.frame.db.common.exception;

import com.huazie.frame.common.exception.CommonException;

/**
 * <p> 操作分表 异常实现类 </p>
 *
 * @author huazie
 * @version v1.0.0
 * @since 1.0.0
 */
public class TableSplitException extends CommonException {

    public TableSplitException(String key) {
        super(key);
    }

    public TableSplitException(String key, String... values) {
        super(key, values);
    }

    public TableSplitException(String key, Throwable cause) {
        super(key, cause);
    }

    public TableSplitException(String key, Throwable cause, String... values) {
        super(key, cause, values);
    }

}
