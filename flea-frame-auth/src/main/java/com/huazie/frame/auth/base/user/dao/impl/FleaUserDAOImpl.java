package com.huazie.frame.auth.base.user.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDaoImpl;
import com.huazie.frame.auth.base.user.dao.interfaces.IFleaUserDAO;
import com.huazie.frame.auth.base.user.entity.FleaUser;
import org.springframework.stereotype.Repository;

/**
 * <p> Flea用户DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaUserDAO")
public class FleaUserDAOImpl extends FleaAuthDaoImpl<FleaUser> implements IFleaUserDAO {

    //private final static Logger LOGGER = LoggerFactory.getLogger(FleaUserDAOImpl.class);

}
