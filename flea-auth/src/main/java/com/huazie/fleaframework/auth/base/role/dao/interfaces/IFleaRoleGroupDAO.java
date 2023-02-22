package com.huazie.fleaframework.auth.base.role.dao.interfaces;

import com.huazie.fleaframework.auth.base.role.entity.FleaRoleGroup;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

import java.util.List;

/**
 * Flea角色组DAO层接口
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public interface IFleaRoleGroupDAO extends IAbstractFleaJPADAO<FleaRoleGroup> {

    /**
     * 根据角色组编号，查询在用状态的角色组数据
     *
     * @param roleGroupId 角色组编号
     * @return 在用的角色组数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    FleaRoleGroup queryRoleGroupInUse(Long roleGroupId) throws CommonException;

    /**
     * 根据角色组名称，查询在用状态的角色组数据集【用于分页查询】
     *
     * @param roleGroupName 角色组名称
     * @param pageNum       指定页
     * @param pageCount     每页条数
     * @return 在用的角色组数据集
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaRoleGroup> queryRoleGroupsInUse4Page(String roleGroupName, int pageNum, int pageCount) throws CommonException;

    /**
     * 根据角色组名称，查询在用状态的角色组数据总数
     *
     * @param roleGroupName 角色组名称
     * @return 在用的角色组数据总数
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    long queryRoleGroupsInUseCount(String roleGroupName) throws CommonException;
}