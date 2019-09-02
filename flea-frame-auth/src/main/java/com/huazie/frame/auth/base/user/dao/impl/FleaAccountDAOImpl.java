package com.huazie.frame.auth.base.user.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDaoImpl;
import com.huazie.frame.auth.base.user.dao.interfaces.IFleaAccountDAO;
import com.huazie.frame.auth.base.user.entity.FleaAccount;
import org.springframework.stereotype.Repository;

/**
 * <p> Flea账户信息DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaAccountDAO")
public class FleaAccountDAOImpl extends FleaAuthDaoImpl<FleaAccount> implements IFleaAccountDAO {
}