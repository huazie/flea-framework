package com.huazie.frame.tools;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.border.StandardBorderPainter;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * <p> 启动类 </p>
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

            FleaFrameTools tools = new FleaFrameTools();
            tools.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "系统不支持", "警告", JOptionPane.OK_OPTION);
        }
    }

}
