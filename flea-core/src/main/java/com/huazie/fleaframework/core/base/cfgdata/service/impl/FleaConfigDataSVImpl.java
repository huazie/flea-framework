package com.huazie.fleaframework.core.base.cfgdata.service.impl;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.core.base.cfgdata.service.interfaces.IFleaConfigDataSV;
import com.huazie.fleaframework.core.base.cfgdata.dao.interfaces.IFleaConfigDataDAO;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaConfigData;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> Flea 配置数据SV层实现类 </p>
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
@Service("fleaConfigDataSV")
public class FleaConfigDataSVImpl extends AbstractFleaJPASVImpl<FleaConfigData> implements IFleaConfigDataSV {

    private IFleaConfigDataDAO fleaConfigDataDao;

    @Autowired
    @Qualifier("fleaConfigDataDAO")
    public void setFleaConfigDataDao(IFleaConfigDataDAO fleaConfigDataDao) {
        this.fleaConfigDataDao = fleaConfigDataDao;
    }

    @Override
    public List<FleaConfigData> getConfigDatas(String configType, String configCode) throws CommonException {
        return fleaConfigDataDao.getConfigDataList(configType, configCode);
    }

    @Override
    public FleaConfigData getConfigData(String configType, String configCode) throws CommonException {

        List<FleaConfigData> fleaConfigDataList = this.getConfigDatas(configType, configCode);

        FleaConfigData fleaConfigData = null;

        if (CollectionUtils.isNotEmpty(fleaConfigDataList)) {
            fleaConfigData = fleaConfigDataList.get(0);
        }

        return fleaConfigData;
    }

    @Override
    protected IAbstractFleaJPADAO<FleaConfigData> getDAO() {
        return fleaConfigDataDao;
    }

}