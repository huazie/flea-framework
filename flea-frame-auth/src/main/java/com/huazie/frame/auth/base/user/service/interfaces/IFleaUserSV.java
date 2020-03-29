package com.huazie.frame.auth.base.user.service.interfaces;

import com.huazie.frame.auth.base.user.entity.FleaUser;
import com.huazie.frame.auth.common.pojo.user.FleaUserPOJO;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.db.jpa.service.interfaces.IAbstractFleaJPASV;

/**
 * <p> Flea用户SV层接口定义 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaUserSV extends IAbstractFleaJPASV<FleaUser> {

    /**
     * <p> 新建一个Flea用户 </p>
     *
     * @param fleaUserPOJO Flea用户POJO类实例
     * @return Flea用户对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaUser saveFleaUser(FleaUserPOJO fleaUserPOJO) throws CommonException;

}