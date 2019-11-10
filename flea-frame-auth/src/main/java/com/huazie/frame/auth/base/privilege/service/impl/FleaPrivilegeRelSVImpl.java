package com.huazie.frame.auth.base.privilege.service.impl;

import com.huazie.frame.auth.base.privilege.dao.interfaces.IFleaPrivilegeRelDAO;
import com.huazie.frame.auth.base.privilege.entity.FleaPrivilegeRel;
import com.huazie.frame.auth.base.privilege.service.interfaces.IFleaPrivilegeRelSV;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p> Flea权限关联（菜单， 操作， 页面元素）SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaPrivilegeRelSV")
public class FleaPrivilegeRelSVImpl extends AbstractFleaJPASVImpl<FleaPrivilegeRel> implements IFleaPrivilegeRelSV {

    @Autowired
    @Qualifier("fleaPrivilegeRelDAO")
    private IFleaPrivilegeRelDAO fleaPrivilegeRelDao;

    @Override
    protected IAbstractFleaJPADAO<FleaPrivilegeRel> getDAO() {
        return fleaPrivilegeRelDao;
    }
}