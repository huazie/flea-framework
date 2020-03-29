package com.huazie.frame.auth.base.user.service.impl;

import com.huazie.frame.auth.base.user.dao.interfaces.IFleaRealNameInfoDAO;
import com.huazie.frame.auth.base.user.entity.FleaRealNameInfo;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaRealNameInfoSV;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p> Flea实名信息SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaRealNameInfoSV")
public class FleaRealNameInfoSVImpl extends AbstractFleaJPASVImpl<FleaRealNameInfo> implements IFleaRealNameInfoSV {

    private final IFleaRealNameInfoDAO fleaRealNameInfoDao;

    @Autowired
    public FleaRealNameInfoSVImpl(@Qualifier("fleaRealNameInfoDAO") IFleaRealNameInfoDAO fleaRealNameInfoDao) {
        this.fleaRealNameInfoDao = fleaRealNameInfoDao;
    }

    @Override
    protected IAbstractFleaJPADAO<FleaRealNameInfo> getDAO() {
        return fleaRealNameInfoDao;
    }
}