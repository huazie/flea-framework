package com.huazie.fleaframework.auth.user;

import com.huazie.fleaframework.auth.common.service.interfaces.IFleaAuthSV;
import com.huazie.fleaframework.auth.util.FleaAuthLogger;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class UserAuthMainTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(UserAuthMainTest.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        LOGGER.debug("ApplicationContext={}", applicationContext);
        testAsyncSaveQuitLog(applicationContext);
    }

    private static void testAsyncSaveQuitLog(ApplicationContext applicationContext) {
        final IFleaAuthSV fleaUserLoginSV = (IFleaAuthSV) applicationContext.getBean("fleaAuthSV");
        Long accountId = 1L;
        FleaAuthLogger.asyncSaveQuitLog(fleaUserLoginSV, accountId);
        FleaAuthLogger.asyncSaveQuitLog(fleaUserLoginSV, accountId);
    }

}
