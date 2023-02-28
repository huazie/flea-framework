package com.huazie.fleaframework.core.base.cfgdata;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.core.base.cfgdata.bean.FleaConfigDataSpringBean;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaMenuFavorites;
import com.huazie.fleaframework.core.common.pojo.FleaMenuFavoritesPOJO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 菜单收藏夹单元测试类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class FleaMenuFavoritesTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaJerseyResServiceTest.class);

    @Autowired
    private FleaConfigDataSpringBean bean;

    @Test
    public void testSaveFleaMenuFavorites() throws CommonException {
        FleaMenuFavoritesPOJO fleaMenuFavoritesPOJO = new FleaMenuFavoritesPOJO();
        fleaMenuFavoritesPOJO.setAccountId(10000L);
        fleaMenuFavoritesPOJO.setMenuCode("menu_add");
        fleaMenuFavoritesPOJO.setMenuName("菜单新增");
        fleaMenuFavoritesPOJO.setMenuIcon("plus-circle");
        fleaMenuFavoritesPOJO.setRemarks("自动添加");

        bean.saveFleaMenuFavorites(fleaMenuFavoritesPOJO);
    }

    @Test
    public void testQueryFleaMenuFavoritesByAccountId() throws CommonException {
        Long accountId = 10000L;
        List<FleaMenuFavorites> fleaMenuFavoritesList = bean.queryValidFleaMenuFavorites(accountId);

        LOGGER.debug("MENU FAVORITES LIST = {}", fleaMenuFavoritesList);
    }

    @Test
    public void testQueryFleaMenuFavoritesByAccountIdAndMenuCode() throws CommonException {
        Long accountId = 10000L;
        String menuCode = "menu_add";
        FleaMenuFavorites fleaMenuFavorites = bean.queryValidFleaMenuFavorites(accountId, menuCode);

        LOGGER.debug("MENU FAVORITES = {}", fleaMenuFavorites);
    }
}
