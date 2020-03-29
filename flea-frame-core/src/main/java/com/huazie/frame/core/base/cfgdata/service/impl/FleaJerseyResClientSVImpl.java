package com.huazie.frame.core.base.cfgdata.service.impl;

import com.huazie.frame.core.base.cfgdata.dao.interfaces.IFleaJerseyResClientDAO;
import com.huazie.frame.core.base.cfgdata.entity.FleaJerseyResClient;
import com.huazie.frame.core.base.cfgdata.service.interfaces.IFleaJerseyResClientSV;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p> Flea Jersey 资源客户端服务层实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("resClientSV")
public class FleaJerseyResClientSVImpl extends AbstractFleaJPASVImpl<FleaJerseyResClient> implements IFleaJerseyResClientSV {

    private final IFleaJerseyResClientDAO resClientDAO;

    @Autowired
    public FleaJerseyResClientSVImpl(@Qualifier("resClientDAO") IFleaJerseyResClientDAO resClientDAO) {
        this.resClientDAO = resClientDAO;
    }

    @Override
    @Cacheable(value = "fleajerseyresclient", key = "#clientCode")
    public FleaJerseyResClient getResClient(String clientCode) throws Exception {
        return resClientDAO.getResClient(clientCode);
    }

    @Override
    protected IAbstractFleaJPADAO<FleaJerseyResClient> getDAO() {
        return resClientDAO;
    }
}
