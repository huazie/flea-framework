package com.huazie.fleaframework.auth.base.privilege.service.interfaces;

import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeGroup;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeGroupPOJO;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;

/**
 * Flea权限组SV层接口定义
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public interface IFleaPrivilegeGroupSV extends IAbstractFleaJPASV<FleaPrivilegeGroup> {

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
     * @param pageNum            指定页
     * @param pageCount          每页条数
     * @return 在用的权限组数据集
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaPrivilegeGroup> queryPrivilegeGroupsInUse4Page(String privilegeGroupName, Integer isMain, String functionType, int pageNum, int pageCount) throws CommonException;

    /**
     * 根据权限组名称，查询在用状态的权限组数据集
     *
     * @param privilegeGroupName 权限组名称
     * @return 在用的权限组数据集
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaPrivilegeGroup> queryPrivilegeGroupsInUse(String privilegeGroupName, Integer isMain, String functionType) throws CommonException;

    /**
     * 根据权限组名称，查询在用状态的权限组数据总数
     *
     * @param privilegeGroupName 权限组名称
     * @return 在用的权限组数据总数
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    long queryPrivilegeGroupsInUseCount(String privilegeGroupName, Integer isMain, String functionType) throws CommonException;

    /**
     * 根据功能类型，查询在用状态的主权限组数据
     *
     * @param functionType 功能类型
     * @return 在用的主权限组数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    FleaPrivilegeGroup queryMainPrivilegeGroupInUse(String functionType) throws CommonException;

    /**
     * 保存Flea权限组
     *
     * @param fleaPrivilegeGroupPOJO Flea权限组POJO类对象
     * @return Flea权限组数据
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaPrivilegeGroup savePrivilegeGroup(FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO) throws CommonException;
}