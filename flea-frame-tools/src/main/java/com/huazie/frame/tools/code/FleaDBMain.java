package com.huazie.frame.tools.code;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> 数据操作和业务逻辑层实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaDBMain {

    private static String rootPackage = "F:\\FileRecv\\project\\flea-frame\\flea-frame-auth";

    private static String codePackage = "com.huazie.frame.auth";
    private static String fleaPersistenceUnitDaoClassPackage = "com.huazie.frame.auth.base.FleaAuthDaoImpl";
    private static String tableName = "flea_real_name_info";
    private static String tableDesc = "Flea用户实名信息";

    public static void main(String[] args) throws Exception {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("ROOT_PACKAGE", rootPackage);
        param.put("CODE_PACKAGE", codePackage);
        param.put("FLEA_PERSISTENCE_UNIT_DAO_CLASS_PACKAGE", fleaPersistenceUnitDaoClassPackage);
        param.put("TABLE_NAME", tableName);
        param.put("TABLE_DESC", tableDesc);
        // 编写代码
        FleaCodeProgramer.code(param);
    }

}
