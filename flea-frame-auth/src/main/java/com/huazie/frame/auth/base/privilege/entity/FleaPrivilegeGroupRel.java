package com.huazie.frame.auth.base.privilege.entity;

import com.huazie.frame.common.FleaEntity;
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
 * <p> Flea权限组关联（权限）表对应的实体类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "flea_privilege_group_rel")
public class FleaPrivilegeGroupRel extends FleaEntity {

    private static final long serialVersionUID = -4914885396185785376L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FLEA_PRIVILEGE_GROUP_REL_GENERATOR")
    @TableGenerator(
        // 唯一的生成器名称，可以由一个或多个类引用以作为id值的生成器。
        name = "FLEA_PRIVILEGE_GROUP_REL_GENERATOR",
        // 存储生成的ID值的表的名称
        table = "flea_id_generator",
        // 表中主键列的名称
        pkColumnName = "id_generator_key",
        // 存储最后生成的主键值的列的名称
        valueColumnName = "id_generator_value",
        // ID生成器表中的主键值模板，用于将该生成值集与其他可能存储在表中的值区分开
        pkColumnValue = "pk_flea_privilege_group_rel",
        // 从ID生成器表中分配ID号时增加的数量
        allocationSize = 1
    )
    @Column(name = "privilege_group_rel_id", unique = true, nullable = false)
    private Long privilegeGroupRelId; // 权限组关联编号

    @Column(name = "privilege_group_id", nullable = false)
    private Long privilegeGroupId; // 权限组编号

    @Column(name = "rel_id", nullable = false)
    private Long relId; // 关联编号

    @Column(name = "rel_type", nullable = false)
    private String relType; // 关联类型

    @Column(name = "rel_state", nullable = false)
    private Integer relState; // 关联状态(0: 删除 1: 正常)

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate; // 创建日期

    @Column(name = "done_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date doneDate; // 修改日期

    @Column(name = "remarks")
    private String remarks; // 备注信息

    @Column(name = "rel_ext_a")
    private String relExtA; // 关联扩展字段A

    @Column(name = "rel_ext_b")
    private String relExtB; // 关联扩展字段B

    @Column(name = "rel_ext_c")
    private String relExtC; // 关联扩展字段C

    @Column(name = "rel_ext_x")
    private String relExtX; // 关联扩展字段X

    @Column(name = "rel_ext_y")
    private String relExtY; // 关联扩展字段Y

    @Column(name = "rel_ext_z")
    private String relExtZ; // 关联扩展字段Z

    public Long getPrivilegeGroupRelId() {
        return privilegeGroupRelId;
    }

    public void setPrivilegeGroupRelId(Long privilegeGroupRelId) {
        this.privilegeGroupRelId = privilegeGroupRelId;
    }

    public Long getPrivilegeGroupId() {
        return privilegeGroupId;
    }

    public void setPrivilegeGroupId(Long privilegeGroupId) {
        this.privilegeGroupId = privilegeGroupId;
    }

    public Long getRelId() {
        return relId;
    }

    public void setRelId(Long relId) {
        this.relId = relId;
    }

    public String getRelType() {
        return relType;
    }

    public void setRelType(String relType) {
        this.relType = relType;
    }

    public Integer getRelState() {
        return relState;
    }

    public void setRelState(Integer relState) {
        this.relState = relState;
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

    public String getRelExtA() {
        return relExtA;
    }

    public void setRelExtA(String relExtA) {
        this.relExtA = relExtA;
    }

    public String getRelExtB() {
        return relExtB;
    }

    public void setRelExtB(String relExtB) {
        this.relExtB = relExtB;
    }

    public String getRelExtC() {
        return relExtC;
    }

    public void setRelExtC(String relExtC) {
        this.relExtC = relExtC;
    }

    public String getRelExtX() {
        return relExtX;
    }

    public void setRelExtX(String relExtX) {
        this.relExtX = relExtX;
    }

    public String getRelExtY() {
        return relExtY;
    }

    public void setRelExtY(String relExtY) {
        this.relExtY = relExtY;
    }

    public String getRelExtZ() {
        return relExtZ;
    }

    public void setRelExtZ(String relExtZ) {
        this.relExtZ = relExtZ;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}