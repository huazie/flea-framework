package com.huazie.fleaframework.algorithm.leetcode.simple;

import com.huazie.fleaframework.algorithm.leetcode.simple.s1700.CountStudents;
import org.junit.Test;

public class CountStudentsTest {

    private final CountStudents countStudents = new CountStudents();

    @Test
    public void testCountStudents() {
        int[] students = {1,1,0,0};
        int[] sandwiches = {0,1,0,1};
        System.out.println(countStudents.countStudents(students, sandwiches));
    }
}