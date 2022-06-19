package com.huazie.fleaframework.auth.base.user.service.impl;

import com.huazie.fleaframework.auth.base.user.dao.interfaces.IFleaUserGroupRelDAO;
import com.huazie.fleaframework.auth.base.user.entity.FleaUserGroupRel;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaUserGroupRelSV;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserGroupRelPOJO;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Flea用户组关联（角色，角色组）SV层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaUserGroupRelSV")
public class FleaUserGroupRelSVImpl extends AbstractFleaJPASVImpl<FleaUserGroupRel> implements IFleaUserGroupRelSV {

    private IFleaUserGroupRelDAO fleaUserGroupRelDao;

    @Autowired
    @Qualifier("fleaUserGroupRelDAO")
    public void setFleaUserGroupRelDao(IFleaUserGroupRelDAO fleaUserGroupRelDao) {
        this.fleaUserGroupRelDao = fleaUserGroupRelDao;
    }

    @Override
    public List<FleaUserGroupRel> getUserGroupRelList(Long userGroupId, Long relId, String authRelType) throws CommonException {
        return fleaUserGroupRelDao.getUserGroupRelList(userGroupId, relId, authRelType);
    }

    @Override
    public FleaUserGroupRel saveUserGroupRel(FleaUserGroupRelPOJO fleaUserGroupRelPOJO) throws CommonException {
        FleaUserGroupRel fleaUserGroupRel = newFleaUserGroupRel(fleaUserGroupRelPOJO);
        // 保存Flea用户组关联数据
        this.save(fleaUserGroupRel);
        return fleaUserGroupRel;
    }

    /**
     * 新建Flea用户组关联数据
     *
     * @param fleaUserGroupRelPOJO Flea用户组关联POJO对象
     * @return Flea用户组关联数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    private FleaUserGroupRel newFleaUserGroupRel(FleaUserGroupRelPOJO fleaUserGroupRelPOJO) throws CommonException {
        // 校验Flea用户组关联POJO对象
        FleaAuthCheck.checkFleaUserGroupRelPOJO(fleaUserGroupRelPOJO);

        return new FleaUserGroupRel(fleaUserGroupRelPOJO.getUserGroupId(),
                fleaUserGroupRelPOJO.getRelId(),
                fleaUserGroupRelPOJO.getRelType(),
                fleaUserGroupRelPOJO.getRemarks(),
                fleaUserGroupRelPOJO.getRelExtA(),
                fleaUserGroupRelPOJO.getRelExtB(),
                fleaUserGroupRelPOJO.getRelExtC(),
                fleaUserGroupRelPOJO.getRelExtX(),
                fleaUserGroupRelPOJO.getRelExtY(),
                fleaUserGroupRelPOJO.getRelExtZ());
    }

    @Override
    protected IAbstractFleaJPADAO<FleaUserGroupRel> getDAO() {
        return fleaUserGroupRelDao;
    }
}