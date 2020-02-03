package com.huazie.frame.auth.user;

import com.huazie.frame.auth.base.user.entity.FleaAccount;
import com.huazie.frame.auth.common.pojo.account.attr.FleaAccountAttrInfo;
import com.huazie.frame.auth.common.pojo.user.attr.FleaUserAttrInfo;
import com.huazie.frame.auth.common.pojo.user.login.FleaUserLoginInfo;
import com.huazie.frame.auth.common.pojo.user.register.FleaUserRegisterInfo;
import com.huazie.frame.auth.common.service.interfaces.IFleaUserLoginSV;
import com.huazie.frame.auth.common.service.interfaces.IFleaUserRegisterSV;
import com.huazie.frame.common.exception.CommonException;
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

        FleaUserLoginInfo fleaUserLoginInfo = new FleaUserLoginInfo();
        fleaUserLoginInfo.setAccountCode("13218010892");
        fleaUserLoginInfo.setAccountPwd("123asd");

        IFleaUserLoginSV fleaUserLoginSV = (IFleaUserLoginSV) applicationContext.getBean("fleaUserLoginSV");

        try {
            FleaAccount fleaAccount = fleaUserLoginSV.login(fleaUserLoginInfo);
            LOGGER.debug("FleaAccount = {}", fleaAccount);
        } catch (CommonException e) {
            LOGGER.error("Exception = ", e);
        }

    }

    @Test
    public void userRegisterTest() {

        FleaUserRegisterInfo fleaUserRegisterInfo = new FleaUserRegisterInfo();

        fleaUserRegisterInfo.setAccountCode("13218010892");
        fleaUserRegisterInfo.setAccountPwd("123qwe");

        // 添加用户属性
        List<FleaUserAttrInfo> fleaUserAttrInfoList = new ArrayList<FleaUserAttrInfo>();

        FleaUserAttrInfo fleaUserAttrInfo1 = new FleaUserAttrInfo();
        fleaUserAttrInfo1.setAttrCode("USER_TEST1");
        fleaUserAttrInfo1.setAttrValue("11111");
        fleaUserAttrInfoList.add(fleaUserAttrInfo1);

        FleaUserAttrInfo fleaUserAttrInfo2 = new FleaUserAttrInfo();
        fleaUserAttrInfo2.setAttrCode("USER_TEST2");
        fleaUserAttrInfo2.setAttrValue("22222");
        fleaUserAttrInfoList.add(fleaUserAttrInfo2);

        // 添加账户属性
        List<FleaAccountAttrInfo> fleaAccountAttrInfoList = new ArrayList<FleaAccountAttrInfo>();

        FleaAccountAttrInfo fleaAccountAttrInfo1 = new FleaAccountAttrInfo();
        fleaAccountAttrInfo1.setAttrCode("ACCOUNT_TEST1");
        fleaAccountAttrInfo1.setAttrValue("11111");
        fleaAccountAttrInfoList.add(fleaAccountAttrInfo1);

        FleaAccountAttrInfo fleaAccountAttrInfo2 = new FleaAccountAttrInfo();
        fleaAccountAttrInfo2.setAttrCode("ACCOUNT_TEST2");
        fleaAccountAttrInfo2.setAttrValue("22222");
        fleaAccountAttrInfoList.add(fleaAccountAttrInfo2);

        fleaUserRegisterInfo.setUserAttrList(fleaUserAttrInfoList);
        fleaUserRegisterInfo.setAccountAttrList(fleaAccountAttrInfoList);

        fleaUserRegisterInfo.setRemarks("用户自己注册时新增数据");

        IFleaUserRegisterSV fleaUserRegisterSV = (IFleaUserRegisterSV) applicationContext.getBean("fleaUserRegisterSV");

        try {
            FleaAccount fleaAccount = fleaUserRegisterSV.register(fleaUserRegisterInfo);
            LOGGER.debug("FleaAccount = {}", fleaAccount);
        } catch (CommonException e) {
            LOGGER.error("Exception = ", e);
        }
    }
}
