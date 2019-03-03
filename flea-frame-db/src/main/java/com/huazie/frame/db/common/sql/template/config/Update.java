package com.huazie.frame.db.common.sql.template.config;

import com.huazie.frame.db.common.util.EntityUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p> 定义更新语句模板集合 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Update {
	
	private List<Template> updates = new ArrayList<Template>();

	/**
	 * <p> 获取更新模板的List对象 </p>
	 *
	 * @return 更新模板的List对象
	 * @since 1.0.0
	 */
	public List<Template> getUpdates() {
		return updates;
	}

	/**
	 * <p> 获取更新模板的数组对象 </p>
	 *
	 * @return 更新模板的数组对象
	 * @since 1.0.0
	 */
	public Template[] toUpdatesArray(){
		return updates.toArray(new Template[0]);
	}

	/**
	 * <p> 获取更新模板的Map对象，便于根据Sql模板id查找 </p>
	 *
	 * @return 更新模板的Map对象
	 * @since 1.0.0
	 */
	public Map<String, Template> toUpdatesMap(){
		return EntityUtils.toTemplatesMap(updates);
	}

	/**
	 * <p> 添加一个更新模板 </p>
	 *
	 * @param template Sql模板对象
	 * @since 1.0.0
	 */
	public void addTemplate(Template template){
		updates.add(template);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
