package com.huazie.frame.auth.base.privilege.service.impl;

import com.huazie.frame.auth.base.privilege.dao.interfaces.IFleaPrivilegeDAO;
import com.huazie.frame.auth.base.privilege.entity.FleaPrivilege;
import com.huazie.frame.auth.base.privilege.service.interfaces.IFleaPrivilegeSV;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p> Flea权限SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaPrivilegeSV")
public class FleaPrivilegeSVImpl extends AbstractFleaJPASVImpl<FleaPrivilege> implements IFleaPrivilegeSV {

    @Autowired
    @Qualifier("fleaPrivilegeDAO")
    private IFleaPrivilegeDAO fleaPrivilegeDao;

    @Override
    protected IAbstractFleaJPADAO<FleaPrivilege> getDAO() {
        return fleaPrivilegeDao;
    }
}