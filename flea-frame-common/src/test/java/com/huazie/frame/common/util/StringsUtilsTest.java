package com.huazie.frame.common.util;

import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;

/**
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月25日
 *
 */
public class StringsUtilsTest {

	private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(StringsUtilsTest.class);
	
	@Test
	public void testStringBefore(){
		LOGGER.debug(StringUtils.subStrBefore("asda", 0));
		LOGGER.debug(StringUtils.subStrBefore("asda", -1));
		LOGGER.debug(StringUtils.subStrBefore("asda", 2));
		LOGGER.debug(StringUtils.subStrBefore("asda", 5));
		String server = "127.0.0.1:10001";
		String host = StringUtils.subStrBefore(server, server.indexOf(CommonConstants.SymbolConstants.COLON));
		String port = StringUtils.subStrLast(server, server.length() - server.indexOf(CommonConstants.SymbolConstants.COLON) - 1);
		LOGGER.debug("HOST={}", host);
		LOGGER.debug("PORT={}", port);
	}
	
	@Test
	public void testStringLast(){
		LOGGER.debug(StringUtils.subStrLast("asda", 0));
		LOGGER.debug(StringUtils.subStrLast("asda", -1));
		LOGGER.debug(StringUtils.subStrLast("asda", 2));
		LOGGER.debug(StringUtils.subStrLast("asda", 5));
	}
	
	@Test
	public void testStringCat(){
		LOGGER.debug(StringUtils.strCat("flea_file_info", "_" , "ff"));
	}
	
	@Test
	public void testReplace(){
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("INSERT INTO {##table##} ( {##columns##} ) VALUES( {##values##} )");
		LOGGER.debug(strBuilder.toString());
		String placeholder = "{##table##}";
		String str = "flea_file_info_ff";
		try {
			StringUtils.replace(strBuilder, placeholder, str);
		} catch (Exception e) {
			LOGGER.error("Exception={}", e);
		}
		LOGGER.debug(strBuilder.toString());
	}
	
	@Test
	public void testSplit(){
		String[] strs = StringUtils.split(" para_id = :paraId, para_type = :paraType", ",");
		if(!ArrayUtils.isEmpty(strs)){
			for(String str : strs){
				LOGGER.debug("Str={}", str);
			}
		}else{
			LOGGER.debug("null");
		}
	}

	@Test
	public void testToLowerCaseInitial() {
		String aa = "a";
		LOGGER.debug("aa = {}", StringUtils.toLowerCaseInitial(aa));
		String bb = "A";
		LOGGER.debug("bb = {}", StringUtils.toLowerCaseInitial(bb));
		String cc = "AbCD";
		LOGGER.debug("cc = {}", StringUtils.toLowerCaseInitial(cc));
	}

	@Test
	public void testToUpperCaseInitial() {
		String aa = "a";
		LOGGER.debug("aa = {}", StringUtils.toUpperCaseInitial(aa));
		String bb = "A";
		LOGGER.debug("bb = {}", StringUtils.toUpperCaseInitial(bb));
		String cc = "abCD";
		LOGGER.debug("cc = {}", StringUtils.toUpperCaseInitial(cc));
	}

	@Test
	public void testToUpperCaseFirstAndLowerCaseLeft() {
		String aa = "a";
		LOGGER.debug("aa = {}", StringUtils.toUpperCaseFirstAndLowerCaseLeft(aa));
		String bb = "A";
		LOGGER.debug("bb = {}", StringUtils.toUpperCaseFirstAndLowerCaseLeft(bb));
		String cc = "abCD";
		LOGGER.debug("cc = {}", StringUtils.toUpperCaseFirstAndLowerCaseLeft(cc));
	}
}
