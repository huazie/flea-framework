package com.huazie.fleaframework.jersey.common.exceptions;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.i18n.FleaI18nResEnum;

/**
 * Flea Jersey 通用异常处理类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJerseyCommonException extends CommonException {

    private static final long serialVersionUID = -7784333975625919329L;

    public FleaJerseyCommonException(String key) {
        super(key, FleaI18nResEnum.ERROR_JERSEY);
    }

    public FleaJerseyCommonException(String key, String... values) {
        super(key, FleaI18nResEnum.ERROR_JERSEY, values);
    }

    public FleaJerseyCommonException(String key, Throwable cause) {
        super(key, FleaI18nResEnum.ERROR_JERSEY, cause);
    }

    public FleaJerseyCommonException(String key, Throwable cause, String... values) {
        super(key, FleaI18nResEnum.ERROR_JERSEY, cause, values);
    }

}
