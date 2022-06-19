package com.huazie.fleaframework.auth.base.user.concurrency;

import com.huazie.fleaframework.auth.common.service.interfaces.IFleaUserModuleSV;
import com.huazie.fleaframework.auth.util.FleaAuthLogger;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 待验证
 *
 * @author huazie
 * @version 2.0.0
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
        final IFleaUserModuleSV fleaUserModuleSV = (IFleaUserModuleSV) applicationContext.getBean("fleaUserModuleSV");
        Long accountId = 10000L;
        FleaAuthLogger.asyncSaveQuitLog(fleaUserModuleSV, accountId);
        FleaAuthLogger.asyncSaveQuitLog(fleaUserModuleSV, accountId);
        FleaAuthLogger.asyncSaveQuitLog(fleaUserModuleSV, accountId);
    }

}
