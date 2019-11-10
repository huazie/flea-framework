package com.huazie.frame.auth.base.role.service.impl;

import com.huazie.frame.auth.base.role.dao.interfaces.IFleaRoleRelDAO;
import com.huazie.frame.auth.base.role.entity.FleaRoleRel;
import com.huazie.frame.auth.base.role.service.interfaces.IFleaRoleRelSV;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p> Flea角色关联（角色， 权限， 权限组）SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaRoleRelSV")
public class FleaRoleRelSVImpl extends AbstractFleaJPASVImpl<FleaRoleRel> implements IFleaRoleRelSV {

    @Autowired
    @Qualifier("fleaRoleRelDAO")
    private IFleaRoleRelDAO fleaRoleRelDao;

    @Override
    protected IAbstractFleaJPADAO<FleaRoleRel> getDAO() {
        return fleaRoleRelDao;
    }
}