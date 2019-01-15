package com.huazie.frame.common;

import java.util.Locale;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huazie.frame.common.i18n.config.FleaI18nConfig;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.common.util.UnicodeUtils;

/**
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年7月30日
 *
 */
public class FleaI18NTest {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(FleaI18NTest.class);
	
	@Test
	public void fleaI18NTest(){
		FleaI18nConfig config = FleaI18nConfig.getConfig();
		try {
			config.getI18NData("CACHE00000001", "cache", Locale.FRANCE);
		} catch (Exception e) {
			LOGGER.error("Exception={}", e);
		}
	}
	
	@Test
	public void nativeToUnicodeTest(){
		try {
			System.out.println(UnicodeUtils.nativeToUnicode("你好，我是huazie"));
		} catch (Exception e) {
			LOGGER.error("Exception={}", e);
		}
	}
	
	@Test
	public void unicodeToNatvieTest(){
		try {
			System.out.println(UnicodeUtils.unicodeToNative("\\u68\\u75\\u61\\u7a\\u69\\u65"));
		} catch (Exception e) {
			LOGGER.error("Exception={}", e);
		}
	}
	
	@Test
	public void i18nTest(){
		String str = "hello, My name is {0}, I am from {1}";
		StringBuilder strBuilder = new StringBuilder(str);
		try {
			StringUtils.replace(strBuilder, "{" + 0 + "}", "huazie");
			StringUtils.replace(strBuilder, "{" + 1 + "}", "china");
			System.out.println(strBuilder.toString());
		} catch (Exception e) {
			LOGGER.error("Exception={}", e);
		}
	}
}
