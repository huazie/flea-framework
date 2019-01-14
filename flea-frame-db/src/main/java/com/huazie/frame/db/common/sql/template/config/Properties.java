package com.huazie.frame.db.common.sql.template.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *  
 *  <p>
 *   属性集合
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年3月23日
 *
 */
public class Properties {
	
	private List<Property> properties = new ArrayList<Property>();

	public List<Property> getProperties() {
		return properties;
	}

	public Property[] toPropertiesArray(){
		return properties.toArray(new Property[0]);
	}
	
	public void addProperty(Property property){
		properties.add(property);
	}
	
	/**
	 * <p>
	 * 获取模板属性的集合
	 * 
	 * @date 2018年1月28日
	 *
	 * @return
	 */
	public Map<String, Property> toPropMap(){
		Map<String, Property> propMap = new HashMap<String, Property>();
		Iterator<Property> propIt = this.properties.iterator();
		while(propIt.hasNext()){
			Property prop = propIt.next();
			propMap.put(prop.getKey(), prop);
		}
		return propMap;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
