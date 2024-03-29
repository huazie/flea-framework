package com.huazie.fleaframework.auth.base.user.service.interfaces;

import com.huazie.fleaframework.auth.base.user.entity.FleaUserAttr;
import com.huazie.fleaframework.auth.common.pojo.user.attr.FleaUserAttrPOJO;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;

/**
 * Flea用户扩展属性SV层接口定义
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaUserAttrSV extends IAbstractFleaJPASV<FleaUserAttr> {

    /**
     * 根据用户编号获取用户扩展属性信息（属性状态 1 正常，未失效）</p>
     *
     * @param userId 用户编号
     * @return 用户扩展属性信息
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaUserAttr> queryValidUserAttrs(Long userId) throws CommonException;

    /**
     * 新增Flea用户扩展属性
     *
     * @param fleaUserAttrPOJO Flea用户扩展属性POJO类
     * @return Flea用户扩展属性实体类
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaUserAttr saveFleaUserAttr(FleaUserAttrPOJO fleaUserAttrPOJO) throws CommonException;

    /**
     * 批量新增Flea用户扩展属性
     *
     * @param fleaUserAttrPOJOList Flea用户扩展属性POJO类List集合
     * @return Flea用户扩展属性实体类List集合
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaUserAttr> saveFleaUserAttrs(List<FleaUserAttrPOJO> fleaUserAttrPOJOList) throws CommonException;
}