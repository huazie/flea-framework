package com.huazie.frame.db.common.sql.template.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *  <p>
 *  定义删除语句模板集合
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月26日
 *
 */
public class Delete {
	private List<Template> deletes = new ArrayList<Template>();

	public List<Template> getDeletes() {
		return deletes;
	}
	
	public Template[] toDeletesArray(){
		return deletes.toArray(new Template[0]);
	}
	
	/**
	 * <p>
	 * 获取删除模板的Map集合，便于根据id查找
	 * 
	 * @date 2018年1月26日
	 *
	 * @return
	 */
	public Map<String, Template> toDeletesMap(){
		Map<String, Template> deletesMap = new HashMap<String, Template>();
		Iterator<Template> deletesIt = deletes.iterator();
		while(deletesIt.hasNext()){
			Template template = deletesIt.next();
			deletesMap.put(template.getId(), template);
		}
		return deletesMap;
	}
	
	public void addTemplate(Template template){
		deletes.add(template);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
