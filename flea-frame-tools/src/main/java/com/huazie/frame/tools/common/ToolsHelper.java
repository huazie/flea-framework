package com.huazie.frame.tools.common;

import javax.swing.JOptionPane;

/**
 * <p> 工具类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class ToolsHelper {

    /**
     * <p> 展示提示消息 </p>
     *
     * @param title       标题
     * @param msg         消息
     * @param messageType 消息类型
     * @since 1.0.0
     */
    public static void showTips(String title, String msg, int messageType) {
        JOptionPane.showMessageDialog(null, msg, title, messageType);
    }
}
