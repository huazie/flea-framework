package com.huazie.frame.db.common.exception;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.i18n.FleaI18nResEnum;

/**
 * <p> 分表实现异常类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class TableSplitException extends CommonException {

    public TableSplitException(String key) {
        super(key, FleaI18nResEnum.ERROR_DB);
    }

    public TableSplitException(String key, String... values) {
        super(key, FleaI18nResEnum.ERROR_DB, values);
    }

    public TableSplitException(String key, Throwable cause) {
        super(key, FleaI18nResEnum.ERROR_DB, cause);
    }

    public TableSplitException(String key, Throwable cause, String... values) {
        super(key, FleaI18nResEnum.ERROR_DB, cause, values);
    }

}
