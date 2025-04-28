package com.huazie.fleaframework.tools;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.border.StandardBorderPainter;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * Flea Tools 启动类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class StartMain {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new SubstanceLookAndFeel());
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            SubstanceLookAndFeel.setCurrentBorderPainter(new StandardBorderPainter());

            FleaTools tools = new FleaTools();
            tools.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "系统不支持", "警告", JOptionPane.ERROR_MESSAGE);
        }
    }

}
