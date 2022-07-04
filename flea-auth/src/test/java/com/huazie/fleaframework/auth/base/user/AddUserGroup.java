package com.huazie.fleaframework.auth.base.user;

import com.huazie.fleaframework.auth.common.pojo.user.FleaUserGroupPOJO;
import com.huazie.fleaframework.auth.common.service.interfaces.IFleaUserModuleSV;
import com.huazie.fleaframework.common.exception.CommonException;

/**
 * 添加Flea用户组
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class AddUserGroup {

    private IFleaUserModuleSV fleaUserModuleSV;

    public AddUserGroup(IFleaUserModuleSV fleaUserModuleSV) {
        this.fleaUserModuleSV = fleaUserModuleSV;
    }

    public void addUserGroup() throws CommonException {
        addSystemUserGroup();
        addOperationUserGroup();

        addFleaFSAuthUserGroup();
    }

    // 添加【系统用户】用户组
    private void addSystemUserGroup() throws CommonException {
        FleaUserGroupPOJO fleaUserGroupPOJO = new FleaUserGroupPOJO();
        fleaUserGroupPOJO.setUserGroupName("系统用户");
        fleaUserGroupPOJO.setUserGroupDesc("实际的系统用户归属的用户组");
        fleaUserGroupPOJO.setRemarks("该用户组与实际的系统用户相关");
        fleaUserModuleSV.addUserGroup(fleaUserGroupPOJO);
    }

    // 添加【操作用户】用户组
    private void addOperationUserGroup() throws CommonException {
        FleaUserGroupPOJO fleaUserGroupPOJO = new FleaUserGroupPOJO();
        fleaUserGroupPOJO.setUserGroupName("操作用户");
        fleaUserGroupPOJO.setUserGroupDesc("实际的操作用户归属的用户组");
        fleaUserGroupPOJO.setRemarks("该用户组与实际的操作用户相关");
        fleaUserModuleSV.addUserGroup(fleaUserGroupPOJO);
    }

    // 添加【FleaFS授权用户】用户组
    private void addFleaFSAuthUserGroup() throws CommonException {
        FleaUserGroupPOJO fleaUserGroupPOJO = new FleaUserGroupPOJO();
        fleaUserGroupPOJO.setUserGroupName("FleaFS授权用户");
        fleaUserGroupPOJO.setUserGroupDesc("实际接入FleaFS的用户归属的用户组");
        fleaUserGroupPOJO.setRemarks("该用户组与实际接入FleaFS的用户相关");
        fleaUserModuleSV.addUserGroup(fleaUserGroupPOJO);
    }
}
