package com.huazie.frame.auth.base.user.service.impl;

import com.huazie.frame.auth.base.user.dao.interfaces.IFleaUserGroupDAO;
import com.huazie.frame.auth.base.user.entity.FleaUserGroup;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaUserGroupSV;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p> Flea用户组SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaUserGroupSV")
public class FleaUserGroupSVImpl extends AbstractFleaJPASVImpl<FleaUserGroup> implements IFleaUserGroupSV {

    @Autowired
    @Qualifier("fleaUserGroupDAO")
    private IFleaUserGroupDAO fleaUserGroupDao;

    @Override
    protected IAbstractFleaJPADAO<FleaUserGroup> getDAO() {
        return fleaUserGroupDao;
    }
}