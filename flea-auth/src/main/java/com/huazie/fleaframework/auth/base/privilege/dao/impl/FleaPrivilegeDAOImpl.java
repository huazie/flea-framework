package com.huazie.fleaframework.auth.base.privilege.dao.impl;

import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.base.privilege.dao.interfaces.IFleaPrivilegeDAO;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilege;
import org.springframework.stereotype.Repository;

/**
 * Flea权限DAO层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaPrivilegeDAO")
public class FleaPrivilegeDAOImpl extends FleaAuthDAOImpl<FleaPrivilege> implements IFleaPrivilegeDAO {
}