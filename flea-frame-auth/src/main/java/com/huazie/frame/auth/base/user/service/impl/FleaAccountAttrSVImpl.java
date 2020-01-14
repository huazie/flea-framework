package com.huazie.frame.auth.base.user.service.impl;

import com.huazie.frame.auth.base.user.dao.interfaces.IFleaAccountAttrDAO;
import com.huazie.frame.auth.base.user.entity.FleaAccountAttr;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaAccountAttrSV;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p> Flea帐户属性SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaAccountAttrSV")
public class FleaAccountAttrSVImpl extends AbstractFleaJPASVImpl<FleaAccountAttr> implements IFleaAccountAttrSV {

    private final IFleaAccountAttrDAO fleaAccountAttrDao;

    @Autowired
    public FleaAccountAttrSVImpl(@Qualifier("fleaAccountAttrDAO") IFleaAccountAttrDAO fleaAccountAttrDao) {
        this.fleaAccountAttrDao = fleaAccountAttrDao;
    }

    @Override
    protected IAbstractFleaJPADAO<FleaAccountAttr> getDAO() {
        return fleaAccountAttrDao;
    }
}