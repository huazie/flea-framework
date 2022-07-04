package com.huazie.fleaframework.auth.base.user;

import com.huazie.fleaframework.auth.common.service.interfaces.IFleaUserModuleSV;
import com.huazie.fleaframework.common.exception.CommonException;

/**
 * 添加用户组关联用户
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class AddUserGroupRelUser {

    private IFleaUserModuleSV fleaUserModuleSV;

    public AddUserGroupRelUser(IFleaUserModuleSV fleaUserModuleSV) {
        this.fleaUserModuleSV = fleaUserModuleSV;
    }

    public void addUserGroupRelUser() throws CommonException {
        userGroupRelUserForFleaFramework();
        userGroupRelUserForFleaMgmt();
        userGroupRelUserForFleaFS();
        userGroupRelUserForHuazie();
        userGroupRelUserForLGH();

        fleaFSAuthUserGroupRelUserForFleaFramework();
        fleaFSAuthUserGroupRelUserForFleaMgmt();
        fleaFSAuthUserGroupRelUserForFleaFS();
    }

    // 用户组【系统用户】关联用户【Flea框架】
    private void userGroupRelUserForFleaFramework() throws CommonException {
        Long userGroupId = 1000L;
        Long userId = 1000L;
        fleaUserModuleSV.userGroupRelUser(userGroupId, userId, null);
    }

    // 用户组【系统用户】关联用户【Flea管家】
    private void userGroupRelUserForFleaMgmt() throws CommonException {
        Long userGroupId = 1000L;
        Long userId = 1001L;
        fleaUserModuleSV.userGroupRelUser(userGroupId, userId, null);
    }

    // 用户组【系统用户】关联用户【Flea文件服务器】
    private void userGroupRelUserForFleaFS() throws CommonException {
        Long userGroupId = 1000L;
        Long userId = 1002L;
        fleaUserModuleSV.userGroupRelUser(userGroupId, userId, null);
    }

    // 用户组【操作用户】关联用户【Huazie】
    private void userGroupRelUserForHuazie() throws CommonException {
        Long userGroupId = 1001L;
        Long userId = 10000L;
        fleaUserModuleSV.userGroupRelUser(userGroupId, userId, null);
    }

    // 用户组【操作用户】关联用户【LGH】
    private void userGroupRelUserForLGH() throws CommonException {
        Long userGroupId = 1001L;
        Long userId = 10001L;
        fleaUserModuleSV.userGroupRelUser(userGroupId, userId, null);
    }

    // 用户组【FleaFS授权用户】关联用户【Flea框架】
    private void fleaFSAuthUserGroupRelUserForFleaFramework() throws CommonException {
        Long userGroupId = 1002L;
        Long userId = 1000L;
        fleaUserModuleSV.userGroupRelUser(userGroupId, userId, null);
    }

    // 用户组【FleaFS授权用户】关联用户【Flea管家】
    private void fleaFSAuthUserGroupRelUserForFleaMgmt() throws CommonException {
        Long userGroupId = 1002L;
        Long userId = 1001L;
        fleaUserModuleSV.userGroupRelUser(userGroupId, userId, null);
    }

    // 用户组【FleaFS授权用户】关联用户【Flea文件服务器】
    private void fleaFSAuthUserGroupRelUserForFleaFS() throws CommonException {
        Long userGroupId = 1002L;
        Long userId = 1002L;
        fleaUserModuleSV.userGroupRelUser(userGroupId, userId, null);
    }
}
