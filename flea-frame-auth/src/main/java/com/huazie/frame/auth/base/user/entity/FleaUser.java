package com.huazie.frame.auth.base.user.entity;

import com.huazie.frame.common.FleaEntity;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Access;
import javax.persistence.AccessType;
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
 * <p> Flea用户表对应实体类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "flea_user")
@Access(AccessType.PROPERTY)
public class FleaUser implements FleaEntity {

	private static final long serialVersionUID = 6453485802859212467L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "USER_SEQ")
    @SequenceGenerator(name = "USER_SEQ")
    @Column( name = "user_id", unique = true, nullable = false)
	private Long userId;			// 用户编号

    @Column(name = "user_name", unique = true, nullable = false)
	private String userName;		// 昵称

    @Column(name = "user_sex")
	private Integer userSex;		// 性别

    @Column(name = "user_birthday")
    @Temporal(TemporalType.DATE)
	private Date userBirthday; 		// 出生日期

    @Column(name = "user_address")
	private String userAddress; 	// 住址

    @Column(name = "user_email")
	private String userEmail;		// 形如 ***@**.com

    @Column(name = "user_phone")
	private String userPhone;		// 11位的手机号

    @Column(name = "user_level")
	private Integer userLevel;		// 等级(1: 普通用户，2:普通会员 ,3:高级会员)

    @Column(name = "user_state", nullable = false)
	private Integer userState;		// 状态（0：删除，1：正常 ，2：禁用）

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
	private Date createDate;		// 创建日期

    @Column(name = "done_date")
    @Temporal(TemporalType.TIMESTAMP)
	private Date doneDate;			// 修改日期

    @Column(name = "effective_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
	private Date effectiveDate;		// 生效时间

    @Column(name = "expiry_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
	private Date expiryDate;		// 失效时间

    @Column(name = "user_pic", nullable = false)
	private String userPic;			// 用户头像

    @Column(name = "user_autograph")
	private String userAutoGraph;	// 签名

    @Column(name = "remarks")
	private String remarks;		    // 描述信息

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

	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
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

	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}

	public String getUserAutoGraph() {
		return userAutoGraph;
	}

	public void setUserAutoGraph(String userAutoGraph) {
		this.userAutoGraph = userAutoGraph;
	}

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
