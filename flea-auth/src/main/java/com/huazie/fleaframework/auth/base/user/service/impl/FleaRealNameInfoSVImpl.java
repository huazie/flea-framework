package com.huazie.fleaframework.auth.base.user.service.impl;

import com.huazie.fleaframework.auth.base.user.dao.interfaces.IFleaRealNameInfoDAO;
import com.huazie.fleaframework.auth.base.user.entity.FleaRealNameInfo;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaRealNameInfoSV;
import com.huazie.fleaframework.auth.common.pojo.user.FleaRealNameInfoPOJO;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Flea实名信息SV层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaRealNameInfoSV")
public class FleaRealNameInfoSVImpl extends AbstractFleaJPASVImpl<FleaRealNameInfo> implements IFleaRealNameInfoSV {

    private IFleaRealNameInfoDAO fleaRealNameInfoDao;

    @Autowired
    @Qualifier("fleaRealNameInfoDAO")
    public void setFleaRealNameInfoDao(IFleaRealNameInfoDAO fleaRealNameInfoDao) {
        this.fleaRealNameInfoDao = fleaRealNameInfoDao;
    }

    @Override
    public FleaRealNameInfo queryValidRealNameInfo(Long realNameId) throws CommonException {
        return fleaRealNameInfoDao.queryValidRealNameInfo(realNameId);
    }

    @Override
    public FleaRealNameInfo saveRealNameInfo(FleaRealNameInfoPOJO fleaRealNameInfoPOJO) throws CommonException {
        FleaRealNameInfo fleaRealNameInfo = newFleaRealNameInfo(fleaRealNameInfoPOJO);
        // 保存Flea实名信息
        this.save(fleaRealNameInfo);
        return fleaRealNameInfo;
    }

    /**
     * 新建Flea实名信息
     *
     * @param fleaRealNameInfoPOJO Flea实名信息POJO对象
     * @return Flea实名信息
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    private FleaRealNameInfo newFleaRealNameInfo(FleaRealNameInfoPOJO fleaRealNameInfoPOJO) throws CommonException {
        // 校验Flea实名信息POJO对象
        FleaAuthCheck.checkFleaRealNameInfoPOJO(fleaRealNameInfoPOJO);

        return new FleaRealNameInfo(fleaRealNameInfoPOJO.getCertType(),
                fleaRealNameInfoPOJO.getCertCode(),
                fleaRealNameInfoPOJO.getCertName(),
                fleaRealNameInfoPOJO.getCertAddress(),
                fleaRealNameInfoPOJO.getEffectiveDate(),
                fleaRealNameInfoPOJO.getExpiryDate(),
                fleaRealNameInfoPOJO.getRemarks());
    }

    @Override
    protected IAbstractFleaJPADAO<FleaRealNameInfo> getDAO() {
        return fleaRealNameInfoDao;
    }
}