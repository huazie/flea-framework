package com.huazie.frame.common.util;

import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertTrue;

/**
 * <p> 数字相关工具测试类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class NumberUtilsTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(NumberUtilsTest.class);

    @Test
    public void testIsPositiveNumber() {
        Integer integerNum1 = 10;
        LOGGER.debug("INTEGER NUM1 IS POSITIVE NUMBER : {}", NumberUtils.isPositiveNumber(integerNum1));
        Integer integerNum2 = -10;
        LOGGER.debug("INTEGER NUM2 IS POSITIVE NUMBER : {}", NumberUtils.isPositiveNumber(integerNum2));

        Long longNum1 = 10L;
        LOGGER.debug("LONG NUM1 IS POSITIVE NUMBER : {}", NumberUtils.isPositiveNumber(longNum1));
        Long longNum2 = -10L;
        LOGGER.debug("LONG NUM2 IS POSITIVE NUMBER : {}", NumberUtils.isPositiveNumber(longNum2));

        Short shortNum1 = 10;
        LOGGER.debug("SHORT NUM1 IS POSITIVE NUMBER : {}", NumberUtils.isPositiveNumber(shortNum1));
        Short shortNum2 = -10;
        LOGGER.debug("SHORT NUM2 IS POSITIVE NUMBER : {}", NumberUtils.isPositiveNumber(shortNum2));

        Float floatNum1 = 10.0f;
        LOGGER.debug("FLOAT NUM1 IS POSITIVE NUMBER : {}", NumberUtils.isPositiveNumber(floatNum1));
        Float floatNum2 = -10.0f;
        LOGGER.debug("FLOAT NUM2 IS POSITIVE NUMBER : {}", NumberUtils.isPositiveNumber(floatNum2));

        Double doubleNum1 = 10.0;
        LOGGER.debug("DOUBLE NUM1 IS POSITIVE NUMBER : {}", NumberUtils.isPositiveNumber(doubleNum1));
        Double doubleNum2 = -10.0;
        LOGGER.debug("DOUBLE NUM2 IS POSITIVE NUMBER : {}", NumberUtils.isPositiveNumber(doubleNum2));

        Byte byteNum1 = 10;
        LOGGER.debug("BYTE NUM1 IS POSITIVE NUMBER : {}", NumberUtils.isPositiveNumber(byteNum1));
        Byte byteNum2 = -10;
        LOGGER.debug("BYTE NUM2 IS POSITIVE NUMBER : {}", NumberUtils.isPositiveNumber(byteNum2));
    }

    @Test
    public void testSqrtForBigInteger() {
        BigInteger sqrtNum = NumberUtils.sqrt(BigInteger.valueOf(1000002));
        assertTrue(sqrtNum.compareTo(BigInteger.valueOf(1000)) == 0);
    }
}
