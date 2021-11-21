package com.huazie.fleaframework.common.util.concurrent;

import com.huazie.fleaframework.common.FleaSessionManager;
import com.huazie.fleaframework.common.IFleaUser;

/**
 * <p> Flea Runnable </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class FleaRunnable implements Runnable {

    private IFleaUser fleaUser; // Flea用户信息

    /**
     * <p> 带参数的构造方法 </p>
     *
     * @param fleaUser Flea用户信息
     * @since 1.0.0
     */
    public FleaRunnable(IFleaUser fleaUser) {
        this.fleaUser = fleaUser;
    }

    @Override
    public void run() {
        FleaSessionManager.setUserInfo(fleaUser);
        subRun();
        FleaSessionManager.setUserInfo(null);
    }

    /**
     * <p> 子类实现该方法，完成具体的线程任务 </p>
     *
     * @since 1.0.0
     */
    protected abstract void subRun();
}
