package com.huazie.frame.auth.common.service.interfaces;

import com.huazie.frame.common.IFleaUser;
import com.huazie.frame.common.object.FleaObjectFactory;

import java.util.Map;

/**
 * <p> Flea 授权服务接口类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaAuthSV {

    /**
     * <p> 初始化用户信息 </p>
     *
     * @param userId       用户编号
     * @param acctId       账户编号
     * @param systemAcctId 系统账户编号
     * @param otherAttrs   其他属性
     * @since 1.0.0
     */
    void initUserInfo(Long userId, Long acctId, Long systemAcctId, Map<String, Object> otherAttrs, FleaObjectFactory<IFleaUser> fleaObjectFactory);

}
