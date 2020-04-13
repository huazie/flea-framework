package com.huazie.frame.common.i18n;

/**
 * <p> Flea I18n 资源枚举 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public enum FleaI18nResEnum {

    ERROR("error", "异常信息国际码资源文件类型"),
    ERROR_CORE("error_core", "FLEA FRAME CORE异常信息国际码资源文件类型"),
    ERROR_DB("error_db", "FLEA FRAME DB异常信息国际码资源文件类型"),
    ERROR_JERSEY("error_jersey", "FLEA FRAME JERSEY异常信息国际码资源文件类型"),
    ERROR_AUTH("error_auth", "FLEA FRAME AUTH异常信息国际码资源文件类型"),
    COMMON("common", "公共信息国际码资源文件类型");

    private String resName;
    private String resDesc;

    /**
     * <p> 资源文件类型枚举构造方法 </p>
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
