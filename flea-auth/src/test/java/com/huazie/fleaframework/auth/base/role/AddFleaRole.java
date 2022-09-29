package com.huazie.fleaframework.auth.base.role;

import com.huazie.fleaframework.auth.common.pojo.role.FleaRolePOJO;
import com.huazie.fleaframework.auth.common.service.interfaces.IFleaRoleModuleSV;
import com.huazie.fleaframework.common.exception.CommonException;

/**
 * 添加Flea角色数据
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class AddFleaRole {

    private IFleaRoleModuleSV fleaRoleModuleSV;

    public AddFleaRole(IFleaRoleModuleSV fleaRoleModuleSV) {
        this.fleaRoleModuleSV = fleaRoleModuleSV;
    }

    public void addFleaRole() throws CommonException {
        addSuperManagerRole();
        addBusinessManagerRole();
        addAuthManagerRole();
        addUserModuleManagerRole();
        addRoleModuleManagerRole();
        addPrivilegeModuleManagerRole();
        addFunctionModuleManagerRole();

        addTouristRole();

        addOneFleaMasterRole();
        addTwoFleaMasterRole();
        addThreeFleaMasterRole();
        addFourFleaMasterRole();
        addFiveFleaMasterRole();

        addOneFleaCustomerRole();
        addTwoFleaCustomerRole();
        addThreeFleaCustomerRole();
        addFourFleaCustomerRole();
        addFiveFleaCustomerRole();

        addFleaFSUserRole();
    }

    // 添加【超级管理员】角色
    private void addSuperManagerRole() throws CommonException {
        FleaRolePOJO fleaRolePOJO = new FleaRolePOJO();
        fleaRolePOJO.setRoleName("超级管理员");
        fleaRolePOJO.setRoleDesc("最高系统权限拥有者角色");
        fleaRolePOJO.setRemarks("超级管理员拥有系统最高权限");
        fleaRoleModuleSV.addFleaRole(fleaRolePOJO);
    }

    // 添加【业务管理员】角色
    private void addBusinessManagerRole() throws CommonException {
        FleaRolePOJO fleaRolePOJO = new FleaRolePOJO();
        fleaRolePOJO.setRoleName("业务管理员");
        fleaRolePOJO.setRoleDesc("与业务处理相关的角色");
        fleaRolePOJO.setRemarks("业务管理员用于与业务处理相关的权限");
        fleaRoleModuleSV.addFleaRole(fleaRolePOJO);
    }

    // 添加【授权管理员】角色
    private void addAuthManagerRole() throws CommonException {
        FleaRolePOJO fleaRolePOJO = new FleaRolePOJO();
        fleaRolePOJO.setRoleName("授权管理员");
        fleaRolePOJO.setRoleDesc("与授权管理相关的角色");
        fleaRolePOJO.setRemarks("授权管理员拥有与授权管理相关的权限，包含用户模块管理、角色模块管理、权限模块管理和功能模块管理");
        fleaRoleModuleSV.addFleaRole(fleaRolePOJO);
    }

    // 添加【用户模块管理员】角色
    private void addUserModuleManagerRole() throws CommonException {
        FleaRolePOJO fleaRolePOJO = new FleaRolePOJO();
        fleaRolePOJO.setRoleName("用户模块管理员");
        fleaRolePOJO.setRoleDesc("与用户模块管理相关的角色");
        fleaRolePOJO.setRemarks("用户模块管理员拥有与用户模块管理相关的权限，包含用户管理、用户组管理");
        fleaRoleModuleSV.addFleaRole(fleaRolePOJO);
    }

    // 添加【角色模块管理员】角色
    private void addRoleModuleManagerRole() throws CommonException {
        FleaRolePOJO fleaRolePOJO = new FleaRolePOJO();
        fleaRolePOJO.setRoleName("角色模块管理员");
        fleaRolePOJO.setRoleDesc("与角色模块管理相关的角色");
        fleaRolePOJO.setRemarks("角色模块管理员拥有与角色模块管理相关的权限，包含角色管理、角色组管理");
        fleaRoleModuleSV.addFleaRole(fleaRolePOJO);
    }

    // 添加【权限模块管理员】角色
    private void addPrivilegeModuleManagerRole() throws CommonException {
        FleaRolePOJO fleaRolePOJO = new FleaRolePOJO();
        fleaRolePOJO.setRoleName("权限模块管理员");
        fleaRolePOJO.setRoleDesc("与权限模块管理相关的角色");
        fleaRolePOJO.setRemarks("权限模块管理员拥有与权限模块管理相关的权限，包含权限管理、权限组管理");
        fleaRoleModuleSV.addFleaRole(fleaRolePOJO);
    }

    // 添加【功能模块管理员】角色
    private void addFunctionModuleManagerRole() throws CommonException {
        FleaRolePOJO fleaRolePOJO = new FleaRolePOJO();
        fleaRolePOJO.setRoleName("功能模块管理员");
        fleaRolePOJO.setRoleDesc("与功能模块管理相关的角色");
        fleaRolePOJO.setRemarks("功能模块管理员拥有与功能模块管理相关的权限，包含菜单管理、操作管理、元素管理和资源管理");
        fleaRoleModuleSV.addFleaRole(fleaRolePOJO);
    }

    // 添加【游客】角色
    private void addTouristRole() throws CommonException {
        FleaRolePOJO fleaRolePOJO = new FleaRolePOJO();
        fleaRolePOJO.setRoleName("游客");
        fleaRolePOJO.setRoleDesc("系统观光者的角色");
        fleaRolePOJO.setRemarks("游客只拥有有限的系统浏览权限");
        fleaRoleModuleSV.addFleaRole(fleaRolePOJO);
    }

    // 添加【青铜主】角色
    private void addOneFleaMasterRole() throws CommonException {
        FleaRolePOJO fleaRolePOJO = new FleaRolePOJO();
        fleaRolePOJO.setRoleName("青铜主");
        fleaRolePOJO.setRoleDesc("一星等级的跳主角色");
        fleaRolePOJO.setRemarks("青铜主拥有一星等级的权限，跳主角色中最低权限拥有者");
        fleaRoleModuleSV.addFleaRole(fleaRolePOJO);
    }

    // 添加【白银主】角色
    private void addTwoFleaMasterRole() throws CommonException {
        FleaRolePOJO fleaRolePOJO = new FleaRolePOJO();
        fleaRolePOJO.setRoleName("白银主");
        fleaRolePOJO.setRoleDesc("二星等级的跳主角色");
        fleaRolePOJO.setRemarks("白银主拥有二星等级的权限");
        fleaRoleModuleSV.addFleaRole(fleaRolePOJO);
    }

    // 添加【黄金主】角色
    private void addThreeFleaMasterRole() throws CommonException {
        FleaRolePOJO fleaRolePOJO = new FleaRolePOJO();
        fleaRolePOJO.setRoleName("黄金主");
        fleaRolePOJO.setRoleDesc("三星等级的跳主角色");
        fleaRolePOJO.setRemarks("黄金主拥有三星等级的权限");
        fleaRoleModuleSV.addFleaRole(fleaRolePOJO);
    }

    // 添加【砖石主】角色
    private void addFourFleaMasterRole() throws CommonException {
        FleaRolePOJO fleaRolePOJO = new FleaRolePOJO();
        fleaRolePOJO.setRoleName("砖石主");
        fleaRolePOJO.setRoleDesc("四星等级的跳主角色");
        fleaRolePOJO.setRemarks("砖石主拥有四星等级的权限");
        fleaRoleModuleSV.addFleaRole(fleaRolePOJO);
    }

    // 添加【皇冠主】角色
    private void addFiveFleaMasterRole() throws CommonException {
        FleaRolePOJO fleaRolePOJO = new FleaRolePOJO();
        fleaRolePOJO.setRoleName("皇冠主");
        fleaRolePOJO.setRoleDesc("五星等级的跳主角色");
        fleaRolePOJO.setRemarks("皇冠主拥有五星等级的权限，跳主角色中最高权限拥有者");
        fleaRoleModuleSV.addFleaRole(fleaRolePOJO);
    }

    // 添加【青铜客】角色
    private void addOneFleaCustomerRole() throws CommonException {
        FleaRolePOJO fleaRolePOJO = new FleaRolePOJO();
        fleaRolePOJO.setRoleName("青铜客");
        fleaRolePOJO.setRoleDesc("一星等级的跳客角色");
        fleaRolePOJO.setRemarks("青铜客拥有一星等级的权限，跳客角色中最低权限拥有者");
        fleaRoleModuleSV.addFleaRole(fleaRolePOJO);
    }

    // 添加【白银客】角色
    private void addTwoFleaCustomerRole() throws CommonException {
        FleaRolePOJO fleaRolePOJO = new FleaRolePOJO();
        fleaRolePOJO.setRoleName("白银客");
        fleaRolePOJO.setRoleDesc("二星等级的跳客角色");
        fleaRolePOJO.setRemarks("白银客拥有二星等级的权限");
        fleaRoleModuleSV.addFleaRole(fleaRolePOJO);
    }

    // 添加【黄金客】角色
    private void addThreeFleaCustomerRole() throws CommonException {
        FleaRolePOJO fleaRolePOJO = new FleaRolePOJO();
        fleaRolePOJO.setRoleName("黄金客");
        fleaRolePOJO.setRoleDesc("三星等级的跳客角色");
        fleaRolePOJO.setRemarks("黄金客拥有三星等级的权限");
        fleaRoleModuleSV.addFleaRole(fleaRolePOJO);
    }

    // 添加【砖石客】角色
    private void addFourFleaCustomerRole() throws CommonException {
        FleaRolePOJO fleaRolePOJO = new FleaRolePOJO();
        fleaRolePOJO.setRoleName("砖石客");
        fleaRolePOJO.setRoleDesc("四星等级的跳客角色");
        fleaRolePOJO.setRemarks("砖石客拥有四星等级的权限");
        fleaRoleModuleSV.addFleaRole(fleaRolePOJO);
    }

    // 添加【皇冠客】角色
    private void addFiveFleaCustomerRole() throws CommonException {
        FleaRolePOJO fleaRolePOJO = new FleaRolePOJO();
        fleaRolePOJO.setRoleName("皇冠客");
        fleaRolePOJO.setRoleDesc("五星等级的跳客角色");
        fleaRolePOJO.setRemarks("皇冠客拥有五星等级的权限，跳客角色中最高权限拥有者");
        fleaRoleModuleSV.addFleaRole(fleaRolePOJO);
    }

    // 添加【FleaFS接入员】
    private void addFleaFSUserRole() throws CommonException {
        FleaRolePOJO fleaRolePOJO = new FleaRolePOJO();
        fleaRolePOJO.setRoleName("FleaFS接入员");
        fleaRolePOJO.setRoleDesc("用于接入Flea文件服务器的角色");
        fleaRolePOJO.setRemarks("FleaFS接入员，拥有调用Flea文件服务器提供的资源的权限");
        fleaRoleModuleSV.addFleaRole(fleaRolePOJO);
    }
}
