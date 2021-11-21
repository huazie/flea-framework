package com.huazie.fleaframework.auth.base.function.entity;

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
 * <p> Flea菜单表对应的实体类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "flea_menu")
public class FleaMenu extends FleaEntity implements Comparable<FleaMenu> {

    private static final long serialVersionUID = 7136613747316056204L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FLEA_MENU_GENERATOR")
    @TableGenerator(
        // 唯一的生成器名称，可以由一个或多个类引用以作为id值的生成器。
        name = "FLEA_MENU_GENERATOR",
        // 存储生成的ID值的表的名称
        table = "flea_id_generator",
        // 表中主键列的名称
        pkColumnName = "id_generator_key",
        // 存储最后生成的主键值的列的名称
        valueColumnName = "id_generator_value",
        // ID生成器表中的主键值模板，用于将该生成值集与其他可能存储在表中的值区分开
        pkColumnValue = "pk_flea_menu",
        // 从ID生成器表中分配ID号时增加的数量
        allocationSize = 1
    )
    @Column(name = "menu_id", unique = true, nullable = false)
    private Long menuId; // 菜单编号

    @Column(name = "menu_code", nullable = false)
    private String menuCode; // 菜单编码

    @Column(name = "menu_name", nullable = false)
    private String menuName; // 菜单名称

    @Column(name = "menu_icon", nullable = false)
    private String menuIcon; // 菜单FontAwesome小图标

    @Column(name = "menu_sort", nullable = false)
    private Integer menuSort; // 菜单展示顺序(同一个父菜单下)

    @Column(name = "menu_view")
    private String menuView; // 菜单对应页面（非叶子菜单的可以为空）

    @Column(name = "menu_level", nullable = false)
    private Integer menuLevel; // 菜单层级（1：一级菜单 2；二级菜单 3：三级菜单 4：四级菜单）

    @Column(name = "menu_state", nullable = false)
    private Integer menuState; // 菜单状态（0:下线，1: 在用 ）

    @Column(name = "parent_id", nullable = false)
    private Long parentId; // 父菜单编号

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
    private String remarks; // 菜单描述

    /**
     * <p> 无参数构造方法 </p>
     *
     * @since 1.0.0
     */
    public FleaMenu() {
    }

    /**
     * <p> 带参数构造方法 </p>
     *
     * @param menuCode      菜单编码
     * @param menuName      菜单名称
     * @param menuIcon      菜单FontAwesome小图标
     * @param menuSort      菜单展示顺序(同一个父菜单下)
     * @param menuView      菜单对应页面（非叶子菜单的可以为空）
     * @param menuLevel     菜单层级（1：一级菜单 2；二级菜单 3：三级菜单 4：四级菜单）
     * @param parentId      父菜单编号
     * @param effectiveDate 生效日期
     * @param expiryDate    失效日期
     * @param remarks       备注
     * @since 1.0.0
     */
    public FleaMenu(String menuCode, String menuName, String menuIcon, Integer menuSort, String menuView, Integer menuLevel, Long parentId, Date effectiveDate, Date expiryDate, String remarks) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuIcon = menuIcon;
        this.menuSort = menuSort;
        this.menuView = menuView;
        this.menuLevel = menuLevel;
        this.menuState = EntityStateEnum.IN_USE.getState();
        if (ObjectUtils.isEmpty(parentId)) {
            parentId = CommonConstants.NumeralConstants.MINUS_ONE;
        }
        this.parentId = parentId;
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
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
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

    public Integer getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
    }

    public String getMenuView() {
        return menuView;
    }

    public void setMenuView(String menuView) {
        this.menuView = menuView;
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public Integer getMenuState() {
        return menuState;
    }

    public void setMenuState(Integer menuState) {
        this.menuState = menuState;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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
    public int compareTo(@SuppressWarnings("NullableProblems") FleaMenu fleaMenu) {
        return Integer.compare(menuSort, fleaMenu.getMenuSort());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}