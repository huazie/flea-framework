package com.huazie.fleaframework.common.slf4j;

import org.slf4j.Logger;

/**
 * Flea 日志接口，继承slf4j日志接口的所有方法，同时
 * 拓展了其他用于自定义打印输出的其他方法。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface FleaLogger extends Logger {

    String MDC_KEY_MN = "METHOD_NAME"; // 方法名

    String MDC_KEY_MPN = "METHOD_PARAM_NAME"; // 方法参数名

    String MDC_KEY_CFN = "CLASS_FULL_NAME"; // 类全名

    String MDC_KEY_LN = "LINE_NUMBER"; // 代码行数

    String MDC_KEY_FN = "FILE_NAME"; // 源文件名

    String MDC_KEY_LOC = "LOCATION"; // 输出日志事件的发生位置

    void trace1(Object obj, String msg);

    void trace1(Object obj, String format, Object arg);

    void trace1(Object obj, String format, Object arg1, Object arg2);

    void trace1(Object obj, String format, Object... arguments);

    void trace1(Object obj, String msg, Throwable t);

    void debug1(Object obj, String msg);

    void debug1(Object obj, String format, Object arg);

    void debug1(Object obj, String format, Object arg1, Object arg2);

    void debug1(Object obj, String format, Object... arguments);

    void debug1(Object obj, String msg, Throwable t);

    void info1(Object obj, String msg);

    void info1(Object obj, String format, Object arg);

    void info1(Object obj, String format, Object arg1, Object arg2);

    void info1(Object obj, String format, Object... arguments);

    void info1(Object obj, String msg, Throwable t);

    void warn1(Object obj, String msg);

    void warn1(Object obj, String format, Object arg);

    void warn1(Object obj, String format, Object arg1, Object arg2);

    void warn1(Object obj, String format, Object... arguments);

    void warn1(Object obj, String msg, Throwable t);

    void error1(Object obj, String msg);

    void error1(Object obj, String format, Object arg);

    void error1(Object obj, String format, Object arg1, Object arg2);

    void error1(Object obj, String format, Object... arguments);

    void error1(Object obj, String msg, Throwable t);
}
