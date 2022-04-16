package com.huazie.fleaframework.db.common.table;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.db.common.table.split.ITableSplit;
import com.huazie.fleaframework.db.common.table.split.TableSplitEnum;
import com.huazie.fleaframework.db.common.table.split.config.TableSplitConfig;
import com.huazie.fleaframework.db.common.table.split.impl.OddEvenLowercaseTableSplitImpl;
import com.huazie.fleaframework.db.common.table.split.impl.OddEvenNumberTableSplitImpl;
import com.huazie.fleaframework.db.common.table.split.impl.OddEvenUppercaseTableSplitImpl;
import com.huazie.fleaframework.db.common.table.split.impl.OneBeforeLowercaseTableSplitImpl;
import com.huazie.fleaframework.db.common.table.split.impl.OneBeforeUppercaseTableSplitImpl;
import com.huazie.fleaframework.db.common.table.split.impl.OneLowercaseTableSplitImpl;
import com.huazie.fleaframework.db.common.table.split.impl.OneUppercaseTableSplitImpl;
import com.huazie.fleaframework.db.common.table.split.impl.TwoBeforeLowercaseTableSplitImpl;
import com.huazie.fleaframework.db.common.table.split.impl.TwoBeforeUppercaseTableSplitImpl;
import com.huazie.fleaframework.db.common.table.split.impl.TwoLowercaseTableSplitImpl;
import com.huazie.fleaframework.db.common.table.split.impl.TwoUppercaseTableSplitImpl;
import com.huazie.fleaframework.db.common.table.split.impl.YYYYMMDDTableSplitImpl;
import com.huazie.fleaframework.db.common.table.split.impl.YYYYMMTableSplitImpl;
import com.huazie.fleaframework.db.common.table.split.impl.YYYYTableSplitImpl;
import com.huazie.fleaframework.db.common.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class TableSplitTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(TableSplitTest.class);

    @Test
    public void testOddEvenNumberSplitEmpty() {
        //FleaFrameManager.getManager().setLocale(Locale.US);
        ITableSplit tableSplit = new OddEvenNumberTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("123123120"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testOddEvenLowercaseSplitEmpty() {
        //FleaFrameManager.getManager().setLocale(Locale.US);
        ITableSplit tableSplit = new OddEvenLowercaseTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("123123121"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testOddEvenUppercaseSplitEmpty() {
        //FleaFrameManager.getManager().setLocale(Locale.US);
        ITableSplit tableSplit = new OddEvenUppercaseTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("123123122"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testTwoLowercaseTableSplitEmpty() {
        //FleaFrameManager.getManager().setLocale(Locale.US);
        ITableSplit tableSplit = new TwoLowercaseTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert(null));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testTwoLowercaseTableSplit() {
        ITableSplit tableSplit = new TwoLowercaseTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("DF312311FF"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testTwoBeforeLowercaseTableSplit() {
        ITableSplit tableSplit = new TwoBeforeLowercaseTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("DF312311FF"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testTwoUppercaseTableSplit() {
        ITableSplit tableSplit = new TwoUppercaseTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("df312311ff"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testTwoBeforeUppercaseTableSplit() {
        ITableSplit tableSplit = new TwoBeforeUppercaseTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("df312311ff"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testOneLowercaseTableSplit() {
        ITableSplit tableSplit = new OneLowercaseTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("DF312311FF"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testOneBeforeLowercaseTableSplit() {
        ITableSplit tableSplit = new OneBeforeLowercaseTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("DF312311FF"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testOneUppercaseTableSplit() {
        ITableSplit tableSplit = new OneUppercaseTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("df312311ff"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testOneBeforeUppercaseTableSplit() {
        ITableSplit tableSplit = new OneBeforeUppercaseTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("df312311ff"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testTableSplitYYYY() {

        ITableSplit tableSplit = new YYYYTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("123"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testTableSplitYYYYMM() {

        ITableSplit tableSplit = new YYYYMMTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert(null));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testTableSplitYYYYMMDD() {

        ITableSplit tableSplit = new YYYYMMDDTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert(""));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testTableSplitConfig() {
        LOGGER.debug("tables = {}", TableSplitConfig.getConfig().getFleaTableSplit());
    }

    @Test
    public void testTableSplitEnum() {
        TableSplitEnum tableSplitEnum = (TableSplitEnum) EntityUtils.getEntity(TableSplitEnum.values(), "key", "TWO");
        Assert.assertNotNull(tableSplitEnum);
    }
}
