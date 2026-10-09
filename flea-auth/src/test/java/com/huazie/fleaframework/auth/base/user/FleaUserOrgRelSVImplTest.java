package com.huazie.fleaframework.auth.base.user;

import com.huazie.fleaframework.auth.base.user.entity.FleaUserOrgRel;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaUserOrgRelSV;
import com.huazie.fleaframework.common.exceptions.CommonException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Flea用户组织关联SV层实现类测试
 *
 * <p> 对应数据表：flea_user_org_rel </p>
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class FleaUserOrgRelSVImplTest {

    @Resource(name = "fleaUserOrgRelSV")
    private IFleaUserOrgRelSV fleaUserOrgRelSV;

    @Test
    public void saveUserOrgRel() throws CommonException {
        // 新建用户组织关联数据（isPrimary: 1 表示主组织）
        FleaUserOrgRel userOrgRel = new FleaUserOrgRel(1000L, 1000L, 1, "用户组织关联测试数据");
        fleaUserOrgRelSV.save(userOrgRel);
        System.out.println(userOrgRel);
    }

    @Test
    public void getUserOrgRelList() throws CommonException {
        // 根据用户编号查询有效的用户组织关联数据（orgId 为空表示不限制组织条件）
        List<FleaUserOrgRel> userOrgRelList = fleaUserOrgRelSV.getUserOrgRelList(1000L, null);
        System.out.println(userOrgRelList);
    }

    @Test
    public void getUserOrgIds() throws CommonException {
        // 获取用户 1000 的组织编号集
        List<Long> userOrgIdList = fleaUserOrgRelSV.getUserOrgIds(1000L);
        System.out.println(userOrgIdList);
    }
}
