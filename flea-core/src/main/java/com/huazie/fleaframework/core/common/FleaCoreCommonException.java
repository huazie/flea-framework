package com.huazie.fleaframework.core.common;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.i18n.FleaI18nResEnum;

/**
 * <p> Flea Core 通用异常类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaCoreCommonException extends CommonException {

    public FleaCoreCommonException(String key) {
        super(key, FleaI18nResEnum.ERROR_CORE);
    }

    public FleaCoreCommonException(String key, String... values) {
        super(key, FleaI18nResEnum.ERROR_CORE, values);
    }

    public FleaCoreCommonException(String key, Throwable cause) {
        super(key, FleaI18nResEnum.ERROR_CORE, cause);
    }

    public FleaCoreCommonException(String key, Throwable cause, String... values) {
        super(key, FleaI18nResEnum.ERROR_CORE, cause, values);
    }
}
