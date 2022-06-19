package com.huazie.fleaframework.auth.base.user;

import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaUserAttrSV;
import com.huazie.fleaframework.auth.common.pojo.user.attr.FleaUserAttrPOJO;
import com.huazie.fleaframework.auth.util.FleaAuthPOJOUtils;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * 已验证
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class FleaUserAttrSVImplTest {

    @Resource(name = "fleaUserAttrSV")
    private IFleaUserAttrSV fleaUserAttrSV; // Flea用户扩展属性服务

    @Test
    public void queryValidUserAttrs() throws CommonException {
        this.fleaUserAttrSV.queryValidUserAttrs(10000L);
    }

    @Test
    public void saveFleaUserAttr() throws CommonException {
        FleaUserAttrPOJO fleaUserAttrPOJO = FleaAuthPOJOUtils.newOperatorUserAttr();
        fleaUserAttrSV.saveFleaUserAttr(fleaUserAttrPOJO);
    }

    @Test
    public void saveFleaUserAttrs() throws CommonException {
        List<FleaUserAttrPOJO> fleaUserAttrPOJOList = CollectionUtils.newArrayList(FleaAuthPOJOUtils.newOperatorUserAttr());
        fleaUserAttrSV.saveFleaUserAttrs(fleaUserAttrPOJOList);
    }
}