package com.huazie.fleaframework.auth.base.function.dao.interfaces;

import com.huazie.fleaframework.auth.base.function.entity.FleaFunctionAttr;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

import java.util.List;

/**
 * Flea功能扩展属性DAO层接口
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaFunctionAttrDAO extends IAbstractFleaJPADAO<FleaFunctionAttr> {

    /**
     * 根据属性编号和功能类型，获取功能扩展属性信息
     *
     * @param attrId       属性编号
     * @param functionType 功能类型
     * @return 功能扩展属性信息
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaFunctionAttr queryValidFunctionAttr(Long attrId, String functionType) throws CommonException;

    /**
     * 根据功能编号、功能类型和属性码，获取功能扩展属性集
     *
     * @param functionId       功能编号
     * @param functionType     功能类型
     * @param attrCode         属性码
     * @param attrValue        属性值
     * @param isAttrValueEqual true : 全值匹配 false ：模糊匹配
     * @return 功能扩展属性集
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaFunctionAttr> queryValidFunctionAttrs(Long functionId, String functionType, String attrCode, String attrValue, boolean isAttrValueEqual) throws CommonException;
}