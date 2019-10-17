package com.huazie.frame.core.base.cfgdata.dao.impl;

import com.huazie.frame.core.base.cfgdata.dao.interfaces.IFleaParaDetailDAO;
import com.huazie.frame.core.base.cfgdata.entity.FleaParaDetail;
import com.huazie.frame.core.common.EntityStateEnum;
import com.huazie.frame.core.common.FleaEntityConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaParaDetailDAOImpl.class);

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<FleaParaDetail> getParaDetail(String paraType, String paraCode) throws Exception {

        List<FleaParaDetail> fleaParaDetailList = getQuery(null)
                .equal(FleaEntityConstants.S_PARA_TYPE, paraType)
                .equal(FleaEntityConstants.S_PARA_CODE, paraCode)
                .equal(FleaEntityConstants.S_PARA_STATE, EntityStateEnum.IN_USE.getValue()) // 查在用状态的配置数据
                .getResultList();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaParaDetailDAOImpl##getParaDetail(String, String) List={}", fleaParaDetailList);
            LOGGER.debug("FleaParaDetailDAOImpl##getParaDetail(String, String) Count={}", fleaParaDetailList.size());
        }

        return fleaParaDetailList;
    }

}
