package com.huazie.frame.auth.base.function.service.impl;

import com.huazie.frame.auth.base.function.dao.interfaces.IFleaMenuDAO;
import com.huazie.frame.auth.base.function.entity.FleaMenu;
import com.huazie.frame.auth.base.function.service.interfaces.IFleaMenuSV;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> Flea菜单SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaMenuSV")
public class FleaMenuSVImpl extends AbstractFleaJPASVImpl<FleaMenu> implements IFleaMenuSV {

    private IFleaMenuDAO fleaMenuDao;

    @Autowired
    @Qualifier("fleaMenuDAO")
    public void setFleaMenuDao(IFleaMenuDAO fleaMenuDao) {
        this.fleaMenuDao = fleaMenuDao;
    }

    @Override
    public List<FleaMenu> getAllAccessibleMenus(List<Long> systemRelMenuIdList, List<Long> menuIdList) throws CommonException {

        // 取交集
        systemRelMenuIdList.retainAll(menuIdList);

        // 获取菜单列表
        List<FleaMenu> allAccessibleMenus = null;
        if (CollectionUtils.isNotEmpty(systemRelMenuIdList)) {
            allAccessibleMenus = new ArrayList<>();
            for (Long menuId : systemRelMenuIdList) {
                if (null != menuId && menuId > 0L) {
                    FleaMenu fleaMenu = this.query(menuId);
                    if (null != fleaMenu) {
                        allAccessibleMenus.add(fleaMenu);
                    }
                }
            }
        }

        return allAccessibleMenus;
    }

    @Override
    protected IAbstractFleaJPADAO<FleaMenu> getDAO() {
        return fleaMenuDao;
    }
}