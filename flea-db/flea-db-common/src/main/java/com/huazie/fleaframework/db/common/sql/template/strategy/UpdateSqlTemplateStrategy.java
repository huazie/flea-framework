package com.huazie.fleaframework.db.common.sql.template.strategy;

import com.huazie.fleaframework.common.exception.FleaStrategyException;
import com.huazie.fleaframework.common.strategy.IFleaStrategy;
import com.huazie.fleaframework.db.common.sql.pojo.SqlTemplatePOJO;
import com.huazie.fleaframework.db.common.sql.template.SqlTemplate;
import com.huazie.fleaframework.db.common.sql.template.impl.UpdateSqlTemplate;

/**
 * 更新SQL模板策略
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class UpdateSqlTemplateStrategy<T> implements IFleaStrategy<SqlTemplate<T>, SqlTemplatePOJO<T>> {

    @Override
    public SqlTemplate<T> execute(SqlTemplatePOJO<T> contextParam) throws FleaStrategyException {
        return new UpdateSqlTemplate<>(contextParam.getRelationId(), contextParam.getEntity());
    }
}
