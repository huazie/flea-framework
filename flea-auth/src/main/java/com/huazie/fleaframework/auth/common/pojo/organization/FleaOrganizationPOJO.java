package com.huazie.fleaframework.auth.common.pojo.organization;

import com.huazie.fleaframework.common.pojo.FleaRemarksPOJO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Flea组织POJO类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaOrganizationPOJO extends FleaRemarksPOJO {

    private static final long serialVersionUID = 7356902148532174608L;

    private String orgCode; // 组织编码

    private String orgName; // 组织名称

    private String orgDesc; // 组织描述

    private Integer orgType; // 组织类型(1:公司 2:部门 3:小组)

    private Long parentId; // 父组织编号(-1 表示根)

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

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
