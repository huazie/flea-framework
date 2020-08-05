package com.huazie.frame.auth.base.function.service.impl;

import com.huazie.frame.auth.base.function.dao.interfaces.IFleaFunctionAttrDAO;
import com.huazie.frame.auth.base.function.entity.FleaFunctionAttr;
import com.huazie.frame.auth.base.function.service.interfaces.IFleaFunctionAttrSV;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> Flea功能扩展属性SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaFunctionAttrSV")
public class FleaFunctionAttrSVImpl extends AbstractFleaJPASVImpl<FleaFunctionAttr> implements IFleaFunctionAttrSV {

    private IFleaFunctionAttrDAO fleaFunctionAttrDao;

    @Autowired
    @Qualifier("fleaFunctionAttrDAO")
    public void setFleaFunctionAttrDao(IFleaFunctionAttrDAO fleaFunctionAttrDao) {
        this.fleaFunctionAttrDao = fleaFunctionAttrDao;
    }

    @Override
    public List<FleaFunctionAttr> getFunctionAttrList(Long functionId, String functionType, String attrCode) throws CommonException {
        return fleaFunctionAttrDao.getFunctionAttrList(functionId, functionType, attrCode);
    }

    @Override
    protected IAbstractFleaJPADAO<FleaFunctionAttr> getDAO() {
        return fleaFunctionAttrDao;
    }
}