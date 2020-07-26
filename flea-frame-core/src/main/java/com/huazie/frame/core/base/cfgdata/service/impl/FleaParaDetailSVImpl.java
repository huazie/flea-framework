package com.huazie.frame.core.base.cfgdata.service.impl;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.core.base.cfgdata.dao.interfaces.IFleaParaDetailDAO;
import com.huazie.frame.core.base.cfgdata.entity.FleaParaDetail;
import com.huazie.frame.core.base.cfgdata.service.interfaces.IFleaParaDetailSV;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> 参数配置数据service层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaParaDetailSV")
public class FleaParaDetailSVImpl extends AbstractFleaJPASVImpl<FleaParaDetail> implements IFleaParaDetailSV {

    private final IFleaParaDetailDAO fleaParaDetailDao;

    @Autowired
    public FleaParaDetailSVImpl(@Qualifier("fleaParaDetailDAO") IFleaParaDetailDAO fleaParaDetailDao) {
        this.fleaParaDetailDao = fleaParaDetailDao;
    }

    @Override
    @Cacheable(value = "fleaparadetail", key = "#paraType")
    public List<FleaParaDetail> getParaDetails(String paraType, String paraCode) throws CommonException {
        return fleaParaDetailDao.getParaDetail(paraType, paraCode);
    }

    @Override
    @Cacheable(value = "fleaparadetail", key = "#paraType + '_' + #paraCode")
    public FleaParaDetail getParaDetail(String paraType, String paraCode) throws CommonException {

        List<FleaParaDetail> fleaParaDetails = fleaParaDetailDao.getParaDetail(paraType, paraCode);
        FleaParaDetail fleaParaDetailValue = null;

        if (CollectionUtils.isNotEmpty(fleaParaDetails)) {
            fleaParaDetailValue = fleaParaDetails.get(0);
        }

        return fleaParaDetailValue;
    }

    @Override
    protected IAbstractFleaJPADAO<FleaParaDetail> getDAO() {
        return fleaParaDetailDao;
    }

}
