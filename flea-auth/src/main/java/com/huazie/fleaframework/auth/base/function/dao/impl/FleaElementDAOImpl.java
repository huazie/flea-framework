package com.huazie.fleaframework.auth.base.function.dao.impl;

import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.base.function.dao.interfaces.IFleaElementDAO;
import com.huazie.fleaframework.auth.base.function.entity.FleaElement;
import org.springframework.stereotype.Repository;

/**
 * Flea元素DAO层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaElementDAO")
public class FleaElementDAOImpl extends FleaAuthDAOImpl<FleaElement> implements IFleaElementDAO {
}