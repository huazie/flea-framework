package com.huazie.frame.auth.base.role.service.impl;

import com.huazie.frame.auth.base.role.dao.interfaces.IFleaRoleGroupRelDAO;
import com.huazie.frame.auth.base.role.entity.FleaRoleGroupRel;
import com.huazie.frame.auth.base.role.service.interfaces.IFleaRoleGroupRelSV;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> Flea角色组关联（角色）SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaRoleGroupRelSV")
public class FleaRoleGroupRelSVImpl extends AbstractFleaJPASVImpl<FleaRoleGroupRel> implements IFleaRoleGroupRelSV {

    private IFleaRoleGroupRelDAO fleaRoleGroupRelDao;

    @Autowired
    @Qualifier("fleaRoleGroupRelDAO")
    public void setFleaRoleGroupRelDao(IFleaRoleGroupRelDAO fleaRoleGroupRelDao) {
        this.fleaRoleGroupRelDao = fleaRoleGroupRelDao;
    }

    @Override
    public List<FleaRoleGroupRel> getRoleGroupRelList(Long roleGroupId, String authRelType) throws CommonException {
        return fleaRoleGroupRelDao.getRoleGroupRelList(roleGroupId, authRelType);
    }

    @Override
    protected IAbstractFleaJPADAO<FleaRoleGroupRel> getDAO() {
        return fleaRoleGroupRelDao;
    }
}