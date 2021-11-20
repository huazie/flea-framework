package com.huazie.frame.db.common.sql.template.config;

import com.huazie.frame.db.common.util.EntityUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p> 定义SQL模板和模板参数关联关系集合 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Relations {

    private List<Relation> relations = new ArrayList<>();

    /**
     * <p> 获取SQL模板和模板参数关联关系的List对象 </p>
     *
     * @return SQL模板和模板参数关联关系的List对象
     * @since 1.0.0
     */
    public List<Relation> getRelations() {
        return relations;
    }

    /**
     * <p> 获取SQL模板和模板参数关联关系的数组对象 </p>
     *
     * @return SQL模板和模板参数关联关系的数组对象
     * @since 1.0.0
     */
    public Relation[] toRelationsArray() {
        return relations.toArray(new Relation[0]);
    }

    /**
     * <p> 获取SQL模板和模板参数关联关系的Map对象，便于根据关系id查找 </p>
     *
     * @return SQL模板和模板参数关联关系的Map对象
     * @since 1.0.0
     */
    Map<String, Relation> toRelationsMap() {
        return EntityUtils.toRelationsMap(relations);
    }

    /**
     * <p> 添加一个SQL模板和模板参数关联关系 </p>
     *
     * @param relation SQL模板和模板参数关联关系
     * @since 1.0.0
     */
    public void addRelation(Relation relation) {
        relations.add(relation);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
