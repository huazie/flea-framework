package com.huazie.frame.db.jpa;

import com.huazie.frame.common.FleaApplicationContext;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.SharedEntityManagerCreator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class EntityMangerTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(EntityMangerTest.class);

    @Before
    public void init() {
        new ClassPathXmlApplicationContext("applicationContext.xml");
        LOGGER.debug("ApplicationContext={}", FleaApplicationContext.getApplicationContext());
    }

    @Test
    public void test() {
        JpaTransactionManager manger = (JpaTransactionManager) FleaApplicationContext.getBean("fleaAuthTransactionManager");
        LOGGER.debug("JpaTransactionManager={}", manger);
        EntityManagerFactory entityManagerFactory = manger.getEntityManagerFactory();
        LOGGER.debug("EntityManagerFactory={}", entityManagerFactory);
        EntityManager entityManager = SharedEntityManagerCreator.createSharedEntityManager(entityManagerFactory);
        LOGGER.debug("EntityManager={}", entityManager);
    }
}
