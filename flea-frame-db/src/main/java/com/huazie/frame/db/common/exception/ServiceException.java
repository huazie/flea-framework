package com.huazie.frame.db.common.exception;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.i18n.FleaI18nResEnum;

/**
 * <p> 业务逻辑层出现的异常 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class ServiceException extends CommonException {

    public ServiceException(String key) {
        super(key, FleaI18nResEnum.ERROR);
    }

    public ServiceException(String key, String... values) {
        super(key, FleaI18nResEnum.ERROR, values);
    }

    public ServiceException(String key, Throwable cause) {
        super(key, FleaI18nResEnum.ERROR, cause);
    }

    public ServiceException(String key, Throwable cause, String... values) {
        super(key, FleaI18nResEnum.ERROR, cause, values);
    }

}
