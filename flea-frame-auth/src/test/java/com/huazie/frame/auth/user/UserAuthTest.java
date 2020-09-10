package com.huazie.frame.auth.user;

import com.huazie.frame.auth.base.user.entity.FleaAccount;
import com.huazie.frame.auth.base.user.entity.FleaUserRel;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaUserGroupRelSV;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaUserRelSV;
import com.huazie.frame.auth.common.AccountTypeEnum;
import com.huazie.frame.auth.common.AuthRelTypeEnum;
import com.huazie.frame.auth.common.FleaAuthConstants;
import com.huazie.frame.auth.common.UserStateEnum;
import com.huazie.frame.auth.common.UserTypeEnum;
import com.huazie.frame.auth.common.pojo.account.attr.FleaAccountAttrPOJO;
import com.huazie.frame.auth.common.pojo.user.attr.FleaUserAttrPOJO;
import com.huazie.frame.auth.common.pojo.user.login.FleaUserLoginPOJO;
import com.huazie.frame.auth.common.pojo.user.register.FleaUserRegisterPOJO;
import com.huazie.frame.auth.common.service.interfaces.IFleaAuthSV;
import com.huazie.frame.auth.common.service.interfaces.IFleaUserModuleSV;
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
 * <p></p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class UserAuthTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthTest.class);

    private ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        LOGGER.debug("ApplicationContext={}", applicationContext);
    }

    @Test
    public void userLoginTest() {

        FleaUserLoginPOJO fleaUserLoginInfo = new FleaUserLoginPOJO();
        fleaUserLoginInfo.setAccountCode("13218010892");
        fleaUserLoginInfo.setAccountPwd("123qwe");

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
        List<FleaUserAttrPOJO> fleaUserAttrInfoList = new ArrayList<FleaUserAttrPOJO>();

        FleaUserAttrPOJO fleaUserAttrInfo1 = new FleaUserAttrPOJO();
        fleaUserAttrInfo1.setAttrCode(FleaAuthConstants.AttrCodeConstants.ATTR_CODE_USER_TYPE);
        fleaUserAttrInfo1.setAttrValue(UserTypeEnum.OPERATOR_USER.getType());
        fleaUserAttrInfoList.add(fleaUserAttrInfo1);

        // 添加账户属性
        List<FleaAccountAttrPOJO> fleaAccountAttrInfoList = new ArrayList<FleaAccountAttrPOJO>();

        FleaAccountAttrPOJO fleaAccountAttrInfo1 = new FleaAccountAttrPOJO();
        fleaAccountAttrInfo1.setAttrCode(FleaAuthConstants.AttrCodeConstants.ATTR_CODE_ACCOUNT_TYPE);
        fleaAccountAttrInfo1.setAttrValue(AccountTypeEnum.OPERATOR_ACCOUNT.getType());
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
        List<FleaUserAttrPOJO> fleaUserAttrInfoList = new ArrayList<FleaUserAttrPOJO>();

        FleaUserAttrPOJO fleaUserAttrInfo1 = new FleaUserAttrPOJO();
        fleaUserAttrInfo1.setAttrCode(FleaAuthConstants.AttrCodeConstants.ATTR_CODE_USER_TYPE);
        fleaUserAttrInfo1.setAttrValue(UserTypeEnum.SYSTEM_USER.getType());
        fleaUserAttrInfoList.add(fleaUserAttrInfo1);

        // 添加账户属性
        List<FleaAccountAttrPOJO> fleaAccountAttrInfoList = new ArrayList<FleaAccountAttrPOJO>();

        FleaAccountAttrPOJO fleaAccountAttrInfo1 = new FleaAccountAttrPOJO();
        fleaAccountAttrInfo1.setAttrCode(FleaAuthConstants.AttrCodeConstants.ATTR_CODE_ACCOUNT_TYPE);
        fleaAccountAttrInfo1.setAttrValue(AccountTypeEnum.SYSTEM_ACCOUNT.getType());
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
        IFleaAuthSV fleaUserLoginSV = (IFleaAuthSV) applicationContext.getBean("fleaAuthSV");
        fleaUserLoginSV.saveQuitLog(1L);
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

}
