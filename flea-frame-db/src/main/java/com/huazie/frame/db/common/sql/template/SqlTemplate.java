package com.huazie.frame.db.common.sql.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ArrayUtils;

import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.DBConstants;
import com.huazie.frame.db.common.exception.SqlTemplateException;
import com.huazie.frame.db.common.sql.template.config.Property;
import com.huazie.frame.db.common.sql.template.config.Rule;
import com.huazie.frame.db.common.sql.template.config.SqlTemplateConfig;
import com.huazie.frame.db.common.sql.template.config.Template;
import com.huazie.frame.db.common.tab.column.Column;
import com.huazie.frame.db.common.tab.split.TableSplitHelper;
import com.huazie.frame.db.common.util.EntityUtils;

/**
 * <p>
 *  Sql模板父类定义
 * 
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月25日
 *
 */
@SuppressWarnings("serial")
public abstract class SqlTemplate<T> implements ITemplate {
	
	private StringBuilder sql = new StringBuilder();
	
	private Map<String, Object> params = new HashMap<String, Object>(); // SQL参数

	private String tableName; // 主表名

	private String realTableName; // 分表名

	private T entity; // 实体类
	
	private String tempId;	//模板编号

	private Template template; // SQL模板
	
	private Rule rule;//校验规则
	
	protected TemplateTypeEnum templateType;// 模板类型
	
	public SqlTemplate(){
	}
	
	public SqlTemplate(String id, T entity){
		this.tempId = id;
		this.template = this.getSqlTemplate(id);
		this.rule = SqlTemplateConfig.getConfig().getRule(this.template.getRuleId());
		this.entity = entity;
	}
	
	public SqlTemplate(String id, String tableName, T entity) {
		this(id, entity);
		this.tableName = tableName;
	}
	
	@Override
	public void initialize()throws Exception{
		if(ObjectUtils.isEmpty(this.template)){
			throw new SqlTemplateException("请检查SQL模板【id=" + this.getId() + "】配置（没有找到指定模板编号【id=" + tempId + "】的模板）");
		}
		Map<String, Property> propMap = this.template.toPropMap();
		if(propMap == null || propMap.isEmpty()){
			throw new SqlTemplateException("请检查SQL模板【id=" + this.getId() + "】配置（该模板没有配置相关属性）");
		}
		// 获取key=template的属性(即SQL模板)
		Property template = propMap.get(SqlTemplateEnum.TEMPLATE.getKey());
		// 获取key=table的属性(即表名)
		Property table = propMap.get(SqlTemplateEnum.TABLE.getKey());
		
		if(ObjectUtils.isEmpty(template)){
			throw new SqlTemplateException("请检查SQL模板【id=" + this.getId() + "】配置（该模板没有配置【key= " + SqlTemplateEnum.TEMPLATE.getKey() + "】的模板属性）");
		}
		
		if(ObjectUtils.isEmpty(table)){
			throw new SqlTemplateException("请检查SQL模板【id=" + this.getId() + "】配置（该模板没有配置【key=" + SqlTemplateEnum.TABLE.getKey() + "】的表名属性）");
		}
		
		// SQL模板规则校验
		this.checkRule(template.getValue());
		// 初始化模板
		this.sql.append(template.getValue());
		// 获取配置的SQL模板的表名
		String tName = table.getValue();
		
		if(StringUtils.isBlank(this.tableName)){
			this.tableName = EntityUtils.getTableName(this.entity);// 从实体类上获取表名
			if(StringUtils.isBlank(this.tableName)){
				throw new SqlTemplateException("请检查初始实体类(其上的注解@Table或者@FleaTable对应的表名不能为空)");
			}
		}
		
		if(StringUtils.isBlank(tName)){
			throw new SqlTemplateException("请检查SQL模板【id=" + this.getId() + "】配置(【key=table】表名不能为空)）");
		}
		
		if(!this.tableName.equals(tName)){
			throw new SqlTemplateException("请检查SQL模板【id=" + this.getId() + "】配置(【key=table】表名和初始化的表名两者必须保持一致)");
		}
		
		if(ObjectUtils.isEmpty(this.entity)){
			throw new SqlTemplateException("请检查初始实体类（该实体类对象没有被初始化）");
		}
		
		// 获取实体类T的对象的属性列相关信息
		Column[] entityCols = EntityUtils.toColumnsArray(this.entity);
		
		if(ArrayUtils.isEmpty(entityCols)){
			throw new SqlTemplateException("请检查初始实体类（实体类的属性列相关信息不存在）");
		}
		
		// 获取真实表名，如是分表，则获取分表名
		this.realTableName = TableSplitHelper.getRealTableName(this.tableName, entityCols);
		// 替换真实表名
		StringUtils.replace(this.sql, this.createPlaceHolder(SqlTemplateEnum.TABLE.getKey()), this.realTableName);
		// 特殊处理初始化
		initSqlTemplate(this.sql, this.params, entityCols, propMap);
		
	}
	
