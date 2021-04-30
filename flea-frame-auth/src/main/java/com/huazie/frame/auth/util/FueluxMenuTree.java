package com.huazie.frame.auth.util;

import com.huazie.frame.auth.base.function.entity.FleaMenu;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Fuelux菜单树 {@code FueluxMenuTree}
 *
 * <p> 其中父节点，子节点的图标采用 Font Awesome。
 *
 * <p> 父节点图标颜色配置参数{@code params} 【key = folder_icon_class, value = red or blue】

 * <p> 子节点图标颜色配置参数{@code params} 【key = item_icon_class, value = red or blue】
 *
 * @author huazie
 * @version 1.0.0
 * @see FleaMenuTree
 * @since 1.0.0
 */
public class FueluxMenuTree extends FleaMenuTree {

    private static final long serialVersionUID = 8344142120807611410L;

    public static final String FOLDER_ICON_CLASS = "folder_icon_class";

    public static final String ITEM_ICON_CLASS = "item_icon_class";

    private Map<String, String> params;

    /**
     * <p> 带参数的构造方法 </p>
     *
     * @param systemName 系统名
     * @param params 配置参数
     */
    public FueluxMenuTree(String systemName, Map<String, String> params) {
        super(systemName);
        this.params = params;
        if (ObjectUtils.isEmpty(params)) {
            this.params = new HashMap<>();
        }
        if (!this.params.containsKey(FOLDER_ICON_CLASS)) {
            this.params.put(FOLDER_ICON_CLASS, "blue"); // 默认父节点展示蓝色
        }
        if (!this.params.containsKey(ITEM_ICON_CLASS)) {
            this.params.put(ITEM_ICON_CLASS, "blue"); // 默认叶子节点展示蓝色
        }
    }

    @Override
    protected String getMapKeyForSubNotes() {
        return "additionalParameters";
    }

    @Override
    protected Map<String, Object> toMap(FleaMenu element, long id, int height, FleaMenu pElement, long pId, int pHeight, boolean isHasSubNotes) {
        Map<String, Object> menuMap = new HashMap<>();
        String menuName = element.getMenuName();
        // 存在子菜单或者菜单视图为空 则认为当前是父节点
        if (isHasSubNotes || StringUtils.isBlank(element.getMenuView())) {
            menuMap.put("name", menuName);
            menuMap.put("type", "folder");
            menuMap.put("icon-class", this.params.get(FOLDER_ICON_CLASS));
        } else {
            StringBuilder itemName = new StringBuilder();
            itemName.append("<i class=\"fa fa-")
                    .append(element.getMenuIcon()).append(" ")
                    .append(this.params.get(ITEM_ICON_CLASS))
                    .append("\"></i> ").append(menuName);
            menuMap.put("name", itemName.toString());
            menuMap.put("type", "item");
        }
        menuMap.put("id", element.getMenuId());
        menuMap.put("code", element.getMenuCode());
        menuMap.put("level", element.getMenuLevel());
        return menuMap;
    }

    @Override
    protected void reHandleTreeNodeMap(Map<String, Object> treeNodeMap) {
        // 重新设置子节点数据
        Object subNodeMapList = treeNodeMap.get(getMapKeyForSubNotes());
        Map<String, Object> childrenMap = new HashMap<>();
        childrenMap.put("children", subNodeMapList);
        treeNodeMap.put(getMapKeyForSubNotes(), childrenMap);
    }
}
