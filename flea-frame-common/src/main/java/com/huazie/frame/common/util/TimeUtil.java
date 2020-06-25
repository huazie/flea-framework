package com.huazie.frame.common.util;

import java.sql.Timestamp;

/**
 * <p> 时间工具类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class TimeUtil {

    /**
     * <p> 获取系统当前时间戳 </p>
     *
     * @return 系统当前时间戳
     * @since 1.0.0
     */
    public static Timestamp getSystemCurrentTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * <p> 获取系统当前时间戳（long类型） </p>
     *
     * @return 系统当前时间戳（long类型）
     * @since 1.0.0
     */
    public static long getSystemCurrentTimeForLong() {
        return System.currentTimeMillis();
    }
}
