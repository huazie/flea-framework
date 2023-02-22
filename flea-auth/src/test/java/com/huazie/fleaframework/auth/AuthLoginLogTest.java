package com.huazie.fleaframework.auth;

import com.huazie.fleaframework.auth.base.user.entity.FleaLoginLog;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.pool.FleaObjectPoolFactory;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.db.jpa.common.FleaJPAQuery;
import com.huazie.fleaframework.db.jpa.common.FleaJPAQueryPool;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

/**
 * 已验证
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class AuthLoginLogTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(AuthLoginLogTest.class);

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction tx;

    @BeforeClass
    public static void initEntityManager() {
        Map<String, Object> map = new HashMap<>();
        // 持久化配置文件
        map.put("eclipselink.persistencexml", "META-INF/fleaauth-persistence.xml");
        // 显示查询SQL
        map.put("eclipselink.logging.level.sql", "FINE");
        map.put("eclipselink.logging.parameters", "true");
        map.put("eclipselink.logging.thread", "true"); // 打印线程信息
        emf = Persistence.createEntityManagerFactory("fleaauth", map);
        em = emf.createEntityManager();
    }

    @AfterClass
    public static void closeEntityManager() {
        if (null != em) {
            em.close();
        }
        if (null != emf) {
            emf.close();
        }
    }

    @Before
    public void initTransaction() {
        tx = em.getTransaction();
    }

    @Test
    public void testFleaLoginLog() throws CommonException {
        FleaLoginLog fleaLoginLog = new FleaLoginLog();
        fleaLoginLog.setLoginIp4("0.0.0");
        fleaLoginLog.setCreateDate(DateUtils.getCurrentTime());

        FleaJPAQueryPool fleaJPAQueryPool = FleaObjectPoolFactory.getFleaObjectPool(FleaJPAQuery.class, FleaJPAQueryPool.class);
        FleaJPAQuery query = fleaJPAQueryPool.getFleaObject();
        LOGGER.debug("FleaJPAQuery: {}", query);
        query.init(em, FleaLoginLog.class, null);
        // 去重查询某一列数据, 模糊查询 para_code
        query.initQueryEntity(fleaLoginLog).distinct("accountId").like("loginIp4").getSingleResultList();

        FleaJPAQuery query1 = fleaJPAQueryPool.getFleaObject();
        LOGGER.debug("FleaJPAQuery: {}", query1);
        query1.init(em, FleaLoginLog.class, null);
        query1.initQueryEntity(fleaLoginLog).getResultList();
    }

}
