package com.huazie.fleaframework.core.base.cfgdata.dao.impl;

import com.huazie.fleaframework.core.base.cfgdata.dao.interfaces.IFleaJerseyResServiceLogDAO;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyResServiceLog;
import org.springframework.stereotype.Repository;

/**
 * <p> Flea Jersey资源服务调用日志DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaJerseyResServiceLogDAO")
public class FleaJerseyResServiceLogDAOImpl extends FleaConfigDAOImpl<FleaJerseyResServiceLog> implements IFleaJerseyResServiceLogDAO {
}