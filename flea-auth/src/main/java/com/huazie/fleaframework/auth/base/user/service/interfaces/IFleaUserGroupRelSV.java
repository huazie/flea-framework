package com.huazie.fleaframework.auth.base.user.service.interfaces;

import com.huazie.fleaframework.auth.base.user.entity.FleaUserGroupRel;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserGroupRelPOJO;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;

/**
 * Flea用户组关联（角色，角色组）SV层接口定义
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaUserGroupRelSV extends IAbstractFleaJPASV<FleaUserGroupRel> {

    /**
     * 根据用户组编号、关联编号和授权关联类型，查询用户组关联数据
     *
     * @param userGroupId 用户组编号
     * @param relId       关联编号
     * @param authRelType 授权关联类型
     * @return 用户组关联数据
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaUserGroupRel> getUserGroupRelList(Long userGroupId, Long relId, String authRelType) throws CommonException;

    /**
     * 保存Flea用户组关联数据
     *
     * @param fleaUserGroupRelPOJO Flea用户组关联POJO对象
     * @return Flea用户组关联数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    FleaUserGroupRel saveUserGroupRel(FleaUserGroupRelPOJO fleaUserGroupRelPOJO) throws CommonException;
}