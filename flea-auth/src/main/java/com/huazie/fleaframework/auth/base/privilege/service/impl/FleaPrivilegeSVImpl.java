package com.huazie.fleaframework.auth.base.privilege.service.impl;

import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.auth.common.exception.FleaAuthCommonException;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegePOJO;
import com.huazie.fleaframework.auth.base.privilege.dao.interfaces.IFleaPrivilegeDAO;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilege;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeSV;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p> Flea权限SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaPrivilegeSV")
public class FleaPrivilegeSVImpl extends AbstractFleaJPASVImpl<FleaPrivilege> implements IFleaPrivilegeSV {

    private IFleaPrivilegeDAO fleaPrivilegeDao;

    @Autowired
    @Qualifier("fleaPrivilegeDAO")
    public void setFleaPrivilegeDao(IFleaPrivilegeDAO fleaPrivilegeDao) {
        this.fleaPrivilegeDao = fleaPrivilegeDao;
    }

    @Override
    public FleaPrivilege savePrivilege(FleaPrivilegePOJO fleaPrivilegePOJO) throws CommonException {
        FleaPrivilege fleaPrivilege = newFleaPrivilege(fleaPrivilegePOJO);
        // 保存Flea权限
        this.save(fleaPrivilege);
        return fleaPrivilege;
    }

    /**
     * <p> 新建一个Flea权限实体类对象 </p>
     *
     * @param fleaPrivilegePOJO Flea权限POJO类对象
     * @return Flea权限实体对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private FleaPrivilege newFleaPrivilege(FleaPrivilegePOJO fleaPrivilegePOJO) throws CommonException {

        // 校验Flea权限POJO类对象是否为空
        // ERROR-AUTH-COMMON0000000001 【{0}】不能为空
        ObjectUtils.checkEmpty(fleaPrivilegePOJO, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", FleaPrivilegePOJO.class.getSimpleName());

        // 校验权限名称不能为空
        String privilegeName = fleaPrivilegePOJO.getPrivilegeName();
        StringUtils.checkBlank(privilegeName, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", FleaAuthEntityConstants.PrivilegeEntityConstants.E_PRIVILEGE_NAME);

        return new FleaPrivilege(privilegeName,
                fleaPrivilegePOJO.getPrivilegeDesc(),
                fleaPrivilegePOJO.getGroupId(),
                fleaPrivilegePOJO.getRemarks());
    }

    @Override
    protected IAbstractFleaJPADAO<FleaPrivilege> getDAO() {
        return fleaPrivilegeDao;
    }
}