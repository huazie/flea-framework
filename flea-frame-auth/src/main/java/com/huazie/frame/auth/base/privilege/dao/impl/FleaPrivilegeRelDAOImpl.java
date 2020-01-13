package com.huazie.frame.auth.base.privilege.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDAOImpl;
import com.huazie.frame.auth.base.privilege.dao.interfaces.IFleaPrivilegeRelDAO;
import com.huazie.frame.auth.base.privilege.entity.FleaPrivilegeRel;
import org.springframework.stereotype.Repository;

/**
 * <p> Flea权限关联（菜单， 操作， 元素）DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaPrivilegeRelDAO")
public class FleaPrivilegeRelDAOImpl extends FleaAuthDAOImpl<FleaPrivilegeRel> implements IFleaPrivilegeRelDAO {
}