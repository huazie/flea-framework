package com.huazie.fleaframework.auth.base.function.service.impl;

import com.huazie.fleaframework.auth.base.function.dao.interfaces.IFleaOperationDAO;
import com.huazie.fleaframework.auth.base.function.entity.FleaOperation;
import com.huazie.fleaframework.auth.base.function.service.interfaces.IFleaOperationSV;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Flea操作SV层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaOperationSV")
public class FleaOperationSVImpl extends AbstractFleaJPASVImpl<FleaOperation> implements IFleaOperationSV {

    private IFleaOperationDAO fleaOperationDao;

    @Autowired
    @Qualifier("fleaOperationDAO")
    public void setFleaOperationDao(IFleaOperationDAO fleaOperationDao) {
        this.fleaOperationDao = fleaOperationDao;
    }

    @Override
    protected IAbstractFleaJPADAO<FleaOperation> getDAO() {
        return fleaOperationDao;
    }
}