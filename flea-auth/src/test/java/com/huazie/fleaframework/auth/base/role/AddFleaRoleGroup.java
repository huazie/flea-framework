package com.huazie.fleaframework.auth.base.role;

import com.huazie.fleaframework.auth.common.pojo.role.FleaRoleGroupPOJO;
import com.huazie.fleaframework.auth.common.service.interfaces.IFleaRoleModuleSV;
import com.huazie.fleaframework.common.exception.CommonException;

/**
 * 添加Flea角色组数据
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class AddFleaRoleGroup {

    private IFleaRoleModuleSV fleaRoleModuleSV;

    public AddFleaRoleGroup(IFleaRoleModuleSV fleaRoleModuleSV) {
        this.fleaRoleModuleSV = fleaRoleModuleSV;
    }

    public void addFleaRoleGroup() throws CommonException {
        // 添加【管理员】角色组
        addManagerRoleGroup();
        // 添加【跳主】角色组
        addFleaMasterRoleGroup();
        // 添加【跳客】角色组
        addFleaCustomerRoleGroup();
    }

    // 添加【管理员】角色组
    private void addManagerRoleGroup() throws CommonException {
        FleaRoleGroupPOJO fleaRoleGroupPOJO = new FleaRoleGroupPOJO();
        fleaRoleGroupPOJO.setRoleGroupName("管理员");
        fleaRoleGroupPOJO.setRoleGroupDesc("各种管理员角色归属的角色组");
        fleaRoleGroupPOJO.setRemarks("【管理员】角色组包含各种管理员角色");
        fleaRoleModuleSV.addFleaRoleGroup(fleaRoleGroupPOJO);
    }

    // 添加【跳主】角色组
    private void addFleaMasterRoleGroup() throws CommonException {
        FleaRoleGroupPOJO fleaRoleGroupPOJO = new FleaRoleGroupPOJO();
        fleaRoleGroupPOJO.setRoleGroupName("跳主");
        fleaRoleGroupPOJO.setRoleGroupDesc("各星级跳主角色归属的角色组");
        fleaRoleGroupPOJO.setRemarks("【跳主】角色组包含各星级跳主角色");
        fleaRoleModuleSV.addFleaRoleGroup(fleaRoleGroupPOJO);
    }

    // 添加【跳客】角色组
    private void addFleaCustomerRoleGroup() throws CommonException {
        FleaRoleGroupPOJO fleaRoleGroupPOJO = new FleaRoleGroupPOJO();
        fleaRoleGroupPOJO.setRoleGroupName("跳客");
        fleaRoleGroupPOJO.setRoleGroupDesc("各星级跳客角色归属的角色组");
        fleaRoleGroupPOJO.setRemarks("【跳客】角色组包含各星级跳客角色");
        fleaRoleModuleSV.addFleaRoleGroup(fleaRoleGroupPOJO);
    }
    
}
