package com.huazie.frame.common.pojo;

import java.io.Serializable;

/**
 * <p>定义公用的返回数据</p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class OutputCommonData implements Serializable {

    private static final long serialVersionUID = -9098279075924276663L;

    private String retCode; // 返回码
    private String retMess; // 返回信息

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMess() {
        return retMess;
    }

    public void setRetMess(String retMess) {
        this.retMess = retMess;
    }

}
