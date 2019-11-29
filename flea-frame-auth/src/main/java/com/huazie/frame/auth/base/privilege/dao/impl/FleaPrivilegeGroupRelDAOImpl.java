package com.huazie.frame.auth.base.privilege.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDAOImpl;
import com.huazie.frame.auth.base.privilege.dao.interfaces.IFleaPrivilegeGroupRelDAO;
import com.huazie.frame.auth.base.privilege.entity.FleaPrivilegeGroupRel;
import org.springframework.stereotype.Repository;

/**
 * <p> Flea权限组关联（权限）DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaPrivilegeGroupRelDAO")
public class FleaPrivilegeGroupRelDAOImpl extends FleaAuthDAOImpl<FleaPrivilegeGroupRel> implements IFleaPrivilegeGroupRelDAO {
}