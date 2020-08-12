package com.huazie.frame.auth.base.user.service.impl;

import com.huazie.frame.auth.base.user.dao.interfaces.IFleaUserGroupRelDAO;
import com.huazie.frame.auth.base.user.entity.FleaUserGroupRel;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaUserGroupRelSV;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> Flea用户组关联（角色，角色组）SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaUserGroupRelSV")
public class FleaUserGroupRelSVImpl extends AbstractFleaJPASVImpl<FleaUserGroupRel> implements IFleaUserGroupRelSV {

    private IFleaUserGroupRelDAO fleaUserGroupRelDao;

    @Autowired
    @Qualifier("fleaUserGroupRelDAO")
    public void setFleaUserGroupRelDao(IFleaUserGroupRelDAO fleaUserGroupRelDao) {
        this.fleaUserGroupRelDao = fleaUserGroupRelDao;
    }

    @Override
    public List<FleaUserGroupRel> getUserGroupRelList(Long userGroupId, String authRelType) throws CommonException {
        return fleaUserGroupRelDao.getUserGroupRelList(userGroupId, authRelType);
    }

    @Override
    protected IAbstractFleaJPADAO<FleaUserGroupRel> getDAO() {
        return fleaUserGroupRelDao;
    }
}