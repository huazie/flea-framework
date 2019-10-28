package com.huazie.frame.auth.base.user.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDAOImpl;
import com.huazie.frame.auth.base.user.dao.interfaces.IFleaRealNameInfoDAO;
import com.huazie.frame.auth.base.user.entity.FleaRealNameInfo;
import org.springframework.stereotype.Repository;

/**
 * <p> Flea实名信息DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaRealNameInfoDAO")
public class FleaRealNameInfoDAOImpl extends FleaAuthDAOImpl<FleaRealNameInfo> implements IFleaRealNameInfoDAO {
}