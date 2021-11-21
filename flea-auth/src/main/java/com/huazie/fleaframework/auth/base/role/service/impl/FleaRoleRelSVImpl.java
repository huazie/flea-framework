package com.huazie.fleaframework.auth.base.role.service.impl;

import com.huazie.fleaframework.auth.base.role.service.interfaces.IFleaRoleRelSV;
import com.huazie.fleaframework.auth.base.role.dao.interfaces.IFleaRoleRelDAO;
import com.huazie.fleaframework.auth.base.role.entity.FleaRoleRel;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> Flea角色关联（角色， 权限， 权限组）SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaRoleRelSV")
public class FleaRoleRelSVImpl extends AbstractFleaJPASVImpl<FleaRoleRel> implements IFleaRoleRelSV {

    private IFleaRoleRelDAO fleaRoleRelDao;

    @Autowired
    @Qualifier("fleaRoleRelDAO")
    public void setFleaRoleRelDao(IFleaRoleRelDAO fleaRoleRelDao) {
        this.fleaRoleRelDao = fleaRoleRelDao;
    }

    @Override
    public List<FleaRoleRel> getRoleRelList(Long roleId, String authRelType) throws CommonException {
        return fleaRoleRelDao.getRoleRelList(roleId, authRelType);
    }

    @Override
    protected IAbstractFleaJPADAO<FleaRoleRel> getDAO() {
        return fleaRoleRelDao;
    }
}