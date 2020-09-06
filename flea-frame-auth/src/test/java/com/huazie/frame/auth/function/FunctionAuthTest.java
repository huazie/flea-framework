package com.huazie.frame.auth.function;

import com.huazie.frame.auth.base.function.entity.FleaFunctionAttr;
import com.huazie.frame.auth.base.function.entity.FleaMenu;
import com.huazie.frame.auth.base.function.service.interfaces.IFleaFunctionAttrSV;
import com.huazie.frame.auth.base.function.service.interfaces.IFleaMenuSV;
import com.huazie.frame.auth.base.privilege.entity.FleaPrivilegeRel;
import com.huazie.frame.auth.base.privilege.service.interfaces.IFleaPrivilegeRelSV;
import com.huazie.frame.auth.common.FleaAuthConstants;
import com.huazie.frame.auth.common.FunctionTypeEnum;
import com.huazie.frame.auth.common.MenuLevelEnum;
import com.huazie.frame.auth.common.pojo.function.attr.FleaFunctionAttrPOJO;
import com.huazie.frame.auth.common.pojo.function.menu.FleaMenuPOJO;
import com.huazie.frame.auth.common.pojo.privilege.FleaPrivilegeGroupRelPOJO;
import com.huazie.frame.auth.common.service.interfaces.IFleaFunctionModuleSV;
import com.huazie.frame.auth.common.service.interfaces.IFleaPrivilegeModuleSV;
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

