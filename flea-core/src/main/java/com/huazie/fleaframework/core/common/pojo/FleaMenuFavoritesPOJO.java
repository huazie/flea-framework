package com.huazie.fleaframework.core.common.pojo;

import com.huazie.fleaframework.common.pojo.FleaEffExpDatePOJO;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p> Flea菜单收藏夹POJO类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaMenuFavoritesPOJO extends FleaEffExpDatePOJO {

    private static final long serialVersionUID = 1410890415201200273L;

    private Long accountId; // 账户编号

    private String menuCode; // 菜单编码

    private String menuName; // 菜单名称

    private String menuIcon; // 菜单FontAwesome小图标

    private String ext1; // 扩展字段1

    private String ext2; // 扩展字段2

    private String ext3; // 扩展字段3

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
