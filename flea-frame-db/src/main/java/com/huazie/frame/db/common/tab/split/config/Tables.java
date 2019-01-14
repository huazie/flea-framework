package com.huazie.frame.db.common.tab.split.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p>
 * 分表集合类
 * 
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月25日
 *
 */
public class Tables implements Serializable {

	private static final long serialVersionUID = 3209329482179720497L;

	private List<Table> tableList = new ArrayList<Table>();

	public List<Table> getTableList() {
		return tableList;
	}
	
	public Table[] getTableArray(){
		return this.tableList.toArray(new Table[0]);
	}
	
	/**
	 * <p>
	 * 获取分表定义相关属性集合
	 * 
	 * @date 2018年1月29日
	 *
	 * @return
	 */
	public Map<String, Table> toTableMap(){
		Map<String, Table> tableMap = new HashMap<String, Table>();
		Iterator<Table> tableIt = this.tableList.iterator();
		while(tableIt.hasNext()){
			Table table = tableIt.next();
			tableMap.put(table.getName(), table);
		}
		return tableMap;
	}
	
	public void addTable(Table table){
		this.tableList.add(table);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
