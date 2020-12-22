package com.huazie.frame.common.slf4j;

import org.slf4j.Logger;

/**
 * <p> Flea Logger 自定义接口 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface FleaLogger extends Logger {

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
