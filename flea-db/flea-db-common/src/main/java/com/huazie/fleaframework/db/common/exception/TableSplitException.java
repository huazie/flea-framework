package com.huazie.fleaframework.db.common.exception;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.i18n.FleaI18nResEnum;

/**
 * 分表实现异常类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class TableSplitException extends CommonException {

    private static final long serialVersionUID = -4673322966676796640L;

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
