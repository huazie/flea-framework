package com.huazie.fleaframework.auth.base.function;

import com.huazie.fleaframework.auth.base.function.service.interfaces.IFleaResourceSV;
import com.huazie.fleaframework.auth.common.pojo.function.resource.FleaResourcePOJO;
import com.huazie.fleaframework.common.exception.CommonException;
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
public class FleaResourceSVImplTest {

    @Resource(name = "fleaResourceSV")
    private IFleaResourceSV fleaResourceSV;

    @Test
    public void queryValidResource() throws CommonException {
        fleaResourceSV.queryValidResource(1L);
    }

    @Test
    public void queryValidResources() throws CommonException {
        fleaResourceSV.queryValidResources("upload", "上传");
    }

    @Test
    public void queryAllValidResources() throws CommonException {
        fleaResourceSV.queryAllValidResources();
    }

    @Test
    public void saveFleaResource() throws CommonException {
        FleaResourcePOJO fleaResourcePOJO = new FleaResourcePOJO();

        fleaResourcePOJO.setResourceCode("upload");
        fleaResourcePOJO.setResourceName("上传资源");
        fleaResourcePOJO.setResourceDesc("上传资源");
        fleaResourcePOJO.setRemarks("资源保存测试");

        fleaResourceSV.saveResource(fleaResourcePOJO);
    }
}