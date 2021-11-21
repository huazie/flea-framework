package com.huazie.fleaframework.auth.base.privilege.service.interfaces;

import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeRel;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeRelPOJO;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;

/**
 * <p> Flea权限关联（菜单， 操作， 元素）SV层接口定义 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaPrivilegeRelSV extends IAbstractFleaJPASV<FleaPrivilegeRel> {

    /**
     * <p> 获取指定权限编号【privilegeId】关联的指定授权关联类型【authRelType】的权限关联信息 </p>
     *
     * @param privilegeId 权限编号
     * @param authRelType 授权关联类型
     * @return 权限关联信息
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaPrivilegeRel> getPrivilegeRelList(Long privilegeId, String authRelType) throws CommonException;

    /**
     * <p> 获取指定菜单编号【menuId】关联的授权类型【rel_type = PRIVILEGE_REL_MENU】权限关联信息 </p>
     *
     * @param menuId 菜单编号
     * @return 权限关联信息
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaPrivilegeRel getPrivilegeRelMenu(Long menuId) throws CommonException;

    /**
     * <p> 保存Flea权限关联 </p>
     *
     * @param fleaPrivilegeRelPOJO Flea权限关联POJO类对象
     * @return Flea权限关联实体类对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaPrivilegeRel savePrivilegeRel(FleaPrivilegeRelPOJO fleaPrivilegeRelPOJO) throws CommonException;
}