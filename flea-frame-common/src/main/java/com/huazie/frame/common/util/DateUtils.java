package com.huazie.frame.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huazie.frame.common.DateFormatEnum;

/**
 * <p>
 * 		日期工具类
 * </p>
 * 
 * @author huazie
 * @version v1.0.0
 * @date 2017年3月20日
 *
 */
public class DateUtils {

	private final static Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);
	
	/**
	 * <p>
	 * 获取当前系统时间
	 * 
	 * @date 2018年1月25日
	 *
	 * @return
	 * @throws Exception
	 */
	public static Date getCurrentTime(){
		return new Date(System.currentTimeMillis());
	}
	
	/**
	 * 
	 * <p>将日期转换成指定的字符串</p>
	 * 
	 * @version v1.0.0
	 * @date 2017年3月20日
	 *
	 * @param date
	 * @param datePattern
	 * @return
	 */
	public static String date2String(Date date, String datePattern) {
		String dateStr = null;
		if(date == null){
			date = DateUtils.getCurrentTime();
		}
		if (StringUtils.isEmpty(datePattern)) {
			datePattern = DateFormatEnum.DATA_FORMAT_DEFAULT;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
		try {
			dateStr = dateFormat.format(date);
		} catch (Exception e) {
			DateUtils.LOGGER.error("DateUtils##date2String 日期转换异常：", e);
		}
		return dateStr;
	}
	
	/**
	 * 
	 * <p>将日期转换成指定的字符串</p>
	 * 
	 * <pre>
	 *  Date date = DateUtils.string2Date("2017-03-20 10:20:21", DateFormat.YYYY_MM_DDHH_MM_SS);<br/>
	 *  String dateStr = DateUtils.date2String(date, DateFormat.YYYY_MM_DD);
	 * </pre>
	 * @version v1.0.0
	 * @date 2017年3月20日
	 *
	 * @param date
	 * @param datePattern
	 * @return
	 */
	public static String date2String(Date date, DateFormatEnum datePattern) {
		return date2String(date, datePattern.getFormat());
	}
	
	/**
	 * 
	 * <p>将日期字符串转换为日期对象</p>
	 * 
	 * @version v1.0.0
	 * @date 2017年3月20日
	 *
	 * @param dateStr
	 * @param datepattern
	 * @return
	 */
	public static Date string2Date(String dateStr, String datepattern) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(datepattern);
            return dateFormat.parse(dateStr);
        } catch (Exception e) {
        	DateUtils.LOGGER.error("DateUtils##string2Date 日期字符串转换异常：",e);
        }
        return null;
    }
	
	/**
	 * 
	 * <p>将日期字符串转换为日期对象<p>
	 * 
	 * @version v1.0.0
	 * @date 2017年3月20日
	 *
	 * @param dateStr
	 * @return
	 */
	public static Date string2Date(String dateStr) {
		return string2Date(dateStr, DateFormatEnum.DATA_FORMAT_DEFAULT);
    }
	
	/**
	 * 
	 * <p>将日期字符串转换为日期对象</p>
	 * 
	 * <pre>
	 *  Date date = DateUtils.string2Date("2016-02-25 10:20:21", DateFormat.YYYY_MM_DDHH_MM_SS);
	 * </pre>
	 * 
	 * @version v1.0.0
	 * @date 2017年3月20日
	 *
	 * @param dateStr
	 * @param datepattern
	 * @return
	 */
	public static Date string2Date(String dateStr, DateFormatEnum datepattern) {
		return string2Date(dateStr, datepattern.getFormat());
	}
	
}
