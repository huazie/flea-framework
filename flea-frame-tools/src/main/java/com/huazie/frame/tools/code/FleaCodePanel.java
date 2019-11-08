package com.huazie.frame.tools.code;

import com.huazie.frame.common.i18n.FleaI18nHelper;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.DBSystemEnum;
import com.huazie.frame.tools.common.ToolsConstants;
import com.huazie.frame.tools.common.ToolsException;
import com.huazie.frame.tools.common.ToolsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> 数据操作和业务逻辑层实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaCodePanel extends JPanel implements ActionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaCodePanel.class);

    private JComboBox<String> dbSystemComboBox; // 数据库系统下拉框

    private JTextField dbNameTextField; // 数据库名

    private JTextField tableNameTextField; // 表名

    private JTextField tableNameDescTextField; // 表名描述

    private JTextField authorTextField; // 作者

    private JTextField versionTextField; // 版本

    private JTextField rootPathTextField; // 根目录

    private JButton rootPathSelectButton; // 根目录选择按钮

    private JTextField codePackageTextField; // 包名

    private JRadioButton newRadioButton; // 新建持久化单元DAO层实现 单选按钮

    private JRadioButton oldRadioButton; // 现有持久化单元DAO层实现 单选按钮

    private ButtonGroup btnGroup; // 单选按钮组

    private JTextField puDaoPackageTextField; // 持久化单元DAO层实现包名

    private JLabel puDAOClassNameLabel;

    private JTextField puDaoClassNameTextField; // 持久化单元DAO层实现类名

    private JLabel persistenceUnitNameLabel;

    private JTextField puNameTextField; // 持久化单元名

    private JLabel persistenceUnitAliasNameLabel;

    private JTextField puAliasNameTextField; // 持久化单元别名

    private JButton importButton; // 导入 按钮

    private JButton generateButton; // 生成 按钮

    private JButton destroyButton; // 销毁 按钮

    private JButton resetButton; // 重置 按钮

    private GridBagLayout configGridBagLayout;

    private JPanel configPanel;

    private GridBagConstraints configGridBagConstraints;

    public FleaCodePanel() {
        init();
    }

    private void init() {

        setLayout(new GridLayout(1, 1));

        configGridBagLayout = new GridBagLayout();
        configPanel = new JPanel(configGridBagLayout);
        configGridBagConstraints = new GridBagConstraints();

        initElements(); // 初始化页面元素

        add(configPanel);
    }

    private void initElements() {
        // 初始化数据库配置页面元素
        initDBConfig();

        // 添加分隔线
        addSeparator();

        // 初始化代码配置页面元素
        initCodeConfig();

        // 添加分隔线
        addSeparator();

        // 初始化持久化单元配置页面元素
        initPersistenceUnitConfig();

        // 添加分隔线
        addSeparator();

        // 初始化按钮
        initButton();
    }

    private void initDBConfig() {
        // 数据库配置
        JLabel dbConfigLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00001"), JLabel.LEFT);
        dbConfigLabel.setFont(new Font("宋体", Font.PLAIN, 20)); // 字体

        configGridBagConstraints.fill = GridBagConstraints.BOTH;
        configGridBagConstraints.weightx = 1.0;
        configGridBagConstraints.insets = new Insets(5, 20, 2, 0);
        configGridBagLayout.setConstraints(dbConfigLabel, configGridBagConstraints);
        configPanel.add(dbConfigLabel);
        configGridBagConstraints.insets = new Insets(5, 5, 2, 0);
        JLabel other1 = new JLabel(" ");
        configGridBagLayout.setConstraints(other1, configGridBagConstraints);
        configPanel.add(other1);
        JLabel other2 = new JLabel(" ");
        configGridBagLayout.setConstraints(other2, configGridBagConstraints);
        configPanel.add(other2);
        JLabel other3 = new JLabel(" ");
        configGridBagLayout.setConstraints(other3, configGridBagConstraints);
        configPanel.add(other3);
        JLabel other4 = new JLabel(" ");
        configGridBagLayout.setConstraints(other4, configGridBagConstraints);
        configPanel.add(other4);
        JLabel other5 = new JLabel(" ");
        configGridBagLayout.setConstraints(other5, configGridBagConstraints);
        configPanel.add(other5);
        JLabel other6 = new JLabel(" ");
        configGridBagLayout.setConstraints(other6, configGridBagConstraints);
        configPanel.add(other6);
        JLabel other7 = new JLabel(" ");
        configGridBagLayout.setConstraints(other7, configGridBagConstraints);
        configPanel.add(other7);
        JLabel other8 = new JLabel(" ");
        configGridBagLayout.setConstraints(other8, configGridBagConstraints);
        configPanel.add(other8);
        JLabel other9 = new JLabel(" ");
        configGridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; // end row
        configGridBagLayout.setConstraints(other9, configGridBagConstraints);
        configPanel.add(other9);

        // 数据库系统
        JLabel dbSystemLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00002") + ":", JLabel.RIGHT);
        dbSystemLabel.setFont(new Font("宋体", Font.PLAIN, 15)); // 字体
        dbSystemComboBox = new JComboBox<String>();
        dbSystemComboBox.addItem(DBSystemEnum.MySQL.getName());
        dbSystemComboBox.addItem(DBSystemEnum.Oracle.getName());

        // 数据库名
        JLabel dbNameLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00003") + ":", JLabel.RIGHT);
        dbNameLabel.setFont(new Font("宋体", Font.PLAIN, 15)); // 字体
        dbNameTextField = new JTextField();

        configGridBagConstraints.insets = new Insets(2, 0, 2, 0);
        configGridBagConstraints.weightx = 0.0;
        configGridBagConstraints.gridwidth = 1;
        configGridBagLayout.setConstraints(dbSystemLabel, configGridBagConstraints);
        configPanel.add(dbSystemLabel);
        configGridBagConstraints.gridwidth = 4;
        configGridBagLayout.setConstraints(dbSystemComboBox, configGridBagConstraints);
        configPanel.add(dbSystemComboBox);
        configGridBagConstraints.gridwidth = 1;
        configGridBagLayout.setConstraints(dbNameLabel, configGridBagConstraints);
        configPanel.add(dbNameLabel);
        configGridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; // end row
        configGridBagLayout.setConstraints(dbNameTextField, configGridBagConstraints);
        configPanel.add(dbNameTextField);

        // 表名
        JLabel tableNameLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00004") + ":", JLabel.RIGHT);
        tableNameLabel.setFont(new Font("宋体", Font.PLAIN, 15)); // 字体
        tableNameTextField = new JTextField();

        // 表名描述
        JLabel tableNameDescLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00005") + ":", JLabel.RIGHT);
        tableNameDescLabel.setFont(new Font("宋体", Font.PLAIN, 15)); // 字体
        tableNameDescTextField = new JTextField();

        configGridBagConstraints.gridwidth = 1;
        configGridBagLayout.setConstraints(tableNameLabel, configGridBagConstraints);
        configPanel.add(tableNameLabel);
        configGridBagConstraints.gridwidth = 4;
        configGridBagLayout.setConstraints(tableNameTextField, configGridBagConstraints);
        configPanel.add(tableNameTextField);
        configGridBagConstraints.gridwidth = 1;
        configGridBagLayout.setConstraints(tableNameDescLabel, configGridBagConstraints);
        configPanel.add(tableNameDescLabel);
        configGridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; // end row
        configGridBagLayout.setConstraints(tableNameDescTextField, configGridBagConstraints);
        configPanel.add(tableNameDescTextField);
    }

    private void initCodeConfig() {
        // 代码配置
        JLabel codeConfigLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00006"), JLabel.LEFT);
        codeConfigLabel.setFont(new Font("宋体", Font.PLAIN, 20)); // 字体

        configGridBagConstraints.weightx = 1.0;
        configGridBagConstraints.gridwidth = 1;
        configGridBagConstraints.insets = new Insets(2, 20, 2, 0);
        configGridBagLayout.setConstraints(codeConfigLabel, configGridBagConstraints);
        configPanel.add(codeConfigLabel);
        configGridBagConstraints.insets = new Insets(5, 5, 2, 0);
        JLabel codeOther1 = new JLabel(" ");
        configGridBagLayout.setConstraints(codeOther1, configGridBagConstraints);
        configPanel.add(codeOther1);
        JLabel codeOther2 = new JLabel(" ");
        configGridBagLayout.setConstraints(codeOther2, configGridBagConstraints);
        configPanel.add(codeOther2);
        JLabel codeOther3 = new JLabel(" ");
        configGridBagLayout.setConstraints(codeOther3, configGridBagConstraints);
        configPanel.add(codeOther3);
        JLabel codeOther4 = new JLabel(" ");
        configGridBagLayout.setConstraints(codeOther4, configGridBagConstraints);
        configPanel.add(codeOther4);
        JLabel codeOther5 = new JLabel(" ");
        configGridBagLayout.setConstraints(codeOther5, configGridBagConstraints);
        configPanel.add(codeOther5);
        JLabel codeOther6 = new JLabel(" ");
        configGridBagLayout.setConstraints(codeOther6, configGridBagConstraints);
        configPanel.add(codeOther6);
        JLabel codeOther7 = new JLabel(" ");
        configGridBagLayout.setConstraints(codeOther7, configGridBagConstraints);
        configPanel.add(codeOther7);
        JLabel codeOther8 = new JLabel(" ");
        configGridBagLayout.setConstraints(codeOther8, configGridBagConstraints);
        configPanel.add(codeOther8);
        JLabel codeOther9 = new JLabel(" ");
        configGridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; // end row
        configGridBagLayout.setConstraints(codeOther9, configGridBagConstraints);
        configPanel.add(codeOther9);

        // 作者
        JLabel authorLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00007") + ":", JLabel.RIGHT);
        authorLabel.setFont(new Font("宋体", Font.PLAIN, 15)); // 字体
        authorTextField = new JTextField();

        // 版本
        JLabel versionLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00008") + ":", JLabel.RIGHT);
        versionLabel.setFont(new Font("宋体", Font.PLAIN, 15)); // 字体
        versionTextField = new JTextField();

        configGridBagConstraints.insets = new Insets(2, 0, 2, 0);
        configGridBagConstraints.weightx = 0.0;
        configGridBagConstraints.gridwidth = 1;
        configGridBagLayout.setConstraints(authorLabel, configGridBagConstraints);
        configPanel.add(authorLabel);
        configGridBagConstraints.gridwidth = 4;
        configGridBagLayout.setConstraints(authorTextField, configGridBagConstraints);
        configPanel.add(authorTextField);
        configGridBagConstraints.gridwidth = 1;
        configGridBagLayout.setConstraints(versionLabel, configGridBagConstraints);
        configPanel.add(versionLabel);
        configGridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; // end row
        configGridBagLayout.setConstraints(versionTextField, configGridBagConstraints);
        configPanel.add(versionTextField);

        // 根目录
        JLabel rootPathLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00009") + ":", JLabel.RIGHT);
        rootPathLabel.setFont(new Font("宋体", Font.PLAIN, 15)); // 字体
        rootPathTextField = new JTextField();
        rootPathSelectButton = new JButton(FleaI18nHelper.i18nForCommon("COMMON_I18N_00008"));
        rootPathSelectButton.addActionListener(this);

        configGridBagConstraints.gridwidth = 1;
        configGridBagLayout.setConstraints(rootPathLabel, configGridBagConstraints);
        configPanel.add(rootPathLabel);
        configGridBagConstraints.gridwidth = 8;
        configGridBagLayout.setConstraints(rootPathTextField, configGridBagConstraints);
        configPanel.add(rootPathTextField);
        configGridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; // end row
        configGridBagLayout.setConstraints(rootPathSelectButton, configGridBagConstraints);
        configPanel.add(rootPathSelectButton);

        // 包名
        JLabel codePackageLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00010") + ":", JLabel.RIGHT);
        codePackageLabel.setFont(new Font("宋体", Font.PLAIN, 15)); // 字体
        codePackageTextField = new JTextField();

        configGridBagConstraints.gridwidth = 1;
        configGridBagLayout.setConstraints(codePackageLabel, configGridBagConstraints);
        configPanel.add(codePackageLabel);
        configGridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; // end row
        configGridBagLayout.setConstraints(codePackageTextField, configGridBagConstraints);
        configPanel.add(codePackageTextField);
    }

    private void initPersistenceUnitConfig() {
        // 持久化单元配置
        JLabel puConfigLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00020"), JLabel.LEFT);
        puConfigLabel.setFont(new Font("宋体", Font.PLAIN, 20)); // 字体

        configGridBagConstraints.weightx = 1.0;
        configGridBagConstraints.gridwidth = 1;
        configGridBagConstraints.insets = new Insets(2, 20, 2, 0);
        configGridBagLayout.setConstraints(puConfigLabel, configGridBagConstraints);
        configPanel.add(puConfigLabel);
        configGridBagConstraints.insets = new Insets(5, 5, 2, 0);
        JLabel puOther1 = new JLabel(" ");
        configGridBagLayout.setConstraints(puOther1, configGridBagConstraints);
        configPanel.add(puOther1);
        JLabel puOther2 = new JLabel(" ");
        configGridBagLayout.setConstraints(puOther2, configGridBagConstraints);
        configPanel.add(puOther2);
        JLabel puOther3 = new JLabel(" ");
        configGridBagLayout.setConstraints(puOther3, configGridBagConstraints);
        configPanel.add(puOther3);
        JLabel puOther4 = new JLabel(" ");
        configGridBagLayout.setConstraints(puOther4, configGridBagConstraints);
        configPanel.add(puOther4);
        JLabel puOther5 = new JLabel(" ");
        configGridBagLayout.setConstraints(puOther5, configGridBagConstraints);
        configPanel.add(puOther5);
        JLabel puOther6 = new JLabel(" ");
        configGridBagLayout.setConstraints(puOther6, configGridBagConstraints);
        configPanel.add(puOther6);
        JLabel puOther7 = new JLabel(" ");
        configGridBagLayout.setConstraints(puOther7, configGridBagConstraints);
        configPanel.add(puOther7);
        JLabel puOther8 = new JLabel(" ");
        configGridBagLayout.setConstraints(puOther8, configGridBagConstraints);
        configPanel.add(puOther8);
        JLabel puOther9 = new JLabel(" ");
        configGridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; // end row
        configGridBagLayout.setConstraints(puOther9, configGridBagConstraints);
        configPanel.add(puOther9);

        // 持久化单元DAO层实现 单元框， 新建 或 现有
        JLabel puDaoClassLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00011") + ":", JLabel.RIGHT);
        puDaoClassLabel.setFont(new Font("宋体", Font.PLAIN, 15)); // 字体
        // 新建 持久化单元DAO层实现类
        newRadioButton = new JRadioButton(FleaI18nHelper.i18nForCommon("COMMON_CODE_00012"));
        newRadioButton.addActionListener(this); // 添加按钮事件
        // 现有 持久化单元DAO层实现类
        oldRadioButton = new JRadioButton(FleaI18nHelper.i18nForCommon("COMMON_CODE_00013"));
        oldRadioButton.addActionListener(this); // 添加按钮事件
        // 创建按钮组，把两个单选按钮添加到该组
        btnGroup = new ButtonGroup();
        btnGroup.add(newRadioButton);
        btnGroup.add(oldRadioButton);

        configGridBagConstraints.gridwidth = 1;
        configGridBagLayout.setConstraints(puDaoClassLabel, configGridBagConstraints);
        configPanel.add(puDaoClassLabel);
        configGridBagConstraints.gridwidth = 3;
        configGridBagLayout.setConstraints(newRadioButton, configGridBagConstraints);
        configPanel.add(newRadioButton);
        configGridBagConstraints.gridwidth = 3;
        configGridBagLayout.setConstraints(oldRadioButton, configGridBagConstraints);
        configPanel.add(oldRadioButton);
        configGridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; // end row
        JLabel radioLabel = new JLabel(" ");
        configGridBagLayout.setConstraints(radioLabel, configGridBagConstraints);
        configPanel.add(radioLabel);

        // 持久化单元DAO层实现类包名
        JLabel puDAOClassPackageLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00010") + ":", JLabel.RIGHT);
        puDAOClassPackageLabel.setFont(new Font("宋体", Font.PLAIN, 15)); // 字体
        puDaoPackageTextField = new JTextField();

        configGridBagConstraints.gridwidth = 1;
        configGridBagLayout.setConstraints(puDAOClassPackageLabel, configGridBagConstraints);
        configPanel.add(puDAOClassPackageLabel);
        configGridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; // end row
        configGridBagLayout.setConstraints(puDaoPackageTextField, configGridBagConstraints);
        configPanel.add(puDaoPackageTextField);

        // 持久化单元DAO层实现类名
        puDAOClassNameLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00016") + ":", JLabel.RIGHT);
        puDAOClassNameLabel.setFont(new Font("宋体", Font.PLAIN, 15)); // 字体
        puDaoClassNameTextField = new JTextField();

        configGridBagConstraints.gridwidth = 1;
        configGridBagLayout.setConstraints(puDAOClassNameLabel, configGridBagConstraints);
        configPanel.add(puDAOClassNameLabel);
        configGridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; // end row
        configGridBagLayout.setConstraints(puDaoClassNameTextField, configGridBagConstraints);
        configPanel.add(puDaoClassNameTextField);

        // 持久化单元名
        persistenceUnitNameLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00014") + ":", JLabel.RIGHT);
        persistenceUnitNameLabel.setFont(new Font("宋体", Font.PLAIN, 15)); // 字体
        puNameTextField = new JTextField();
        // 持久化单元别名 （单词首字母大写）
        persistenceUnitAliasNameLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00015") + ":", JLabel.RIGHT);
        persistenceUnitAliasNameLabel.setFont(new Font("宋体", Font.PLAIN, 15)); // 字体
        puAliasNameTextField = new JTextField();

        configGridBagConstraints.gridwidth = 1;
        configGridBagLayout.setConstraints(persistenceUnitNameLabel, configGridBagConstraints);
        configPanel.add(persistenceUnitNameLabel);
        configGridBagConstraints.gridwidth = 4;
        configGridBagLayout.setConstraints(puNameTextField, configGridBagConstraints);
        configPanel.add(puNameTextField);
        configGridBagConstraints.gridwidth = 1;
        configGridBagLayout.setConstraints(persistenceUnitAliasNameLabel, configGridBagConstraints);
        configPanel.add(persistenceUnitAliasNameLabel);
        configGridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; // end row
        configGridBagLayout.setConstraints(puAliasNameTextField, configGridBagConstraints);
        configPanel.add(puAliasNameTextField);

        // 初始化
        // 设置第一个单选按钮选中
        oldRadioButton.setSelected(true);
        persistenceUnitNameLabel.setVisible(false);
        puNameTextField.setVisible(false);
        persistenceUnitAliasNameLabel.setVisible(false);
        puAliasNameTextField.setVisible(false);
    }

    private void addSeparator() {
        configGridBagConstraints.weightx = 0.0;
        configGridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; // end row
        configGridBagConstraints.insets = new Insets(2, 0, 2, 0);
        JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
        sep.setBackground(new Color(10, 10, 10));
        configGridBagLayout.setConstraints(sep, configGridBagConstraints);
        configPanel.add(sep);
    }

    private void initButton() {
        JPanel buttonPanel = new JPanel();
        // 生成按钮
        generateButton = new JButton(FleaI18nHelper.i18nForCommon("COMMON_CODE_00017"));
        generateButton.addActionListener(this);
        buttonPanel.add(generateButton);

        // 销毁按钮
        destroyButton = new JButton(FleaI18nHelper.i18nForCommon("COMMON_CODE_00018"));
        destroyButton.addActionListener(this);
        buttonPanel.add(destroyButton);

        resetButton = new JButton(FleaI18nHelper.i18nForCommon("COMMON_I18N_00007"));
        resetButton.addActionListener(this);
        buttonPanel.add(resetButton);

        // 导入按钮
        importButton = new JButton(FleaI18nHelper.i18nForCommon("COMMON_CODE_00019"));
        importButton.addActionListener(this);
        buttonPanel.add(importButton);

        configGridBagConstraints.weightx = 0.0;
        configGridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; // end row
        configGridBagConstraints.insets = new Insets(2, 0, 2, 0);
        configGridBagLayout.setConstraints(buttonPanel, configGridBagConstraints);
        configPanel.add(buttonPanel);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            Object source = ae.getSource();
            if (newRadioButton == source) {
                puDAOClassNameLabel.setVisible(false);
                puDaoClassNameTextField.setVisible(false);
                persistenceUnitNameLabel.setVisible(true);
                puNameTextField.setVisible(true);
                persistenceUnitAliasNameLabel.setVisible(true);
                puAliasNameTextField.setVisible(true);
            } else if (oldRadioButton == source) {
                puDAOClassNameLabel.setVisible(true);
                puDaoClassNameTextField.setVisible(true);
                persistenceUnitNameLabel.setVisible(false);
                puNameTextField.setVisible(false);
                persistenceUnitAliasNameLabel.setVisible(false);
                puAliasNameTextField.setVisible(false);
            } else if (generateButton == source) {
                code();
            } else if (destroyButton == source) {
                clean();
            } else if (resetButton == source) {
                reset();
            } else if (rootPathSelectButton == source) {
                selectFile();
            } else if (importButton == source) {
                importConfig();
            }
        } catch (Exception e1) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Exception = {}", e1);
            }
            ToolsHelper.showTips(FleaI18nHelper.i18nForCommon("COMMON_I18N_00010"), e1.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private void code() throws Exception {
        // 编写代码
        FleaCodeProgrammer.code(createParamMap());
    }

    private void clean() throws Exception {
        // 清理代码
        FleaCodeProgrammer.clean(createParamMap());
    }

    /**
     * <p> 重置 </p>
     *
     * @since 1.0.0
     */
    private void reset() {
        // 数据库配置
        dbSystemComboBox.setSelectedIndex(0);
        dbNameTextField.setText("");
        tableNameTextField.setText("");
        tableNameDescTextField.setText("");

        // 代码配置
        authorTextField.setText("");
        versionTextField.setText("");
        rootPathTextField.setText("");
        codePackageTextField.setText("");

        // 持久化单元配置
        puDaoPackageTextField.setText("");
        puDaoClassNameTextField.setText("");
        puNameTextField.setText("");
        puAliasNameTextField.setText("");

    }

    /**
     * <p> 导入配置 </p>
     *
     * @since 1.0.0
     */
    private void importConfig() {
        FleaCodeConfig config = FleaCodeConfig.getConfig();
        dbNameTextField.setText(config.getDbName());
        tableNameTextField.setText(config.getTableName());
        tableNameDescTextField.setText(config.getTableNameDesc());
        authorTextField.setText(config.getAuthor());
        versionTextField.setText(config.getVersion());
        rootPathTextField.setText(config.getRootPath());
        codePackageTextField.setText(config.getCodePackage());
        puDaoPackageTextField.setText(config.getPuDaoCodePackage());
        puDaoClassNameTextField.setText(config.getPuDaoClassName());
        puNameTextField.setText(config.getPuName());
        puAliasNameTextField.setText(config.getPuAliasName());
    }

    /**
     * <p> 弹出选择文件页面 </p>
     *
     * @since 1.0.0
     */
    private void selectFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.showDialog(new JLabel(), FleaI18nHelper.i18nForCommon("COMMON_I18N_00008"));
        File file = fileChooser.getSelectedFile();
        if (ObjectUtils.isNotEmpty(file)) {
            rootPathTextField.setText(file.getAbsolutePath());
        }
    }

    private Map<String, Object> createParamMap() throws Exception {
        Map<String, Object> param = new HashMap<String, Object>();

        // 1. 数据库配置
        // 获取数据库系统名
        String dbSystemName = dbSystemComboBox.getSelectedItem().toString();
        param.put(ToolsConstants.CodeConstants.DB_SYSTEM_NAME, dbSystemName);
        // 获取数据库名
        String dbName = dbNameTextField.getText();
        if (StringUtils.isBlank(dbName)) {
            throw new ToolsException("COMMON_I18N_00013", FleaI18nHelper.i18nForCommon("COMMON_CODE_00003"));
        }
        param.put(ToolsConstants.CodeConstants.DB_NAME, dbName);
        // 获取表名
        String tableName = tableNameTextField.getText();
        if (StringUtils.isBlank(tableName)) {
            throw new ToolsException("COMMON_I18N_00013", FleaI18nHelper.i18nForCommon("COMMON_CODE_00004"));
        }
        param.put(ToolsConstants.CodeConstants.TABLE_NAME, tableName);
        // 获取表名描述
        String tableNameDesc = tableNameDescTextField.getText();
        if (StringUtils.isBlank(tableNameDesc)) {
            throw new ToolsException("COMMON_I18N_00013", FleaI18nHelper.i18nForCommon("COMMON_CODE_00005"));
        }
        param.put(ToolsConstants.CodeConstants.TABLE_DESC, tableNameDesc);

        // 2. 代码配置
        // 获取作者
        String author = authorTextField.getText();
        if (StringUtils.isBlank(author)) {
            throw new ToolsException("COMMON_I18N_00013", FleaI18nHelper.i18nForCommon("COMMON_CODE_00007"));
        }
        param.put(ToolsConstants.CodeConstants.AUTHOR, author);
        // 获取版本
        String version = versionTextField.getText();
        if (StringUtils.isBlank(version)) {
            throw new ToolsException("COMMON_I18N_00013", FleaI18nHelper.i18nForCommon("COMMON_CODE_00008"));
        }
        param.put(ToolsConstants.CodeConstants.VERSION, version);
        // 获取根目录
        String rootPath = rootPathTextField.getText();
        if (StringUtils.isBlank(rootPath)) {
            throw new ToolsException("COMMON_I18N_00013", FleaI18nHelper.i18nForCommon("COMMON_CODE_00009"));
        }
        param.put(ToolsConstants.CodeConstants.ROOT_PACKAGE, rootPath);
        // 获取代码包名
        String codePackage = codePackageTextField.getText();
        if (StringUtils.isBlank(codePackage)) {
            throw new ToolsException("COMMON_I18N_00013", FleaI18nHelper.i18nForCommon("COMMON_CODE_00010"));
        }
        param.put(ToolsConstants.CodeConstants.CODE_PACKAGE, codePackage);

        // 3. 持久化单元配置
        String puDaoClassPackage = puDaoPackageTextField.getText();
        if (StringUtils.isBlank(puDaoClassPackage)) {
            throw new ToolsException("COMMON_I18N_00013", FleaI18nHelper.i18nForCommon("COMMON_CODE_00011") + FleaI18nHelper.i18nForCommon("COMMON_CODE_00010"));
        }
        param.put(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_DAO_CLASS_PACKAGE, puDaoClassPackage);
        // 获取单选按钮
        if (btnGroup.isSelected(newRadioButton.getModel())) { // 选择新建
            // 持久化单元名
            String fleaPersistenceUnitName = puNameTextField.getText();
            if (StringUtils.isBlank(fleaPersistenceUnitName)) {
                throw new ToolsException("COMMON_I18N_00013", FleaI18nHelper.i18nForCommon("COMMON_CODE_00014"));
            }
            param.put(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_NAME, fleaPersistenceUnitName);
            // 持久化单元别名
            String fleaPersistenceUnitAliasName = puAliasNameTextField.getText();
            if (StringUtils.isBlank(fleaPersistenceUnitAliasName)) {
                throw new ToolsException("COMMON_I18N_00013", FleaI18nHelper.i18nForCommon("COMMON_CODE_00015"));
            }
            param.put(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_ALIAS_NAME, fleaPersistenceUnitAliasName);
        } else { // 选择现有
            // 持久化单元DAO层实现类名
            String fleaPersistenceUnitDaoClassName = puDaoClassNameTextField.getText();
            if (StringUtils.isBlank(fleaPersistenceUnitDaoClassName)) {
                throw new ToolsException("COMMON_I18N_00013", FleaI18nHelper.i18nForCommon("COMMON_CODE_00016"));
            }
            param.put(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_DAO_CLASS_NAME, fleaPersistenceUnitDaoClassName);
        }

        return param;
    }

}
