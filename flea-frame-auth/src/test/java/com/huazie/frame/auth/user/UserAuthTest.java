package com.huazie.frame.auth.user;

import com.huazie.frame.auth.base.user.entity.FleaAccount;
import com.huazie.frame.auth.common.pojo.user.login.FleaUserLoginInfo;
import com.huazie.frame.auth.common.service.interfaces.IFleaUserLoginSV;
import com.huazie.frame.common.exception.CommonException;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
}
