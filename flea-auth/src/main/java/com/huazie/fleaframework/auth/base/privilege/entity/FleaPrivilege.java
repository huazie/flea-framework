package com.huazie.fleaframework.auth.base.privilege.entity;

import com.huazie.fleaframework.common.CommonConstants;
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
 * <p> Flea权限表对应的实体类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "flea_privilege")
public class FleaPrivilege extends FleaEntity {

    private static final long serialVersionUID = 9087738171790593468L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FLEA_PRIVILEGE_GENERATOR")
    @TableGenerator(
        // 唯一的生成器名称，可以由一个或多个类引用以作为id值的生成器。
        name = "FLEA_PRIVILEGE_GENERATOR",
        // 存储生成的ID值的表的名称
        table = "flea_id_generator",
        // 表中主键列的名称
        pkColumnName = "id_generator_key",
        // 存储最后生成的主键值的列的名称
        valueColumnName = "id_generator_value",
        // ID生成器表中的主键值模板，用于将该生成值集与其他可能存储在表中的值区分开
        pkColumnValue = "pk_flea_privilege",
        // 从ID生成器表中分配ID号时增加的数量
        allocationSize = 1
    )
    @Column(name = "privilege_id", unique = true, nullable = false)
    private Long privilegeId; // 权限编号

    @Column(name = "privilege_name", nullable = false)
    private String privilegeName; // 权限名称

    @Column(name = "privilege_desc")
    private String privilegeDesc; // 权限描述

    @Column(name = "group_id", nullable = false)
    private Long groupId; // 权限组编号

    @Column(name = "privilege_state", nullable = false)
    private Integer privilegeState; // 权限状态(0: 删除 1: 正常)

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate; // 创建日期

    @Column(name = "done_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date doneDate; // 修改日期

    @Column(name = "remarks")
    private String remarks; // 备注信息

    /**
     * <p> 无参数构造方法 </p>
     *
     * @since 1.0.0
     */
    public FleaPrivilege() {
    }

    /**
     * <p> 带参数构造方法 </p>
     *
     * @param privilegeName 权限名称
     * @param privilegeDesc 权限描述
     * @param groupId       权限组编号
     * @param remarks       备注信息
     * @since 1.0.0
     */
    public FleaPrivilege(String privilegeName, String privilegeDesc, Long groupId, String remarks) {
        this.privilegeName = privilegeName;
        this.privilegeDesc = privilegeDesc;
        if (ObjectUtils.isEmpty(groupId)) {
            groupId = CommonConstants.NumeralConstants.MINUS_ONE;
        }
        this.groupId = groupId;
        this.privilegeState = EntityStateEnum.IN_USE.getState();
        this.createDate = DateUtils.getCurrentTime();
        this.remarks = remarks;
    }

    public Long getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Long privilegeId) {
        this.privilegeId = privilegeId;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public String getPrivilegeDesc() {
        return privilegeDesc;
    }

    public void setPrivilegeDesc(String privilegeDesc) {
        this.privilegeDesc = privilegeDesc;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Integer getPrivilegeState() {
        return privilegeState;
    }

    public void setPrivilegeState(Integer privilegeState) {
        this.privilegeState = privilegeState;
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