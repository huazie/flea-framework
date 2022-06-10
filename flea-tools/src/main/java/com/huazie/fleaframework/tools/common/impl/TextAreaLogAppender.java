package com.huazie.fleaframework.tools.common.impl;

import com.huazie.fleaframework.tools.common.LogAppender;

import javax.swing.*;
import java.awt.*;
import java.io.PipedReader;
import java.util.Scanner;

/**
 * <p> 文本域日志截取子类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class TextAreaLogAppender extends LogAppender {

    private JTextArea textArea;

    private JScrollPane scrollPane;

    /**
     * <p> 带参数构造方法 </p>
     *
     * @param textArea   文本域
     * @param scrollPane 滚动条
     */
    public TextAreaLogAppender(JTextArea textArea, JScrollPane scrollPane) {
        this.textArea = textArea;
        this.scrollPane = scrollPane;
    }

    @Override
    protected void append(PipedReader reader) {
        // 不间断地扫描输入流
        Scanner scanner = new Scanner(reader);
        // 将扫描到的字符流输出到指定的JTextArea组件
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            textArea.append(line);
            textArea.append("\n");
            //使垂直滚动条自动向下滚动
            scrollPane.getViewport().setViewPosition(getCurrentPoint(textArea));
        }
    }

    @Override
    protected void appendError(Exception ex) {
        textArea.append("\n");
        textArea.append(ex.getMessage());
        textArea.append("\n");
    }

    private Point getCurrentPoint(JTextArea textArea) {
        int height = 20;
        Point point = new Point();
        point.setLocation(0, textArea.getLineCount() * height);
        return point;
    }
}
