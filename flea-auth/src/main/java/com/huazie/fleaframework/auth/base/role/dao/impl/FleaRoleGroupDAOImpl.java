package com.huazie.fleaframework.auth.base.role.dao.impl;

import com.huazie.fleaframework.auth.base.role.entity.FleaRoleGroup;
import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.base.role.dao.interfaces.IFleaRoleGroupDAO;
import org.springframework.stereotype.Repository;

/**
 * <p> Flea角色组DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaRoleGroupDAO")
public class FleaRoleGroupDAOImpl extends FleaAuthDAOImpl<FleaRoleGroup> implements IFleaRoleGroupDAO {
}