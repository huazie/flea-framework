package com.huazie.frame.auth.base.function.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDAOImpl;
import com.huazie.frame.auth.base.function.dao.interfaces.IFleaMenuAttrDAO;
import com.huazie.frame.auth.base.function.entity.FleaMenuAttr;
import org.springframework.stereotype.Repository;

/**
 * <p> Flea菜单扩展属性DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaMenuAttrDAO")
public class FleaMenuAttrDAOImpl extends FleaAuthDAOImpl<FleaMenuAttr> implements IFleaMenuAttrDAO {
}