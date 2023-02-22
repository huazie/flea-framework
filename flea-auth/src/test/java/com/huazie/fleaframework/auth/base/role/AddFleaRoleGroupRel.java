package com.huazie.fleaframework.auth.base.role;

import com.huazie.fleaframework.auth.common.service.interfaces.IFleaRoleModuleSV;
import com.huazie.fleaframework.common.exceptions.CommonException;

/**
 * 添加Flea角色组关联角色数据
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class AddFleaRoleGroupRel {

    private IFleaRoleModuleSV fleaRoleModuleSV;

    public AddFleaRoleGroupRel(IFleaRoleModuleSV fleaRoleModuleSV) {
        this.fleaRoleModuleSV = fleaRoleModuleSV;
    }

    public void roleGroupRelRole() throws CommonException {
        managerRoleGroupRelSuperManagerRole();
        managerRoleGroupRelBusinessManagerRole();
        managerRoleGroupRelAuthManagerRole();
        managerRoleGroupRelUserModuleManagerRole();
        managerRoleGroupRelRoleModuleManagerRole();
        managerRoleGroupRelPrivilegeModuleManagerRole();
        managerRoleGroupRelFunctionModuleManagerRole();

        fleaMasterRoleGroupRelOneFleaMasterRole();
        fleaMasterRoleGroupRelTwoFleaMasterRole();
        fleaMasterRoleGroupRelThreeFleaMasterRole();
        fleaMasterRoleGroupRelFourFleaMasterRole();
        fleaMasterRoleGroupRelFiveFleaMasterRole();

        fleaCustomerRoleGroupRelOneFleaCustomerRole();
        fleaCustomerRoleGroupRelTwoFleaCustomerRole();
        fleaCustomerRoleGroupRelThreeFleaCustomerRole();
        fleaCustomerRoleGroupRelFourFleaCustomerRole();
        fleaCustomerRoleGroupRelFiveFleaCustomerRole();
    }

    // 【管理员】角色组关联【超级管理员】角色
    private void managerRoleGroupRelSuperManagerRole() throws CommonException {
        Long roleGroupId = 1000L;
        Long roleId = 1000L;
        fleaRoleModuleSV.roleGroupRelRole(roleGroupId, roleId, null);
    }

    // 【管理员】角色组关联【业务管理员】角色
    private void managerRoleGroupRelBusinessManagerRole() throws CommonException {
        Long roleGroupId = 1000L;
        Long roleId = 1001L;
        fleaRoleModuleSV.roleGroupRelRole(roleGroupId, roleId, null);
    }

    // 【管理员】角色组关联【授权管理员】角色
    private void managerRoleGroupRelAuthManagerRole() throws CommonException {
        Long roleGroupId = 1000L;
        Long roleId = 1002L;
        fleaRoleModuleSV.roleGroupRelRole(roleGroupId, roleId, null);
    }

    // 【管理员】角色组关联【用户模块管理员】角色
    private void managerRoleGroupRelUserModuleManagerRole() throws CommonException {
        Long roleGroupId = 1000L;
        Long roleId = 1003L;
        fleaRoleModuleSV.roleGroupRelRole(roleGroupId, roleId, null);
    }

    // 【管理员】角色组关联【角色模块管理员】角色
    private void managerRoleGroupRelRoleModuleManagerRole() throws CommonException {
        Long roleGroupId = 1000L;
        Long roleId = 1004L;
        fleaRoleModuleSV.roleGroupRelRole(roleGroupId, roleId, null);
    }

    // 【管理员】角色组关联【权限模块管理员】角色
    private void managerRoleGroupRelPrivilegeModuleManagerRole() throws CommonException {
        Long roleGroupId = 1000L;
        Long roleId = 1005L;
        fleaRoleModuleSV.roleGroupRelRole(roleGroupId, roleId, null);
    }

    // 【管理员】角色组关联【功能模块管理员】角色
    private void managerRoleGroupRelFunctionModuleManagerRole() throws CommonException {
        Long roleGroupId = 1000L;
        Long roleId = 1006L;
        fleaRoleModuleSV.roleGroupRelRole(roleGroupId, roleId, null);
    }

    // 【跳主】角色组关联【青铜主】角色
    private void fleaMasterRoleGroupRelOneFleaMasterRole() throws CommonException {
        Long roleGroupId = 1001L;
        Long roleId = 1008L;
        fleaRoleModuleSV.roleGroupRelRole(roleGroupId, roleId, null);
    }

    // 【跳主】角色组关联【白银主】角色
    private void fleaMasterRoleGroupRelTwoFleaMasterRole() throws CommonException {
        Long roleGroupId = 1001L;
        Long roleId = 1009L;
        fleaRoleModuleSV.roleGroupRelRole(roleGroupId, roleId, null);
    }

    // 【跳主】角色组关联【黄金主】角色
    private void fleaMasterRoleGroupRelThreeFleaMasterRole() throws CommonException {
        Long roleGroupId = 1001L;
        Long roleId = 1010L;
        fleaRoleModuleSV.roleGroupRelRole(roleGroupId, roleId, null);
    }

    // 【跳主】角色组关联【砖石主】角色
    private void fleaMasterRoleGroupRelFourFleaMasterRole() throws CommonException {
        Long roleGroupId = 1001L;
        Long roleId = 1011L;
        fleaRoleModuleSV.roleGroupRelRole(roleGroupId, roleId, null);
    }

    // 【跳主】角色组关联【皇冠主】角色
    private void fleaMasterRoleGroupRelFiveFleaMasterRole() throws CommonException {
        Long roleGroupId = 1001L;
        Long roleId = 1012L;
        fleaRoleModuleSV.roleGroupRelRole(roleGroupId, roleId, null);
    }

    // 【跳客】角色组关联【青铜客】角色
    private void fleaCustomerRoleGroupRelOneFleaCustomerRole() throws CommonException {
        Long roleGroupId = 1002L;
        Long roleId = 1013L;
        fleaRoleModuleSV.roleGroupRelRole(roleGroupId, roleId, null);
    }

    // 【跳客】角色组关联【白银客】角色
    private void fleaCustomerRoleGroupRelTwoFleaCustomerRole() throws CommonException {
        Long roleGroupId = 1002L;
        Long roleId = 1014L;
        fleaRoleModuleSV.roleGroupRelRole(roleGroupId, roleId, null);
    }

    // 【跳客】角色组关联【黄金客】角色
    private void fleaCustomerRoleGroupRelThreeFleaCustomerRole() throws CommonException {
        Long roleGroupId = 1002L;
        Long roleId = 1015L;
        fleaRoleModuleSV.roleGroupRelRole(roleGroupId, roleId, null);
    }

    // 【跳客】角色组关联【砖石客】角色
    private void fleaCustomerRoleGroupRelFourFleaCustomerRole() throws CommonException {
        Long roleGroupId = 1002L;
        Long roleId = 1016L;
        fleaRoleModuleSV.roleGroupRelRole(roleGroupId, roleId, null);
    }

    // 【跳客】角色组关联【皇冠客】角色
    private void fleaCustomerRoleGroupRelFiveFleaCustomerRole() throws CommonException {
        Long roleGroupId = 1002L;
        Long roleId = 1017L;
        fleaRoleModuleSV.roleGroupRelRole(roleGroupId, roleId, null);
    }
}
