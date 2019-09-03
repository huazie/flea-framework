package com.huazie.frame.tools;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.border.StandardBorderPainter;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * <p></p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaFrameToolsTest {

    public static void main(String[] args){
//        FleaFrameManager.getManager().setLocale(Locale.US);
//        FleaI18NPanel main = new FleaI18NPanel();
//        main.setVisible(true);
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
