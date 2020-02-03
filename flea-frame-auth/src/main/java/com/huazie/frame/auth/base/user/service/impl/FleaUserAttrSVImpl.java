package com.huazie.frame.auth.base.user.service.impl;

import com.huazie.frame.auth.base.user.dao.interfaces.IFleaUserAttrDAO;
import com.huazie.frame.auth.base.user.entity.FleaUserAttr;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaUserAttrSV;
import com.huazie.frame.auth.common.exception.FleaAuthCommonException;
import com.huazie.frame.auth.common.pojo.user.attr.FleaUserAttrInfo;
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
 * <p> Flea用户扩展属性SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaUserAttrSV")
public class FleaUserAttrSVImpl extends AbstractFleaJPASVImpl<FleaUserAttr> implements IFleaUserAttrSV {

    private final IFleaUserAttrDAO fleaUserAttrDao;

    @Autowired
    public FleaUserAttrSVImpl(@Qualifier("fleaUserAttrDAO") IFleaUserAttrDAO fleaUserAttrDao) {
        this.fleaUserAttrDao = fleaUserAttrDao;
    }

    @Override
    public FleaUserAttr saveFleaUserAttr(FleaUserAttrInfo fleaUserAttrInfo) throws CommonException {

        FleaUserAttr fleaUserAttr = newFleaUserAttr(fleaUserAttrInfo);
        // 保存Flea用户扩展属性
        fleaUserAttrDao.save(fleaUserAttr);

        return fleaUserAttr;
    }

    @Override
    public List<FleaUserAttr> saveFleaUserAttrs(List<FleaUserAttrInfo> fleaUserAttrInfoList) throws CommonException {

        List<FleaUserAttr> fleaUserAttrList = null;
        if (CollectionUtils.isNotEmpty(fleaUserAttrInfoList)) {
            fleaUserAttrList = new ArrayList<FleaUserAttr>();
            for (FleaUserAttrInfo fleaUserAttrInfo : fleaUserAttrInfoList) {
                if (ObjectUtils.isNotEmpty(fleaUserAttrInfo)) {
                    FleaUserAttr fleaUserAttr = newFleaUserAttr(fleaUserAttrInfo);
                    fleaUserAttrList.add(fleaUserAttr);
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
     * <p> 新建一个Flea用户扩展属性实体对象 </p>
     *
     * @param fleaUserAttrInfo Flea用户扩展属性POJO类对象
     * @return Flea用户扩展属性实体对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private FleaUserAttr newFleaUserAttr(FleaUserAttrInfo fleaUserAttrInfo) throws CommonException {

        // 校验Flea用户扩展属性POJO类对象是否为空
        // ERROR-AUTH-COMMON0000000001 【{0}】不能为空
        ObjectUtils.checkEmpty(fleaUserAttrInfo, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", new String[]{"FleaUserAttrInfo"});

        // 校验用户编号是否为空
        Long userId = fleaUserAttrInfo.getUserId();
        ObjectUtils.checkEmpty(userId, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", new String[]{"userId"});

        // 校验扩展属性码是否为空
        String attrCode = fleaUserAttrInfo.getAttrCode();
        StringUtils.checkBlank(attrCode, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", new String[]{"attrCode"});

        return new FleaUserAttr(userId, attrCode,
                fleaUserAttrInfo.getAttrValue(),
                fleaUserAttrInfo.getEffectiveDate(),
                fleaUserAttrInfo.getExpiryDate(),
                fleaUserAttrInfo.getRemarks());
    }

    @Override
    protected IAbstractFleaJPADAO<FleaUserAttr> getDAO() {
        return fleaUserAttrDao;
    }

}