package com.huazie.frame.core.base.cfgdata.dao.impl;

import com.huazie.frame.common.EntityStateEnum;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.core.base.cfgdata.dao.interfaces.IFleaParaDetailDAO;
import com.huazie.frame.core.base.cfgdata.entity.FleaParaDetail;
import com.huazie.frame.core.common.FleaConfigEntityConstants;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p> 静态参数配置DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaParaDetailDAO")
public class FleaParaDetailDAOImpl extends FleaConfigDAOImpl<FleaParaDetail> implements IFleaParaDetailDAO {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaParaDetailDAOImpl.class);

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<FleaParaDetail> getParaDetail(String paraType, String paraCode) throws CommonException {

        List<FleaParaDetail> fleaParaDetailList = getQuery(null)
                .equal(FleaConfigEntityConstants.E_PARA_TYPE, paraType)
                .equal(FleaConfigEntityConstants.E_PARA_CODE, paraCode)
                .equal(FleaConfigEntityConstants.E_PARA_STATE, EntityStateEnum.IN_USE.getState()) // 查在用状态的配置数据
                .getResultList();

        if (LOGGER.isDebugEnabled()) {
            Object obj = new Object() {};
            LOGGER.debug1(obj, "List={}", fleaParaDetailList);
            LOGGER.debug1(obj, "Count={}", fleaParaDetailList.size());
        }

        return fleaParaDetailList;
    }

}
