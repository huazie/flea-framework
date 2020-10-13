package com.huazie.frame.core.base.cfgdata;

import com.huazie.frame.core.base.cfgdata.bean.FleaConfigDataSpringBean;
import com.huazie.frame.core.common.pojo.FleaMenuFavoritesPOJO;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>  </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaMenuFavoritesTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaJerseyResServiceTest.class);

    private ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        LOGGER.debug("ApplicationContext={}", applicationContext);
    }

    @Test
    public void testSaveFleaMenuFavorites() {

        FleaConfigDataSpringBean bean = (FleaConfigDataSpringBean) applicationContext.getBean("fleaConfigDataSpringBean");

        try {
            FleaMenuFavoritesPOJO fleaMenuFavoritesPOJO = new FleaMenuFavoritesPOJO();
            fleaMenuFavoritesPOJO.setAccountId(1015L);
            fleaMenuFavoritesPOJO.setMenuCode("menu_add");
            fleaMenuFavoritesPOJO.setMenuName("菜单新增");
            fleaMenuFavoritesPOJO.setMenuIcon("plus-circle");
            fleaMenuFavoritesPOJO.setRemarks("自动添加");

            bean.saveFleaMenuFavorites(fleaMenuFavoritesPOJO);

        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }
}
