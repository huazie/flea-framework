package com.huazie.fleaframework.auth.base.function.service.impl;

import com.huazie.fleaframework.auth.base.function.dao.interfaces.IFleaElementDAO;
import com.huazie.fleaframework.auth.base.function.entity.FleaElement;
import com.huazie.fleaframework.auth.base.function.service.interfaces.IFleaElementSV;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p> Flea元素SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaElementSV")
public class FleaElementSVImpl extends AbstractFleaJPASVImpl<FleaElement> implements IFleaElementSV {

    private IFleaElementDAO fleaElementDao;

    @Autowired
    @Qualifier("fleaElementDAO")
    public void setFleaElementDao(IFleaElementDAO fleaElementDao) {
        this.fleaElementDao = fleaElementDao;
    }

    @Override
    protected IAbstractFleaJPADAO<FleaElement> getDAO() {
        return fleaElementDao;
    }
}