package com.huazie.fleaframework.auth.base.role.dao.interfaces;

import com.huazie.fleaframework.auth.base.role.entity.FleaRole;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

import java.util.List;

/**
 * Flea角色DAO层接口
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public interface IFleaRoleDAO extends IAbstractFleaJPADAO<FleaRole> {

    /**
     * 根据角色编号，查询在用状态的角色数据
     *
     * @param roleId 角色编号
     * @return 在用的角色数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    FleaRole queryRoleInUse(Long roleId) throws CommonException;

    /**
     * 根据角色名称 和 角色组编号，查询在用状态的角色数据集
     *
     * @param roleName  角色名称
     * @param groupId   角色组编号
     * @param pageNum   指定页
     * @param pageCount 每页条数
     * @return 在用的角色数据集
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaRole> queryRolesInUse4Page(String roleName, Long groupId, int pageNum, int pageCount) throws CommonException;

    /**
     * 根据角色名称，查询在用状态的角色数据总数
     *
     * @param roleName 角色名称
     * @param groupId  角色组编号
     * @return 在用状态的角色数据总数
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    long queryRolesInUseCount(String roleName, Long groupId) throws CommonException;
}