package com.huazie.fleaframework.core.base.cfgdata;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.core.base.cfgdata.bean.FleaConfigDataSpringBean;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaMenuFavorites;
import com.huazie.fleaframework.core.common.pojo.FleaMenuFavoritesPOJO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * <p>  </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaMenuFavoritesTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaJerseyResServiceTest.class);

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
            fleaMenuFavoritesPOJO.setAccountId(10000L);
            fleaMenuFavoritesPOJO.setMenuCode("menu_add");
            fleaMenuFavoritesPOJO.setMenuName("菜单新增");
            fleaMenuFavoritesPOJO.setMenuIcon("plus-circle");
            fleaMenuFavoritesPOJO.setRemarks("自动添加");

            bean.saveFleaMenuFavorites(fleaMenuFavoritesPOJO);

        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testQueryFleaMenuFavoritesByAccountId() {

        FleaConfigDataSpringBean bean = (FleaConfigDataSpringBean) applicationContext.getBean("fleaConfigDataSpringBean");

        try {
            Long accountId = 10000L;
            List<FleaMenuFavorites> fleaMenuFavoritesList = bean.queryValidFleaMenuFavorites(accountId);

            LOGGER.debug("MENU FAVORITES LIST = {}", fleaMenuFavoritesList);

        } catch (CommonException e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testQueryFleaMenuFavoritesByAccountIdAndMenuCode() {

        FleaConfigDataSpringBean bean = (FleaConfigDataSpringBean) applicationContext.getBean("fleaConfigDataSpringBean");

        try {
            Long accountId = 10000L;
            String menuCode = "menu_add";
            FleaMenuFavorites fleaMenuFavorites = bean.queryValidFleaMenuFavorites(accountId, menuCode);

            LOGGER.debug("MENU FAVORITES = {}", fleaMenuFavorites);

        } catch (CommonException e) {
            LOGGER.error("Exception:", e);
        }
    }
}
