package com.huazie.frame.core.base.cfgdata.entity;

import com.huazie.frame.common.EntityStateEnum;
import com.huazie.frame.common.FleaEntity;
import com.huazie.frame.common.util.DateUtils;
import com.huazie.frame.common.util.ObjectUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * <p> Flea菜单收藏夹表对应的实体类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "flea_menu_favorites")
public class FleaMenuFavorites extends FleaEntity {

    private static final long serialVersionUID = -4242865820201995524L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorites_id", unique = true, nullable = false)
    private Long favoritesId; // 菜单收藏夹编号

    @Column(name = "account_id", nullable = false)
    private Long accountId; // 账户编号

    @Column(name = "menu_code", nullable = false)
    private String menuCode; // 菜单编码

    @Column(name = "menu_name", nullable = false)
    private String menuName; // 菜单名称

    @Column(name = "menu_icon", nullable = false)
    private String menuIcon; // 菜单FontAwesome小图标

    @Column(name = "favorites_state", nullable = false)
    private Integer favoritesState; // 菜单收藏夹状态（1: 正常  0: 删除）

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate; // 创建时间

    @Column(name = "done_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date doneDate; // 修改时间

    @Column(name = "effective_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectiveDate; // 生效时间

    @Column(name = "expiry_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate; // 失效时间

    @Column(name = "remarks")
    private String remarks; // 备注

    @Column(name = "ext1")
    private String ext1; // 扩展字段1

    @Column(name = "ext2")
    private String ext2; // 扩展字段2

    @Column(name = "ext3")
    private String ext3; // 扩展字段3

    /**
     * <p> 无参数构造方法 </p>
     *
     * @since 1.0.0
     */
    public FleaMenuFavorites() {
    }

    /**
     * <p> 带参数构造方法 </p>
     *
     * @param accountId     操作账户编号
     * @param menuCode      菜单编码
     * @param menuName      菜单名称
     * @param menuIcon      菜单FontAwesome小图标
     * @param effectiveDate 生效日期
     * @param expiryDate    失效日期
     * @param remarks       备注
     * @param ext1          扩展字段1
     * @param ext2          扩展字段2
     * @param ext3          扩展字段3
     * @since 1.0.0
     */
    public FleaMenuFavorites(Long accountId, String menuCode, String menuName, String menuIcon, Date effectiveDate, Date expiryDate, String remarks, String ext1, String ext2, String ext3) {
        this.accountId = accountId;
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuIcon = menuIcon;
        this.favoritesState = EntityStateEnum.IN_USE.getState();
        this.createDate = DateUtils.getCurrentTime();
        if (ObjectUtils.isEmpty(effectiveDate)) {
            effectiveDate = createDate;
        }
        this.effectiveDate = effectiveDate;
        if (ObjectUtils.isEmpty(expiryDate)) {
            expiryDate = DateUtils.getExpiryTimeForever();
        }
        this.expiryDate = expiryDate;
        this.remarks = remarks;
        this.ext1 = ext1;
        this.ext2 = ext2;
        this.ext3 = ext3;
    }

    public Long getFavoritesId() {
        return favoritesId;
    }

    public void setFavoritesId(Long favoritesId) {
        this.favoritesId = favoritesId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public Integer getFavoritesState() {
        return favoritesState;
    }

    public void setFavoritesState(Integer favoritesState) {
        this.favoritesState = favoritesState;
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

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}