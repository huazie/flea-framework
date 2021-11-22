package com.huazie.fleaframework.auth.base.function.service.interfaces;

import com.huazie.fleaframework.auth.common.pojo.function.attr.FleaFunctionAttrPOJO;
import com.huazie.fleaframework.auth.base.function.entity.FleaFunctionAttr;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;

/**
 * <p> Flea功能扩展属性SV层接口定义 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaFunctionAttrSV extends IAbstractFleaJPASV<FleaFunctionAttr> {

    /**
     * <p> 获取功能扩展属性列表 </p>
     *
     * @param functionId   功能编号
     * @param functionType 功能类型
     * @param attrCode     属性码
     * @return 功能扩展属性列表
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaFunctionAttr> getFunctionAttrList(Long functionId, String functionType, String attrCode) throws CommonException;

    /**
     * <p> 保存功能扩展属性 </p>
     *
     * @param fleaFunctionAttrPOJO flea功能扩展属性POJO类
     * @return 功能扩展属性
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaFunctionAttr saveFunctionAttr(FleaFunctionAttrPOJO fleaFunctionAttrPOJO) throws CommonException;
}