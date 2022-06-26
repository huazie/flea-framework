package com.huazie.fleaframework.auth.base.user.entity;

import com.huazie.fleaframework.auth.common.UserStateEnum;
import com.huazie.fleaframework.common.CommonConstants;
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
 * Flea用户表对应的实体类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "flea_user")
public class FleaUser extends FleaEntity {

    private static final long serialVersionUID = -5095406172789395653L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FLEA_USER_GENERATOR")
    @TableGenerator(
        // 唯一的生成器名称，可以由一个或多个类引用以作为id值的生成器。
        name = "FLEA_USER_GENERATOR",
        // 存储生成的ID值的表的名称
        table = "flea_id_generator",
        // 表中主键列的名称
        pkColumnName = "id_generator_key",
        // 存储最后生成的主键值的列的名称
        valueColumnName = "id_generator_value",
        // ID生成器表中的主键值模板，用于将该生成值集与其他可能存储在表中的值区分开
        pkColumnValue = "pk_flea_user",
        // 从ID生成器表中分配ID号时增加的数量
        allocationSize = 1
    )
    @Column(name = "user_id", unique = true, nullable = false)
    private Long userId; // 用户编号

    @Column(name = "user_name", nullable = false)
    private String userName; // 昵称

    @Column(name = "user_sex")
    private Integer userSex; // 性别（1：男 2：女 3：其他）

    @Column(name = "user_birthday")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userBirthday; // 生日

    @Column(name = "user_address")
    private String userAddress; // 住址

    @Column(name = "user_email")
    private String userEmail; // 邮箱

    @Column(name = "user_phone")
    private String userPhone; // 手机

    @Column(name = "group_id", nullable = false)
    private Long groupId; // 用户组编号

    @Column(name = "user_state", nullable = false)
    private Integer userState; // 用户状态（0：删除，1：正常 ，2：禁用，3：待审核）

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
     * @since 1.0.0
     */
    public FleaUser() {
    }

    /**
     * 带参数构造方法
     *
     * @param userName  账号
     * @param groupId   用户组编号
     * @param userState 用户状态（0：删除，1：正常 ，2：禁用，3：待审核）
     * @param remarks   备注
     * @since 1.0.0
     */
    public FleaUser(String userName, Long groupId, Integer userState, Date effectiveDate, Date expiryDate, String remarks) {
        this.userName = userName;
        if (ObjectUtils.isEmpty(groupId)) {
            // 默认用户组编号为 -1
            groupId = CommonConstants.NumeralConstants.MINUS_ONE;
        }
        this.groupId = groupId;
        if (ObjectUtils.isEmpty(userState)) {
            // 默认用户状态为 3 : 待审核
            userState = UserStateEnum.IN_AUDITING.getState();
        }
        this.userState = userState;
        this.createDate = DateUtils.getCurrentTime();
        if (ObjectUtils.isEmpty(effectiveDate)) {
            effectiveDate = DateUtils.getCurrentTime();
        }
        this.effectiveDate = effectiveDate;
        if (ObjectUtils.isEmpty(expiryDate)) {
            expiryDate = DateUtils.getExpiryTimeForever();
        }
        this.expiryDate = expiryDate;
        this.remarks = remarks;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
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