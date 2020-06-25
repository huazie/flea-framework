package com.huazie.frame.auth.user;

import com.huazie.frame.auth.base.user.entity.FleaAccount;
import com.huazie.frame.auth.common.UserStateEnum;
import com.huazie.frame.auth.common.pojo.account.attr.FleaAccountAttrPOJO;
import com.huazie.frame.auth.common.pojo.user.attr.FleaUserAttrPOJO;
import com.huazie.frame.auth.common.pojo.user.login.FleaUserLoginPOJO;
import com.huazie.frame.auth.common.pojo.user.register.FleaUserRegisterPOJO;
import com.huazie.frame.auth.common.service.interfaces.IFleaAuthSV;
import com.huazie.frame.auth.common.service.interfaces.IFleaUserLoginSV;
import com.huazie.frame.auth.common.service.interfaces.IFleaUserRegisterSV;
import com.huazie.frame.common.FleaSessionManager;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.concurrent.FleaAsyncVoidTask;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

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

        FleaUserRegisterPOJO fleaUserRegisterPOJO = new FleaUserRegisterPOJO();

        fleaUserRegisterPOJO.setAccountCode("13218010892");
        fleaUserRegisterPOJO.setAccountPwd("123qwe");
        fleaUserRegisterPOJO.setState(UserStateEnum.IN_USE.getValue());

        // 添加用户属性
        List<FleaUserAttrPOJO> fleaUserAttrInfoList = new ArrayList<FleaUserAttrPOJO>();

        FleaUserAttrPOJO fleaUserAttrInfo1 = new FleaUserAttrPOJO();
        fleaUserAttrInfo1.setAttrCode("USER_TEST1");
        fleaUserAttrInfo1.setAttrValue("11111");
        fleaUserAttrInfoList.add(fleaUserAttrInfo1);

        FleaUserAttrPOJO fleaUserAttrInfo2 = new FleaUserAttrPOJO();
        fleaUserAttrInfo2.setAttrCode("USER_TEST2");
        fleaUserAttrInfo2.setAttrValue("22222");
        fleaUserAttrInfoList.add(fleaUserAttrInfo2);

        // 添加账户属性
        List<FleaAccountAttrPOJO> fleaAccountAttrInfoList = new ArrayList<FleaAccountAttrPOJO>();

        FleaAccountAttrPOJO fleaAccountAttrInfo1 = new FleaAccountAttrPOJO();
        fleaAccountAttrInfo1.setAttrCode("ACCOUNT_TEST1");
        fleaAccountAttrInfo1.setAttrValue("11111");
        fleaAccountAttrInfoList.add(fleaAccountAttrInfo1);

        FleaAccountAttrPOJO fleaAccountAttrInfo2 = new FleaAccountAttrPOJO();
        fleaAccountAttrInfo2.setAttrCode("ACCOUNT_TEST2");
        fleaAccountAttrInfo2.setAttrValue("22222");
        fleaAccountAttrInfoList.add(fleaAccountAttrInfo2);

        fleaUserRegisterPOJO.setUserAttrList(fleaUserAttrInfoList);
        fleaUserRegisterPOJO.setAccountAttrList(fleaAccountAttrInfoList);

        fleaUserRegisterPOJO.setRemarks("用户自己注册时新增数据");

        IFleaUserRegisterSV fleaUserRegisterSV = (IFleaUserRegisterSV) applicationContext.getBean("fleaUserRegisterSV");

        try {
            FleaAccount fleaAccount = fleaUserRegisterSV.register(fleaUserRegisterPOJO);
            LOGGER.debug("FleaAccount = {}", fleaAccount);
        } catch (CommonException e) {
            LOGGER.error("Exception = ", e);
        }
    }

    @Test
    public void testSaveQuitLog() throws Exception {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                IFleaAuthSV fleaUserLoginSV = (IFleaAuthSV) applicationContext.getBean("fleaAuthSV");
                fleaUserLoginSV.saveQuitLog(1L);
            }
        });
        thread.start();
    }

    @Test
    public void testAsyncSaveQuitLog() throws Exception {
        IFleaAuthSV fleaUserLoginSV = (IFleaAuthSV) applicationContext.getBean("fleaAuthSV");
        Long accountId = 1L;
        FleaAsyncVoidTask fleaAsyncVoidTask = new FleaAsyncVoidTask(FleaSessionManager.getUserInfo(), fleaUserLoginSV,
                "saveQuitLog", new Class<?>[]{Long.class}, new Object[]{accountId});
        ForkJoinPool forkJoinPool = new ForkJoinPool(1);
        forkJoinPool.execute(fleaAsyncVoidTask);
    }
}
