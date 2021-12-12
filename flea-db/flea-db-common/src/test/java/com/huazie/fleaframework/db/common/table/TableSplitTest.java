package com.huazie.fleaframework.db.common.table;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.db.common.table.split.ITableSplit;
import com.huazie.fleaframework.db.common.table.split.TableSplitEnum;
import com.huazie.fleaframework.db.common.table.split.config.TableSplitConfig;
import com.huazie.fleaframework.db.common.table.split.impl.OddEvenAlphabetTableSplitImpl;
import com.huazie.fleaframework.db.common.table.split.impl.OddEvenNumberTableSplitImpl;
import com.huazie.fleaframework.db.common.table.split.impl.OddEvenUpperAlphabetTableSplitImpl;
import com.huazie.fleaframework.db.common.table.split.impl.OneBeforeTableSplitImpl;
import com.huazie.fleaframework.db.common.table.split.impl.OneBeforeUpperTableSplitImpl;
import com.huazie.fleaframework.db.common.table.split.impl.OneTableSplitImpl;
import com.huazie.fleaframework.db.common.table.split.impl.OneUpperTableSplitImpl;
import com.huazie.fleaframework.db.common.table.split.impl.TwoBeforeTableSplitImpl;
import com.huazie.fleaframework.db.common.table.split.impl.TwoBeforeUpperTableSplitImpl;
import com.huazie.fleaframework.db.common.table.split.impl.TwoTableSplitImpl;
import com.huazie.fleaframework.db.common.table.split.impl.TwoUpperTableSplitImpl;
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
    public void testOddEvenAlphabetSplitEmpty() {
        //FleaFrameManager.getManager().setLocale(Locale.US);
        ITableSplit tableSplit = new OddEvenAlphabetTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("123123121"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testOddEvenUpperAlphabetSplitEmpty() {
        //FleaFrameManager.getManager().setLocale(Locale.US);
        ITableSplit tableSplit = new OddEvenUpperAlphabetTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("123123122"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testTwoTableSplitEmpty() {
        //FleaFrameManager.getManager().setLocale(Locale.US);
        ITableSplit tableSplit = new TwoTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert(null));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testTwoTableSplit() {
        ITableSplit tableSplit = new TwoTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("DF312311FF"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testTwoBeforeTableSplit() {
        ITableSplit tableSplit = new TwoBeforeTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("DF312311FF"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testTwoUpperTableSplit() {
        ITableSplit tableSplit = new TwoUpperTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("df312311ff"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testTwoBeforeUpperTableSplit() {
        ITableSplit tableSplit = new TwoBeforeUpperTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("df312311ff"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testOneTableSplit() {
        ITableSplit tableSplit = new OneTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("DF312311FF"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testOneBeforeTableSplit() {
        ITableSplit tableSplit = new OneBeforeTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("DF312311FF"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testOneUpperTableSplit() {
        ITableSplit tableSplit = new OneUpperTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("df312311ff"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testOneBeforeUpperTableSplit() {
        ITableSplit tableSplit = new OneBeforeUpperTableSplitImpl();
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
        LOGGER.debug("tables = {}", TableSplitConfig.getConfig().getTables());
    }

    @Test
    public void testEntityUtils() {
        TableSplitEnum tableSplitEnum = (TableSplitEnum) EntityUtils.getEntity(TableSplitEnum.values(), "key", "twohex1");
        Assert.assertNull(tableSplitEnum);
    }
}
