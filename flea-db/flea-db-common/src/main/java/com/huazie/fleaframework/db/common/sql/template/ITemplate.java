package com.huazie.fleaframework.db.common.sql.template;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.db.common.sql.pojo.SqlParam;
import com.huazie.fleaframework.db.common.sql.template.config.Rule;
import com.huazie.fleaframework.db.common.sql.template.config.Template;

import java.io.Serializable;
import java.util.List;

/**
 * 模板接口类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ITemplate<T> extends Serializable {

    /**
     * 获取模板编号
     *
     * @return 模板编号
     * @since 1.0.0
     */
    String getId();

    /**
     * 获取模板对应的表
     *
     * @return 模板对应的表名
     * @since 1.0.0
     */
    String getTableName();

    /**
     * 获取模板实例化后匹配的实体类
     *
     * @return 模板实例化后匹配的实体类
     * @since 1.0.0
     */
    T getEntity();

    /**
     * 获取分表名
     *
     * @return 分表名
     * @since 1.0.0
     */
    String getSplitTableName();

    /**
     * 获取模板配置对象
     *
     * @return 模板配置对象
     * @since 1.0.0
     */
    Template getTemplate();

    /**
     * 获取模板对应校验规则对象
     *
     * @return 模板对应校验规则对象
     * @since 1.0.0
     */
    Rule getRule();

    /**
     * 初始化SQL模板
     *
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    void initialize() throws CommonException;

    /**
     * 获取原生SQL
     *
     * @return 获取原生SQL
     * @since 1.0.0
     */
    String toNativeSql();

    /**
     * 获取原生SQL的参数
     *
     * @return 获取原生SQL的参数
     * @since 1.0.0
     */
    List<SqlParam> toNativeParams();

    /**
     * 获取当前模板类型
     *
     * @return 返回SQL模板类型枚举对象实例
     * @since 1.0.0
     */
    TemplateTypeEnum getTemplateType();
}
