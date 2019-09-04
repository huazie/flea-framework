package com.huazie.frame.tools.i18n;

import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.i18n.FleaI18nHelper;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.common.util.UnicodeUtils;
import com.huazie.frame.tools.common.ToolsHelper;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * I18N 图形化转化类，将Unicode字符和本地环境相互转化
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("serial")
public class FleaI18NPanel extends JPanel {

    private JTabbedPane mTabbedPane = new JTabbedPane(SwingConstants.TOP);// 选项卡

    private JPanel nativeToUnicodeFilePanel;    // 文件本地转Unicode
    private JPanel unicodeToNativeFilePanel;    // 文件Unicode转本地
    private JPanel nativeToUnicodeStringPanel;  // 字符本地转Unicode
    private JPanel unicodeToNativeStringPanel;  // 字符Unicode转本地
    private JPanel operatePanel;                // 操作按钮面板

    private JTextField nativeFileText;          // 本地文件输入框
    private JButton nativeFileSelectBtn;        // 本地文件选择按钮
    private JTextArea unicodeFilePathArea;      // Unicode生成文件路径

    private JTextField unicodeFileText;         // Unicode文件输入框
    private JButton unicodeFileSelectBtn;       // Unicode文件选择按钮
    private JTextArea nativeFilePathArea;       // 本地生成文件路径

    private JTextField nativeStringText;        // 本地字符串输入框
    private JTextArea unicodeStringArea;        // Unicode文本域

    private JTextField unicodeStringText;       // Unicode字符串输入框
    private JTextArea nativeStringArea;         // 本地文本域

    private JFileChooser fileChooser;           // 文件选择

    private JButton open;       // 打开生成文件目录
    private JButton convert;    // 确定按钮
    private JButton reset;      // 重置按钮

    private int currTab = 1;    // 当前TAB页

    private static String EXE_PATH_DEFAULT = "E:\\Software\\Java\\bin\\jdk1.7.0\\bin\\native2ascii.exe";

    private int initWidhtLen = 520;

    private int initHeightLen = (int) (initWidhtLen * 0.618);

    public FleaI18NPanel() {
        init();
    }

