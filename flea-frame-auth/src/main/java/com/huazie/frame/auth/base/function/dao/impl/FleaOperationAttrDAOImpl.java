package com.huazie.frame.auth.base.function.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDAOImpl;
import com.huazie.frame.auth.base.function.dao.interfaces.IFleaOperationAttrDAO;
import com.huazie.frame.auth.base.function.entity.FleaOperationAttr;
import org.springframework.stereotype.Repository;

/**
 * <p> Flea操作扩展属性DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaOperationAttrDAO")
public class FleaOperationAttrDAOImpl extends FleaAuthDAOImpl<FleaOperationAttr> implements IFleaOperationAttrDAO {
}