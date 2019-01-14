package com.huazie.frame.common.i18n;

/**
 *  Flea I18n 资源枚举
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年11月12日
 *
 */
public enum FleaI18nResEnum {
	
	ERROR("error", "异常信息国际码资源文件类型"),
	COMMON("common", "公共信息国际码资源文件类型");
	
	private String resName;
	private String resDesc;

	private FleaI18nResEnum(String resName, String resDesc) {
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
