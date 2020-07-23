package com.huazie.frame.auth.base.role.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDAOImpl;
import com.huazie.frame.auth.base.role.dao.interfaces.IFleaRoleGroupRelDAO;
import com.huazie.frame.auth.base.role.entity.FleaRoleGroupRel;
import org.springframework.stereotype.Repository;

/**
 * <p> Flea角色组关联（角色）DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaRoleGroupRelDAO")
public class FleaRoleGroupRelDAOImpl extends FleaAuthDAOImpl<FleaRoleGroupRel> implements IFleaRoleGroupRelDAO {
}