package com.huazie.fleaframework.jersey.server.filter;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.jersey.common.JerseyXmlDigesterHelper;
import com.huazie.fleaframework.jersey.common.filter.config.FleaJerseyFilterConfig;
import com.huazie.fleaframework.jersey.common.filter.config.Jersey;
import org.junit.Test;

/**
 * <p> Flea Jersey Filter 配置单元测试类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJerseyFilterConfigTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaJerseyFilterConfigTest.class);

    @Test
    public void testJerseyFilter() {
        Jersey jersey = JerseyXmlDigesterHelper.getInstance().getJersey();
        LOGGER.debug("config={}", jersey);
    }

    @Test
    public void testJerseyFilterConfigOfBefore() {
        LOGGER.debug("before={}", FleaJerseyFilterConfig.getBeforeFilters());
    }

    @Test
    public void testJerseyFilterConfigOfService() {
        LOGGER.debug("service={}", FleaJerseyFilterConfig.getServiceFilters());
    }

    @Test
    public void testJerseyFilterConfigOfAfter() {
        LOGGER.debug("after={}", FleaJerseyFilterConfig.getAfterFilters());
    }

    @Test
    public void testJerseyFilterConfigOfError() {
        LOGGER.debug("error={}", FleaJerseyFilterConfig.getErrorFilters());
    }

    @Test
    public void testJerseyFilterConfigOfI18nError() {
        LOGGER.debug("i18n-error-mapping={}", FleaJerseyFilterConfig.getI18nErrorMapping("ERROR-JERSEY-FILTER0000000003"));
    }
}