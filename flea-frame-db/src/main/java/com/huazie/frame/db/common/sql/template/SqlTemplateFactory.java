package com.huazie.frame.db.common.sql.template;

import com.huazie.frame.db.common.sql.template.impl.DeleteSqlTemplate;
import com.huazie.frame.db.common.sql.template.impl.InsertSqlTemplate;
import com.huazie.frame.db.common.sql.template.impl.SelectSqlTemplate;
import com.huazie.frame.db.common.sql.template.impl.UpdateSqlTemplate;

/**
 * <p> SQL模板工厂类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class SqlTemplateFactory {

    /**
     * <p> 新建SQL模板 </p>
     *
     * @param relationId       关系编号
     * @param entity           实体对象实例
     * @param templateTypeEnum 模板类型枚举
     * @param <T>              实体对象类型
     * @return 指定模板类型的SQL模板
     * @since 1.0.0
     */
    public static <T> SqlTemplate<T> newSqlTemplate(String relationId, T entity, TemplateTypeEnum templateTypeEnum) {
        SqlTemplate<T> sqlTemplate;
        if (TemplateTypeEnum.INSERT.getKey().equals(templateTypeEnum.getKey())) {
            sqlTemplate = new InsertSqlTemplate<>(relationId, entity);
        } else if (TemplateTypeEnum.SELECT.getKey().equals(templateTypeEnum.getKey())) {
            sqlTemplate = new SelectSqlTemplate<>(relationId, entity);
        } else if (TemplateTypeEnum.UPDATE.getKey().equals(templateTypeEnum.getKey())) {
            sqlTemplate = new UpdateSqlTemplate<>(relationId, entity);
        } else if (TemplateTypeEnum.DELETE.getKey().equals(templateTypeEnum.getKey())) {
            sqlTemplate = new DeleteSqlTemplate<>(relationId, entity);
        } else {
            throw new RuntimeException("未知异常");
        }
        return sqlTemplate;
    }

}
