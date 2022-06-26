package com.huazie.fleaframework.auth.base.function;

import com.huazie.fleaframework.auth.base.function.service.interfaces.IFleaResourceSV;
import com.huazie.fleaframework.common.exception.CommonException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 已验证
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class FleaResourceCacheSVImplTest {

    @Resource(name = "fleaResourceCacheSV")
    private IFleaResourceSV fleaResourceCacheSV; // Flea资源缓存服务

    @Test
    public void queryValidResource() throws CommonException {
        fleaResourceCacheSV.queryValidResource(1L);
    }

    @Test
    public void queryValidResources() throws CommonException {
        fleaResourceCacheSV.queryValidResources("upload", "上传");
    }

    @Test
    public void queryAllValidResources() throws CommonException {
        fleaResourceCacheSV.queryAllValidResources();
    }

}