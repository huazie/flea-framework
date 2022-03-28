package com.huazie.fleaframework.auth.base.user.service.impl;

import com.huazie.fleaframework.auth.base.user.dao.interfaces.IFleaUserDAO;
import com.huazie.fleaframework.auth.base.user.entity.FleaUser;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaUserSV;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.auth.common.exception.FleaAuthCommonException;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserPOJO;
import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Flea用户SV层实现类
 *
 * @author huazie
 * @version 1.0.0
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
    public FleaUser saveFleaUser(FleaUserPOJO fleaUserPOJO) throws CommonException {

        FleaUser fleaUser = newFleaUser(fleaUserPOJO);

        // 系统用户生成时指定用户编号
        Long userId = fleaUserPOJO.getUserId();
        if (ObjectUtils.isNotEmpty(userId) && userId > CommonConstants.NumeralConstants.ZERO) {
            fleaUser.setUserId(userId);
        }

        // 保存Flea用户
        fleaUserDao.save(fleaUser);

        return fleaUser;
    }

    /**
     * 新建一个Flea用户
     *
     * @param fleaUserPOJO Flea用户POJO类实例
     * @return Flea用户实体类实例
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private FleaUser newFleaUser(FleaUserPOJO fleaUserPOJO) throws CommonException {

        // 校验Flea用户POJO类对象是否为空
        // ERROR-AUTH-COMMON0000000001 【{0}】不能为空
        ObjectUtils.checkEmpty(fleaUserPOJO, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", FleaUserPOJO.class.getSimpleName());

        // 校验用户昵称是否为空
        String userName = fleaUserPOJO.getUserName();
        StringUtils.checkBlank(userName, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", FleaAuthEntityConstants.UserEntityConstants.E_USER_NAME);

        return new FleaUser(userName,
                fleaUserPOJO.getGroupId(),
                fleaUserPOJO.getUserState(),
                fleaUserPOJO.getEffectiveDate(),
                fleaUserPOJO.getExpiryDate(),
                fleaUserPOJO.getRemarks());
    }

    @Override
    public FleaUser queryValidUser(Long userId) throws CommonException {
        return fleaUserDao.queryValidUser(userId);
    }

    @Override
    protected IAbstractFleaJPADAO<FleaUser> getDAO() {
        return fleaUserDao;
    }
}