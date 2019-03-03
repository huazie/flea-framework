package com.huazie.frame.db.common.sql.template.config;

import com.huazie.frame.db.common.util.EntityUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p> 定义查询语句模板集合 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Select {

    private List<Template> selects = new ArrayList<Template>();

    /**
     * <p> 获取查询模板的List对象 </p>
     *
     * @return 查询模板的List对象
     * @since 1.0.0
     */
    public List<Template> getSelects() {
        return selects;
    }

    /**
     * <p> 获取查询模板的数组对象 </p>
     *
     * @return 查询模板的数组对象
     * @since 1.0.0
     */
    public Template[] toSelectsArray() {
        return selects.toArray(new Template[0]);
    }

    /**
     * <p> 获取查询模板的Map对象，便于根据Sql模板id查找 </p>
     *
     * @return 查询模板的Map对象
     * @since 1.0.0
     */
    public Map<String, Template> toSelectsMap() {
        return EntityUtils.toTemplatesMap(selects);
    }

    /**
     * <p> 添加一个查询模板 </p>
     *
     * @param template Sql模板对象
     * @since 1.0.0
     */
    public void addTemplate(Template template) {
        selects.add(template);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
