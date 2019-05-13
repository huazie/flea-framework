package com.huazie.frame.core.base.cfgdata.dao.impl;

import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.core.base.cfgdata.dao.interfaces.IFleaParaDetailDAO;
import com.huazie.frame.core.base.cfgdata.entity.FleaParaDetail;
import com.huazie.frame.core.common.FleaEntityConstants;
import com.huazie.frame.db.jpa.common.FleaJPAQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p> 静态参数配置DAO层实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaParaDetailDAOImpl")
public class FleaParaDetailDAOImpl extends FleaConfigDAOImpl<FleaParaDetail> implements IFleaParaDetailDAO {

    private final static Logger LOGGER = LoggerFactory.getLogger(FleaParaDetailDAOImpl.class);

    @Override
    public List<FleaParaDetail> getParaDetail(String paraType, String paraCode) throws Exception {

        FleaJPAQuery query = getQuery(null);

        if (StringUtils.isNotBlank(paraType)) {
            query.equal(FleaEntityConstants.FleaParaDetailConstants.S_PARA_TYPE, paraType);
        }

        if (StringUtils.isNotBlank(paraCode)) {
            query.equal(FleaEntityConstants.FleaParaDetailConstants.S_PARA_CODE, paraCode);
        }

        query.equal(FleaEntityConstants.FleaParaDetailConstants.S_PARA_STATE, FleaEntityConstants.FleaParaDetailConstants.PARA_STATE_IN_USE);

        List<FleaParaDetail> fleaParaDetailList = query.getResultList();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaParaDetailDAOImpl##getParaDetail(String, String) FleaParaDetailList={}", fleaParaDetailList);
            LOGGER.debug("FleaParaDetailDAOImpl##getParaDetail(String, String) The Number of FleaParaDetailList={}", fleaParaDetailList.size());
        }

        return fleaParaDetailList;
    }

}
