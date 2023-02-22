package com.huazie.fleaframework.db.common.exceptions;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.i18n.FleaI18nResEnum;

/**
 * 分库实现异常类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class LibSplitException extends CommonException {

    private static final long serialVersionUID = -5114305374845166242L;

    public LibSplitException(String key) {
        super(key, FleaI18nResEnum.ERROR_DB);
    }

    public LibSplitException(String key, String... values) {
        super(key, FleaI18nResEnum.ERROR_DB, values);
    }

    public LibSplitException(String key, Throwable cause) {
        super(key, FleaI18nResEnum.ERROR_DB, cause);
    }

    public LibSplitException(String key, Throwable cause, String... values) {
        super(key, FleaI18nResEnum.ERROR_DB, cause, values);
    }

}
