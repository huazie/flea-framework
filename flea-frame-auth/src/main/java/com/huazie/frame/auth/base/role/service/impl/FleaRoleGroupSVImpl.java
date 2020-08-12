package com.huazie.frame.auth.base.role.service.impl;

import com.huazie.frame.auth.base.role.dao.interfaces.IFleaRoleGroupDAO;
import com.huazie.frame.auth.base.role.entity.FleaRoleGroup;
import com.huazie.frame.auth.base.role.service.interfaces.IFleaRoleGroupSV;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p> Flea角色组SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaRoleGroupSV")
public class FleaRoleGroupSVImpl extends AbstractFleaJPASVImpl<FleaRoleGroup> implements IFleaRoleGroupSV {

    private IFleaRoleGroupDAO fleaRoleGroupDao;

    @Autowired
    @Qualifier("fleaRoleGroupDAO")
    public void setFleaRoleGroupDao(IFleaRoleGroupDAO fleaRoleGroupDao) {
        this.fleaRoleGroupDao = fleaRoleGroupDao;
    }

    @Override
    protected IAbstractFleaJPADAO<FleaRoleGroup> getDAO() {
        return fleaRoleGroupDao;
    }
}