    public void init() {

        setLayout(new GridLayout(2, 1));

        initFileNativeToUnicode();    // 初始化本地文件转Unicode文件页面

        initFileUnicodeToNative();    // 初始化Unicode文件转本地文件页面

        initStringNativeToUnicode();// 初始化本地字符串转Unicode字符串页面

        initStringUnicodeToNative();// 初始化Unicode字符串转本地字符串页面

        mTabbedPane.add(nativeToUnicodeFilePanel, FleaI18nHelper.i18nForCommon("COMMON_I18N_00001"));
        mTabbedPane.add(unicodeToNativeFilePanel, FleaI18nHelper.i18nForCommon("COMMON_I18N_00002"));
        mTabbedPane.add(nativeToUnicodeStringPanel, FleaI18nHelper.i18nForCommon("COMMON_I18N_00003"));
        mTabbedPane.add(unicodeToNativeStringPanel, FleaI18nHelper.i18nForCommon("COMMON_I18N_00004"));

        mTabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent paramChangeEvent) {
                int selectedIndex = mTabbedPane.getSelectedIndex();
                currTab = selectedIndex + 1;
                if (currTab == 1 || currTab == 2) {
                    open.setVisible(true);
                } else if (currTab == 3 || currTab == 4) {
                    open.setVisible(false);
                }
            }
        });

        add(mTabbedPane);

        operatePanel = new JPanel();

        open = new JButton(FleaI18nHelper.i18nForCommon("COMMON_I18N_00005"));
        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    open();
                } catch (Exception e1) {
                }
            }
        });
        operatePanel.add(open);

        convert = new JButton(FleaI18nHelper.i18nForCommon("COMMON_I18N_00006"));
        convert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (currTab == 1) {
                        fileNativeToUnicode();// 本地文件转Unicode文件
                    } else if (currTab == 2) {
                        fileUnicodeToNative();// Unicode文件转本地文件
                    } else if (currTab == 3) {
                        stringNativeToUnicode();// 本地字符串转Unicode字符串
                    } else if (currTab == 4) {
                        stringUnicodeToNative();// Unicode字符串转本地字符串
                    }
                } catch (Exception e1) {
                }
            }
        });
        operatePanel.add(convert);


        reset = new JButton(FleaI18nHelper.i18nForCommon("COMMON_I18N_00007"));
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currTab == 1) {
                    nativeFileText.setText("");
                } else if (currTab == 2) {
                    unicodeFileText.setText("");
                } else if (currTab == 3) {
                    nativeStringText.setText("");
                    unicodeStringArea.setText("");
                } else if (currTab == 4) {
                    unicodeStringText.setText("");
                    nativeStringArea.setText("");
                }
            }
        });
        operatePanel.add(reset);
        add(operatePanel);

    }

    /**
     * 初始化本地文件转Unicode文件页面
     *
     * @date 2018年10月29日
     */
    private void initFileNativeToUnicode() {
        JLabel nativeFileLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_I18N_00001") + ":", JLabel.RIGHT);
        nativeFileLabel.setFont(new Font("宋体", Font.PLAIN, 16)); // 设置"本地文件"字体
        nativeFileText = new JTextField();// 本地文件路径输入框
        nativeFileText.setSelectedTextColor(Color.RED);
        nativeFileSelectBtn = new JButton(FleaI18nHelper.i18nForCommon("COMMON_I18N_00008"));// 本地文件选择按钮
        nativeFileSelectBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    selectFile();
                } catch (Exception ex) {
                }
            }
        });
        unicodeFilePathArea = new JTextArea();// Unicode文本域
        unicodeFilePathArea.setSelectedTextColor(Color.RED);// 设置选中文本的颜色
        unicodeFilePathArea.setLineWrap(true);// 设置自动换行
        unicodeFilePathArea.setWrapStyleWord(true);// 设置换行不断字
        unicodeFilePathArea.setEditable(false);

        // File Native To Unicode
        nativeToUnicodeFilePanel = new JPanel(new BorderLayout(10, 5));
        JPanel topJpanel = new JPanel(new BorderLayout(10, 10));
        topJpanel.add(nativeFileLabel, BorderLayout.WEST);
        topJpanel.add(nativeFileText, BorderLayout.CENTER);
        topJpanel.add(nativeFileSelectBtn, BorderLayout.EAST);
        nativeToUnicodeFilePanel.add(topJpanel, BorderLayout.NORTH);
        nativeToUnicodeFilePanel.add(unicodeFilePathArea);
    }

    /**
     * 初始化Unicode文件转本地文件页面
     *
     * @date 2018年10月29日
     */
    private void initFileUnicodeToNative() {
        JLabel unicodeFileLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_I18N_00002") + ":", JLabel.RIGHT);
        unicodeFileLabel.setFont(new Font("宋体", Font.PLAIN, 16)); // 设置"Unicode文件"字体
        unicodeFileText = new JTextField();// Unicode文件路径输入框
        unicodeFileText.setSelectedTextColor(Color.RED);
        unicodeFileSelectBtn = new JButton(FleaI18nHelper.i18nForCommon("COMMON_I18N_00008"));// Unicode文件选择按钮
        unicodeFileSelectBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    selectFile();
                } catch (Exception ex) {
                }
            }
        });
        nativeFilePathArea = new JTextArea();// Unicode文本域
        nativeFilePathArea.setSelectedTextColor(Color.RED);// 设置选中文本的颜色
        nativeFilePathArea.setLineWrap(true);// 设置自动换行
        nativeFilePathArea.setWrapStyleWord(true);// 设置换行不断字
        nativeFilePathArea.setEditable(false);

        // File Unicode To Native
        unicodeToNativeFilePanel = new JPanel(new BorderLayout(10, 5));
        JPanel topJpanel = new JPanel(new BorderLayout(10, 10));
        topJpanel.add(unicodeFileLabel, BorderLayout.WEST);
        topJpanel.add(unicodeFileText, BorderLayout.CENTER);
        topJpanel.add(unicodeFileSelectBtn, BorderLayout.EAST);
        unicodeToNativeFilePanel.add(topJpanel, BorderLayout.NORTH);
        unicodeToNativeFilePanel.add(nativeFilePathArea);
    }

    /**
     * 初始化本地字符串转Unicode字符串页面
     *
     * @date 2018年10月29日
     */
    private void initStringNativeToUnicode() {
        JLabel nativeStringLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_I18N_00003") + ":", JLabel.RIGHT);
        nativeStringLabel.setFont(new Font("宋体", Font.PLAIN, 16)); // 设置"本地文件"字体
        nativeStringText = new JTextField();// 本地文件路径输入框
        nativeStringText.setSelectedTextColor(Color.RED);
        unicodeStringArea = new JTextArea();// Unicode文本域
        unicodeStringArea.setSelectedTextColor(Color.RED);// 设置选中文本的颜色
        unicodeStringArea.setLineWrap(true);// 设置自动换行
        unicodeStringArea.setWrapStyleWord(true);// 设置换行不断字

        // String Native To Unicode
        nativeToUnicodeStringPanel = new JPanel(new BorderLayout(10, 5));
        JPanel topJpanel = new JPanel(new BorderLayout(10, 10));
        topJpanel.add(nativeStringLabel, BorderLayout.WEST);
        topJpanel.add(nativeStringText, BorderLayout.CENTER);
        nativeToUnicodeStringPanel.add(topJpanel, BorderLayout.NORTH);
        nativeToUnicodeStringPanel.add(unicodeStringArea);
    }

    /**
     * 初始化Unicode字符串转本地字符串页面
     *
     * @date 2018年10月29日
     */
    private void initStringUnicodeToNative() {
        JLabel unicodeStringLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_I18N_00004") + ":", JLabel.RIGHT);
        unicodeStringLabel.setFont(new Font("宋体", Font.PLAIN, 16)); // 设置"Unicode文件"字体
        unicodeStringText = new JTextField();// Unicode文件路径输入框
        unicodeStringText.setSelectedTextColor(Color.RED);
        nativeStringArea = new JTextArea();// 本地文本域
        nativeStringArea.setSelectedTextColor(Color.RED);// 设置选中文本的颜色
        nativeStringArea.setLineWrap(true);// 设置自动换行
        nativeStringArea.setWrapStyleWord(true);// 设置换行不断字

        // String Unicode To Native
        unicodeToNativeStringPanel = new JPanel(new BorderLayout(10, 5));
        JPanel topJpanel = new JPanel(new BorderLayout(10, 10));
        topJpanel.add(unicodeStringLabel, BorderLayout.WEST);
        topJpanel.add(unicodeStringText, BorderLayout.CENTER);
        unicodeToNativeStringPanel.add(topJpanel, BorderLayout.NORTH);
        unicodeToNativeStringPanel.add(nativeStringArea);
    }

    /**
     * 打开文件目录
     *
     * @date 2018年10月29日
     */
    private void open() {
        String path = null;
        if (currTab == 1) {
            // 获取生成的文件路径
            path = unicodeFilePathArea.getText();
        } else if (currTab == 2) {
            // 获取生成的文件路径
            path = nativeFilePathArea.getText();
        }
        // 打开 生成的文件 所在目录
        if (StringUtils.isNotBlank(path)) {
            try {
                Desktop.getDesktop().open(new File(path).getParentFile());
            } catch (IOException e) {
                ToolsHelper.showTips(FleaI18nHelper.i18nForCommon("COMMON_I18N_00010"), "大佬，由于系统异常，打开生成文件 所在目录异常", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            if (currTab == 1) {
                ToolsHelper.showTips(FleaI18nHelper.i18nForCommon("COMMON_I18N_00009"), FleaI18nHelper.i18nForCommon("COMMON_I18N_00015", new String[]{FleaI18nHelper.i18nForCommon("COMMON_I18N_00001")}), JOptionPane.WARNING_MESSAGE);
            } else if (currTab == 2) {
                ToolsHelper.showTips(FleaI18nHelper.i18nForCommon("COMMON_I18N_00009"), FleaI18nHelper.i18nForCommon("COMMON_I18N_00015", new String[]{FleaI18nHelper.i18nForCommon("COMMON_I18N_00002")}), JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * 本地文件转Unicode文件
     *
     * @date 2018年10月10日
     */
    private void fileNativeToUnicode() {
        // 获取本地文件的路径
        String nativeFilePath = nativeFileText.getText();
        if (StringUtils.isNotBlank(nativeFilePath)) {
            // Unicode文件路径，默认取本地文件路径，文件名追加Unicode，文件后缀保持不变
            String nativeFilePre = nativeFilePath.substring(0, nativeFilePath.lastIndexOf(CommonConstants.SymbolConstants.DOT));
            String nativeFileExt = nativeFilePath.substring(nativeFilePath.lastIndexOf(CommonConstants.SymbolConstants.DOT));
            String unicodeFilePath = nativeFilePre + CommonConstants.SymbolConstants.UNDERLINE + "Unicode" + nativeFileExt;
            try {
                UnicodeUtils.fileNativeToUnicode(EXE_PATH_DEFAULT, "gbk", nativeFilePath, unicodeFilePath);
                // 提示 转化成功，展示文件路径
                ToolsHelper.showTips(FleaI18nHelper.i18nForCommon("COMMON_I18N_00011"), FleaI18nHelper.i18nForCommon("COMMON_I18N_00016", new String[]{FleaI18nHelper.i18nForCommon("COMMON_I18N_00002"), unicodeFilePath}), JOptionPane.INFORMATION_MESSAGE);
                unicodeFilePathArea.setText(unicodeFilePath);
            } catch (Exception e) {
                ToolsHelper.showTips(FleaI18nHelper.i18nForCommon("COMMON_I18N_00010"), FleaI18nHelper.i18nForCommon("COMMON_I18N_00014"), JOptionPane.ERROR_MESSAGE);
            }
        } else {
            ToolsHelper.showTips(FleaI18nHelper.i18nForCommon("COMMON_I18N_00009"), FleaI18nHelper.i18nForCommon("COMMON_I18N_00012", new String[]{FleaI18nHelper.i18nForCommon("COMMON_I18N_00001")}), JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Unicode文件转本地文件
     *
     * @date 2018年10月10日
     */
    private void fileUnicodeToNative() {
        // 获取Unicode文件路径
        String unicodeFilePath = unicodeFileText.getText();
        if (StringUtils.isNotBlank(unicodeFilePath)) {
            // Unicode文件路径，默认取本地文件路径，文件名追加Unicode，文件后缀保持不变
            String unicodeFilePre = unicodeFilePath.substring(0, unicodeFilePath.lastIndexOf(CommonConstants.SymbolConstants.DOT));
            String unicodeFileExt = unicodeFilePath.substring(unicodeFilePath.lastIndexOf(CommonConstants.SymbolConstants.DOT));
            String nativeFilePath = unicodeFilePre + CommonConstants.SymbolConstants.UNDERLINE + "Native" + unicodeFileExt;
            try {
                UnicodeUtils.fileUnicodeToNative(EXE_PATH_DEFAULT, unicodeFilePath, nativeFilePath);
                // 提示 转化成功，展示文件路径
                ToolsHelper.showTips(FleaI18nHelper.i18nForCommon("COMMON_I18N_00011"), FleaI18nHelper.i18nForCommon("COMMON_I18N_00016", new String[]{FleaI18nHelper.i18nForCommon("COMMON_I18N_00001"), nativeFilePath}), JOptionPane.INFORMATION_MESSAGE);
                nativeFilePathArea.setText(nativeFilePath);
            } catch (Exception e) {
                ToolsHelper.showTips(FleaI18nHelper.i18nForCommon("COMMON_I18N_00010"), FleaI18nHelper.i18nForCommon("COMMON_I18N_00014"), JOptionPane.ERROR_MESSAGE);
            }
        } else {
            ToolsHelper.showTips(FleaI18nHelper.i18nForCommon("COMMON_I18N_00009"), FleaI18nHelper.i18nForCommon("COMMON_I18N_00012", new String[]{FleaI18nHelper.i18nForCommon("COMMON_I18N_00002")}), JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * 本地字符串转Unicode字符串
     *
     * @date 2018年10月10日
     */
    private void stringNativeToUnicode() {
        // 获取本地字符串
        String nativeStr = nativeStringText.getText();
        if (StringUtils.isNotBlank(nativeStr)) {
            try {
                String unicodeStr = UnicodeUtils.nativeToUnicode(nativeStr);
                unicodeStringArea.setText(unicodeStr);
            } catch (Exception e) {
                ToolsHelper.showTips(FleaI18nHelper.i18nForCommon("COMMON_I18N_00010"), FleaI18nHelper.i18nForCommon("COMMON_I18N_00014"), JOptionPane.ERROR_MESSAGE);
            }
        } else {
            ToolsHelper.showTips(FleaI18nHelper.i18nForCommon("COMMON_I18N_00009"), FleaI18nHelper.i18nForCommon("COMMON_I18N_00013", new String[]{FleaI18nHelper.i18nForCommon("COMMON_I18N_00003")}), JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Unicode字符串转本地字符串
     *
     * @date 2018年10月10日
     */
    private void stringUnicodeToNative() {
        // 获取Unicode字符串
        String unicodeStr = unicodeStringText.getText();
        if (StringUtils.isNotBlank(unicodeStr)) {
            try {
                String nativeStr = UnicodeUtils.unicodeToNative(unicodeStr);
                nativeStringArea.setText(nativeStr);
            } catch (Exception e) {
                ToolsHelper.showTips(FleaI18nHelper.i18nForCommon("COMMON_I18N_00010"), FleaI18nHelper.i18nForCommon("COMMON_I18N_00014"), JOptionPane.ERROR_MESSAGE);
            }
        } else {
            ToolsHelper.showTips(FleaI18nHelper.i18nForCommon("COMMON_I18N_00009"), FleaI18nHelper.i18nForCommon("COMMON_I18N_00013", new String[]{FleaI18nHelper.i18nForCommon("COMMON_I18N_00004")}), JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * 弹出选择文件页面
     *
     * @since 1.0.0
     */
    private void selectFile() {
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.showDialog(new JLabel(), FleaI18nHelper.i18nForCommon("COMMON_I18N_00008"));
        File file = fileChooser.getSelectedFile();
        if (file != null) {
            if (currTab == 1) {
                nativeFileText.setText(file.getAbsolutePath());
            } else if (currTab == 2) {
                unicodeFileText.setText(file.getAbsolutePath());
            }
        }
    }

}

