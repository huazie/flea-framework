package com.huazie.frame.auth.base.user.service.impl;

import com.huazie.frame.auth.base.user.dao.interfaces.IFleaAccountDAO;
import com.huazie.frame.auth.base.user.entity.FleaAccount;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaAccountSV;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p> Flea帐户信息SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaAccountSV")
public class FleaAccountSVImpl extends AbstractFleaJPASVImpl<FleaAccount> implements IFleaAccountSV {

    @Autowired
    @Qualifier("fleaAccountDAO")
    private IFleaAccountDAO fleaAccountDao;

    @Override
    protected IAbstractFleaJPADAO<FleaAccount> getDAO() {
        return fleaAccountDao;
    }
}