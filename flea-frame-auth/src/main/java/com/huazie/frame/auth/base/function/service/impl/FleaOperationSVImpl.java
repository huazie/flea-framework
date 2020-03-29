package com.huazie.frame.auth.base.function.service.impl;

import com.huazie.frame.auth.base.function.dao.interfaces.IFleaOperationDAO;
import com.huazie.frame.auth.base.function.entity.FleaOperation;
import com.huazie.frame.auth.base.function.service.interfaces.IFleaOperationSV;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p> Flea操作SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaOperationSV")
public class FleaOperationSVImpl extends AbstractFleaJPASVImpl<FleaOperation> implements IFleaOperationSV {

    private final IFleaOperationDAO fleaOperationDao;

    @Autowired
    public FleaOperationSVImpl(@Qualifier("fleaOperationDAO") IFleaOperationDAO fleaOperationDao) {
        this.fleaOperationDao = fleaOperationDao;
    }

    @Override
    protected IAbstractFleaJPADAO<FleaOperation> getDAO() {
        return fleaOperationDao;
    }
}