package com.huazie.fleaframework.auth.base.user.service.impl;

import com.huazie.fleaframework.auth.base.user.dao.interfaces.IFleaLoginLogDAO;
import com.huazie.fleaframework.auth.base.user.entity.FleaLoginLog;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaLoginLogSV;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Flea登录日志SV层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaLoginLogSV")
public class FleaLoginLogSVImpl extends AbstractFleaJPASVImpl<FleaLoginLog> implements IFleaLoginLogSV {

    private IFleaLoginLogDAO fleaLoginLogDao;

    @Autowired
    @Qualifier("fleaLoginLogDAO")
    public void setFleaLoginLogDao(IFleaLoginLogDAO fleaLoginLogDao) {
        this.fleaLoginLogDao = fleaLoginLogDao;
    }

    @Override
    public FleaLoginLog queryLastUserLoginLog(Long accountId) throws CommonException {
        return fleaLoginLogDao.queryLastUserLoginLog(accountId);
    }

    @Override
    protected IAbstractFleaJPADAO<FleaLoginLog> getDAO() {
        return fleaLoginLogDao;
    }
}