import java.util.ArrayList;
import java.util.List;

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

        Long userModuleParentId = addUserModuleMgmt(parentId);
        Long roleModuleParentId = addRoleModuleMgmt(parentId);
        Long privilegeModuleParentId = addPrivilegeModuleMgmt(parentId);
        Long functionModuleParentId = addFunctionModuleMgmt(parentId);

        Long userMgmtParentId = addUserMgmt(userModuleParentId);
        Long userGroupMgmtParentId = addUserGroupMgmt(userModuleParentId);

        Long roleMgmtParentId = addRoleMgmt(roleModuleParentId);
        Long roleGroupMgmtParentId = addRoleGroupMgmt(roleModuleParentId);

        Long privilegeMgmtParentId = addPrivilegeMgmt(privilegeModuleParentId);
        Long privilegeGroupMgmtParentId = addPrivilegeGroupMgmt(privilegeModuleParentId);

        Long menuMgmtParentId = addMenuMgmt(functionModuleParentId);
        Long operationMgmtParentId = addOperationMgmt(functionModuleParentId);
        Long elementMgmtParentId = addElementMgmt(functionModuleParentId);

        Long privilegeGroupId = 1000L;
        addPrivilegeGroupRel(parentId, privilegeGroupId);

        addPrivilegeGroupRel(userModuleParentId, privilegeGroupId);
        addPrivilegeGroupRel(roleModuleParentId, privilegeGroupId);
        addPrivilegeGroupRel(privilegeModuleParentId, privilegeGroupId);
        addPrivilegeGroupRel(functionModuleParentId, privilegeGroupId);

        addPrivilegeGroupRel(userMgmtParentId, privilegeGroupId);
        addPrivilegeGroupRel(userGroupMgmtParentId, privilegeGroupId);

        addPrivilegeGroupRel(roleMgmtParentId, privilegeGroupId);
        addPrivilegeGroupRel(roleGroupMgmtParentId, privilegeGroupId);

        addPrivilegeGroupRel(privilegeMgmtParentId, privilegeGroupId);
        addPrivilegeGroupRel(privilegeGroupMgmtParentId, privilegeGroupId);

        addPrivilegeGroupRel(menuMgmtParentId, privilegeGroupId);
        addPrivilegeGroupRel(operationMgmtParentId, privilegeGroupId);
        addPrivilegeGroupRel(elementMgmtParentId, privilegeGroupId);
    }

    private Long addAuthMgmt() throws Exception {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("auth_mgmt");
        fleaMenuPOJO.setMenuName("授权管理");
        fleaMenuPOJO.setMenuIcon("font");
        fleaMenuPOJO.setMenuSort(2);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_ONE.getLevel());
        fleaMenuPOJO.setRemarks("授权管理，包含用户模块管理，角色模块管理，权限模块管理，功能模块管理");

        IFleaFunctionModuleSV fleaFunctionModuleSV = (IFleaFunctionModuleSV) applicationContext.getBean("fleaFunctionModuleSV");
        return fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO, addFleaFunctionAttrPOJOList());
    }

    // 添加《用户模块管理》菜单
    private Long addUserModuleMgmt(Long parentId) throws Exception {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("user_module_mgmt");
        fleaMenuPOJO.setMenuName("用户模块管理");
        fleaMenuPOJO.setMenuIcon("user-circle");
        fleaMenuPOJO.setMenuSort(1);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_TWO.getLevel());
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("用户模块管理，包含用户管理，用户组管理");

        IFleaFunctionModuleSV fleaFunctionModuleSV = (IFleaFunctionModuleSV) applicationContext.getBean("fleaFunctionModuleSV");
        return fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO, addFleaFunctionAttrPOJOList());
    }

    // 添加《角色模块管理》菜单
    private Long addRoleModuleMgmt(Long parentId) throws Exception {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("role_module_mgmt");
        fleaMenuPOJO.setMenuName("角色模块管理");
        fleaMenuPOJO.setMenuIcon("user-secret");
        fleaMenuPOJO.setMenuSort(2);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_TWO.getLevel());
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("角色模块管理，包含角色管理，角色组管理");

        IFleaFunctionModuleSV fleaFunctionModuleSV = (IFleaFunctionModuleSV) applicationContext.getBean("fleaFunctionModuleSV");
        return fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO, addFleaFunctionAttrPOJOList());
    }

    // 添加《权限模块管理》菜单
    private Long addPrivilegeModuleMgmt(Long parentId) throws Exception {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("privilege_module_mgmt");
        fleaMenuPOJO.setMenuName("权限模块管理");
        fleaMenuPOJO.setMenuIcon("lock");
        fleaMenuPOJO.setMenuSort(3);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_TWO.getLevel());
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("权限模块管理，包含权限管理，权限组管理");

        IFleaFunctionModuleSV fleaFunctionModuleSV = (IFleaFunctionModuleSV) applicationContext.getBean("fleaFunctionModuleSV");
        return fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO, addFleaFunctionAttrPOJOList());
    }

    // 添加《功能模块管理》菜单
    private Long addFunctionModuleMgmt(Long parentId) throws Exception {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("function_module_mgmt");
        fleaMenuPOJO.setMenuName("功能模块管理");
        fleaMenuPOJO.setMenuIcon("gears");
        fleaMenuPOJO.setMenuSort(4);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_TWO.getLevel());
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("功能模块管理，包含菜单管理，操作管理，元素管理");

        IFleaFunctionModuleSV fleaFunctionModuleSV = (IFleaFunctionModuleSV) applicationContext.getBean("fleaFunctionModuleSV");
        return fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO, addFleaFunctionAttrPOJOList());
    }

    // 添加《用户管理》菜单
    private Long addUserMgmt(Long parentId) throws Exception {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("user_mgmt");
        fleaMenuPOJO.setMenuName("用户管理");
        fleaMenuPOJO.setMenuIcon("user");
        fleaMenuPOJO.setMenuSort(1);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_THREE.getLevel());
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("用户管理，包含用户注册，用户变更，用户授权");

        IFleaFunctionModuleSV fleaFunctionModuleSV = (IFleaFunctionModuleSV) applicationContext.getBean("fleaFunctionModuleSV");
        return fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO, addFleaFunctionAttrPOJOList());
    }

    // 添加《用户组管理》菜单
    private Long addUserGroupMgmt(Long parentId) throws Exception {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("user_group_mgmt");
        fleaMenuPOJO.setMenuName("用户组管理");
        fleaMenuPOJO.setMenuIcon("users");
        fleaMenuPOJO.setMenuSort(2);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_THREE.getLevel());
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("用户组管理，包含用户组新增，用户组变更，用户组授权");

        IFleaFunctionModuleSV fleaFunctionModuleSV = (IFleaFunctionModuleSV) applicationContext.getBean("fleaFunctionModuleSV");
        return fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO, addFleaFunctionAttrPOJOList());
    }

    // 添加《角色管理》菜单
    private Long addRoleMgmt(Long parentId) throws Exception {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("role_mgmt");
        fleaMenuPOJO.setMenuName("角色管理");
        fleaMenuPOJO.setMenuIcon("user");
        fleaMenuPOJO.setMenuSort(1);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_THREE.getLevel());
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("角色管理，包含角色新增，角色变更，角色授权");

        IFleaFunctionModuleSV fleaFunctionModuleSV = (IFleaFunctionModuleSV) applicationContext.getBean("fleaFunctionModuleSV");
        return fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO, addFleaFunctionAttrPOJOList());
    }

    // 添加《角色组管理》菜单
    private Long addRoleGroupMgmt(Long parentId) throws Exception {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("role_group_mgmt");
        fleaMenuPOJO.setMenuName("角色组管理");
        fleaMenuPOJO.setMenuIcon("users");
        fleaMenuPOJO.setMenuSort(2);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_THREE.getLevel());
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("角色组管理，包含角色组新增，角色组变更，角色组关联");

        IFleaFunctionModuleSV fleaFunctionModuleSV = (IFleaFunctionModuleSV) applicationContext.getBean("fleaFunctionModuleSV");
        return fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO, addFleaFunctionAttrPOJOList());
    }

    // 添加《权限管理》菜单
    private Long addPrivilegeMgmt(Long parentId) throws Exception {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("privilege_mgmt");
        fleaMenuPOJO.setMenuName("权限管理");
        fleaMenuPOJO.setMenuIcon("tag");
        fleaMenuPOJO.setMenuSort(1);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_THREE.getLevel());
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("权限管理，包含权限新增，权限变更");

        IFleaFunctionModuleSV fleaFunctionModuleSV = (IFleaFunctionModuleSV) applicationContext.getBean("fleaFunctionModuleSV");
        return fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO, addFleaFunctionAttrPOJOList());
    }

    // 添加《权限组管理》菜单
    private Long addPrivilegeGroupMgmt(Long parentId) throws Exception {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("privilege_group_mgmt");
        fleaMenuPOJO.setMenuName("权限组管理");
        fleaMenuPOJO.setMenuIcon("tags");
        fleaMenuPOJO.setMenuSort(2);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_THREE.getLevel());
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("权限组管理，包含权限组新增，权限组变更，权限组关联");

        IFleaFunctionModuleSV fleaFunctionModuleSV = (IFleaFunctionModuleSV) applicationContext.getBean("fleaFunctionModuleSV");
        return fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO, addFleaFunctionAttrPOJOList());
    }

    // 添加《菜单管理》菜单
    private Long addMenuMgmt(Long parentId) throws Exception {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("menu_mgmt");
        fleaMenuPOJO.setMenuName("菜单管理");
        fleaMenuPOJO.setMenuIcon("list-alt");
        fleaMenuPOJO.setMenuSort(1);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_THREE.getLevel());
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("菜单管理，包含菜单新增，菜单变更，菜单授权");

        IFleaFunctionModuleSV fleaFunctionModuleSV = (IFleaFunctionModuleSV) applicationContext.getBean("fleaFunctionModuleSV");
        return fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO, addFleaFunctionAttrPOJOList());
    }

    // 添加《操作管理》菜单
    private Long addOperationMgmt(Long parentId) throws Exception {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("operation_mgmt");
        fleaMenuPOJO.setMenuName("操作管理");
        fleaMenuPOJO.setMenuIcon("wrench");
        fleaMenuPOJO.setMenuSort(2);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_THREE.getLevel());
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("操作管理，包含操作新增，操作变更，操作授权");

        IFleaFunctionModuleSV fleaFunctionModuleSV = (IFleaFunctionModuleSV) applicationContext.getBean("fleaFunctionModuleSV");
        return fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO, addFleaFunctionAttrPOJOList());
    }

    // 添加《元素管理》菜单
    private Long addElementMgmt(Long parentId) throws Exception {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("element_mgmt");
        fleaMenuPOJO.setMenuName("元素管理");
        fleaMenuPOJO.setMenuIcon("code");
        fleaMenuPOJO.setMenuSort(3);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_THREE.getLevel());
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("元素管理，包含元素新增，元素变更，元素授权");

        IFleaFunctionModuleSV fleaFunctionModuleSV = (IFleaFunctionModuleSV) applicationContext.getBean("fleaFunctionModuleSV");
        return fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO, addFleaFunctionAttrPOJOList());
    }

    // 添加 菜单属性
    private List<FleaFunctionAttrPOJO> addFleaFunctionAttrPOJOList() {
        FleaFunctionAttrPOJO fleaFunctionAttrPOJO = new FleaFunctionAttrPOJO();
        fleaFunctionAttrPOJO.setAttrCode(FleaAuthConstants.AttrCodeConstants.ATTR_CODE_SYSTEM_IN_USE);
        fleaFunctionAttrPOJO.setAttrValue("1000");
        fleaFunctionAttrPOJO.setRemarks("【跳蚤管家】正在使用中");
        List<FleaFunctionAttrPOJO> fleaFunctionAttrPOJOList = new ArrayList<>();
        fleaFunctionAttrPOJOList.add(fleaFunctionAttrPOJO);
        return fleaFunctionAttrPOJOList;
    }

    // 添加权限组关联
    private void addPrivilegeGroupRel(Long menuId, Long privilegeGroupId) throws Exception {
        // 权限关联，获取权限编号
        IFleaPrivilegeRelSV fleaPrivilegeRelSV = (IFleaPrivilegeRelSV) applicationContext.getBean("fleaPrivilegeRelSV");
        FleaPrivilegeRel fleaPrivilegeRelMenu = fleaPrivilegeRelSV.getPrivilegeRelMenu(menuId);
        if (null != fleaPrivilegeRelMenu) {
            FleaPrivilegeGroupRelPOJO fleaPrivilegeGroupRelPOJO = new FleaPrivilegeGroupRelPOJO();
            fleaPrivilegeGroupRelPOJO.setPrivilegeGroupId(privilegeGroupId);
            fleaPrivilegeGroupRelPOJO.setRelId(fleaPrivilegeRelMenu.getPrivilegeId());
            IFleaPrivilegeModuleSV fleaPrivilegeModuleSV = (IFleaPrivilegeModuleSV) applicationContext.getBean("fleaPrivilegeModuleSV");
            fleaPrivilegeModuleSV.addPrivilegeGroupRel(fleaPrivilegeGroupRelPOJO);
        }
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
