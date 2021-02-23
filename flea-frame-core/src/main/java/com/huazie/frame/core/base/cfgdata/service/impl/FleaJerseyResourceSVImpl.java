package com.huazie.frame.core.base.cfgdata.service.impl;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.core.base.cfgdata.dao.interfaces.IFleaJerseyResourceDAO;
import com.huazie.frame.core.base.cfgdata.entity.FleaJerseyResource;
import com.huazie.frame.core.base.cfgdata.service.interfaces.IFleaJerseyResourceSV;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> Flea Jersey 资源SV实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("resourceSV")
public class FleaJerseyResourceSVImpl extends AbstractFleaJPASVImpl<FleaJerseyResource> implements IFleaJerseyResourceSV {

    private final IFleaJerseyResourceDAO fleaJerseyResourceDAO;

    @Autowired
    public FleaJerseyResourceSVImpl(@Qualifier("resourceDAO") IFleaJerseyResourceDAO fleaJerseyResourceDAO) {
        this.fleaJerseyResourceDAO = fleaJerseyResourceDAO;
    }

    @Override
    public FleaJerseyResource getResource(String resourceCode) throws CommonException {
        FleaJerseyResource resource = null;
        if (StringUtils.isNotBlank(resourceCode)) {
            List<FleaJerseyResource> resourceList = fleaJerseyResourceDAO.getResource(resourceCode);
            if (CollectionUtils.isNotEmpty(resourceList)) {
                resource = resourceList.get(0);
            }
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
