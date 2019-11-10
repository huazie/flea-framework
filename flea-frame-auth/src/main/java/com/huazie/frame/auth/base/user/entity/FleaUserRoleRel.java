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
 * <p> Flea用户和角色关联表对应的实体类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "flea_user_role_rel")
public class FleaUserRoleRel extends FleaEntity {

    private static final long serialVersionUID = -7130224789401696538L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "FLEA_USER_ROLE_REL_SEQ")
    @SequenceGenerator(name = "FLEA_USER_ROLE_REL_SEQ")
    @Column(name = "user_role_rel_id", unique = true, nullable = false)
    private Long userRoleRelId; // 用户角色关联编号

    @Column(name = "user_id", nullable = false)
    private Long userId; // 用户编号

    @Column(name = "role_id", nullable = false)
    private Long roleId; // 角色编号

    @Column(name = "user_role_rel_state", nullable = false)
    private Integer userRoleRelState; // 用户角色关联关系状态（1: 正常  0: 删除）

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate; // 创建日期

    @Column(name = "done_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date doneDate; // 修改日期

    @Column(name = "remarks")
    private String remarks; // 备注信息

    public Long getUserRoleRelId() {
        return userRoleRelId;
    }

    public void setUserRoleRelId(Long userRoleRelId) {
        this.userRoleRelId = userRoleRelId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Integer getUserRoleRelState() {
        return userRoleRelState;
    }

    public void setUserRoleRelState(Integer userRoleRelState) {
        this.userRoleRelState = userRoleRelState;
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