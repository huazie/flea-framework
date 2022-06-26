package com.huazie.fleaframework.auth.base.function.menu;

import com.huazie.fleaframework.auth.common.MenuLevelEnum;
import com.huazie.fleaframework.auth.common.pojo.function.attr.FleaFunctionAttrPOJO;
import com.huazie.fleaframework.auth.common.pojo.function.menu.FleaMenuPOJO;
import com.huazie.fleaframework.auth.common.service.interfaces.IFleaFunctionModuleSV;
import com.huazie.fleaframework.auth.util.FleaAuthPOJOUtils;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;

import java.util.List;

/**
 * 授权管理菜单
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class AddFleaMenu {

    private IFleaFunctionModuleSV fleaFunctionModuleSV;

    public AddFleaMenu(IFleaFunctionModuleSV fleaFunctionModuleSV) {
        this.fleaFunctionModuleSV = fleaFunctionModuleSV;
    }

    /**
     * 添加 Flea管家 菜单
      */
    public void addFleaMgmtMenu() throws CommonException {
        addConsoleMenu();
    }

    // 添加《控制台》菜单
    private Long addConsoleMenu() throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("console"); // 菜单编码
        fleaMenuPOJO.setMenuName("控制台"); // 菜单名称
        fleaMenuPOJO.setMenuIcon("dashboard"); // 菜单FontAwesome小图标
        fleaMenuPOJO.setMenuSort(1);  // 菜单展示顺序(同一个父菜单下)
        fleaMenuPOJO.setMenuView("mgmt/console.html"); // 菜单对应页面（非叶子菜单的可以为空）
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_ONE.getLevel()); // 菜单层级（1：一级菜单 2；二级菜单 3：三级菜单 4：四级菜单）
        fleaMenuPOJO.setRemarks("控制台，展示收藏夹，快捷菜单入口");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        return fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    /**
     * 添加授权管理相关菜单
     */
    public void addAuthMgmtMenu() throws CommonException {
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
        Long resourceMgmtParentId = addResourceMgmt(functionModuleParentId);

        userRegister(userMgmtParentId);
        userModify(userMgmtParentId);
        userAuth(userMgmtParentId);

        userGroupAdd(userGroupMgmtParentId);
        userGroupModify(userGroupMgmtParentId);
        userGroupAuth(userGroupMgmtParentId);

        roleAdd(roleMgmtParentId);
        roleModify(roleMgmtParentId);
        roleAuth(roleMgmtParentId);

        roleGroupAdd(roleGroupMgmtParentId);
        roleGroupModify(roleGroupMgmtParentId);
        roleGroupRel(roleGroupMgmtParentId);

        privilegeAdd(privilegeMgmtParentId);
        privilegeModify(privilegeMgmtParentId);
        privilegeRel(privilegeMgmtParentId);

        privilegeGroupAdd(privilegeGroupMgmtParentId);
        privilegeGroupModify(privilegeGroupMgmtParentId);
        privilegeGroupRel(privilegeGroupMgmtParentId);

        menuAdd(menuMgmtParentId);
        menuModify(menuMgmtParentId);

        operationAdd(operationMgmtParentId);
        operationModify(operationMgmtParentId);

        elementAdd(elementMgmtParentId);
        elementModify(elementMgmtParentId);

        resourceAdd(resourceMgmtParentId);
        resourceModify(resourceMgmtParentId);
    }

    // 添加《授权管理》菜单
    private Long addAuthMgmt() throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("auth_mgmt");
        fleaMenuPOJO.setMenuName("授权管理");
        fleaMenuPOJO.setMenuIcon("font");
        fleaMenuPOJO.setMenuSort(2);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_ONE.getLevel());
        fleaMenuPOJO.setRemarks("授权管理，包含用户模块管理、角色模块管理、权限模块管理和功能模块管理");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        return fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加《用户模块管理》菜单
    private Long addUserModuleMgmt(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("user_module_mgmt");
        fleaMenuPOJO.setMenuName("用户模块管理");
        fleaMenuPOJO.setMenuIcon("user-circle");
        fleaMenuPOJO.setMenuSort(1);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_TWO.getLevel());
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("用户模块管理，包含用户管理、用户组管理");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        return fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加《角色模块管理》菜单
    private Long addRoleModuleMgmt(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("role_module_mgmt");
        fleaMenuPOJO.setMenuName("角色模块管理");
        fleaMenuPOJO.setMenuIcon("user-secret");
        fleaMenuPOJO.setMenuSort(2);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_TWO.getLevel());
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("角色模块管理，包含角色管理、角色组管理");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        return fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加《权限模块管理》菜单
    private Long addPrivilegeModuleMgmt(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("privilege_module_mgmt");
        fleaMenuPOJO.setMenuName("权限模块管理");
        fleaMenuPOJO.setMenuIcon("lock");
        fleaMenuPOJO.setMenuSort(3);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_TWO.getLevel());
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("权限模块管理，包含权限管理、权限组管理");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        return fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加《功能模块管理》菜单
    private Long addFunctionModuleMgmt(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("function_module_mgmt");
        fleaMenuPOJO.setMenuName("功能模块管理");
        fleaMenuPOJO.setMenuIcon("gears");
        fleaMenuPOJO.setMenuSort(4);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_TWO.getLevel());
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("功能模块管理，包含菜单管理、操作管理、元素管理和资源管理");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        return fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加《用户管理》菜单
    private Long addUserMgmt(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("user_mgmt");
        fleaMenuPOJO.setMenuName("用户管理");
        fleaMenuPOJO.setMenuIcon("user");
        fleaMenuPOJO.setMenuSort(1);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_THREE.getLevel());
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("用户管理，包含用户注册、用户变更、用户授权");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        return fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加《用户组管理》菜单
    private Long addUserGroupMgmt(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("user_group_mgmt");
        fleaMenuPOJO.setMenuName("用户组管理");
        fleaMenuPOJO.setMenuIcon("users");
        fleaMenuPOJO.setMenuSort(2);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_THREE.getLevel());
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("用户组管理，包含用户组新增、用户组变更、用户组授权");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        return fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加《角色管理》菜单
    private Long addRoleMgmt(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("role_mgmt");
        fleaMenuPOJO.setMenuName("角色管理");
        fleaMenuPOJO.setMenuIcon("user");
        fleaMenuPOJO.setMenuSort(1);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_THREE.getLevel());
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("角色管理，包含角色新增、角色变更、角色授权");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        return fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加《角色组管理》菜单
    private Long addRoleGroupMgmt(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("role_group_mgmt");
        fleaMenuPOJO.setMenuName("角色组管理");
        fleaMenuPOJO.setMenuIcon("users");
        fleaMenuPOJO.setMenuSort(2);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_THREE.getLevel());
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("角色组管理，包含角色组新增、角色组变更、角色组关联");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        return fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加《权限管理》菜单
    private Long addPrivilegeMgmt(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("privilege_mgmt");
        fleaMenuPOJO.setMenuName("权限管理");
        fleaMenuPOJO.setMenuIcon("tag");
        fleaMenuPOJO.setMenuSort(1);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_THREE.getLevel());
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("权限管理，包含权限新增、权限变更、权限关联");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        return fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加《权限组管理》菜单
    private Long addPrivilegeGroupMgmt(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("privilege_group_mgmt");
        fleaMenuPOJO.setMenuName("权限组管理");
        fleaMenuPOJO.setMenuIcon("tags");
        fleaMenuPOJO.setMenuSort(2);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_THREE.getLevel());
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("权限组管理，包含权限组新增、权限组变更、权限组关联");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        return fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加《菜单管理》菜单
    private Long addMenuMgmt(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("menu_mgmt");
        fleaMenuPOJO.setMenuName("菜单管理");
        fleaMenuPOJO.setMenuIcon("list-alt");
        fleaMenuPOJO.setMenuSort(1);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_THREE.getLevel());
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("菜单管理，包含菜单新增、菜单变更");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        return fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加《操作管理》菜单
    private Long addOperationMgmt(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("operation_mgmt");
        fleaMenuPOJO.setMenuName("操作管理");
        fleaMenuPOJO.setMenuIcon("wrench");
        fleaMenuPOJO.setMenuSort(2);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_THREE.getLevel());
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("操作管理，包含操作新增、操作变更");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        return fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加《元素管理》菜单
    private Long addElementMgmt(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("element_mgmt");
        fleaMenuPOJO.setMenuName("元素管理");
        fleaMenuPOJO.setMenuIcon("code");
        fleaMenuPOJO.setMenuSort(3);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_THREE.getLevel());
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("元素管理，包含元素新增、元素变更");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        return fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加《资源管理》菜单
    private Long addResourceMgmt(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("resource_mgmt");
        fleaMenuPOJO.setMenuName("资源管理");
        fleaMenuPOJO.setMenuIcon("link");
        fleaMenuPOJO.setMenuSort(4);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_THREE.getLevel());
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("资源管理，包含资源新增、资源变更");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        return fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加 菜单扩展属性【归属系统】
    private List<FleaFunctionAttrPOJO> addFleaFunctionAttrPOJOList() {
        String systemId = "1001";
        String systemName = "Flea管家";
        return CollectionUtils.newArrayList(FleaAuthPOJOUtils.newSystemInUseAttr(systemId, systemName));
    }

    // 添加 《用户注册》菜单
    private void userRegister(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("user_register");
        fleaMenuPOJO.setMenuName("用户注册");
        fleaMenuPOJO.setMenuIcon("user-plus");
        fleaMenuPOJO.setMenuSort(1);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_FOUR.getLevel());
        fleaMenuPOJO.setMenuView("auth/user-module/user/userRegister.html");
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("该菜单用于注册新用户，包含系统用户和操作用户");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加 《用户变更》菜单
    private void userModify(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("user_modify");
        fleaMenuPOJO.setMenuName("用户变更");
        fleaMenuPOJO.setMenuIcon("edit");
        fleaMenuPOJO.setMenuSort(2);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_FOUR.getLevel());
        fleaMenuPOJO.setMenuView("auth/user-module/user/userModify.html");
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("该菜单用于修改用户信息");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加 《用户授权》菜单
    private void userAuth(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("user_auth");
        fleaMenuPOJO.setMenuName("用户授权");
        fleaMenuPOJO.setMenuIcon("arrows");
        fleaMenuPOJO.setMenuSort(3);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_FOUR.getLevel());
        fleaMenuPOJO.setMenuView("auth/user-module/user/userAuth.html");
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("该菜单用于新增或修改用户关联角色（角色组）数据");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加 《用户组新增》菜单
    private void userGroupAdd(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("user_group_add");
        fleaMenuPOJO.setMenuName("用户组新增");
        fleaMenuPOJO.setMenuIcon("plus-circle");
        fleaMenuPOJO.setMenuSort(1);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_FOUR.getLevel());
        fleaMenuPOJO.setMenuView("auth/user-module/user-group/userGroupAdd.html");
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("该菜单用于新增用户组数据");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加 《用户组变更》菜单
    private void userGroupModify(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("user_group_modify");
        fleaMenuPOJO.setMenuName("用户组变更");
        fleaMenuPOJO.setMenuIcon("edit");
        fleaMenuPOJO.setMenuSort(2);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_FOUR.getLevel());
        fleaMenuPOJO.setMenuView("auth/user-module/user-group/userGroupModify.html");
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("该菜单用于修改用户组数据");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加 《用户组授权》菜单
    private void userGroupAuth(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("user_group_auth");
        fleaMenuPOJO.setMenuName("用户组授权");
        fleaMenuPOJO.setMenuIcon("arrows");
        fleaMenuPOJO.setMenuSort(3);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_FOUR.getLevel());
        fleaMenuPOJO.setMenuView("auth/user-module/user-group/userGroupAuth.html");
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("该菜单用于新增或修改用户组关联角色（角色组）数据");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加 《角色新增》菜单
    private void roleAdd(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("role_add");
        fleaMenuPOJO.setMenuName("角色新增");
        fleaMenuPOJO.setMenuIcon("plus-circle");
        fleaMenuPOJO.setMenuSort(1);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_FOUR.getLevel());
        fleaMenuPOJO.setMenuView("auth/role-module/role/roleAdd.html");
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("该菜单用于新增角色数据");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加 《角色变更》菜单
    private void roleModify(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("role_modify");
        fleaMenuPOJO.setMenuName("角色变更");
        fleaMenuPOJO.setMenuIcon("edit");
        fleaMenuPOJO.setMenuSort(2);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_FOUR.getLevel());
        fleaMenuPOJO.setMenuView("auth/role-module/role/roleModify.html");
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("该菜单用于修改角色数据");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加 《角色授权》菜单
    private void roleAuth(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("role_auth");
        fleaMenuPOJO.setMenuName("角色授权");
        fleaMenuPOJO.setMenuIcon("arrows");
        fleaMenuPOJO.setMenuSort(3);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_FOUR.getLevel());
        fleaMenuPOJO.setMenuView("auth/role-module/role/roleAuth.html");
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("该菜单用于新增或修改角色关联权限（权限组）数据");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加 《角色组新增》菜单
    private void roleGroupAdd(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("role_group_add");
        fleaMenuPOJO.setMenuName("角色组新增");
        fleaMenuPOJO.setMenuIcon("plus-circle");
        fleaMenuPOJO.setMenuSort(1);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_FOUR.getLevel());
        fleaMenuPOJO.setMenuView("auth/role-module/role-group/roleGroupAdd.html");
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("该菜单用于新增角色组数据");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加 《角色组变更》菜单
    private void roleGroupModify(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("role_group_modify");
        fleaMenuPOJO.setMenuName("角色组变更");
        fleaMenuPOJO.setMenuIcon("edit");
        fleaMenuPOJO.setMenuSort(2);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_FOUR.getLevel());
        fleaMenuPOJO.setMenuView("auth/role-module/role-group/roleGroupModify.html");
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("该菜单用于修改角色组数据");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加 《角色组关联》菜单
    private void roleGroupRel(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("role_group_rel");
        fleaMenuPOJO.setMenuName("角色组关联");
        fleaMenuPOJO.setMenuIcon("exchange");
        fleaMenuPOJO.setMenuSort(3);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_FOUR.getLevel());
        fleaMenuPOJO.setMenuView("auth/role-module/role-group/roleGroupRel.html");
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("该菜单用于新增或修改角色组关联角色数据");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加 《权限新增》菜单
    private void privilegeAdd(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("privilege_add");
        fleaMenuPOJO.setMenuName("权限新增");
        fleaMenuPOJO.setMenuIcon("plus-circle");
        fleaMenuPOJO.setMenuSort(1);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_FOUR.getLevel());
        fleaMenuPOJO.setMenuView("auth/privilege-module/privilege/privilegeAdd.html");
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("该菜单用于新增权限数据");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加 《权限变更》菜单
    private void privilegeModify(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("privilege_modify");
        fleaMenuPOJO.setMenuName("权限变更");
        fleaMenuPOJO.setMenuIcon("edit");
        fleaMenuPOJO.setMenuSort(2);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_FOUR.getLevel());
        fleaMenuPOJO.setMenuView("auth/privilege-module/privilege/privilegeModify.html");
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("该菜单用于修改权限数据");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加 《权限关联》菜单
    private void privilegeRel(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("privilege_rel");
        fleaMenuPOJO.setMenuName("权限关联");
        fleaMenuPOJO.setMenuIcon("exchange");
        fleaMenuPOJO.setMenuSort(3);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_FOUR.getLevel());
        fleaMenuPOJO.setMenuView("auth/privilege-module/privilege/privilegeRel.html");
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("该菜单用于新增或修改权限关联功能数据");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加 《权限组新增》菜单
    private void privilegeGroupAdd(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("privilege_group_add");
        fleaMenuPOJO.setMenuName("权限组新增");
        fleaMenuPOJO.setMenuIcon("plus-circle");
        fleaMenuPOJO.setMenuSort(1);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_FOUR.getLevel());
        fleaMenuPOJO.setMenuView("auth/privilege-module/privilege-group/privilegeGroupAdd.html");
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("该菜单用于新增权限组数据");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加 《权限组变更》菜单
    private void privilegeGroupModify(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("privilege_group_modify");
        fleaMenuPOJO.setMenuName("权限组变更");
        fleaMenuPOJO.setMenuIcon("edit");
        fleaMenuPOJO.setMenuSort(2);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_FOUR.getLevel());
        fleaMenuPOJO.setMenuView("auth/privilege-module/privilege-group/privilegeGroupModify.html");
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("该菜单用于修改权限组数据");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加 《权限组关联》菜单
    private void privilegeGroupRel(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("privilege_group_rel");
        fleaMenuPOJO.setMenuName("权限组关联");
        fleaMenuPOJO.setMenuIcon("exchange");
        fleaMenuPOJO.setMenuSort(3);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_FOUR.getLevel());
        fleaMenuPOJO.setMenuView("auth/privilege-module/privilege-group/privilegeGroupRel.html");
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("该菜单用于新增或修改权限组关联权限数据");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加 《菜单新增》菜单
    private void menuAdd(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("menu_add");
        fleaMenuPOJO.setMenuName("菜单新增");
        fleaMenuPOJO.setMenuIcon("plus-circle");
        fleaMenuPOJO.setMenuSort(1);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_FOUR.getLevel());
        fleaMenuPOJO.setMenuView("auth/function-module/menu/menuAdd.html");
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("该菜单用于新增菜单相关数据，包含菜单数据、菜单扩展属性、访问菜单权限、访问菜单权限关联和菜单访问权限组关联");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加 《菜单变更》菜单
    private void menuModify(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("menu_modify");
        fleaMenuPOJO.setMenuName("菜单变更");
        fleaMenuPOJO.setMenuIcon("edit");
        fleaMenuPOJO.setMenuSort(2);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_FOUR.getLevel());
        fleaMenuPOJO.setMenuView("auth/function-module/menu/menuModify.html");
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("该菜单用于修改菜单数据、菜单扩展属性");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加 《操作新增》菜单
    private void operationAdd(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("operation_add");
        fleaMenuPOJO.setMenuName("操作新增");
        fleaMenuPOJO.setMenuIcon("plus-circle");
        fleaMenuPOJO.setMenuSort(1);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_FOUR.getLevel());
        fleaMenuPOJO.setMenuView("auth/function-module/operation/operationAdd.html");
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("该菜单用于新增操作相关数据，包含操作数据、操作扩展属性、执行操作权限、执行操作权限关联和操作执行权限组关联");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加 《操作变更》菜单
    private void operationModify(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("operation_modify");
        fleaMenuPOJO.setMenuName("操作变更");
        fleaMenuPOJO.setMenuIcon("edit");
        fleaMenuPOJO.setMenuSort(2);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_FOUR.getLevel());
        fleaMenuPOJO.setMenuView("auth/function-module/operation/operationModify.html");
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("该菜单用于修改操作数据、操作扩展属性");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加 《元素新增》菜单
    private void elementAdd(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("element_add");
        fleaMenuPOJO.setMenuName("元素新增");
        fleaMenuPOJO.setMenuIcon("plus-circle");
        fleaMenuPOJO.setMenuSort(1);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_FOUR.getLevel());
        fleaMenuPOJO.setMenuView("auth/function-module/element/elementAdd.html");
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("该菜单用于新增元素相关数据，包含元素数据、元素扩展属性、展示元素权限、展示元素权限关联和元素展示权限组关联");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加 《元素变更》菜单
    private void elementModify(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("element_modify");
        fleaMenuPOJO.setMenuName("元素变更");
        fleaMenuPOJO.setMenuIcon("edit");
        fleaMenuPOJO.setMenuSort(2);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_FOUR.getLevel());
        fleaMenuPOJO.setMenuView("auth/function-module/element/elementModify.html");
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("该菜单用于修改元素数据、元素扩展属性");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加 《资源新增》菜单
    private void resourceAdd(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("resource_add");
        fleaMenuPOJO.setMenuName("资源新增");
        fleaMenuPOJO.setMenuIcon("plus-circle");
        fleaMenuPOJO.setMenuSort(1);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_FOUR.getLevel());
        fleaMenuPOJO.setMenuView("auth/function-module/resource/resourceAdd.html");
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("该菜单用于新增资源相关数据，包含资源数据、资源扩展属性、调用资源权限、调用资源权限关联、资源调用权限组关联");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }

    // 添加 《资源变更》菜单
    private void resourceModify(Long parentId) throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("resource_modify");
        fleaMenuPOJO.setMenuName("资源变更");
        fleaMenuPOJO.setMenuIcon("edit");
        fleaMenuPOJO.setMenuSort(2);
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_FOUR.getLevel());
        fleaMenuPOJO.setMenuView("auth/function-module/resource/resourceModify.html");
        fleaMenuPOJO.setParentId(parentId);
        fleaMenuPOJO.setRemarks("该菜单用于修改资源数据、资源扩展属性");
        fleaMenuPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());

        fleaFunctionModuleSV.addFleaMenu(fleaMenuPOJO);
    }
}
