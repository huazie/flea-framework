package com.huazie.fleaframework.auth.concurrency;

import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaLoginLogSV;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 数据库并发测试
 *
 * @author huazie
 * @version 1.2.0
 * @since 1.0.0
 */
public class LoginLogAuthMainTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(LoginLogAuthMainTest.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        LOGGER.debug("ApplicationContext={}", applicationContext);

        IFleaLoginLogSV fleaLoginLogSV = (IFleaLoginLogSV) applicationContext.getBean("fleaLoginLogSV");

        ExecutorService executorService = Executors.newCachedThreadPool();

        // 测试 EntityManager 并发 remove
        RemoveByIdTest.testRemoveById(executorService, fleaLoginLogSV);

        // 测试多线程获取主键
        GetFleaNextValueTest.testGetFleaNextValue(executorService, fleaLoginLogSV);

        // 测试 EntityManager 并发 find
        FindByIdTest.testFindById(executorService, fleaLoginLogSV);

        // 测试 FleaJPAQuery 并发
        FleaJPAQueryTest.testFleaJPAQuery(executorService, fleaLoginLogSV);

        // 测试 EntityManager 并发 persist
        InsertTest.testInsert(executorService, fleaLoginLogSV);

        // 测试 EntityManager 并发 merge
        UpdateTest.testUpdate(executorService, fleaLoginLogSV);
    }

}
