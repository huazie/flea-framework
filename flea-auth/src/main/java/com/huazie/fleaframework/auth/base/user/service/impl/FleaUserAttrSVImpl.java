package com.huazie.fleaframework.auth.base.user.service.impl;

import com.huazie.fleaframework.auth.base.user.dao.interfaces.IFleaUserAttrDAO;
import com.huazie.fleaframework.auth.base.user.entity.FleaUserAttr;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaUserAttrSV;
import com.huazie.fleaframework.auth.common.pojo.user.attr.FleaUserAttrPOJO;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Flea用户扩展属性SV层实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@Service("fleaUserAttrSV")
public class FleaUserAttrSVImpl extends AbstractFleaJPASVImpl<FleaUserAttr> implements IFleaUserAttrSV {

    private IFleaUserAttrDAO fleaUserAttrDao;

    @Autowired
    @Qualifier("fleaUserAttrDAO")
    public void setFleaUserAttrDao(IFleaUserAttrDAO fleaUserAttrDao) {
        this.fleaUserAttrDao = fleaUserAttrDao;
    }

    @Override
    public List<FleaUserAttr> queryValidUserAttrs(Long userId) throws CommonException {
        return fleaUserAttrDao.queryValidUserAttrs(userId);
    }

    @Override
    public FleaUserAttr saveFleaUserAttr(FleaUserAttrPOJO fleaUserAttrPOJO) throws CommonException {
        FleaUserAttr fleaUserAttr = newFleaUserAttr(fleaUserAttrPOJO);
        // 保存Flea用户扩展属性信息
        fleaUserAttrDao.save(fleaUserAttr);
        return fleaUserAttr;
    }

    @Override
    public List<FleaUserAttr> saveFleaUserAttrs(List<FleaUserAttrPOJO> fleaUserAttrPOJOList) throws CommonException {

        List<FleaUserAttr> fleaUserAttrList = null;
        if (CollectionUtils.isNotEmpty(fleaUserAttrPOJOList)) {
            fleaUserAttrList = new ArrayList<>();
            for (FleaUserAttrPOJO fleaUserAttrPOJO : fleaUserAttrPOJOList) {
                if (ObjectUtils.isNotEmpty(fleaUserAttrPOJO)) {
                    fleaUserAttrList.add(newFleaUserAttr(fleaUserAttrPOJO));
                }
            }
        }

        // 保存Flea用户扩展属性List集合
        if (CollectionUtils.isNotEmpty(fleaUserAttrList)) {
            fleaUserAttrDao.batchSave(fleaUserAttrList);
        }

        return fleaUserAttrList;
    }

    /**
     * 新建一个Flea用户扩展属性信息
     *
     * @param fleaUserAttrPOJO Flea用户扩展属性POJO类对象
     * @return Flea用户扩展属性信息
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private FleaUserAttr newFleaUserAttr(FleaUserAttrPOJO fleaUserAttrPOJO) throws CommonException {
        // 校验Flea用户扩展属性POJO对象
        FleaAuthCheck.checkFleaUserAttrPOJO(fleaUserAttrPOJO);

        return new FleaUserAttr(fleaUserAttrPOJO.getUserId(),
                fleaUserAttrPOJO.getAttrCode(),
                fleaUserAttrPOJO.getAttrValue(),
                fleaUserAttrPOJO.getAttrDesc(),
                fleaUserAttrPOJO.getEffectiveDate(),
                fleaUserAttrPOJO.getExpiryDate(),
                fleaUserAttrPOJO.getRemarks());
    }

    @Override
    protected IAbstractFleaJPADAO<FleaUserAttr> getDAO() {
        return fleaUserAttrDao;
    }

}