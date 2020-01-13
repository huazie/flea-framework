package com.huazie.frame.auth.base.privilege.service.impl;

import com.huazie.frame.auth.base.privilege.dao.interfaces.IFleaPrivilegeGroupDAO;
import com.huazie.frame.auth.base.privilege.entity.FleaPrivilegeGroup;
import com.huazie.frame.auth.base.privilege.service.interfaces.IFleaPrivilegeGroupSV;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p> Flea权限组SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaPrivilegeGroupSV")
public class FleaPrivilegeGroupSVImpl extends AbstractFleaJPASVImpl<FleaPrivilegeGroup> implements IFleaPrivilegeGroupSV {

    private final IFleaPrivilegeGroupDAO fleaPrivilegeGroupDao;

    @Autowired
    public FleaPrivilegeGroupSVImpl(@Qualifier("fleaPrivilegeGroupDAO") IFleaPrivilegeGroupDAO fleaPrivilegeGroupDao) {
        this.fleaPrivilegeGroupDao = fleaPrivilegeGroupDao;
    }

    @Override
    protected IAbstractFleaJPADAO<FleaPrivilegeGroup> getDAO() {
        return fleaPrivilegeGroupDao;
    }
}