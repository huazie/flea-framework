package com.huazie.fleaframework.auth.base.user.service.impl;

import com.huazie.fleaframework.auth.base.user.dao.interfaces.IFleaUserRelDAO;
import com.huazie.fleaframework.auth.base.user.entity.FleaUserRel;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaUserRelSV;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserRelPOJO;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Flea用户关联（用户，用户组）SV层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaUserRelSV")
public class FleaUserRelSVImpl extends AbstractFleaJPASVImpl<FleaUserRel> implements IFleaUserRelSV {

    private IFleaUserRelDAO fleaUserRelDao;

    @Autowired
    @Qualifier("fleaUserRelDAO")
    public void setFleaUserRelDao(IFleaUserRelDAO fleaUserRelDao) {
        this.fleaUserRelDao = fleaUserRelDao;
    }

    @Override
    public List<FleaUserRel> getUserRelList(Long userId, String authRelType) throws CommonException {
        return fleaUserRelDao.getUserRelList(userId, authRelType);
    }

    @Override
    public FleaUserRel saveUserRel(FleaUserRelPOJO fleaUserRelPOJO) throws CommonException {
        FleaUserRel fleaUserRel = newFleaUserRel(fleaUserRelPOJO);
        // 保存用户关联数据
        this.save(fleaUserRel);
        return fleaUserRel;
    }

    /**
     * 新建Flea用户关联数据
     *
     * @param fleaUserRelPOJO Flea用户关联POJO对象
     * @return Flea用户关联数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    private FleaUserRel newFleaUserRel(FleaUserRelPOJO fleaUserRelPOJO) throws CommonException {
        // 校验Flea用户关联POJO对象
        FleaAuthCheck.checkFleaUserRelPOJO(fleaUserRelPOJO);

        return new FleaUserRel(fleaUserRelPOJO.getUserId(),
                fleaUserRelPOJO.getRelId(),
                fleaUserRelPOJO.getRelType(),
                fleaUserRelPOJO.getRemarks(),
                fleaUserRelPOJO.getRelExtA(),
                fleaUserRelPOJO.getRelExtB(),
                fleaUserRelPOJO.getRelExtC(),
                fleaUserRelPOJO.getRelExtX(),
                fleaUserRelPOJO.getRelExtY(),
                fleaUserRelPOJO.getRelExtZ());
    }

    @Override
    protected IAbstractFleaJPADAO<FleaUserRel> getDAO() {
        return fleaUserRelDao;
    }
}