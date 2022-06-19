package com.huazie.fleaframework.auth.common.pojo.function.element;

import com.huazie.fleaframework.auth.common.pojo.function.attr.FleaFunctionOtherPOJO;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Flea元素POJO类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaElementPOJO extends FleaFunctionOtherPOJO {

    private static final long serialVersionUID = -2174547430981793344L;

    private String elementCode; // 元素编码

    private String elementName; // 元素名称

    private String elementDesc; // 元素描述

    private Integer elementType; // 元素类型

    private String elementContent; // 元素内容

    public String getElementCode() {
        return elementCode;
    }

    public void setElementCode(String elementCode) {
        this.elementCode = elementCode;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getElementDesc() {
        return elementDesc;
    }

    public void setElementDesc(String elementDesc) {
        this.elementDesc = elementDesc;
    }

    public Integer getElementType() {
        return elementType;
    }

    public void setElementType(Integer elementType) {
        this.elementType = elementType;
    }

    public String getElementContent() {
        return elementContent;
    }

    public void setElementContent(String elementContent) {
        this.elementContent = elementContent;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
