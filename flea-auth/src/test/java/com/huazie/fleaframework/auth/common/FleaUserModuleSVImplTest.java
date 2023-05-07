package com.huazie.fleaframework.auth.common;

import com.huazie.fleaframework.auth.base.user.AddUserGroup;
import com.huazie.fleaframework.auth.base.user.AddUserGroupRelUser;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserGroupPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.login.FleaUserLoginPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.register.FleaUserRegisterPOJO;
import com.huazie.fleaframework.auth.common.service.interfaces.IFleaUserModuleSV;
import com.huazie.fleaframework.auth.util.FleaAuthPOJOUtils;
import com.huazie.fleaframework.common.exceptions.CommonException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 *
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class FleaUserModuleSVImplTest {

    @Resource(name = "fleaUserModuleSV")
    private IFleaUserModuleSV fleaUserModuleSV;

    @Test
    public void login() throws CommonException {
        FleaUserLoginPOJO fleaUserLoginInfo = FleaAuthPOJOUtils.newFleaUserLoginPOJO("13218010892", "2022#lgh");
        fleaUserModuleSV.login(fleaUserLoginInfo);
    }

    @Test
    public void userRegisterForPhone() throws CommonException {
        String userName = "Huazie";
        String accountCode = "13218010892";
        String accountPwd = "2022#lgh";

        operatorUserRegister(userName, accountCode, accountPwd);
    }

    @Test
    public void userRegisterForEmail() throws CommonException {
        String userName = "LGH";
        String accountCode = "2352553988@qq.com";
        String accountPwd = "2022#lgh";

        operatorUserRegister(userName, accountCode, accountPwd);
    }

    // 操作用户注册
    private void operatorUserRegister(String userName, String accountCode, String accountPwd) throws CommonException {
        FleaUserRegisterPOJO fleaUserRegisterPOJO = new FleaUserRegisterPOJO();

        fleaUserRegisterPOJO.setUserName(userName);
        fleaUserRegisterPOJO.setAccountCode(accountCode);
        fleaUserRegisterPOJO.setAccountPwd(accountPwd);
        fleaUserRegisterPOJO.setState(UserStateEnum.IN_USE.getState());

        fleaUserRegisterPOJO.setRemarks("用户自己注册时新增数据");

        fleaUserModuleSV.register(fleaUserRegisterPOJO);
    }

    @Test
    public void systemUserRegister4FleaFramework() throws CommonException {
        long systemId = 1000L;
        String systemName = "Flea框架";
        String accountCode = "SYS_FLEA_FRAMEWORK";
        String accountPwd = "2022#huazie";
        String remarks = "【Flea Framework】";

        systemUserRegister(systemId, systemName, accountCode, accountPwd, remarks);
    }

    @Test
    public void systemUserRegister4FleaMgmt() throws CommonException {
        long systemId = 1001L;
        String systemName = "Flea管家";
        String accountCode = "SYS_FLEA_MGMT";
        String accountPwd = "2022#huazie";
        String remarks = "【Flea Management】";

        systemUserRegister(systemId, systemName, accountCode, accountPwd, remarks);
    }

    @Test
    public void systemUserRegister4FleaFS() throws CommonException {
        long systemId = 1002L;
        String systemName = "Flea文件服务器";
        String accountCode = "SYS_FLEA_FS";
        String accountPwd = "2022#huazie";
        String remarks = "【Flea File Server】";

        systemUserRegister(systemId, systemName, accountCode, accountPwd, remarks);
    }

    // 系统用户注册
    private void systemUserRegister(long systemId, String systemName, String accountCode, String accountPwd, String remarks) throws CommonException {
        FleaUserRegisterPOJO fleaUserRegisterPOJO = new FleaUserRegisterPOJO();

        fleaUserRegisterPOJO.setSystemId(systemId);
        fleaUserRegisterPOJO.setUserName(systemName);
        fleaUserRegisterPOJO.setAccountCode(accountCode);
        fleaUserRegisterPOJO.setAccountPwd(accountPwd);
        fleaUserRegisterPOJO.setState(UserStateEnum.IN_USE.getState());

        fleaUserRegisterPOJO.setRemarks(remarks);

        fleaUserModuleSV.register(fleaUserRegisterPOJO);
    }

    @Test
    public void addUserGroup() throws CommonException {
        AddUserGroup userGroupAdd = new AddUserGroup(fleaUserModuleSV);
        userGroupAdd.addUserGroup();
    }

    @Test
    public void modifyFleaUserGroup() throws CommonException {
        FleaUserGroupPOJO fleaUserGroupPOJO = new FleaUserGroupPOJO();
        fleaUserGroupPOJO.setUserGroupName("系统用户");
        fleaUserGroupPOJO.setUserGroupDesc("实际的系统用户归属的用户组");
        fleaUserGroupPOJO.setRemarks("该用户组与实际的系统用户相关");
        fleaUserModuleSV.modifyFleaUserGroup(1000L, fleaUserGroupPOJO);
    }

    @Test
    public void userRelRole() throws CommonException {
        // 用户【Huazie】绑定【超级管理员】角色
        Long userId = 10000L;
        Long roleId = 1000L;
        fleaUserModuleSV.userRelRole(userId, roleId, null);
    }

    @Test
    public void userRelRoleGroup() throws CommonException { // TODO
        Long userId = 10000L;
        Long roleGroupId = 1000L;
        fleaUserModuleSV.userRelRoleGroup(userId, roleGroupId, null);
    }

    @Test
    public void userGroupRelRole() throws CommonException {
        Long userGroupId = 1002L;
        Long roleId = 1018L;
        // 用户组【FleaFS授权用户】绑定【FleaFS接入员】角色
        fleaUserModuleSV.userGroupRelRole(userGroupId, roleId, null);
    }

    @Test
    public void userGroupRelRoleGroup() throws CommonException { // TODO
        Long userGroupId = 1000L;
        Long roleGroupId = 1000L;
        fleaUserModuleSV.userGroupRelRoleGroup(userGroupId, roleGroupId, null);
    }

    @Test
    public void userGroupRelUser() throws CommonException {
        AddUserGroupRelUser userGroupRelUserAdd = new AddUserGroupRelUser(fleaUserModuleSV);
        userGroupRelUserAdd.addUserGroupRelUser();
    }

    @Test
    public void testSaveLoginAndQuitLog() {
        fleaUserModuleSV.saveLoginLog(10000L, null);
        fleaUserModuleSV.saveQuitLog(10000L);
    }

    @Test
    public void saveLoginLog() {
        fleaUserModuleSV.saveLoginLog(10000L, null);
    }

    @Test
    public void saveQuitLog() {
        fleaUserModuleSV.saveQuitLog(10000L);
    }
}