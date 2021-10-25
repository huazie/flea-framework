package com.huazie.frame.db.common.table;

import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.db.common.table.split.ITableSplit;
import com.huazie.frame.db.common.table.split.TableSplitEnum;
import com.huazie.frame.db.common.table.split.config.TableSplitConfig;
import com.huazie.frame.db.common.table.split.impl.TwoBeforeTableSplitImpl;
import com.huazie.frame.db.common.table.split.impl.TwoBeforeUpperTableSplitImpl;
import com.huazie.frame.db.common.table.split.impl.TwoTableSplitImpl;
import com.huazie.frame.db.common.table.split.impl.TwoUpperTableSplitImpl;
import com.huazie.frame.db.common.table.split.impl.YYYYMMDDTableSplitImpl;
import com.huazie.frame.db.common.table.split.impl.YYYYMMTableSplitImpl;
import com.huazie.frame.db.common.table.split.impl.YYYYTableSplitImpl;
import com.huazie.frame.db.common.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月25日
 */
public class TableSplitTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(TableSplitTest.class);

    @Test
    public void testTwoHexTableSplitEmpty() {
        //FleaFrameManager.getManager().setLocale(Locale.US);
        ITableSplit tableSplit = new TwoTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert(null));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testTwoHexTableSplit() {
        ITableSplit tableSplit = new TwoTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("DF312311FF"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testTwoHexBeforeTableSplit() {
        ITableSplit tableSplit = new TwoBeforeTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("DF312311FF"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testTwoHexUpperTableSplit() {
        ITableSplit tableSplit = new TwoUpperTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("df312311ff"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testTwoHexBeforeUpperTableSplit() {
        ITableSplit tableSplit = new TwoBeforeUpperTableSplitImpl();
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
