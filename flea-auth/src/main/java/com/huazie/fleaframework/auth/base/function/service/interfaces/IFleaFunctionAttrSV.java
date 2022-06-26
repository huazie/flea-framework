package com.huazie.fleaframework.auth.base.function.service.interfaces;

import com.huazie.fleaframework.auth.base.function.entity.FleaFunctionAttr;
import com.huazie.fleaframework.auth.common.pojo.function.attr.FleaFunctionAttrPOJO;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;

/**
 * Flea功能扩展属性SV层接口定义
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaFunctionAttrSV extends IAbstractFleaJPASV<FleaFunctionAttr> {

    /**
     * 根据属性编号 和 功能类型，获取功能扩展属性信息
     *
     * @param attrId       属性编号
     * @param functionType 功能类型
     * @return 功能扩展属性信息
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaFunctionAttr queryValidFunctionAttr(Long attrId, String functionType) throws CommonException;

    /**
     * 获取功能扩展属性列表
     *
     * @param functionId   功能编号
     * @param functionType 功能类型
     * @param attrCode     属性码
     * @return 功能扩展属性列表
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaFunctionAttr> queryValidFunctionAttrs(Long functionId, String functionType, String attrCode) throws CommonException;

    /**
     * 获取功能扩展属性列表
     *
     * @param functionId       功能编号
     * @param functionType     功能类型
     * @param attrCode         属性码
     * @param attrValue        属性值
     * @param isAttrValueEqual true : 全值匹配 false ：模糊匹配
     * @return 功能扩展属性列表
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaFunctionAttr> queryValidFunctionAttrs(Long functionId, String functionType, String attrCode, String attrValue, boolean isAttrValueEqual) throws CommonException;

    /**
     * 获取指定系统账户下，指定功能类型对应的功能属性集
     *
     * @param functionId      功能编号
     * @param functionType    功能类型
     * @param systemAccountId 系统账户编号
     * @return 功能编号集
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaFunctionAttr> querySystemRelFunctionAttrs(Long functionId, String functionType, Long systemAccountId) throws CommonException;

    /**
     * 指定系统账户下，是否存在对应的功能
     *
     * @param functionId      功能编号
     * @param functionType    功能类型
     * @param systemAccountId 系统账户编号
     * @return true：存在 false：不存在
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    boolean isExistSystemRelFunction(Long functionId, String functionType, Long systemAccountId) throws CommonException;

    /**
     * 获取指定系统账户下，指定功能类型对应的功能编号集
     *
     * @param functionType    功能类型
     * @param systemAccountId 系统账户编号
     * @return 功能编号集
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<Long> querySystemRelFunctionIds(String functionType, Long systemAccountId) throws CommonException;

    /**
     * 保存功能扩展属性集合
     *
     * @param fleaFunctionAttrPOJOList flea功能扩展属性POJO对象集合
     * @return 功能扩展属性
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaFunctionAttr> saveFunctionAttrs(List<FleaFunctionAttrPOJO> fleaFunctionAttrPOJOList) throws CommonException;

    /**
     * 保存功能扩展属性
     *
     * @param fleaFunctionAttrPOJO flea功能扩展属性POJO对象
     * @return 功能扩展属性
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaFunctionAttr saveFunctionAttr(FleaFunctionAttrPOJO fleaFunctionAttrPOJO) throws CommonException;
}