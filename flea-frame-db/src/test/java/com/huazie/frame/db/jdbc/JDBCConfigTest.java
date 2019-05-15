package com.huazie.frame.db.jdbc;

import com.huazie.frame.common.FleaFrameManager;
import com.huazie.frame.db.common.DBSystemEnum;
import com.huazie.frame.db.jdbc.config.FleaJDBCConfig;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author huazie
 * @version 1.0.0
 * @date 2018年1月23日
 */
public class JDBCConfigTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(JDBCConfigTest.class);

    @Test
    public void testDBPrefix() {
        FleaJDBCConfig.init(DBSystemEnum.MySQL.getName(), "fleamanagement");
        LOGGER.debug(FleaFrameManager.getManager().getDBPrefix());
    }

    @Test
    public void testJDBCQuery1() {
        FleaJDBCConfig.init(DBSystemEnum.MySQL.getName(), "fleamarket");
        try {
            List<Map<String, Object>> results = FleaJDBCHelper.query("SELECT * FROM flea_user");
            Assert.assertNotNull(results);
            LOGGER.debug(results.toString());
        } catch (Exception e) {
            LOGGER.error("Exception={}", e);
        }
    }

    @Test
    public void testJDBCQuery2() {
        FleaJDBCConfig.init(DBSystemEnum.MySQL.getName(), "fleamarket");
        try {
            Object[] params = {new Long(1L)};
            List<Map<String, Object>> results = FleaJDBCHelper.query("SELECT * FROM flea_user where user_id = ?", params);
            Assert.assertNotNull(results);
            LOGGER.debug(results.toString());
        } catch (Exception e) {
            LOGGER.error("Exception={}", e);
        }
    }

    @Test
    public void testJDBCQuery3() {
        FleaJDBCConfig.init(DBSystemEnum.MySQL.getName(), "fleamarket");
        try {
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(new Long(1000000012L));
            List<Map<String, Object>> results = FleaJDBCHelper.query("SELECT * FROM flea_user where user_id = ?", paramList);
            Assert.assertNotNull(results);
            LOGGER.debug(results.toString());
        } catch (Exception e) {
            LOGGER.error("Exception={}", e);
        }
    }

    @Test
    public void testJDBCSingleQuery1() {
        FleaJDBCConfig.init(DBSystemEnum.MySQL.getName(), "fleamarket");
        try {
            List<Map<String, Object>> results = FleaJDBCHelper.query("SELECT user_name FROM flea_user");
            Assert.assertNotNull(results);
            LOGGER.debug(results.toString());
        } catch (Exception e) {
            LOGGER.error("Exception={}", e);
        }
    }

    @Test
    public void testJDBCSingleQuery2() {
        FleaJDBCConfig.init(DBSystemEnum.MySQL.getName(), "fleamarket");
        try {
            Object[] params = {new Long(1L)};
            List<Map<String, Object>> results = FleaJDBCHelper.query("SELECT user_name FROM flea_user where user_id = ?", params);
            Assert.assertNotNull(results);
            LOGGER.debug(results.toString());
        } catch (Exception e) {
            LOGGER.error("Exception={}", e);
        }
    }

    @Test
    public void testJDBCSingleQuery3() {
        FleaJDBCConfig.init(DBSystemEnum.MySQL.getName(), "fleamarket");
        try {
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(new Long(1000000012L));
            paramList.add(new Long(1L));
            List<Map<String, Object>> results = FleaJDBCHelper.query("SELECT user_name FROM flea_user where user_id in (?, ?)", paramList);
            Assert.assertNotNull(results);
            LOGGER.debug(results.toString());
        } catch (Exception e) {
            LOGGER.error("Exception={}", e);
        }
    }

}
