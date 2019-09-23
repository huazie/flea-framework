package com.huazie.frame.auth.base.user.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDAOImpl;
import com.huazie.frame.auth.base.user.dao.interfaces.IFleaLoginLogDAO;
import com.huazie.frame.auth.base.user.entity.FleaLoginLog;
import org.springframework.stereotype.Repository;

/**
 * <p> Flea登录日志DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaLoginLogDAO")
public class FleaLoginLogDAOImpl extends FleaAuthDAOImpl<FleaLoginLog> implements IFleaLoginLogDAO {
}