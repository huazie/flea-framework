package com.huazie.frame.auth.base.role.service.impl;

import com.huazie.frame.auth.base.role.dao.interfaces.IFleaRoleGroupRelDAO;
import com.huazie.frame.auth.base.role.entity.FleaRoleGroupRel;
import com.huazie.frame.auth.base.role.service.interfaces.IFleaRoleGroupRelSV;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p> Flea角色组关联（角色，用户，用户组）SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaRoleGroupRelSV")
public class FleaRoleGroupRelSVImpl extends AbstractFleaJPASVImpl<FleaRoleGroupRel> implements IFleaRoleGroupRelSV {

    @Autowired
    @Qualifier("fleaRoleGroupRelDAO")
    private IFleaRoleGroupRelDAO fleaRoleGroupRelDao;

    @Override
    protected IAbstractFleaJPADAO<FleaRoleGroupRel> getDAO() {
        return fleaRoleGroupRelDao;
    }
}