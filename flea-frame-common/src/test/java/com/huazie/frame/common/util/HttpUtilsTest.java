package com.huazie.frame.common.util;

import org.junit.Test;

public class HttpUtilsTest {

    @Test
    public void getAddressByTaoBao() {
        HttpUtils.getAddressByTaoBao("218.205.56.222");
    }

    @Test
    public void getAddressBySina() {
        HttpUtils.getAddressBySina("218.205.56.222");
    }
}