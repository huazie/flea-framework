package com.huazie.frame.auth.base.user.service.impl;

import com.huazie.frame.auth.base.user.dao.interfaces.IFleaUserGroupRelDAO;
import com.huazie.frame.auth.base.user.entity.FleaUserGroupRel;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaUserGroupRelSV;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p> Flea用户组关联（角色，角色组）SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaUserGroupRelSV")
public class FleaUserGroupRelSVImpl extends AbstractFleaJPASVImpl<FleaUserGroupRel> implements IFleaUserGroupRelSV {

    private final IFleaUserGroupRelDAO fleaUserGroupRelDao;

    @Autowired
    public FleaUserGroupRelSVImpl(@Qualifier("fleaUserGroupRelDAO") IFleaUserGroupRelDAO fleaUserGroupRelDao) {
        this.fleaUserGroupRelDao = fleaUserGroupRelDao;
    }

    @Override
    protected IAbstractFleaJPADAO<FleaUserGroupRel> getDAO() {
        return fleaUserGroupRelDao;
    }
}