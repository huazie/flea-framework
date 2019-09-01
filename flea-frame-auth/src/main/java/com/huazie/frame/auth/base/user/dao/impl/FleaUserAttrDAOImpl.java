package com.huazie.frame.auth.base.user.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDaoImpl;
import com.huazie.frame.auth.base.user.dao.interfaces.IFleaUserAttrDAO;
import com.huazie.frame.auth.base.user.entity.FleaUserAttr;
import org.springframework.stereotype.Repository;

/**
 * <p> Flea用户属性DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaUserAttrDAO")
public class FleaUserAttrDAOImpl extends FleaAuthDaoImpl<FleaUserAttr> implements IFleaUserAttrDAO {
}