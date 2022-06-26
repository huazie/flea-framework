package com.huazie.fleaframework.auth.base.role.service.impl;

import com.huazie.fleaframework.auth.base.role.dao.interfaces.IFleaRoleGroupRelDAO;
import com.huazie.fleaframework.auth.base.role.entity.FleaRoleGroupRel;
import com.huazie.fleaframework.auth.base.role.service.interfaces.IFleaRoleGroupRelSV;
import com.huazie.fleaframework.auth.common.pojo.role.FleaRoleGroupRelPOJO;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Flea角色组关联（角色）SV层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaRoleGroupRelSV")
public class FleaRoleGroupRelSVImpl extends AbstractFleaJPASVImpl<FleaRoleGroupRel> implements IFleaRoleGroupRelSV {

    private IFleaRoleGroupRelDAO fleaRoleGroupRelDao;

    @Autowired
    @Qualifier("fleaRoleGroupRelDAO")
    public void setFleaRoleGroupRelDao(IFleaRoleGroupRelDAO fleaRoleGroupRelDao) {
        this.fleaRoleGroupRelDao = fleaRoleGroupRelDao;
    }

    @Override
    public List<FleaRoleGroupRel> getRoleGroupRelList(Long roleGroupId, String authRelType) throws CommonException {
        return fleaRoleGroupRelDao.getRoleGroupRelList(roleGroupId, authRelType);
    }

    @Override
    public FleaRoleGroupRel saveRoleGroupRel(FleaRoleGroupRelPOJO fleaRoleGroupRelPOJO) throws CommonException {
        FleaRoleGroupRel fleaRoleGroupRel = newFleaRoleGroupRel(fleaRoleGroupRelPOJO);
        // 保存Flea角色组关联数据
        this.save(fleaRoleGroupRel);
        return fleaRoleGroupRel;
    }

    /**
     * 新建Flea角色组关联数据
     *
     * @param fleaRoleGroupRelPOJO Flea角色组关联POJO对象
     * @return Flea角色组关联数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    private FleaRoleGroupRel newFleaRoleGroupRel(FleaRoleGroupRelPOJO fleaRoleGroupRelPOJO) throws CommonException {
        // 校验Flea角色组关联POJO对象
        FleaAuthCheck.checkFleaRoleGroupRelPOJO(fleaRoleGroupRelPOJO);

        return new FleaRoleGroupRel(fleaRoleGroupRelPOJO.getRoleGroupId(),
                fleaRoleGroupRelPOJO.getRelId(),
                fleaRoleGroupRelPOJO.getRelType(),
                fleaRoleGroupRelPOJO.getRemarks(),
                fleaRoleGroupRelPOJO.getRelExtA(),
                fleaRoleGroupRelPOJO.getRelExtB(),
                fleaRoleGroupRelPOJO.getRelExtC(),
                fleaRoleGroupRelPOJO.getRelExtX(),
                fleaRoleGroupRelPOJO.getRelExtY(),
                fleaRoleGroupRelPOJO.getRelExtZ());
    }

    @Override
    protected IAbstractFleaJPADAO<FleaRoleGroupRel> getDAO() {
        return fleaRoleGroupRelDao;
    }
}