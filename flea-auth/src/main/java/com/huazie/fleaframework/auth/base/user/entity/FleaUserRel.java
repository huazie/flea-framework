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
 * Flea用户关联（角色，角色组）表对应的实体类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "flea_user_rel")
public class FleaUserRel extends FleaEntity {

    private static final long serialVersionUID = -8401706808209268007L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FLEA_USER_REL_GENERATOR")
    @TableGenerator(
        // 唯一的生成器名称，可以由一个或多个类引用以作为id值的生成器。
        name = "FLEA_USER_REL_GENERATOR",
        // 存储生成的ID值的表的名称
        table = "flea_id_generator",
        // 表中主键列的名称
        pkColumnName = "id_generator_key",
        // 存储最后生成的主键值的列的名称
        valueColumnName = "id_generator_value",
        // ID生成器表中的主键值模板，用于将该生成值集与其他可能存储在表中的值区分开
        pkColumnValue = "pk_flea_user_rel",
        // 从ID生成器表中分配ID号时增加的数量
        allocationSize = 1
    )
    @Column(name = "user_rel_id", unique = true, nullable = false)
    private Long userRelId; // 用户关联编号

    @Column(name = "user_id", nullable = false)
    private Long userId; // 用户编号

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

    /**
     * 无参数构造方法
     *
     * @since 2.0.0
     */
    public FleaUserRel() {
    }

    /**
     * 带参数构造方法
     *
     * @param userId  用户编号
     * @param relId   关联编号
     * @param relType 关联类型
     * @param remarks 备注信息
     * @param relExtA 关联扩展字段A
     * @param relExtB 关联扩展字段B
     * @param relExtC 关联扩展字段C
     * @param relExtX 关联扩展字段X
     * @param relExtY 关联扩展字段Y
     * @param relExtZ 关联扩展字段Z
     * @since 2.0.0
     */
    public FleaUserRel(Long userId, Long relId, String relType, String remarks, String relExtA, String relExtB, String relExtC, String relExtX, String relExtY, String relExtZ) {
        this.userId = userId;
        this.relId = relId;
        this.relType = relType;
        this.relState = EntityStateEnum.IN_USE.getState();
        this.createDate = DateUtils.getCurrentTime();
        this.remarks = remarks;
        this.relExtA = relExtA;
        this.relExtB = relExtB;
        this.relExtC = relExtC;
        this.relExtX = relExtX;
        this.relExtY = relExtY;
        this.relExtZ = relExtZ;
    }

    public Long getUserRelId() {
        return userRelId;
    }

    public void setUserRelId(Long userRelId) {
        this.userRelId = userRelId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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