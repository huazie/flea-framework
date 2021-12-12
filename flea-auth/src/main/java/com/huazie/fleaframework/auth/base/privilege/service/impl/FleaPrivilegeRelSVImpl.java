package com.huazie.fleaframework.auth.base.privilege.service.impl;

import com.huazie.fleaframework.auth.base.privilege.dao.interfaces.IFleaPrivilegeRelDAO;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeRel;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeRelSV;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.auth.common.exception.FleaAuthCommonException;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeRelPOJO;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> Flea权限关联（菜单， 操作， 元素）SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaPrivilegeRelSV")
public class FleaPrivilegeRelSVImpl extends AbstractFleaJPASVImpl<FleaPrivilegeRel> implements IFleaPrivilegeRelSV {

    private IFleaPrivilegeRelDAO fleaPrivilegeRelDao;

    @Autowired
    @Qualifier("fleaPrivilegeRelDAO")
    public void setFleaPrivilegeRelDao(IFleaPrivilegeRelDAO fleaPrivilegeRelDao) {
        this.fleaPrivilegeRelDao = fleaPrivilegeRelDao;
    }

    @Override
    public List<FleaPrivilegeRel> getPrivilegeRelList(Long privilegeId, String authRelType) throws CommonException {
        return fleaPrivilegeRelDao.getPrivilegeRelList(privilegeId, authRelType);
    }

    @Override
    public FleaPrivilegeRel getPrivilegeRelMenu(Long menuId) throws CommonException {
        return fleaPrivilegeRelDao.getPrivilegeRelMenu(menuId);
    }

    @Override
    public FleaPrivilegeRel savePrivilegeRel(FleaPrivilegeRelPOJO fleaPrivilegeRelPOJO) throws CommonException {
        FleaPrivilegeRel fleaPrivilegeRel = newFleaPrivilegeRel(fleaPrivilegeRelPOJO);
        // 保存Flea权限关联
        this.save(fleaPrivilegeRel);
        return fleaPrivilegeRel;
    }

    /**
     * <p> 新建一个Flea权限关联实体类对象 </p>
     *
     * @param fleaPrivilegeRelPOJO Flea权限关联POJO类对象
     * @return Flea权限实体类对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private FleaPrivilegeRel newFleaPrivilegeRel(FleaPrivilegeRelPOJO fleaPrivilegeRelPOJO) throws CommonException {

        // 校验Flea权限关联POJO类对象是否为空
        // ERROR-AUTH-COMMON0000000001 【{0}】不能为空
        ObjectUtils.checkEmpty(fleaPrivilegeRelPOJO, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", FleaPrivilegeRelPOJO.class.getSimpleName());

        // 校验权限编号不能为空
        Long privilegeId = fleaPrivilegeRelPOJO.getPrivilegeId();
        ObjectUtils.checkEmpty(privilegeId, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", FleaAuthEntityConstants.PrivilegeEntityConstants.E_PRIVILEGE_ID);

        // 校验权限关联POJO类对象
        FleaAuthCheck.checkAuthRelPOJO(fleaPrivilegeRelPOJO);

        return new FleaPrivilegeRel(privilegeId,
                fleaPrivilegeRelPOJO.getRelId(),
                fleaPrivilegeRelPOJO.getRelType(),
                fleaPrivilegeRelPOJO.getRemarks(),
                fleaPrivilegeRelPOJO.getRelExtA(),
                fleaPrivilegeRelPOJO.getRelExtB(),
                fleaPrivilegeRelPOJO.getRelExtC(),
                fleaPrivilegeRelPOJO.getRelExtX(),
                fleaPrivilegeRelPOJO.getRelExtY(),
                fleaPrivilegeRelPOJO.getRelExtZ());
    }

    @Override
    protected IAbstractFleaJPADAO<FleaPrivilegeRel> getDAO() {
        return fleaPrivilegeRelDao;
    }
}