package com.huazie.fleaframework.auth.base.function.service.impl;

import com.huazie.fleaframework.auth.base.function.dao.interfaces.IFleaFunctionAttrDAO;
import com.huazie.fleaframework.auth.base.function.entity.FleaFunctionAttr;
import com.huazie.fleaframework.auth.base.function.service.interfaces.IFleaFunctionAttrSV;
import com.huazie.fleaframework.auth.common.FleaAuthConstants;
import com.huazie.fleaframework.auth.common.pojo.function.attr.FleaFunctionAttrPOJO;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Flea功能扩展属性SV层实现类
 *
 * @author huazie
 * @version 2.0.0
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
    public FleaFunctionAttr queryValidFunctionAttr(Long attrId, String functionType) throws CommonException {
        return this.fleaFunctionAttrDao.queryValidFunctionAttr(attrId, functionType);
    }

    @Override
    public List<FleaFunctionAttr> queryValidFunctionAttrs(Long functionId, String functionType, String attrCode) throws CommonException {
        return this.fleaFunctionAttrDao.queryValidFunctionAttrs(functionId, functionType, attrCode, null, true);
    }

    @Override
    public List<FleaFunctionAttr> queryValidFunctionAttrs(Long functionId, String functionType, String attrCode, String attrValue, boolean isAttrValueEqual) throws CommonException {
        return this.fleaFunctionAttrDao.queryValidFunctionAttrs(functionId, functionType, attrCode, attrValue, isAttrValueEqual);
    }

    @Override
    public List<FleaFunctionAttr> querySystemRelFunctionAttrs(Long functionId, String functionType, Long systemAccountId) throws CommonException {
        return this.fleaFunctionAttrDao.queryValidFunctionAttrs(functionId, functionType, FleaAuthConstants.AttrCodeConstants.ATTR_CODE_SYSTEM_IN_USE, StringUtils.valueOf(systemAccountId), true);
    }

    @Override
    public boolean isExistSystemRelFunction(Long functionId, String functionType, Long systemAccountId) throws CommonException {
        List<FleaFunctionAttr> fleaFunctionAttrList = this.querySystemRelFunctionAttrs(functionId, functionType, systemAccountId);
        return CollectionUtils.isNotEmpty(fleaFunctionAttrList);
    }

    @Override
    public List<Long> querySystemRelFunctionIds(String functionType, Long systemAccountId) throws CommonException {
        // 获取指定功能类型，指定属性码，指定属性值的功能属性数据
        List<FleaFunctionAttr> fleaFunctionAttrList = this.fleaFunctionAttrDao.queryValidFunctionAttrs(null, functionType,
                FleaAuthConstants.AttrCodeConstants.ATTR_CODE_SYSTEM_IN_USE, StringUtils.valueOf(systemAccountId), true);
        List<Long> functionIdList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(functionIdList)) {
            for (FleaFunctionAttr fleaFunctionAttr : fleaFunctionAttrList) {
                if (ObjectUtils.isEmpty(fleaFunctionAttr)) continue;
                functionIdList.add(fleaFunctionAttr.getFunctionId());
            }
        }
        return functionIdList;
    }

    @Override
    public List<FleaFunctionAttr> saveFunctionAttrs(List<FleaFunctionAttrPOJO> fleaFunctionAttrPOJOList) throws CommonException {
        List<FleaFunctionAttr> fleaFunctionAttrList = newFleaFunctionAttrList(fleaFunctionAttrPOJOList);
        // 保存Flea功能扩展属性信息集
        this.batchSave(fleaFunctionAttrList);
        return fleaFunctionAttrList;
    }

    @Override
    public FleaFunctionAttr saveFunctionAttr(FleaFunctionAttrPOJO fleaFunctionAttrPOJO) throws CommonException {
        FleaFunctionAttr fleaFunctionAttr = newFleaFunctionAttr(fleaFunctionAttrPOJO);
        // 保存Flea功能扩展属性信息
        this.save(fleaFunctionAttr);
        return fleaFunctionAttr;
    }

    /**
     * 新建Flea功能扩展属性信息集合
     *
     * @param fleaFunctionAttrPOJOList flea功能扩展属性POJO类对象集合
     * @return Flea功能扩展属性信息
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private List<FleaFunctionAttr> newFleaFunctionAttrList(List<FleaFunctionAttrPOJO> fleaFunctionAttrPOJOList) throws CommonException {
        List<FleaFunctionAttr> fleaFunctionAttrList = null;
        if (CollectionUtils.isNotEmpty(fleaFunctionAttrPOJOList)) {
            fleaFunctionAttrList = new ArrayList<>();
            for (FleaFunctionAttrPOJO fleaFunctionAttrPOJO : fleaFunctionAttrPOJOList) {
                fleaFunctionAttrList.add(newFleaFunctionAttr(fleaFunctionAttrPOJO));
            }
        }
        return fleaFunctionAttrList;
    }

    /**
     * 新建Flea功能扩展属性信息
     *
     * @param fleaFunctionAttrPOJO flea功能扩展属性POJO对象
     * @return Flea功能扩展属性信息
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private FleaFunctionAttr newFleaFunctionAttr(FleaFunctionAttrPOJO fleaFunctionAttrPOJO) throws CommonException {
        // 校验Flea功能扩展属性信息
        FleaAuthCheck.checkFleaFunctionAttrPOJO(fleaFunctionAttrPOJO);

        // 校验功能编号不能为空
        FleaAuthCheck.checkFunctionId(fleaFunctionAttrPOJO.getFunctionId());

        FleaFunctionAttr fleaFunctionAttr = new FleaFunctionAttr(fleaFunctionAttrPOJO.getFunctionId(), fleaFunctionAttrPOJO.getFunctionType(),
                fleaFunctionAttrPOJO.getAttrCode(), fleaFunctionAttrPOJO.getAttrValue(), fleaFunctionAttrPOJO.getAttrDesc(),
                fleaFunctionAttrPOJO.getEffectiveDate(), fleaFunctionAttrPOJO.getExpiryDate(), fleaFunctionAttrPOJO.getRemarks());
        // 分表场景，主动获取功能属性编号【主键】
        fleaFunctionAttr.setAttrId((Long) this.getFleaNextValue(fleaFunctionAttr));
        return fleaFunctionAttr;
    }

    @Override
    protected IAbstractFleaJPADAO<FleaFunctionAttr> getDAO() {
        return fleaFunctionAttrDao;
    }
}