package com.huazie.frame.auth.base.user.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDAOImpl;
import com.huazie.frame.auth.base.user.dao.interfaces.IFleaUserRoleRelDAO;
import com.huazie.frame.auth.base.user.entity.FleaUserRoleRel;
import org.springframework.stereotype.Repository;

/**
 * <p> Flea用户和角色关联DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaUserRoleRelDAO")
public class FleaUserRoleRelDAOImpl extends FleaAuthDAOImpl<FleaUserRoleRel> implements IFleaUserRoleRelDAO {
}