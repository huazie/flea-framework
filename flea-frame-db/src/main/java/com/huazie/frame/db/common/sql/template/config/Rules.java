package com.huazie.frame.db.common.sql.template.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年3月23日
 *
 */
public class Rules {
	
	private List<Rule> rules = new ArrayList<Rule>();
	
	public List<Rule> getRules() {
		return rules;
	}
	
	public Rule[] toRulesArray(){
		return rules.toArray(new Rule[0]);
	}
	
	/**
	 * <p>
	 * 获取校验规则的Map集合，便于根据id查找
	 * 
	 * @date 2018年1月26日
	 *
	 * @return
	 */
	public Map<String, Rule> toRulesMap(){
		Map<String, Rule> rulesMap = new HashMap<String, Rule>();
		Iterator<Rule> rulesIt = rules.iterator();
		while(rulesIt.hasNext()){
			Rule rule = rulesIt.next();
			rulesMap.put(rule.getId(), rule);
		}
		return rulesMap;
	}
	
	public void addRule(Rule rule){
		rules.add(rule);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
