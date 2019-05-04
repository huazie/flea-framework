package com.huazie.frame.db.common.exception;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.i18n.FleaI18nResEnum;

/**
 * <p> 持久化Dao层操作出现的异常 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class DaoException extends CommonException {

    public DaoException(String key) {
        super(key, FleaI18nResEnum.ERROR);
    }

    public DaoException(String key, String... values) {
        super(key, FleaI18nResEnum.ERROR, values);
    }

    public DaoException(String key, Throwable cause) {
        super(key, FleaI18nResEnum.ERROR, cause);
    }

    public DaoException(String key, Throwable cause, String... values) {
        super(key, FleaI18nResEnum.ERROR, cause, values);
    }

}
