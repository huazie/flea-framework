package com.huazie.frame.db.common.sql.template.config;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p> 校验规则集合 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Rules {

    private List<Rule> rules = new ArrayList<Rule>();

    /**
     * <p> 获取校验规则的List对象 </p>
     *
     * @return 校验规则的List对象
     * @since 1.0.0
     */
    public List<Rule> getRules() {
        return rules;
    }

    /**
     * <p> 获取校验规则的数组对象 </p>
     *
     * @return 校验规则的数组对象
     * @since 1.0.0
     */
    public Rule[] toRulesArray() {
        return rules.toArray(new Rule[0]);
    }

    /**
     * <p> 获取校验规则的Map对象，便于根据规则id查找 </p>
     *
     * @return 校验规则的Map对象
     * @since 1.0.0
     */
    public Map<String, Rule> toRulesMap() {
        Map<String, Rule> rulesMap = new HashMap<String, Rule>();
        Iterator<Rule> rulesIt = rules.iterator();
        while (rulesIt.hasNext()) {
            Rule rule = rulesIt.next();
            rulesMap.put(rule.getId(), rule);
        }
        return rulesMap;
    }

    /**
     * <p> 添加一个校验规则 </p>
     *
     * @param rule 校验规则对象
     * @since 1.0.0
     */
    public void addRule(Rule rule) {
        rules.add(rule);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
