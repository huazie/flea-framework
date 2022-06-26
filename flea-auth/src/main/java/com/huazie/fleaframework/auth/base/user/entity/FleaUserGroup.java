package com.huazie.fleaframework.auth.base.user.entity;

import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.FleaEntity;
import com.huazie.fleaframework.common.util.DateUtils;
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
 * Flea用户组表对应的实体类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "flea_user_group")
public class FleaUserGroup extends FleaEntity {

    private static final long serialVersionUID = -7561778222152504449L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FLEA_USER_GROUP_GENERATOR")
    @TableGenerator(
        // 唯一的生成器名称，可以由一个或多个类引用以作为id值的生成器。
        name = "FLEA_USER_GROUP_GENERATOR",
        // 存储生成的ID值的表的名称
        table = "flea_id_generator",
        // 表中主键列的名称
        pkColumnName = "id_generator_key",
        // 存储最后生成的主键值的列的名称
        valueColumnName = "id_generator_value",
        // ID生成器表中的主键值模板，用于将该生成值集与其他可能存储在表中的值区分开
        pkColumnValue = "pk_flea_user_group",
        // 从ID生成器表中分配ID号时增加的数量
        allocationSize = 1
    )
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

    /**
     * 无参数构造方法
     *
     * @since 2.0.0
     */
    public FleaUserGroup() {
    }

    /**
     * 带参数构造方法
     *
     * @param userGroupName 用户组名称
     * @param userGroupDesc 用户组描述
     * @param remarks       备注信息
     * @since 2.0.0
     */
    public FleaUserGroup(String userGroupName, String userGroupDesc, String remarks) {
        this.userGroupName = userGroupName;
        this.userGroupDesc = userGroupDesc;
        this.userGroupState = EntityStateEnum.IN_USE.getState();
        this.createDate = DateUtils.getCurrentTime();
        this.remarks = remarks;
    }

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