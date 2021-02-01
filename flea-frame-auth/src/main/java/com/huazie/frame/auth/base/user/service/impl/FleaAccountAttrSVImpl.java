package com.huazie.frame.auth.base.user.service.impl;

import com.huazie.frame.auth.base.user.dao.interfaces.IFleaAccountAttrDAO;
import com.huazie.frame.auth.base.user.entity.FleaAccountAttr;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaAccountAttrSV;
import com.huazie.frame.auth.common.FleaAuthEntityConstants;
import com.huazie.frame.auth.common.exception.FleaAuthCommonException;
import com.huazie.frame.auth.common.pojo.user.attr.FleaAccountAttrPOJO;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> Flea账户属性SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
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
    public FleaAccountAttr saveFleaAccountAttr(FleaAccountAttrPOJO fleaAccountAttrPOJO) throws CommonException {

        FleaAccountAttr fleaAccountAttr = newFleaAccountAttr(fleaAccountAttrPOJO);
        // 保存Flea账户扩展属性
        fleaAccountAttrDao.save(fleaAccountAttr);

        return fleaAccountAttr;
    }

    @Override
    public List<FleaAccountAttr> saveFleaAccountAttrs(List<FleaAccountAttrPOJO> fleaAccountAttrPOJOList) throws CommonException {

        List<FleaAccountAttr> fleaAccountAttrList = null;
        if (CollectionUtils.isNotEmpty(fleaAccountAttrPOJOList)) {
            fleaAccountAttrList = new ArrayList<>();
            for (FleaAccountAttrPOJO fleaAccountAttrPOJO : fleaAccountAttrPOJOList) {
                if (ObjectUtils.isNotEmpty(fleaAccountAttrPOJO)) {
                    FleaAccountAttr fleaAccountAttr = newFleaAccountAttr(fleaAccountAttrPOJO);
                    fleaAccountAttrList.add(fleaAccountAttr);
                }
            }
        }

        // 保存Flea账户扩展属性List集合
        if (CollectionUtils.isNotEmpty(fleaAccountAttrList)) {
            fleaAccountAttrDao.batchSave(fleaAccountAttrList);
        }

        return fleaAccountAttrList;
    }

    /**
     * <p> 新建一个Flea账户扩展属性实体对象 </p>
     *
     * @param fleaAccountAttrPOJO Flea账户扩展属性POJO类对象
     * @return Flea账户扩展属性实体对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private FleaAccountAttr newFleaAccountAttr(FleaAccountAttrPOJO fleaAccountAttrPOJO) throws CommonException {

        // 校验Flea账户扩展属性POJO类对象是否为空
        // ERROR-AUTH-COMMON0000000001 【{0}】不能为空
        ObjectUtils.checkEmpty(fleaAccountAttrPOJO, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", FleaAccountAttrPOJO.class.getSimpleName());

        // 校验账户编号是否为空
        Long accountId = fleaAccountAttrPOJO.getAccountId();
        ObjectUtils.checkEmpty(accountId, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", FleaAuthEntityConstants.UserEntityConstants.E_ACCOUNT_ID);

        // 校验扩展属性码是否为空
        String attrCode = fleaAccountAttrPOJO.getAttrCode();
        StringUtils.checkBlank(attrCode, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", FleaAuthEntityConstants.E_ATTR_CODE);

        return new FleaAccountAttr(accountId, attrCode,
                fleaAccountAttrPOJO.getAttrValue(),
                fleaAccountAttrPOJO.getEffectiveDate(),
                fleaAccountAttrPOJO.getExpiryDate(),
                fleaAccountAttrPOJO.getRemarks());
    }

    @Override
    public List<FleaAccountAttr> queryValidAccountAttrs(Long accountId) throws CommonException {
        return fleaAccountAttrDao.queryValidAccountAttrs(accountId);
    }

    @Override
    protected IAbstractFleaJPADAO<FleaAccountAttr> getDAO() {
        return fleaAccountAttrDao;
    }

}