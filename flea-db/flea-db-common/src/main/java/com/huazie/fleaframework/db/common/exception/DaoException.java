package com.huazie.fleaframework.db.common.exception;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.i18n.FleaI18nResEnum;

/**
 * <p> 持久化Dao层操作出现的异常 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class DaoException extends CommonException {

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
