package com.huazie.frame.common.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataConvertTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataConvertTest.class);

    @Test
    public void hex2byte() {
        String hex = "ABCDE";
        byte[] bytes = DataHandleUtils.hex2byte(hex);
        if(ArrayUtils.isNotEmpty(bytes)){
            for(byte bte : bytes){
                LOGGER.debug("" + bte);
            }
        }
    }

    @Test
    public void byte2hex() {
        String hex = "ABCD";
        byte[] bytes = DataHandleUtils.hex2byte(hex);
        String hex1 = DataHandleUtils.byte2hex(bytes);
        LOGGER.debug("byte2hex = " + hex1);
    }
}