package com.huazie.frame.auth.base.user.service.impl;

import com.huazie.frame.auth.base.user.dao.interfaces.IFleaUserAttrDAO;
import com.huazie.frame.auth.base.user.entity.FleaUserAttr;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaUserAttrSV;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p> Flea用户属性SV层接口实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaUserAttrSV")
public class FleaUserAttrSVImpl extends AbstractFleaJPASVImpl<FleaUserAttr> implements IFleaUserAttrSV {

    @Autowired
    @Qualifier("fleaUserAttrDAO")
    private IFleaUserAttrDAO fleaUserAttrDao;

    @Override
    protected IAbstractFleaJPADAO<FleaUserAttr> getDAO() {
        return fleaUserAttrDao;
    }
}