	/**
	 * SQL模板校验规则
	 * 
	 * @date 2018年6月24日
	 *
	 * @param template
	 * @throws Exception
	 */
	private void checkRule(String template) throws Exception{
		if(this.rule == null){// 需要校验
			throw new SqlTemplateException("请检查SQL模板【id=" + this.getId() + "】配置（未找到指定的校验规则）");
		}
		
		Map<String, Property> propMap = this.rule.toPropMap();// 获取其属性值
		if(propMap != null && !propMap.isEmpty()){
			Property prop = propMap.get(SqlTemplateEnum.SQL.getKey());// 获取指定的校验规则配置
			if(prop == null){
				throw new SqlTemplateException("请检查SQL模板【id=" + this.getId() + "】配置（配置的校验规则未找到【key=sql】的属性）");
			}
			String regExp = prop.getValue();
			
			Pattern p = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
			Matcher matcher = p.matcher(template);
			if(!matcher.matches()){// SQL模板不满足校验规则配置
				throw new SqlTemplateException("请检查SQL模板【id=" + this.getId() + "】配置（【key=template】模板配置有误）");
			}
		}
	}
	
	/**
	 * 生成SQL模板中的占位符
	 * 
	 * @date 2018年7月29日
	 *
	 * @param str
	 * @return
	 */
	protected String createPlaceHolder(String str){
		return SqlTemplateEnum.PLACEHOLDER.getKey() + str + SqlTemplateEnum.PLACEHOLDER.getKey();
	}
	
	/**
	 * 获取SQL语句中的参数Map集合
	 * 
	 * @date 2018年6月3日
	 *
	 * @param paramMap
	 *            SQL语句中的参数Map集合
	 * @param entityCols
	 *            实体类中属性列集合
	 * @throws Exception
	 */
	protected void createParamMap(Map<String, Object> paramMap, Column[] entityCols)throws Exception{
		if(ArrayUtils.isEmpty(entityCols)){
			return;
		}
		if(ObjectUtils.isEmpty(paramMap)){
			paramMap = new HashMap<String, Object>();
		}
		for(int i = 0; i < entityCols.length; i++){
			Column column = entityCols[i];
			paramMap.put(column.getAttrName(), column.getAttrValue());
		}
	}
	
	/**
	 * 获取SQL条件语句的各个条件的Map集合
	 * 
	 * @date 2018年6月3日
	 *
	 * @param condition
	 *            SQL条件语句字符串
	 * @return
	 * @throws Exception
	 */
	protected Map<String, String> createConditionMap(String condition)throws Exception{
		Map<String, String> map = null;
		// 首先需要将条件语句中的 圆括号 全部替换成空格
		String newConn = condition
				.replace(DBConstants.SQLConstants.SQL_LEFT_ROUND_BRACKETS, DBConstants.SQLConstants.SQL_BLANK)
				.replace(DBConstants.SQLConstants.SQL_RIGHT_ROUND_BRACKETS, DBConstants.SQLConstants.SQL_BLANK);
		
		// 根据 and 和 or 进行分组
		String[] singleConnAttr = StringUtils.split(newConn, DBConstants.SQLConstants.SQL_AND, DBConstants.SQLConstants.SQL_LOWER_AND, DBConstants.SQLConstants.SQL_OR, DBConstants.SQLConstants.SQL_LOWER_OR);
		if(!ArrayUtils.isEmpty(singleConnAttr)){
			
			map = new HashMap<String, String>();
			
			for(String singleConn : singleConnAttr){
				String[] connAttr = StringUtils.split(singleConn, DBConstants.SQLConstants.SQL_EQUAL,
						DBConstants.SQLConstants.SQL_GE, DBConstants.SQLConstants.SQL_GT,
						DBConstants.SQLConstants.SQL_LE, DBConstants.SQLConstants.SQL_LT,
						DBConstants.SQLConstants.SQL_LIKE, DBConstants.SQLConstants.SQL_LOWER_LIKE,
						DBConstants.SQLConstants.SQL_IN, DBConstants.SQLConstants.SQL_LOWER_IN);
				if(connAttr.length == 2){
					String key = connAttr[0];
					String value = connAttr[1];
					
					if(StringUtils.isBlank(key) || StringUtils.isBlank(value)){
						continue;
					}
					
					if(value.contains(DBConstants.SQLConstants.SQL_COLON)){
						map.put(StringUtils.trim(key), StringUtils.trim(value));
					}
				}
			}
		}
		
		return map;
	}
	
