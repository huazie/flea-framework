package com.huazie.frame.auth.base.user.entity;

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
 * <p> Flea用户组表对应的实体类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "flea_user_group")
public class FleaUserGroup extends FleaEntity {

    private static final long serialVersionUID = -7561778222152504449L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "FLEA_USER_GROUP_SEQ")
    @SequenceGenerator(name = "FLEA_USER_GROUP_SEQ")
    @Column(name = "user_group_id", unique = true, nullable = false)
    private Long userGroupId; // 用户组编号

    @Column(name = "user_group_name", nullable = false)
    private String userGroupName; // 用户组名

    @Column(name = "user_group_desc")
    private String userGroupDesc; // 用户组描述

    @Column(name = "user_group_state", nullable = false)
    private Integer userGroupState; // 用户组状态(0: 删除 1: 正常)

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate; // 创建日期

    @Column(name = "done_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date doneDate; // 修改日期

    @Column(name = "remarks")
    private String remarks; // 备注信息

    public Long getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Long userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }

    public String getUserGroupDesc() {
        return userGroupDesc;
    }

    public void setUserGroupDesc(String userGroupDesc) {
        this.userGroupDesc = userGroupDesc;
    }

    public Integer getUserGroupState() {
        return userGroupState;
    }

    public void setUserGroupState(Integer userGroupState) {
        this.userGroupState = userGroupState;
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