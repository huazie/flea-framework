package com.huazie.frame.common.pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @Description 物品数据
 * 
 * @author huazie
 * @version v1.0.0
 * @date 2017年4月1日
 *
 */
public class FleaData implements Serializable{
	
	private static final long serialVersionUID = 5313604041435638870L;

	private int count;						//总数
	
	private List<Map<String, Object>> data;	//数据集（如果多的话，可能是某一页的数据）

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Map<String, Object>> getData() {
		return data;
	}

	public void setData(List<Map<String, Object>> data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
