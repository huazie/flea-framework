package com.huazie.frame.common.util;

import com.huazie.frame.common.DateFormatEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>日期工具类</p>
 *
 * @author huazie
 * @version v1.0.0
 * @since 1.0.0
 */
public class DateUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

    /**
     * <p>获取当前系统时间</p>
     *
     * @return 当前系统时间
     */
    public static Date getCurrentTime() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * <p>将日期转换成指定格式的字符串</p>
     * <p>当<code>date</code>为<code>null</code>,默认格式化当前系统时间</p>
     * <p>当<code>dateFormatStr</code>为空字符串或null，默认格式化类型<code>DateFormatEnum.DATE_FORMAT_DEFAULT</code></p>
     *
     * @param date          日期对象
     * @param dateFormatStr 日期格式化类型字符串
     * @return 格式化后的日期字符串
     * @since 1.0.0
     */
    public static String date2String(Date date, String dateFormatStr) {
        String dateStr = null;
        if (date == null) {
            date = getCurrentTime();// 默认取当前系统时间
        }
        if (StringUtils.isBlank(dateFormatStr)) {
            dateFormatStr = DateFormatEnum.DATE_FORMAT_DEFAULT; // 默认的日期格式化类型
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
        try {
            dateStr = dateFormat.format(date);
        } catch (Exception e) {
            LOGGER.error("DateUtils##date2String 日期转换异常：", e);
        }
        return dateStr;
    }

    /**
     * <p>将日期转换成指定格式的字符串</p>
     * <p>当<code>date</code>为<code>null</code>,默认格式化当前系统时间</p>
     * <p>当<code>dateFormatEnum</code>为<code>null</code>，默认日期格式化类型<code>DateFormatEnum.getDefaultEnum()</p>
     *
     * @param date           日期对象
     * @param dateFormatEnum 日期格式化类型
     * @return 格式化后的日期字符串
     * @since 1.0.0
     */
    public static String date2String(Date date, DateFormatEnum dateFormatEnum) {
        if(dateFormatEnum == null){
            dateFormatEnum = DateFormatEnum.getDefaultEnum(); // 默认的日期格式化类型
        }
        return date2String(date, dateFormatEnum.getFormat());
    }

    /**
     * <p>将日期转换成指定格式的字符串</p>
     * <p>当<code>date</code>为<code>null</code>,默认格式化当前系统时间</p>
     *
     * @param date           日期对象
     * @return 格式化后的日期字符串
     * @since 1.0.0
     */
    public static String date2String(Date date) {
        return date2String(date, DateFormatEnum.DATE_FORMAT_DEFAULT);
    }

    /**
     * <p>将日期字符串转换为日期对象</p>
     * <p>当<code>dateFormatStr</code>为空字符串或null，默认日期格式化类型<code>DateFormatEnum.DATE_FORMAT_DEFAULT</code></p>
     *
     * @param dateStr       日期字符串
     * @param dateFormatStr 日期格式化类型字符串
     * @return 转换后的日期对象
     * @since 1.0.0
     */
    public static Date string2Date(String dateStr, String dateFormatStr) {
        try {
            if (StringUtils.isBlank(dateFormatStr)) {
                dateFormatStr = DateFormatEnum.DATE_FORMAT_DEFAULT; //默认的日期格式化类型
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
            return dateFormat.parse(dateStr);
        } catch (Exception e) {
            LOGGER.error("DateUtils##string2Date 日期字符串转换异常：", e);
        }
        return null;
    }

    /**
     * <p>将日期字符串转换为日期对象</p>
     * <p>当<code>dateFormatEnum</code>为<code>null</code>，默认日期格式化类型<code>DateFormatEnum.getDefaultEnum()</code></p>
     *
     * @param dateStr        日期对象
     * @param dateFormatEnum 日期格式化类型
     * @return 转换后的日期对象
     * @since 1.0.0
     */
    public static Date string2Date(String dateStr, DateFormatEnum dateFormatEnum) {
        if(dateFormatEnum == null){
            dateFormatEnum = DateFormatEnum.getDefaultEnum(); //默认的日期格式化类型
        }
        return string2Date(dateStr, dateFormatEnum.getFormat());
    }

    /**
     * <p>将日期字符串转换为日期对象<p>
     *
     * @param dateStr 日期字符串
     * @return 转换后的日期对象
     * @since 1.0.0
     */
    public static Date string2Date(String dateStr) {
        return string2Date(dateStr, DateFormatEnum.DATE_FORMAT_DEFAULT);
    }


}
