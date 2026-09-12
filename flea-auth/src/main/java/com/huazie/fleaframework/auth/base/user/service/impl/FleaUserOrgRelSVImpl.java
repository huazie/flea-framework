package com.huazie.fleaframework.auth.base.user.service.impl;

import com.huazie.fleaframework.auth.base.user.dao.interfaces.IFleaUserOrgRelDAO;
import com.huazie.fleaframework.auth.base.user.entity.FleaUserOrgRel;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaUserOrgRelSV;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Flea用户组织关联SV层实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@Service("fleaUserOrgRelSV")
public class FleaUserOrgRelSVImpl extends AbstractFleaJPASVImpl<FleaUserOrgRel> implements IFleaUserOrgRelSV {

    private IFleaUserOrgRelDAO fleaUserOrgRelDAO;

    @Autowired
    @Qualifier("fleaUserOrgRelDAO")
    public void setFleaUserOrgRelDAO(IFleaUserOrgRelDAO fleaUserOrgRelDAO) {
        this.fleaUserOrgRelDAO = fleaUserOrgRelDAO;
    }

    @Override
    public List<FleaUserOrgRel> getUserOrgRelList(Long userId, Long orgId) throws CommonException {
        return fleaUserOrgRelDAO.getUserOrgRelList(userId, orgId);
    }

    @Override
    public List<Long> getUserOrgIds(Long userId) throws CommonException {
        // 用户组织编号集
        List<Long> userOrgIdList = new ArrayList<>();

        // 获取有效的用户组织关联数据
        List<FleaUserOrgRel> userOrgRelList = this.getUserOrgRelList(userId, null);
        if (CollectionUtils.isNotEmpty(userOrgRelList)) {
            for (FleaUserOrgRel fleaUserOrgRel : userOrgRelList) {
                if (ObjectUtils.isEmpty(fleaUserOrgRel)) continue;
                CollectionUtils.distinctAdd(userOrgIdList, fleaUserOrgRel.getOrgId());
            }
        }

        return userOrgIdList;
    }

    @Override
    protected IAbstractFleaJPADAO<FleaUserOrgRel> getDAO() {
        return fleaUserOrgRelDAO;
    }
}
