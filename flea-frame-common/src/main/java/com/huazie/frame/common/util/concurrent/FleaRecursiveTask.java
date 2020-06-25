package com.huazie.frame.common.util.concurrent;

import com.huazie.frame.common.FleaSessionManager;
import com.huazie.frame.common.IFleaUser;

import java.util.concurrent.RecursiveTask;

/**
 * <p> Flea RecursiveTask </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class FleaRecursiveTask<V> extends RecursiveTask<V> {

    private static final long serialVersionUID = 279788762237537615L;

    private IFleaUser fleaUser; // Flea用户信息

    /**
     * <p> 带参数的构造方法 </p>
     *
     * @param fleaUser Flea用户信息
     * @since 1.0.0
     */
    public FleaRecursiveTask(IFleaUser fleaUser) {
        this.fleaUser = fleaUser;
    }

    @Override
    protected V compute() {
        FleaSessionManager.setUserInfo(fleaUser);
        V result = subCompute();
        FleaSessionManager.setUserInfo(null);
        return result;
    }

    /**
     * <p> 子类实现该方法，完成具体的计算工作 </p>
     *
     * @return 计算结果
     * @since 1.0.0
     */
    protected abstract V subCompute();
}
