package com.huazie.fleaframework.auth;

import com.huazie.fleaframework.common.i18n.FleaI18nHelper;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.db.common.table.split.config.TableSplitConfig;
import org.junit.Test;

/**
 * 已验证
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public class AuthConfigTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(AuthConfigTest.class);

    @Test
    public void fleaI18nHelperTest() {
        String value = FleaI18nHelper.i18n("AUTH-COMMON0000000001", "auth");
        LOGGER.debug("Value = {}", value);
    }

    @Test
    public void testTableSplitConfig() {
        LOGGER.debug("Flea Table Split = {}", TableSplitConfig.getConfig().getFleaTableSplit());
    }
}
