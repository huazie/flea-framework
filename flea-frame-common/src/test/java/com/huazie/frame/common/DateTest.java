package com.huazie.frame.common;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huazie.frame.common.util.DateUtils;

/**
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月25日
 *
 */
public class DateTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(DateTest.class);
	
	@Test
	public void testDate(){
		DateTest.LOGGER.debug(DateUtils.date2String(null, DateFormatEnum.YYYY_MM_DDHH_MM_SS));
	}
}
