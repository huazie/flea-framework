package com.huazie.frame.db.common.sql.template.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *  <p>
 *  定义插入语句模板集合
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月26日
 *
 */
public class Insert {
	
	private List<Template> inserts = new ArrayList<Template>();

	public List<Template> getInserts() {
		return inserts;
	}
	
	public Template[] toInsertsArray(){
		return inserts.toArray(new Template[0]);
	}
	
	/**
	 * <p>
	 * 获取插入模板的Map集合，便于根据id查找
	 * 
	 * @date 2018年1月26日
	 *
	 * @return
	 */
	public Map<String, Template> toInsertsMap(){
		Map<String, Template> insertsMap = new HashMap<String, Template>();
		Iterator<Template> insertsIt = inserts.iterator();
		while(insertsIt.hasNext()){
			Template template = insertsIt.next();
			insertsMap.put(template.getId(), template);
		}
		return insertsMap;
	}
	
	public void addTemplate(Template template){
		inserts.add(template);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
