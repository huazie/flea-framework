package com.huazie.frame.auth;

import com.huazie.frame.auth.base.user.entity.FleaLoginLog;
import com.huazie.frame.common.pool.FleaObjectPoolFactory;
import com.huazie.frame.db.common.exception.DaoException;
import com.huazie.frame.db.jpa.common.FleaJPAQuery;
import com.huazie.frame.db.jpa.common.FleaJPAQueryPool;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class AuthTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthTest.class);

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction tx;

    @BeforeClass
    public static void initEntityManager() {
        Map<String, Object> map = new HashMap<String, Object>();
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
    public void testFleaLoginLog() {
        try {
            FleaJPAQueryPool fleaJPAQueryPool = FleaObjectPoolFactory.getFleaObjectPool(FleaJPAQuery.class, FleaJPAQueryPool.class);
            FleaJPAQuery query = fleaJPAQueryPool.getFleaObject();
            LOGGER.debug("FleaJPAQuery: {}", query);
            query.init(em, FleaLoginLog.class, null);
            // 去重查询某一列数据, 模糊查询 para_code
            query.distinct("accountId").like("loginIp4","127.0.0");
            List<String> list = query.getSingleResultList();
            LOGGER.debug("List : {}", list);

            FleaJPAQuery query1 = fleaJPAQueryPool.getFleaObject();
            LOGGER.debug("FleaJPAQuery: {}", query1);
            query1.init(em, FleaLoginLog.class, null);
            List<FleaLoginLog> fleaLoginLogList = query1.getResultList();
            LOGGER.debug("Resource List : {}", fleaLoginLogList);

        } catch (DaoException e) {
            LOGGER.error("Exception:", e);
        }
    }

}
