package com.huazie.fleaframework.auth.base.privilege.service.impl;

import com.huazie.fleaframework.auth.base.privilege.dao.interfaces.IFleaPrivilegeGroupRelDAO;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilege;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeGroup;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeGroupRel;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeGroupRelSV;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeGroupRelPOJO;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.auth.util.FleaAuthPOJOUtils;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Flea权限组关联（权限）SV层实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@Service("fleaPrivilegeGroupRelSV")
public class FleaPrivilegeGroupRelSVImpl extends AbstractFleaJPASVImpl<FleaPrivilegeGroupRel> implements IFleaPrivilegeGroupRelSV {

    private IFleaPrivilegeGroupRelDAO fleaPrivilegeGroupRelDao;

    @Autowired
    @Qualifier("fleaPrivilegeGroupRelDAO")
    public void setFleaPrivilegeGroupRelDao(IFleaPrivilegeGroupRelDAO fleaPrivilegeGroupRelDao) {
        this.fleaPrivilegeGroupRelDao = fleaPrivilegeGroupRelDao;
    }

    @Override
    public List<FleaPrivilegeGroupRel> getPrivilegeGroupRelList(Long privilegeGroupId, String authRelType) throws CommonException {
        return fleaPrivilegeGroupRelDao.getPrivilegeGroupRelList(privilegeGroupId, authRelType);
    }

    @Override
    public void savePrivilegeGroup(FleaPrivilegeGroup fleaPrivilegeGroup, FleaPrivilege fleaPrivilege) throws CommonException {
        this.saveFleaPrivilegeGroupRel(FleaAuthPOJOUtils.newPrivilegeGroupRelPrivilegePOJO(fleaPrivilegeGroup, fleaPrivilege));
    }

    @Override
    public FleaPrivilegeGroupRel saveFleaPrivilegeGroupRel(FleaPrivilegeGroupRelPOJO fleaPrivilegeGroupRelPOJO) throws CommonException {
        FleaPrivilegeGroupRel fleaPrivilegeGroupRel = newFleaPrivilegeGroupRel(fleaPrivilegeGroupRelPOJO);
        // 保存权限组关联数据
        this.save(fleaPrivilegeGroupRel);
        return fleaPrivilegeGroupRel;
    }

    /**
     * 新建Flea权限关联实体类对象
     *
     * @param fleaPrivilegeGroupRelPOJO Flea权限关联POJO类对象
     * @return Flea权限实体类对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private FleaPrivilegeGroupRel newFleaPrivilegeGroupRel(FleaPrivilegeGroupRelPOJO fleaPrivilegeGroupRelPOJO) throws CommonException {

        // 校验权限关联POJO类对象
        FleaAuthCheck.checkFleaPrivilegeGroupRelPOJO(fleaPrivilegeGroupRelPOJO);

        return new FleaPrivilegeGroupRel(fleaPrivilegeGroupRelPOJO.getPrivilegeGroupId(),
                fleaPrivilegeGroupRelPOJO.getRelId(),
                fleaPrivilegeGroupRelPOJO.getRelType(),
                fleaPrivilegeGroupRelPOJO.getRemarks(),
                fleaPrivilegeGroupRelPOJO.getRelExtA(),
                fleaPrivilegeGroupRelPOJO.getRelExtB(),
                fleaPrivilegeGroupRelPOJO.getRelExtC(),
                fleaPrivilegeGroupRelPOJO.getRelExtX(),
                fleaPrivilegeGroupRelPOJO.getRelExtY(),
                fleaPrivilegeGroupRelPOJO.getRelExtZ());
    }

    @Override
    protected IAbstractFleaJPADAO<FleaPrivilegeGroupRel> getDAO() {
        return fleaPrivilegeGroupRelDao;
    }
}