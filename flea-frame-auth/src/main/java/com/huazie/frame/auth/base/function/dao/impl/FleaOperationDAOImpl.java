package com.huazie.frame.auth.base.function.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDAOImpl;
import com.huazie.frame.auth.base.function.dao.interfaces.IFleaOperationDAO;
import com.huazie.frame.auth.base.function.entity.FleaOperation;
import org.springframework.stereotype.Repository;

/**
 * <p> Flea操作DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaOperationDAO")
public class FleaOperationDAOImpl extends FleaAuthDAOImpl<FleaOperation> implements IFleaOperationDAO {
}