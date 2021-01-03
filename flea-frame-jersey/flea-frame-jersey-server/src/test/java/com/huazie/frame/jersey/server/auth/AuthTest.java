package com.huazie.frame.jersey.server.auth;

import com.huazie.frame.auth.common.service.interfaces.IFleaUserModuleSV;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.jersey.common.FleaUserImplObjectFactory;
import org.junit.Before;
import org.junit.Test;
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

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(AuthTest.class);

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
        Long systemAccountId = 1000L;

        fleaUserModuleSV.initUserInfo(userId, accountId, systemAccountId, null, new FleaUserImplObjectFactory() {
            @Override
            public void initObject() {
                LOGGER.debug("Ending to init userInfo");
            }
        });
    }
}
