package com.huazie.fleaframework.auth.user;

import com.huazie.fleaframework.auth.base.user.entity.FleaAccount;
import com.huazie.fleaframework.auth.base.user.entity.FleaUserRel;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaUserGroupRelSV;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaUserRelSV;
import com.huazie.fleaframework.auth.common.AccountTypeEnum;
import com.huazie.fleaframework.auth.common.AuthRelTypeEnum;
import com.huazie.fleaframework.auth.common.FleaAuthConstants;
import com.huazie.fleaframework.auth.common.UserStateEnum;
import com.huazie.fleaframework.auth.common.UserTypeEnum;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserModuleData;
import com.huazie.fleaframework.auth.common.pojo.user.attr.FleaAccountAttrPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.attr.FleaUserAttrPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.login.FleaUserLoginPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.register.FleaUserRegisterPOJO;
import com.huazie.fleaframework.auth.common.service.interfaces.IFleaAuthSV;
import com.huazie.fleaframework.auth.common.service.interfaces.IFleaUserModuleSV;
import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class UserAuthTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(UserAuthTest.class);

    private ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        LOGGER.debug("ApplicationContext={}", applicationContext);
    }

    @Test
    public void userLoginTest() {

        FleaUserLoginPOJO fleaUserLoginInfo = new FleaUserLoginPOJO();
        fleaUserLoginInfo.setAccountCode("huazie");
        fleaUserLoginInfo.setAccountPwd("2020#huazie");

        IFleaUserModuleSV fleaUserModuleSV = (IFleaUserModuleSV) applicationContext.getBean("fleaUserModuleSV");

        try {
            FleaAccount fleaAccount = fleaUserModuleSV.login(fleaUserLoginInfo);
            LOGGER.debug("FleaAccount = {}", fleaAccount);
        } catch (CommonException e) {
            LOGGER.error("Exception = ", e);
        }

    }

    @Test
    public void userRegisterTest() {

        FleaUserRegisterPOJO fleaUserRegisterPOJO = new FleaUserRegisterPOJO();

        fleaUserRegisterPOJO.setAccountCode("huazie");
        fleaUserRegisterPOJO.setAccountPwd("2020#huazie");
        fleaUserRegisterPOJO.setState(UserStateEnum.IN_USE.getState());

        // 添加用户属性
        List<FleaUserAttrPOJO> fleaUserAttrInfoList = new ArrayList<>();

        FleaUserAttrPOJO fleaUserAttrInfo1 = new FleaUserAttrPOJO();
        fleaUserAttrInfo1.setAttrCode(FleaAuthConstants.AttrCodeConstants.ATTR_CODE_USER_TYPE);
        fleaUserAttrInfo1.setAttrValue(UserTypeEnum.OPERATOR_USER.getType());
        fleaUserAttrInfo1.setAttrDesc("操作用户");
        fleaUserAttrInfoList.add(fleaUserAttrInfo1);

        // 添加账户属性
        List<FleaAccountAttrPOJO> fleaAccountAttrInfoList = new ArrayList<>();

        FleaAccountAttrPOJO fleaAccountAttrInfo1 = new FleaAccountAttrPOJO();
        fleaAccountAttrInfo1.setAttrCode(FleaAuthConstants.AttrCodeConstants.ATTR_CODE_ACCOUNT_TYPE);
        fleaAccountAttrInfo1.setAttrValue(AccountTypeEnum.OPERATOR_ACCOUNT.getType());
        fleaAccountAttrInfo1.setAttrDesc("操作账户");
        fleaAccountAttrInfoList.add(fleaAccountAttrInfo1);

        fleaUserRegisterPOJO.setUserAttrList(fleaUserAttrInfoList);
        fleaUserRegisterPOJO.setAccountAttrList(fleaAccountAttrInfoList);

        fleaUserRegisterPOJO.setRemarks("用户自己注册时新增数据");

        IFleaUserModuleSV fleaUserModuleSV = (IFleaUserModuleSV) applicationContext.getBean("fleaUserModuleSV");

        try {
            FleaAccount fleaAccount = fleaUserModuleSV.register(fleaUserRegisterPOJO);
            LOGGER.debug("FleaAccount = {}", fleaAccount);
        } catch (CommonException e) {
            LOGGER.error("Exception = ", e);
        }
    }

    @Test
    public void systemUserRegisterTest() {
        FleaUserRegisterPOJO fleaUserRegisterPOJO = new FleaUserRegisterPOJO();

        fleaUserRegisterPOJO.setSystemId(1000L);
        fleaUserRegisterPOJO.setUserName("跳蚤管家");
        fleaUserRegisterPOJO.setAccountCode("SYS_FLEA_MGMT");
        fleaUserRegisterPOJO.setAccountPwd("2020#huazie");
        fleaUserRegisterPOJO.setState(UserStateEnum.IN_USE.getState());

        // 添加用户属性
        List<FleaUserAttrPOJO> fleaUserAttrInfoList = new ArrayList<>();

        FleaUserAttrPOJO fleaUserAttrInfo1 = new FleaUserAttrPOJO();
        fleaUserAttrInfo1.setAttrCode(FleaAuthConstants.AttrCodeConstants.ATTR_CODE_USER_TYPE);
        fleaUserAttrInfo1.setAttrValue(UserTypeEnum.SYSTEM_USER.getType());
        fleaUserAttrInfo1.setAttrDesc("系统用户");
        fleaUserAttrInfoList.add(fleaUserAttrInfo1);

        // 添加账户属性
        List<FleaAccountAttrPOJO> fleaAccountAttrInfoList = new ArrayList<>();

        FleaAccountAttrPOJO fleaAccountAttrInfo1 = new FleaAccountAttrPOJO();
        fleaAccountAttrInfo1.setAttrCode(FleaAuthConstants.AttrCodeConstants.ATTR_CODE_ACCOUNT_TYPE);
        fleaAccountAttrInfo1.setAttrValue(AccountTypeEnum.SYSTEM_ACCOUNT.getType());
        fleaAccountAttrInfo1.setAttrDesc("系统账户");
        fleaAccountAttrInfoList.add(fleaAccountAttrInfo1);

        fleaUserRegisterPOJO.setUserAttrList(fleaUserAttrInfoList);
        fleaUserRegisterPOJO.setAccountAttrList(fleaAccountAttrInfoList);

        fleaUserRegisterPOJO.setRemarks("【跳蚤管家】");

        IFleaUserModuleSV fleaUserModuleSV = (IFleaUserModuleSV) applicationContext.getBean("fleaUserModuleSV");

        try {
            FleaAccount fleaAccount = fleaUserModuleSV.register(fleaUserRegisterPOJO);
            LOGGER.debug("FleaAccount = {}", fleaAccount);
        } catch (CommonException e) {
            LOGGER.error("Exception = ", e);
        }
    }

    @Test
    public void testSaveQuitLog() {
        IFleaUserModuleSV fleaUserModuleSV = (IFleaUserModuleSV) applicationContext.getBean("fleaUserModuleSV");
        fleaUserModuleSV.saveQuitLog(10000L);
    }

    @Test
    public void testSaveLoginAndQuitLog() {
        IFleaUserModuleSV fleaUserModuleSV = (IFleaUserModuleSV) applicationContext.getBean("fleaUserModuleSV");
        fleaUserModuleSV.saveLoginLog(10000L, null);

        fleaUserModuleSV.saveQuitLog(10000L);
    }

    @Test
    public void testInsertUserRel() {

        FleaUserRel fleaUserRel = new FleaUserRel();
        fleaUserRel.setUserId(10000L);
        fleaUserRel.setRelId(1000L);
        fleaUserRel.setRelType(AuthRelTypeEnum.USER_REL_ROLE.getRelType());
        fleaUserRel.setRelState(EntityStateEnum.IN_USE.getState());
        fleaUserRel.setCreateDate(DateUtils.getCurrentTime());
        fleaUserRel.setRemarks("用户【huazie】绑定【超级管理员】角色");

        try {
            IFleaUserRelSV fleaUserRelSV = (IFleaUserRelSV) applicationContext.getBean("fleaUserRelSV");
            fleaUserRelSV.save(fleaUserRel);
        } catch (CommonException e) {
            LOGGER.error("Exception = ", e);
        }
    }

    @Test
    public void testQueryUserGroupRel() {

        try {
            IFleaUserGroupRelSV fleaUserGroupRelSV = (IFleaUserGroupRelSV) applicationContext.getBean("fleaUserGroupRelSV");
            fleaUserGroupRelSV.getUserGroupRelList(1000L, AuthRelTypeEnum.USER_GROUP_REL_ROLE.getRelType());
        } catch (CommonException e) {
            LOGGER.error("Exception = ", e);
        }
    }

    @Test
    public void testQueryUserRel() {

        try {
            IFleaUserRelSV fleaUserRelSV = (IFleaUserRelSV) applicationContext.getBean("fleaUserRelSV");
            fleaUserRelSV.getUserRelList(10000L, AuthRelTypeEnum.USER_REL_ROLE.getRelType());
        } catch (CommonException e) {
            LOGGER.error("Exception = ", e);
        }
    }

    @Test
    public void testQueryFleaUserModuleData() {

        try {
            IFleaAuthSV fleaAuthSV = (IFleaAuthSV) applicationContext.getBean("fleaAuthSV");
            FleaUserModuleData fleaUserModuleData = fleaAuthSV.getFleaUserModuleData(1000L);
            LOGGER.debug("FleaUserModuleData = {}", fleaUserModuleData);
        } catch (CommonException e) {
            LOGGER.error("Exception = ", e);
        }
    }

}
