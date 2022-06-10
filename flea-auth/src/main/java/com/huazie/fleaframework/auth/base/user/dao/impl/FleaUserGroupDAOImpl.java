package com.huazie.fleaframework.auth.base.user.dao.impl;

import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.base.user.dao.interfaces.IFleaUserGroupDAO;
import com.huazie.fleaframework.auth.base.user.entity.FleaUserGroup;
import org.springframework.stereotype.Repository;

/**
 * Flea用户组DAO层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaUserGroupDAO")
public class FleaUserGroupDAOImpl extends FleaAuthDAOImpl<FleaUserGroup> implements IFleaUserGroupDAO {
}