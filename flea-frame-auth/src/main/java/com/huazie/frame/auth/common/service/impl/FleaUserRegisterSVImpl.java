package com.huazie.frame.auth.common.service.impl;

import com.huazie.frame.auth.base.user.entity.FleaAccount;
import com.huazie.frame.auth.base.user.entity.FleaUser;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaAccountAttrSV;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaAccountSV;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaUserAttrSV;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaUserSV;
import com.huazie.frame.auth.common.exception.FleaAuthCommonException;
import com.huazie.frame.auth.common.pojo.user.register.FleaUserRegisterInfo;
import com.huazie.frame.auth.common.service.interfaces.IFleaUserRegisterSV;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p> Flea 用户注册服务实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaUserRegisterSV")
public class FleaUserRegisterSVImpl implements IFleaUserRegisterSV {

    private IFleaAccountSV fleaAccountSV; // Flea账户信息服务

    private IFleaUserSV fleaUserSV; // Flea用户信息服务

    private IFleaAccountAttrSV fleaAccountAttrSV; // Flea账户扩展属性服务

    private IFleaUserAttrSV fleaUserAttrSV; // Flea用户扩展属性服务

    @Autowired
    @Qualifier("fleaAccountSV")
    public void setFleaAccountSV(IFleaAccountSV fleaAccountSV) {
        this.fleaAccountSV = fleaAccountSV;
    }

    @Autowired
    @Qualifier("fleaUserSV")
    public void setFleaUserSV(IFleaUserSV fleaUserSV) {
        this.fleaUserSV = fleaUserSV;
    }

    @Autowired
    @Qualifier("fleaAccountAttrSV")
    public void setFleaAccountAttrSV(IFleaAccountAttrSV fleaAccountAttrSV) {
        this.fleaAccountAttrSV = fleaAccountAttrSV;
    }

    @Autowired
    @Qualifier("fleaUserAttrSV")
    public void setFleaUserAttrSV(IFleaUserAttrSV fleaUserAttrSV) {
        this.fleaUserAttrSV = fleaUserAttrSV;
    }

    @Override
    public FleaAccount register(FleaUserRegisterInfo fleaUserRegisterInfo) throws CommonException {

        String accountCode = fleaUserRegisterInfo.getAccountCode();
        if (StringUtils.isBlank(accountCode)) {
            // 账号不能为空！
            throw new FleaAuthCommonException("ERROR-AUTH-COMMON0000000001");
        }

        // 查询注册账户是否已存在
        FleaAccount oldFleaAccount = fleaAccountSV.queryAccount(accountCode, null);
        if (ObjectUtils.isNotEmpty(oldFleaAccount)) {
            //【{0}】已存在！
            throw new FleaAuthCommonException("ERROR-AUTH-COMMON0000000004");
        }

        String accountPwd = fleaUserRegisterInfo.getAccountPwd();
        if (StringUtils.isBlank(accountPwd)) {
            // 密码不能为空！
            throw new FleaAuthCommonException("ERROR-AUTH-COMMON0000000002");
        }

        String remarks = fleaUserRegisterInfo.getRemarks();

        // 新建一个flea用户
        FleaUser fleaUser = fleaUserSV.newFleaUser(accountCode, -1L, null, remarks);

        // 新建一个flea账户
        FleaAccount newFleaAccount = fleaAccountSV.newFleaAccount(fleaUser.getUserId(), accountCode, accountPwd, null, remarks);

        // 添加用户扩展属性

        // 添加账户扩展属性

        return null;
    }
}
