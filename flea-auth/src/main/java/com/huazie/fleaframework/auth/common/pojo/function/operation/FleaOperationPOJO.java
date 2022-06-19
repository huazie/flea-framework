package com.huazie.fleaframework.auth.common.pojo.function.operation;

import com.huazie.fleaframework.auth.common.pojo.function.attr.FleaFunctionOtherPOJO;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Flea操作POJO类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaOperationPOJO extends FleaFunctionOtherPOJO {

    private static final long serialVersionUID = 3045124491855439966L;

    private String operationCode; // 操作编码

    private String operationName; // 操作名称

    private String operationDesc; // 操作描述

    public String getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getOperationDesc() {
        return operationDesc;
    }

    public void setOperationDesc(String operationDesc) {
        this.operationDesc = operationDesc;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
