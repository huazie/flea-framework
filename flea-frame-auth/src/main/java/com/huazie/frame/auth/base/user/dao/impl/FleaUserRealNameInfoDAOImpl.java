package com.huazie.frame.auth.base.user.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDaoImpl;
import com.huazie.frame.auth.base.user.dao.interfaces.IFleaUserRealNameInfoDAO;
import com.huazie.frame.auth.base.user.entity.FleaUserRealNameInfo;
import org.springframework.stereotype.Repository;

/**
 * <p> Flea用户实名信息DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaUserRealNameInfoDAO")
public class FleaUserRealNameInfoDAOImpl extends FleaAuthDaoImpl<FleaUserRealNameInfo> implements IFleaUserRealNameInfoDAO {
}