package com.huazie.fleaframework.auth.base.user.service.interfaces;

import com.huazie.fleaframework.auth.base.user.entity.FleaRealNameInfo;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

/**
 * Flea实名信息SV层接口定义
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaRealNameInfoSV extends IAbstractFleaJPASV<FleaRealNameInfo> {

    /**
     * 根据实名编号获取实名信息（实名状态 1 正常，未失效）</p>
     *
     * @param realNameId 实名编号
     * @return 实名信息
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaRealNameInfo queryValidRealNameInfo(Long realNameId) throws CommonException;
}