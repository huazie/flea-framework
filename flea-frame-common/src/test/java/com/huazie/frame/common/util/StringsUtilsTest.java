package com.huazie.frame.common.util;

import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月25日
 *
 */
public class StringsUtilsTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(StringsUtilsTest.class);
	
	@Test
	public void testStringBefore(){
		LOGGER.debug(StringUtils.subStrBefore("asda", 0));
		LOGGER.debug(StringUtils.subStrBefore("asda", -1));
		LOGGER.debug(StringUtils.subStrBefore("asda", 2));
		LOGGER.debug(StringUtils.subStrBefore("asda", 5));
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
}
