package com.huazie.fleaframework.auth.base.function.service.impl;

import com.huazie.fleaframework.auth.base.function.dao.interfaces.IFleaElementDAO;
import com.huazie.fleaframework.auth.base.function.entity.FleaElement;
import com.huazie.fleaframework.auth.base.function.service.interfaces.IFleaElementSV;
import com.huazie.fleaframework.auth.common.pojo.function.element.FleaElementPOJO;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Flea元素SV层实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@Service("fleaElementSV")
public class FleaElementSVImpl extends AbstractFleaJPASVImpl<FleaElement> implements IFleaElementSV {

    private IFleaElementDAO fleaElementDao;

    @Autowired
    @Qualifier("fleaElementDAO")
    public void setFleaElementDao(IFleaElementDAO fleaElementDao) {
        this.fleaElementDao = fleaElementDao;
    }

    @Override
    public FleaElement queryValidElement(Long elementId) throws CommonException {
        return this.fleaElementDao.queryValidElement(elementId);
    }

    @Override
    public List<FleaElement> queryValidElements(String elementCode, String elementName, Integer elementType, String elementContent) throws CommonException {
        return this.fleaElementDao.queryValidElements(elementCode, elementName, elementType, elementContent);
    }

    @Override
    public List<FleaElement> queryAllValidElements() throws CommonException {
        return this.fleaElementDao.queryValidElements(null, null, null, null);
    }

    @Override
    public FleaElement saveElement(FleaElementPOJO fleaElementPOJO) throws CommonException {
        FleaElement fleaElement = newFleaElement(fleaElementPOJO);
        // 保存Flea元素数据
        this.save(fleaElement);
        return fleaElement;
    }

    /**
     * 新建Flea元素数据
     *
     * @param fleaElementPOJO Flea元素POJO对象
     * @return Flea元素数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    private FleaElement newFleaElement(FleaElementPOJO fleaElementPOJO) throws CommonException {
        // 校验Flea元素POJO对象
        FleaAuthCheck.checkFleaElementPOJO(fleaElementPOJO);

        return new FleaElement(fleaElementPOJO.getElementCode(), fleaElementPOJO.getElementName(),
                fleaElementPOJO.getElementDesc(), fleaElementPOJO.getElementType(),
                fleaElementPOJO.getElementContent(), fleaElementPOJO.getEffectiveDate(),
                fleaElementPOJO.getExpiryDate(), fleaElementPOJO.getRemarks());
    }

    @Override
    protected IAbstractFleaJPADAO<FleaElement> getDAO() {
        return fleaElementDao;
    }
}