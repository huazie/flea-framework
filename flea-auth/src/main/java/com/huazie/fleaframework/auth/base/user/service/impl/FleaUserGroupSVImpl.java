package com.huazie.fleaframework.auth.base.user.service.impl;

import com.huazie.fleaframework.auth.base.user.dao.interfaces.IFleaUserGroupDAO;
import com.huazie.fleaframework.auth.base.user.entity.FleaUserGroup;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaUserGroupSV;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserGroupPOJO;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Flea用户组SV层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaUserGroupSV")
public class FleaUserGroupSVImpl extends AbstractFleaJPASVImpl<FleaUserGroup> implements IFleaUserGroupSV {

    private IFleaUserGroupDAO fleaUserGroupDao;

    @Autowired
    @Qualifier("fleaUserGroupDAO")
    public void setFleaUserGroupDao(IFleaUserGroupDAO fleaUserGroupDao) {
        this.fleaUserGroupDao = fleaUserGroupDao;
    }

    @Override
    public FleaUserGroup queryUserGroupInUse(Long userGroupId) throws CommonException {
        return this.fleaUserGroupDao.queryUserGroupInUse(userGroupId);
    }

    @Override
    public FleaUserGroup saveUserGroup(FleaUserGroupPOJO fleaUserGroupPOJO) throws CommonException {
        FleaUserGroup fleaUserGroup = newFleaUserGroup(fleaUserGroupPOJO);
        // 保存Flea用户组数据
        this.save(fleaUserGroup);
        return fleaUserGroup;
    }

    /**
     * 新建Flea用户组数据
     *
     * @param fleaUserGroupPOJO Flea用户组POJO对象
     * @return Flea用户组数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    private FleaUserGroup newFleaUserGroup(FleaUserGroupPOJO fleaUserGroupPOJO) throws CommonException {
        // 校验Flea用户组POJO对象
        FleaAuthCheck.checkFleaUserGroupPOJO(fleaUserGroupPOJO);

        return new FleaUserGroup(fleaUserGroupPOJO.getUserGroupName(),
                fleaUserGroupPOJO.getUserGroupDesc(),
                fleaUserGroupPOJO.getRemarks());
    }

    @Override
    protected IAbstractFleaJPADAO<FleaUserGroup> getDAO() {
        return fleaUserGroupDao;
    }
}