package com.huazie.fleaframework.db.common.table;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.db.common.table.split.ITableSplit;
import com.huazie.fleaframework.db.common.table.split.TableSplitEnum;
import com.huazie.fleaframework.db.common.table.split.config.TableSplitConfig;
import com.huazie.fleaframework.db.common.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * 分表转换测试类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public class TableSplitTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(TableSplitTest.class);

    @Test
    public void testOddEvenNumberSplitEmpty() {
        //FleaFrameManager.getManager().setLocale(Locale.US);
        ITableSplit tableSplit = TableSplitEnum.ODD_EVEN_N.newInstance();
        try {
            LOGGER.debug(tableSplit.convert("123123120"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testOddEvenLowercaseSplitEmpty() {
        //FleaFrameManager.getManager().setLocale(Locale.US);
        ITableSplit tableSplit = TableSplitEnum.ODD_EVEN_L.newInstance();
        try {
            LOGGER.debug(tableSplit.convert("123123121"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testOddEvenUppercaseSplitEmpty() {
        //FleaFrameManager.getManager().setLocale(Locale.US);
        ITableSplit tableSplit = TableSplitEnum.ODD_EVEN_U.newInstance();
        try {
            LOGGER.debug(tableSplit.convert("123123122"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testTwoLowercaseTableSplitEmpty() {
        //FleaFrameManager.getManager().setLocale(Locale.US);
        ITableSplit tableSplit = TableSplitEnum.TWO.newInstance();
        try {
            LOGGER.debug(tableSplit.convert(null));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testTwoLowercaseTableSplit() {
        ITableSplit tableSplit = TableSplitEnum.TWO.newInstance();
        try {
            LOGGER.debug(tableSplit.convert("DF312311FF"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testTwoBeforeLowercaseTableSplit() {
        ITableSplit tableSplit = TableSplitEnum.TWO_B.newInstance();
        try {
            LOGGER.debug(tableSplit.convert("DF312311FF"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testTwoUppercaseTableSplit() {
        ITableSplit tableSplit = TableSplitEnum.TWO_U.newInstance();
        try {
            LOGGER.debug(tableSplit.convert("df312311ff"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testTwoBeforeUppercaseTableSplit() {
        ITableSplit tableSplit = TableSplitEnum.TWO_BU.newInstance();
        try {
            LOGGER.debug(tableSplit.convert("df312311ff"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testOneLowercaseTableSplit() {
        ITableSplit tableSplit = TableSplitEnum.ONE.newInstance();
        try {
            LOGGER.debug(tableSplit.convert("DF312311FF"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testOneBeforeLowercaseTableSplit() {
        ITableSplit tableSplit = TableSplitEnum.ONE_B.newInstance();
        try {
            LOGGER.debug(tableSplit.convert("DF312311FF"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testOneUppercaseTableSplit() {
        ITableSplit tableSplit = TableSplitEnum.ONE_U.newInstance();
        try {
            LOGGER.debug(tableSplit.convert("df312311ff"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testOneBeforeUppercaseTableSplit() {
        ITableSplit tableSplit = TableSplitEnum.ONE_BU.newInstance();
        try {
            LOGGER.debug(tableSplit.convert("df312311ff"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testTableSplitYYYY() {

        ITableSplit tableSplit = TableSplitEnum.YYYY.newInstance();
        try {
            LOGGER.debug(tableSplit.convert("123"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testTableSplitYYYYMM() {

        ITableSplit tableSplit = TableSplitEnum.YYYY_MM.newInstance();
        try {
            LOGGER.debug(tableSplit.convert(null));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testTableSplitYYYYMMDD() {

        ITableSplit tableSplit = TableSplitEnum.YYYY_MM_DD.newInstance();
        try {
            LOGGER.debug(tableSplit.convert(""));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testTableSplitConfig() {
        LOGGER.debug("Flea Table Split = {}", TableSplitConfig.getConfig().getFleaTableSplit());
    }

    @Test
    public void testTableSplitEnum() {
        TableSplitEnum tableSplitEnum = (TableSplitEnum) EntityUtils.getEntity(TableSplitEnum.values(), "key", "TWO");
        Assert.assertNotNull(tableSplitEnum);
    }
}
