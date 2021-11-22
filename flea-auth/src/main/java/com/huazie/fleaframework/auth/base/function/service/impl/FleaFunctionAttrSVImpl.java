package com.huazie.fleaframework.auth.base.function.service.impl;

import com.huazie.fleaframework.auth.base.function.service.interfaces.IFleaFunctionAttrSV;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.auth.common.exception.FleaAuthCommonException;
import com.huazie.fleaframework.auth.common.pojo.function.attr.FleaFunctionAttrPOJO;
import com.huazie.fleaframework.auth.base.function.dao.interfaces.IFleaFunctionAttrDAO;
import com.huazie.fleaframework.auth.base.function.entity.FleaFunctionAttr;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
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
    public FleaFunctionAttr saveFunctionAttr(FleaFunctionAttrPOJO fleaFunctionAttrPOJO) throws CommonException {
        FleaFunctionAttr fleaFunctionAttr = newFleaFunctionAttr(fleaFunctionAttrPOJO);
        // 保存Flea功能扩展属性
        this.save(fleaFunctionAttr);
        return fleaFunctionAttr;
    }

    /**
     * <p> 新建一个Flea功能扩展属性实体对象 </p>
     *
     * @param fleaFunctionAttrPOJO flea功能扩展属性POJO类
     * @return Flea功能扩展属性实体对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private FleaFunctionAttr newFleaFunctionAttr(FleaFunctionAttrPOJO fleaFunctionAttrPOJO) throws CommonException {

        // 校验Flea功能扩展属性POJO类对象是否为空
        // ERROR-AUTH-COMMON0000000001 【{0}】不能为空
        ObjectUtils.checkEmpty(fleaFunctionAttrPOJO, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", FleaFunctionAttrPOJO.class.getSimpleName());

        // 校验功能编号不能为空
        Long functionId = fleaFunctionAttrPOJO.getFunctionId();
        ObjectUtils.checkEmpty(functionId, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", FleaAuthEntityConstants.FunctionEntityConstants.E_FUNCTION_ID);

        // 校验功能类型不能为空
        String functionType = fleaFunctionAttrPOJO.getFunctionType();
        StringUtils.checkBlank(functionType, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", FleaAuthEntityConstants.FunctionEntityConstants.E_FUNCTION_TYPE);

        // 校验属性码不能为空
        String attrCode = fleaFunctionAttrPOJO.getAttrCode();
        StringUtils.checkBlank(attrCode, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", FleaAuthEntityConstants.E_ATTR_CODE);

        return new FleaFunctionAttr(functionId, functionType, attrCode,
                fleaFunctionAttrPOJO.getAttrValue(),
                fleaFunctionAttrPOJO.getAttrDesc(),
                fleaFunctionAttrPOJO.getEffectiveDate(),
                fleaFunctionAttrPOJO.getExpiryDate(),
                fleaFunctionAttrPOJO.getRemarks());
    }

    @Override
    protected IAbstractFleaJPADAO<FleaFunctionAttr> getDAO() {
        return fleaFunctionAttrDao;
    }
}