package com.huazie.frame.auth.base.function.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDAOImpl;
import com.huazie.frame.auth.base.function.dao.interfaces.IFleaFunctionAttrDAO;
import com.huazie.frame.auth.base.function.entity.FleaFunctionAttr;
import org.springframework.stereotype.Repository;

/**
 * <p> Flea功能扩展属性DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaFunctionAttrDAO")
public class FleaFunctionAttrDAOImpl extends FleaAuthDAOImpl<FleaFunctionAttr> implements IFleaFunctionAttrDAO {
}