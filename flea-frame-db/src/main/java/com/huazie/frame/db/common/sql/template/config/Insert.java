package com.huazie.frame.db.common.sql.template.config;

import com.huazie.frame.db.common.util.EntityUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p> 定义新增语句模板集合 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Insert {

    private List<Template> inserts = new ArrayList<Template>();

    /**
     * <p> 获取新增模板的List对象 </p>
     *
     * @return 新增模板的List对象
     * @since 1.0.0
     */
    public List<Template> getInserts() {
        return inserts;
    }

    /**
     * <p> 获取新增模板的数组对象 </p>
     *
     * @return 新增模板的数组对象
     * @since 1.0.0
     */
    public Template[] toInsertsArray() {
        return inserts.toArray(new Template[0]);
    }

    /**
     * <p> 获取新增模板的Map对象，便于根据Sql模板id查找 </p>
     *
     * @return 新增模板的Map对象
     * @since 1.0.0
     */
    public Map<String, Template> toInsertsMap() {
        return EntityUtils.toTemplatesMap(inserts);
    }

    /**
     * <p> 添加一个新增模板 </p>
     *
     * @param template Sql模板对象
     * @since 1.0.0
     */
    public void addTemplate(Template template) {
        inserts.add(template);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
