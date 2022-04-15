package com.huazie.fleaframework.auth.common.pojo.user;

import com.huazie.fleaframework.auth.base.user.entity.FleaAccount;
import com.huazie.fleaframework.auth.base.user.entity.FleaAccountAttr;
import com.huazie.fleaframework.auth.base.user.entity.FleaRealNameInfo;
import com.huazie.fleaframework.auth.base.user.entity.FleaUser;
import com.huazie.fleaframework.auth.base.user.entity.FleaUserAttr;
import com.huazie.fleaframework.auth.util.FleaAuthManager;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.MapUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Flea用户模块数据
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public class FleaUserModuleData implements Serializable {

    private static final long serialVersionUID = 8241025840158828658L;

    private FleaUser fleaUser; // Flea用户

    private FleaAccount fleaAccount; // Flea账户

    private List<FleaUserAttr> fleaUserAttrs; // Flea用户扩展属性

    private List<FleaAccountAttr> fleaAccountAttrs; // Flea账户扩展属性

    private FleaRealNameInfo fleaRealNameInfo; // Flea实名信息

    public FleaUser getFleaUser() {
        return fleaUser;
    }

    public void setFleaUser(FleaUser fleaUser) {
        this.fleaUser = fleaUser;
    }

    public FleaAccount getFleaAccount() {
        return fleaAccount;
    }

    public void setFleaAccount(FleaAccount fleaAccount) {
        this.fleaAccount = fleaAccount;
    }

    /**
     * 获取用户属性的Map集合，其中键为属性码，值为属性值。
     *
     * @return 用户属性的Map集合
     * @since 2.0.0
     */
    public Map<String, Object> toUserAttrMap() {
        return toUserAttrMap(null);
    }

    /**
     * 获取用户属性的Map集合，其中键为prefix + 下划线 + 属性码，值为属性值。
     *
     * @param prefix 前缀标识
     * @return 用户属性的Map集合
     * @since 2.0.0
     */
    public Map<String, Object> toUserAttrMap(String prefix) {
        Map<String, Object> userAttrMap = null;
        if (CollectionUtils.isNotEmpty(fleaUserAttrs)) {
            userAttrMap = new HashMap<>();
            for (FleaUserAttr userAttr : fleaUserAttrs) {
                String attrCode = FleaAuthManager.generateAttrMapKey(prefix, userAttr.getAttrCode());
                userAttrMap.put(attrCode, userAttr.getAttrValue());
            }
        }
        return userAttrMap;
    }

    /**
     * 获取指定属性码对应的用户属性值
     *
     * @param attrCode 属性码
     * @return 用户属性值
     * @since 2.0.0
     */
    public Object getUserAttrValue(String attrCode) {
        Map<String, Object> userAttrMap = toUserAttrMap();
        if (MapUtils.isNotEmpty(userAttrMap))
            return userAttrMap.get(attrCode);
        return null;
    }

    public List<FleaUserAttr> getFleaUserAttrs() {
        return fleaUserAttrs;
    }

    public void setFleaUserAttrs(List<FleaUserAttr> fleaUserAttrs) {
        this.fleaUserAttrs = fleaUserAttrs;
    }

    /**
     * 获取账户属性的Map集合，其中键为属性码，值为属性值。
     *
     * @return 账户属性的Map集合
     * @since 2.0.0
     */
    public Map<String, Object> toAccountAttrMap() {
        return toAccountAttrMap(null);
    }

    /**
     * 获取账户属性的Map集合，其中键为prefix + 下划线 + 属性码，值为属性值。
     *
     * @param prefix 前缀标识
     * @return 账户属性的Map集合
     * @since 2.0.0
     */
    public Map<String, Object> toAccountAttrMap(String prefix) {
        Map<String, Object> accountAttrMap = null;
        if (CollectionUtils.isNotEmpty(fleaAccountAttrs)) {
            accountAttrMap = new HashMap<>();
            for (FleaAccountAttr accountAttr : fleaAccountAttrs) {
                String attrCode = FleaAuthManager.generateAttrMapKey(prefix, accountAttr.getAttrCode());
                accountAttrMap.put(attrCode, accountAttr.getAttrValue());
            }
        }
        return accountAttrMap;
    }

    /**
     * 获取指定属性码对应的账户属性值
     *
     * @param attrCode 属性码
     * @return 账户属性值
     * @since 2.0.0
     */
    public Object getAccountAttrValue(String attrCode) {
        Map<String, Object> accountAttrMap = toAccountAttrMap();
        if (MapUtils.isNotEmpty(accountAttrMap))
            return accountAttrMap.get(attrCode);
        return null;
    }

    public List<FleaAccountAttr> getFleaAccountAttrs() {
        return fleaAccountAttrs;
    }

    public void setFleaAccountAttrs(List<FleaAccountAttr> fleaAccountAttrs) {
        this.fleaAccountAttrs = fleaAccountAttrs;
    }

    public FleaRealNameInfo getFleaRealNameInfo() {
        return fleaRealNameInfo;
    }

    public void setFleaRealNameInfo(FleaRealNameInfo fleaRealNameInfo) {
        this.fleaRealNameInfo = fleaRealNameInfo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
