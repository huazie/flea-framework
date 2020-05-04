package com.huazie.frame.core.filter;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.core.request.FleaRequestContext;
import com.huazie.frame.core.request.FleaRequestUtil;

/**
 * Copyright: Copyright (c) 2020 Asiainfo-Linkage
 *
 * @author huazie
 * @version v1.0.0
 * @ClassName FleaRequestMainTest
 * @Description
 * @date 2020-4-26
 * <p>
 * Modification History:
 * Date         Author          Version         Description
 * ---------------------------------------------------------*
 * 2020-4-26     huazie          1.0.0           新增
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
