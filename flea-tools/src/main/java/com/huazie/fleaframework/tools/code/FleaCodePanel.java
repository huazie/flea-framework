package com.huazie.fleaframework.tools.code;

import com.huazie.fleaframework.common.i18n.FleaI18nHelper;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.db.common.DBSystemEnum;
import com.huazie.fleaframework.tools.common.ToolsConstants;
import com.huazie.fleaframework.tools.common.ToolsException;
import com.huazie.fleaframework.tools.common.ToolsHelper;

import javax.persistence.GenerationType;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据操作和业务逻辑层实现
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaCodePanel extends JPanel implements ActionListener, ItemListener {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaCodePanel.class);

    private JComboBox<String> dbSystemComboBox; // 数据库系统下拉框

    private JTextField dbNameTextField; // 数据库名

    private JTextField tableNameTextField; // 表名

    private JTextField tableNameDescTextField; // 表名描述

    private JComboBox<String> idGeneratorStrategyComboBox; // 主键生成策略下拉框

    private JLabel idGeneratorTableLabel; // 主键生成器表标签

    private JTextField idGeneratorTableTextField; // 主键生成器表文本域

    private JLabel pkColumnNameLabel; // 主键列名

    private JTextField pkColumnNameTextField; // 主键列名文本域

    private JLabel valueColumnNameLabel; // 主键值列名

    private JTextField valueColumnNameTextField; // 主键值列名文本域

    private JTextField authorTextField; // 作者

    private JTextField versionTextField; // 版本

    private JCheckBox lombokEntityCheckBox; // 实体类使用Lombok

    private JTextField rootPathTextField; // 根目录

    private JButton rootPathSelectButton; // 根目录选择按钮

    private JTextField codePackageTextField; // 包名

    private JCheckBox allCheckBox; // 全选

    private JCheckBox daoImplCheckBox; // DAO层实现类

    private JCheckBox daoInterfaceCheckBox; // DAO层接口类

    private JCheckBox entityCheckBox; // 实体类

    private JCheckBox svImplCheckBox; // SV层实现类

    private JCheckBox svInterfaceCheckBox; // SV层接口类

    private JRadioButton newRadioButton; // 新建持久化单元DAO层实现 单选按钮

    private JRadioButton oldRadioButton; // 现有持久化单元DAO层实现 单选按钮

    private ButtonGroup btnGroup; // 单选按钮组

    private JCheckBox customTransactionalCheckBox; // 自定义事物

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
        configGridBagConstraints.fill = GridBagConstraints.BOTH;

        initElements(); // 初始化页面元素

        add(configPanel);
    }

    private void initElements() {
        // 初始化数据库配置页面元素
        initDBConfig();

        // 添加分隔线
        addSeparator();

        // 初始化主键生成策略配置页面元素
        initIDGeneratorConfig();

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
        showTitleConfig("COMMON_CODE_00001");

        configGridBagConstraints.insets = new Insets(2, 0, 2, 0);
        configGridBagConstraints.weightx = 0.0;

        // 数据库系统下拉框
        dbSystemComboBox = new JComboBox<>();
        dbSystemComboBox.addItem(DBSystemEnum.MySQL.getName());
        dbSystemComboBox.addItem(DBSystemEnum.Oracle.getName());

        // 数据库系统
        showOneContentConfig(null, "COMMON_CODE_00002", dbSystemComboBox, 4, false);

        // 数据库名文本域
        dbNameTextField = new JTextField();
        // 数据库名
        showOneContentConfig(null, "COMMON_CODE_00003", dbNameTextField, 4, true);

        // 表名文本域
        tableNameTextField = new JTextField();
        // 表名
        showOneContentConfig(null, "COMMON_CODE_00004", tableNameTextField, 4, false);

        // 表名描述文本域
        tableNameDescTextField = new JTextField();
        // 表名描述
        showOneContentConfig(null, "COMMON_CODE_00005", tableNameDescTextField, 4, true);
    }

    private void initIDGeneratorConfig() {
        // 主键生成策略配置
        showTitleConfig("COMMON_CODE_00021");

        // 主键生成策略下拉框
        idGeneratorStrategyComboBox = new JComboBox<>();
        idGeneratorStrategyComboBox.addItem(ToolsConstants.CodeConstants.NONE);
        idGeneratorStrategyComboBox.addItem(GenerationType.TABLE.name());
        idGeneratorStrategyComboBox.addItem(GenerationType.IDENTITY.name());
        idGeneratorStrategyComboBox.addItem(GenerationType.SEQUENCE.name());
        idGeneratorStrategyComboBox.addItemListener(this);
        // 主键生成策略
        showOneContentConfig(null, "COMMON_CODE_00022", idGeneratorStrategyComboBox, 4, false);

        addEmptyLabel();
        addEmptyLabel();
        addEmptyLabel();
        addEmptyLabel();
        configGridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; // end row
        addEmptyLabel();

        // 主键生成器表标签
        idGeneratorTableLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00023") + ":", JLabel.RIGHT);
        // 主键生成器表文本域
        idGeneratorTableTextField = new JTextField();
        // 主键生成策略
        showOneContentConfig(idGeneratorTableLabel, "", idGeneratorTableTextField, 3, false);

        // 主键列名标签
        pkColumnNameLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00024") + ":", JLabel.RIGHT);
        // 主键列名文本域
        pkColumnNameTextField = new JTextField();
        showOneContentConfig(pkColumnNameLabel, "", pkColumnNameTextField, 3, false);

        // 主键值列名标签
        valueColumnNameLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00025") + ":", JLabel.RIGHT);
        // 主键值列名文本域
        valueColumnNameTextField = new JTextField();
        showOneContentConfig(valueColumnNameLabel, "", valueColumnNameTextField, 0, true);

        setGenerationTypeTableVisible(false);
    }

    private void initCodeConfig() {
        // 代码配置
        showTitleConfig("COMMON_CODE_00006");

        // 作者文本域
        authorTextField = new JTextField();
        // 作者
        showOneContentConfig(null, "COMMON_CODE_00007", authorTextField, 4, false);

        // 版本文本域
        versionTextField = new JTextField();
        // 版本
        showOneContentConfig(null, "COMMON_CODE_00008", versionTextField, 4, false);

        // 实体类使用Lombok
        lombokEntityCheckBox = newCheckBox(FleaI18nHelper.i18nForCommon("COMMON_CODE_00026"));
        configGridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        configGridBagLayout.setConstraints(lombokEntityCheckBox, configGridBagConstraints);
        configPanel.add(lombokEntityCheckBox);

        // 根目录文本域
        rootPathTextField = new JTextField();
        // 根目录
        showOneContentConfig(null, "COMMON_CODE_00009", rootPathTextField, 9, false);

        // 根目录选择按钮
        rootPathSelectButton = new JButton(FleaI18nHelper.i18nForCommon("COMMON_I18N_00008"));
        rootPathSelectButton.addActionListener(this);
        configGridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; // end row
        configGridBagLayout.setConstraints(rootPathSelectButton, configGridBagConstraints);
        configPanel.add(rootPathSelectButton);

        // 包名文本域
        codePackageTextField = new JTextField();
        // 包名
        showOneContentConfig(null, "COMMON_CODE_00010", codePackageTextField, 0, true);

        GridLayout  codeGeneratingLayout = new GridLayout(1,6);
        JPanel codeGeneratingPanel = new JPanel(codeGeneratingLayout);

        allCheckBox = newCheckBox(FleaI18nHelper.i18nForCommon("COMMON_CODE_00027"));
        allCheckBox.addActionListener(this);
        daoImplCheckBox = newCheckBox(FleaI18nHelper.i18nForCommon("COMMON_CODE_00028"));
        daoInterfaceCheckBox = newCheckBox(FleaI18nHelper.i18nForCommon("COMMON_CODE_00029"));
        entityCheckBox = newCheckBox(FleaI18nHelper.i18nForCommon("COMMON_CODE_00030"));
        svImplCheckBox = newCheckBox(FleaI18nHelper.i18nForCommon("COMMON_CODE_00031"));
        svInterfaceCheckBox = newCheckBox(FleaI18nHelper.i18nForCommon("COMMON_CODE_00032"));

        codeGeneratingPanel.add(allCheckBox);
        codeGeneratingPanel.add(daoImplCheckBox);
        codeGeneratingPanel.add(daoInterfaceCheckBox);
        codeGeneratingPanel.add(entityCheckBox);
        codeGeneratingPanel.add(svImplCheckBox);
        codeGeneratingPanel.add(svInterfaceCheckBox);

        showOneContentConfig(null, "COMMON_CODE_00033", codeGeneratingPanel, 0, true);

    }

    private JCheckBox newCheckBox(String text) {
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setMnemonic(KeyEvent.VK_C);
        checkBox.setSelected(true);
        return checkBox;
    }

    private void initPersistenceUnitConfig() {
        // 持久化单元配置
        showTitleConfig("COMMON_CODE_00020");

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

        // 使用自定义事物注解
        customTransactionalCheckBox = newCheckBox(FleaI18nHelper.i18nForCommon("COMMON_CODE_00034"));
        customTransactionalCheckBox.setSelected(false); // 默认补选中
        // 分库或分表场景，请勾选！
        customTransactionalCheckBox.setToolTipText(FleaI18nHelper.i18nForCommon("COMMON_CODE_00035"));

        configGridBagConstraints.gridwidth = 1;
        configGridBagLayout.setConstraints(puDaoClassLabel, configGridBagConstraints);
        configPanel.add(puDaoClassLabel);
        configGridBagConstraints.gridwidth = 2;
        configGridBagLayout.setConstraints(newRadioButton, configGridBagConstraints);
        configPanel.add(newRadioButton);
        configGridBagConstraints.gridwidth = 2;
        configGridBagLayout.setConstraints(oldRadioButton, configGridBagConstraints);
        configPanel.add(oldRadioButton);
        configGridBagConstraints.gridwidth = 2;
        configGridBagLayout.setConstraints(customTransactionalCheckBox, configGridBagConstraints);
        configPanel.add(customTransactionalCheckBox);
        configGridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; // end row
        JLabel radioLabel = new JLabel(" ");
        configGridBagLayout.setConstraints(radioLabel, configGridBagConstraints);
        configPanel.add(radioLabel);

        // 持久化单元DAO层实现类包名文本域
        puDaoPackageTextField = new JTextField();
        // 持久化单元DAO层实现类包名
        showOneContentConfig(null, "COMMON_CODE_00010", puDaoPackageTextField, 0, true);

        // 持久化单元DAO层实现类名
        puDAOClassNameLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00016") + ":", JLabel.RIGHT);
        // 持久化单元DAO层实现类名文本域
        puDaoClassNameTextField = new JTextField();
        showOneContentConfig(puDAOClassNameLabel, "", puDaoClassNameTextField, 0, true);

        // 持久化单元名
        persistenceUnitNameLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00014") + ":", JLabel.RIGHT);
        // 持久化单元名文本域
        puNameTextField = new JTextField();
        showOneContentConfig(persistenceUnitNameLabel, "", puNameTextField, 4, false);

        // 持久化单元别名 （单词首字母大写）
        persistenceUnitAliasNameLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00015") + ":", JLabel.RIGHT);
        // 持久化单元别名文本域
        puAliasNameTextField = new JTextField();
        showOneContentConfig(persistenceUnitAliasNameLabel, "", puAliasNameTextField, 0, true);

        // 初始化
        // 设置第一个单选按钮选中
        oldRadioButton.setSelected(true);
        persistenceUnitNameLabel.setVisible(false);
        puNameTextField.setVisible(false);
        persistenceUnitAliasNameLabel.setVisible(false);
        puAliasNameTextField.setVisible(false);
    }

    /**
     * <p> 处理配置标题展示 </p>
     *
     * @param titleNameKey 标题配置标签名称i18n key
     * @since 1.0.0
     */
    private void showTitleConfig(String titleNameKey) {

        JLabel configTitleLabel = new JLabel(FleaI18nHelper.i18nForCommon(titleNameKey), JLabel.LEFT);
        configTitleLabel.setFont(new Font("宋体", Font.PLAIN, 20)); // 字体

        configGridBagConstraints.weightx = 1.0;
        configGridBagConstraints.gridwidth = 1;
        configGridBagConstraints.insets = new Insets(5, 20, 2, 0);
        configGridBagLayout.setConstraints(configTitleLabel, configGridBagConstraints);
        configPanel.add(configTitleLabel);
        configGridBagConstraints.insets = new Insets(5, 5, 2, 0);
        addEmptyLabel();
        addEmptyLabel();
        addEmptyLabel();
        addEmptyLabel();
        addEmptyLabel();
        addEmptyLabel();
        addEmptyLabel();
        addEmptyLabel();
        configGridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; // end row
        addEmptyLabel();
    }

    /**
     * <p> 添加一个空标签【占位】 </p>
     */
    private void addEmptyLabel() {
        JLabel other = new JLabel(" ");
        configGridBagLayout.setConstraints(other, configGridBagConstraints);
        configPanel.add(other);
    }

    /**
     * <p> 展示一个内容配置  </p>
     *
     * @param labelNameKey 内容配置标签名称i18n key
     * @since 1.0.0
     */
    private void showOneContentConfig(JComponent labelJComponent, String labelNameKey, JComponent contentJComponent, int contentGridWidth, boolean isNewLine) {
        if (null == labelJComponent) {
            labelJComponent = new JLabel(FleaI18nHelper.i18nForCommon(labelNameKey) + ":", JLabel.RIGHT);
        }
        labelJComponent.setFont(new Font("宋体", Font.PLAIN, 15)); // 字体

        configGridBagConstraints.gridwidth = 1;
        configGridBagLayout.setConstraints(labelJComponent, configGridBagConstraints);
        configPanel.add(labelJComponent);
        if (isNewLine) {
            configGridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; // end row
        } else {
            configGridBagConstraints.gridwidth = contentGridWidth;
        }
        configGridBagLayout.setConstraints(contentJComponent, configGridBagConstraints);
        configPanel.add(contentJComponent);
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

        // 导入按钮
        importButton = new JButton(FleaI18nHelper.i18nForCommon("COMMON_CODE_00019"));
        importButton.addActionListener(this);
        buttonPanel.add(importButton);

        // 重置按钮
        resetButton = new JButton(FleaI18nHelper.i18nForCommon("COMMON_I18N_00007"));
        resetButton.addActionListener(this);
        buttonPanel.add(resetButton);

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
            } else if (allCheckBox == source) {
                if (allCheckBox.isSelected()) {
                    setCodeGeneratingCheckBoxSelected(true);
                } else {
                    setCodeGeneratingCheckBoxSelected(false);
                }
            }
        } catch (Exception e1) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Exception = {}", e1);
            }
            ToolsHelper.showTips(FleaI18nHelper.i18nForCommon("COMMON_I18N_00010"), e1.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        try {
            if (ItemEvent.DESELECTED == e.getStateChange()) {
                return;
            }
            Object source = e.getSource();
            if (idGeneratorStrategyComboBox == source) {
                Object dbSystemObj = dbSystemComboBox.getSelectedItem();
                Object idGeneratorStrategyObj = idGeneratorStrategyComboBox.getSelectedItem();
                if (ObjectUtils.isNotEmpty(dbSystemObj) && ObjectUtils.isNotEmpty(idGeneratorStrategyObj)) {
                    String dbSystemName = dbSystemObj.toString();
                    String idGeneratorStrategy = idGeneratorStrategyObj.toString();
                    if (ToolsConstants.CodeConstants.NONE.equals(idGeneratorStrategy)) {
                        setGenerationTypeTableVisible(false);
                    } else if (GenerationType.TABLE.name().equals(idGeneratorStrategy)) {
                        setGenerationTypeTableVisible(true);
                    } else if (GenerationType.IDENTITY.name().equals(idGeneratorStrategy)) {
                        if (DBSystemEnum.Oracle.getName().equals(dbSystemName)) {
                            // Oracle不支持GenerationType.IDENTITY
                            throw new Exception(FleaI18nHelper.i18nForCommon("COMMON_I18N_00017"));
                        }
                        setGenerationTypeTableVisible(false);
                    } else if (GenerationType.SEQUENCE.name().equals(idGeneratorStrategy)) {
                        if (DBSystemEnum.MySQL.getName().equals(dbSystemName)) {
                            // MySQL不支持GenerationType.SEQUENCE
                            throw new Exception(FleaI18nHelper.i18nForCommon("COMMON_I18N_00018"));
                        }
                        setGenerationTypeTableVisible(false);
                    }
                }
            }
        } catch (Exception e1) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Exception = {}", e1);
            }
            ToolsHelper.showTips(FleaI18nHelper.i18nForCommon("COMMON_I18N_00010"), e1.getMessage(), JOptionPane.ERROR_MESSAGE);
            idGeneratorStrategyComboBox.setSelectedIndex(0); // 重新选择TABLE
        }
    }

    private void setCodeGeneratingCheckBoxSelected(boolean isSelected) {
        daoImplCheckBox.setSelected(isSelected);
        daoInterfaceCheckBox.setSelected(isSelected);
        entityCheckBox.setSelected(isSelected);
        svImplCheckBox.setSelected(isSelected);
        svInterfaceCheckBox.setSelected(isSelected);
    }

    private void setGenerationTypeTableVisible(boolean isShow) {
        idGeneratorTableLabel.setVisible(isShow);
        idGeneratorTableTextField.setVisible(isShow);
        pkColumnNameLabel.setVisible(isShow);
        pkColumnNameTextField.setVisible(isShow);
        valueColumnNameLabel.setVisible(isShow);
        valueColumnNameTextField.setVisible(isShow);
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
        resetDBConfig();

        // 主键生成策略配置
        resetIDGeneratorStrategyConfig();

        // 代码配置
        resetCodeConfig();

        // 持久化单元配置
        resetPersistenceUnitConfig();
    }

    private void resetDBConfig() {
        dbSystemComboBox.setSelectedIndex(0);
        dbNameTextField.setText("");
        tableNameTextField.setText("");
        tableNameDescTextField.setText("");
    }

    private void resetIDGeneratorStrategyConfig() {
        idGeneratorStrategyComboBox.setSelectedIndex(0);
        setGenerationTypeTableVisible(true);
        idGeneratorTableTextField.setText("");
        pkColumnNameTextField.setText("");
        valueColumnNameTextField.setText("");
    }

    private void resetCodeConfig() {
        authorTextField.setText("");
        versionTextField.setText("");
        rootPathTextField.setText("");
        codePackageTextField.setText("");
    }

    private void resetPersistenceUnitConfig() {
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
        idGeneratorTableTextField.setText(config.getIdGeneratorTable());
        pkColumnNameTextField.setText(config.getPkColumnName());
        valueColumnNameTextField.setText(config.getValueColumnName());
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
        Map<String, Object> param = new HashMap<>();

        // 1. 数据库配置
        // 获取数据库系统名
        String dbSystemName = StringUtils.valueOf(dbSystemComboBox.getSelectedItem());
        param.put(ToolsConstants.CodeConstants.DB_SYSTEM_NAME, dbSystemName);
        // 获取数据库名
        String dbName = dbNameTextField.getText();
        // 大佬，请您先输入{0}内容哦~
        StringUtils.checkBlank(dbName, ToolsException.class, "COMMON_I18N_00013", FleaI18nHelper.i18nForCommon("COMMON_CODE_00003"));
        param.put(ToolsConstants.CodeConstants.DB_NAME, dbName);

        // 获取表名
        String tableName = tableNameTextField.getText();
        // 大佬，请您先输入{0}内容哦~
        StringUtils.checkBlank(tableName, ToolsException.class, "COMMON_I18N_00013", FleaI18nHelper.i18nForCommon("COMMON_CODE_00004"));
        param.put(ToolsConstants.CodeConstants.TABLE_NAME, tableName);

        // 获取表名描述
        String tableNameDesc = tableNameDescTextField.getText();
        // 大佬，请您先输入{0}内容哦~
        StringUtils.checkBlank(tableNameDesc, ToolsException.class, "COMMON_I18N_00013", FleaI18nHelper.i18nForCommon("COMMON_CODE_00005"));
        param.put(ToolsConstants.CodeConstants.TABLE_DESC, tableNameDesc);

        // 2主键生成策略配置
        // 获取主键生成策略
        String idGeneratorStrategy = StringUtils.valueOf(idGeneratorStrategyComboBox.getSelectedItem());
        param.put(ToolsConstants.CodeConstants.ID_GENERATOR_STRATEGY, idGeneratorStrategy);
        // 获取主键生成器表
        String idGeneratorTable = idGeneratorTableTextField.getText();
        if (StringUtils.isNotBlank(idGeneratorTable)) {
            param.put(ToolsConstants.CodeConstants.ID_GENERATOR_TABLE, idGeneratorTable);
        }
        // 获取主键列名
        String pkColumnName = pkColumnNameTextField.getText();
        if (StringUtils.isNotBlank(pkColumnName)) {
            param.put(ToolsConstants.CodeConstants.PK_COLUMN_NAME, pkColumnName);
        }
        // 获取主键值列名
        String valueColumnName = valueColumnNameTextField.getText();
        if (StringUtils.isNotBlank(valueColumnName)) {
            param.put(ToolsConstants.CodeConstants.VALUE_COLUMN_NAME, valueColumnName);
        }

        // 3. 代码配置
        // 获取作者
        String author = authorTextField.getText();
        // 大佬，请您先输入{0}内容哦~
        StringUtils.checkBlank(author, ToolsException.class, "COMMON_I18N_00013", FleaI18nHelper.i18nForCommon("COMMON_CODE_00007"));
        param.put(ToolsConstants.CodeConstants.AUTHOR, author);

        // 获取版本
        String version = versionTextField.getText();
        // 大佬，请您先输入{0}内容哦~
        StringUtils.checkBlank(version, ToolsException.class, "COMMON_I18N_00013", FleaI18nHelper.i18nForCommon("COMMON_CODE_00008"));
        param.put(ToolsConstants.CodeConstants.VERSION, version);

        // 是否选中 "实体类使用Lombok"
        boolean isSelected = lombokEntityCheckBox.isSelected();
        param.put(ToolsConstants.CodeConstants.IS_SELECTED_LOMBOK, isSelected);

        // 获取根目录
        String rootPath = rootPathTextField.getText();
        // 大佬，请您先输入{0}内容哦~
        StringUtils.checkBlank(rootPath, ToolsException.class, "COMMON_I18N_00013", FleaI18nHelper.i18nForCommon("COMMON_CODE_00009"));
        param.put(ToolsConstants.CodeConstants.ROOT_PACKAGE, rootPath);

        // 获取代码包名
        String codePackage = codePackageTextField.getText();
        // 大佬，请您先输入{0}内容哦~
        StringUtils.checkBlank(codePackage, ToolsException.class, "COMMON_I18N_00013", FleaI18nHelper.i18nForCommon("COMMON_CODE_00010"));
        param.put(ToolsConstants.CodeConstants.CODE_PACKAGE, codePackage);

        // 待生成代码类
        boolean isSelectedDaoImpl = daoImplCheckBox.isSelected();
        param.put(ToolsConstants.CodeConstants.IS_SELECTED_DAO_IMPL, isSelectedDaoImpl);
        boolean isSelectedDaoInterface = daoInterfaceCheckBox.isSelected();
        param.put(ToolsConstants.CodeConstants.IS_SELECTED_DAO_INTERFACE, isSelectedDaoInterface);
        boolean isSelectedEntity = entityCheckBox.isSelected();
        param.put(ToolsConstants.CodeConstants.IS_SELECTED_ENTITY, isSelectedEntity);
        boolean isSelectedSVImpl = svImplCheckBox.isSelected();
        param.put(ToolsConstants.CodeConstants.IS_SELECTED_SV_IMPL, isSelectedSVImpl);
        boolean isSelectedSVInterface = svInterfaceCheckBox.isSelected();
        param.put(ToolsConstants.CodeConstants.IS_SELECTED_SV_INTERFACE, isSelectedSVInterface);

        // 4. 持久化单元配置
        String puDaoClassPackage = puDaoPackageTextField.getText();
        // 大佬，请您先输入{0}内容哦~
        StringUtils.checkBlank(puDaoClassPackage, ToolsException.class, "COMMON_I18N_00013", FleaI18nHelper.i18nForCommon("COMMON_CODE_00011") + FleaI18nHelper.i18nForCommon("COMMON_CODE_00010"));
        param.put(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_DAO_CLASS_PACKAGE, puDaoClassPackage);

        // 获取单选按钮
        if (btnGroup.isSelected(newRadioButton.getModel())) { // 选择新建
            // 持久化单元名
            String fleaPersistenceUnitName = puNameTextField.getText();
            // 大佬，请您先输入{0}内容哦~
            StringUtils.checkBlank(fleaPersistenceUnitName, ToolsException.class, "COMMON_I18N_00013", FleaI18nHelper.i18nForCommon("COMMON_CODE_00014"));
            param.put(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_NAME, fleaPersistenceUnitName);

            // 持久化单元别名
            String fleaPersistenceUnitAliasName = puAliasNameTextField.getText();
            // 大佬，请您先输入{0}内容哦~
            StringUtils.checkBlank(fleaPersistenceUnitAliasName, ToolsException.class, "COMMON_I18N_00013", FleaI18nHelper.i18nForCommon("COMMON_CODE_00015"));
            param.put(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_ALIAS_NAME, fleaPersistenceUnitAliasName);
        } else { // 选择现有
            // 持久化单元DAO层实现类名
            String fleaPersistenceUnitDaoClassName = puDaoClassNameTextField.getText();
            // 大佬，请您先输入{0}内容哦~
            StringUtils.checkBlank(fleaPersistenceUnitDaoClassName, ToolsException.class, "COMMON_I18N_00013", FleaI18nHelper.i18nForCommon("COMMON_CODE_00016"));
            param.put(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_DAO_CLASS_NAME, fleaPersistenceUnitDaoClassName);
        }

        // 是否使用自定义事物注解
        boolean isUseFleaTransactional = customTransactionalCheckBox.isSelected();
        param.put(ToolsConstants.CodeConstants.IS_SELECTED_CUSTOM_TRANSACTIONAL, isUseFleaTransactional);

        return param;
    }

}
