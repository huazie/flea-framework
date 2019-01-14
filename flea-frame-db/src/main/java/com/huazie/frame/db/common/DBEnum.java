package com.huazie.frame.db.common;

/**
 *  <p>
 *  	数据库枚举
 *  </p>
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月23日
 *
 */
public enum DBEnum {
	/**
	 *  一个关系型数据库管理系统（由瑞典MySQL AB公司开发，目前属于 Oracle 旗下产品）
	 */
	MySQL("MySQL", "一个关系型数据库管理系统（由瑞典MySQL AB公司开发，目前属于 Oracle 旗下产品）"),
	/**
	 *  一款关系数据库管理系统（由美国ORACLE公司（甲骨文）提供的以分布式数据库为核心的一组软件产品）
	 */
	Oracle("Oracle", "一款关系数据库管理系统（由美国ORACLE公司（甲骨文）提供的以分布式数据库为核心的一组软件产品）");
	
	private String name;	//数据库名称
	private String desc;	//数据库描述
	
	private DBEnum(String name, String desc){
		this.name = name;
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}
}
