package com.huazie.frame.auth.util;

import com.huazie.frame.auth.base.function.entity.FleaMenu;
import com.huazie.frame.common.util.StringUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> Fuelux菜单树 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FueluxMenuTree extends FleaMenuTree {

    public FueluxMenuTree(String systemName) {
        super(systemName);
    }

    public FueluxMenuTree(String systemName, Comparator<? super FleaMenu> comparator) {
        super(systemName, comparator);
    }

    @Override
    protected String getMapKeyForSubNotes() {
        return "additionalParameters";
    }

    @Override
    protected void reHandleTreeNodeMap(Map<String, Object> treeNodeMap) {
        boolean hasSubMenu = (boolean) treeNodeMap.get(HAS_SUB_MENU);
        long menuId = (long) treeNodeMap.get(MENU_ID);
        String menuName = StringUtils.valueOf(treeNodeMap.get(MENU_NAME));
        String menuCode = StringUtils.valueOf(treeNodeMap.get(MENU_CODE));
        String menuIcon = StringUtils.valueOf(treeNodeMap.get(MENU_ICON));
        Integer menuLevel = (Integer) treeNodeMap.get(MENU_LEVEL);

        if (hasSubMenu) {
            treeNodeMap.put("name", menuName);
            treeNodeMap.put("type", "folder");
            treeNodeMap.put("icon-class", "red");
        } else {
            treeNodeMap.put("name", "<i class=\"fa fa-" + menuIcon + " blue\"></i>" + menuName);
            treeNodeMap.put("type", "item");
        }

        treeNodeMap.put("id", menuId);
        treeNodeMap.put("code", menuCode);
        treeNodeMap.put("level", menuLevel);

        // 重新设置子节点数据
        Object subNodeMapList = treeNodeMap.get(getMapKeyForSubNotes());
        Map<String, Object> childrenMap = new HashMap<>();
        childrenMap.put("children", subNodeMapList);
        treeNodeMap.put(getMapKeyForSubNotes(), childrenMap);
    }
}
