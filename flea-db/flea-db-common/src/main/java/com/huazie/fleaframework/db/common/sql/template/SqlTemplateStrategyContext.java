package com.huazie.fleaframework.db.common.sql.template;

import com.huazie.fleaframework.common.strategy.FleaStrategyContext;
import com.huazie.fleaframework.common.strategy.IFleaStrategy;
import com.huazie.fleaframework.db.common.sql.pojo.SqlTemplatePOJO;
import com.huazie.fleaframework.db.common.sql.template.strategy.DeleteSqlTemplateStrategy;
import com.huazie.fleaframework.db.common.sql.template.strategy.InsertSqlTemplateStrategy;
import com.huazie.fleaframework.db.common.sql.template.strategy.SelectSqlTemplateStrategy;
import com.huazie.fleaframework.db.common.sql.template.strategy.UpdateSqlTemplateStrategy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * SQL模板策略上下文
 *
 * @param <T> 实体类型
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class SqlTemplateStrategyContext<T> extends FleaStrategyContext<SqlTemplate<T>, SqlTemplatePOJO<T>> {

    public SqlTemplateStrategyContext(String relationId, T entity) {
        super(new SqlTemplatePOJO<>(relationId, entity));
    }

    @Override
    protected Map<String, IFleaStrategy<SqlTemplate<T>, SqlTemplatePOJO<T>>> init() {
        Map<String, IFleaStrategy<SqlTemplate<T>, SqlTemplatePOJO<T>>> strategyMap = new HashMap<>();
        strategyMap.put(TemplateTypeEnum.INSERT.getKey(), new InsertSqlTemplateStrategy<T>());
        strategyMap.put(TemplateTypeEnum.SELECT.getKey(), new SelectSqlTemplateStrategy<T>());
        strategyMap.put(TemplateTypeEnum.UPDATE.getKey(), new UpdateSqlTemplateStrategy<T>());
        strategyMap.put(TemplateTypeEnum.DELETE.getKey(), new DeleteSqlTemplateStrategy<T>());
        return Collections.unmodifiableMap(strategyMap);
    }
}
