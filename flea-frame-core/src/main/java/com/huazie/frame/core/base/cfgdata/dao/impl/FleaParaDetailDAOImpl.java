package com.huazie.frame.core.base.cfgdata.dao.impl;

import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.core.base.cfgdata.dao.interfaces.IFleaParaDetailDAO;
import com.huazie.frame.core.base.cfgdata.entity.FleaParaDetail;
import com.huazie.frame.core.common.EntityStateEnum;
import com.huazie.frame.core.common.FleaEntityConstants;
import com.huazie.frame.db.jpa.common.FleaJPAQuery;
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
@Repository("fleaParaDetailDAOImpl")
public class FleaParaDetailDAOImpl extends FleaConfigDAOImpl<FleaParaDetail> implements IFleaParaDetailDAO {

    private final static Logger LOGGER = LoggerFactory.getLogger(FleaParaDetailDAOImpl.class);

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<FleaParaDetail> getParaDetail(String paraType, String paraCode) throws Exception {

        FleaJPAQuery query = getQuery(null);

        if (StringUtils.isNotBlank(paraType)) {
            query.equal(FleaEntityConstants.FleaParaDetailConstants.S_PARA_TYPE, paraType);
        }

        if (StringUtils.isNotBlank(paraCode)) {
            query.equal(FleaEntityConstants.FleaParaDetailConstants.S_PARA_CODE, paraCode);
        }
        // 查正常状态的数据
        query.equal(FleaEntityConstants.FleaParaDetailConstants.S_PARA_STATE, EntityStateEnum.IN_USE.getValue());

        List<FleaParaDetail> fleaParaDetailList = query.getResultList();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaParaDetailDAOImpl##getParaDetail(String, String) List={}", fleaParaDetailList);
            LOGGER.debug("FleaParaDetailDAOImpl##getParaDetail(String, String) Count={}", fleaParaDetailList.size());
        }

        return fleaParaDetailList;
    }

}
