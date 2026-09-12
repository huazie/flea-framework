package com.huazie.fleaframework.auth.base.user.service.interfaces;

import com.huazie.fleaframework.auth.base.user.entity.FleaUserOrgRel;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;

/**
 * Flea用户组织关联SV层接口
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public interface IFleaUserOrgRelSV extends IAbstractFleaJPASV<FleaUserOrgRel> {

    /**
     * 根据用户编号和组织编号，查询有效的用户组织关联数据
     *
     * <p> userId 和 orgId 均可为空，为空表示不限制该条件 </p>
     *
     * @param userId 用户编号
     * @param orgId  组织编号
     * @return 有效的用户组织关联数据集合
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaUserOrgRel> getUserOrgRelList(Long userId, Long orgId) throws CommonException;

    /**
     * 获取指定用户的组织编号集（取自有效的用户组织关联数据）
     *
     * @param userId 用户编号
     * @return 用户组织编号集
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<Long> getUserOrgIds(Long userId) throws CommonException;
}
