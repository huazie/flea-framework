package com.huazie.fleaframework.auth.common.exceptions;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.i18n.FleaI18nResEnum;

/**
 * Flea Auth通用异常类，其对应的国际化资源名为【error_auth】
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaAuthCommonException extends CommonException {

    private static final long serialVersionUID = -3459805370458702768L;

    public FleaAuthCommonException(String key) {
        super(key, FleaI18nResEnum.ERROR_AUTH);
    }

    public FleaAuthCommonException(String key, String... values) {
        super(key, FleaI18nResEnum.ERROR_AUTH, values);
    }

    public FleaAuthCommonException(String key, Throwable cause) {
        super(key, FleaI18nResEnum.ERROR_AUTH, cause);
    }

    public FleaAuthCommonException(String key, Throwable cause, String... values) {
        super(key, FleaI18nResEnum.ERROR_AUTH, cause, values);
    }

}
