package com.huazie.fleaframework.db.common.sql.template.config;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 属性集合
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Properties {

    private List<Property> properties = new ArrayList<>();

    /**
     * 获取属性的List对象
     *
     * @return 属性的List对象
     * @since 1.0.0
     */
    public List<Property> getProperties() {
        return properties;
    }

    /**
     * 获取属性的数组对象
     *
     * @return 属性的数组对象
     * @since 1.0.0
     */
    public Property[] toPropertiesArray() {
        return properties.toArray(new Property[0]);
    }

    /**
     * 获取属性的Map对象，便于根据属性的key查找
     *
     * @return 删除模板的Map对象
     * @since 1.0.0
     */
    public Map<String, Property> toPropMap() {
        Map<String, Property> propMap = new HashMap<>();
        for (Property prop : properties) {
            propMap.put(prop.getKey(), prop);
        }
        return propMap;
    }

    /**
     * 添加一个属性
     *
     * @param property 属性对象
     * @since 1.0.0
     */
    public void addProperty(Property property) {
        properties.add(property);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
