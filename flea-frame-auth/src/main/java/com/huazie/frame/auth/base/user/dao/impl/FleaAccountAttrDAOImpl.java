package com.huazie.frame.auth.base.user.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDAOImpl;
import com.huazie.frame.auth.base.user.dao.interfaces.IFleaAccountAttrDAO;
import com.huazie.frame.auth.base.user.entity.FleaAccountAttr;
import org.springframework.stereotype.Repository;

/**
 * <p> Flea帐户属性DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaAccountAttrDAO")
public class FleaAccountAttrDAOImpl extends FleaAuthDAOImpl<FleaAccountAttr> implements IFleaAccountAttrDAO {
}