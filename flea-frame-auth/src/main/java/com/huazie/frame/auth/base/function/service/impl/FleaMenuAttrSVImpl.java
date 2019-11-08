package com.huazie.frame.auth.base.function.service.impl;

import com.huazie.frame.auth.base.function.dao.interfaces.IFleaMenuAttrDAO;
import com.huazie.frame.auth.base.function.entity.FleaMenuAttr;
import com.huazie.frame.auth.base.function.service.interfaces.IFleaMenuAttrSV;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p> Flea菜单扩展属性SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaMenuAttrSV")
public class FleaMenuAttrSVImpl extends AbstractFleaJPASVImpl<FleaMenuAttr> implements IFleaMenuAttrSV {

    @Autowired
    @Qualifier("fleaMenuAttrDAO")
    private IFleaMenuAttrDAO fleaMenuAttrDao;

    @Override
    protected IAbstractFleaJPADAO<FleaMenuAttr> getDAO() {
        return fleaMenuAttrDao;
    }
}