package com.huazie.fleaframework.core.base.cfgdata.service.impl;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.core.base.cfgdata.dao.interfaces.IFleaJerseyResourceDAO;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyResource;
import com.huazie.fleaframework.core.base.cfgdata.service.interfaces.IFleaJerseyResourceSV;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Flea Jersey 资源SV层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("resourceSV")
public class FleaJerseyResourceSVImpl extends AbstractFleaJPASVImpl<FleaJerseyResource> implements IFleaJerseyResourceSV {

    private IFleaJerseyResourceDAO fleaJerseyResourceDAO;

    @Autowired
    @Qualifier("resourceDAO")
    public void setFleaJerseyResourceDAO(IFleaJerseyResourceDAO fleaJerseyResourceDAO) {
        this.fleaJerseyResourceDAO = fleaJerseyResourceDAO;
    }

    @Override
    public FleaJerseyResource getResource(String resourceCode) throws CommonException {
        FleaJerseyResource resource = null;
        if (StringUtils.isNotBlank(resourceCode)) {
            List<FleaJerseyResource> resourceList = fleaJerseyResourceDAO.getResource(resourceCode);
            resource = CollectionUtils.getFirstElement(resourceList, FleaJerseyResource.class);
        }
        return resource;
    }

    @Override
    public List<String> getResourcePackages() throws CommonException {
        return fleaJerseyResourceDAO.getResourcePackages();
    }

    @Override
    protected IAbstractFleaJPADAO<FleaJerseyResource> getDAO() {
        return fleaJerseyResourceDAO;
    }
}
