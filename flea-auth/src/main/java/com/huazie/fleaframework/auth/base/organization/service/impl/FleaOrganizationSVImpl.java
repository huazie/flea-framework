package com.huazie.fleaframework.auth.base.organization.service.impl;

import com.huazie.fleaframework.auth.base.organization.dao.interfaces.IFleaOrganizationDAO;
import com.huazie.fleaframework.auth.base.organization.entity.FleaOrganization;
import com.huazie.fleaframework.auth.base.organization.service.interfaces.IFleaOrganizationSV;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Flea组织SV层实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@Service("fleaOrganizationSV")
public class FleaOrganizationSVImpl extends AbstractFleaJPASVImpl<FleaOrganization> implements IFleaOrganizationSV {

    private IFleaOrganizationDAO fleaOrganizationDAO;

    @Autowired
    @Qualifier("fleaOrganizationDAO")
    public void setFleaOrganizationDAO(IFleaOrganizationDAO fleaOrganizationDAO) {
        this.fleaOrganizationDAO = fleaOrganizationDAO;
    }

    @Override
    public FleaOrganization queryValidOrganization(Long orgId) throws CommonException {
        return fleaOrganizationDAO.queryValidOrganization(orgId);
    }

    @Override
    public List<FleaOrganization> querySubTree(Long orgId) throws CommonException {
        return fleaOrganizationDAO.querySubTree(orgId);
    }

    @Override
    protected IAbstractFleaJPADAO<FleaOrganization> getDAO() {
        return fleaOrganizationDAO;
    }
}
