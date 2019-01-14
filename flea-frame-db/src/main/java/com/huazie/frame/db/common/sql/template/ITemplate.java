package com.huazie.frame.db.common.sql.template;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * 模板接口类
 * 
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月25日
 *
 */
public interface ITemplate extends Serializable {

	/**
	 * <p>
	 * 初始化SQL模板
	 * 
	 * @date 2018年1月29日
	 *
	 */
	public void initialize() throws Exception;

	/**
	 * <p>
	 * 获取原生SQL
	 * 
	 * @date 2018年1月28日
	 *
	 * @return
	 */
	public String toNativeSql();
	
	/**
	 * <p>
	 * 获取原生SQL的参数
	 * 
	 * @date 2018年1月29日
	 *
	 * @return
	 */
	public Map<String, Object> toNativeParams();
}
