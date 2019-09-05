package com.huazie.frame.tools.common;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.WriterAppender;

import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.Writer;

/**
 * <p> log4j日志截取父类线程 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class LogAppender extends Thread {

    @Override
    public void run() {
        try (// 定义一个未连接的输入流管道
             PipedReader reader = new PipedReader();
             // 定义一个已连接的输出流管理，并连接到reader
             Writer writer = new PipedWriter(reader)) {
            Logger root = Logger.getRootLogger();
            // 获取子记录器的输出源
            Appender appender = root.getAppender("myConsole");
            // 设置 appender 输出流
            ((WriterAppender) appender).setWriter(writer);
            append(reader);
        } catch (Exception ex) {
            appendError(ex);
        }
    }

    /**
     * 截取日志添加到GUI控件中，子类实现
     *
     * @param reader Piped character-input streams
     * @since 1.0.0
     */
    protected abstract void append(PipedReader reader);

    /**
     * 截取异常信息添加到GUI控件中，子类实现
     *
     * @param ex 异常信息
     * @since 1.0.0
     */
    protected abstract void appendError(Exception ex);

}
