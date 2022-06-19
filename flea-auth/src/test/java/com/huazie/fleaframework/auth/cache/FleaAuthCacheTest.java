package com.huazie.fleaframework.auth.cache;

import com.huazie.fleaframework.auth.base.function.entity.FleaMenu;
import com.huazie.fleaframework.auth.cache.bean.FleaAuthCache;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserModuleData;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class FleaAuthCacheTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaAuthCacheTest.class);

    @Resource(type = FleaAuthCache.class)
    private FleaAuthCache fleaAuthCache; // Flea 授权缓存

    @Test
    public void getFleaUserModuleData() throws CommonException {
        Long accountId = 10000L;

        long start = System.currentTimeMillis();

        FleaUserModuleData fleaUserModuleData = fleaAuthCache.getFleaUserModuleData(accountId);

        long end = System.currentTimeMillis();

        LOGGER.debug("COST = {}", end - start);
        LOGGER.debug("FleaUserModuleData = {}", fleaUserModuleData);
    }

    @Test
    public void queryAllAccessibleMenus() throws CommonException {
        Long accountId = 10002L;
        Long systemAccountId = 1001L;

        long start = System.currentTimeMillis();

        List<FleaMenu> fleaMenuList = fleaAuthCache.queryAllAccessibleMenus(accountId, systemAccountId);

        long end = System.currentTimeMillis();

        LOGGER.debug("COST = {}", end - start);
        LOGGER.debug("Menu List = {}", fleaMenuList);
    }

    @Test
    public void checkResourceAuth() throws CommonException {
        Long accountId = 10000L;

        Long systemAccountId = 1002L;

        String resourceCode = "upload";

        long start = System.currentTimeMillis();

        boolean checkFlag = fleaAuthCache.checkResourceAuth(accountId, systemAccountId, resourceCode);

        long end = System.currentTimeMillis();

        LOGGER.debug("COST = {}", end - start);
        LOGGER.debug("Check = {}", checkFlag);

        FleaAuthCheck.checkIsExistSystemRelResource(checkFlag, resourceCode, systemAccountId);
    }

}