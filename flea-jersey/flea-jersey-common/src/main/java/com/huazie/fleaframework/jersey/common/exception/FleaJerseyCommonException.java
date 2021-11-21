package com.huazie.fleaframework.jersey.common.exception;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.i18n.FleaI18nResEnum;

/**
 * <p> Flea Jersey 通用异常处理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJerseyCommonException extends CommonException {

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
