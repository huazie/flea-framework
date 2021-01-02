package com.huazie.frame.core.filter;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.core.request.FleaRequestContext;
import com.huazie.frame.core.request.FleaRequestUtil;

/**
 * <p>  </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaRequestMainTest {

    public static void main(String[] args) {
        Thread thread = new Thread(new DoFilterTaskRunnable());
        Thread thread1 = new Thread(new DoFilterTaskRunnable());
        Thread thread2 = new Thread(new DoFilterTaskRunnable());

        thread.start();
        thread1.start();
        thread2.start();
    }

    private static class DoFilterTaskRunnable implements Runnable {
        @Override
        public void run() {
            try {
                FleaRequestUtil.doFilterTask(new FleaRequestContext());
            } catch (CommonException e) {

            }
        }
    }
}
