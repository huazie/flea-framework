package com.huazie.fleaframework.auth.base.privilege.service.impl;

import com.huazie.fleaframework.auth.base.privilege.dao.interfaces.IFleaPrivilegeRelDAO;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeRel;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeRelSV;
import com.huazie.fleaframework.auth.common.AuthRelTypeEnum;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeRelPOJO;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Flea权限关联功能【菜单、操作、元素和资源】SV层实现类
 *
 * @author huazie
 * @version 2.0.0
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
        return this.fleaPrivilegeRelDao.getPrivilegeRelList(privilegeId, authRelType);
    }

    @Override
    public List<FleaPrivilegeRel> getPrivilegeRelList(List<Long> privilegeIdList, String authRelType) throws CommonException {
        return this.fleaPrivilegeRelDao.getPrivilegeRelList(privilegeIdList, authRelType);
    }

    @Override
    public List<Long> getPrivilegeRelIdList(List<Long> privilegeIdList, String authRelType) throws CommonException {
        List<Long> relIdList = new ArrayList<>();
        // 获取权限关联功能数据
        List<FleaPrivilegeRel> privilegeRelFunctions = this.getPrivilegeRelList(privilegeIdList, authRelType);
        // 处理权限关联菜单数据，获取关联编号集
        if (CollectionUtils.isNotEmpty(privilegeRelFunctions)) {
            for (FleaPrivilegeRel privilegeRel : privilegeRelFunctions) {
                if (ObjectUtils.isEmpty(privilegeRel)) continue;
                CollectionUtils.distinctAdd(relIdList, privilegeRel.getRelId());
            }
        }
        return relIdList;
    }

    @Override
    public FleaPrivilegeRel getPrivilegeRelMenu(Long menuId) throws CommonException {
        return this.fleaPrivilegeRelDao.getPrivilegeRelFunction(menuId, AuthRelTypeEnum.PRIVILEGE_REL_MENU.getRelType());
    }

    @Override
    public FleaPrivilegeRel getPrivilegeRelOperation(Long operationId) throws CommonException {
        return this.fleaPrivilegeRelDao.getPrivilegeRelFunction(operationId, AuthRelTypeEnum.PRIVILEGE_REL_OPERATION.getRelType());
    }

    @Override
    public FleaPrivilegeRel getPrivilegeRelElement(Long elementId) throws CommonException {
        return this.fleaPrivilegeRelDao.getPrivilegeRelFunction(elementId, AuthRelTypeEnum.PRIVILEGE_REL_ELEMENT.getRelType());
    }

    @Override
    public FleaPrivilegeRel getPrivilegeRelResource(Long resourceId) throws CommonException {
        return this.fleaPrivilegeRelDao.getPrivilegeRelFunction(resourceId, AuthRelTypeEnum.PRIVILEGE_REL_RESOURCE.getRelType());
    }

    @Override
    public FleaPrivilegeRel savePrivilegeRel(FleaPrivilegeRelPOJO fleaPrivilegeRelPOJO) throws CommonException {
        FleaPrivilegeRel fleaPrivilegeRel = newFleaPrivilegeRel(fleaPrivilegeRelPOJO);
        // 保存Flea权限关联数据
        this.save(fleaPrivilegeRel);
        return fleaPrivilegeRel;
    }

    @Override
    public void removePrivilegeRel(Long privilegeId, Long relId, String authRelType) throws CommonException {
        // 校验权限编号
        FleaAuthCheck.checkNonPositiveNumber(privilegeId, "权限编号");
        // 校验关联编号
        FleaAuthCheck.checkNonPositiveNumber(relId, "关联编号");
        // 校验关联类型
        FleaAuthCheck.checkBlank(authRelType, "关联类型");

        // 查询该权限在指定关联类型下的有效关联数据
        List<FleaPrivilegeRel> privilegeRelList = this.getPrivilegeRelList(privilegeId, authRelType);
        if (CollectionUtils.isNotEmpty(privilegeRelList)) {
            for (FleaPrivilegeRel fleaPrivilegeRel : privilegeRelList) {
                if (ObjectUtils.isEmpty(fleaPrivilegeRel)) continue;
                if (relId.compareTo(fleaPrivilegeRel.getRelId()) == 0) {
                    // 逻辑删除关联数据
                    fleaPrivilegeRel.setRelState(EntityStateEnum.BE_DELETED.getState());
                    fleaPrivilegeRel.setDoneDate(DateUtils.getCurrentTime());
                    this.update(fleaPrivilegeRel);
                }
            }
        }
    }

    /**
     * 新建Flea权限关联实体类对象
     *
     * @param fleaPrivilegeRelPOJO Flea权限关联POJO类对象
     * @return Flea权限实体类对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private FleaPrivilegeRel newFleaPrivilegeRel(FleaPrivilegeRelPOJO fleaPrivilegeRelPOJO) throws CommonException {
        // 校验权限关联POJO对象
        FleaAuthCheck.checkFleaPrivilegeRelPOJO(fleaPrivilegeRelPOJO);

        return new FleaPrivilegeRel(fleaPrivilegeRelPOJO.getPrivilegeId(),
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