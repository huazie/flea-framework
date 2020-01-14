package com.huazie.frame.auth.common.exception;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.i18n.FleaI18nResEnum;

/**
 * <p> Flea Auth通用异常处理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaAuthCommonException extends CommonException {

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
