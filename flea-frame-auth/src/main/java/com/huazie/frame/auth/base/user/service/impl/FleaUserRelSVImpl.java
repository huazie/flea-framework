package com.huazie.frame.auth.base.user.service.impl;

import com.huazie.frame.auth.base.user.dao.interfaces.IFleaUserRelDAO;
import com.huazie.frame.auth.base.user.entity.FleaUserRel;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaUserRelSV;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p> Flea用户关联（角色，角色组）SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaUserRelSV")
public class FleaUserRelSVImpl extends AbstractFleaJPASVImpl<FleaUserRel> implements IFleaUserRelSV {

    private final IFleaUserRelDAO fleaUserRelDao;

    @Autowired
    public FleaUserRelSVImpl(@Qualifier("fleaUserRelDAO") IFleaUserRelDAO fleaUserRelDao) {
        this.fleaUserRelDao = fleaUserRelDao;
    }

    @Override
    protected IAbstractFleaJPADAO<FleaUserRel> getDAO() {
        return fleaUserRelDao;
    }
}