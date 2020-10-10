package com.huazie.frame.auth.base.user.dao.interfaces;

import com.huazie.frame.auth.base.user.entity.FleaRealNameInfo;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

/**
 * <p> Flea实名信息DAO层接口 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaRealNameInfoDAO extends IAbstractFleaJPADAO<FleaRealNameInfo> {

    /**
     * <p> 根据实名编号获取实名信息（实名状态 1 正常，未失效）</p>
     *
     * @param realNameId 实名编号
     * @return 实名信息
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaRealNameInfo queryValidRealNameInfo(Long realNameId) throws CommonException;
}