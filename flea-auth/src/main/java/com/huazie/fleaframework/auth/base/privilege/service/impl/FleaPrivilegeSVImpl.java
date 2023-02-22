package com.huazie.fleaframework.auth.base.privilege.service.impl;

import com.huazie.fleaframework.auth.base.privilege.dao.interfaces.IFleaPrivilegeDAO;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilege;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeSV;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegePOJO;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Flea权限SV层实现类
 *
 * @author huazie
 * @version 2.0.0
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
    public FleaPrivilege queryPrivilegeInUse(Long privilegeId) throws CommonException {
        return this.fleaPrivilegeDao.queryPrivilegeInUse(privilegeId);
    }

    @Override
    public List<FleaPrivilege> queryPrivilegesInUse4Page(String privilegeName, Long groupId, int pageNum, int pageCount) throws CommonException {
        return this.fleaPrivilegeDao.queryPrivilegesInUse4Page(privilegeName, groupId, pageNum, pageCount);
    }

    @Override
    public List<FleaPrivilege> queryPrivilegesInUse(String privilegeName, Long groupId) throws CommonException {
        return this.queryPrivilegesInUse4Page(privilegeName, groupId, -1, -1);
    }

    @Override
    public long queryPrivilegesInUseCount(String privilegeName, Long groupId) throws CommonException {
        return this.fleaPrivilegeDao.queryPrivilegesInUseCount(privilegeName, groupId);
    }

    @Override
    public FleaPrivilege savePrivilege(FleaPrivilegePOJO fleaPrivilegePOJO) throws CommonException {
        FleaPrivilege fleaPrivilege = newFleaPrivilege(fleaPrivilegePOJO);
        // 保存Flea权限数据
        this.save(fleaPrivilege);
        return fleaPrivilege;
    }

    /**
     * 新建Flea权限实体类对象
     *
     * @param fleaPrivilegePOJO Flea权限POJO类对象
     * @return Flea权限数据
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private FleaPrivilege newFleaPrivilege(FleaPrivilegePOJO fleaPrivilegePOJO) throws CommonException {
        // 校验Flea权限POJO对象
        FleaAuthCheck.checkFleaPrivilegePOJO(fleaPrivilegePOJO);

        return new FleaPrivilege(fleaPrivilegePOJO.getPrivilegeName(),
                fleaPrivilegePOJO.getPrivilegeDesc(),
                fleaPrivilegePOJO.getGroupId(),
                fleaPrivilegePOJO.getRemarks());
    }

    @Override
    protected IAbstractFleaJPADAO<FleaPrivilege> getDAO() {
        return fleaPrivilegeDao;
    }
}