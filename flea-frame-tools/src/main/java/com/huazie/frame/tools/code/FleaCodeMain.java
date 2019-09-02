package com.huazie.frame.tools.code;

import com.huazie.frame.common.i18n.FleaI18nHelper;
import com.huazie.frame.tools.common.ToolsConstants;

import javax.swing.JFrame;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> 数据操作和业务逻辑层实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaCodeMain extends JFrame {

    private static String author = "huazie";
    private static String version = "1.0.0";
    //private static String rootPackage = "E:\\fleaworkspace\\flea-frame\\flea-frame-auth\\src\\main\\java";
    private static String rootPackage = "F:\\FileRecv\\project\\flea-frame\\flea-frame-auth\\src\\main\\java";

    private static String codePackage = "com.huazie.frame.auth.base.user";
    private static String fleaPersistenceUnitDaoClassPackage = "com.huazie.frame.auth.base";
//    private static String fleaPersistenceUnitDaoClassName = "FleaAuthDAOImpl";
    private static String fleaPersistenceUnitName = "fleaauth";
    private static String fleaPersistenceUnitAliasName = "FleaAuth";
    private static String tableName = "flea_account_attr";
    private static String tableDesc = "Flea账户属性";

    private static String dbSystemName = "MySQL";
    private static String dbName = "fleaauth";

    public FleaCodeMain() {
        super(FleaI18nHelper.i18nForCommon("COMMON_I18N_00000"));
        init();
    }

    private void init() {

    }

    public void code() {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(ToolsConstants.CodeConstants.AUTHOR, author);
        param.put(ToolsConstants.CodeConstants.VERSION, version);

        param.put(ToolsConstants.CodeConstants.ROOT_PACKAGE, rootPackage);
        param.put(ToolsConstants.CodeConstants.CODE_PACKAGE, codePackage);
        param.put(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_DAO_CLASS_PACKAGE, fleaPersistenceUnitDaoClassPackage);
        // 1. 现有 持久化单元DAO层实现类
//        param.put(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_DAO_CLASS_NAME, fleaPersistenceUnitDaoClassName);
        // 2. 新增 持久化单元DAO层实现类
        param.put(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_NAME, fleaPersistenceUnitName);
        param.put(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_ALIAS_NAME, fleaPersistenceUnitAliasName);

        param.put(ToolsConstants.CodeConstants.TABLE_NAME, tableName);
        param.put(ToolsConstants.CodeConstants.TABLE_DESC, tableDesc);

        param.put(ToolsConstants.CodeConstants.DB_SYSTEM_NAME, dbSystemName);
        param.put(ToolsConstants.CodeConstants.DB_NAME, dbName);

        // 编写代码
        FleaCodeProgrammer.code(param);
    }

    public void clean() {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(ToolsConstants.CodeConstants.ROOT_PACKAGE, rootPackage);
        param.put(ToolsConstants.CodeConstants.CODE_PACKAGE, codePackage);
        param.put(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_DAO_CLASS_PACKAGE, fleaPersistenceUnitDaoClassPackage);
        // 1. 现有 持久化单元DAO层实现类
//        param.put(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_DAO_CLASS_NAME, fleaPersistenceUnitDaoClassName);
        // 2. 新增 持久化单元DAO层实现类
        param.put(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_NAME, fleaPersistenceUnitName);
        param.put(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_ALIAS_NAME, fleaPersistenceUnitAliasName);
        param.put(ToolsConstants.CodeConstants.TABLE_NAME, tableName);

        // 清理代码
        FleaCodeProgrammer.clean(param);
    }

}
