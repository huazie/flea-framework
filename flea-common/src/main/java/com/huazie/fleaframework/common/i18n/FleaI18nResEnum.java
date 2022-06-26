package com.huazie.fleaframework.common.i18n;

/**
 * Flea I18N 资源枚举
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public enum FleaI18nResEnum {

    ERROR("error", "异常信息国际码资源文件类型"),
    ERROR_CORE("error_core", "FLEA CORE异常信息国际码资源文件类型"),
    ERROR_DB("error_db", "FLEA DB异常信息国际码资源文件类型"),
    ERROR_JERSEY("error_jersey", "FLEA JERSEY异常信息国际码资源文件类型"),
    ERROR_AUTH("error_auth", "FLEA AUTH异常信息国际码资源文件类型"),
    AUTH("auth", "FLEA AUTH 国际码资源文件类型"),
    COMMON("common", "公共信息国际码资源文件类型");

    private String resName;
    private String resDesc;

    /**
     * 资源文件类型枚举构造方法
     *
     * @param resName 资源名
     * @param resDesc 资源描述
     * @since 1.0.0
     */
    FleaI18nResEnum(String resName, String resDesc) {
        this.resName = resName;
        this.resDesc = resDesc;
    }

    public String getResName() {
        return resName;
    }

    public String getResDesc() {
        return resDesc;
    }

}
