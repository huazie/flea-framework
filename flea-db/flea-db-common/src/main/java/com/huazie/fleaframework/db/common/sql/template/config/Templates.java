package com.huazie.fleaframework.db.common.sql.template.config;

import com.huazie.fleaframework.db.common.util.EntityUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 定义SQL模板集合
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Templates {

    private List<Template> templates = new ArrayList<>();

    /**
     * 获取SQL模板的List对象
     *
     * @return SQL模板的List对象
     * @since 1.0.0
     */
    public List<Template> getTemplates() {
        return templates;
    }

    /**
     * 获取SQL模板的数组对象
     *
     * @return SQL模板的数组对象
     * @since 1.0.0
     */
    public Template[] toTemplatesArray() {
        return templates.toArray(new Template[0]);
    }

    /**
     * 获取SQL模板的Map对象，便于根据SQL模板id查找
     *
     * @return SQL模板的Map对象
     * @since 1.0.0
     */
    Map<String, Template> toTemplatesMap() {
        return EntityUtils.toTemplatesMap(templates);
    }

    /**
     * 添加一个SQL模板
     *
     * @param template SQL模板对象
     * @since 1.0.0
     */
    public void addTemplate(Template template) {
        templates.add(template);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
