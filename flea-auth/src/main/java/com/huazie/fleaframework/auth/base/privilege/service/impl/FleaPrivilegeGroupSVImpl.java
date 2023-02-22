package com.huazie.fleaframework.auth.base.privilege.service.impl;

import com.huazie.fleaframework.auth.base.privilege.dao.interfaces.IFleaPrivilegeGroupDAO;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeGroup;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeGroupSV;
import com.huazie.fleaframework.auth.common.FleaAuthConstants.PrivilegeModuleConstants;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeGroupPOJO;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Flea权限组SV层实现类
 *
 * @author huazie
 * @version 2.0.0
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
    public FleaPrivilegeGroup queryPrivilegeGroupInUse(Long privilegeGroupId) throws CommonException {
        return this.fleaPrivilegeGroupDao.queryPrivilegeGroupInUse(privilegeGroupId);
    }

    @Override
    public List<FleaPrivilegeGroup> queryPrivilegeGroupsInUse4Page(String privilegeGroupName, Integer isMain, String functionType, int pageNum, int pageCount) throws CommonException {
        return this.fleaPrivilegeGroupDao.queryPrivilegeGroupsInUse4Page(privilegeGroupName, isMain, functionType, pageNum, pageCount);
    }

    @Override
    public List<FleaPrivilegeGroup> queryPrivilegeGroupsInUse(String privilegeGroupName, Integer isMain, String functionType) throws CommonException {
        return this.queryPrivilegeGroupsInUse4Page(privilegeGroupName, isMain, functionType, -1, -1);
    }

    @Override
    public long queryPrivilegeGroupsInUseCount(String privilegeGroupName, Integer isMain, String functionType) throws CommonException {
        return this.fleaPrivilegeGroupDao.queryPrivilegeGroupsInUseCount(privilegeGroupName, isMain, functionType);
    }

    @Override
    public FleaPrivilegeGroup queryMainPrivilegeGroupInUse(String functionType) throws CommonException {
        List<FleaPrivilegeGroup> fleaPrivilegeGroupList = this.queryPrivilegeGroupsInUse(null, PrivilegeModuleConstants.MAIN, functionType);
        return CollectionUtils.getFirstElement(fleaPrivilegeGroupList, FleaPrivilegeGroup.class);
    }

    @Override
    public FleaPrivilegeGroup savePrivilegeGroup(FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO) throws CommonException {
        FleaPrivilegeGroup fleaPrivilegeGroup = newFleaPrivilegeGroup(fleaPrivilegeGroupPOJO);
        // 保存Flea权限组数据
        this.save(fleaPrivilegeGroup);
        return fleaPrivilegeGroup;
    }

    /**
     * 新建Flea权限组数据
     *
     * @param fleaPrivilegeGroupPOJO Flea权限组POJO类对象
     * @return Flea权限组数据
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private FleaPrivilegeGroup newFleaPrivilegeGroup(FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO) throws CommonException {
        // 校验Flea权限组POJO对象
        FleaAuthCheck.checkFleaPrivilegeGroupPOJO(fleaPrivilegeGroupPOJO);

        return new FleaPrivilegeGroup(fleaPrivilegeGroupPOJO.getPrivilegeGroupName(),
                fleaPrivilegeGroupPOJO.getPrivilegeGroupDesc(),
                fleaPrivilegeGroupPOJO.getIsMain(),
                fleaPrivilegeGroupPOJO.getFunctionType(),
                fleaPrivilegeGroupPOJO.getRemarks());
    }

    @Override
    protected IAbstractFleaJPADAO<FleaPrivilegeGroup> getDAO() {
        return fleaPrivilegeGroupDao;
    }
}