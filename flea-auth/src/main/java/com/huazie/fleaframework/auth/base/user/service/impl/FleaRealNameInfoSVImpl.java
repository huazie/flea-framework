package com.huazie.fleaframework.auth.base.user.service.impl;

import com.huazie.fleaframework.auth.base.user.dao.interfaces.IFleaRealNameInfoDAO;
import com.huazie.fleaframework.auth.base.user.entity.FleaRealNameInfo;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaRealNameInfoSV;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
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

    private IFleaRealNameInfoDAO fleaRealNameInfoDao;

    @Autowired
    @Qualifier("fleaRealNameInfoDAO")
    public void setFleaRealNameInfoDao(IFleaRealNameInfoDAO fleaRealNameInfoDao) {
        this.fleaRealNameInfoDao = fleaRealNameInfoDao;
    }

    @Override
    public FleaRealNameInfo queryValidRealNameInfo(Long realNameId) throws CommonException {
        return fleaRealNameInfoDao.queryValidRealNameInfo(realNameId);
    }

    @Override
    protected IAbstractFleaJPADAO<FleaRealNameInfo> getDAO() {
        return fleaRealNameInfoDao;
    }
}