package com.huazie.frame.common.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class DataHandleUtilsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataHandleUtilsTest.class);

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

    @Test
    public void testGzip() {
        File file = new File("E:\\IMG.jpg");
        String input = IOUtils.toString(file);

        String compressedStr = DataHandleUtils.gzip(input);

        String originalStr = DataHandleUtils.unGzip(compressedStr);

        File newFile = IOUtils.toFile(originalStr, "E:\\IMG_1.jpg");
        LOGGER.debug("FILE : {}", newFile);
    }

    @Test
    public void testZip() {
        File file = new File("E:\\IMG.jpg");
        String input = IOUtils.toString(file);

        String compressedStr = DataHandleUtils.zip(input);

        String originalStr = DataHandleUtils.unZip(compressedStr);

        File newFile = IOUtils.toFile(originalStr, "E:\\IMG_1.jpg");
        LOGGER.debug("FILE : {}", newFile);
    }
}