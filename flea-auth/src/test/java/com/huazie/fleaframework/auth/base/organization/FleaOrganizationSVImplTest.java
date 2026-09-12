package com.huazie.fleaframework.auth.base.organization;

import com.huazie.fleaframework.auth.base.organization.entity.FleaOrganization;
import com.huazie.fleaframework.auth.base.organization.service.interfaces.IFleaOrganizationSV;
import com.huazie.fleaframework.auth.common.OrgTypeEnum;
import com.huazie.fleaframework.common.exceptions.CommonException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Flea组织SV层实现类测试
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class FleaOrganizationSVImplTest {

    @Resource(name = "fleaOrganizationSV")
    private IFleaOrganizationSV fleaOrganizationSV;

    @Test
    public void saveAndQueryOrganization() throws CommonException {
        // 新建公司组织（父组织编号为空时置 -1，表示根组织）
        FleaOrganization company = new FleaOrganization("ORG_HUAZIE", "Huazie集团", "测试用公司组织", null, 1, OrgTypeEnum.COMPANY.getType(), "组织模型扩展测试数据");
        fleaOrganizationSV.save(company);

        // 根据组织编号查询有效组织
        FleaOrganization queryOrg = fleaOrganizationSV.queryValidOrganization(company.getOrgId());
        System.out.println(queryOrg);
    }

    @Test
    public void querySubTree() throws CommonException {
        // 查询组织编号 1000 下的组织子树（不含自身）
        fleaOrganizationSV.querySubTree(1000L);
    }
}
