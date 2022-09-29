package com.huazie.fleaframework.db.common.sql.template;

import com.huazie.fleaframework.common.strategy.FleaStrategyFacade;

/**
 * SQL模板工厂类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class SqlTemplateFactory {

    private SqlTemplateFactory() {
    }

    /**
     * 新建SQL模板
     *
     * @param relationId       关系编号
     * @param entity           实体对象实例
     * @param templateTypeEnum 模板类型枚举
     * @return 指定模板类型的SQL模板
     * @since 1.0.0
     */
    public static SqlTemplate<Object> newSqlTemplate(String relationId, Object entity, TemplateTypeEnum templateTypeEnum) {
        SqlTemplateStrategyContext strategyContext = new SqlTemplateStrategyContext(relationId, entity);
        return FleaStrategyFacade.invoke(templateTypeEnum.getKey(), strategyContext);
    }

}
