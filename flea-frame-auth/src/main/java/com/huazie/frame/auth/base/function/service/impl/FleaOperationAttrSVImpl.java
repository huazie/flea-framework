package com.huazie.frame.auth.base.function.service.impl;

import com.huazie.frame.auth.base.function.dao.interfaces.IFleaOperationAttrDAO;
import com.huazie.frame.auth.base.function.entity.FleaOperationAttr;
import com.huazie.frame.auth.base.function.service.interfaces.IFleaOperationAttrSV;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p> Flea操作扩展属性SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaOperationAttrSV")
public class FleaOperationAttrSVImpl extends AbstractFleaJPASVImpl<FleaOperationAttr> implements IFleaOperationAttrSV {

    @Autowired
    @Qualifier("fleaOperationAttrDAO")
    private IFleaOperationAttrDAO fleaOperationAttrDao;

    @Override
    protected IAbstractFleaJPADAO<FleaOperationAttr> getDAO() {
        return fleaOperationAttrDao;
    }
}