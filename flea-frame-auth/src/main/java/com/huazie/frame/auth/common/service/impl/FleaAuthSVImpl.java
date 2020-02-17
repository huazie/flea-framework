package com.huazie.frame.auth.common.service.impl;

import com.huazie.frame.auth.common.service.interfaces.IFleaAuthSV;
import com.huazie.frame.common.FleaFrameManager;
import com.huazie.frame.common.IFleaUser;
import com.huazie.frame.common.object.FleaObjectFactory;
import com.huazie.frame.common.util.MapUtils;
import com.huazie.frame.common.util.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

/**
 * <p> Flea 授权服务实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaAuthSV")
public class FleaAuthSVImpl implements IFleaAuthSV {

    @Override
    public void initUserInfo(Long userId, Long acctId, Long systemAcctId, Map<String, Object> otherAttrs, FleaObjectFactory<IFleaUser> fleaObjectFactory) {
        IFleaUser fleaUser = FleaFrameManager.getManager().getUserInfo();
        if (ObjectUtils.isEmpty(fleaUser)) {
            fleaUser = fleaObjectFactory.newObject().getObject();

            if (ObjectUtils.isNotEmpty(userId)) {
                fleaUser.setUserId(userId);
            }

            if (ObjectUtils.isNotEmpty(acctId)) {
                fleaUser.setAcctId(acctId);
            }

            if (ObjectUtils.isNotEmpty(systemAcctId)) {
                fleaUser.setSystemAcctId(systemAcctId);
            }

            if (MapUtils.isNotEmpty(otherAttrs)) {
                Set<String> attrKeySet = otherAttrs.keySet();
                for (String key : attrKeySet) {
                    Object value = otherAttrs.get(key);
                    fleaUser.set(key, value);
                }
            }

            FleaFrameManager.getManager().setUserInfo(fleaUser);
        }
    }

}
