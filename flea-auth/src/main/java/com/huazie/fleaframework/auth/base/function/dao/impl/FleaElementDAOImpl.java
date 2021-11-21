package com.huazie.fleaframework.auth.base.function.dao.impl;

import com.huazie.fleaframework.auth.base.function.dao.interfaces.IFleaElementDAO;
import com.huazie.fleaframework.auth.base.function.entity.FleaElement;
import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import org.springframework.stereotype.Repository;

/**
 * <p> Flea元素DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaElementDAO")
public class FleaElementDAOImpl extends FleaAuthDAOImpl<FleaElement> implements IFleaElementDAO {
}