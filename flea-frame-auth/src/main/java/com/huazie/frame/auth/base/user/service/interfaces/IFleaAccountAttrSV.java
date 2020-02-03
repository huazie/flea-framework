package com.huazie.frame.auth.base.user.service.interfaces;

import com.huazie.frame.auth.base.user.entity.FleaAccountAttr;
import com.huazie.frame.auth.common.pojo.account.attr.FleaAccountAttrInfo;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;

/**
 * <p> Flea帐户属性SV层接口定义 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaAccountAttrSV extends IAbstractFleaJPASV<FleaAccountAttr> {

    /**
     * <p> 新增Flea账户属性 </p>
     *
     * @param fleaAccountAttrInfo Flea账户属性POJO类
     * @return Flea账户属性实体类
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaAccountAttr saveFleaAccountAttr(FleaAccountAttrInfo fleaAccountAttrInfo) throws CommonException;

    /**
     * <p> 批量新增Flea账户属性 </p>
     *
     * @param fleaAccountAttrInfoList Flea账户属性POJO类List集合
     * @return Flea账户属性实体类List集合
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaAccountAttr> saveFleaAccountAttrs(List<FleaAccountAttrInfo> fleaAccountAttrInfoList) throws CommonException;

}