	/**
	 * <p>
	 * 校验 表字段列和变量是否一一对应（如para_id = :paraId）
	 * 
	 * @date 2018年1月31日
	 *
	 * @param entityCols
	 *            实体类的属性列数组
	 * @param map
	 *            map的key存表字段列，value存变量
	 * @throws Exception 
	 */
	protected Column[] checkOneByOne(final Column[] entityCols, Map<String, String> map, SqlTemplateEnum sqlTemplateEnum) throws Exception{
		List<Column> cols = null;
		if(map == null || map.isEmpty()){
			return null;
		}
		
		cols = new ArrayList<Column>();
		
		Set<String> tabColNameSet = map.keySet();
		Iterator<String> tabColNameIt = tabColNameSet.iterator();
		while(tabColNameIt.hasNext()){
			String tabColName = tabColNameIt.next();
			String attrName = map.get(tabColName);
			
			if(StringUtils.isEmpty(attrName)){
				throw new SqlTemplateException("请检查SQL模板配置【id=" + this.getId() + "】属性（【key=" + sqlTemplateEnum.getKey() + "】中的字段" + tabColName + "对应的属性变量为空）");
			}
			
			Column column = (Column) EntityUtils.getEntity(entityCols, Column.COLUMN_TAB_COL_NAME, tabColName);
			if(ObjectUtils.isEmpty(column)){
				throw new SqlTemplateException("请检查SQL模板配置【id=" + this.getId() + "】属性（【key=" + sqlTemplateEnum.getKey() + "】中的字段" + tabColName + "不存在）");
			}
		
			String attrN = column.getAttrName();
			if(!attrName.equals(StringUtils.strCat(DBConstants.SQLConstants.SQL_COLON, attrN))){
				throw new SqlTemplateException("请检查SQL模板配置【id=" + this.getId() + "】属性（【key=" + sqlTemplateEnum.getKey() + "】中的属性列 【" + tabColName + "】与  属性变量【" + attrName + "】不一一对应）");
			}
			cols.add(column);
		}
		return cols.toArray(new Column[0]);
	}
	
	public String getId() {
		return tempId;
	}
	
	public void setId(String id) {
		this.tempId = id;
		this.template = this.getSqlTemplate(id);
		this.rule = SqlTemplateConfig.getConfig().getRule(this.template.getRuleId());
	}

	public String getTableName() {
		return tableName;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public T getEntity() {
		return entity;
	}
	
	public void setEntity(T entity) {
		this.entity = entity;
	}

	public String getSplitTableName() {
		return realTableName;
	}
	
	public Template getTemplate() {
		return template;
	}
	
	public Rule getRule() {
		return rule;
	}

	/**
	 * <p>
	 * 根据模板编号，获取对应SQL模板
	 * 
	 * @date 2018年1月29日
	 *
	 * @param id
	 * @return
	 */
	protected abstract Template getSqlTemplate(String id);
	
	/**
	 * <p>
	 * 用于特殊处理SQL模板的初始化工作
	 * 
	 * @date 2018年1月29日
	 *
	 * @param propMap
	 * @throws Exception
	 */
	protected abstract void initSqlTemplate(StringBuilder sql, Map<String, Object> params, final Column[] entityCols, Map<String, Property> propMap)throws Exception;

	@Override
	public String toNativeSql() {
		return this.sql.toString();
	}
	
	@Override
	public Map<String, Object> toNativeParams(){
		return this.params;
	}

}
