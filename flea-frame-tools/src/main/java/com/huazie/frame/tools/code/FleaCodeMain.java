package com.huazie.frame.tools.code;

import com.huazie.frame.tools.common.ToolsConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> 数据操作和业务逻辑层实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaCodeMain {

    private static String author = "huazie";
    private static String version = "1.0.0";
    private static String rootPackage = "E:\\fleaworkspace\\flea-frame\\flea-frame-auth\\src\\main\\java";

    private static String codePackage = "com.huazie.frame.auth.base.user";
    private static String fleaPersistenceUnitDaoClassPackage = "com.huazie.frame.auth.base.FleaAuthDaoImpl";
    private static String tableName = "flea_user";
    private static String tableDesc = "Flea用户";

    private static String dbSystemName = "MySQL";
    private static String dbName = "fleaauth";

    public void code() {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(ToolsConstants.CodeConstants.AUTHOR, author);
        param.put(ToolsConstants.CodeConstants.VERSION, version);

        param.put(ToolsConstants.CodeConstants.ROOT_PACKAGE, rootPackage);
        param.put(ToolsConstants.CodeConstants.CODE_PACKAGE, codePackage);
        param.put(ToolsConstants.CodeConstants.FLEA_PERSISTENCE_UNIT_DAO_CLASS_PACKAGE, fleaPersistenceUnitDaoClassPackage);
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
        param.put(ToolsConstants.CodeConstants.TABLE_NAME, tableName);

        // 清理代码
        FleaCodeProgrammer.clean(param);
    }

}
