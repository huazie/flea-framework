package com.huazie.fleaframework.common.util;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import org.junit.Test;

public class HttpUtilsTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(HttpUtilsTest.class);

    @Test
    public void getAddressByTaoBao() {
        HttpUtils.getAddressByTaoBao("60.176.205.72");
    }

    @Test
    public void get() {
        String url = "http://localhost:8080/demo?factor=2441696060695515";
        LOGGER.debug(HttpUtils.get(url));
    }
}