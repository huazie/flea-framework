package com.huazie.fleaframework.auth.common;

import com.huazie.fleaframework.auth.base.organization.entity.FleaOrganization;
import com.huazie.fleaframework.auth.common.exceptions.FleaAuthCommonException;
import com.huazie.fleaframework.auth.common.pojo.organization.FleaOrganizationPOJO;
import com.huazie.fleaframework.auth.common.service.interfaces.IFleaOrgModuleSV;
import com.huazie.fleaframework.common.exceptions.CommonException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Flea组织模块SV层实现类测试
 *
 * <p> 覆盖 {@link IFleaOrgModuleSV} 全部 8 个方法：新增组织（挂根/挂父/编码重复）、
 * 修改组织（字段变更/移动父组织）、有效组织查询、用户组织关联与取消关联 </p>
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class FleaOrgModuleSVImplTest {

    @Resource(name = "fleaOrgModuleSV")
    private IFleaOrgModuleSV fleaOrgModuleSV;

    @Test
    public void addOrganizationToRoot() throws CommonException {
        // 新建组织（未指定父组织时挂到根下，层级为 1）
        FleaOrganization org = addTestOrg("ORG_T_ROOT", "根挂载测试组织");

        Assert.assertNotNull("新增组织后应能查询到有效组织数据", org);
        Assert.assertEquals("未指定父组织时应挂到根下", Long.valueOf(-1L), org.getParentId());
        Assert.assertEquals("根下组织层级应为 1", Integer.valueOf(1), org.getOrgLevel());
    }

    @Test
    public void addSubOrganization() throws CommonException {
        // 先建父组织，再在其下建子组织
        FleaOrganization parentOrg = addTestOrg("ORG_T_PARENT", "子挂载测试父组织");

        FleaOrganizationPOJO childPOJO = newOrgPOJO("ORG_T_CHILD", "子挂载测试子组织");
        childPOJO.setParentId(parentOrg.getOrgId());
        Long childOrgId = fleaOrgModuleSV.addOrganization(childPOJO);

        FleaOrganization childOrg = fleaOrgModuleSV.queryValidOrganization(childOrgId);
        Assert.assertNotNull("新增子组织后应能查询到有效组织数据", childOrg);
        Assert.assertEquals("子组织父编号应为父组织编号", parentOrg.getOrgId(), childOrg.getParentId());
        Assert.assertEquals("子组织层级应为父组织层级 + 1", Integer.valueOf(parentOrg.getOrgLevel() + 1), childOrg.getOrgLevel());
    }

    @Test(expected = FleaAuthCommonException.class)
    public void addOrganizationWithDuplicateCode() throws CommonException {
        // 组织编码全局唯一，重复新增同一编码应报错
        String orgCode = "ORG_T_DUP_" + System.currentTimeMillis();

        FleaOrganizationPOJO fleaOrganizationPOJO = newOrgPOJO(orgCode, "编码重复测试组织");
        fleaOrgModuleSV.addOrganization(fleaOrganizationPOJO);
        fleaOrgModuleSV.addOrganization(fleaOrganizationPOJO);
    }

    @Test
    public void modifyOrganization() throws CommonException {
        // 新建后仅变更可编辑字段（组织编码不支持变更）
        FleaOrganization org = addTestOrg("ORG_T_MODIFY", "修改测试组织（修改前）");

        FleaOrganizationPOJO modifyPOJO = new FleaOrganizationPOJO();
        modifyPOJO.setOrgName("修改测试组织（修改后）");
        modifyPOJO.setOrgDesc("组织模块SV修改自测数据");
        modifyPOJO.setOrgType(OrgTypeEnum.GROUP.getType());
        modifyPOJO.setRemarks("组织模块SV修改自测备注");

        fleaOrgModuleSV.modifyOrganization(org.getOrgId(), modifyPOJO);

        FleaOrganization modifiedOrg = fleaOrgModuleSV.queryValidOrganization(org.getOrgId());
        Assert.assertNotNull("修改后组织应仍然有效", modifiedOrg);
        Assert.assertEquals("组织名称应已更新", "修改测试组织（修改后）", modifiedOrg.getOrgName());
        Assert.assertEquals("组织描述应已更新", "组织模块SV修改自测数据", modifiedOrg.getOrgDesc());
        Assert.assertEquals("组织类型应已更新", OrgTypeEnum.GROUP.getType(), modifiedOrg.getOrgType());
    }

    @Test
    public void modifyOrganizationMoveToRoot() throws CommonException {
        // 先建父子两级组织，再将子组织移动到根下（层级应重算为 1）
        FleaOrganization parentOrg = addTestOrg("ORG_T_MOVE_PARENT", "移动测试父组织");

        FleaOrganizationPOJO childPOJO = newOrgPOJO("ORG_T_MOVE_CHILD", "移动测试子组织");
        childPOJO.setParentId(parentOrg.getOrgId());
        Long childOrgId = fleaOrgModuleSV.addOrganization(childPOJO);

        FleaOrganizationPOJO moveToRootPOJO = new FleaOrganizationPOJO();
        moveToRootPOJO.setOrgName("移动测试子组织（移到根下）");
        moveToRootPOJO.setOrgType(OrgTypeEnum.DEPARTMENT.getType());
        moveToRootPOJO.setParentId(-1L);

        fleaOrgModuleSV.modifyOrganization(childOrgId, moveToRootPOJO);

        FleaOrganization movedOrg = fleaOrgModuleSV.queryValidOrganization(childOrgId);
        Assert.assertNotNull("移动后组织应仍然有效", movedOrg);
        Assert.assertEquals("移动到根下后父编号应为 -1", Long.valueOf(-1L), movedOrg.getParentId());
        Assert.assertEquals("移动到根下后层级应为 1", Integer.valueOf(1), movedOrg.getOrgLevel());
    }

    @Test
    public void queryValidOrganizations() throws CommonException {
        // 按组织编号 / 组织编码查询有效组织，并查询全部有效组织
        FleaOrganization org = addTestOrg("ORG_T_QUERY", "查询测试组织");

        FleaOrganization queryOrg = fleaOrgModuleSV.queryValidOrganization(org.getOrgId());
        Assert.assertNotNull("按编号应能查询到有效组织", queryOrg);
        Assert.assertEquals("按编号查询的组织编码应一致", org.getOrgCode(), queryOrg.getOrgCode());

        FleaOrganization queryOrgByCode = fleaOrgModuleSV.queryValidOrganizationByCode(org.getOrgCode());
        Assert.assertNotNull("按编码应能查询到有效组织", queryOrgByCode);
        Assert.assertEquals("按编码查询的组织编号应一致", org.getOrgId(), queryOrgByCode.getOrgId());

        List<FleaOrganization> allValidOrgList = fleaOrgModuleSV.queryAllValidOrganizations();
        Assert.assertNotNull("全部有效组织集合不应为 null", allValidOrgList);
        Assert.assertTrue("全部有效组织集合应包含新建组织", containsOrg(allValidOrgList, org.getOrgId()));
    }

    @Test
    public void userRelOrganization() throws CommonException {
        // 用户【Huazie】关联新建组织，重复关联应幂等跳过
        Long userId = 10000L;
        FleaOrganization org = addTestOrg("ORG_T_REL", "关联测试组织");

        boolean firstRel = fleaOrgModuleSV.userRelOrganization(userId, org.getOrgId(), null);
        Assert.assertTrue("首次关联应新增成功", firstRel);

        boolean secondRel = fleaOrgModuleSV.userRelOrganization(userId, org.getOrgId(), null);
        Assert.assertFalse("重复关联应幂等跳过", secondRel);
    }

    @Test
    public void getRelUserIdsAndCancelUserOrganization() throws CommonException {
        // 关联后取已关联用户编号集，再取消关联并校验逻辑删除结果
        Long userId = 10000L;
        FleaOrganization org = addTestOrg("ORG_T_CANCEL", "取消关联测试组织");

        fleaOrgModuleSV.userRelOrganization(userId, org.getOrgId(), null);

        List<Long> relUserIds = fleaOrgModuleSV.getRelUserIds(org.getOrgId());
        Assert.assertNotNull("已关联用户编号集不应为 null", relUserIds);
        Assert.assertTrue("已关联用户编号集应包含关联用户", relUserIds.contains(userId));

        int cancelCount = fleaOrgModuleSV.cancelUserOrganization(userId, org.getOrgId());
        Assert.assertEquals("取消关联应逻辑删除 1 条关联", 1, cancelCount);

        List<Long> relUserIdsAfterCancel = fleaOrgModuleSV.getRelUserIds(org.getOrgId());
        Assert.assertFalse("取消关联后用户编号集不应再包含该用户", relUserIdsAfterCancel.contains(userId));

        int cancelAgainCount = fleaOrgModuleSV.cancelUserOrganization(userId, org.getOrgId());
        Assert.assertEquals("已取消的关联再次取消应为 0 条", 0, cancelAgainCount);
    }

    // 新建一个挂根下的测试组织并返回查询结果
    private FleaOrganization addTestOrg(String codePrefix, String orgName) throws CommonException {
        FleaOrganizationPOJO fleaOrganizationPOJO = newOrgPOJO(codePrefix + "_" + System.currentTimeMillis(), orgName);
        Long orgId = fleaOrgModuleSV.addOrganization(fleaOrganizationPOJO);
        return fleaOrgModuleSV.queryValidOrganization(orgId);
    }

    // 构造组织POJO（必填：组织编码、组织名称、组织类型）
    private FleaOrganizationPOJO newOrgPOJO(String orgCode, String orgName) {
        FleaOrganizationPOJO fleaOrganizationPOJO = new FleaOrganizationPOJO();
        fleaOrganizationPOJO.setOrgCode(orgCode);
        fleaOrganizationPOJO.setOrgName(orgName);
        fleaOrganizationPOJO.setOrgDesc("组织模块SV自测数据");
        fleaOrganizationPOJO.setOrgType(OrgTypeEnum.DEPARTMENT.getType());
        fleaOrganizationPOJO.setRemarks("FleaOrgModuleSVImplTest 自测数据");
        return fleaOrganizationPOJO;
    }

    // 判断组织集合中是否包含指定组织编号
    private boolean containsOrg(List<FleaOrganization> orgList, Long orgId) {
        for (FleaOrganization fleaOrganization : orgList) {
            if (orgId.equals(fleaOrganization.getOrgId())) {
                return true;
            }
        }
        return false;
    }
}
