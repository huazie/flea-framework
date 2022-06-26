package com.huazie.fleaframework.auth.base.privilege.dao.interfaces;

import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilege;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

import java.util.List;

/**
 * Flea权限DAO层接口
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public interface IFleaPrivilegeDAO extends IAbstractFleaJPADAO<FleaPrivilege> {

    /**
     * 根据权限编号，查询在用状态的权限数据
     *
     * @param privilegeId 权限编号
     * @return 在用的权限数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    FleaPrivilege queryPrivilegeInUse(Long privilegeId) throws CommonException;

    /**
     * 根据权限名称 和 权限组编号，查询在用状态的权限数据集【用于分页查询】
     *
     * @param privilegeName 权限名称
     * @param groupId       权限组编号
     * @param pageNum       指定页
     * @param pageCount     每页条数
     * @return 在用的权限数据集
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaPrivilege> queryPrivilegesInUse4Page(String privilegeName, Long groupId, int pageNum, int pageCount) throws CommonException;

    /**
     * 根据权限名称，查询在用状态的权限数据总数
     *
     * @param privilegeName 权限名称
     * @param groupId       权限组编号
     * @return 在用状态的权限数据总数
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    long queryPrivilegesInUseCount(String privilegeName, Long groupId) throws CommonException;
}