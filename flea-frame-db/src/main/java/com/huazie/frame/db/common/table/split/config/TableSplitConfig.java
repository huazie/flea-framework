package com.huazie.frame.db.common.table.split.config;

import com.huazie.frame.db.common.XmlDigesterHelper;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

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

	private TableSplitConfig(Tables tables){
		this.tables = tables;
	}

	public static TableSplitConfig getConfig(){
		if(config == null){
			try {
				config = new TableSplitConfig(XmlDigesterHelper.getInstance().getTables());
			} catch (Exception e) {
				LOGGER.debug("Fail to init flea-table-split.xml");
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
		Map<String, Table> tableMap = tables.toTableMap();
		Table table = tableMap.get(name);
		return table;
	}
	
	public Tables getTables() {
		return tables;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
