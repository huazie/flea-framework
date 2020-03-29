package com.huazie.frame.auth.base.role.service.impl;

import com.huazie.frame.auth.base.role.dao.interfaces.IFleaRoleDAO;
import com.huazie.frame.auth.base.role.entity.FleaRole;
import com.huazie.frame.auth.base.role.service.interfaces.IFleaRoleSV;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p> Flea角色SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaRoleSV")
public class FleaRoleSVImpl extends AbstractFleaJPASVImpl<FleaRole> implements IFleaRoleSV {

    private final IFleaRoleDAO fleaRoleDao;

    @Autowired
    public FleaRoleSVImpl(@Qualifier("fleaRoleDAO") IFleaRoleDAO fleaRoleDao) {
        this.fleaRoleDao = fleaRoleDao;
    }

    @Override
    protected IAbstractFleaJPADAO<FleaRole> getDAO() {
        return fleaRoleDao;
    }
}