package com.huazie.frame.auth.base.function.dao.interfaces;

import com.huazie.frame.auth.base.function.entity.FleaFunctionAttr;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

import java.util.List;

/**
 * <p> Flea功能扩展属性DAO层接口 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaFunctionAttrDAO extends IAbstractFleaJPADAO<FleaFunctionAttr> {

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
}