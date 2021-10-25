package com.huazie.frame.db.common.exception;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.i18n.FleaI18nResEnum;

/**
 * <p> 分库实现异常类 </p>
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
