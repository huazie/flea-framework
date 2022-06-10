package com.huazie.fleaframework.auth.base.privilege.service.impl;

import com.huazie.fleaframework.auth.base.privilege.dao.interfaces.IFleaPrivilegeGroupDAO;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeGroup;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeGroupSV;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.auth.common.exception.FleaAuthCommonException;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeGroupPOJO;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Flea权限组SV层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaPrivilegeGroupSV")
public class FleaPrivilegeGroupSVImpl extends AbstractFleaJPASVImpl<FleaPrivilegeGroup> implements IFleaPrivilegeGroupSV {

    private IFleaPrivilegeGroupDAO fleaPrivilegeGroupDao;

    @Autowired
    @Qualifier("fleaPrivilegeGroupDAO")
    public void setFleaPrivilegeGroupDao(IFleaPrivilegeGroupDAO fleaPrivilegeGroupDao) {
        this.fleaPrivilegeGroupDao = fleaPrivilegeGroupDao;
    }

    @Override
    public FleaPrivilegeGroup savePrivilegeGroup(FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO) throws CommonException {
        FleaPrivilegeGroup fleaPrivilegeGroup = newFleaPrivilegeGroup(fleaPrivilegeGroupPOJO);
        // 保存Flea权限组
        this.save(fleaPrivilegeGroup);
        return fleaPrivilegeGroup;
    }

    /**
     * 保存Flea权限组
     *
     * @param fleaPrivilegeGroupPOJO Flea权限组POJO类对象
     * @return Flea权限组实体对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private FleaPrivilegeGroup newFleaPrivilegeGroup(FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO) throws CommonException {

        // 校验Flea权限组POJO类对象是否为空
        // ERROR-AUTH-COMMON0000000001 【{0}】不能为空
        ObjectUtils.checkEmpty(fleaPrivilegeGroupPOJO, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", FleaPrivilegeGroupPOJO.class.getSimpleName());

        // 校验权限组名称不能为空
        String privilegeGroupName = fleaPrivilegeGroupPOJO.getPrivilegeGroupName();
        StringUtils.checkBlank(privilegeGroupName, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", FleaAuthEntityConstants.PrivilegeEntityConstants.E_PRIVILEGE_GROUP_NAME);

        return new FleaPrivilegeGroup(privilegeGroupName,
                fleaPrivilegeGroupPOJO.getPrivilegeGroupDesc(),
                fleaPrivilegeGroupPOJO.getRemarks());
    }

    @Override
    protected IAbstractFleaJPADAO<FleaPrivilegeGroup> getDAO() {
        return fleaPrivilegeGroupDao;
    }
}