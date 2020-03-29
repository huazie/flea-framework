package com.huazie.frame.auth.base.user.service.impl;

import com.huazie.frame.auth.base.user.dao.interfaces.IFleaLoginLogDAO;
import com.huazie.frame.auth.base.user.entity.FleaLoginLog;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaLoginLogSV;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p> Flea登录日志SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaLoginLogSV")
public class FleaLoginLogSVImpl extends AbstractFleaJPASVImpl<FleaLoginLog> implements IFleaLoginLogSV {

    private final IFleaLoginLogDAO fleaLoginLogDao;

    @Autowired
    public FleaLoginLogSVImpl(@Qualifier("fleaLoginLogDAO") IFleaLoginLogDAO fleaLoginLogDao) {
        this.fleaLoginLogDao = fleaLoginLogDao;
    }

    @Override
    protected IAbstractFleaJPADAO<FleaLoginLog> getDAO() {
        return fleaLoginLogDao;
    }
}