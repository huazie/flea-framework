package com.huazie.frame.tools.common;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.i18n.FleaI18nResEnum;

/**
 * <p> 工具类常用异常 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class ToolsException extends CommonException {

    private static final long serialVersionUID = -4026453350524815598L;

    public ToolsException(String key) {
        super(key, FleaI18nResEnum.COMMON);
    }

    public ToolsException(String key, String... values) {
        super(key, FleaI18nResEnum.COMMON, values);
    }

    public ToolsException(String key, Throwable cause) {
        super(key, FleaI18nResEnum.COMMON, cause);
    }

    public ToolsException(String key, Throwable cause, String... values) {
        super(key, FleaI18nResEnum.COMMON, cause, values);
    }

}
