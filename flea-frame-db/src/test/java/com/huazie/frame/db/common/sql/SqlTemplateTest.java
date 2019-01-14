package com.huazie.frame.db.common.sql;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huazie.frame.db.common.sql.template.SqlTemplateEnum;
import com.huazie.frame.db.common.sql.template.config.Property;
import com.huazie.frame.db.common.sql.template.config.Rule;
import com.huazie.frame.db.common.sql.template.config.SqlTemplateConfig;

/**
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月26日
 *
 */
public class SqlTemplateTest {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(SqlTemplateTest.class);

	@Test
	public void testSqlTemplate(){
		SqlTemplateTest.LOGGER.debug(SqlTemplateConfig.getConfig().getSql().toString());
	}
	
	@Test
	public void testRules(){
		SqlTemplateTest.LOGGER.debug(SqlTemplateConfig.getConfig().getRule("insert").toString());
		SqlTemplateTest.LOGGER.debug(SqlTemplateConfig.getConfig().getRule("update").toString());
		SqlTemplateTest.LOGGER.debug(SqlTemplateConfig.getConfig().getRule("delete").toString());
		SqlTemplateTest.LOGGER.debug(SqlTemplateConfig.getConfig().getRule("select").toString());
	}
	
	@Test
	public void testInsertSqlTemplate(){
		SqlTemplateTest.LOGGER.debug(SqlTemplateConfig.getConfig().getInsertTemplate("insert").toString());
	}
	
	@Test
	public void testUpdateSqlTemplate(){
		SqlTemplateTest.LOGGER.debug(SqlTemplateConfig.getConfig().getUpdateTemplate("update").toString());
	}
	
	@Test
	public void testSelectSqlTemplate(){
		SqlTemplateTest.LOGGER.debug(SqlTemplateConfig.getConfig().getSelectTemplate("select").toString());
	}
	
	@Test
	public void testDeleteSqlTemplate(){
		SqlTemplateTest.LOGGER.debug(SqlTemplateConfig.getConfig().getDeleteTemplate("delete").toString());
	}
	
	@Test
	public void testInsertTemplateRule(){
		
//		String template = " INSERT INTO ##table## ( ##columns## ) VALUES ( ##values## )";
		String template = " insert into ##table## ( ##columns## ) values ( ##values## )";
		
		SqlTemplateTest.LOGGER.debug("======INSERT规则校验开始======");
		
		Rule rule = SqlTemplateConfig.getConfig().getRule("insert");
		
		Map<String, Property> propMap = rule.toPropMap();// 获取其属性值
		Property prop = propMap.get(SqlTemplateEnum.SQL.getKey());// 获取指定的校验规则配置
		String regExp = prop.getValue();
		
		Pattern p = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(template);
		if(matcher.matches()){// SQL模板满足校验规则配置
			SqlTemplateTest.LOGGER.debug("INSERT规则校验通过");
		}else{
			SqlTemplateTest.LOGGER.debug("INSERT规则校验失败");
		}
		
		SqlTemplateTest.LOGGER.debug("======INSERT规则校验结束======");
	}
	
	@Test
	public void testUpdateTemplateRule(){
		
//		String template = " UPDATE ##table## SET ##columns## WHERE ##conditions## ";
		String template = " update ##table## set ##columns## where ##conditions## ";

		SqlTemplateTest.LOGGER.debug("======UPDATE规则校验开始======");
		
		Rule rule = SqlTemplateConfig.getConfig().getRule("update");
		
		Map<String, Property> propMap = rule.toPropMap();// 获取其属性值
		Property prop = propMap.get(SqlTemplateEnum.SQL.getKey());// 获取指定的校验规则配置
		String regExp = prop.getValue();
		
		Pattern p = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(template);
		if(matcher.matches()){// SQL模板满足校验规则配置
			SqlTemplateTest.LOGGER.debug("UPDATE规则校验通过");
		}else{
			SqlTemplateTest.LOGGER.debug("UPDATE规则校验失败");
		}
		
		SqlTemplateTest.LOGGER.debug("======UPDATE规则校验结束======");
	}
	
	@Test
	public void testSelectTemplateRule(){
		
//		String template = " SELECT ##columns## FROM ##table## WHERE ##conditions## ";
		String template = " select ##columns## from ##table## where ##conditions## ";

		SqlTemplateTest.LOGGER.debug("======SELECT规则校验开始======");
		
		Rule rule = SqlTemplateConfig.getConfig().getRule("select");
		
		Map<String, Property> propMap = rule.toPropMap();// 获取其属性值
		Property prop = propMap.get(SqlTemplateEnum.SQL.getKey());// 获取指定的校验规则配置
		String regExp = prop.getValue();
		
		Pattern p = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(template);
		if(matcher.matches()){// SQL模板满足校验规则配置
			SqlTemplateTest.LOGGER.debug("SELECT规则校验通过");
		}else{
			SqlTemplateTest.LOGGER.debug("SELECT规则校验失败");
		}
		
		SqlTemplateTest.LOGGER.debug("======SELECT规则校验结束======");
	}
	
	@Test
	public void testDeleteTemplateRule(){
		
		String template = " DELETE FROM ##table## WHERE ##conditions## ";
//		String template = " delete from ##table## where ##conditions## ";

		SqlTemplateTest.LOGGER.debug("======DELETE规则校验开始======");
		
		Rule rule = SqlTemplateConfig.getConfig().getRule("delete");
		
		Map<String, Property> propMap = rule.toPropMap();// 获取其属性值
		Property prop = propMap.get(SqlTemplateEnum.SQL.getKey());// 获取指定的校验规则配置
		String regExp = prop.getValue();
		
		Pattern p = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(template);
		if(matcher.matches()){// SQL模板满足校验规则配置
			SqlTemplateTest.LOGGER.debug("DELETE规则校验通过");
		}else{
			SqlTemplateTest.LOGGER.debug("DELETE规则校验失败");
		}
		
		SqlTemplateTest.LOGGER.debug("======DELETE规则校验结束======");
	}
	
}
