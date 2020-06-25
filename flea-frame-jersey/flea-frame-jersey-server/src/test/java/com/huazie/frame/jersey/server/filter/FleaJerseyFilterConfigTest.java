package com.huazie.frame.jersey.server.filter;

import com.huazie.frame.jersey.common.JerseyXmlDigesterHelper;
import com.huazie.frame.jersey.common.filter.config.FleaJerseyFilterConfig;
import com.huazie.frame.jersey.common.filter.config.Jersey;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p> Flea Jersey Filter 配置单元测试类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJerseyFilterConfigTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaJerseyFilterConfigTest.class);

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

}