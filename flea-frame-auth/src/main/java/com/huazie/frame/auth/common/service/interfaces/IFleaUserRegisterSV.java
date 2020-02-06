package com.huazie.frame.auth.common.service.interfaces;

import com.huazie.frame.auth.base.user.entity.FleaAccount;
import com.huazie.frame.auth.common.pojo.user.register.FleaUserRegisterPOJO;
import com.huazie.frame.common.exception.CommonException;

/**
 * <p> Flea 用户注册服务接口类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaUserRegisterSV {

    /**
     * <p> 用户注册 </p>
     *
     * @param fleaUserRegisterInfo 用户注册信息
     * @return 账户信息
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaAccount register(FleaUserRegisterPOJO fleaUserRegisterInfo) throws CommonException;
}
