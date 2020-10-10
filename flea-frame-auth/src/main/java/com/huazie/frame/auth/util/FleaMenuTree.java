package com.huazie.frame.auth.util;

import com.huazie.frame.auth.base.function.entity.FleaMenu;
import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.EntityStateEnum;
import com.huazie.frame.common.FleaTree;
import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单树 {@code FleaMenuTree}, 根节点为菜单归属系统，子节点为
 * 其下的一级菜单、二级菜单、三级菜单、四级菜单
 *
 * <p> 归属系统 {@code menuLevel}=0，菜单树的根节点，高度 {@code height}=1,
 * 编号 {@code id}=-1
 *
 * <p> 一级菜单 {@code menuLevel}=1，菜单树高度 {@code height}=2，
 * 菜单顺序 {@code menuSort}, 值越少，菜单展示越靠前；
 *
 * <p> 二级菜单、三级菜单、四级菜单，依此类推。
 *
 * @author huazie
 * @version 1.0.0
 * @see FleaTree
 * @since 1.0.0
 */
public class FleaMenuTree extends FleaTree<FleaMenu> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaMenuTree.class);

    private static final long serialVersionUID = 782100083467014824L;

    public static final String MENU_TREE = "MENU_TREE";

    public static final String HAS_SUB_MENU = "HAS_SUB_MENU";

    public static final String IS_SELECT = "IS_SELECT";

    public static final String MENU_ID = "MENU_ID";

    public static final String MENU_CODE = "MENU_CODE";

    public static final String MENU_NAME = "MENU_NAME";

    public static final String MENU_ICON = "MENU_ICON";

    public static final String MENU_LEVEL = "MENU_LEVEL";

    public static final String PARENT_MENU_ID = "PARENT_MENU_ID";

    private String systemName; // 归属系统名称

    public FleaMenuTree(String systemName) {
        addRootNode(systemName);
    }

    public FleaMenuTree(String systemName, Comparator<? super FleaMenu> comparator) {
        super(comparator);
        addRootNode(systemName);
    }

    /**
     * <p> 添加菜单树的根节点，菜单归属系统 </p>
     *
     * @param systemName 归属系统名称
     * @since 1.0.0
     */
    private void addRootNode(String systemName) {
        this.systemName = systemName;
        FleaMenu systemFleaMenu = new FleaMenu();
        systemFleaMenu.setMenuId(CommonConstants.NumeralConstants.MINUS_ONE);
        systemFleaMenu.setMenuName(systemName);
        systemFleaMenu.setParentId(CommonConstants.NumeralConstants.MINUS_TWO);
        systemFleaMenu.setMenuSort(CommonConstants.NumeralConstants.INT_ONE);
        systemFleaMenu.setMenuLevel(CommonConstants.NumeralConstants.INT_ZERO);
        systemFleaMenu.setMenuState(EntityStateEnum.IN_USE.getState());
        addRootTreeNote(systemFleaMenu);
    }

    /**
     * <p> 菜单树归属系统名称 </p>
     *
     * @return 归属系统名称
     * @since 1.0.0
     */
    public String getSystemName() {
        return systemName;
    }

    /**
     * <p> 获取指定菜单编码的叶子菜单 </p>
     *
     * @param menuCode 菜单编码
     * @return 指定菜单编码的叶子菜单
     */
    public FleaMenu getTreeLeafMenu(String menuCode) {

        FleaMenu treeLeafMenu = null;

        // 获取所有的叶子菜单
        List<FleaMenu> treeLeafMenuList = getAllTreeLeafElement();
        if (CollectionUtils.isNotEmpty(treeLeafMenuList)) {
            for (FleaMenu current : treeLeafMenuList) {
                if (ObjectUtils.isNotEmpty(current) && StringUtils.isNotBlank(menuCode) && menuCode.equals(current.getMenuCode())) {
                    treeLeafMenu = current;
                    break;
                }
            }
        }
        return treeLeafMenu;
    }

    /**
     * <p> 添加菜单到菜单树中 </p>
     *
     * @param fleaMenu 菜单
     * @since 1.0.0
     */
    public void add(FleaMenu fleaMenu, FleaMenu parentMenu) {
        if (ObjectUtils.isEmpty(fleaMenu) || ObjectUtils.isEmpty(parentMenu)) {
            return;
        }

        long id = fleaMenu.getMenuId();
        int menuLevel = fleaMenu.getMenuLevel();
        int height = menuLevel + 1;

        long pId = parentMenu.getMenuId();
        int pMenuLevel = parentMenu.getMenuLevel();
        int pHeight = pMenuLevel + 1;

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaMenuTree#add(FleaMenu, FleaMenu) Start Adding Menu to MenuTree");
            LOGGER.debug("FleaMenuTree#add(FleaMenu, FleaMenu) Current Menu = {}", fleaMenu);
            LOGGER.debug("FleaMenuTree#add(FleaMenu, FleaMenu) Current Menu Id = {}", id);
            LOGGER.debug("FleaMenuTree#add(FleaMenu, FleaMenu) Current Menu Level = {}", menuLevel);
            LOGGER.debug("FleaMenuTree#add(FleaMenu, FleaMenu) Parent Menu = {}", parentMenu);
            LOGGER.debug("FleaMenuTree#add(FleaMenu, FleaMenu) Parent Menu Id = {}", pId);
            LOGGER.debug("FleaMenuTree#add(FleaMenu, FleaMenu) Parent Menu Level = {}", pMenuLevel);
            LOGGER.debug("FleaMenuTree#add(FleaMenu, FleaMenu) Finish Adding Menu to MenuTree");
        }

        addTreeNote(fleaMenu, id, height, parentMenu, pId, pHeight);
    }

    /**
     * <p> 批量添加菜单到菜单树中 </p>
     *
     * @param fleaMenuList 菜单集合
     * @since 1.0.0
     */
    public void addAll(List<FleaMenu> fleaMenuList) {

        if (ObjectUtils.isEmpty(fleaMenuList)) {
            return;
        }

        for (FleaMenu fleaMenu : fleaMenuList) {
            add(fleaMenu, getParentMenu(fleaMenu, fleaMenuList));
        }
    }

    @Override
    protected String toString(FleaMenu element) {
        return element.getMenuName();
    }

    @Override
    protected Map<String, Object> toMap(FleaMenu element, long id, int height, FleaMenu pElement, long pId, int pHeight, boolean isHasSubNotes) {
        Map<String, Object> menuMap = new HashMap<>();
        menuMap.put(HAS_SUB_MENU, isHasSubNotes);
        menuMap.put(IS_SELECT, false);
        menuMap.put(MENU_ID, id);
        menuMap.put(MENU_CODE, element.getMenuCode());
        menuMap.put(MENU_NAME, element.getMenuName());
        menuMap.put(MENU_ICON, element.getMenuIcon());
        menuMap.put(MENU_LEVEL, element.getMenuLevel());
        menuMap.put(PARENT_MENU_ID, pId);
        return menuMap;
    }

    @Override
    protected String getMapKeyForSubNotes() {
        return "SUB_MENUS";
    }

    /**
     * <p> 从菜单集合中获取当前菜单对应的父菜单 </p>
     *
     * @param current      当前菜单
     * @param fleaMenuList 菜单列表
     * @return 当前菜单对应的父菜单
     * @since 1.0.0
     */
    private FleaMenu getParentMenu(FleaMenu current, List<FleaMenu> fleaMenuList) {

        FleaMenu parentMenu = null;

        if (ObjectUtils.isNotEmpty(current) && CollectionUtils.isNotEmpty(fleaMenuList)) {
            for (FleaMenu fleaMenu : fleaMenuList) {
                // current的父菜单的菜单等级 = current的菜单等级 - 1 = fleaMenu的菜单等级
                // current的父菜单编号 = fleaMenu的菜单编号
                if (ObjectUtils.isNotEmpty(fleaMenu) && current.getMenuLevel() - 1 == fleaMenu.getMenuLevel()
                        && current.getParentId().equals(fleaMenu.getMenuId())) {
                    parentMenu = fleaMenu;
                }
            }
            // 如果当前菜单对应的父菜单在菜单列表中不存在，即当前菜单是一级菜单，则取根节点为他的父菜单
            if (ObjectUtils.isEmpty(parentMenu)) {
                parentMenu = getRootElement();
            }
        }
        return parentMenu;
    }

}
