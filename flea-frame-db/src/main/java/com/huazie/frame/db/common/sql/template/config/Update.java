package com.huazie.frame.db.common.sql.template.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *  <p>
 *  定义更新语句模板集合
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月26日
 *
 */
public class Update {
	
	private List<Template> updates = new ArrayList<Template>();

	public List<Template> getUpdates() {
		return updates;
	}
	
	public Template[] toUpdatesArray(){
		return updates.toArray(new Template[0]);
	}
	
	/**
	 * <p>
	 * 获取更新模板的Map集合，便于根据id查找
	 * 
	 * @date 2018年1月26日
	 *
	 * @return
	 */
	public Map<String, Template> toUpdatesMap(){
		Map<String, Template> updatesMap = new HashMap<String, Template>();
		Iterator<Template> updatesIt = updates.iterator();
		while(updatesIt.hasNext()){
			Template template = updatesIt.next();
			updatesMap.put(template.getId(), template);
		}
		return updatesMap;
	}
	
	public void addTemplate(Template template){
		updates.add(template);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
