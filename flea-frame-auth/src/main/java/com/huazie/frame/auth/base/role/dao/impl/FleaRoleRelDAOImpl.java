package com.huazie.frame.auth.base.role.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDAOImpl;
import com.huazie.frame.auth.base.role.dao.interfaces.IFleaRoleRelDAO;
import com.huazie.frame.auth.base.role.entity.FleaRoleRel;
import org.springframework.stereotype.Repository;

/**
 * <p> Flea角色关联（角色， 权限， 权限组）DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaRoleRelDAO")
public class FleaRoleRelDAOImpl extends FleaAuthDAOImpl<FleaRoleRel> implements IFleaRoleRelDAO {
}