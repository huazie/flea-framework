package com.huazie.frame.auth.base.function.service.impl;

import com.huazie.frame.auth.base.function.dao.interfaces.IFleaMenuDAO;
import com.huazie.frame.auth.base.function.entity.FleaMenu;
import com.huazie.frame.auth.base.function.service.interfaces.IFleaMenuSV;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p> Flea菜单SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaMenuSV")
public class FleaMenuSVImpl extends AbstractFleaJPASVImpl<FleaMenu> implements IFleaMenuSV {

    private IFleaMenuDAO fleaMenuDao;

    @Autowired
    @Qualifier("fleaMenuDAO")
    public void setFleaMenuDao(IFleaMenuDAO fleaMenuDao) {
        this.fleaMenuDao = fleaMenuDao;
    }

    @Override
    protected IAbstractFleaJPADAO<FleaMenu> getDAO() {
        return fleaMenuDao;
    }
}