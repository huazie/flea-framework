package com.huazie.frame.auth.base.user.service.impl;

import com.huazie.frame.auth.base.user.dao.interfaces.IFleaUserDAO;
import com.huazie.frame.auth.base.user.entity.FleaUser;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaUserSV;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p> Flea用户SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaUserSV")
public class FleaUserSVImpl extends AbstractFleaJPASVImpl<FleaUser> implements IFleaUserSV {

    private final IFleaUserDAO fleaUserDao;

    @Autowired
    public FleaUserSVImpl(@Qualifier("fleaUserDAO") IFleaUserDAO fleaUserDao) {
        this.fleaUserDao = fleaUserDao;
    }

    @Override
    public FleaUser saveFleaUser(String accountCode, Long groupId, Integer userState, String remarks) throws CommonException {

        FleaUser fleaUser = new FleaUser(accountCode, groupId, userState, remarks);

        // 保存Flea用户
        fleaUserDao.save(fleaUser);

        return fleaUser;
    }

    @Override
    protected IAbstractFleaJPADAO<FleaUser> getDAO() {
        return fleaUserDao;
    }
}