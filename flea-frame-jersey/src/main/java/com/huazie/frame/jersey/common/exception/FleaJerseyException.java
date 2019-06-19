package com.huazie.frame.jersey.common.exception;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.i18n.FleaI18nResEnum;

/**
 * <p> Flea Resource 异常类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJerseyException extends CommonException {

    public FleaJerseyException(String key) {
        super(key, FleaI18nResEnum.ERROR);
    }

    public FleaJerseyException(String key, String... values) {
        super(key, FleaI18nResEnum.ERROR, values);
    }

    public FleaJerseyException(String key, Throwable cause) {
        super(key, FleaI18nResEnum.ERROR, cause);
    }

    public FleaJerseyException(String key, Throwable cause, String... values) {
        super(key, FleaI18nResEnum.ERROR, cause, values);
    }

}
