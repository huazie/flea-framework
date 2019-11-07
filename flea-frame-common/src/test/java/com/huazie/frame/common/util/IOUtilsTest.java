package com.huazie.frame.common.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;

public class IOUtilsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(IOUtilsTest.class);

    @Test
    public void testToStringAndFile() {
        InputStream inputStream = IOUtils.getInputStreamFromClassPath("flea/flea-config.xml");
        String input = IOUtils.toString(inputStream, true);
        LOGGER.debug("INPUT STRING : \n{}", input);

        File file = IOUtils.toFile(input, "E:\\flea-config.xml", true);
        LOGGER.debug("FILE : {}", file);
    }

    @Test
    public void testOtherFile() {
        File file = new File("E:\\IMG.jpg");
        String input = IOUtils.toString(file, true);
        IOUtils.toFile(input, "E:\\IMG_1.jpg", true);
    }
}