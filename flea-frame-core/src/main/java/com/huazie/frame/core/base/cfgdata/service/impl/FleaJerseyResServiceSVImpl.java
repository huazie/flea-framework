package com.huazie.frame.core.base.cfgdata.service.impl;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.core.base.cfgdata.dao.interfaces.IFleaJerseyResServiceDAO;
import com.huazie.frame.core.base.cfgdata.entity.FleaJerseyResService;
import com.huazie.frame.core.base.cfgdata.service.interfaces.IFleaJerseyResServiceSV;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
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

    private final IFleaJerseyResServiceDAO resServiceDAO;

    @Autowired
    public FleaJerseyResServiceSVImpl(@Qualifier("resServiceDAO") IFleaJerseyResServiceDAO resServiceDAO) {
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
