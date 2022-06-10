package com.huazie.fleaframework.auth.common.pojo.function.menu;

import com.huazie.fleaframework.auth.common.pojo.function.attr.FleaFunctionAttrPOJO;
import com.huazie.fleaframework.common.pojo.FleaEffExpDatePOJO;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

/**
 * Flea菜单POJO类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaMenuPOJO extends FleaEffExpDatePOJO {

    private static final long serialVersionUID = 5990688871036682592L;

    private String menuCode; // 菜单编码

    private String menuName; // 菜单名称

    private String menuIcon; // 菜单FontAwesome小图标

    private Integer menuSort; // 菜单展示顺序(同一个父菜单下)

    private String menuView; // 菜单对应页面（非叶子菜单的可以为空）

    private Integer menuLevel; // 菜单层级（1：一级菜单 2；二级菜单 3：三级菜单 4：四级菜单）

    private Long parentId; // 父菜单编号

    private List<FleaFunctionAttrPOJO> functionAttrPOJOList; // 功能扩展属性

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<FleaFunctionAttrPOJO> getFunctionAttrPOJOList() {
        return functionAttrPOJOList;
    }

    public void setFunctionAttrPOJOList(List<FleaFunctionAttrPOJO> functionAttrPOJOList) {
        this.functionAttrPOJOList = functionAttrPOJOList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
