package com.huazie.fleaframework.auth.base.privilege.service.impl;

import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeGroupRelSV;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.auth.common.exception.FleaAuthCommonException;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeGroupRelPOJO;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.auth.base.privilege.dao.interfaces.IFleaPrivilegeGroupRelDAO;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeGroupRel;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> Flea权限组关联（权限）SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
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
    public FleaPrivilegeGroupRel saveFleaPrivilegeGroupRel(FleaPrivilegeGroupRelPOJO fleaPrivilegeGroupRelPOJO) throws CommonException {
        FleaPrivilegeGroupRel fleaPrivilegeGroupRel = newFleaPrivilegeGroupRel(fleaPrivilegeGroupRelPOJO);
        // 保存权限组关联
        this.save(fleaPrivilegeGroupRel);
        return fleaPrivilegeGroupRel;
    }

    /**
     * <p> 新建一个Flea权限关联实体类对象 </p>
     *
     * @param fleaPrivilegeGroupRelPOJO Flea权限关联POJO类对象
     * @return Flea权限实体类对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private FleaPrivilegeGroupRel newFleaPrivilegeGroupRel(FleaPrivilegeGroupRelPOJO fleaPrivilegeGroupRelPOJO) throws CommonException {

        // 校验Flea权限关联POJO类对象是否为空
        // ERROR-AUTH-COMMON0000000001 【{0}】不能为空
        ObjectUtils.checkEmpty(fleaPrivilegeGroupRelPOJO, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", FleaPrivilegeGroupRelPOJO.class.getSimpleName());

        // 校验权限组编号不能为空
        Long privilegeGroupId = fleaPrivilegeGroupRelPOJO.getPrivilegeGroupId();
        ObjectUtils.checkEmpty(privilegeGroupId, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", FleaAuthEntityConstants.PrivilegeEntityConstants.E_PRIVILEGE_GROUP_ID);

        // 校验权限关联POJO类对象
        FleaAuthCheck.checkAuthRelPOJO(fleaPrivilegeGroupRelPOJO);

        return new FleaPrivilegeGroupRel(privilegeGroupId,
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