package com.huazie.frame.auth.base.user.service.impl;

import com.huazie.frame.auth.base.user.dao.interfaces.IFleaAccountAttrDAO;
import com.huazie.frame.auth.base.user.entity.FleaAccountAttr;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaAccountAttrSV;
import com.huazie.frame.auth.common.exception.FleaAuthCommonException;
import com.huazie.frame.auth.common.pojo.account.attr.FleaAccountAttrPOJO;
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
 * <p> Flea帐户属性SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaAccountAttrSV")
public class FleaAccountAttrSVImpl extends AbstractFleaJPASVImpl<FleaAccountAttr> implements IFleaAccountAttrSV {

    private final IFleaAccountAttrDAO fleaAccountAttrDao;

    @Autowired
    public FleaAccountAttrSVImpl(@Qualifier("fleaAccountAttrDAO") IFleaAccountAttrDAO fleaAccountAttrDao) {
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
            fleaAccountAttrList = new ArrayList<FleaAccountAttr>();
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
        ObjectUtils.checkEmpty(fleaAccountAttrPOJO, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", new String[]{"FleaAccountAttrInfo"});

        // 校验账户编号是否为空
        Long accountId = fleaAccountAttrPOJO.getAccountId();
        ObjectUtils.checkEmpty(accountId, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", new String[]{"accountId"});

        // 校验扩展属性码是否为空
        String attrCode = fleaAccountAttrPOJO.getAttrCode();
        StringUtils.checkBlank(attrCode, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", new String[]{"attrCode"});

        return new FleaAccountAttr(accountId, attrCode,
                fleaAccountAttrPOJO.getAttrValue(),
                fleaAccountAttrPOJO.getEffectiveDate(),
                fleaAccountAttrPOJO.getExpiryDate(),
                fleaAccountAttrPOJO.getRemarks());
    }

    @Override
    protected IAbstractFleaJPADAO<FleaAccountAttr> getDAO() {
        return fleaAccountAttrDao;
    }

}