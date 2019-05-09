package com.huazie.frame.db.jpa;

import com.huazie.frame.common.FleaFrameManager;
import com.huazie.frame.core.base.cfgdata.entity.FleaParaDetail;
import com.huazie.frame.core.base.cfgdata.ivalues.IFleaParaDetailValue;
import com.huazie.frame.db.common.exception.DaoException;
import com.huazie.frame.db.jpa.common.FleaJPAQuery;
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
import java.util.Locale;
import java.util.Map;

public class FleaJPAQueryTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(FleaJPAQueryTest.class);

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction tx;

    @BeforeClass
    public static void initEntityManager() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("eclipselink.persistencexml", "META-INF/fleaconfig-persistence.xml");
        emf = Persistence.createEntityManagerFactory("fleaconfig", map);
        em = emf.createEntityManager();
    }

    @AfterClass
    public static void closeEntityManager() {
        if(null != em) {
            em.close();
        }
        if(null != emf) {
            emf.close();
        }
    }

    @Before
    public void initTransaction() {
        tx = em.getTransaction();
    }

    @Test
    public void testFleaJPAQuery() {
        FleaFrameManager.getManager().setLocale(Locale.US);
        try {
            FleaJPAQuery query = FleaJPAQuery.getQuery();
            query.init(em, FleaParaDetail.class, null);
            query.distinct(IFleaParaDetailValue.S_PARA_TYPE);
            List<FleaParaDetail> list = query.getSingleResultList();
            LOGGER.debug("list:{}", list);
        } catch (DaoException e) {
            LOGGER.error("Exception:", e);
        }
    }
}
