package com.huazie.frame.db.common.tab.split.config;

import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huazie.frame.db.common.XmlDigesterHelper;

/**
 *  <p>
 *  	分表配置类
 *  </p>
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月26日
 *
 */
public class TableSplitConfig {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TableSplitConfig.class);
	
	private static TableSplitConfig config;
	
	private Tables tables;	//分表集合类
	
	public static TableSplitConfig getConfig(){
		if(config == null){
			try {
				config = new TableSplitConfig(XmlDigesterHelper.getInstance().getTables());
			} catch (Exception e) {
				TableSplitConfig.LOGGER.debug("Fail to init flea-table-split.xml");
			}
		}
		return config;
	}
	
	/**
	 * <p>
	 * 根据name获取指定的分表定义信息
	 * 
	 * @date 2018年1月29日
	 *
	 * @param name
	 *            主表名
	 * @return
	 */
	public Table getTable(String name){
		Table table = null;
		Map<String, Table> tableMap = this.tables.toTableMap();
		table = tableMap.get(name);
		return table;
	}
	
	private TableSplitConfig(Tables tables){
		this.tables = tables;
	}

	public Tables getTables() {
		return tables;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
