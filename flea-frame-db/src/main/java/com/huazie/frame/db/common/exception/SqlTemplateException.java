package com.huazie.frame.db.common.exception;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.i18n.FleaI18nResEnum;

/**
 * <p> 处理SQL模板 异常实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class SqlTemplateException extends CommonException {

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
