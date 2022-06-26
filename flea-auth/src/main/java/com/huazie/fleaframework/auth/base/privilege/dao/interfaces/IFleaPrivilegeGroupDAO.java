package com.huazie.fleaframework.auth.base.privilege.dao.interfaces;

import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeGroup;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

import java.util.List;

/**
 * Flea权限组DAO层接口
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public interface IFleaPrivilegeGroupDAO extends IAbstractFleaJPADAO<FleaPrivilegeGroup> {

    /**
     * 根据权限组编号，查询在用状态的权限组数据
     *
     * @param privilegeGroupId 权限组编号
     * @return 在用的权限组数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    FleaPrivilegeGroup queryPrivilegeGroupInUse(Long privilegeGroupId) throws CommonException;

    /**
     * 根据权限组名称，查询在用状态的权限组数据集【用于分页查询】
     *
     * @param privilegeGroupName 权限组名称
     * @param isMain             是否为主权限组（0：不是 1：是）
     * @param functionType       功能类型(菜单、操作、元素、资源)
     * @param pageNum            指定页
     * @param pageCount          每页条数
     * @return 在用的权限组数据集
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaPrivilegeGroup> queryPrivilegeGroupsInUse4Page(String privilegeGroupName, Integer isMain, String functionType, int pageNum, int pageCount) throws CommonException;

    /**
     * 根据权限组名称，查询在用状态的权限组数据总数
     *
     * @param privilegeGroupName 权限组名称
     * @param isMain             是否为主权限组（0：不是 1：是）
     * @param functionType       功能类型(菜单、操作、元素、资源)
     * @return 在用的权限组数据总数
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    long queryPrivilegeGroupsInUseCount(String privilegeGroupName, Integer isMain, String functionType) throws CommonException;
}