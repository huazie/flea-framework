package com.huazie.fleaframework.common.util;

import com.huazie.fleaframework.common.PinyinEnum;
import org.junit.Test;

public class PinyinUtilsTest {

    @Test
    public void getJianPin() {
        PinyinUtils.getJianPin("我爱你中国", PinyinEnum.LOWER_CASE.getType());
        PinyinUtils.getJianPin("我爱你中国", PinyinEnum.UPPER_CASE.getType());
    }

    @Test
    public void getQuanPin() {
        PinyinUtils.getQuanPin("我爱你中国", PinyinEnum.LOWER_CASE.getType());
        PinyinUtils.getQuanPin("我爱你中国", PinyinEnum.UPPER_CASE.getType());
    }
}