package com.huazie.fleaframework.auth.common.pojo.function.attr;

import com.huazie.fleaframework.auth.common.pojo.FleaAttrPOJO;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Flea功能扩展属性POJO类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaFunctionAttrPOJO extends FleaAttrPOJO {

    private static final long serialVersionUID = -2198373443495464732L;

    private Long attrId; // 属性编号【修改功能属性时填写】

    private Long functionId; // 功能编号

    private String functionType; // 功能类型

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public Long getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }

    public String getFunctionType() {
        return functionType;
    }

    public void setFunctionType(String functionType) {
        this.functionType = functionType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
