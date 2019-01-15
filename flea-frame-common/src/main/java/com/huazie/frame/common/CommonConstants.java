package com.huazie.frame.common;

/**
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月24日
 *
 */
public interface CommonConstants {
	
	/**
	 * <p>
	 * 方法常量
	 *  
	 * @author huazie
	 * @version v1.0.0
	 * @date 2018年1月29日
	 *
	 */
	interface MethodConstants{
		String GET = "get";
		String SET = "set";
	}
	
	/**
	 * <p>
	 * 符号常量
	 *  
	 * @author huazie
	 * @version v1.0.0
	 * @date 2018年6月3日
	 *
	 */
	interface SymbolConstants{
		/**
		 * 左花括号
		 */
		String LEFT_CURLY_BRACE = "{";
		/**
		 * 右花括号
		 */
		String RIGHT_CURLY_BRACE = "}";
		/**
		 * 竖线
		 */
		String VERTICALLINE = "|";
		/**
		 * 下划线
		 */
		String UNDERLINE = "_";
		/**
		 * 点号
		 */
		String DOT = ".";
	}
	
	/**
	 * 
	 *  
	 * @author huazie
	 * @version v1.0.0
	 * @date 2018年11月8日
	 *
	 */
	interface FleaI18NConstants{
		String FLEA_I18N_FILE_NAME_PREFIX = "flea_i18n";
		String FLEA_I18N_CONFIG_FILE_NAME = "flea_i18n_config.properties";
		String FLEA_I18N_CONFIG_KEY_FILE_NAME_PREFIX = "file_name_prefix";
	}
	
	/**
	 * 
	 * @Description IP地址常量
	 * 
	 * @author huazie
	 * @version v1.0.0
	 * @date 2017年3月2日
	 *
	 */
	interface IPAddressConstants{
		/**
		 * @Description 国家
		 */
		String COUNTRY = "country";
		/**
		 * @Description 地区
		 */
		String REGION = "region";
		/**
		 * @Description 省份
		 */
		String PROVINCE = "province";
		/**
		 * @Description 城市
		 */
		String CITY = "city";
		/**
		 * @Description 互联网服务提供商
		 */
		String ISP = "isp";
	}
}
