package com.huazie.fleaframework.core.base.cfgdata.dao.impl;

import com.huazie.fleaframework.core.base.cfgdata.dao.interfaces.IFleaJerseyResServiceLogDAO;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyResServiceLog;
import org.springframework.stereotype.Repository;

/**
 * Flea Jersey资源服务调用日志DAO层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("resServiceLogDAO")
public class FleaJerseyResServiceLogDAOImpl extends FleaConfigDAOImpl<FleaJerseyResServiceLog> implements IFleaJerseyResServiceLogDAO {
}