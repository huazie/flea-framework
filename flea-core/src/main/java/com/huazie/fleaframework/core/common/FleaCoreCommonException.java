package com.huazie.fleaframework.core.common;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.i18n.FleaI18nResEnum;

/**
 * Flea Core 通用异常类，对应国际化资源名为 error_core。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaCoreCommonException extends CommonException {

    private static final long serialVersionUID = 4343210993561487190L;

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
