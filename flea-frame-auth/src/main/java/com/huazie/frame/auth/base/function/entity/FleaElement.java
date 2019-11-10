package com.huazie.frame.auth.base.function.entity;

import com.huazie.frame.common.FleaEntity;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * <p> Flea元素表对应的实体类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "flea_element")
public class FleaElement extends FleaEntity {

    private static final long serialVersionUID = -2622976696060173561L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "FLEA_ELEMENT_SEQ")
    @SequenceGenerator(name = "FLEA_ELEMENT_SEQ")
    @Column(name = "element_id", unique = true, nullable = false)
    private Long elementId; // 元素编号

    @Column(name = "element_code", nullable = false)
    private String elementCode; // 元素编码

    @Column(name = "element_name", nullable = false)
    private String elementName; // 元素名称

    @Column(name = "element_desc")
    private String elementDesc; // 元素描述

    @Column(name = "element_type", nullable = false)
    private Integer elementType; // 元素类型

    @Column(name = "element_content")
    private String elementContent; // 元素内容

    @Column(name = "element_state", nullable = false)
    private Integer elementState; // 元素状态

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate; // 创建日期

    @Column(name = "done_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date doneDate; // 修改日期

    @Column(name = "remarks")
    private String remarks; // 备注信息

    public Long getElementId() {
        return elementId;
    }

    public void setElementId(Long elementId) {
        this.elementId = elementId;
    }

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

    public Integer getElementState() {
        return elementState;
    }

    public void setElementState(Integer elementState) {
        this.elementState = elementState;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(Date doneDate) {
        this.doneDate = doneDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}