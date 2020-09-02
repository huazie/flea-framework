package com.huazie.frame.auth.base.privilege.service.interfaces;

import com.huazie.frame.auth.base.privilege.entity.FleaPrivilege;
import com.huazie.frame.auth.common.pojo.privilege.FleaPrivilegePOJO;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.db.jpa.service.interfaces.IAbstractFleaJPASV;

/**
 * <p> Flea权限SV层接口定义 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaPrivilegeSV extends IAbstractFleaJPASV<FleaPrivilege> {

    /**
     * <p> 保存Flea权限 </p>
     *
     * @param fleaPrivilegePOJO Flea权限POJO类对象
     * @return Flea权限实体对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaPrivilege savePrivilege(FleaPrivilegePOJO fleaPrivilegePOJO) throws CommonException;
}