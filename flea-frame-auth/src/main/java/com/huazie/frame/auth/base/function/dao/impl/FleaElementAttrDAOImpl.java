package com.huazie.frame.auth.base.function.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDAOImpl;
import com.huazie.frame.auth.base.function.dao.interfaces.IFleaElementAttrDAO;
import com.huazie.frame.auth.base.function.entity.FleaElementAttr;
import org.springframework.stereotype.Repository;

/**
 * <p> Flea元素扩展属性DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaElementAttrDAO")
public class FleaElementAttrDAOImpl extends FleaAuthDAOImpl<FleaElementAttr> implements IFleaElementAttrDAO {
}