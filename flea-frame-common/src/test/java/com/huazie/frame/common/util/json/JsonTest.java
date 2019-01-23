package com.huazie.frame.common.util.json;

import com.huazie.frame.common.i18n.pojo.FleaI18nData;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(JsonTest.class);

    @Test
    public void testFastJson() {
        FleaI18nData data = new FleaI18nData();
        data.setKey("CACHE0000001");
        data.setValue("你好，我是huazie");
        String jsonString = FastJsonUtils.toJsonString(data);
        LOGGER.debug("JSON = {}", jsonString);

        FleaI18nData data1 = FastJsonUtils.toEntity(jsonString, FleaI18nData.class);
        LOGGER.debug("DATA = {}", data1);
    }

    @Test
    public void testGsonJson() {

    }

    @Test
    public void testJson() {

    }
}

