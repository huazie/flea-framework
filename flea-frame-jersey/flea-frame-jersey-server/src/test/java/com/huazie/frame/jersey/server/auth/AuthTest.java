package com.huazie.frame.jersey.server.auth;

import com.huazie.frame.auth.common.service.interfaces.IFleaAuthSV;
import com.huazie.frame.auth.common.service.interfaces.IFleaUserModuleSV;
import com.huazie.frame.common.IFleaUser;
import com.huazie.frame.common.object.FleaObject;
import com.huazie.frame.common.object.FleaObjectFactory;
import com.huazie.frame.jersey.common.FleaUserImplObjectFactory;
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
public class AuthTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthTest.class);

    private ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        LOGGER.debug("ApplicationContext={}", applicationContext);
    }

    @Test
    public void testInitUserInfo() {

        IFleaUserModuleSV fleaUserModuleSV = (IFleaUserModuleSV) applicationContext.getBean("fleaUserModuleSV");
        Long userId = 10000L;
        Long accountId = 10000L;
        Long systemAcctId = 1000L;

        fleaUserModuleSV.initUserInfo(userId, accountId, systemAcctId, null, new FleaUserImplObjectFactory() {
            @Override
            public void initObject() {
                LOGGER.debug("Ending to init userInfo");
            }
        });
    }
}
