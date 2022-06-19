package com.huazie.fleaframework.auth.base.function.service.impl;

import com.huazie.fleaframework.auth.base.function.dao.interfaces.IFleaOperationDAO;
import com.huazie.fleaframework.auth.base.function.entity.FleaOperation;
import com.huazie.fleaframework.auth.base.function.service.interfaces.IFleaOperationSV;
import com.huazie.fleaframework.auth.common.pojo.function.operation.FleaOperationPOJO;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Flea操作SV层实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@Service("fleaOperationSV")
public class FleaOperationSVImpl extends AbstractFleaJPASVImpl<FleaOperation> implements IFleaOperationSV {

    private IFleaOperationDAO fleaOperationDao;

    @Autowired
    @Qualifier("fleaOperationDAO")
    public void setFleaOperationDao(IFleaOperationDAO fleaOperationDao) {
        this.fleaOperationDao = fleaOperationDao;
    }

    @Override
    public FleaOperation queryValidOperation(Long operationId) throws CommonException {
        return this.fleaOperationDao.queryValidOperation(operationId);
    }

    @Override
    public List<FleaOperation> queryValidOperations(String operationCode, String operationName) throws CommonException {
        return this.fleaOperationDao.queryValidOperations(operationCode, operationName);
    }

    @Override
    public List<FleaOperation> queryAllValidOperations() throws CommonException {
        return this.fleaOperationDao.queryValidOperations(null, null);
    }

    @Override
    public FleaOperation saveOperation(FleaOperationPOJO fleaOperationPOJO) throws CommonException {
        FleaOperation fleaOperation = newFleaOperation(fleaOperationPOJO);
        // 保存Flea操作数据
        this.save(fleaOperation);
        return fleaOperation;
    }

    /**
     * 新建Flea操作数据
     *
     * @param fleaOperationPOJO Flea操作POJO对象
     * @return Flea操作数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    private FleaOperation newFleaOperation(FleaOperationPOJO fleaOperationPOJO) throws CommonException {
        // 校验Flea操作POJO对象
        FleaAuthCheck.checkFleaOperationPOJO(fleaOperationPOJO);

        return new FleaOperation(fleaOperationPOJO.getOperationCode(), fleaOperationPOJO.getOperationName(),
                fleaOperationPOJO.getOperationDesc(), fleaOperationPOJO.getEffectiveDate(),
                fleaOperationPOJO.getExpiryDate(), fleaOperationPOJO.getRemarks());
    }

    @Override
    protected IAbstractFleaJPADAO<FleaOperation> getDAO() {
        return fleaOperationDao;
    }
}