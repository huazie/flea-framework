package com.huazie.frame.common.log.impl;

import com.huazie.frame.common.log.LogAppender;

import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

/**
 * <p>  </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class TextAreaLogAppender extends LogAppender {

    private JTextArea textArea;

    private JScrollPane scrollPane;

    public TextAreaLogAppender(JTextArea textArea, JScrollPane scrollPane) throws IOException {
        super("JTextArea");
        this.textArea = textArea;
        this.scrollPane = scrollPane;
    }

    @Override
    public void run() {
        // 不间断地扫描输入流
        Scanner scanner = new Scanner(reader);
        // 将扫描到的字符流输出到指定的JTextArea组件
        while (scanner.hasNextLine()) {
            try {
                String line = scanner.nextLine();
                textArea.append(line);
                textArea.append("\n");
                //使垂直滚动条自动向下滚动
                scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
            } catch (Exception ex) {
                //异常不做处理
            }
        }

    }
}
