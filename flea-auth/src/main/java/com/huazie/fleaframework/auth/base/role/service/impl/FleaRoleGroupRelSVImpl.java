package com.huazie.fleaframework.auth.base.role.service.impl;

import com.huazie.fleaframework.auth.base.role.service.interfaces.IFleaRoleGroupRelSV;
import com.huazie.fleaframework.auth.base.role.dao.interfaces.IFleaRoleGroupRelDAO;
import com.huazie.fleaframework.auth.base.role.entity.FleaRoleGroupRel;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
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