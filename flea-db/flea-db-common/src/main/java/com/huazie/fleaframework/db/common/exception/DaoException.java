package com.huazie.fleaframework.db.common.exception;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.i18n.FleaI18nResEnum;

/**
 * 持久化层异常类，定义了数据持久化层抛出的异常，
 * 其对应的国际化资源名为【error_db】
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class DaoException extends CommonException {

    private static final long serialVersionUID = -7126580845210939289L;

    public DaoException(String key) {
        super(key, FleaI18nResEnum.ERROR_DB);
    }

    public DaoException(String key, String... values) {
        super(key, FleaI18nResEnum.ERROR_DB, values);
    }

    public DaoException(String key, Throwable cause) {
        super(key, FleaI18nResEnum.ERROR_DB, cause);
    }

    public DaoException(String key, Throwable cause, String... values) {
        super(key, FleaI18nResEnum.ERROR_DB, cause, values);
    }

}
