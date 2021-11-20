package com.huazie.frame.db.common.sql.template;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.db.common.sql.pojo.SqlParam;
import com.huazie.frame.db.common.sql.template.config.Rule;
import com.huazie.frame.db.common.sql.template.config.Template;

import java.io.Serializable;
import java.util.List;

/**
 * <p> 模板接口类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ITemplate<T> extends Serializable {

    /**
     * <p> 获取模板编号 </p>
     *
     * @return 模板编号
     * @since 1.0.0
     */
    String getId();

    /**
     * <p> 获取模板对应的表 </p>
     *
     * @return 模板对应的表名
     * @since 1.0.0
     */
    String getTableName();

    /**
     * <p> 获取模板实例化后匹配的实体类 </p>
     *
     * @return 模板实例化后匹配的实体类
     * @since 1.0.0
     */
    T getEntity();

    /**
     * <p> 获取分表名 </p>
     *
     * @return 分表名
     * @since 1.0.0
     */
    String getSplitTableName();

    /**
     * <p> 获取模板配置对象 </p>
     *
     * @return 模板配置对象
     * @since 1.0.0
     */
    Template getTemplate();

    /**
     * <p> 获取模板对应校验规则对象 </p>
     *
     * @return 模板对应校验规则对象
     * @since 1.0.0
     */
    Rule getRule();

    /**
     * <p> 初始化SQL模板 </p>
     *
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    void initialize() throws CommonException;

    /**
     * <p> 获取原生SQL </p>
     *
     * @return 获取原生SQL
     * @since 1.0.0
     */
    String toNativeSql();

    /**
     * <p> 获取原生SQL的参数 </p>
     *
     * @return 获取原生SQL的参数
     * @since 1.0.0
     */
    List<SqlParam> toNativeParams();

    /**
     * <p> 获取当前模板类型 </p>
     *
     * @return 返回SQL模板类型枚举对象实例
     * @since 1.0.0
     */
    TemplateTypeEnum getTemplateType();
}
