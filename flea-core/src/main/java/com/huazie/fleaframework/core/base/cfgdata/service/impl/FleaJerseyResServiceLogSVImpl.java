package com.huazie.fleaframework.core.base.cfgdata.service.impl;

import com.huazie.fleaframework.core.base.cfgdata.service.interfaces.IFleaJerseyResServiceLogSV;
import com.huazie.fleaframework.core.base.cfgdata.dao.interfaces.IFleaJerseyResServiceLogDAO;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyResServiceLog;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p> Flea Jersey资源服务调用日志SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaJerseyResServiceLogSV")
public class FleaJerseyResServiceLogSVImpl extends AbstractFleaJPASVImpl<FleaJerseyResServiceLog> implements IFleaJerseyResServiceLogSV {

    private IFleaJerseyResServiceLogDAO fleaJerseyResServiceLogDao;

    @Autowired
    @Qualifier("fleaJerseyResServiceLogDAO")
    public void setFleaJerseyResServiceLogDao(IFleaJerseyResServiceLogDAO fleaJerseyResServiceLogDao) {
        this.fleaJerseyResServiceLogDao = fleaJerseyResServiceLogDao;
    }

    @Override
    protected IAbstractFleaJPADAO<FleaJerseyResServiceLog> getDAO() {
        return fleaJerseyResServiceLogDao;
    }
}