package com.huazie.frame.core.base.cfgdata.dao.impl;

import java.util.List;

import com.huazie.frame.core.base.cfgdata.dao.interfaces.IFleaParaDetailDAO;
import com.huazie.frame.core.base.cfgdata.entity.FleaParaDetail;
import com.huazie.frame.core.base.cfgdata.ivalues.IFleaParaDetailValue;
import com.huazie.frame.core.flea.FleaManagementDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.jpa.common.FleaJPAQuery;

/**
 *
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaParaDetailDAOImpl")
public class FleaParaDetailDAOImpl extends FleaManagementDAOImpl<FleaParaDetail> implements IFleaParaDetailDAO {

    private final static Logger LOGGER = LoggerFactory.getLogger(FleaParaDetailDAOImpl.class);

    @Override
    public List<IFleaParaDetailValue> getParaDetail(String paraType, String paraCode) throws Exception {

        FleaJPAQuery query = this.getQuery(null);

        if (StringUtils.isNotBlank(paraType)) {
            query.equal(IFleaParaDetailValue.S_PARA_TYPE, paraType);
        }

        if (StringUtils.isNotBlank(paraCode)) {
            query.equal(IFleaParaDetailValue.S_PARA_CODE, paraCode);
        }

        query.equal(IFleaParaDetailValue.S_PARA_STATE, IFleaParaDetailValue.PARA_STATE_IN_USE);

        List<IFleaParaDetailValue> fleaParaDetailList = query.getResultList();

        if (FleaParaDetailDAOImpl.LOGGER.isDebugEnabled()) {
            FleaParaDetailDAOImpl.LOGGER.debug("FleaParaDetailDAOImpl##getParaDetail(paraType, paraCode) FleaParaDetailList={}", fleaParaDetailList);
            FleaParaDetailDAOImpl.LOGGER.debug("FleaParaDetailDAOImpl##getParaDetail(paraType, paraCode) The Number of FleaParaDetailList={}", fleaParaDetailList.size());
        }

        return fleaParaDetailList;
    }

}
