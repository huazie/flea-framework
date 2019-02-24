package com.huazie.frame.common.util;

import org.junit.Test;

public class RandomCodeTest {

    @Test
    public void toNumberCode() {
        RandomCode.toNumberCode(4);
    }

    @Test
    public void toLowerLetterCode() {
        RandomCode.toLowerLetterCode(4);
    }

    @Test
    public void toUpperLetterCode() {
        RandomCode.toUpperLetterCode(4);
    }

    @Test
    public void toUUID() {
        RandomCode.toUUID();
    }
}