package com.huazie.frame.auth.base.user.service.interfaces;

import com.huazie.frame.auth.base.user.entity.FleaUserAttr;
import com.huazie.frame.auth.common.pojo.user.attr.FleaUserAttrPOJO;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;

/**
 * <p> Flea用户属性SV层接口定义 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaUserAttrSV extends IAbstractFleaJPASV<FleaUserAttr> {

    /**
     * <p> 新增Flea用户属性 </p>
     *
     * @param fleaUserAttrPOJO Flea用户属性POJO类
     * @return Flea用户属性实体类
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaUserAttr saveFleaUserAttr(FleaUserAttrPOJO fleaUserAttrPOJO) throws CommonException;

    /**
     * <p> 批量新增Flea用户属性 </p>
     *
     * @param fleaUserAttrPOJOList Flea用户属性POJO类List集合
     * @return Flea用户属性实体类List集合
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaUserAttr> saveFleaUserAttrs(List<FleaUserAttrPOJO> fleaUserAttrPOJOList) throws CommonException;

}