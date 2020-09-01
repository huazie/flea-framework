package com.huazie.frame.auth.function;

import com.huazie.frame.auth.base.function.entity.FleaFunctionAttr;
import com.huazie.frame.auth.base.function.entity.FleaMenu;
import com.huazie.frame.auth.base.function.service.interfaces.IFleaFunctionAttrSV;
import com.huazie.frame.auth.base.function.service.interfaces.IFleaMenuSV;
import com.huazie.frame.auth.common.FleaAuthConstants;
import com.huazie.frame.auth.common.FunctionTypeEnum;
import com.huazie.frame.auth.common.MenuLevelEnum;
import com.huazie.frame.auth.common.pojo.function.attr.FleaFunctionAttrPOJO;
import com.huazie.frame.auth.common.pojo.function.menu.FleaMenuPOJO;
import com.huazie.frame.auth.user.UserAuthTest;
import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.EntityStateEnum;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>  </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FunctionAuthTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthTest.class);

    private ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        LOGGER.debug("ApplicationContext={}", applicationContext);
    }

    @Test
    public void testSaveMenu() {

        FleaMenu fleaMenu = new FleaMenu();
        fleaMenu.setMenuCode("console"); // 菜单编码
        fleaMenu.setMenuName("控制台"); // 菜单名称
        fleaMenu.setMenuIcon("dashboard"); // 菜单FontAwesome小图标
        fleaMenu.setMenuSort(1);  // 菜单展示顺序(同一个父菜单下)
        fleaMenu.setMenuView("mgmt/console.html"); // 菜单对应页面（非叶子菜单的可以为空）
        fleaMenu.setMenuLevel(MenuLevelEnum.LEVEL_ONE.getLevel()); // 菜单层级（1：一级菜单 2；二级菜单 3：三级菜单 4：四级菜单）
        fleaMenu.setMenuState(EntityStateEnum.IN_USE.getState()); // 菜单状态（0:下线，1: 在用 ）
        fleaMenu.setParentId(CommonConstants.NumeralConstants.MINUS_ONE); // 父菜单编号
        fleaMenu.setCreateDate(DateUtils.getCurrentTime());
        fleaMenu.setEffectiveDate(fleaMenu.getCreateDate());
        fleaMenu.setExpiryDate(DateUtils.getExpiryTimeForever());
        fleaMenu.setRemarks("控制台，展示收藏夹，快捷菜单入口");

        try {
            IFleaMenuSV fleaMenuSV = (IFleaMenuSV) applicationContext.getBean("fleaMenuSV");
            fleaMenuSV.save(fleaMenu);
        } catch (CommonException e) {
            LOGGER.error("Exception: ", e);
        }

    }

    @Test
    public void testSaveMenuAttr() {

        FleaFunctionAttr fleaFunctionAttr = new FleaFunctionAttr();

        // 功能编号
        fleaFunctionAttr.setFunctionId(1000L);
        // 功能类型(菜单、操作、元素)
        fleaFunctionAttr.setFunctionType(FunctionTypeEnum.MENU.getType());
        // 属性码
        fleaFunctionAttr.setAttrCode(FleaAuthConstants.AttrCodeConstants.ATTR_CODE_SYSTEM_IN_USE);
        // 属性值
        fleaFunctionAttr.setAttrValue("1000");
        // 属性状态(0: 删除 1: 正常）
        fleaFunctionAttr.setState(EntityStateEnum.IN_USE.getState());
        fleaFunctionAttr.setCreateDate(DateUtils.getCurrentTime());
        fleaFunctionAttr.setEffectiveDate(fleaFunctionAttr.getCreateDate());
        fleaFunctionAttr.setExpiryDate(DateUtils.getExpiryTimeForever());
        fleaFunctionAttr.setRemarks("【跳蚤管家】正在使用中");

        try {
            IFleaFunctionAttrSV fleaFunctionAttrSV = (IFleaFunctionAttrSV) applicationContext.getBean("fleaFunctionAttrSV");
            fleaFunctionAttrSV.save(fleaFunctionAttr);
        } catch (CommonException e) {
            LOGGER.error("Exception: ", e);
        }

    }

    @Test
    public void testSaveMenuForAuth() throws Exception {
        Long parentId = addAuthMgmt();
        Long userParentId = addUserMgmt(parentId);
        Long roleParentId = addRoleMgmt(parentId);
        Long privilegeParentId = addPrivilegeMgmt(parentId);
        Long functionParentId = addFunctionMgmt(parentId);
        addUserMgmtSubMenu(userParentId);
        addRoleMgmtSubMenu(roleParentId);
        addPrivilegeMgmtSubMenu(privilegeParentId);
        addFunctionMgmtSubMenu(functionParentId);
    }

    private Long addAuthMgmt() throws Exception {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("auth_mgmt");
        fleaMenuPOJO.setMenuName("授权管理");
        fleaMenuPOJO.setMenuIcon("font");
        fleaMenuPOJO.setMenuSort(2);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_ONE.getLevel());
        fleaMenuPOJO.setRemarks("授权管理，包含用户管理，角色管理，权限管理，功能管理");

        IFleaMenuSV fleaMenuSV = (IFleaMenuSV) applicationContext.getBean("fleaMenuSV");
        FleaMenu fleaMenu = fleaMenuSV.saveFleaMenu(fleaMenuPOJO);

        FleaFunctionAttrPOJO fleaFunctionAttrPOJO = new FleaFunctionAttrPOJO();
        fleaFunctionAttrPOJO.setFunctionId(fleaMenu.getMenuId());
        fleaFunctionAttrPOJO.setFunctionType(FunctionTypeEnum.MENU.getType());
        fleaFunctionAttrPOJO.setAttrCode(FleaAuthConstants.AttrCodeConstants.ATTR_CODE_SYSTEM_IN_USE);
        fleaFunctionAttrPOJO.setAttrValue("1000");
        fleaFunctionAttrPOJO.setRemarks("【跳蚤管家】正在使用中");

        IFleaFunctionAttrSV fleaFunctionAttrSV = (IFleaFunctionAttrSV) applicationContext.getBean("fleaFunctionAttrSV");
        fleaFunctionAttrSV.saveFunctionAttr(fleaFunctionAttrPOJO);
        return fleaMenu.getMenuId();
    }

    // 添加《用户管理》菜单
    private Long addUserMgmt(Long parentId) throws Exception {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("user_mgmt");
        fleaMenuPOJO.setMenuName("用户管理");
        fleaMenuPOJO.setMenuIcon("users");
        fleaMenuPOJO.setMenuSort(1);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_TWO.getLevel());
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("用户管理，包含用户（组）新增，用户（组）授权等");

        IFleaMenuSV fleaMenuSV = (IFleaMenuSV) applicationContext.getBean("fleaMenuSV");
        FleaMenu fleaMenu = fleaMenuSV.saveFleaMenu(fleaMenuPOJO);

        FleaFunctionAttrPOJO fleaFunctionAttrPOJO = new FleaFunctionAttrPOJO();
        fleaFunctionAttrPOJO.setFunctionId(fleaMenu.getMenuId());
        fleaFunctionAttrPOJO.setFunctionType(FunctionTypeEnum.MENU.getType());
        fleaFunctionAttrPOJO.setAttrCode(FleaAuthConstants.AttrCodeConstants.ATTR_CODE_SYSTEM_IN_USE);
        fleaFunctionAttrPOJO.setAttrValue("1000");
        fleaFunctionAttrPOJO.setRemarks("【跳蚤管家】正在使用中");

        IFleaFunctionAttrSV fleaFunctionAttrSV = (IFleaFunctionAttrSV) applicationContext.getBean("fleaFunctionAttrSV");
        fleaFunctionAttrSV.saveFunctionAttr(fleaFunctionAttrPOJO);
        return fleaMenu.getMenuId();
    }

    // 添加《角色管理》菜单
    private Long addRoleMgmt(Long parentId) throws Exception {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("role_mgmt");
        fleaMenuPOJO.setMenuName("角色管理");
        fleaMenuPOJO.setMenuIcon("user");
        fleaMenuPOJO.setMenuSort(2);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_TWO.getLevel());
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("角色管理，包含角色（组）新增，角色（组）变更，角色（组）授权等");

        IFleaMenuSV fleaMenuSV = (IFleaMenuSV) applicationContext.getBean("fleaMenuSV");
        FleaMenu fleaMenu = fleaMenuSV.saveFleaMenu(fleaMenuPOJO);

        FleaFunctionAttrPOJO fleaFunctionAttrPOJO = new FleaFunctionAttrPOJO();
        fleaFunctionAttrPOJO.setFunctionId(fleaMenu.getMenuId());
        fleaFunctionAttrPOJO.setFunctionType(FunctionTypeEnum.MENU.getType());
        fleaFunctionAttrPOJO.setAttrCode(FleaAuthConstants.AttrCodeConstants.ATTR_CODE_SYSTEM_IN_USE);
        fleaFunctionAttrPOJO.setAttrValue("1000");
        fleaFunctionAttrPOJO.setRemarks("【跳蚤管家】正在使用中");

        IFleaFunctionAttrSV fleaFunctionAttrSV = (IFleaFunctionAttrSV) applicationContext.getBean("fleaFunctionAttrSV");
        fleaFunctionAttrSV.saveFunctionAttr(fleaFunctionAttrPOJO);
        return fleaMenu.getMenuId();
    }

    // 添加《权限管理》菜单
    private Long addPrivilegeMgmt(Long parentId) throws Exception {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("privilege_mgmt");
        fleaMenuPOJO.setMenuName("权限管理");
        fleaMenuPOJO.setMenuIcon("lock");
        fleaMenuPOJO.setMenuSort(3);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_TWO.getLevel());
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("权限管理，包含权限（组）新增，权限（组）变更，功能授权等");

        IFleaMenuSV fleaMenuSV = (IFleaMenuSV) applicationContext.getBean("fleaMenuSV");
        FleaMenu fleaMenu = fleaMenuSV.saveFleaMenu(fleaMenuPOJO);

        FleaFunctionAttrPOJO fleaFunctionAttrPOJO = new FleaFunctionAttrPOJO();
        fleaFunctionAttrPOJO.setFunctionId(fleaMenu.getMenuId());
        fleaFunctionAttrPOJO.setFunctionType(FunctionTypeEnum.MENU.getType());
        fleaFunctionAttrPOJO.setAttrCode(FleaAuthConstants.AttrCodeConstants.ATTR_CODE_SYSTEM_IN_USE);
        fleaFunctionAttrPOJO.setAttrValue("1000");
        fleaFunctionAttrPOJO.setRemarks("【跳蚤管家】正在使用中");

        IFleaFunctionAttrSV fleaFunctionAttrSV = (IFleaFunctionAttrSV) applicationContext.getBean("fleaFunctionAttrSV");
        fleaFunctionAttrSV.saveFunctionAttr(fleaFunctionAttrPOJO);
        return fleaMenu.getMenuId();
    }

    // 添加《功能管理》菜单
    private Long addFunctionMgmt(Long parentId) throws Exception {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("function_mgmt");
        fleaMenuPOJO.setMenuName("功能管理");
        fleaMenuPOJO.setMenuIcon("gears");
        fleaMenuPOJO.setMenuSort(4);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_TWO.getLevel());
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("功能管理，包含菜单管理，操作管理，元素管理等");

        IFleaMenuSV fleaMenuSV = (IFleaMenuSV) applicationContext.getBean("fleaMenuSV");
        FleaMenu fleaMenu = fleaMenuSV.saveFleaMenu(fleaMenuPOJO);

        FleaFunctionAttrPOJO fleaFunctionAttrPOJO = new FleaFunctionAttrPOJO();
        fleaFunctionAttrPOJO.setFunctionId(fleaMenu.getMenuId());
        fleaFunctionAttrPOJO.setFunctionType(FunctionTypeEnum.MENU.getType());
        fleaFunctionAttrPOJO.setAttrCode(FleaAuthConstants.AttrCodeConstants.ATTR_CODE_SYSTEM_IN_USE);
        fleaFunctionAttrPOJO.setAttrValue("1000");
        fleaFunctionAttrPOJO.setRemarks("【跳蚤管家】正在使用中");

        IFleaFunctionAttrSV fleaFunctionAttrSV = (IFleaFunctionAttrSV) applicationContext.getBean("fleaFunctionAttrSV");
        fleaFunctionAttrSV.saveFunctionAttr(fleaFunctionAttrPOJO);
        return fleaMenu.getMenuId();
    }

    // 添加用户管理子菜单
    private void addUserMgmtSubMenu(Long parentId) throws Exception{

    }

    // 添加角色管理子菜单
    private void addRoleMgmtSubMenu(Long parentId) throws Exception{

    }

    // 添加权限管理子菜单
    private void addPrivilegeMgmtSubMenu(Long parentId) {

    }

    // 添加功能管理子菜单
    private void addFunctionMgmtSubMenu(Long parentId) {

    }

    @Test
    public void testFunctionAttrQuery() {

        try {
            IFleaFunctionAttrSV fleaFunctionAttrSV = (IFleaFunctionAttrSV) applicationContext.getBean("fleaFunctionAttrSV");
            fleaFunctionAttrSV.getFunctionAttrList(null, FunctionTypeEnum.MENU.getType(), FleaAuthConstants.AttrCodeConstants.ATTR_CODE_SYSTEM_IN_USE);
        } catch (CommonException e) {
            LOGGER.error("Exception: ", e);
        }

    }
}
