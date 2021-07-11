package com.huazie.frame.db.common.table;

import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.db.common.table.split.ITableSplit;
import com.huazie.frame.db.common.table.split.TableSplitEnum;
import com.huazie.frame.db.common.table.split.config.TableSplitConfig;
import com.huazie.frame.db.common.table.split.impl.TwoHexBeforeTableSplitImpl;
import com.huazie.frame.db.common.table.split.impl.TwoHexBeforeUpperTableSplitImpl;
import com.huazie.frame.db.common.table.split.impl.TwoHexTableSplitImpl;
import com.huazie.frame.db.common.table.split.impl.TwoHexUpperTableSplitImpl;
import com.huazie.frame.db.common.table.split.impl.YYYYMMDDTableSplitImpl;
import com.huazie.frame.db.common.table.split.impl.YYYYMMTableSplitImpl;
import com.huazie.frame.db.common.table.split.impl.YYYYTableSplitImpl;
import com.huazie.frame.db.common.util.EntityUtils;
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
        ITableSplit tableSplit = new TwoHexTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert(null));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testTwoHexTableSplit() {
        ITableSplit tableSplit = new TwoHexTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("DF312311FF"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testTwoHexBeforeTableSplit() {
        ITableSplit tableSplit = new TwoHexBeforeTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("DF312311FF"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testTwoHexUpperTableSplit() {
        ITableSplit tableSplit = new TwoHexUpperTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("df312311ff"));
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }

    @Test
    public void testTwoHexBeforeUpperTableSplit() {
        ITableSplit tableSplit = new TwoHexBeforeUpperTableSplitImpl();
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
        LOGGER.debug(TableSplitConfig.getConfig().getTables().toString());
    }

    @Test
    public void testEntityUtils() {
        try {
            LOGGER.debug(EntityUtils.getEntity(TableSplitEnum.values(), "key", "twohex").toString());
        } catch (Exception e) {
            LOGGER.error("Exception=", e);
        }
    }
}
