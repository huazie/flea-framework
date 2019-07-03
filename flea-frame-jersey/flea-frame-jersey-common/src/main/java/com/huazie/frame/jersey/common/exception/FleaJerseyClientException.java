package com.huazie.frame.jersey.common.exception;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.i18n.FleaI18nResEnum;

/**
 * <p> Flea Jersey 客户端异常处理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJerseyClientException extends CommonException {

    public FleaJerseyClientException(String key) {
        super(key, FleaI18nResEnum.ERROR_JERSEY);
    }

    public FleaJerseyClientException(String key, String... values) {
        super(key, FleaI18nResEnum.ERROR_JERSEY, values);
    }

    public FleaJerseyClientException(String key, Throwable cause) {
        super(key, FleaI18nResEnum.ERROR_JERSEY, cause);
    }

    public FleaJerseyClientException(String key, Throwable cause, String... values) {
        super(key, FleaI18nResEnum.ERROR_JERSEY, cause, values);
    }

}
