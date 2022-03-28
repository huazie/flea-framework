package com.huazie.fleaframework.auth.base.privilege.dao.interfaces;

import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeRel;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

import java.util.List;

/**
 * Flea权限关联（菜单， 操作， 元素）DAO层接口
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaPrivilegeRelDAO extends IAbstractFleaJPADAO<FleaPrivilegeRel> {

    /**
     * 获取指定权限编号【privilegeId】关联的指定授权关联类型【authRelType】的权限关联信息
     *
     * @param privilegeId 权限编号
     * @param authRelType 授权关联类型
     * @return 权限关联信息
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaPrivilegeRel> getPrivilegeRelList(Long privilegeId, String authRelType) throws CommonException;

    /**
     * 获取指定菜单编号【menuId】关联的授权类型【rel_type = PRIVILEGE_REL_MENU】权限关联信息
     *
     * @param menuId 菜单编号
     * @return 权限关联信息
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaPrivilegeRel getPrivilegeRelMenu(Long menuId) throws CommonException;
}