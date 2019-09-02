package com.huazie.frame.auth.base.user.service.impl;

import com.huazie.frame.auth.base.user.dao.interfaces.IFleaUserRealNameInfoDAO;
import com.huazie.frame.auth.base.user.entity.FleaUserRealNameInfo;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaUserRealNameInfoSV;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p> Flea用户实名信息SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaUserRealNameInfoSV")
public class FleaUserRealNameInfoSVImpl extends AbstractFleaJPASVImpl<FleaUserRealNameInfo> implements IFleaUserRealNameInfoSV {

    @Autowired
    @Qualifier("fleaUserRealNameInfoDAO")
    private IFleaUserRealNameInfoDAO fleaUserRealNameInfoDao;

    @Override
    protected IAbstractFleaJPADAO<FleaUserRealNameInfo> getDAO() {
        return fleaUserRealNameInfoDao;
    }
}