package com.huazie.fleaframework.common.slf4j.impl;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

/**
 * Flea 日志本地实现类，实现Flea日志接口，用于自定义日志打印输出格式。
 *
 * <p> 成员变量slf4j日志对象【{@code logger}】，由构造器初始化，仍然
 * 从slf4j的日志工厂类获取；拓展了slf4j日志对象的方法，可查看这些方法
 * 【{@code trace1}】、【{@code debug1}】、【{@code info1}】、
 * 【{@code warn1}】和【{@code error1}】。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaLocalLogger implements FleaLogger {

    private Logger logger;

    private FleaLocalLogger(Class<?> loggerClass) {
        logger = LoggerFactory.getLogger(loggerClass);
    }

    @Override
    public void trace1(Object obj, String msg) {
        trace(msg);
    }

    @Override
    public void trace1(Object obj, String format, Object arg) {
        trace(format, arg);
    }

    @Override
    public void trace1(Object obj, String format, Object arg1, Object arg2) {
        trace(format, arg1, arg2);
    }

    @Override
    public void trace1(Object obj, String format, Object... arguments) {
        trace(format, arguments);
    }

    @Override
    public void trace1(Object obj, String msg, Throwable t) {
        trace(msg, t);
    }

    @Override
    public void debug1(Object obj, String msg) {
        debug(msg);
    }

    @Override
    public void debug1(Object obj, String format, Object arg) {
        debug(format, arg);
    }

    @Override
    public void debug1(Object obj, String format, Object arg1, Object arg2) {
        debug(format, arg1, arg2);
    }

    @Override
    public void debug1(Object obj, String format, Object... arguments) {
        debug(format, arguments);
    }

    @Override
    public void debug1(Object obj, String msg, Throwable t) {
        debug(msg, t);
    }

    @Override
    public void info1(Object obj, String msg) {
        info(msg);
    }

    @Override
    public void info1(Object obj, String format, Object arg) {
        info(format, arg);
    }

    @Override
    public void info1(Object obj, String format, Object arg1, Object arg2) {
        info(format, arg1, arg2);
    }

    @Override
    public void info1(Object obj, String format, Object... arguments) {
        info(format, arguments);
    }

    @Override
    public void info1(Object obj, String msg, Throwable t) {
        info(msg, t);
    }

    @Override
    public void warn1(Object obj, String msg) {
        warn(msg);
    }

    @Override
    public void warn1(Object obj, String format, Object arg) {
        warn(format, arg);
    }

    @Override
    public void warn1(Object obj, String format, Object arg1, Object arg2) {
        warn(format, arg1, arg2);
    }

    @Override
    public void warn1(Object obj, String format, Object... arguments) {
        warn(format, arguments);
    }

    @Override
    public void warn1(Object obj, String msg, Throwable t) {
        warn(msg, t);
    }

    @Override
    public void error1(Object obj, String msg) {
        error(msg);
    }

    @Override
    public void error1(Object obj, String format, Object arg) {
        error(format, arg);
    }

    @Override
    public void error1(Object obj, String format, Object arg1, Object arg2) {
        error(format, arg1, arg2);
    }

    @Override
    public void error1(Object obj, String format, Object... arguments) {
        error(format, arguments);
    }

    @Override
    public void error1(Object obj, String msg, Throwable t) {
        error(msg, t);
    }

    @Override
    public String getName() {
        return logger.getName();
    }

    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    @Override
    public void trace(String msg) {
        logger.trace(msg);
    }

    @Override
    public void trace(String format, Object arg) {
        logger.trace(format, arg);
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        logger.trace(format, arg1, arg2);
    }

    @Override
    public void trace(String format, Object... arguments) {
        logger.trace(format, arguments);
    }

    @Override
    public void trace(String msg, Throwable t) {
        logger.trace(msg, t);
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return logger.isTraceEnabled(marker);
    }

    @Override
    public void trace(Marker marker, String msg) {
        logger.trace(marker, msg);
    }

    @Override
    public void trace(Marker marker, String format, Object arg) {
        logger.trace(marker, format, arg);
    }

    @Override
    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        logger.trace(marker, format, arg1, arg2);
    }

    @Override
    public void trace(Marker marker, String format, Object... arguments) {
        logger.trace(marker, format, arguments);
    }

    @Override
    public void trace(Marker marker, String msg, Throwable t) {
        logger.trace(marker, msg, t);
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    public void debug(String msg) {
        logger.debug(msg);
    }

    @Override
    public void debug(String format, Object arg) {
        logger.debug(format, arg);
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        logger.debug(format, arg1, arg2);
    }

    @Override
    public void debug(String format, Object... arguments) {
        logger.debug(format, arguments);
    }

    @Override
    public void debug(String msg, Throwable t) {
        logger.debug(msg, t);
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return logger.isDebugEnabled(marker);
    }

    @Override
    public void debug(Marker marker, String msg) {
        logger.debug(marker, msg);
    }

    @Override
    public void debug(Marker marker, String format, Object arg) {
        logger.debug(marker, format, arg);
    }

    @Override
    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        logger.debug(marker, format, arg1, arg2);
    }

    @Override
    public void debug(Marker marker, String format, Object... arguments) {
        logger.debug(marker, format, arguments);
    }

    @Override
    public void debug(Marker marker, String msg, Throwable t) {
        logger.debug(marker, msg, t);
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    @Override
    public void info(String msg) {
        logger.info(msg);
    }

    @Override
    public void info(String format, Object arg) {
        logger.info(format, arg);
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        logger.info(format, arg1, arg2);
    }

    @Override
    public void info(String format, Object... arguments) {
        logger.info(format, arguments);
    }

    @Override
    public void info(String msg, Throwable t) {
        logger.info(msg, t);
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return logger.isInfoEnabled(marker);
    }

    @Override
    public void info(Marker marker, String msg) {
        logger.info(marker, msg);
    }

    @Override
    public void info(Marker marker, String format, Object arg) {
        logger.info(marker, format, arg);
    }

    @Override
    public void info(Marker marker, String format, Object arg1, Object arg2) {
        logger.info(marker, format, arg1, arg2);
    }

    @Override
    public void info(Marker marker, String format, Object... arguments) {
        logger.info(marker, format, arguments);
    }

    @Override
    public void info(Marker marker, String msg, Throwable t) {
        logger.info(marker, msg, t);
    }

    @Override
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    @Override
    public void warn(String msg) {
        logger.warn(msg);
    }

    @Override
    public void warn(String format, Object arg) {
        logger.warn(format, arg);
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        logger.warn(format, arg1, arg2);
    }

    @Override
    public void warn(String format, Object... arguments) {
        logger.warn(format, arguments);
    }

    @Override
    public void warn(String msg, Throwable t) {
        logger.warn(msg, t);
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return logger.isWarnEnabled(marker);
    }

    @Override
    public void warn(Marker marker, String msg) {
        logger.warn(marker, msg);
    }

    @Override
    public void warn(Marker marker, String format, Object arg) {
        logger.warn(marker, format, arg);
    }

    @Override
    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        logger.warn(marker, format, arg1, arg2);
    }

    @Override
    public void warn(Marker marker, String format, Object... arguments) {
        logger.warn(marker, format, arguments);
    }

    @Override
    public void warn(Marker marker, String msg, Throwable t) {
        logger.warn(marker, msg, t);
    }

    @Override
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    @Override
    public void error(String msg) {
        logger.error(msg);
    }

    @Override
    public void error(String format, Object arg) {
        logger.error(format, arg);
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        logger.error(format, arg1, arg2);
    }

    @Override
    public void error(String format, Object... arguments) {
        logger.error(format, arguments);
    }

    @Override
    public void error(String msg, Throwable t) {
        logger.error(msg, t);
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return logger.isErrorEnabled(marker);
    }

    @Override
    public void error(Marker marker, String msg) {
        logger.error(marker, msg);
    }

    @Override
    public void error(Marker marker, String format, Object arg) {
        logger.error(marker, format, arg);
    }

    @Override
    public void error(Marker marker, String format, Object arg1, Object arg2) {
        logger.error(marker, format, arg1, arg2);
    }

    @Override
    public void error(Marker marker, String format, Object... arguments) {
        logger.error(marker, format, arguments);
    }

    @Override
    public void error(Marker marker, String msg, Throwable t) {
        logger.error(marker, msg, t);
    }

    /**
     * <p> 内部建造者类 </p>
     *
     * @author huazie
     * @since 1.0.0
     */
    public static class Builder {

        private Class<?> loggerClass; // 日志打印类

        /**
         * <p> 带参数的构造器 </p>
         *
         * @param loggerClass 日志打印类
         * @since 1.0.0
         */
        Builder(Class<?> loggerClass) {
            this.loggerClass = loggerClass;
        }

        /**
         * <p> 构建Flea Logger本地实现类 </p>
         *
         * @return Redis客户端
         * @since 1.0.0
         */
        public FleaLocalLogger build() {
            return new FleaLocalLogger(loggerClass);
        }
    }
}
