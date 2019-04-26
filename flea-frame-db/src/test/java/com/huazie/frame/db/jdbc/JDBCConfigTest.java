package com.huazie.frame.db.jdbc;

import com.huazie.frame.common.FleaFrameManager;
import com.huazie.frame.db.common.DBEnum;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月23日
 *
 */
public class JDBCConfigTest {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(JDBCConfigTest.class);

	@Test
	public void testDBPrefix(){
		FleaJDBCConfig.init(DBEnum.MySQL.getName(), "fleamanagement");
		LOGGER.debug(FleaFrameManager.getManager().getDBPrefix());
	}
	
	@Test
	public void testJDBCQuery(){
		FleaJDBCConfig.init(DBEnum.MySQL.getName(), "fleamarket");
		try {
			List<Map<String, Object>> results = FleaJDBCUtils.query("SELECT * FROM flea_user");
			Assert.assertNotNull(results);
			LOGGER.debug(results.toString());
		} catch (SQLException e) {
			LOGGER.error("Exception={}",e);
		}
	}
}
