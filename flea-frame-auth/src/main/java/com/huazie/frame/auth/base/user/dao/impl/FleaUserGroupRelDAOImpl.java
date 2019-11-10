package com.huazie.frame.auth.base.user.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDAOImpl;
import com.huazie.frame.auth.base.user.dao.interfaces.IFleaUserGroupRelDAO;
import com.huazie.frame.auth.base.user.entity.FleaUserGroupRel;
import org.springframework.stereotype.Repository;

/**
 * <p> Flea用户组关联（用户，角色）DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaUserGroupRelDAO")
public class FleaUserGroupRelDAOImpl extends FleaAuthDAOImpl<FleaUserGroupRel> implements IFleaUserGroupRelDAO {
}