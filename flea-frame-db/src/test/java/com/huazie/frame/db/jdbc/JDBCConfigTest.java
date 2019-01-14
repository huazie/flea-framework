package com.huazie.frame.db.jdbc;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.huazie.frame.db.common.DBEnum;
import com.huazie.frame.db.jdbc.JDBCConfig;
import com.huazie.frame.db.jdbc.JDBCUtils;

/**
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月23日
 *
 */
public class JDBCConfigTest {
	
	private final static Logger LOGGER = Logger.getLogger(JDBCConfigTest.class);

	@Test
	public void testConfig(){
		JDBCConfig.init(DBEnum.MySQL.getName(), "fleamanagement");
		JDBCConfigTest.LOGGER.debug(JDBCConfig.getConfig());
	}
	
	@Test
	public void testJDBCQuery(){
		JDBCConfig.init(DBEnum.MySQL.getName(), "fleamarket");
		try {
			JDBCConfigTest.LOGGER.debug(JDBCUtils.query("SELECT * FROM flea_user"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
