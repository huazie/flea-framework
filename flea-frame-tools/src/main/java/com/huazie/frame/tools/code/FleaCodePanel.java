package com.huazie.frame.tools.code;

import com.huazie.frame.common.i18n.FleaI18nHelper;
import com.huazie.frame.db.common.DBSystemEnum;
import com.huazie.frame.tools.common.ToolsConstants;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> 数据操作和业务逻辑层实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaCodePanel extends JPanel implements ActionListener, ItemListener {

    private static String author = "huazie";
    private static String version = "1.0.0";
    private static String rootPackage = "E:\\fleaworkspace\\flea-frame\\flea-frame-auth\\src\\main\\java";
    //private static String rootPackage = "F:\\FileRecv\\project\\flea-frame\\flea-frame-auth\\src\\main\\java";

    private static String codePackage = "com.huazie.frame.auth.base.user";
    private static String fleaPersistenceUnitDaoClassPackage = "com.huazie.frame.auth.base";
    private static String fleaPersistenceUnitDaoClassName = "FleaAuthDAOImpl";
    private static String fleaPersistenceUnitName = "fleaauth";
    private static String fleaPersistenceUnitAliasName = "FleaAuth";
    private static String tableName = "flea_account_attr";
    private static String tableDesc = "Flea账户属性";

    private static String dbSystemName = "MySQL";
    private static String dbName = "fleaauth";

    private JTextField dbNameTextField; // 数据库名

    private JTextField tableNameTextField; // 表名

    private JTextField tableNameDescTextField; // 表名描述

    public FleaCodePanel() {
        init();
    }

    private void init() {

        setLayout(new GridLayout(4, 1, 20, 20));

        initDBConfig();

        initCodeConfig();

    }

    private void initDBConfig() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        JPanel dbConfigPanel = new JPanel(gridBagLayout);
        dbConfigPanel.setBorder(new EmptyBorder(5, 5, 5, 5));// 设置面板的边框

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        JLabel dbConfigLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00001"));
        dbConfigLabel.setFont(new Font("宋体", Font.PLAIN, 20)); // 字体

        // 数据库系统
        JLabel dbSystemLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00002") + ":");
        dbSystemLabel.setFont(new Font("宋体", Font.PLAIN, 13)); // 字体
        JComboBox<String> mComboBox = new JComboBox<String>();
        mComboBox.addItem(DBSystemEnum.MySQL.getName());
        mComboBox.addItem(DBSystemEnum.Oracle.getName());
        mComboBox.addItemListener(this);

        // 数据库名
        JLabel dbNameLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00003") + ":");
        dbNameLabel.setFont(new Font("宋体", Font.PLAIN, 13)); // 字体
        dbNameTextField = new JTextField();

        // 表名
        JLabel tableNameLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00004") + ":");
        tableNameLabel.setFont(new Font("宋体", Font.PLAIN, 13)); // 字体
        tableNameTextField = new JTextField();

        // 表名描述
        JLabel tableNameDescLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00005") + ":");
        tableNameDescLabel.setFont(new Font("宋体", Font.PLAIN, 13)); // 字体
        tableNameDescTextField = new JTextField();

        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(5,2,5,2);
        gridBagLayout.setConstraints(dbConfigLabel, gridBagConstraints);
        dbConfigPanel.add(dbConfigLabel);
        JLabel other1 = new JLabel(" ");
        gridBagLayout.setConstraints(other1, gridBagConstraints);
        dbConfigPanel.add(other1);
        JLabel other2 = new JLabel(" ");
        gridBagLayout.setConstraints(other2, gridBagConstraints);
        dbConfigPanel.add(other2);
        JLabel other3 = new JLabel(" ");
        gridBagLayout.setConstraints(other3, gridBagConstraints);
        dbConfigPanel.add(other3);
        JLabel other4 = new JLabel(" ");
        gridBagLayout.setConstraints(other4, gridBagConstraints);
        dbConfigPanel.add(other4);
        JLabel other5 = new JLabel(" ");
        gridBagLayout.setConstraints(other5, gridBagConstraints);
        dbConfigPanel.add(other5);
        JLabel other6 = new JLabel(" ");
        gridBagLayout.setConstraints(other6, gridBagConstraints);
        dbConfigPanel.add(other6);
        JLabel other7 = new JLabel(" ");
        gridBagLayout.setConstraints(other7, gridBagConstraints);
        dbConfigPanel.add(other7);
        JLabel other8 = new JLabel(" ");
        gridBagLayout.setConstraints(other8, gridBagConstraints);
        dbConfigPanel.add(other8);
        JLabel other9 = new JLabel(" ");
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; // end row
        gridBagLayout.setConstraints(other9, gridBagConstraints);
        dbConfigPanel.add(other9);

        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(5,0,5,0);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 1;
        gridBagLayout.setConstraints(dbSystemLabel, gridBagConstraints);
        dbConfigPanel.add(dbSystemLabel);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridwidth = 4;
        gridBagLayout.setConstraints(mComboBox, gridBagConstraints);
        dbConfigPanel.add(mComboBox);
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.gridwidth = 1;
        gridBagLayout.setConstraints(dbNameLabel, gridBagConstraints);
        dbConfigPanel.add(dbNameLabel);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; // end row
        gridBagLayout.setConstraints(dbNameTextField, gridBagConstraints);
        dbConfigPanel.add(dbNameTextField);

        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.gridwidth = 1;
        gridBagLayout.setConstraints(tableNameLabel, gridBagConstraints);
        dbConfigPanel.add(tableNameLabel);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridwidth = 4;
        gridBagLayout.setConstraints(tableNameTextField, gridBagConstraints);
        dbConfigPanel.add(tableNameTextField);
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.gridwidth = 1;
        gridBagLayout.setConstraints(tableNameDescLabel, gridBagConstraints);
        dbConfigPanel.add(tableNameDescLabel);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; // end row
        gridBagLayout.setConstraints(tableNameDescTextField, gridBagConstraints);
        dbConfigPanel.add(tableNameDescTextField);
        add(dbConfigPanel);
    }

    private void initCodeConfig() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        JPanel dbConfigPanel = new JPanel(gridBagLayout);
        dbConfigPanel.setBorder(new EmptyBorder(5, 5, 5, 5));// 设置面板的边框

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        JLabel dbConfigLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00006"));
        dbConfigLabel.setFont(new Font("宋体", Font.PLAIN, 20)); // 字体

        // 作者
        JLabel authorLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00007") + ":");
        authorLabel.setFont(new Font("宋体", Font.PLAIN, 13)); // 字体
        JComboBox<String> mComboBox = new JComboBox<String>();
        mComboBox.addItem(DBSystemEnum.MySQL.getName());
        mComboBox.addItem(DBSystemEnum.Oracle.getName());
        mComboBox.addItemListener(this);

        // 数据库名
        JLabel dbNameLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00003") + ":");
        dbNameLabel.setFont(new Font("宋体", Font.PLAIN, 13)); // 字体
        dbNameTextField = new JTextField();

        // 表名
        JLabel tableNameLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00004") + ":");
        tableNameLabel.setFont(new Font("宋体", Font.PLAIN, 13)); // 字体
        tableNameTextField = new JTextField();

        // 表名描述
        JLabel tableNameDescLabel = new JLabel(FleaI18nHelper.i18nForCommon("COMMON_CODE_00005") + ":");
        tableNameDescLabel.setFont(new Font("宋体", Font.PLAIN, 13)); // 字体
        tableNameDescTextField = new JTextField();

        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(5,2,5,2);
        gridBagLayout.setConstraints(dbConfigLabel, gridBagConstraints);
        dbConfigPanel.add(dbConfigLabel);
        JLabel other1 = new JLabel(" ");
        gridBagLayout.setConstraints(other1, gridBagConstraints);
        dbConfigPanel.add(other1);
        JLabel other2 = new JLabel(" ");
        gridBagLayout.setConstraints(other2, gridBagConstraints);
        dbConfigPanel.add(other2);
        JLabel other3 = new JLabel(" ");
        gridBagLayout.setConstraints(other3, gridBagConstraints);
        dbConfigPanel.add(other3);
        JLabel other4 = new JLabel(" ");
        gridBagLayout.setConstraints(other4, gridBagConstraints);
        dbConfigPanel.add(other4);
        JLabel other5 = new JLabel(" ");
        gridBagLayout.setConstraints(other5, gridBagConstraints);
        dbConfigPanel.add(other5);
        JLabel other6 = new JLabel(" ");
        gridBagLayout.setConstraints(other6, gridBagConstraints);
        dbConfigPanel.add(other6);
        JLabel other7 = new JLabel(" ");
        gridBagLayout.setConstraints(other7, gridBagConstraints);
        dbConfigPanel.add(other7);
        JLabel other8 = new JLabel(" ");
        gridBagLayout.setConstraints(other8, gridBagConstraints);
        dbConfigPanel.add(other8);
        JLabel other9 = new JLabel(" ");
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; // end row
        gridBagLayout.setConstraints(other9, gridBagConstraints);
        dbConfigPanel.add(other9);

        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(5,0,5,0);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 1;
        gridBagLayout.setConstraints(authorLabel, gridBagConstraints);
        dbConfigPanel.add(authorLabel);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridwidth = 4;
        gridBagLayout.setConstraints(mComboBox, gridBagConstraints);
        dbConfigPanel.add(mComboBox);
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.gridwidth = 1;
        gridBagLayout.setConstraints(dbNameLabel, gridBagConstraints);
        dbConfigPanel.add(dbNameLabel);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; // end row
        gridBagLayout.setConstraints(dbNameTextField, gridBagConstraints);
        dbConfigPanel.add(dbNameTextField);

        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.gridwidth = 1;
        gridBagLayout.setConstraints(tableNameLabel, gridBagConstraints);
        dbConfigPanel.add(tableNameLabel);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridwidth = 4;
        gridBagLayout.setConstraints(tableNameTextField, gridBagConstraints);
        dbConfigPanel.add(tableNameTextField);
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.gridwidth = 1;
        gridBagLayout.setConstraints(tableNameDescLabel, gridBagConstraints);
        dbConfigPanel.add(tableNameDescLabel);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; // end row
        gridBagLayout.setConstraints(tableNameDescTextField, gridBagConstraints);
        dbConfigPanel.add(tableNameDescTextField);
        add(dbConfigPanel);
    }

    public void code() {
        // 编写代码
        FleaCodeProgrammer.code(createParamMap());
    }

    public void clean() {
        // 清理代码
        FleaCodeProgrammer.clean(createParamMap());
    }

    private Map<String, Object> createParamMap() {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(ToolsConstants.CodeConstants.AUTHOR, author);
        param.put(ToolsConstants.CodeConstants.VERSION, version);

        param.put(ToolsConstants.CodeConstants.ROOT_PACKAGE, rootPackage);
        param.put(ToolsConstants.CodeConstants.CODE_PACKAGE, codePackage);
        param.put(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_DAO_CLASS_PACKAGE, fleaPersistenceUnitDaoClassPackage);
        // 1. 现有 持久化单元DAO层实现类
        param.put(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_DAO_CLASS_NAME, fleaPersistenceUnitDaoClassName);
        // 2. 新增 持久化单元DAO层实现类
//        param.put(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_NAME, fleaPersistenceUnitName);
//        param.put(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_ALIAS_NAME, fleaPersistenceUnitAliasName);

        param.put(ToolsConstants.CodeConstants.TABLE_NAME, tableName);
        param.put(ToolsConstants.CodeConstants.TABLE_DESC, tableDesc);

        param.put(ToolsConstants.CodeConstants.DB_SYSTEM_NAME, dbSystemName);
        param.put(ToolsConstants.CodeConstants.DB_NAME, dbName);
        return param;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
