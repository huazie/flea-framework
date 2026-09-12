package com.huazie.fleaframework.auth.common.service.interfaces;

import com.huazie.fleaframework.auth.base.function.entity.FleaMenu;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserModuleData;
import com.huazie.fleaframework.common.exceptions.CommonException;

import java.util.List;

/**
 * Flea 授权服务接口类，对外提供可缓存的授权数据查询API。
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public interface IFleaAuthSV {

    /**
     * 根据账户编号获取用户模块数据，包含用户，账户，
     * 用户扩展属性，账户扩展属性，用户实名信息。
     *
     * @param accountId 账户编号
     * @return 用户模块数据
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaUserModuleData getFleaUserModuleData(Long accountId) throws CommonException;

    /**
     * 获取指定用户下的角色编号集
     *
     * @param userId 用户编号
     * @return 角色编号集
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<Long> getUserRoles(Long userId) throws CommonException;

    /**
     * 获取指定用户下的权限编号集
     *
     * @param userId 用户编号
     * @return 权限编号集
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<Long> getUserPrivileges(Long userId) throws CommonException;

    /**
     * 获取指定操作账户可以访问的指定系统账户下的所有的菜单。
     *
     * @param accountId       操作账户编号
     * @param systemAccountId 系统帐户编号
     * @return 所有可以访问的菜单
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaMenu> queryAllAccessibleMenus(Long accountId, Long systemAccountId) throws CommonException;

    /**
     * 校验资源授权，如果允许指定操作账号，调用指定系统账户下的资源，
     * 则校验通过，返回true；否则校验失败，返回false。
     *
     * @param accountId       账户编号
     * @param systemAccountId 系统账户编号
     * @param resourceCode    资源编码
     * @return true：允许调用该资源  false：不允许调用该资源
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    boolean checkResourceAuth(Long accountId, Long systemAccountId, String resourceCode) throws CommonException;

    /**
     * 获取指定用户的数据范围允许的组织编号集。
     *
     * <p> 解析规则：取用户组织集 U，对用户全部角色关联的数据范围（rel_type = ROLE_REL_DATA_SCOPE）逐一展开；
     * 多角色叠加采用最宽松策略——任一角色为【全部数据】则整体无约束，否则对各角色范围取并集。
     * 角色未绑定数据范围时，视为无约束（兼容既有角色数据）。 </p>
     *
     * <p> 注意：数据范围类型为【仅本人 SELF】时，不贡献组织编号，行级过滤需由调用方
     * 追加 create_user_id = userId 条件。 </p>
     *
     * @param userId 用户编号
     * @return {@code null}：无约束（允许访问全部数据）；
     *         非 {@code null}：允许的组织编号集（可能为空，空集表示无数据权限）
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<Long> getDataScopeOrgIds(Long userId) throws CommonException;

    /**
     * 校验数据范围，判断指定用户是否可以访问归属指定组织的数据。
     *
     * <p> 注意：该方法仅校验组织维度的数据权限；数据范围为【仅本人 SELF】的行级数据，
     * 需由调用方额外按 create_user_id = userId 过滤。 </p>
     *
     * @param userId   用户编号
     * @param rowOrgId 数据归属的组织编号
     * @return true：允许访问该组织的数据  false：不允许访问该组织的数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    boolean checkDataScope(Long userId, Long rowOrgId) throws CommonException;

}
