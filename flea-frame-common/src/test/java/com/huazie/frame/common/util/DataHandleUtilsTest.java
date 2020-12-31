package com.huazie.frame.common.util;

import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;

public class DataHandleUtilsTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(DataHandleUtilsTest.class);

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
        // 图片压缩 （压缩率 高于95%, 压缩效果不好）
        File file = new File("E:\\IMG.jpg");
        String input = IOUtils.toString(file, true);

        String compressedStr = DataHandleUtils.gzip(input);

        String originalStr = DataHandleUtils.unGzip(compressedStr);

        File newFile = IOUtils.toFile(originalStr, "E:\\IMG_1.jpg", true);
        LOGGER.debug("FILE : {}", newFile);
    }

    @Test
    public void testGzip1() {
        // 配置文件压缩 (压缩率 低于50%，压缩效果明显)
        InputStream inputStream = IOUtils.getInputStreamFromClassPath("applicationContext.xml");
        String input = IOUtils.toString(inputStream, false);

        String compressedStr = DataHandleUtils.gzip(input);

        String originalStr = DataHandleUtils.unGzip(compressedStr);

        File file = IOUtils.toFile(originalStr, "E:\\applicationContext.xml", false);
        LOGGER.debug("FILE : {}", file);
    }

    @Test
    public void testZip() {
        // 图片压缩 （压缩率 高于95%）
        File file = new File("E:\\IMG.jpg");
        String input = IOUtils.toString(file, true);

        String compressedStr = DataHandleUtils.zip(input);

        String originalStr = DataHandleUtils.unZip(compressedStr);

        File newFile = IOUtils.toFile(originalStr, "E:\\IMG_1.jpg", true);
        LOGGER.debug("FILE : {}", newFile);
    }

    @Test
    public void testZip1() {
        // 配置文件压缩 (压缩率 低于50%)
        InputStream inputStream = IOUtils.getInputStreamFromClassPath("applicationContext.xml");
        String input = IOUtils.toString(inputStream, false);

        String compressedStr = DataHandleUtils.zip(input);

        String originalStr = DataHandleUtils.unZip(compressedStr);

        File file = IOUtils.toFile(originalStr, "E:\\applicationContext.xml", false);
        LOGGER.debug("FILE : {}", file);
    }

}