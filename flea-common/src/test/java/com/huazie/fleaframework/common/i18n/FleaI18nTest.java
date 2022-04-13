package com.huazie.fleaframework.common.i18n;

import com.huazie.fleaframework.common.FleaFrameManager;
import com.huazie.fleaframework.common.i18n.config.FleaI18nConfig;
import com.huazie.fleaframework.common.i18n.pojo.FleaI18nData;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.common.util.UnicodeUtils;
import org.junit.Test;

import java.util.Locale;

/**
 * @author huazie
 * @version 1.0.0
 */
public class FleaI18nTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaI18nTest.class);

    @Test
    public void fleaI18nConfigTest() {
        FleaI18nConfig config = FleaI18nConfig.getConfig();
        FleaI18nData fleaI18nData = config.getI18NData("ERROR0000000001", "error", Locale.US);
        LOGGER.debug("FleaI18nData = {}", fleaI18nData);
    }

    @Test
    public void fleaI18nHelperTest() {
        String value1 = FleaI18nHelper.i18n("ERROR0000000001", "error", Locale.FRANCE);
        LOGGER.debug("Value1 = {}", value1);
        String value2 = FleaI18nHelper.i18n("ERROR0000000002", new String[]{"Test"}, "error", Locale.US);
        LOGGER.debug("Value2 = {}", value2);
        FleaFrameManager.getManager().setLocale(Locale.CHINESE);
        String value3 = FleaI18nHelper.i18n("ERROR0000000002", new String[]{"Test"}, "error");
        LOGGER.debug("Value3 = {}", value3);
    }

    @Test
    public void nativeToUnicodeTest() {
        try {
            LOGGER.debug(UnicodeUtils.nativeToUnicode("你好，我是huazie"));
        } catch (Exception e) {
            LOGGER.error("Exception={}", e);
        }
    }

    @Test
    public void unicodeToNativeTest() {
        try {
            LOGGER.debug(UnicodeUtils.unicodeToNative("\\u68\\u75\\u61\\u7a\\u69\\u65"));
        } catch (Exception e) {
            LOGGER.error("Exception={}", e);
        }
    }

    @Test
    public void i18nTest() {
        String str = "hello, My name is {0}, I am from {1}";
        StringBuilder strBuilder = new StringBuilder(str);
        try {
            StringUtils.replace(strBuilder, "{" + 0 + "}", "huazie");
            StringUtils.replace(strBuilder, "{" + 1 + "}", "china");
            LOGGER.debug(strBuilder.toString());
        } catch (Exception e) {
            LOGGER.error("Exception={}", e);
        }
    }
}
