package com.huazie.fleaframework.jersey.client.auth;

import com.huazie.fleaframework.auth.common.service.interfaces.IFleaUserModuleSV;
import com.huazie.fleaframework.common.IFleaUser;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.jersey.common.FleaUserImplObjectFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class AuthTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(AuthTest.class);

    @Resource(name = "fleaUserModuleSV")
    private IFleaUserModuleSV fleaUserModuleSV;

    @Test
    public void testInitUserInfo() throws CommonException {
        Long accountId = 10000L;
        Long systemAccountId = 1000L;

        fleaUserModuleSV.initUserInfo(accountId, systemAccountId, null, new FleaUserImplObjectFactory() {
            @Override
            public void initObject(IFleaUser fleaUser) {
                LOGGER.debug("USER_INFO = {}", fleaUser.toMap());
                LOGGER.debug("Ending to init userInfo");
            }
        });
    }

    @Test
    public void saveLoginLog() {
        fleaUserModuleSV.saveLoginLog(10000L, null);
    }
}
