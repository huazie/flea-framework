package com.huazie.fleaframework.auth.base.function;

import com.huazie.fleaframework.auth.base.function.service.interfaces.IFleaElementSV;
import com.huazie.fleaframework.auth.common.pojo.function.element.FleaElementPOJO;
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
public class FleaElementSVImplTest {

    @Resource(name = "fleaElementSV")
    private IFleaElementSV fleaElementSV;

    @Test
    public void queryValidElement() throws CommonException {
        fleaElementSV.queryValidElement(1L);
    }

    @Test
    public void queryValidElements() throws CommonException {
        fleaElementSV.queryValidElements("element1", "元素", 1, "<html>");
    }

    @Test
    public void queryAllValidElements() throws CommonException {
        fleaElementSV.queryAllValidElements();
    }

    @Test
    public void saveFleaElement() throws CommonException {
        // 已验证
        FleaElementPOJO fleaElementPOJO = new FleaElementPOJO();

        fleaElementPOJO.setElementCode("element1");
        fleaElementPOJO.setElementName("元素1");
        fleaElementPOJO.setElementDesc("元素1");
        fleaElementPOJO.setElementType(1); // TODO 元素类型，自行定义
        fleaElementPOJO.setElementContent("<html></html>");
        fleaElementPOJO.setRemarks("元素保存测试");

        fleaElementSV.saveElement(fleaElementPOJO);
    }
}