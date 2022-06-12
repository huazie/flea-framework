package com.huazie.fleaframework.common.util.concurrent;

import com.huazie.fleaframework.common.FleaSessionManager;
import com.huazie.fleaframework.common.IFleaUser;

import java.util.concurrent.RecursiveAction;

/**
 * Flea RecursiveAction
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class FleaRecursiveAction extends RecursiveAction {

    private static final long serialVersionUID = -7536024386915815575L;

    private IFleaUser fleaUser; // Flea用户信息

    /**
     * 带参数的构造方法
     *
     * @param fleaUser Flea用户信息
     * @since 1.0.0
     */
    public FleaRecursiveAction(IFleaUser fleaUser) {
        this.fleaUser = fleaUser;
    }

    @Override
    protected void compute() {
        FleaSessionManager.setUserInfo(fleaUser);
        subCompute();
        FleaSessionManager.setUserInfo(null);
    }

    /**
     * 子类实现该方法，完成具体的计算工作
     *
     * @since 1.0.0
     */
    protected abstract void subCompute();
}
