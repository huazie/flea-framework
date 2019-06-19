package com.huazie.frame.jersey.api.filter;

import com.huazie.frame.jersey.api.filter.config.FleaJerseyFilterConfig;
import com.huazie.frame.jersey.api.filter.config.Jersey;
import com.huazie.frame.jersey.common.JerseyXmlDigesterHelper;
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

    private final static Logger LOGGER = LoggerFactory.getLogger(FleaJerseyFilterConfigTest.class);

    @Test
    public void testJerseyFilter() {
        Jersey jersey = JerseyXmlDigesterHelper.getInstance().getJersey();
        LOGGER.debug("config={}", jersey);
    }

    @Test
    public void testJerseyFilterConfigOfBefore() {
        FleaJerseyFilterConfig config = FleaJerseyFilterConfig.getConfig();
        LOGGER.debug("before={}", config.getBeforeFilters());
    }

    @Test
    public void testJerseyFilterConfigOfService() {
        FleaJerseyFilterConfig config = FleaJerseyFilterConfig.getConfig();
        LOGGER.debug("service={}", config.getServiceFilters());
    }

    @Test
    public void testJerseyFilterConfigOfAfter() {
        FleaJerseyFilterConfig config = FleaJerseyFilterConfig.getConfig();
        LOGGER.debug("after={}", config.getAfterFilters());
    }

    @Test
    public void testJerseyFilterConfigOfError() {
        FleaJerseyFilterConfig config = FleaJerseyFilterConfig.getConfig();
        LOGGER.debug("error={}", config.getErrorFilters());
    }

}