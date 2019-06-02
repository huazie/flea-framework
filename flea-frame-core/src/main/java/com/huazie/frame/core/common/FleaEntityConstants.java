package com.huazie.frame.core.common;

/**
 * <p> 实体类常量 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface FleaEntityConstants {

    interface FleaParaDetailConstants {
        String S_PARA_ID = "paraId";
        String S_PARA_TYPE = "paraType";
        String S_PARA_CODE = "paraCode";
        String S_PARA_NAME = "paraName";
        String S_PARA1 = "para1";
        String S_PARA2 = "para2";
        String S_PARA3 = "para3";
        String S_PARA4 = "para4";
        String S_PARA5 = "para5";
        String S_PARA_STATE = "paraState";
        String S_PARA_DESC = "paraDesc";

        int PARA_STATE_BE_DELETED = 0;    // 删除
        int PARA_STATE_IN_USE = 1;        // 在用
    }

}
