package com.huazie.fleaframework.db.common.sql.template.config;

import com.huazie.fleaframework.db.common.util.EntityUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 定义SQL模板参数集合
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Params {

    private List<Param> params = new ArrayList<>();

    /**
     * 获取SQL模板参数的List对象
     *
     * @return SQL模板参数的List对象
     * @since 1.0.0
     */
    public List<Param> getParams() {
        return params;
    }

    /**
     * 获取SQL模板参数的数组对象
     *
     * @return SQL模板参数的数组对象
     * @since 1.0.0
     */
    public Param[] toParamsArray() {
        return params.toArray(new Param[0]);
    }

    /**
     * 获取SQL模板参数的Map对象，便于根据SQL模板参数id查找
     *
     * @return SQL模板参数的Map对象
     * @since 1.0.0
     */
    Map<String, Param> toParamsMap() {
        return EntityUtils.toParamsMap(params);
    }

    /**
     * 添加一个SQL模板参数
     *
     * @param param SQL模板参数
     * @since 1.0.0
     */
    public void addParam(Param param) {
        params.add(param);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
