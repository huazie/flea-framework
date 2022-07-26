package com.huazie.fleaframework.common.util;

import com.huazie.fleaframework.common.FleaEntityConstants;
import com.huazie.fleaframework.common.config.ConfigItem;
import org.junit.Test;

import java.util.Date;

public class ReflectUtilsTest {

    @Test
    public void setValue() {
        ConfigItem configItem = new ConfigItem();
        Date currentDate = DateUtils.getCurrentTime();
        ReflectUtils.setValue(configItem, FleaEntityConstants.E_DONE_DATE, currentDate);
    }
}