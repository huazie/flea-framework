package com.huazie.fleaframework.jersey.common.filter.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 国际码和错误码映射对象配置，可从配置文件【flea-jersey-filter.xml】
 * 中查看 {@code <i18n-error-mapping> </i18n-error-mapping>} 节点
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class I18nErrorMapping {

    private String i18nCode;

    private String errorCode;

    private String returnMess;

    public String getI18nCode() {
        return i18nCode;
    }

    public void setI18nCode(String i18nCode) {
        this.i18nCode = i18nCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getReturnMess() {
        return returnMess;
    }

    public void setReturnMess(String returnMess) {
        this.returnMess = returnMess;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
