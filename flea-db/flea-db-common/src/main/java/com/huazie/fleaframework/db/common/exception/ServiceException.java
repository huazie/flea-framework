package com.huazie.fleaframework.db.common.exception;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.i18n.FleaI18nResEnum;

/**
 * 业务逻辑层异常类，定义了业务逻辑层抛出的异常，
 * 其对应的国际化资源名为【error】
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class ServiceException extends CommonException {

    private static final long serialVersionUID = -3594701875813812571L;

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
