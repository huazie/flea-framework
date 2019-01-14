package com.huazie.frame.db.common.sql.template.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *  <p>
 *  定义查询语句模板集合
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月26日
 *
 */
public class Select {
	
	private List<Template> selects = new ArrayList<Template>();

	public List<Template> getSelects() {
		return selects;
	}
	
	public Template[] toSelectsArray(){
		return selects.toArray(new Template[0]);
	}
	
	/**
	 * <p>
	 * 获取删除模板的Map集合，便于根据id查找
	 * 
	 * @date 2018年1月26日
	 *
	 * @return
	 */
	public Map<String, Template> toSelectsMap(){
		Map<String, Template> selectsMap = new HashMap<String, Template>();
		Iterator<Template> selectsIt = selects.iterator();
		while(selectsIt.hasNext()){
			Template template = selectsIt.next();
			selectsMap.put(template.getId(), template);
		}
		return selectsMap;
	}
	
	public void addTemplate(Template template){
		selects.add(template);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
