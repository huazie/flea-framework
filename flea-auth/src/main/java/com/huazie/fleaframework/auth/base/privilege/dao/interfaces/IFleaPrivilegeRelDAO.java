package com.huazie.fleaframework.auth.base.privilege.dao.interfaces;

import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeRel;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

import java.util.List;

/**
 * Flea权限关联功能【菜单、操作、元素和资源】DAO层接口
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public interface IFleaPrivilegeRelDAO extends IAbstractFleaJPADAO<FleaPrivilegeRel> {

    /**
     * 根据权限编号和授权类型，查询在用的权限关联功能数据。
     *
     * @param privilegeId 权限编号
     * @param authRelType 授权关联类型
     * @return 权限关联数据
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
     * 根据功能编号和授权类型，查询在用的权限关联功能数据。
     * <p> 其中功能包括菜单、操作、元素和资源等。
     *
     * @param functionId  功能编号【即关联编号 REL_ID】
     * @param authRelType 授权关联类型
     * @return 权限关联数据
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaPrivilegeRel getPrivilegeRelFunction(Long functionId, String authRelType) throws CommonException;
}