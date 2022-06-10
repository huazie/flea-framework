package com.huazie.fleaframework.auth.base.privilege.dao.impl;

import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.base.privilege.dao.interfaces.IFleaPrivilegeGroupDAO;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeGroup;
import org.springframework.stereotype.Repository;

/**
 * Flea权限组DAO层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaPrivilegeGroupDAO")
public class FleaPrivilegeGroupDAOImpl extends FleaAuthDAOImpl<FleaPrivilegeGroup> implements IFleaPrivilegeGroupDAO {
}