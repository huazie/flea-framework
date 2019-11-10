package com.huazie.frame.auth.base.privilege.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDAOImpl;
import com.huazie.frame.auth.base.privilege.dao.interfaces.IFleaPrivilegeDAO;
import com.huazie.frame.auth.base.privilege.entity.FleaPrivilege;
import org.springframework.stereotype.Repository;

/**
 * <p> Flea权限DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaPrivilegeDAO")
public class FleaPrivilegeDAOImpl extends FleaAuthDAOImpl<FleaPrivilege> implements IFleaPrivilegeDAO {
}