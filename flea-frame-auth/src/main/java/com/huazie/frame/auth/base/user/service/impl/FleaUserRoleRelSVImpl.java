package com.huazie.frame.auth.base.user.service.impl;

import com.huazie.frame.auth.base.user.dao.interfaces.IFleaUserRoleRelDAO;
import com.huazie.frame.auth.base.user.entity.FleaUserRoleRel;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaUserRoleRelSV;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p> Flea用户和角色关联SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaUserRoleRelSV")
public class FleaUserRoleRelSVImpl extends AbstractFleaJPASVImpl<FleaUserRoleRel> implements IFleaUserRoleRelSV {

    @Autowired
    @Qualifier("fleaUserRoleRelDAO")
    private IFleaUserRoleRelDAO fleaUserRoleRelDao;

    @Override
    protected IAbstractFleaJPADAO<FleaUserRoleRel> getDAO() {
        return fleaUserRoleRelDao;
    }
}