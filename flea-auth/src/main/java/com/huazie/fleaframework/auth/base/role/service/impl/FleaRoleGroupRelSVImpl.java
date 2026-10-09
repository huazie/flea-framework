package com.huazie.fleaframework.auth.base.role.service.impl;

import com.huazie.fleaframework.auth.base.role.dao.interfaces.IFleaRoleGroupRelDAO;
import com.huazie.fleaframework.auth.base.role.entity.FleaRoleGroupRel;
import com.huazie.fleaframework.auth.base.role.service.interfaces.IFleaRoleGroupRelSV;
import com.huazie.fleaframework.auth.common.pojo.role.FleaRoleGroupRelPOJO;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Flea角色组关联（角色）SV层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaRoleGroupRelSV")
public class FleaRoleGroupRelSVImpl extends AbstractFleaJPASVImpl<FleaRoleGroupRel> implements IFleaRoleGroupRelSV {

    private IFleaRoleGroupRelDAO fleaRoleGroupRelDao;

    @Autowired
    @Qualifier("fleaRoleGroupRelDAO")
    public void setFleaRoleGroupRelDao(IFleaRoleGroupRelDAO fleaRoleGroupRelDao) {
        this.fleaRoleGroupRelDao = fleaRoleGroupRelDao;
    }

    @Override
    public List<FleaRoleGroupRel> getRoleGroupRelList(Long roleGroupId, String authRelType) throws CommonException {
        return fleaRoleGroupRelDao.getRoleGroupRelList(roleGroupId, authRelType);
    }

    @Override
    public FleaRoleGroupRel saveRoleGroupRel(FleaRoleGroupRelPOJO fleaRoleGroupRelPOJO) throws CommonException {
        FleaRoleGroupRel fleaRoleGroupRel = newFleaRoleGroupRel(fleaRoleGroupRelPOJO);
        // 保存Flea角色组关联数据
        this.save(fleaRoleGroupRel);
        return fleaRoleGroupRel;
    }

    @Override
    public void removeRoleGroupRel(Long roleGroupId, Long relId, String authRelType) throws CommonException {
        // 校验角色组编号
        FleaAuthCheck.checkNonPositiveNumber(roleGroupId, "角色组编号");
        // 校验关联编号
        FleaAuthCheck.checkNonPositiveNumber(relId, "关联编号");
        // 校验关联类型
        FleaAuthCheck.checkBlank(authRelType, "关联类型");

        // 查询该角色组在指定关联类型下的有效关联数据
        List<FleaRoleGroupRel> roleGroupRelList = this.getRoleGroupRelList(roleGroupId, authRelType);
        if (CollectionUtils.isNotEmpty(roleGroupRelList)) {
            for (FleaRoleGroupRel fleaRoleGroupRel : roleGroupRelList) {
                if (relId.compareTo(fleaRoleGroupRel.getRelId()) == 0) {
                    // 逻辑删除关联数据
                    fleaRoleGroupRel.setRelState(EntityStateEnum.BE_DELETED.getState());
                    fleaRoleGroupRel.setDoneDate(DateUtils.getCurrentTime());
                    this.update(fleaRoleGroupRel);
                }
            }
        }
    }

    /**
     * 新建Flea角色组关联数据
     *
     * @param fleaRoleGroupRelPOJO Flea角色组关联POJO对象
     * @return Flea角色组关联数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    private FleaRoleGroupRel newFleaRoleGroupRel(FleaRoleGroupRelPOJO fleaRoleGroupRelPOJO) throws CommonException {
        // 校验Flea角色组关联POJO对象
        FleaAuthCheck.checkFleaRoleGroupRelPOJO(fleaRoleGroupRelPOJO);

        return new FleaRoleGroupRel(fleaRoleGroupRelPOJO.getRoleGroupId(),
                fleaRoleGroupRelPOJO.getRelId(),
                fleaRoleGroupRelPOJO.getRelType(),
                fleaRoleGroupRelPOJO.getRemarks(),
                fleaRoleGroupRelPOJO.getRelExtA(),
                fleaRoleGroupRelPOJO.getRelExtB(),
                fleaRoleGroupRelPOJO.getRelExtC(),
                fleaRoleGroupRelPOJO.getRelExtX(),
                fleaRoleGroupRelPOJO.getRelExtY(),
                fleaRoleGroupRelPOJO.getRelExtZ());
    }

    @Override
    protected IAbstractFleaJPADAO<FleaRoleGroupRel> getDAO() {
        return fleaRoleGroupRelDao;
    }
}