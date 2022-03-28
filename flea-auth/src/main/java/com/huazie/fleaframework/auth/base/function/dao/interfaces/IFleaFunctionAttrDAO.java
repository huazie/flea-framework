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
     * 获取功能扩展属性列表
     *
     * @param functionId   功能编号
     * @param functionType 功能类型
     * @param attrCode     属性码
     * @return 功能扩展属性列表
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaFunctionAttr> getFunctionAttrList(Long functionId, String functionType, String attrCode) throws CommonException;
}