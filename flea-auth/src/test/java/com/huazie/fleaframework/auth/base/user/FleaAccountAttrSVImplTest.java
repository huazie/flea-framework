package com.huazie.fleaframework.auth.base.user;

import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaAccountAttrSV;
import com.huazie.fleaframework.auth.common.pojo.user.attr.FleaAccountAttrPOJO;
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
public class FleaAccountAttrSVImplTest {

    @Resource(name = "fleaAccountAttrSV")
    private IFleaAccountAttrSV fleaAccountAttrSV; // Flea账户扩展属性服务

    @Test
    public void queryValidAccountAttrs() throws CommonException {
        fleaAccountAttrSV.queryValidAccountAttrs(10000L);
    }

    @Test
    public void saveFleaAccountAttr() throws CommonException {
        FleaAccountAttrPOJO fleaAccountAttrPOJO = FleaAuthPOJOUtils.newOperatorAccountAttr();
        fleaAccountAttrSV.saveFleaAccountAttr(fleaAccountAttrPOJO);
    }

    @Test
    public void saveFleaAccountAttrs() throws CommonException {
        List<FleaAccountAttrPOJO> fleaAccountAttrPOJOS = CollectionUtils.newArrayList(FleaAuthPOJOUtils.newOperatorAccountAttr());
        fleaAccountAttrSV.saveFleaAccountAttrs(fleaAccountAttrPOJOS);
    }

}