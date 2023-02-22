package com.huazie.fleaframework.auth.base.user.service.interfaces;

import com.huazie.fleaframework.auth.base.user.entity.FleaAccountAttr;
import com.huazie.fleaframework.auth.common.pojo.user.attr.FleaAccountAttrPOJO;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;

/**
 * Flea账户扩展属性SV层接口定义
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaAccountAttrSV extends IAbstractFleaJPASV<FleaAccountAttr> {

    /**
     * 根据账户编号获取账户扩展属性信息（属性状态 1 正常，未失效）</p>
     *
     * @param accountId 账户编号
     * @return 账户扩展属性信息
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaAccountAttr> queryValidAccountAttrs(Long accountId) throws CommonException;

    /**
     * 新增Flea账户扩展属性
     *
     * @param fleaAccountAttrPOJO Flea账户扩展属性POJO类
     * @return Flea账户扩展属性实体类
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaAccountAttr saveFleaAccountAttr(FleaAccountAttrPOJO fleaAccountAttrPOJO) throws CommonException;

    /**
     * 批量新增Flea账户扩展属性
     *
     * @param fleaAccountAttrPOJOList Flea账户扩展属性POJO类List集合
     * @return Flea账户扩展属性实体类List集合
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaAccountAttr> saveFleaAccountAttrs(List<FleaAccountAttrPOJO> fleaAccountAttrPOJOList) throws CommonException;
}