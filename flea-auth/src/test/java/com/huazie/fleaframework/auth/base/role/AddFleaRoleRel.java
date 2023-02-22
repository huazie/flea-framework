package com.huazie.fleaframework.auth.base.role;

import com.huazie.fleaframework.auth.common.service.interfaces.IFleaRoleModuleSV;
import com.huazie.fleaframework.common.exceptions.CommonException;

/**
 * 添加角色关联权限组
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class AddFleaRoleRel {

    private IFleaRoleModuleSV fleaRoleModuleSV;

    public AddFleaRoleRel(IFleaRoleModuleSV fleaRoleModuleSV) {
        this.fleaRoleModuleSV = fleaRoleModuleSV;
    }

    // 角色关联权限组
    public void roleRelPrivilegeGroup() throws CommonException {
        superManagerRoleRelPrivilegeGroupForMenu();
        superManagerRoleRelPrivilegeGroupForOperation();
        superManagerRoleRelPrivilegeGroupForElement();
        superManagerRoleRelPrivilegeGroupForResource();

        fleaFSUserRoleRelFleaFSResourceInvokePrivilegeGroup();
    }

    // 【超级管理员】角色绑定【菜单访问】权限组
    private void superManagerRoleRelPrivilegeGroupForMenu() throws CommonException {
        Long roleId = 1000L;
        Long privilegeGroupId = 1000L;
        fleaRoleModuleSV.roleRelPrivilegeGroup(roleId, privilegeGroupId, null);
    }

    // 【超级管理员】角色绑定【操作执行】权限组
    private void superManagerRoleRelPrivilegeGroupForOperation() throws CommonException {
        Long roleId = 1000L;
        Long privilegeGroupId = 1001L;
        fleaRoleModuleSV.roleRelPrivilegeGroup(roleId, privilegeGroupId, null);
    }

    // 【超级管理员】角色绑定【元素展示】权限组
    private void superManagerRoleRelPrivilegeGroupForElement() throws CommonException {
        Long roleId = 1000L;
        Long privilegeGroupId = 1002L;
        fleaRoleModuleSV.roleRelPrivilegeGroup(roleId, privilegeGroupId, null);
    }

    // 【超级管理员】角色绑定【资源调用】权限组
    private void superManagerRoleRelPrivilegeGroupForResource() throws CommonException {
        Long roleId = 1000L;
        Long privilegeGroupId = 1003L;
        fleaRoleModuleSV.roleRelPrivilegeGroup(roleId, privilegeGroupId, null);
    }

    // 【FleaFS接入员】角色绑定【FleaFS资源调用】权限组
    private void fleaFSUserRoleRelFleaFSResourceInvokePrivilegeGroup() throws CommonException {
        Long roleId = 1018L;
        Long privilegeGroupId = 1004L;
        fleaRoleModuleSV.roleRelPrivilegeGroup(roleId, privilegeGroupId, null);
    }
}
