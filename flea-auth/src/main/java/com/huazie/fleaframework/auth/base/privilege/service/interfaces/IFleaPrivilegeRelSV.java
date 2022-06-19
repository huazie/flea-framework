package com.huazie.fleaframework.auth.base.privilege.service.interfaces;

import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeRel;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeRelPOJO;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;

/**
 * Flea权限关联功能【菜单、操作、元素和资源】SV层接口定义
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public interface IFleaPrivilegeRelSV extends IAbstractFleaJPASV<FleaPrivilegeRel> {

    /**
     * 根据权限编号和授权关联类型，查询权限关联数据集
     *
     * @param privilegeId 权限编号
     * @param authRelType 授权关联类型
     * @return 权限关联数据集
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaPrivilegeRel> getPrivilegeRelList(Long privilegeId, String authRelType) throws CommonException;

    /**
     * 根据权限编号集和授权类型，查询在用的权限关联功能数据。
     *
     * @param privilegeIdList 权限编号集
     * @param authRelType     授权关联类型
     * @return 权限关联数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaPrivilegeRel> getPrivilegeRelList(List<Long> privilegeIdList, String authRelType) throws CommonException;

    /**
     * 根据权限编号集和授权关联类型，查询权限关联的功能编号集
     *
     * @param privilegeIdList 权限编号集
     * @param authRelType 授权关联类型
     * @return 功能编号集
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<Long> getPrivilegeRelIdList(List<Long> privilegeIdList, String authRelType) throws CommonException;

    /**
     * 根据菜单编号，查询对应的权限关联菜单数据
     *
     * @param menuId 菜单编号
     * @return 权限关联菜单数据
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaPrivilegeRel getPrivilegeRelMenu(Long menuId) throws CommonException;

    /**
     * 根据操作编号，查询对应的权限关联操作数据
     *
     * @param operationId 操作编号
     * @return 权限关联操作数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    FleaPrivilegeRel getPrivilegeRelOperation(Long operationId) throws CommonException;

    /**
     * 根据元素编号，查询对应的权限关联元素数据
     *
     * @param elementId 元素编号
     * @return 权限关联元素数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    FleaPrivilegeRel getPrivilegeRelElement(Long elementId) throws CommonException;

    /**
     * 根据资源编号，查询对应的权限关联资源数据
     *
     * @param resourceId 资源编号
     * @return 权限关联资源数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    FleaPrivilegeRel getPrivilegeRelResource(Long resourceId) throws CommonException;

    /**
     * 保存Flea权限关联
     *
     * @param fleaPrivilegeRelPOJO Flea权限关联POJO类对象
     * @return Flea权限关联实体类对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaPrivilegeRel savePrivilegeRel(FleaPrivilegeRelPOJO fleaPrivilegeRelPOJO) throws CommonException;
}