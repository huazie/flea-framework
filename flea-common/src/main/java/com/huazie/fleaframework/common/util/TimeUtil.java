package com.huazie.fleaframework.common.util;

import java.sql.Timestamp;

/**
 * 时间工具类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class TimeUtil {

    private TimeUtil() {
    }

    /**
     * 获取系统当前时间戳
     *
     * @return 系统当前时间戳
     * @since 1.0.0
     */
    public static Timestamp getSystemCurrentTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 获取系统当前时间戳（long类型）
     *
     * @return 系统当前时间戳（long类型）
     * @since 1.0.0
     */
    public static long getSystemCurrentTimeForLong() {
        return System.currentTimeMillis();
    }
}
