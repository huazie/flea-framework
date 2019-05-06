package com.huazie.frame.db.common.table;

import com.huazie.frame.db.common.table.split.impl.ThreeHexTableSplitImpl;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huazie.frame.db.common.table.split.ITableSplit;
import com.huazie.frame.db.common.table.split.TableSplitEnum;
import com.huazie.frame.db.common.table.split.config.TableSplitConfig;
import com.huazie.frame.db.common.table.split.impl.TwoHexTableSplitImpl;
import com.huazie.frame.db.common.table.split.impl.YYYYMMDDTableSplitImpl;
import com.huazie.frame.db.common.table.split.impl.YYYYMMTableSplitImpl;
import com.huazie.frame.db.common.table.split.impl.YYYYTableSplitImpl;
import com.huazie.frame.db.common.util.EntityUtils;

/**
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月25日
 */
public class TableSplitTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(TableSplitTest.class);

    @Test
    public void testTwoHexTableSplit() {

        ITableSplit tableSplit = new TwoHexTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("flea_file_info", "12312311FF"));
        } catch (Exception e) {
            LOGGER.error("Exception={}", e);
        }
    }

    @Test
    public void testThreeHexTableSplit() {
        ITableSplit tableSplit = new ThreeHexTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("flea_file_info", "12312311FF"));
        } catch (Exception e) {
            LOGGER.error("Exception={}", e);
        }
    }

    @Test
    public void testTableSplitYYYY() {

        ITableSplit tableSplit = new YYYYTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("flea_file_info", ""));
        } catch (Exception e) {
            LOGGER.error("Exception={}", e);
        }
    }

    @Test
    public void testTableSplitYYYYMM() {

        ITableSplit tableSplit = new YYYYMMTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("flea_file_info", ""));
        } catch (Exception e) {
            LOGGER.error("Exception={}", e);
        }
    }

    @Test
    public void testTableSplitYYYYMMDD() {

        ITableSplit tableSplit = new YYYYMMDDTableSplitImpl();
        try {
            LOGGER.debug(tableSplit.convert("flea_file_info", ""));
        } catch (Exception e) {
            LOGGER.error("Exception={}", e);
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
            LOGGER.error("Exception={}", e);
        }
    }
}
