package com.huazie.fleaframework.auth.base.function.entity;

import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.FleaEntity;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Flea元素表对应的实体类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "flea_element")
public class FleaElement extends FleaEntity {

    private static final long serialVersionUID = -2622976696060173561L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FLEA_ELEMENT_GENERATOR")
    @TableGenerator(
        // 唯一的生成器名称，可以由一个或多个类引用以作为id值的生成器。
        name = "FLEA_ELEMENT_GENERATOR",
        // 存储生成的ID值的表的名称
        table = "flea_id_generator",
        // 表中主键列的名称
        pkColumnName = "id_generator_key",
        // 存储最后生成的主键值的列的名称
        valueColumnName = "id_generator_value",
        // ID生成器表中的主键值模板，用于将该生成值集与其他可能存储在表中的值区分开
        pkColumnValue = "pk_flea_element",
        // 从ID生成器表中分配ID号时增加的数量
        allocationSize = 1
    )
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
    private Integer elementState; // 元素状态(0: 删除 1: 正常)

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate; // 创建日期

    @Column(name = "done_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date doneDate; // 修改日期

    @Column(name = "effective_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectiveDate; // 生效日期

    @Column(name = "expiry_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate; // 失效日期

    @Column(name = "remarks")
    private String remarks; // 备注信息

    /**
     * 无参数构造方法
     *
     * @since 2.0.0
     */
    public FleaElement() {
    }

    /**
     * 带参数构造方法
     *
     * @param elementCode    元素编码
     * @param elementName    元素名称
     * @param elementDesc    元素描述
     * @param elementType    元素类型
     * @param elementContent 元素内容
     * @param effectiveDate  生效日期
     * @param expiryDate     失效日期
     * @param remarks        备注信息
     * @since 2.0.0
     */
    public FleaElement(String elementCode, String elementName, String elementDesc, Integer elementType, String elementContent, Date effectiveDate, Date expiryDate, String remarks) {
        this.elementCode = elementCode;
        this.elementName = elementName;
        this.elementDesc = elementDesc;
        this.elementType = elementType;
        this.elementContent = elementContent;
        this.elementState = EntityStateEnum.IN_USE.getState();
        this.createDate = DateUtils.getCurrentTime();
        if (ObjectUtils.isEmpty(effectiveDate)) {
            effectiveDate = createDate;
        }
        this.effectiveDate = effectiveDate;
        if (ObjectUtils.isEmpty(expiryDate)) {
            expiryDate = DateUtils.getExpiryTimeForever();
        }
        this.expiryDate = expiryDate;
        this.remarks = remarks;
    }

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

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
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