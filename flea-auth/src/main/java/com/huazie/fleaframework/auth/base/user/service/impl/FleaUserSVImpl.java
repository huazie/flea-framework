package com.huazie.fleaframework.auth.base.user.service.impl;

import com.huazie.fleaframework.auth.base.user.dao.interfaces.IFleaUserDAO;
import com.huazie.fleaframework.auth.base.user.entity.FleaUser;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaUserSV;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserPOJO;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Flea用户SV层实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@Service("fleaUserSV")
public class FleaUserSVImpl extends AbstractFleaJPASVImpl<FleaUser> implements IFleaUserSV {

    private IFleaUserDAO fleaUserDao;

    @Autowired
    @Qualifier("fleaUserDAO")
    public void setFleaUserDao(IFleaUserDAO fleaUserDao) {
        this.fleaUserDao = fleaUserDao;
    }

    @Override
    public FleaUser queryValidUser(Long userId) throws CommonException {
        return fleaUserDao.queryValidUser(userId);
    }

    @Override
    public FleaUser saveFleaUser(FleaUserPOJO fleaUserPOJO) throws CommonException {

        FleaUser fleaUser = newFleaUser(fleaUserPOJO);

        // 系统用户生成时指定用户编号
        Long userId = fleaUserPOJO.getUserId();
        if (ObjectUtils.isNotEmpty(userId) && userId > CommonConstants.NumeralConstants.ZERO) {
            fleaUser.setUserId(userId);
        }

        // 保存Flea用户信息
        fleaUserDao.save(fleaUser);

        return fleaUser;
    }

    /**
     * 新建一个Flea用户信息
     *
     * @param fleaUserPOJO Flea用户POJO类实例
     * @return Flea用户实体类实例
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private FleaUser newFleaUser(FleaUserPOJO fleaUserPOJO) throws CommonException {
        // 校验Flea用户POJO对象
        FleaAuthCheck.checkFleaUserPOJO(fleaUserPOJO);

        return new FleaUser(fleaUserPOJO.getUserName(),
                fleaUserPOJO.getGroupId(),
                fleaUserPOJO.getUserState(),
                fleaUserPOJO.getEffectiveDate(),
                fleaUserPOJO.getExpiryDate(),
                fleaUserPOJO.getRemarks());
    }

    @Override
    protected IAbstractFleaJPADAO<FleaUser> getDAO() {
        return fleaUserDao;
    }
}