package com.huazie.frame.db.common.sql.template.config;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huazie.frame.db.common.XmlDigesterHelper;

/**
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月26日
 *
 */
public class SqlTemplateConfig {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(SqlTemplateConfig.class);
	
	private static SqlTemplateConfig config = null;
	
	private Sql sql;
	
	public static SqlTemplateConfig getConfig(){
		if(config == null){
			try {
				config = new SqlTemplateConfig(XmlDigesterHelper.getInstance().getSqlTemplate());
			} catch (Exception e) {
				SqlTemplateConfig.LOGGER.error("Fail to init flea-sql-template.xml");
			}
		}
		return config;
	}
	
	private SqlTemplateConfig(Sql sql){
		this.sql = sql;
	}
	
	/**
	 *  <p>
	 * 根据校验规则编号，获取指定校验规则
	 * 
	 * @date 2018年3月27日
	 *
	 * @param ruleId
	 * @return
	 */
	public Rule getRule(String ruleId){
		Rule rule = null;
		Map<String, Rule> rulesMap = null;
		if(this.sql != null){
			Rules rules = this.sql.getRules();
			if(rules != null){
				rulesMap = rules.toRulesMap();
				if(rulesMap != null && !rulesMap.isEmpty()){
					rule = rulesMap.get(ruleId);
				}
			}
		}
		return rule;
	}
	
	/**
	 * <p>
	 * 根据插入模板编号，获取指定插入模板
	 * 
	 * @date 2018年1月26日
	 *
	 * @param id
	 * @return
	 */
	public Template getInsertTemplate(String id){
		Template template = null;
		Map<String, Template> insertsMap = null;
		if(this.sql != null){
			Insert insert = this.sql.getInsert();
			if(insert != null){
				insertsMap = insert.toInsertsMap();
				if(insertsMap != null && !insertsMap.isEmpty()){
					template = insertsMap.get(id);
				}
			}
		}
		return template;
	}
	
	/**
	 * <p>
	 * 根据更新模板编号，获取指定更新模板
	 * 
	 * @date 2018年1月26日
	 *
	 * @param id
	 * @return
	 */
	public Template getUpdateTemplate(String id){
		Template template = null;
		Map<String, Template> updatesMap = null;
		if(this.sql != null){
			Update update = this.sql.getUpdate();
			if(update != null){
				updatesMap = update.toUpdatesMap();
				if(updatesMap != null && !updatesMap.isEmpty()){
					template = updatesMap.get(id);
				}
			}
		}
		return template;
	}
	
	/**
	 * <p>
	 * 根据查询模板编号，获取指定查询模板
	 * 
	 * @date 2018年1月26日
	 *
	 * @param id
	 * @return
	 */
	public Template getSelectTemplate(String id){
		Template template = null;
		Map<String, Template> selectsMap = null;
		if(this.sql != null){
			Select select = this.sql.getSelect();
			if(select != null){
				selectsMap = select.toSelectsMap();
				if(selectsMap != null && !selectsMap.isEmpty()){
					template = selectsMap.get(id);
				}
			}
		}
		return template;
	}
	
	/**
	 * <p>
	 * 根据查询模板编号，获取指定查询模板
	 * 
	 * @date 2018年1月26日
	 *
	 * @param id
	 * @return
	 */
	public Template getDeleteTemplate(String id){
		Template template = null;
		Map<String, Template> deletesMap = null;
		if(this.sql != null){
			Delete delete = this.sql.getDelete();
			if(delete != null){
				deletesMap = delete.toDeletesMap();
				if(deletesMap != null && !deletesMap.isEmpty()){
					template = deletesMap.get(id);
				}
			}
		}
		return template;
	}

	public Sql getSql() {
		return sql;
	}

	public void setSql(Sql sql) {
		this.sql = sql;
	}
	
}
