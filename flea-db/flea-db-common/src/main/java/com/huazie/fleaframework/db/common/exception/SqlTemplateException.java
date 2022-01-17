package com.huazie.fleaframework.db.common.exception;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.i18n.FleaI18nResEnum;

/**
 * SQL模板异常类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class SqlTemplateException extends CommonException {

    private static final long serialVersionUID = 5955045201447063616L;

    public SqlTemplateException(String key) {
        super(key, FleaI18nResEnum.ERROR_DB);
    }

    public SqlTemplateException(String key, String... values) {
        super(key, FleaI18nResEnum.ERROR_DB, values);
    }

    public SqlTemplateException(String key, Throwable cause) {
        super(key, FleaI18nResEnum.ERROR_DB, cause);
    }

    public SqlTemplateException(String key, Throwable cause, String... values) {
        super(key, FleaI18nResEnum.ERROR_DB, cause, values);
    }

}
