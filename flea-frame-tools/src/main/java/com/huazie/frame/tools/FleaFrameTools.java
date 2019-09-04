package com.huazie.frame.tools;

import com.huazie.frame.common.i18n.FleaI18nHelper;
import com.huazie.frame.tools.code.FleaCodePanel;
import com.huazie.frame.tools.i18n.FleaI18NPanel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.BorderLayout;
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

    // 菜单栏
    private JMenuBar mBar = new JMenuBar();

    private JMenu mSystemMenu = new JMenu(FleaI18nHelper.i18nForCommon("COMMON_TOOLS_00000"));
    private JMenuItem mExitMenuItem = new JMenuItem(FleaI18nHelper.i18nForCommon("COMMON_TOOLS_00001"));

    private JMenu mHelpMenu = new JMenu(FleaI18nHelper.i18nForCommon("COMMON_TOOLS_00002"));
    private JMenuItem mAboutMenuItem = new JMenuItem(FleaI18nHelper.i18nForCommon("COMMON_TOOLS_00003"));

    // 工具栏
    private JToolBar mToolbar = new JToolBar();

    private JComboBox<String> mComboBox = new JComboBox<String>();

    private JButton mTabbedPanePreBtn = new JButton(new ImageIcon(FleaFrameTools.class.getClassLoader().getResource("flea/image/tabbedPane_pre.png")));
    private JButton mTabbedPaneNextBtn = new JButton(new ImageIcon(FleaFrameTools.class.getClassLoader().getResource("flea/image/tabbedPane_next.png")));

    private JTabbedPane mTabbedPane = new JTabbedPane(SwingConstants.LEFT);// 选项卡

    private FleaI18NPanel mFleaI18NPanel;

    private FleaCodePanel mFleaCodePanel;

    private JScrollPane mJScrollPane;// 滚动条

    public FleaFrameTools() {
        super("Flea Frame Tools");
        init();
    }

    private void init() {
        setBounds(200, 20, (int) (520 / 0.618), 520);

        Dimension screen = getToolkit().getScreenSize(); // 得到屏幕尺寸
        setLocation((screen.width - getSize().width) / 2, (screen.height - getSize().height) / 2); // 设置窗口位置

        // 修改左上角图标
        setIconImage(Toolkit.getDefaultToolkit().createImage(FleaFrameTools.class.getClassLoader().getResource("flea/image/fleaframetools.png")));

        mExitMenuItem.addActionListener(this);
        mSystemMenu.add(mExitMenuItem);

        mAboutMenuItem.addActionListener(this);
        mHelpMenu.add(mAboutMenuItem);

        mBar.add(mSystemMenu); // 系统菜单栏
        mBar.add(mHelpMenu); // 帮助菜单栏

        // 添加菜单栏
        setJMenuBar(mBar);

        // 设置布局
        setLayout(new BorderLayout());

        // 工具栏按钮响应事件
        mTabbedPanePreBtn.addActionListener(this);
        mTabbedPaneNextBtn.addActionListener(this);

        mComboBox.addItem("TOP");
        mComboBox.addItem("LEFT");
        mComboBox.addItem("RIGHT");
        mComboBox.addItem("BOTTOM");
        mComboBox.addItemListener(this);

        setComboValue();

        mToolbar.add(mTabbedPanePreBtn);
        mToolbar.add(new JLabel("  "));
        mToolbar.add(mTabbedPaneNextBtn);
        mToolbar.add(new JLabel("  "));
        mToolbar.addSeparator();
        mToolbar.add(new JLabel("  "));

        Dimension dimension = mComboBox.getPreferredSize();
        mComboBox.setMaximumSize(dimension);
        mToolbar.add(mComboBox); // 根据combox自身的大小设置
        mToolbar.add(new JLabel("  "));
        mToolbar.addSeparator();

        mFleaI18NPanel = new FleaI18NPanel();
        mFleaCodePanel = new FleaCodePanel();
        mJScrollPane = new JScrollPane(mFleaCodePanel);
        mFleaCodePanel.setPreferredSize(new Dimension(getWidth() - 200, getHeight()));

        mTabbedPane.add(mFleaI18NPanel, FleaI18nHelper.i18nForCommon("COMMON_I18N_00000"));
        mTabbedPane.add(mJScrollPane, FleaI18nHelper.i18nForCommon("COMMON_CODE_00000"));
        mTabbedPane.addChangeListener(this);

        add(mTabbedPane);
        // 添加工具栏
        add(mToolbar, BorderLayout.NORTH);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mExitMenuItem) {
            System.exit(0);
        } else if (e.getSource() == mAboutMenuItem) {
            JOptionPane.showMessageDialog(this, "Frame框架工具集\n\n版本号:   1.0.0\n\n", "关于", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == mTabbedPanePreBtn) {
            int i = mTabbedPane.getSelectedIndex();
            if (i == 0) {
                mTabbedPane
                        .setSelectedIndex(mTabbedPane.getComponentCount() - 1);
            } else {
                mTabbedPane.setSelectedIndex(i - 1);
            }
        } else if (e.getSource() == mTabbedPaneNextBtn) {
            int i = mTabbedPane.getSelectedIndex();
            if (i == mTabbedPane.getComponentCount() - 1) {
                mTabbedPane.setSelectedIndex(0);
            } else {
                mTabbedPane.setSelectedIndex(i + 1);
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JComboBox<String> cb = (JComboBox<String>) e.getSource();
        int state = e.getStateChange();

        if (state == ItemEvent.SELECTED) {
            String s = (String) cb.getSelectedItem();

            if (s.equals("TOP"))
                mTabbedPane.setTabPlacement(JTabbedPane.TOP);
            else if (s.equals("LEFT"))
                mTabbedPane.setTabPlacement(JTabbedPane.LEFT);
            else if (s.equals("RIGHT"))
                mTabbedPane.setTabPlacement(JTabbedPane.RIGHT);
            else if (s.equals("BOTTOM"))
                mTabbedPane.setTabPlacement(JTabbedPane.BOTTOM);

            mTabbedPane.validate();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }

    /**
     * 根据tabbedPane选项卡标签位置的设置组合框的值
     */
    private void setComboValue() {
        int placement = mTabbedPane.getTabPlacement();
        String selectedItem = null;

        switch (placement) {
            case JTabbedPane.TOP:
                selectedItem = "TOP";
                break;
            case JTabbedPane.LEFT:
                selectedItem = "LEFT";
                break;
            case JTabbedPane.RIGHT:
                selectedItem = "RIGHT";
                break;
            case JTabbedPane.BOTTOM:
                selectedItem = "BOTTOM";
                break;
        }
        mComboBox.setSelectedItem(selectedItem);
    }
}
