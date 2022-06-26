package com.huazie.fleaframework.common.util;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;

import java.util.List;

/**
 *
 */
public class POJOUtilsTest {

    private FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(POJOUtilsTest.class);

    @Test
    public void copy() {
        TestA testA = new TestA();
        testA.setTest2(10);
        testA.setList(CollectionUtils.newArrayList("BBBB"));

        TestB testB = new TestB();
        testB.setTest1("AAAA");
        testB.setTest2(12);

        POJOUtils.copyNotEmpty(testA, testB);

        LOGGER.debug("Test1 = {}", testA);
        LOGGER.debug("Test2 = {}", testB);
    }

    class TestA {
        String test1;

        Integer test2;

        List<String> list;

        public String getTest1() {
            return test1;
        }

        public void setTest1(String test1) {
            this.test1 = test1;
        }

        public Integer getTest2() {
            return test2;
        }

        public void setTest2(Integer test2) {
            this.test2 = test2;
        }

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }

    class TestB {
        String test1;

        Integer test2;

        List<String> list;

        public String getTest1() {
            return test1;
        }

        public void setTest1(String test1) {
            this.test1 = test1;
        }

        public Integer getTest2() {
            return test2;
        }

        public void setTest2(Integer test2) {
            this.test2 = test2;
        }

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }
}