package com.huazie.frame.core.base.cfgdata.service.impl;

import com.huazie.frame.core.base.cfgdata.dao.interfaces.IFleaParaDetailDAO;
import com.huazie.frame.core.base.cfgdata.entity.FleaParaDetail;
import com.huazie.frame.core.base.cfgdata.ivalues.IFleaParaDetailValue;
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
@Service("fleaParaDetailSVImpl")
public class FleaParaDetailSVImpl extends AbstractFleaJPASVImpl<FleaParaDetail> implements IFleaParaDetailSV {

    @Autowired
    @Qualifier("fleaParaDetailDAOImpl")
    private IFleaParaDetailDAO fleaParaDetailDao;

    @Override
    @Cacheable(value = "fleaparadetail", key = "#paraType")
    public List<IFleaParaDetailValue> getParaDetails(String paraType, String paraCode) throws Exception {

        List<IFleaParaDetailValue> fleaParaDetailValues = fleaParaDetailDao.getParaDetail(paraType, paraCode);
        return fleaParaDetailValues;
    }

    @Override
    @Cacheable(value = "fleaparadetail", key = "#paraType + '_' + #paraCode")
    public IFleaParaDetailValue getParaDetail(String paraType, String paraCode) throws Exception {

        List<IFleaParaDetailValue> fleaParaDetailValues = fleaParaDetailDao.getParaDetail(paraType, paraCode);
        IFleaParaDetailValue fleaParaDetailValue = null;

        if (null != fleaParaDetailValues && !fleaParaDetailValues.isEmpty()) {
            fleaParaDetailValue = fleaParaDetailValues.get(0);
        }

        return fleaParaDetailValue;
    }

    @Override
    protected IAbstractFleaJPADAO<FleaParaDetail> getDAO() {
        return fleaParaDetailDao;
    }

}
