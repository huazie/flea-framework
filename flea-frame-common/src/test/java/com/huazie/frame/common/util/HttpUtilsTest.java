package com.huazie.frame.common.util;

import org.junit.Test;

public class HttpUtilsTest {

    @Test
    public void getAddressByTaoBao() {
        HttpUtils.getAddressByTaoBao("183.232.231.174");
    }

    @Test
    public void getAddressBySina() {
        HttpUtils.getAddressBySina("183.232.231.174");
    }
}