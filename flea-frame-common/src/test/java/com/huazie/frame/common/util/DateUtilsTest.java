package com.huazie.frame.common.util;

import com.huazie.frame.common.DateFormatEnum;
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
public class DateUtilsTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(DateUtilsTest.class);
	
	@Test
	public void testDate(){
		LOGGER.debug(DateUtils.date2String(null, DateFormatEnum.YYYY_MM_DDHH_MM_SS));
	}
}
