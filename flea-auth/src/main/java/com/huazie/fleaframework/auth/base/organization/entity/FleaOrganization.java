package com.huazie.fleaframework.auth.base.organization.entity;

import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.FleaEntity;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
 * Flea组织表对应的实体类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@Entity
@Table(name = "flea_organization")
public class FleaOrganization extends FleaEntity {

    private static final long serialVersionUID = 5839217465028391745L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FLEA_ORGANIZATION_GENERATOR")
    @TableGenerator(
        // 唯一的生成器名称，可以由一个或多个类引用以作为id值的生成器。
        name = "FLEA_ORGANIZATION_GENERATOR",
        // 存储生成的ID值的表的名称
        table = "flea_id_generator",
        // 表中主键列的名称
        pkColumnName = "id_generator_key",
        // 存储最后生成的主键值的列的名称
        valueColumnName = "id_generator_value",
        // ID生成器表中的主键值模板，用于将该生成值集与其他可能存储在表中的值区分开
        pkColumnValue = "pk_flea_organization",
        // 从ID生成器表中分配ID号时增加的数量
        allocationSize = 1
    )
    @Column(name = "org_id", unique = true, nullable = false)
    private Long orgId; // 组织编号

    @Column(name = "org_code", nullable = false)
    private String orgCode; // 组织编码

    @Column(name = "org_name", nullable = false)
    private String orgName; // 组织名称

    @Column(name = "org_desc")
    private String orgDesc; // 组织描述

    @Column(name = "parent_id", nullable = false)
    private Long parentId; // 父组织编号(-1 表示根)

    @Column(name = "org_level", nullable = false)
    private Integer orgLevel; // 组织层级(根=1)

    @Column(name = "org_type", nullable = false)
    private Integer orgType; // 组织类型(1:公司 2:部门 3:小组)

    @Column(name = "org_state", nullable = false)
    private Integer orgState; // 组织状态(0: 删除 1: 正常)

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate; // 创建日期

    @Column(name = "done_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date doneDate; // 修改日期

    @Column(name = "remarks")
    private String remarks; // 备注信息

    /**
     * 无参数构造方法
     *
     * @since 2.0.0
     */
    public FleaOrganization() {
    }

    /**
     * 带参数构造方法
     *
     * @param orgCode  组织编码
     * @param orgName  组织名称
     * @param orgDesc  组织描述
     * @param parentId 父组织编号
     * @param orgLevel 组织层级
     * @param orgType  组织类型
     * @param remarks  备注
     * @since 2.0.0
     */
    public FleaOrganization(String orgCode, String orgName, String orgDesc, Long parentId, Integer orgLevel, Integer orgType, String remarks) {
        this.orgCode = orgCode;
        this.orgName = orgName;
        this.orgDesc = orgDesc;
        if (ObjectUtils.isEmpty(parentId)) {
            parentId = CommonConstants.NumeralConstants.MINUS_ONE;
        }
        this.parentId = parentId;
        this.orgLevel = orgLevel;
        this.orgType = orgType;
        this.orgState = EntityStateEnum.IN_USE.getState();
        this.createDate = DateUtils.getCurrentTime();
        this.remarks = remarks;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgDesc() {
        return orgDesc;
    }

    public void setOrgDesc(String orgDesc) {
        this.orgDesc = orgDesc;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(Integer orgLevel) {
        this.orgLevel = orgLevel;
    }

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }

    public Integer getOrgState() {
        return orgState;
    }

    public void setOrgState(Integer orgState) {
        this.orgState = orgState;
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
