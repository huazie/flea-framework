package com.huazie.frame.tools;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * <p> Flea Tools </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaFrameTools extends JFrame implements ActionListener, ChangeListener, ItemListener {

    public FleaFrameTools() {
        super("Flea Tools");
        init();
    }

    private void init() {
        setBounds(200, 20, (int) (620 / 0.618), 620);

        Dimension screen = getToolkit().getScreenSize(); // 得到屏幕尺寸
        setLocation((screen.width - getSize().width) / 2, (screen.height - getSize().height) / 2); // 设置窗口位置

        // 修改左上角图标
        setIconImage(Toolkit.getDefaultToolkit().createImage(FleaFrameTools.class.getClassLoader().getResource("flea/image/fleaframetools.png")));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
