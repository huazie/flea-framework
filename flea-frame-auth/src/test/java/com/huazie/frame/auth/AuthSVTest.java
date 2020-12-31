package com.huazie.frame.auth;

import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>  </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class AuthSVTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(AuthSVTest.class);

    @Test
    public void testListRetainAll() {
        List<String> a1 = new ArrayList<>();
        a1.add("abc");
        a1.add("bcd");
        a1.add("cde");
        a1.add("def");
        a1.add("efg");
        a1.add("fgh");

        List<String> a2 = new ArrayList<>();
        a2.add("bcd");
        a2.add("def");
        a2.add("fgh");
        a2.add("hij");

        a1.retainAll(a2);

        LOGGER.debug("a1 = {}", a1);
    }
}
