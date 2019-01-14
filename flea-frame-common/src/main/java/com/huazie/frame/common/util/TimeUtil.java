package com.huazie.frame.common.util;

import java.sql.Timestamp;

/**
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月25日
 *
 */
public class TimeUtil {
	
	public Timestamp getSystemTime()throws Exception{
		return new Timestamp(System.currentTimeMillis());
	}
}
