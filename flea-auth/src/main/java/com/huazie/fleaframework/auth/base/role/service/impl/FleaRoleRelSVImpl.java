package com.huazie.fleaframework.auth.base.role.service.impl;

import com.huazie.fleaframework.auth.base.role.dao.interfaces.IFleaRoleRelDAO;
import com.huazie.fleaframework.auth.base.role.entity.FleaRoleRel;
import com.huazie.fleaframework.auth.base.role.service.interfaces.IFleaRoleRelSV;
import com.huazie.fleaframework.auth.common.pojo.role.FleaRoleRelPOJO;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Flea角色关联（角色， 权限， 权限组）SV层实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@Service("fleaRoleRelSV")
public class FleaRoleRelSVImpl extends AbstractFleaJPASVImpl<FleaRoleRel> implements IFleaRoleRelSV {

    private IFleaRoleRelDAO fleaRoleRelDao;

    @Autowired
    @Qualifier("fleaRoleRelDAO")
    public void setFleaRoleRelDao(IFleaRoleRelDAO fleaRoleRelDao) {
        this.fleaRoleRelDao = fleaRoleRelDao;
    }

    @Override
    public List<FleaRoleRel> getRoleRelList(Long roleId, String authRelType) throws CommonException {
        return fleaRoleRelDao.getRoleRelList(roleId, authRelType);
    }

    @Override
    public FleaRoleRel saveRoleRel(FleaRoleRelPOJO fleaRoleRelPOJO) throws CommonException {
        FleaRoleRel fleaRoleRel = newFleaRoleRel(fleaRoleRelPOJO);
        // 保存角色关联数据
        this.save(fleaRoleRel);
        return fleaRoleRel;
    }

    /**
     * 新建Flea角色关联数据
     *
     * @param fleaRoleRelPOJO Flea角色关联POJO对象
     * @return Flea角色关联数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    private FleaRoleRel newFleaRoleRel(FleaRoleRelPOJO fleaRoleRelPOJO) throws CommonException {
        // 校验Flea角色关联POJO对象
        FleaAuthCheck.checkFleaRoleRelPOJO(fleaRoleRelPOJO);

        return new FleaRoleRel(fleaRoleRelPOJO.getRoleId(),
                fleaRoleRelPOJO.getRelId(),
                fleaRoleRelPOJO.getRelType(),
                fleaRoleRelPOJO.getRemarks(),
                fleaRoleRelPOJO.getRelExtA(),
                fleaRoleRelPOJO.getRelExtB(),
                fleaRoleRelPOJO.getRelExtC(),
                fleaRoleRelPOJO.getRelExtX(),
                fleaRoleRelPOJO.getRelExtY(),
                fleaRoleRelPOJO.getRelExtZ());
    }

    @Override
    protected IAbstractFleaJPADAO<FleaRoleRel> getDAO() {
        return fleaRoleRelDao;
    }
}