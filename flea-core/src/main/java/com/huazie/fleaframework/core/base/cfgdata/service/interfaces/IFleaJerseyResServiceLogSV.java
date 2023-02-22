package com.huazie.fleaframework.core.base.cfgdata.service.interfaces;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyResServiceLog;
import com.huazie.fleaframework.core.common.pojo.FleaJerseyResServiceLogPOJO;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

/**
 * Flea Jersey资源服务调用日志SV层接口
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public interface IFleaJerseyResServiceLogSV extends IAbstractFleaJPASV<FleaJerseyResServiceLog> {

    /**
     * 保存资源服务调用日志
     *
     * @param resServiceLogPOJO 资源服务调用日志POJO类
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void saveResServiceLog(FleaJerseyResServiceLogPOJO resServiceLogPOJO) throws CommonException;
}