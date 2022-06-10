package com.huazie.fleaframework.tools.log;

import com.huazie.fleaframework.tools.common.impl.TextAreaLogAppender;

import javax.swing.*;
import java.awt.*;

/**
 * <p> 日志面板 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaLoggerPanel extends JPanel {

    private JTextArea logTextArea; // 日志区域

    public FleaLoggerPanel(LayoutManager layout) {
        super(layout);
        init();
    }

    private void init() {
        logTextArea = new JTextArea();
        logTextArea.setFont(new Font("宋体", Font.PLAIN, 14));
        logTextArea.setSelectedTextColor(Color.RED);// 设置选中文本的颜色
        logTextArea.setLineWrap(true);// 设置自动换行
        logTextArea.setWrapStyleWord(true);// 设置换行不断字
        logTextArea.setEditable(false);
        logTextArea.setBackground(new Color(230, 230, 230));

        JScrollPane jScrollPane = new JScrollPane(logTextArea);
        add(jScrollPane);
        try {
            Thread textAreaLogAppender = new TextAreaLogAppender(logTextArea, jScrollPane);
            textAreaLogAppender.start();
        } catch (Exception e) {
            // 日志启动记录异常
        }
    }
}
