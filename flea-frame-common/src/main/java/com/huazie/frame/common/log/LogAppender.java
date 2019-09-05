package com.huazie.frame.common.log;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.WriterAppender;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.Writer;

/**
 * <p>  </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class LogAppender extends Thread {

    protected PipedReader reader;

    public LogAppender(String appenderName) throws IOException {
        Logger root = Logger.getRootLogger();
        // 获取子记录器的输出源
        Appender appender = root.getAppender(appenderName);
        // 定义一个未连接的输入流管道
        reader = new PipedReader();
        // 定义一个已连接的输出流管理，并连接到reader
        Writer writer = new PipedWriter(reader);
        // 设置 appender 输出流
        ((WriterAppender) appender).setWriter(writer);
    }

}
