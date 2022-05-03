package com.huazie.fleaframework.db.jpa;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.pool.FleaObjectPoolFactory;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaConfigData;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyResource;
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
import java.util.List;
import java.util.Map;

public class FleaJPAQueryTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaJPAQueryTest.class);

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction tx;

    @BeforeClass
    public static void initEntityManager() {
        Map<String, Object> map = new HashMap<>();
        // 持久化配置文件
        map.put("eclipselink.persistencexml", "META-INF/fleaconfig-persistence.xml");
        // 显示查询SQL
        map.put("eclipselink.logging.level.sql", "FINE");
        map.put("eclipselink.logging.parameters", "true");
        emf = Persistence.createEntityManagerFactory("fleaconfig", map);
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
    public void testFleaJPAQuery() {
        //FleaFrameManager.getManager().setLocale(Locale.US);
        try {
            FleaJPAQueryPool fleaJPAQueryPool = FleaObjectPoolFactory.getFleaObjectPool(FleaJPAQuery.class, FleaJPAQueryPool.class);
            FleaJPAQuery query = fleaJPAQueryPool.getFleaObject();
            LOGGER.debug("FleaJPAQuery: {}", query);
            query.init(em, FleaConfigData.class, null);
            // 去重查询某一列数据, 模糊查询 config_code
            query.distinct("data1").like("configCode","hua");
            List<String> list = query.getSingleResultList();
            LOGGER.debug("List : {}", list);

            FleaJPAQuery query1 = fleaJPAQueryPool.getFleaObject();
            LOGGER.debug("FleaJPAQuery: {}", query1);
            query1.init(em, FleaJerseyResource.class, null);
            List<FleaJerseyResource> resourceList = query1.getResultList();
            LOGGER.debug("Resource List : {}", resourceList);

        } catch (CommonException e) {
            LOGGER.error("Exception:", e);
        }
    }
}
