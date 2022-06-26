package com.huazie.fleaframework.common.pojo;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 定义公用的返回数据
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class OutputCommonData implements Serializable {

    private static final long serialVersionUID = -9098279075924276663L;

    private String retCode; // 返回码
    private String retMess; // 返回信息

    public OutputCommonData() {
    }

    /**
     * 带参数的构造方法
     *
     * @param retCode 返回码
     * @param retMess 返回信息
     * @since 1.0.0
     */
    public OutputCommonData(String retCode, String retMess) {
        this.retCode = retCode;
        this.retMess = retMess;
    }

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
