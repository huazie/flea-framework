package com.huazie.fleaframework.auth.base.role.service.impl;

import com.huazie.fleaframework.auth.base.role.dao.interfaces.IFleaRoleDAO;
import com.huazie.fleaframework.auth.base.role.entity.FleaRole;
import com.huazie.fleaframework.auth.base.role.service.interfaces.IFleaRoleSV;
import com.huazie.fleaframework.auth.common.pojo.role.FleaRolePOJO;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Flea角色SV层实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@Service("fleaRoleSV")
public class FleaRoleSVImpl extends AbstractFleaJPASVImpl<FleaRole> implements IFleaRoleSV {

    private IFleaRoleDAO fleaRoleDao;

    @Autowired
    @Qualifier("fleaRoleDAO")
    public void setFleaRoleDao(IFleaRoleDAO fleaRoleDao) {
        this.fleaRoleDao = fleaRoleDao;
    }

    @Override
    public FleaRole queryRoleInUse(Long roleId) throws CommonException {
        return this.fleaRoleDao.queryRoleInUse(roleId);
    }

    @Override
    public List<FleaRole> queryRolesInUse4Page(String roleName, Long groupId, int pageNum, int pageCount) throws CommonException {
        return this.fleaRoleDao.queryRolesInUse4Page(roleName, groupId, pageNum, pageCount);
    }

    @Override
    public List<FleaRole> queryRolesInUse(String roleName, Long groupId) throws CommonException {
        return this.queryRolesInUse4Page(roleName, groupId, -1, -1);
    }

    @Override
    public long queryRolesInUseCount(String roleName, Long groupId) throws CommonException {
        return this.fleaRoleDao.queryRolesInUseCount(roleName, groupId);
    }

    @Override
    public FleaRole saveRole(FleaRolePOJO fleaRolePOJO) throws CommonException {
        FleaRole fleaRole = newFleaRole(fleaRolePOJO);
        // 保存Flea角色数据
        this.save(fleaRole);
        return fleaRole;
    }

    /**
     * 新建Flea角色数据
     *
     * @param fleaRolePOJO Flea角色POJO对象
     * @return Flea角色数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    private FleaRole newFleaRole(FleaRolePOJO fleaRolePOJO) throws CommonException {
        // 校验Flea角色POJO对象
        FleaAuthCheck.checkFleaRolePOJO(fleaRolePOJO);

        return new FleaRole(fleaRolePOJO.getRoleName(),
                fleaRolePOJO.getRoleDesc(),
                fleaRolePOJO.getGroupId(),
                fleaRolePOJO.getRemarks());
    }

    @Override
    protected IAbstractFleaJPADAO<FleaRole> getDAO() {
        return fleaRoleDao;
    }
}