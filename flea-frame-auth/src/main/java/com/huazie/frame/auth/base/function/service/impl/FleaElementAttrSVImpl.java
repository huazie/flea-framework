package com.huazie.frame.auth.base.function.service.impl;

import com.huazie.frame.auth.base.function.dao.interfaces.IFleaElementAttrDAO;
import com.huazie.frame.auth.base.function.entity.FleaElementAttr;
import com.huazie.frame.auth.base.function.service.interfaces.IFleaElementAttrSV;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p> Flea元素扩展属性SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaElementAttrSV")
public class FleaElementAttrSVImpl extends AbstractFleaJPASVImpl<FleaElementAttr> implements IFleaElementAttrSV {

    @Autowired
    @Qualifier("fleaElementAttrDAO")
    private IFleaElementAttrDAO fleaElementAttrDao;

    @Override
    protected IAbstractFleaJPADAO<FleaElementAttr> getDAO() {
        return fleaElementAttrDao;
    }
}