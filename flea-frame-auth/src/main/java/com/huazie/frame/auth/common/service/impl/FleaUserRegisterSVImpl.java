package com.huazie.frame.auth.common.service.impl;

import com.huazie.frame.auth.base.user.entity.FleaAccount;
import com.huazie.frame.auth.base.user.entity.FleaUser;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaAccountAttrSV;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaAccountSV;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaUserAttrSV;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaUserSV;
import com.huazie.frame.auth.common.exception.FleaAuthCommonException;
import com.huazie.frame.auth.common.pojo.user.register.FleaUserRegisterPOJO;
import com.huazie.frame.auth.common.service.interfaces.IFleaUserRegisterSV;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional("fleaAuthTransactionManager")
    public FleaAccount register(FleaUserRegisterPOJO fleaUserRegisterInfo) throws CommonException {

        // 校验用户注册信息对象是否为空
        // ERROR-AUTH-COMMON0000000001 【{0}】不能为空
        ObjectUtils.checkEmpty(fleaUserRegisterInfo, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", new String[]{"FleaUserRegisterInfo"});

        // 校验账号是否为空
        // ERROR-AUTH-COMMON0000000002 账号不能为空！
        String accountCode = fleaUserRegisterInfo.getAccountCode();
        StringUtils.checkBlank(accountCode, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000002");

        // 校验待注册账户是否已存在
        // ERROR-AUTH-COMMON0000000003 【{0}】已存在！
        // TODO 验证账户是否存在（状态 1 和 3）
        FleaAccount oldFleaAccount = fleaAccountSV.queryAccount(accountCode, null);
        ObjectUtils.checkNotEmpty(oldFleaAccount, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000005", new String[]{accountCode});

        // 校验密码是否为空
        // ERROR-AUTH-COMMON0000000003 密码不能为空！
        String accountPwd = fleaUserRegisterInfo.getAccountPwd();
        StringUtils.checkBlank(accountPwd, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000003");

        // 新建一个flea用户
        FleaUser fleaUser = fleaUserSV.saveFleaUser(fleaUserRegisterInfo.newFleaUserPOJO());
        // 将用户信息持久化到数据库中，否则同一事物下，无法获取userId
        fleaUserSV.flush();

        Long userId = fleaUser.getUserId();
        // 新建一个flea账户
        FleaAccount newFleaAccount = fleaAccountSV.saveFleaAccount(fleaUserRegisterInfo.newFleaAccountPOJO(userId));
        // 将账户信息持久化到数据库中，否则同一事物下，无法获取accountId
        fleaAccountSV.flush();

        // 用户扩展属性批量设置用户编号
        fleaUserRegisterInfo.setUserId(userId);
        // 添加用户扩展属性
        fleaUserAttrSV.saveFleaUserAttrs(fleaUserRegisterInfo.getUserAttrList());

        // 账户扩展属性批量设置账户编号
        fleaUserRegisterInfo.setAccountId(newFleaAccount.getAccountId());
        // 添加账户扩展属性
        fleaAccountAttrSV.saveFleaAccountAttrs(fleaUserRegisterInfo.getAccountAttrList());

        return newFleaAccount;
    }
}
