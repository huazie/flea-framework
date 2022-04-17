package com.huazie.fleaframework.auth;

import com.huazie.fleaframework.common.i18n.FleaI18nHelper;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.db.common.table.split.config.TableSplitConfig;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class AuthSVTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(AuthSVTest.class);

    @Test
    public void testListRetainAll() {
        List<String> a1 = new ArrayList<>();
        a1.add("abc");
        a1.add("bcd");
        a1.add("cde");
        a1.add("def");
        a1.add("efg");
        a1.add("fgh");

        List<String> a2 = new ArrayList<>();
        a2.add("bcd");
        a2.add("def");
        a2.add("fgh");
        a2.add("hij");

        a1.retainAll(a2);

        LOGGER.debug("a1 = {}", a1);
    }

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
