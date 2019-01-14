package com.huazie.frame.common;

/**
 * <p>
 * 		拼音相关枚举
 * </p>
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月24日
 *
 */
public enum PinyinEnum {
	/**
	 * 大写
	 */
	UPPER_CASE(0, "大写"),
	/**
	 * 小写
	 */
	LOWER_CASE(1, "小写"),
	/**
	 * 简拼
	 */
	JIANPIN(0, "简拼"),
	/**
	 * 全拼
	 */
	QUANPIN(1, "全拼");
	
	private int type;
	
	private String desc;
	
	private PinyinEnum(int type, String desc){
		this.type = type;
		this.desc = desc;
	}

	public int getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}

}
