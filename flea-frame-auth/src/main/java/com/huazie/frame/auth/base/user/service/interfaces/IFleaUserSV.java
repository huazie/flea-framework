package com.huazie.frame.auth.base.user.service.interfaces;

import com.huazie.frame.auth.base.user.entity.FleaUser;
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
     * @param accountCode 账号
     * @param groupId     用户组编号
     * @param userState   用户状态（0：删除，1：正常 ，2：禁用，3：待审核）
     * @param remarks     备注
     * @return Flea用户对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaUser saveFleaUser(String accountCode, Long groupId, Integer userState, String remarks) throws CommonException;

}