package com.huazie.fleaframework.core.base.cfgdata.service.impl;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.core.base.cfgdata.service.interfaces.IFleaJerseyResServiceSV;
import com.huazie.fleaframework.core.base.cfgdata.dao.interfaces.IFleaJerseyResServiceDAO;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyResService;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p> Flea Jersey资源服务 服务层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("resServiceSV")
public class FleaJerseyResServiceSVImpl extends AbstractFleaJPASVImpl<FleaJerseyResService> implements IFleaJerseyResServiceSV {

    private IFleaJerseyResServiceDAO resServiceDAO;

    @Autowired
    @Qualifier("resServiceDAO")
    public void setResServiceDAO(IFleaJerseyResServiceDAO resServiceDAO) {
        this.resServiceDAO = resServiceDAO;
    }

    @Override
    public FleaJerseyResService getResService(String serviceCode, String resourceCode) throws CommonException {
        return resServiceDAO.getResService(serviceCode, resourceCode);
    }

    @Override
    protected IAbstractFleaJPADAO<FleaJerseyResService> getDAO() {
        return resServiceDAO;
    }
}
