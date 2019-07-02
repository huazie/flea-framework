package com.huazie.frame.core.base.cfgdata.service.impl;

import com.huazie.frame.core.base.cfgdata.dao.interfaces.IFleaJerseyResServiceDAO;
import com.huazie.frame.core.base.cfgdata.entity.FleaJerseyResService;
import com.huazie.frame.core.base.cfgdata.service.interfaces.IFleaJerseyI18nErrorMappingSV;
import com.huazie.frame.core.base.cfgdata.service.interfaces.IFleaJerseyResServiceSV;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>  </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("resServiceSV")
public class FleaJerseyResServiceSVImpl extends AbstractFleaJPASVImpl<FleaJerseyResService> implements IFleaJerseyResServiceSV {

    @Autowired
    @Qualifier("resServiceDAO")
    private IFleaJerseyResServiceDAO fleaJerseyResServiceDAO;

    @Override
    @Cacheable(value = "fleajerseyresservice", key = "#serviceCode + '_' + #resourceCode")
    public FleaJerseyResService getResService(String serviceCode, String resourceCode) throws Exception {
        return fleaJerseyResServiceDAO.getResService(serviceCode, resourceCode);
    }

    @Override
    protected IAbstractFleaJPADAO<FleaJerseyResService> getDAO() {
        return fleaJerseyResServiceDAO;
    }
}
