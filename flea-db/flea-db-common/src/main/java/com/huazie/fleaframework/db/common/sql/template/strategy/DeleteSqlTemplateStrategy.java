package com.huazie.fleaframework.db.common.sql.template.strategy;

import com.huazie.fleaframework.common.exceptions.FleaStrategyException;
import com.huazie.fleaframework.common.strategy.IFleaStrategy;
import com.huazie.fleaframework.db.common.sql.pojo.SqlTemplatePOJO;
import com.huazie.fleaframework.db.common.sql.template.SqlTemplate;
import com.huazie.fleaframework.db.common.sql.template.impl.DeleteSqlTemplate;

/**
 * 删除SQL模板策略
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class DeleteSqlTemplateStrategy<T> implements IFleaStrategy<SqlTemplate<T>, SqlTemplatePOJO<T>> {

    @Override
    public SqlTemplate<T> execute(SqlTemplatePOJO<T> contextParam) throws FleaStrategyException {
        return new DeleteSqlTemplate<>(contextParam.getRelationId(), contextParam.getEntity());
    }
}
