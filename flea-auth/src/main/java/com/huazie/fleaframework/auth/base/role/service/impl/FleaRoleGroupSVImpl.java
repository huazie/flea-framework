package com.huazie.fleaframework.auth.base.role.service.impl;

import com.huazie.fleaframework.auth.base.role.dao.interfaces.IFleaRoleGroupDAO;
import com.huazie.fleaframework.auth.base.role.entity.FleaRoleGroup;
import com.huazie.fleaframework.auth.base.role.service.interfaces.IFleaRoleGroupSV;
import com.huazie.fleaframework.auth.common.pojo.role.FleaRoleGroupPOJO;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Flea角色组SV层实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@Service("fleaRoleGroupSV")
public class FleaRoleGroupSVImpl extends AbstractFleaJPASVImpl<FleaRoleGroup> implements IFleaRoleGroupSV {

    private IFleaRoleGroupDAO fleaRoleGroupDao;

    @Autowired
    @Qualifier("fleaRoleGroupDAO")
    public void setFleaRoleGroupDao(IFleaRoleGroupDAO fleaRoleGroupDao) {
        this.fleaRoleGroupDao = fleaRoleGroupDao;
    }

    @Override
    public FleaRoleGroup queryRoleGroupInUse(Long roleGroupId) throws CommonException {
        return this.fleaRoleGroupDao.queryRoleGroupInUse(roleGroupId);
    }

    @Override
    public List<FleaRoleGroup> queryRoleGroupsInUse4Page(String roleGroupName, int pageNum, int pageCount) throws CommonException {
        return this.fleaRoleGroupDao.queryRoleGroupsInUse4Page(roleGroupName, pageNum, pageCount);
    }

    @Override
    public List<FleaRoleGroup> queryRoleGroupsInUse(String roleGroupName) throws CommonException {
        return this.queryRoleGroupsInUse4Page(roleGroupName, -1, -1);
    }

    @Override
    public long queryRoleGroupsInUseCount(String roleGroupName) throws CommonException {
        return this.fleaRoleGroupDao.queryRoleGroupsInUseCount(roleGroupName);
    }

    @Override
    public FleaRoleGroup saveRoleGroup(FleaRoleGroupPOJO fleaRoleGroupPOJO) throws CommonException {
        FleaRoleGroup fleaRoleGroup = newFleaRoleGroup(fleaRoleGroupPOJO);
        // 保存Flea角色组数据
        this.save(fleaRoleGroup);
        return fleaRoleGroup;
    }

    /**
     * 新建Flea角色组数据
     *
     * @param fleaRoleGroupPOJO Flea角色组POJO对象
     * @return Flea角色组数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    private FleaRoleGroup newFleaRoleGroup(FleaRoleGroupPOJO fleaRoleGroupPOJO) throws CommonException {
        // 校验Flea角色组POJO对象
        FleaAuthCheck.checkFleaRoleGroupPOJO(fleaRoleGroupPOJO);

        return new FleaRoleGroup(fleaRoleGroupPOJO.getRoleGroupName(),
                fleaRoleGroupPOJO.getRoleGroupDesc(),
                fleaRoleGroupPOJO.getRemarks());
    }

    @Override
    protected IAbstractFleaJPADAO<FleaRoleGroup> getDAO() {
        return fleaRoleGroupDao;
    }
}