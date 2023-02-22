package com.huazie.fleaframework.auth.base.function;

import com.huazie.fleaframework.auth.base.function.service.interfaces.IFleaOperationSV;
import com.huazie.fleaframework.auth.common.pojo.function.operation.FleaOperationPOJO;
import com.huazie.fleaframework.common.exceptions.CommonException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 *
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class FleaOperationSVImplTest {

    @Resource(name = "fleaOperationSV")
    private IFleaOperationSV fleaOperationSV;

    @Test
    public void queryValidOperation() throws CommonException {
        fleaOperationSV.queryValidOperation(1L);
    }

    @Test
    public void queryValidOperations() throws CommonException {
        fleaOperationSV.queryValidOperations("operation", "操作");
    }

    @Test
    public void queryAllValidOperations() throws CommonException {
        fleaOperationSV.queryAllValidOperations();
    }

    @Test
    public void saveFleaOperation() throws CommonException {

        FleaOperationPOJO fleaOperationPOJO = new FleaOperationPOJO();

        fleaOperationPOJO.setOperationCode("operation");
        fleaOperationPOJO.setOperationName("操作1");
        fleaOperationPOJO.setOperationDesc("操作1");
        fleaOperationPOJO.setRemarks("操作保存测试");

        fleaOperationSV.saveOperation(fleaOperationPOJO);
    }
}