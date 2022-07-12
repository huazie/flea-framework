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
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class SqlTemplateStrategyContext extends FleaStrategyContext<SqlTemplate<Object>, SqlTemplatePOJO<Object>> {
    
    private static Map<String, IFleaStrategy<SqlTemplate<Object>, SqlTemplatePOJO<Object>>> strategyMap;
    
    static {
        strategyMap = new HashMap<>();
        strategyMap.put(TemplateTypeEnum.INSERT.getKey(), new InsertSqlTemplateStrategy<>());
        strategyMap.put(TemplateTypeEnum.SELECT.getKey(), new SelectSqlTemplateStrategy<>());
        strategyMap.put(TemplateTypeEnum.UPDATE.getKey(), new UpdateSqlTemplateStrategy<>());
        strategyMap.put(TemplateTypeEnum.DELETE.getKey(), new DeleteSqlTemplateStrategy<>());
        strategyMap =  Collections.unmodifiableMap(strategyMap); 
    }

    public SqlTemplateStrategyContext(String relationId, Object entity) {
        super(new SqlTemplatePOJO<>(relationId, entity));
    }

    @Override
    protected Map<String, IFleaStrategy<SqlTemplate<Object>, SqlTemplatePOJO<Object>>> init() {
        return strategyMap;
    }
}
