package com.huazie.frame.db.common.exception;

import com.huazie.frame.common.exception.CommonException;

/**
 * <p> 处理SQL模板 异常实现类 </p>
 *
 * @author huazie
 * @version v1.0.0
 * @since 1.0.0
 */
public class SqlTemplateException extends CommonException {

    public SqlTemplateException(String key) {
        super(key);
    }

    public SqlTemplateException(String key, String... values) {
        super(key, values);
    }

    public SqlTemplateException(String key, Throwable cause) {
        super(key, cause);
    }

    public SqlTemplateException(String key, Throwable cause, String... values) {
        super(key, cause, values);
    }

}
