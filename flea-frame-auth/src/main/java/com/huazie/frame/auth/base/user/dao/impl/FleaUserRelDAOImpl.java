package com.huazie.frame.auth.base.user.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDAOImpl;
import com.huazie.frame.auth.base.user.dao.interfaces.IFleaUserRelDAO;
import com.huazie.frame.auth.base.user.entity.FleaUserRel;
import org.springframework.stereotype.Repository;

/**
 * <p> Flea用户关联（角色，角色组）DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaUserRelDAO")
public class FleaUserRelDAOImpl extends FleaAuthDAOImpl<FleaUserRel> implements IFleaUserRelDAO {
}