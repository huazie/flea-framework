package com.huazie.frame.auth.base.privilege.service.impl;

import com.huazie.frame.auth.base.privilege.dao.interfaces.IFleaPrivilegeGroupRelDAO;
import com.huazie.frame.auth.base.privilege.entity.FleaPrivilegeGroupRel;
import com.huazie.frame.auth.base.privilege.service.interfaces.IFleaPrivilegeGroupRelSV;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> Flea权限组关联（权限）SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaPrivilegeGroupRelSV")
public class FleaPrivilegeGroupRelSVImpl extends AbstractFleaJPASVImpl<FleaPrivilegeGroupRel> implements IFleaPrivilegeGroupRelSV {

    private IFleaPrivilegeGroupRelDAO fleaPrivilegeGroupRelDao;

    @Autowired
    @Qualifier("fleaPrivilegeGroupRelDAO")
    public void setFleaPrivilegeGroupRelDao(IFleaPrivilegeGroupRelDAO fleaPrivilegeGroupRelDao) {
        this.fleaPrivilegeGroupRelDao = fleaPrivilegeGroupRelDao;
    }

    @Override
    public List<FleaPrivilegeGroupRel> getPrivilegeGroupRelList(Long privilegeGroupId, String authRelType) throws CommonException {
        return fleaPrivilegeGroupRelDao.getPrivilegeGroupRelList(privilegeGroupId, authRelType);
    }

    @Override
    protected IAbstractFleaJPADAO<FleaPrivilegeGroupRel> getDAO() {
        return fleaPrivilegeGroupRelDao;
    }
}