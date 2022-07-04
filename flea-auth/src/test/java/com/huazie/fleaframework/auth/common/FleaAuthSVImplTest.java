package com.huazie.fleaframework.auth.common;

import com.huazie.fleaframework.auth.base.function.entity.FleaMenu;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserModuleData;
import com.huazie.fleaframework.auth.common.service.interfaces.IFleaAuthSV;
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
public class FleaAuthSVImplTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaAuthSVImplTest.class);

    @Resource(name = "fleaAuthSV")
    private IFleaAuthSV fleaAuthSV;

    @Test
    public void getFleaUserModuleData() throws CommonException {

        long start = System.currentTimeMillis();

        FleaUserModuleData fleaUserModuleData = fleaAuthSV.getFleaUserModuleData(1000L);

        long end = System.currentTimeMillis();

        LOGGER.debug("COST = {}", end - start);
        LOGGER.debug("FleaUserModuleData = {}", fleaUserModuleData);
    }

    @Test
    public void queryAllAccessibleMenus() throws CommonException {
        Long accountId = 10002L;
        Long systemAccountId = 1001L;

        long start = System.currentTimeMillis();

        List<FleaMenu> fleaMenuList = fleaAuthSV.queryAllAccessibleMenus(accountId, systemAccountId);

        long end = System.currentTimeMillis();

        LOGGER.debug("COST = {}", end - start);
        LOGGER.debug("Menu List = {}", fleaMenuList);
    }

    @Test
    public void checkResourceAuth() throws CommonException {
        Long accountId = 1000L; // Flea Framework

        Long systemAccountId = 1002L; // Flea文件服务器

        String resourceCode = "upload";

        long start = System.currentTimeMillis();

        boolean isCheck = fleaAuthSV.checkResourceAuth(accountId, systemAccountId, resourceCode);

        long end = System.currentTimeMillis();

        LOGGER.debug("COST = {}", end - start);
        LOGGER.debug("Check = {}", isCheck);

        FleaAuthCheck.checkIsExistSystemRelResource(isCheck, resourceCode, systemAccountId);
    }
}