package com.huazie.frame.auth.base.privilege.dao.interfaces;

import com.huazie.frame.auth.base.privilege.entity.FleaPrivilegeRel;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

import java.util.List;

/**
 * <p> Flea权限关联（菜单， 操作， 元素）DAO层接口 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaPrivilegeRelDAO extends IAbstractFleaJPADAO<FleaPrivilegeRel> {

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
}