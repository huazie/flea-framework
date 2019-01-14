package com.huazie.frame.db.common.tab;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huazie.frame.db.common.tab.split.ITableSplit;
import com.huazie.frame.db.common.tab.split.TableSplitEnum;
import com.huazie.frame.db.common.tab.split.config.TableSplitConfig;
import com.huazie.frame.db.common.tab.split.impl.TwoHexTableSplitImpl;
import com.huazie.frame.db.common.tab.split.impl.YYYYMMDDTableSplitImpl;
import com.huazie.frame.db.common.tab.split.impl.YYYYMMTableSplitImpl;
import com.huazie.frame.db.common.tab.split.impl.YYYYTableSplitImpl;
import com.huazie.frame.db.common.util.EntityUtils;

/**
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月25日
 *
 */
public class TableSplitTest {

	private final static Logger LOOGER = LoggerFactory.getLogger(TableSplitTest.class);
	
	@Test
	public void testTableSplit(){
		
		ITableSplit tableSplit = new TwoHexTableSplitImpl();
		try {
			TableSplitTest.LOOGER.debug(tableSplit.convert("flea_file_info", "12312311FF"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTableSplit1(){
		
		ITableSplit tableSplit = new YYYYTableSplitImpl();
		try {
			TableSplitTest.LOOGER.debug(tableSplit.convert("flea_file_info", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTableSplit2(){
		
		ITableSplit tableSplit = new YYYYMMTableSplitImpl();
		try {
			TableSplitTest.LOOGER.debug(tableSplit.convert("flea_file_info", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTableSplit3(){
		
		ITableSplit tableSplit = new YYYYMMDDTableSplitImpl();
		try {
			TableSplitTest.LOOGER.debug(tableSplit.convert("flea_file_info", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTableSplitConfig(){
		TableSplitTest.LOOGER.debug(TableSplitConfig.getConfig().getTables().toString());
	}
	
	@Test
	public void testEntityUtils(){
		try {
			TableSplitTest.LOOGER.debug(EntityUtils.getEntity(TableSplitEnum.values(), "key", "twohex").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
