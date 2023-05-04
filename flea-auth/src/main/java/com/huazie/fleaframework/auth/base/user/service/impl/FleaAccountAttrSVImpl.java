package com.huazie.fleaframework.auth.base.user.service.impl;

import com.huazie.fleaframework.auth.base.user.dao.interfaces.IFleaAccountAttrDAO;
import com.huazie.fleaframework.auth.base.user.entity.FleaAccountAttr;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaAccountAttrSV;
import com.huazie.fleaframework.auth.common.pojo.user.attr.FleaAccountAttrPOJO;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Flea账户扩展属性SV层实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@Service("fleaAccountAttrSV")
public class FleaAccountAttrSVImpl extends AbstractFleaJPASVImpl<FleaAccountAttr> implements IFleaAccountAttrSV {

    private IFleaAccountAttrDAO fleaAccountAttrDao;

    @Autowired
    @Qualifier("fleaAccountAttrDAO")
    public void setFleaAccountAttrDao(IFleaAccountAttrDAO fleaAccountAttrDao) {
        this.fleaAccountAttrDao = fleaAccountAttrDao;
    }

    @Override
    public List<FleaAccountAttr> queryValidAccountAttrs(Long accountId) throws CommonException {
        return fleaAccountAttrDao.queryValidAccountAttrs(accountId);
    }

    @Override
    public FleaAccountAttr saveFleaAccountAttr(FleaAccountAttrPOJO fleaAccountAttrPOJO) throws CommonException {
        FleaAccountAttr fleaAccountAttr = newFleaAccountAttr(fleaAccountAttrPOJO);
        // 保存Flea账户扩展属性信息
        fleaAccountAttrDao.save(fleaAccountAttr);
        return fleaAccountAttr;
    }

    @Override
    public List<FleaAccountAttr> saveFleaAccountAttrs(List<FleaAccountAttrPOJO> fleaAccountAttrPOJOList) throws CommonException {

        List<FleaAccountAttr> fleaAccountAttrList = null;
        if (CollectionUtils.isNotEmpty(fleaAccountAttrPOJOList)) {
            fleaAccountAttrList = new ArrayList<>();
            for (FleaAccountAttrPOJO fleaAccountAttrPOJO : fleaAccountAttrPOJOList) {
                fleaAccountAttrList.add(newFleaAccountAttr(fleaAccountAttrPOJO));
            }
        }

        // 保存Flea账户扩展属性List集合
        if (CollectionUtils.isNotEmpty(fleaAccountAttrList)) {
            fleaAccountAttrDao.batchSave(fleaAccountAttrList);
        }

        return fleaAccountAttrList;
    }

    /**
     * 新建Flea账户扩展属性信息
     *
     * @param fleaAccountAttrPOJO Flea账户扩展属性POJO类对象
     * @return Flea账户扩展属性信息
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private FleaAccountAttr newFleaAccountAttr(FleaAccountAttrPOJO fleaAccountAttrPOJO) throws CommonException {
        // 校验Flea账户扩展属性POJO对象
        FleaAuthCheck.checkFleaAccountAttrPOJO(fleaAccountAttrPOJO);

        return new FleaAccountAttr(fleaAccountAttrPOJO.getAccountId(),
                fleaAccountAttrPOJO.getAttrCode(),
                fleaAccountAttrPOJO.getAttrValue(),
                fleaAccountAttrPOJO.getAttrDesc(),
                fleaAccountAttrPOJO.getEffectiveDate(),
                fleaAccountAttrPOJO.getExpiryDate(),
                fleaAccountAttrPOJO.getRemarks());
    }

    @Override
    protected IAbstractFleaJPADAO<FleaAccountAttr> getDAO() {
        return fleaAccountAttrDao;
    }

}